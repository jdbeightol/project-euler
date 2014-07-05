package projecteuler.p001to009;

public class P6SumSquareDifference 
{
	/*
	 * The sum of the squares of the first ten natural numbers is,
	 * 12 + 22 + ... + 102 = 385
	 * The square of the sum of the first ten natural numbers is,
	 * 
	 * (1 + 2 + ... + 10)2 = 552 = 3025
	 * Hence the difference between the sum of the squares of the first ten natural 
	 * numbers and the square of the sum is 3025 − 385 = 2640.
	 * 
	 * Find the difference between the sum of the squares of the first one hundred 
	 * natural numbers and the square of the sum.
	 */
	public static void main(String[] args) 
	{
		int MAX = 100;
		long squareSum = 0, sumSquare = 0;
		
		for(int i = 1; i<=MAX; i++)
			squareSum += Math.pow(i, 2);
		
		sumSquare = (long)Math.pow(MAX * (MAX + 1) / 2, 2);
		
		System.out.println("Square Sum: " + squareSum);
		System.out.println("Sum Square: " + sumSquare);
		System.out.println("Difference: " + (sumSquare - squareSum));
	}

}
