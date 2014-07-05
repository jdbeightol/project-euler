package projecteuler.p001to009;

import java.util.HashMap;

public class P5SmallestMultiple
{
/*
 * 2520 is the smallest number that can be divided by each of the numbers 
 * from 1 to 10 without any remainder.
 *
 * What is the smallest positive number that is evenly divisible by all 
 * of the numbers from 1 to 20?
 */
	
	public static boolean isPrime(long number)
	{
		if(number >= -1 && number <= 1) return false;
		if(number % 2 == 0 && number != 2) return false;
		
		for(int i = 3;i*i<=number;i+=2)
			if(number % i == 0) return false;			
		
		return true;
	}
	
	/* 
	 * This solution relies on the mathematical representation of the least 
	 * common multiple (LCM), n such that n is the product of the greatest 
	 * number of occurrences of its prime factors.
	 *
	 * eg. 2520 = 2 * 2 * 2 * 3 * 3 * 5 * 7 
	 *                or
	 * n is the product of 2 to the third, 3 squared, 5, and 7
	 * 
	 * Since these prime factors divide n, any composite combination of 
	 * these factors must also divide n. 
	 */ 
	
	public static void main(String[] args)
	{
		int MAX = 20;
		long product = 1;

		HashMap<Long,Integer> factorList = new HashMap<Long,Integer>();
		
		for(long i = 2; i<MAX; i++)
			if(isPrime(i))
				factorList.put(i,(int)(Math.log(MAX) / Math.log(i)));
		
		for(long x : factorList.keySet())
			product *= Math.pow(x, factorList.get(x));

		System.out.println(factorList);
		System.out.println("Product: " + product);
	}
}
