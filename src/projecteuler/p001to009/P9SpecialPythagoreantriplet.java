package projecteuler.p001to009;

public class P9SpecialPythagoreantriplet
{
/*
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 * 
 * 								a^2 + b^2 = c^2
 * 
 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 * 
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 */
	
	private static final int SUM = 1000;
	
	/*
	 * This solution relies on the Euclid's formula to generate a relevant
	 * the Pythagorean triple.  Choose any arbitrary m and n, such that
	 * m is greater than n.  A triple can be formed by,
	 * 
	 * 		a = m^2 - n^2
	 * 		b = 2mn
	 * 		c = m^2 + n^2
	 * 
	 * Given the sum of the triple, s, the formula can be manipulated such that,
	 * 
	 * 		s = a + b + c
	 * 		s = m^2 - n^2 + 2mn + m^2 + n^2
	 * 		s = 2m^2 + 2mn
	 * 		s / 2 = m^2 + mn
	 * 
	 * Given this formula, a valid s, and the constraint n < m, the equation can
	 * be properly solved.
	 */
	
	public static void main(String[] args)
	{
		boolean found = false;
		int a = 0 , b = 0 ,c = 0, m = 1;
		
		while(!found)
		{
			m++;

			for(int n=1;n<m;n++)
			{
				if(m*m + m*n == SUM / 2)
				{
					a = m*m - n*n;
					b = 2 * m * n;
					c = m*m + n*n;
					found = true;
					break;
				}
			}
			
			if(m > Math.sqrt(SUM / 2 - m))
			{
				break;
			}
		}
		
		if(!found)
			System.out.println("A valid Pythagorean triple could not be found.");
		
		else
		{
			System.out.println("a=" + a + " b=" + b + " c=" + c);
			System.out.println("Sum: " + (a + b + c));
			System.out.println("Product: " + (a * b * c));
			System.out.println();
			System.out.println((a*a + b*b == c*c && (a + b + c) == SUM)
					?"The values a, b, and c are a valid Pythagorean triple."
							:"The values are invalid!");
		}
	}
}
