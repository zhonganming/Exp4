
public class TestEEA {
	public static void main(String[] args) {
		extendedEuclidAlgorithm(550, 170);
	}

	/*
	 * 用扩展欧几里德算法求逆元 
	 * 输入： 整数r0, r1, r0 > r1, r0与r1互素 
	 * 输出： s = r0 ^ -1 mod r1, t = r1 ^ -1 mod r0
	 */
	public static void extendedEuclidAlgorithm(long r0, long r1) {
		if (!isCoPrime(r0, r1)) {
			throw new IllegalArgumentException("必须提供两个互素的参数");
		}
		
		if (r0 < r1) {
			long t = r0;
			r0 = r1;
			r1 = t;
		}
		
		long initialr0 = r0;
		long initialr1 = r1;
		
		long s0, s1, s2;
		long t0, t1, t2;
		long r2;
		long q;
		int i = 0;

		s0 = 1; s1 = 0;
		t0 = 0; t1 = 1;

		
		while (0 != r1) {
			q = r0 / r1;
			r2 = r0 - q * r1;
			s2 = s0 - q * s1;
			t2 = t0 - q * t1;
			
			System.out.println("i = " + i++ + " r = " + r2 + " s = " +  s2 + " t = " + t2);
			
			r0 = r1; r1 = r2;
			s0 = s1; s1 = s2;
			t0 = t1; t1 = t2;			
		}
		
		System.out.println(initialr0 + "模" + initialr1 + "的乘法逆元为: " + s0);
		System.out.println(initialr1 + "模" + initialr0 + "的乘法逆元为: " + t0);
	}

	/*
	 * 判断两个整数是否互素 
	 * 输入： 整数r0, r1 
	 * 输出：(r0, r1) = 1时为true，否则为fasle
	 */
	public static boolean isCoPrime(long r0, long r1) {
		long r2 = 0;
		while (r1 != 0) {
			r2 = r0 % r1;
			r0 = r1;
			r1 = r2;
		}
		if (1 == r0) return true;
		return false;
	}
}
