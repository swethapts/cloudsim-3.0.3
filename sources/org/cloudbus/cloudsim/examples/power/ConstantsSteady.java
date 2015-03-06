package org.cloudbus.cloudsim.examples.power;

import java.util.*;
import org.cloudbus.cloudsim.power.models.PowerModelSteady;
//import org.cloudbus.cloudsim.power.models.PowerModelE5507;
import org.cloudbus.cloudsim.power.models.PowerModeli7;

/**
 * If you are using any algorithms, policies or workload included in the power package, please cite
 * the following paper:
 *
 * Anton Beloglazov, and Rajkumar Buyya, "Optimal Online Deterministic Algorithms and Adaptive
 * Heuristics for Energy and Performance Efficient Dynamic Consolidation of Virtual Machines in
 * Cloud Data Centers", Concurrency and Computation: Practice and Experience (CCPE), Volume 24,
 * Issue 13, Pages: 1397-1420, John Wiley & Sons, Ltd, New York, USA, 2012
 *
 * @author Anton Beloglazov
 * @since Jan 6, 2012
 */
public class ConstantsSteady {

	public final static boolean ENABLE_OUTPUT = true;
	public final static boolean OUTPUT_CSV    = false;

	public final static double SCHEDULING_INTERVAL = 1;//300;
	public final static double SIMULATION_LIMIT = 30000;//43200;//24 * 60 * 60;

	public final static int[] CLOUDLET_LENGTH	= {2500000,4000000};//mi //(int) SIMULATION_LIMIT;
	public final static int CLOUDLET_PES	= 1;
	public final static int CLOUDLET_TYPES = CLOUDLET_LENGTH.length;

	/*
	 * VM instance types:
	 *   High-Memory Extra Large Instance: 3.25 EC2 Compute Units, 8.55 GB // too much MIPS
	 *   High-CPU Medium Instance: 2.5 EC2 Compute Units, 0.85 GB
	 *   Extra Large Instance: 2 EC2 Compute Units, 3.75 GB
	 *   Small Instance: 1 EC2 Compute Unit, 1.7 GB
	 *   Micro Instance: 0.5 EC2 Compute Unit, 0.633 GB
	 *   We decrease the memory size two times to enable oversubscription
	 *
	 */
	
