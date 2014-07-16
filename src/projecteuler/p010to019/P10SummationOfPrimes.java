package projecteuler.p010to019;

public class P10SummationOfPrimes
{
/*
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * 
 * Find the sum of all the primes below two million.
 */
	
	private static final int MAXPRIME = 2000000;
	
	public static void main(String[] args)
	{
		long startTime = System.nanoTime(), endTime, sum = 0;
		boolean[] primeList = new boolean[MAXPRIME + 1];
				
		for(int m=2; m*m <= MAXPRIME; m++)
			if(!primeList[m])
				for(int n=m; m*n <= MAXPRIME; n++)
					primeList[m*n] = true;
		
		for(int x = 2; x <= MAXPRIME; x++)
			if(!primeList[x])
				sum += x; 
		
		endTime = System.nanoTime();
		
		System.out.println("Calculation completed in " + (endTime - startTime) / 1000000000.0 + " seconds.");
		System.out.println("Sum: " + sum);
	}
}
