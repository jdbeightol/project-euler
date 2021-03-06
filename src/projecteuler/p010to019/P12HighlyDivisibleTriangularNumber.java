package projecteuler.p010to019;

import java.util.ArrayList;
import java.util.Stack;

public class P12HighlyDivisibleTriangularNumber
{
/*
 * The sequence of triangle numbers is generated by adding the natural numbers. 
 * So the 7th triangle number would be 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. The first 
 * ten terms would be:
 *
 * 		1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 * 
 * Let us list the factors of the first seven triangle numbers:
 * 
 * 1: 1
 * 3: 1,3
 * 6: 1,2,3,6
 * 10: 1,2,5,10
 * 15: 1,3,5,15
 * 21: 1,3,7,21
 * 28: 1,2,4,7,14,28
 * 
 * We can see that 28 is the first triangle number to have over five divisors.
 * 
 * What is the value of the first triangle number to have over five hundred 
 * divisors?
 */
	
	public static void main(String[] args)
	{
		boolean b = false;
		
		for(int i = 1; !b; i++)
		{
			int divisors = 1;
			long n = i * (i + 1) / 2;
			ArrayList<Integer> fact = new ArrayList<Integer>();
						
			for(long l : getPrimeFactors(n))
			{
				int power = 0;
				long m = n;
				
				while(m % l == 0)
				{
					m /= l;
					power++;
				}
				
				fact.add(power);
			}
			
			for(int j : fact)
				divisors *= (j + 1);
			
			if(divisors >= 500)
			{
				System.out.printf("%d has %d positive divisors.\n", n, divisors);
				b = !b;
			}
		}		
	}
	
	private static ArrayList<Long> getPrimeFactors(long INPUTNUMBER)
	{
		long inSqrt = (long)Math.sqrt(INPUTNUMBER);
		ArrayList<Long> factorArray = new ArrayList<Long>();
				
		if(INPUTNUMBER % 2 == 0) factorArray.add((long)2);
		
		for(long i = 3;i <= inSqrt; i += 2)
			if(INPUTNUMBER % i == 0)
				if(isPrime(factorArray, i)) 
					factorArray.add(i);
		
		long n = INPUTNUMBER;
		
		Stack<Long> divisorArray = new Stack<Long>();		
		
		for(long j : factorArray)
			while(n % j == 0)
				if((n /= j) != 1)
					divisorArray.push(n);
		
		while(!divisorArray.isEmpty())
		{
			long k = divisorArray.pop();
			
			if(isPrime(factorArray, k) && !factorArray.contains(k)) factorArray.add(k);
		}
		
		if(INPUTNUMBER > 1 && isPrime(factorArray, INPUTNUMBER))
			factorArray.add(INPUTNUMBER);
		
		return factorArray;
	}

	private static boolean isPrime(ArrayList<Long> factorArray, long i)
	{
		for(long l : factorArray)
			if(i % l == 0)
				return false;
		
		return true;
	}
}