	private final static int[][] VM_CT_SLA_UNSORTED = {{6794,5004,6067,5196,4307,5351,3942,4516,7194,2015},//,4717,4910,4498,5683,4871,5888,3161,4055,6401,4896,4078,5253,5571,6075,6723,4594,4930,4052,5169,3440,3759,4673,5713,3775,5627,4515,5016,5586,5718,5112,5427,3974,4598,4056,7991,3418,5108,6390,3838,5474},
														{3357,3817,3502,4596,2949,5562,4235,4730,4135,3615}};//,2576,4095,3570,6112,3483,2659,3987,3881,3403,4085,3141,4094,3194,3707,1821,3400,4907,4792,4268,1676,3303,3565,1060,2380,2458,2970,3466,1917,3400,3123,2434,3517,3040,2807,4867,4349,3199,3383,5220,2264,}}; 
		//{4908,5902,5991,4042,3907,4556,3799,3806,4488,4049,6984,5845,7797,6107,4873,4531,5181,7831,6617,4932,5684,2234,6183,5977,5194,4960,6648,3357,3187,6147,1365,4882,6028,5057,5166,6316,3970,3900,3406,5099,3283,4994,6101,4147,5054,5080,5195,8033,4241,4044,2591,5289,4088,4144,4187,4045,3861,4538,4045,6532,5802,2848,4119,4694,3748,4754,3998,3612,5958,1341,3827,4051,4759,6484,5981,5956,6400,4198,7392,5162,2827,4589,4031,2283,4303,6222,1201,5733,4703,5723,5919,7411,3576,5571,6729,8231,8300,1976,4385,6984,7315,4432,7003,3610,5997,3033,6210,5400,2626,4334,3943,1862,3212,6124,2592,6241,1172,4851,4815,5909,3128,4476,5939,4561,7971,5214,5708,7975,4324,6677,3921,5690,5288,5777,6115,6032,6971,3323,4669,4839,5738,5187,5217,3748,6335,4103,4440,7250,5109,5422,5792,6788,6955,4359,7427,5571,5045,3430,3415,3936,5419,3608,4638,4711,4895,6237,5195,3709,6130,3527,5030,6514,7161,4736,2876,8971,6206,5212,3961,5297,3455,6426,5960,7474,3909,7240,6330,4235,4329,6865,4244,5979,4386,4422,3625,4624,5497,8424,5139,4213,2312,5538,4470,2105,4393,4977,5610,4773,4698,3030,2519,5608,4527,5788,4135,4087,6547,4542,6160,5625,4484,4622,3083,4992,3320,8289,5187,4278,5267,5416,5376,3078,5394,4854,3572,5553,2631,5974,5125,4547,7340,4504,6041,3948,5838,7237,4913,7650,6930,5139,5720,3108,7580,2904,6000,3009,6347,4756,2521,5733,5170,3984,3927,3552,3984,6617,4868,8460,6096,3678,4785,6868,4368,6527,5554,4872,4354,5883,3941,3454,2513,7027,3769,3034,6259,5589,3488,4050,4214,5186,6018,4679,6540,4824,6150,5789,4398,4603,5286,4312,4906,5177,7105,4817,5535,5717,6306,4886,6102,5307,1362,4103,6412,6555,2796,5523,3043,7809,6870,5055,7237,1680,4344,4210,4602,5331,5419,4358,6510,2932,5924,4624,2666,4340,4642,4650,3931,2780,5362,2351,4225,2983,5498,5993,1951,6582,5345,6002,4479,4846,3595,6764,5140,5358,5295,4848,3774,3353,4886,5203,2775,3128,4768,3317,5684,2351,1212,2539,5341,4074,4800,6225,5447,5893,7138,5851,7630,2223,7796,5684,7302,3363,6572,2568,2605,3463,5179,2812,4681,6454,7707,4153,5262,2434,5384,4799,4862,3543,7790,5435,6524,4844,3216,3268,3786,5133,3988,3354,6651,5258,6949,5801,5191,6287,4424,5228,2993,5856,6077,4444,4638,3038,6301,4803,5046,5777,2109,6349,5102,7460,7498,4861,5213,7251,3397,4750,5775,3951,6113,3834,4892,5497,3139,2401,4460,4124,6115,7953,5872,3782,4299,4704,7457,6909,6432,4702,5129,5572,5867,7299,5578,3257,6222,5998,5779,5769,2949,6283,4319,8568,6075,4979,2437,9657,5147,4377,3311,3446,6840,2560,2941,6994,6407,4546,2440,4245,5608,4395,6851,5049,4754,4696,4735,3437,5740,7204,7257,4439,5354,4858,5440,3864,5509,3534,3106,6244,7921,6567,5363,6426,4087,5612,4314,3533,1457,5242,4299,2920,5513,8038,6714,5818,5900,4709,5043,5784,5832,6366,6695,7194,4665,4111,7748,5301,2387,5061,4383,4123,2381,4381,3028,4140,5785,4103,3907,3360,4052,6249,6907,6032,6443,6569,6972,5706,4274,5209,6134,4938,3951,6024,3179,1647,4484,4474,4645,4934,4860,3703,6604,5127,2860,4477,884,5539,5744,5813,4091,5751,3855,4283,6232,3613,5565,5398,7091,4278,6158,5660,5469,7198,3496,6882,3928,3389,3355,6440,5456,2136,2561,5923,4336,3333,5395,2839,3619,6176,2568,5246,4447,5816,5472,3885,2510,3151,4911,4890,4841,3670,5626,6438,6266,4057,5357,4102,3927,2489,4306,5058,5550,6056,5959,5912,4159,5811,8186,5671,5175,5337,6666,5640,4878,6884,6344,3212,6355,6316,4464,3907,8873,2448,2865,5025,6208,5962,3531,5912,5121,6821,4672,7611,4053,4963,3318,7141,3900,4516,4446,7348,5877,6121,3598,4490,5222,4321,3745,6460,3003,6776,4609,7082,7655,6401,4879,6666,6270,7120,4661,4863,2858,5199,3140,7135,4592,3020,1780,7863,6518,2537,6018,3775,5230,4675,780,7087,6230,5026,5403,5339,4908,7629,1275,4422,6475,5226,5216,6053,5231,4799,2765,2858,4535,3961,4621,4141,7156,3219,4626,6547,5779,8241,3706,3604,4382,5712,3918,5186,5394,6618,4622,5211,4818,6217,7097,2509,4306,4452,5987,5881,4642,5935,4149,5576,5060,6946,3949,7166,6551,4640,3902,3022,4767,5717,4717,4426,5886,6392,6167,6995,4557,6350,4913,6394,6330,5910,7545,2031,4338,4150,2995,4608,5675,6770,2902,5334,7041,5474,4598,4328,2768,7617,4565,4830,5718,3238,2729,8180,3648,6366,2691,5333,4733,4086,3953,4947,1559,3761,5658,5142,5174,5616,7571,5313,5595,3976,8458,5410,4110,4344,3014,3427,6234,4310,4844,4623,5400,7337,7385,3702,6947,6657,5973,4751,5671,5140,5831,2774,5496,2163,3580,3836,4081,5818,6182,4890,4520,7989,4703,4168,6503,6371,4391,3683,5996,2203,4689,7024,2815,2789,6623,5934,4917,4026,2632,8715,4408,4833,3134,3471,4362,4672,6064,5899,2661,4599,3945,6306,5519,4710,6424,5753,7318,3141,8016,2448,4324,4483,6864,3685,3840,6394,4112,7225,5171,5511,3002,6689,4238,4661,4243,7521,2526,6144,2748,5617,5594,2911,3573,4577,5648,2985,2859,4647,5335,4343,3466,6412,7855,4973,4276,4903,4334,2389,7672,6290,5423,5242,5285,5549,5987,5951,3352,3622,4757,4608,4727,5871,8378,6029,5493,6659,3861,6668,6216,3387,4591,7030,7621,4982,7979,5239,2448,4844,7020,4672,5900,8081,4745,5724,4008,4766,6605,1931,5766,5850,5117,3163,5793,3511,3961,6571,5580,6246,4188,5821,8026,5247,6164,4868,5363,4887,3477,3926,6262,5950,4749,5959,4681,4808,5563,4654,5438,5613,6187,2168,5455,3937,6083,5374,2843,3130}; 
		//{3276,2044,3229,3732,3283,7647,4660,4555,3657,3699,4252,9651,5490,4193,5421,5386,4522,3790,5371,1366,4638,9549,7020,5142,3312,2649,5830,8924,7457,5723,6668,4007,7102,2607,5121,4980,4091,7730,4751,4205,6470,2073,3889,3403,6522,6803,3808,4750,1778,9097};
			//{7997,1748,1550,4422,4510,4044,1402,4522,3824,3367,5643,5847,3734,4215,5294,6687,8439,4636,3721,3995,5786,4966,3713,5111,5651,2625,5320,4382,5706,5593,6257,6420,2731,5122,5338,3823,4582,1269,4323,4485,7342,3531,5145,5656,3834,7293,3566,4262,3463,3733,5479,2493,5143,1973,7091,3356,7192,6888,4540,7851,4221,2992,4742,6526,6403,4155,3370,2881,4850,3302,4144,8259,8068,6325,4604,4502,6304,1006,1850,2998,6927,2087,4131,3352,8505,7733,5567,3435,1009,3652,3122,4661,4708,4075,6773,6492,1285,6339,4416,4571,4016,1840,5236,2287,4531,4991,3264,5956,3898,6511,2260,9749,1031,3520,4832,6899,5122,6190,4863,4733,3579,4204,2783,4498,8608,5890,5261,5676,4606,7980,8488,3331,8214,7735,6482,4042,4450,6979,6467,4029,8935,5338,5499,4558,2850,6980,3056,5795,4422,8662,5512,4127,4984,4751,6155,3339,4146,938,6033,3132,6247,4947,5609,3794,6066,5643,2696,5296,5929,6673,4812,8895,5985,4632,2955,5465,7920,3863,6081,6310,4029,2786,8157,4518,9542,9821,3659,4050,2649,1359,8300,5200,7353,7507,6887,2594,5255,3543,4472,3007,2666,5435,2767,5566,3411,7923,4824,9563,6486,2981,5160,4097,5680,6850,5167,3995,8655,3391,7262,5068,6772,5533,2325,8576,8872,6779,3794,5842,3627,2169,3131,7028,4965,5884,2927,1709,3837,3888,6092,2774,7264,6003,8269,4644,4374,7719,7652,6392,6726,4502,7001,3541,3899,3213,5593,8608,2796,5130,7621,6207,5712,6017,5277,7073,6588,7720,7139,7666,7080,4506,5813,7406,6051,5396,7296,2937,5750,4151,3638,5182,7851,1672,6043,2205,6503,4201,5047,1480,3241,2618,8223,2597,3361,6054,7135,6043,5477,3478,6360,3973,4866,7784,3799,4291,3032,5211,7764,4763,2155,8267,8016,3844,5476,4675,4079,6161,3875,5154,7024,4774,3072,8078,1507,6094,2989,4101,3800,7727,3757,9707,4077,968,5661,6214,5907,6574,5321,4907,3772,7314,6152,4848,6460,7813,4072,5318,2793,6825,4340,2605,4518,9724,4380,7084,6962,2477,5664,8133,6276,6132,5434,6317,3276,4368,4531,6196,3397,7349,6458,6418,5117,5768,6413,3580,6288,9193,3321,4177,7259,4065,3082,3812,3201,6949,4329,8865,6126,2583,7878,5223,4508,5073,3940,5070,7754,8816,4357,7389,3963,6965,7553,9774,6500,3453,4819,6975,7393,8018,8949,2408,6118,6719,9646,3935,5438,2487,4319,4481,3634,7851,3950,7534,2318,5508,3618,4496,9774,5955,3167,2986,7356,2371,7462,6865,3314,5688,6202,3768,6165,2938,4442,4708,8526,7826,5853,5754,5885,5971,5308,3941,1524,3727,6217,5193,7638,4992,7665,5651,2217,3075,3330,4377,7532,7698,9037,6810,2908,4939,7098,6408,8048,3634,4324,3234,5622,6596,6235,4887,8834,7574,1470,7224,2906,7603,3723,6861,6216,3956,2488,3035,2327,3632,6239,1940,7912,3639,6637,6770,8766,6408};
		//{6211,7497,3640,6382,6111,4012,4519,7303,6189,4485,3166,2662,5398,7599,5754,3801,4633,3859,5857,7200,4180,5775,5424,2388,7066,1941,4860,1604,6753,3810,4977,3284,4870,4061,3829,2611,5730,3625,6239,6713,5545,7673,5673,6901,8434,6017,6376,4288,4247,4610,5736,4533,4600,3544,6619,6290,2533,4607,4894,5563,4335,5489,2038,6722,4944,5599,1578,3746,3909,4663,4075,4015,4883,6163,3760,5544,3923,6303,4096,6296,4344,3842,3522,4855,4695,5130,4077,5131,6849,2648,6676,4422,7537,7242,5660,3609,4872,4213,4089,8129,4706,6911,4434,891,5124,6756,5771,5175,6899,2608,4555,4709,6090,7385,4289,7865,5812,4395,9028,5680,6545,4968,4258,1297,4771,5800,6493,3920,4671,3227,5350,5462,4209,5173,5736,4673,3529,5577,5835,5851,6466,4670,3418,4797,5971,5186,6837,5557,3286,6205,5602,6889,4822,5804,4167,6335,1397,5287,8682,4636,5347,5955,4523,5152,6579,4251,4638,4890,2271,788,3613,5155,4553,6359,5838,1894,6083,6238,5112,7136,3985,2976,5064,4951,5681,3777,3743,5429,3050,4506,4984,5281,4076,2925,4438,9352,2386,3521,6462,3474,915,6587,7156,3144,5876,4761,6130,3542,3711,5574,6704,4108,4756,6628,3027,4024,6068,5509,6328,5394,3245,5145,3359,3174,4789,3806,5845,5443,4059,3983,5655,4704,5618,3837,2779,4366,5829,6640,4871,7641,4622,815,5329,6228,5811,5561,3203,6262,3926,5389,4831,6402,7149,3338,6216,5501,2658,6441,7107,4163,3678,3358,4963,4345,5044,5107,5518,7219,6133,5664,4209,2779,7033,8007,5117,4558,2661,5074,3596,2536,8322,4284,3929,3017,5455,6764,1069,5741,6800,5313,3853,4522,2896,941,4239,4042,5635,6469,6585,6089,5645,6292,4463,3291,5019,5236,3050,5852,4684,5609,6021,6764,7218,5017,6551,3935,5180,6411,4949,7178,3420,5639,4732,3732,6217,6063,5579,5015,4329,4489,4175,5601,6460,6696,5385,6542,6672,2930,7325,5283,5755,5146,3950,4274,5492,2776,7550,3486,4426,6581,6373,7326,3405,6836,865,4424,5462,5495,5759,5003,5581,5311,5449,8115,6056,7789,2652,6491,3767,4504,7044,7581,6680,4111,2321,3200,9844,4397,7147,4963,3543,4374,4816,5545,5476,5133,4787,6535,4628,6094,8184,3676,6733,2680,7193,5529,6110,5831,5234,4714,5519,4194,6291,6361,4436,4974,5099,3973,4931,5244,4139,4908,6159,4635,4513,6105,3880,3893,7115,4040,7795,4704,6482,6078,5480,6603,4006,1106,6439,2285,4222,5977,6089,4830,4381,6587,2074,3777,5148,6950,4585,3740,3773,6182,3914,6279,7695,7193,6276,4583,3115,2646,3805,3044,4664,4585,5017,7365,7324,5232,4149,2188,5586,4203,3626,4987,6121,6516,3560,1211,7195,4365,7468,5583,4356,3805,2058,4078,6846,1916,5723,4332,4309,7938,6985,8716,7209,3900,3776,7121,5135,3886,5380,3055,4171,4472,3543,5554,6664,3896};
		//{5182,2408,6128,6155,4298,3632,2944,5933,2895,6483,3785,5590,6224,5663,5624,3179,3755,2019,3470,8016,3759,4490,4446,4597,7079,5895,3886,8460,3362,6318,5125,3698,966,7165,6191,4011,4970,4179,2594,2932,4827,6812,2978,4082,5510,3826,6145,3903,4488,5109,4377,4325,5344,4993,4824,3914,5576,4626,4649,5895,4358,4808,5250,6411,7647,6146,3245,5874,6026,4727,1708,7013,4953,4697,2124,3894,4967,3246,7712,4482,3385,6705,5874,5727,3636,4729,6206,4985,5959,3888,5415,3315,5383,3727,7004,5447,3224,5386,3588,4341,3934,4791,8579,6881,4968,6025,2692,6604,7913,8195,3970,4403,3725,5908,6901,4690,5938,2886,6115,6863,5912,3621,5834,4194,6054,4332,3431,2591,4550,4243,4269,4198,4094,5735,5033,4896,4035,6296,6881,3757,4470,4686,1765,8041,6499,3516,5966,5116,7124,4935,2438,3684,2341,4852,5612,4239,5353,3750,5559,6681,6040,3607,6794,1747,8581,4505,6990,5733,5559,6361,3521,5523,6027,4931,2792,6817,4061,4614,3327,4580,4500,5355,4431,6105,4702,7330,6350,5940,3870,6867,3512,8074,5160,4235,4565,4635,3766,3831,6372,5779,5990,4097,3775,4266,6366,4351,3341,5665,5611,5410,3891,3121,7725,4459,4087,4367,5298,5613,7647,4537,6644,3504,4755,9692,5022,4984,3447,5061,3572,3574,4571,5812,4227,1920,6780,6177,6062,6419,3648,5960,4366,2954,5608,4495,5504,4325,3120,3915,4838,3256,4865,4635,5915,7564,6755,1675,5266,5282,4598,5540,5143,6604,4436,3045,4397,6163,6330,4228,6982,6096,6158,6300,3401,1309,5721,3898,5153,3594,6476,5545,5071,7434,5295,7049,7072,4827,4809,5290,5933,3200,3589,5321,6738,3741,3512,5687,2950,5259,7814,3839,2450,4644,3169,6894,3951,4741,2955,5969,6321,2909,6498,4079,3469,6200,9174,6705,4358,5380,4846,7717,3830,2701,4139,5752,6213,2430,5628,8160,5133,4619,3511,4160,5370,8214,4533,4777,6776,7334,3088,5485,6094,4101,3959,4926,4262,4767,6786,8840,7113,5135,4178,3904,4408,7198,6965,6999,4352,3652,4346,6847,4682,5082,6970,5991,6148,4634,6003,2314,5296,4590,3814,7743,2490,4222,5255,6258,4818,4997,4973,5023,4699,7069,3162,4877,3557,5037,3746,4867,5706,3490,7355,3693,6218,4159,4268,3006,4234,4662,3906,2035,5625,4881,2088,5708,5282,5642,3355,4104,5882,5047,7386,6573,4600,4649,5229,6090,5524,5745,4596,5626,5683,5789,6379,5689,3344,2501,4100,4439,4835,5265,6105,5264,4708,3354,4615,5508,5453,3418,5343,5447,8118,3104,7664,4900,2608,7783,6342,8023,4531,8783,6070,6994,5230,3872,6903,3159,5057,5018,5432,4912,2943,1633,4276,4245,5314,4098,6507,7428,4242,2949,5286,5263,4251,5229,4963,3508,5262,5871,6614,5043,4172,6319,5840,2354,4973,4711,7026,3565,5349,6212,4130,8524,2658,5891,6445,5013,4135,5080,5053,5020}; 
		//{3206,2327,2895,3272,3503,3501,2662,2673,3155,2631,2787,3510,3171,2603,3821,2371,2955,3013,2701,3480,2922,3656,2490,2531,2929,2961,2631,3095,3071,2563,2940,3642,3747,2954,2112,3274,3150,3562,2600,3118,2554,2674,3810,2676,2986,3083,3148,3226,2480,2224,3276,2632,2182,2849,2551,2722,2486,3013,2671,3374,3000,3446,3312,3258,2844,2940,2996,2613,3207,2954,2544,3103,2480,3094,2015,2872,2859,2879,3198,3307,1693,2492,2559,3511,2531,2968,3845,3010,2085,2809,3903,3096,2445,2097,2855,3638,3274,2289,3865,3073,2435,2977,3072,2922,3305,3080,3650,3089,3423,2684,2824,3517,2808,3272,3139,2964,2600,2804,2285,2426,3279,2797,2266,4111,2798,3404,1879,3764,2323,2408,3665,3187,3415,2317,2905,2825,3692,3542,3154,2500,2762,1882,2795,3227,3564,3364,2333,2682,2939,3959,2709,3084,3409,3323,2686,3316,3261,2555,4070,3741,3385,3326,2987,3485,3978,3260,2211,3232,3582,2032,3757,2897,2905,2692,2242,2677,3021,3163,3808,3301,2806,2615,2796,3228,2895,3375,2250,3391,3374,2815,2645,3089,2849,2870,2646,3029,3561,1965,2065,3641,3472,2311,4403,2663,3020,2777,2922,2704,2066,2631,2932,3274,3116,3085,2521,2534,3463,2823,2984,3232,2377,2701,3406,3362,2438,2562,3275,3467,4003,3371,2371,2693,2794,3072,1775,2254,3164,3183,3983,2244,3255,2340,2149,3042,4169,3326,3190,2521,3749,2971,2588,3077,3459,3235,3138,3448,3140,2642,3379,2925,3364,3125,3527,3539,3303,3715,3053,3084,2510,3031,2230,2914,2964,2486,2474,3567,3157,3136,3265,3537,3538,3022,2660,2463,2149,2447,3035,3103,2747,2881,2704,3513,3426,2915,2578,2550,3285,2870,2950,3202,2900,3214,3493,2908,1998,3581,2464,3781,3952,3893,3337,2720,2849,2949,2086,2427,3093,3475,2360,3164,2201,3747,3553,3260,2396,2329,3303,2645,3326,2626,3360,3329,3420,4008,2312,2568,1738,3855,2905,3106,3281,2801,3588,2741,3727,2719,3253,2572,3180,2678,3510,2792,2756,3128,3286,2902,3087,2558,3151,3431,3293,3290,3830,2390,3004,2432,3354,3770,3525,3323,2896,3298,3430,2063,3627,2409,3992,2613,2999,2923,3860,3398,2232,3383,3134,1565,2263,2810,3448,3766,3091,3588,2749,2986,2844,3447,2287,3498,2468,3061,3823,2849,3842,2294,3585,2469,2937,3166,3327,2748,3172,1938,3631,2920,3109,3219,2349,2496,2328,2969,3296,3870,3691,2828,1943,3218,3302,2457,3247,2935,3261,3417,1778,2961,3720,4106,2665,3420,2588,3278,2671,3163,3275,3152,3565,3023,3145,3083,2790,3106,2825,3209,3668,3310,2538,3304,2985,2114,2039,2539,2583,3839,3301,3019,2615,2249,4808,2447,3527,3755,3728,2137,3707,3675,3079,3364,3312,2866,3221,2664,2504,3944,3249,2886,2548,3039,2665,2770,3468,2745,3653,2380,3230,2944,2713,1676,3441,2676,2794,3200};//{ 10000, 1250, 2500, 5000 };
	public final static int VM_TYPES	= VM_CT_SLA_UNSORTED[0].length*CLOUDLET_TYPES;
	public final static int[][] VM_CT_SLA = getSortedSla(VM_CT_SLA_UNSORTED);//seconds
	public final static int[][] VM_MIPS	= getVmMips(VM_CT_SLA);//{ 2500, 2000, 1000, 500 };
	public final static int[] VM_PES	= { 1, 1, 1, 1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1};//,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1, 1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1, 1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,1, 1, 1, 1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1,  1, 1, 1,1,1 };
	public final static int[] VM_RAM_UNSORTED	= { 1024, 1024, 1024, 1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024};//,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024, 1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024, 1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,1024, 1024, 1024, 1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024,  1024, 1024, 1024,1024,1024};
	public final static int[] VM_RAM	= getSortedRam(VM_RAM_UNSORTED);
	public final static int VM_BW		= 100000; // 100 Mbit/s
	public final static int VM_SIZE		= 2500; // 2.5 GB
	private final static boolean VM_SLA_SORTING_ASCENDING=true;//false;//true //therefore, mips is sorted in descending order
	private final static boolean VM_MIPS_SORTING=true;// = true; //if false(default) do not sort by mips
	
