package experiments;

import java.util.ArrayList;

public class BigSieve 
{
	private static final long INPUTNUMBER = Integer.MAX_VALUE + 2L;
	
	private static final int CHUNKSIZE = 536870912;
	
	public static void main(String[] args) 
	{
		System.out.println("[JVM] Memory Max:    " + getNamedSize(Runtime.getRuntime().maxMemory()));
		System.out.println("[JVM] Memory Total:  " + getNamedSize(Runtime.getRuntime().totalMemory()));
		System.out.println("[JVM] Memory Free:   " + getNamedSize(Runtime.getRuntime().freeMemory()));
		
		System.out.println("\nFinding largest prime factor of " + INPUTNUMBER);
		
		bigSieve(INPUTNUMBER);
	}
	
	private static void bigSieve(long inputNumber)
	{
		long startTime = System.nanoTime(), endTime;

		final long sqrtTN = (long)Math.sqrt(inputNumber) + 1;
		final long totalBytes = inputNumber / 16;
		
		final int bytesPerArray = CHUNKSIZE;
		final int arrayCount = (int)(totalBytes / bytesPerArray + 1);
		final int lastArrayCount = (int)(inputNumber / 16 % bytesPerArray + 1);
		
		final long totalAllocatedSize = (long)Math.round((arrayCount-1)) * bytesPerArray + lastArrayCount;
		
		System.out.println("The sieve will require " + getNamedSize(totalAllocatedSize) + " of memory (" 
		+ (arrayCount - 1) + "x" +  getNamedSize(bytesPerArray, true) + " + " + getNamedSize(lastArrayCount,true) + ")");
		
		System.out.println("Initializing the Sieve of Eratosthenes...");

		byte[][] sieve = new byte[arrayCount][bytesPerArray];
		sieve[sieve.length-1] = new byte[lastArrayCount];
		
		ArrayList<Long> factorArray = new ArrayList<Long>();
						
		System.out.println("Calculating primes...");

		for(long i = 3; i <= sqrtTN; i+=2)
		{
			int mOver16 = (int)(i/16);
			
			if((sieve[mOver16/bytesPerArray][mOver16%bytesPerArray] & (1 << i/2)) == 0)
				for(long j=i*i; j <= inputNumber; j += i * 2)
				{
					int nOver16 = (int)(j / 16);
					
					if(j == 7) System.out.println("Marking composite: " + j);
					
					sieve[nOver16/bytesPerArray][nOver16%bytesPerArray] |= (1 << (j/2%8));
				}
		}
		
		System.out.println("Locating factors...");

		if(inputNumber % 2 == 0)
			factorArray.add((long)2);
		
		for(long x = 3; x <= inputNumber; x += 2)
		{
			int xOver16 = (int)(x / 16);
			
			if((sieve[xOver16 / bytesPerArray][xOver16 % bytesPerArray] & (1 << (x / 2)%8)) == 0 && inputNumber % x == 0)
				factorArray.add(x);
		}
		
		endTime = System.nanoTime();
		
		System.out.println("Calculation completed in " + (endTime - startTime) / 1000000000.0 + " seconds.");
		
		if(inputNumber <= 128L)
		{
			System.out.print("Prime array:  [");
			
			for(long x = 3; x <= inputNumber; x+=2)
				System.out.print(((sieve[(int)(x/16/bytesPerArray)][(int)(x/16)%bytesPerArray] & (1 << (x / 2)%8)) == 0)?"0":"1");
			
			System.out.println("]");
		}
		
		if(!factorArray.isEmpty())
			System.out.println("Factor array: " + factorArray
					+ "\nLargest prime factor: " + factorArray.get(factorArray.size() - 1));
		
		else
			System.err.println("[ERROR] No factors found.");
	}
	
	private static String getNamedSize(long sizeInBytes)
	{	return getNamedSize(sizeInBytes, false);	}
	
	private static String getNamedSize(long sizeInBytes, boolean longName)
	{
		long size[] = new long[2];

		final String[][] PREFIXES = {
			{"Byte", "Kilobyte", "Megabyte", "Gigabyte", "Terabyte", "Petabyte", "Exabyte", "Zettabyte", "Yottabyte"},
			{"B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"}};
		
		
		for(size[0] = sizeInBytes; size[0] / 1024 > 0; size[0] = Math.round(size[0] / 1024.0))
			size[1]++;

		return size[0] + " " + PREFIXES[(longName)?0:1][(int)size[1]] + ((longName && size[0] != 1)?"s":"");
	}
}