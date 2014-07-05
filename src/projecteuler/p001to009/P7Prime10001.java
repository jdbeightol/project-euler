package projecteuler.p001to009;

public class P7Prime10001
{
/*
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 *
 * What is the 10,001st prime number?
 */

	private static final int PRIMEMAX = 10001;
	
	private static Long[] _primeList = new Long[PRIMEMAX];
	private static int _primeCount = 0;
	
	public static boolean isPrime(long number)
	{
		if(number == 1)
			return false;
		
		for(int i = 0; i< _primeCount; i++)
			if(number % _primeList[i] == 0) return false;			
		
		_primeList[_primeCount++] = number;
		return true;
	}
	
	public static void main(String[] args)
	{		
		long prime = 0, curNumber = 1;
		long startTime = System.nanoTime(), endTime;
		
		while(_primeCount < PRIMEMAX)
		{
			if(isPrime(++curNumber))			
			{
				prime = curNumber;
			}
		}
		
		endTime = System.nanoTime();
		
		System.out.println("Calculation completed in " + (endTime - startTime) / 1000000000.0 + " seconds.");
		System.out.println("Prime " + _primeCount + ": " + prime);
	}
}