	public static VmSlaCloudletListListSteady vmVmSlaCloudletListSteadyPublic;
	/*
	 * Host types:
	 *   HP ProLiant ML110 G4 (1 x [Xeon 3040 1860 MHz, 2 cores], 4GB)
	 *   HP ProLiant ML110 G5 (1 x [Xeon 3075 2660 MHz, 2 cores], 4GB)
	 *   We increase the memory size to enable over-subscription (x4)
	 */
	public final static int HOST_TYPES	 = 1;//2;
	public final static double[][] HOST_MIPS	 = { /*1860, {1597,1730,1863,1996,2129,2262}};//*/{1600,1800,2000,2200,2400,2600,2800,3000,3200,3400} };
	public final static int[] HOST_PES	 = { /*2,*/ 4 };
	public final static int[] HOST_RAM	 = { /*4096,*/ 65536 };
	public final static int HOST_BW		 = 10000000; // 1 Gbit/s
	public final static int HOST_STORAGE = 1000000; // 1 GB

	public final static PowerModelSteady[] HOST_POWER = {
		//new PowerModelE5507()
		new PowerModeli7()
	};

	private static int[][] getSortedSla(int[][] slaUnsorted){
		int [][] vmSlas = new int[slaUnsorted.length][slaUnsorted[0].length];
		for(int i=0;i<slaUnsorted.length;i++){
			vmSlas[i] = Arrays.copyOf(slaUnsorted[i], slaUnsorted[i].length);
			Arrays.sort(vmSlas[i]);
		}
		if(VM_SLA_SORTING_ASCENDING ==false){
			Integer[][] vmSlasInteger = new Integer[CLOUDLET_TYPES][vmSlas[0].length];
			int rowIndex=0;
			for(int[] line : vmSlas){
				for(int i=line.length-1;i>=0;i-- )//reversing
					vmSlasInteger[rowIndex][i] = line[line.length-1-i];
				rowIndex++;
			}
			rowIndex=0;
			for(int[] line : vmSlas){
				for(int i=line.length-1;i>=0;i-- )//copying
					line[i] = vmSlasInteger[rowIndex][i].intValue();
				rowIndex++;
			}
		}
		return vmSlas;
	}
	
