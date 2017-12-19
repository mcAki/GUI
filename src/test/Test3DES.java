package test;

import com.sys.volunteer.common.CryptUtil3DES;

public class Test3DES {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String info = "hIygB4Fv7oykWXyPJTxJDL+uVC3k1Z/qWznuhx9LTnQU+J1/BpHqKxL5VIs2OnjK4mZMvpSrg262uTje8foxM3eZwJTys+UWQ5NxVUy/7GXklk7op/tDVGRvgy3e9T5a+CVTAyEAoloX/jBQbe2M2RLDuScYM+rr8fscxiESlz56FquHSpd5ezsK/j04h/vU8UP+sSpfRLoP7fswBFSs6t0WJOFmBMfDv65ULeTVn+rGKjMC3uNEz0Li8wDRuHp5";
		//String key = "own1acrpfryddacpax0zpiqi";
//		System.out.println(CryptUtil3DES.decrypt(info));
		String info1 = "1234567";
		String info2 = "12345678";
		String key = "L5nl416bYhTtEKSYL2NHcmzU";
		System.out.println(CryptUtil3DES.encrypt(key, info1));
		System.out.println();
		System.out.println(CryptUtil3DES.encrypt(key, info2));
	}

}
