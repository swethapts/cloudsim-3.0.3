/*
 * Title: CloudSim Toolkit Description: CloudSim (Cloud Simulation) Toolkit for Modeling and
 * Simulation of Clouds Licence: GPL - http://www.gnu.org/copyleft/gpl.html
 * 
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.cloudbus.cloudsim;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.cloudbus.cloudsim.lists.PeListSteady;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSteady;

/**
 * VmSchedulerTimeSharedSteady is a VMM allocation policy that allocates one or more PeSteady to a VM, and
 * allows sharing of PEs by multiple VMs. This class also implements 10% performance degration due
 * to VM migration. This scheduler does not support over-subscription.
 * 
 * @author Rodrigo N. Calheiros
 * @author Anton Beloglazov
 * @since CloudSim Toolkit 1.0
 */
public class VmSchedulerTimeSharedSteady extends VmSchedulerSteady {

	/** The mips map requested. */
	private Map<String, List<Double>> mipsMapRequested;

	/** The pes in use. */
	private int pesInUse;

	/**
	 * Instantiates a new vm scheduler time shared.
	 * 
	 * @param pelist the pelist
	 */
	public VmSchedulerTimeSharedSteady(List<? extends PeSteady> pelist) {
		super(pelist);
		setMipsMapRequested(new TreeMap<String, List<Double>>());
	}

	/*
	 * (non-Javadoc)
	 * @see cloudsim.VmSchedulerSteady#allocatePesForVm(cloudsim.VmSteady, java.util.List)
	 */
	@Override
	public boolean allocatePesForVm(VmSteady vm, List<Double> mipsShareRequested) {
		/**
		 * TODO: add the same to RAM and BW provisioners
		 */
		if (vm.isInMigration()) {
			if (!getVmsMigratingIn().contains(vm.getUid()) && !getVmsMigratingOut().contains(vm.getUid())) {
				getVmsMigratingOut().add(vm.getUid());
			}
		} else {
			if (getVmsMigratingOut().contains(vm.getUid())) {
				getVmsMigratingOut().remove(vm.getUid());
			}
		}
		boolean result = allocatePesForVm(vm.getUid(), mipsShareRequested);
		updatePeProvisioning();
		return result;

	}

	/**
	 * Allocate pes for vm.
	 * 
	 * @param vmUid the vm uid
	 * @param mipsShareRequested the mips share requested
	 * @return true, if successful
	 */
	protected boolean allocatePesForVm(String vmUid, List<Double> mipsShareRequested) {
		double totalRequestedMips = 0;
		double peMips = getPeCapacity();
		for (Double mips : mipsShareRequested) {
			// each virtual PE of a VM must require not more than the capacity of a physical PE
			if (mips > peMips) {
				return false;
			}
			totalRequestedMips += mips;
		}
		
		// This scheduler does not allow over-subscription
		if (getAvailableMips() < totalRequestedMips) {
			return false;
		}

		getMipsMapRequested().put(vmUid, mipsShareRequested);
		setPesInUse(getPesInUse() + mipsShareRequested.size());

		if (getVmsMigratingIn().contains(vmUid)) {
			// the destination host only experience 10% of the migrating VM's MIPS
			totalRequestedMips *= 0.1;
		}

		List<Double> mipsShareAllocated = new ArrayList<Double>();
		for (Double mipsRequested : mipsShareRequested) {
			if (getVmsMigratingOut().contains(vmUid)) {
				// performance degradation due to migration = 10% MIPS
				mipsRequested *= 0.9;
			} else if (getVmsMigratingIn().contains(vmUid)) {
				// the destination host only experience 10% of the migrating VM's MIPS
				mipsRequested *= 0.1;
			}
			mipsShareAllocated.add(mipsRequested);
		}

		getMipsMap().put(vmUid, mipsShareAllocated);
		setAvailableMips(getAvailableMips() - totalRequestedMips);
		return true;
	}

	/**
	 * Update allocation of VMs on PEs.
	 */
	protected void updatePeProvisioning() {
		getPeMap().clear();
		for (PeSteady pe : getPeList()) {
			System.out.println("deallocating "+pe.getId()+pe.getPeProvisioner().getTotalAllocatedMips());
			pe.getPeProvisioner().deallocateMipsForAllVms();
		}

		Iterator<PeSteady> peIterator = getPeList().iterator();
		PeSteady pe = peIterator.next();
		PeProvisionerSteady peProvisioner = pe.getPeProvisioner();
		double availableMips = peProvisioner.getAvailableMips();

		for (Map.Entry<String, List<Double>> entry : getMipsMap().entrySet()) {
			String vmUid = entry.getKey();
			getPeMap().put(vmUid, new LinkedList<PeSteady>());

			for (double mips : entry.getValue()) {
				while (mips >= 0.1) {
					if (availableMips >= mips) {
						peProvisioner.allocateMipsForVm(vmUid, mips);
						getPeMap().get(vmUid).add(pe);
						availableMips -= mips;
						break;
					} else {
						peProvisioner.allocateMipsForVm(vmUid, availableMips);
						getPeMap().get(vmUid).add(pe);
						mips -= availableMips;
						if (mips <= 0.1) {
							break;
						}
						if (!peIterator.hasNext()) {
							Log.printLine("There is no enough MIPS (" + mips + ") to accommodate VM " + vmUid);
							// System.exit(0);
						}
						pe = peIterator.next();
						peProvisioner = pe.getPeProvisioner();
						availableMips = peProvisioner.getAvailableMips();
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cloudsim.VmSchedulerSteady#deallocatePesForVm(cloudsim.VmSteady)
	 */
	@Override
	public void deallocatePesForVm(VmSteady vm) {
		getMipsMapRequested().remove(vm.getUid());
		setPesInUse(0);
		getMipsMap().clear();
		setAvailableMips(PeListSteady.getTotalMips(getPeList()));

		for (PeSteady pe : getPeList()) {
			pe.getPeProvisioner().deallocateMipsForVm(vm);
		}

		for (Map.Entry<String, List<Double>> entry : getMipsMapRequested().entrySet()) {
			allocatePesForVm(entry.getKey(), entry.getValue());
		}

		updatePeProvisioning();
	}

	/**
	 * Releases PEs allocated to all the VMs.
	 * 
	 * @pre $none
	 * @post $none
	 */
	@Override
	public void deallocatePesForAllVms() {
		super.deallocatePesForAllVms();
		getMipsMapRequested().clear();
		setPesInUse(0);
	}

	/**
	 * Returns maximum available MIPS among all the PEs. For the time shared policy it is just all
	 * the avaiable MIPS.
	 * 
	 * @return max mips
	 */
	@Override
	public double getMaxAvailableMips() {
		return getAvailableMips();
	}

	/**
	 * Sets the pes in use.
	 * 
	 * @param pesInUse the new pes in use
	 */
	protected void setPesInUse(int pesInUse) {
		this.pesInUse = pesInUse;
	}

	/**
	 * Gets the pes in use.
	 * 
	 * @return the pes in use
	 */
	protected int getPesInUse() {
		return pesInUse;
	}

	/**
	 * Gets the mips map requested.
	 * 
	 * @return the mips map requested
	 */
	protected Map<String, List<Double>> getMipsMapRequested() {
		return mipsMapRequested;
	}

	/**
	 * Sets the mips map requested.
	 * 
	 * @param mipsMapRequested the mips map requested
	 */
	protected void setMipsMapRequested(Map<String, List<Double>> mipsMapRequested) {
		this.mipsMapRequested = mipsMapRequested;
	}

}