	private static int[][] getVmMips(int[][] vmCtSla) {
		int[][] vmMipsInt = new int[vmCtSla[0].length*CLOUDLET_TYPES][2];
		List<Integer> vmMips = new ArrayList<>();
		List<VmSlaCloudletListSteady> vmVmSlaCloudletListSteady = new ArrayList<>();
		System.out.println("Completeion Time\tMips");
		for (int i=0; i<vmCtSla[0].length*CLOUDLET_TYPES;i++){
			vmMips.add((int) (CLOUDLET_LENGTH[i%CLOUDLET_TYPES]/vmCtSla[i%CLOUDLET_TYPES][i/CLOUDLET_TYPES]));
			vmMipsInt[i][1]=i%CLOUDLET_TYPES;
			vmVmSlaCloudletListSteady.add(new VmSlaCloudletListSteady(i,(int) (CLOUDLET_LENGTH[i%CLOUDLET_TYPES]/vmCtSla[i%CLOUDLET_TYPES][i/CLOUDLET_TYPES]),vmCtSla[i%CLOUDLET_TYPES][i/CLOUDLET_TYPES],CLOUDLET_LENGTH[i%CLOUDLET_TYPES]));
			System.out.println(vmCtSla[i%CLOUDLET_TYPES][i/CLOUDLET_TYPES]+"\t"+vmMips.get(i)+"\t"+CLOUDLET_LENGTH[i%CLOUDLET_TYPES]);
		}

		//VmSlaCloudletListSteady.sortByMipsDescending(vmVmSlaCloudletListSteady);
		//System.out.println("ccc");

		VmSlaCloudletListListSteady.copy(vmVmSlaCloudletListSteady);
		
		//for (VmSlaCloudletListSteady ll : VmSlaCloudletListListSteady.getList()){
		//	System.out.println("ccc"+ll.getTuple().length);
		//}
		
		//System.out.println("ccc"+vmVmSlaCloudletListSteadyPublic.getClass());
		if(VM_MIPS_SORTING==true){
			if(VM_SLA_SORTING_ASCENDING==true){System.out.println("descending mips");
				VmSlaCloudletListSteady.sortByMipsDescending(vmVmSlaCloudletListSteady);
				Collections.sort(vmMips,Collections.reverseOrder());}
			else{System.out.println("ascending mips");
				VmSlaCloudletListSteady.sortByMips(vmVmSlaCloudletListSteady);
				Collections.sort(vmMips);}
		}
		int index=0;
		for (Integer mips : vmMips){
			vmMipsInt[index++][0]=mips;
		//	System.out.println(vmCtSla[(index-1)%CLOUDLET_TYPES][index-1]+"\t"+mips);
		}
		for (int i=0; i<vmCtSla[0].length*CLOUDLET_TYPES;i++){
			//vmMips.add((int) (CLOUDLET_LENGTH[i%CLOUDLET_TYPES]/vmCtSla[i%CLOUDLET_TYPES][i/CLOUDLET_TYPES]));
			System.out.println(vmCtSla[i%CLOUDLET_TYPES][i/CLOUDLET_TYPES]+"\t"+vmMips.get(i)+"\t"+CLOUDLET_LENGTH[i%CLOUDLET_TYPES]);
		}
		return vmMipsInt;
	}
	

	private static int[] getSortedRam(int[] ramUnsorted){
		List<Integer> vmRams = new ArrayList<>();
		for (int ram : ramUnsorted)
			vmRams.add(ram);
		Collections.sort(vmRams,Collections.reverseOrder());
		if(VM_SLA_SORTING_ASCENDING != true){
			Collections.sort(vmRams);
		}
		for(int i=0;i<vmRams.size();i++)
			ramUnsorted[i]=vmRams.get(i);
		return ramUnsorted;
	}
	/*public static void setVmSlaSortingAscending(boolean b){
		VM_SLA_SORTING_ASCENDING=b;
		System.out.println("VM_SLA_SORTING_ASCENDING"+VM_SLA_SORTING_ASCENDING);
	}
	public static void setMipsSorting(boolean b){
		VM_MIPS_SORTING=b;
		System.out.println("VM_MIPS_SORTING"+VM_MIPS_SORTING);
	}*/

}
