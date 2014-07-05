package projecteuler.p001to009;

import java.util.ArrayList;

public class P1MultiplesOf3And5
{
/* 
 *  If we list all the natural numbers below 10 that are multiples of 3 or 5, we 
 *  get 3, 5, 6 and 9. The sum of these multiples is 23.
 *
 *  Find the sum of all the multiples of 3 or 5 below 1000.
 */
	public static void main(String[] args) 
	{
		int MAX = 1000;
		
		int sum = 0;
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		
		for(int i = 1; i<MAX;i++)
			if(i%3 == 0 || i%5 == 0)
			{
				intArray.add(i);
				sum += i;
			}	
		
		System.out.println(intArray);
		System.out.println("Sum: " + sum);
	}

}
