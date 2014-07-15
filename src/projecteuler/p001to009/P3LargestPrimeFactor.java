package projecteuler.p001to009;

import java.util.ArrayList;

public class P3LargestPrimeFactor 
{
/*
 * The prime factors of 13195 are 5, 7, 13 and 29.
 *
 * What is the largest prime factor of the number 600851475143?
 */
	
	private static long INPUTNUMBER = 600851475143L;
	
	public static void main(String[] args) 
	{
		long startTime = System.nanoTime(), endTime;
		System.out.println("Finding largest prime factor of " + INPUTNUMBER);

		ArrayList<Long> factorArray = new ArrayList<Long>();
						
		if(INPUTNUMBER % 2 == 0) factorArray.add((long)2);

		for(long i = 3;i<=INPUTNUMBER;i+=2)
		{
			if(INPUTNUMBER % i == 0)
			{
				boolean isPrime = true;
				
				for(long l : factorArray)
					if(i % l == 0)
					{
						isPrime = false;
						break;
					}
				
				if(isPrime) factorArray.add(i);				
			}
		}
		
		endTime = System.nanoTime();
		
		System.out.println("Calculation completed in " + (endTime - startTime) / 1000000000.0 + " seconds.");

		System.out.println("Factors: " + factorArray);
		
		if(!factorArray.isEmpty())
			System.out.println("Largest prime factor: " + factorArray.get(factorArray.size() - 1));	
	}
}