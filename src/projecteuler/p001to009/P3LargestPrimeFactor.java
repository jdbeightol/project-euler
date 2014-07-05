package projecteuler.p001to009;

import java.util.ArrayList;

public class P3LargestPrimeFactor 
{
/*
 * The prime factors of 13195 are 5, 7, 13 and 29.
 *
 * What is the largest prime factor of the number 600851475143?
 */
	
	public static void main(String[] args) 
	{
		long testNumber = 600851475143L;
		
		ArrayList<Long> factorArray = new ArrayList<Long>();
		
		if(testNumber % 2 == 0) factorArray.add((long)2);

		for(long i = 3;i*i<testNumber;i+=2)
		{
			if(testNumber % i == 0)
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
		
		System.out.println(factorArray);
		
		if(!factorArray.isEmpty())
			System.out.println("Largest prime factor: " + factorArray.get(factorArray.size() - 1));		
	}

}
