package projecteuler.p001to009;

import java.util.ArrayList;
import java.util.Stack;

public class P3LargestPrimeFactor 
{
/*
 * The prime factors of 13195 are 5, 7, 13 and 29.
 *
 * What is the largest prime factor of the number 600851475143?
 */
	
	private static long INPUTNUMBER = 2162160;
	
	private static ArrayList<Long> factorArray = new ArrayList<Long>();
	
	public static void main(String[] args) 
	{
		long startTime = System.nanoTime(), endTime;
		long inSqrt = (long)Math.sqrt(INPUTNUMBER);
		
		System.out.println("Finding largest prime factor of " + INPUTNUMBER);
		
		// Since we will skip all even numbers, we must check if 2 is a prime factor.
		if(INPUTNUMBER % 2 == 0) factorArray.add((long)2);
		
		// We will check all odd numbers up to the square root of the input number for prime divisors.
		for(long i = 3;i <= inSqrt; i += 2)
			if(INPUTNUMBER % i == 0)
				if(isPrime(i)) 
					factorArray.add(i);
		
		long n = INPUTNUMBER;
		
		Stack<Long> divisorArray = new Stack<Long>();		
		
		// Find any remaining divisors.
		for(long j : factorArray)
			while(n % j == 0)
				if((n /= j) != 1)
					divisorArray.push(n);
		
		// Check if the remaining divisors are prime.
		while(!divisorArray.isEmpty())
		{
			long k = divisorArray.pop();
			
			if(isPrime(k) && !factorArray.contains(k)) factorArray.add(k);
		}
		
		endTime = System.nanoTime();
		
		System.out.println("Calculation completed in " + (endTime - startTime) / 1000000000.0 + " seconds.");
				
		if(!factorArray.isEmpty())
		{
			System.out.println("Distinct Prime Factors: " + factorArray);
			System.out.println("Largest prime factor: " + factorArray.get(factorArray.size() - 1));
		}
		
		else
			System.out.println("Either " + INPUTNUMBER + " is prime, or something bad happened.");			
	}
	
	private static boolean isPrime(long i)
	{
		/* 
		 * Given that a composite number is always greater than its prime factors.
		 *
		 * We can assume that if a number, n, is divisible by a number m, and m is 
		 * not divisible by any of the factors of n that are less than m, then m must be prime.
		 */
		
		for(long l : factorArray)
			if(i % l == 0)
				return false;
		
		return true;
	}
}