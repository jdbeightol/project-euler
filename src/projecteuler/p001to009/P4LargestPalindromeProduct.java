package projecteuler.p001to009;

public class P4LargestPalindromeProduct
{
/*
 * A palindromic number reads the same both ways. The largest palindrome made from 
 * the product of two 2-digit numbers is 9009 = 91 × 99.
 *
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
	
	public static boolean isPalindrome(int number)
	{
		String k = Integer.toString(number);
		
		for(int i = 0;i<k.length()/2;i++)
			if(k.charAt(i) != k.charAt(k.length() - i - 1))
				return false;
		
		return true;
	}
	
	public static void main(String[] args)
	{
		int palindrome = 0, factor1 = 0, factor2 = 0;
		
		for(int x = 999;x > 99; x--)
			for(int y = 999; x * y > palindrome && y > 99; y--)
			{
				int product = x * y;
				
				if(isPalindrome(product) && product > palindrome)
				{
					palindrome = product;
					factor1 = x;
					factor2 = y;
					break;
				}
			}
		
		System.out.println("Largest palindrome: " + palindrome);
		System.out.println("Factors: " + Integer.toString(factor1) + " and " + Integer.toString(factor2));
	}
}