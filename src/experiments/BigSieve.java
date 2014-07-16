package experiments;

public class BigSieve 
{
	private static final int MAX_CHUNK_SIZE = 536870912;
	
	private static final String
		INFO_SIEVE_MEMORY = "The sieve will require a minimum of %s of memory (%dx%s).\n",
		ERR_MAX_VALUE = "You should never see this. Though the maximum value this implementation can " +
				"handle is 36,893,488,113,059,364,872; the maximum value a 64-bit signed long can " +
				"have is 9,223,372,036,854,775,807.",
		ERR_CHUNK = "The arrays cannot be initialized. Please increase the MAX_CHUNK_SIZE.",
		ERR_MEMORY = "The JVM does not have enough memory available to complete this computation.",
		ERR_SIZEMATTERS = "A calculation cannot be completed on integers less than or equal to 1.";
	
	public static void main(String[] args) 
	{
		System.out.println("[JVM] Memory Max:    " + getNamedSize(Runtime.getRuntime().maxMemory()));
		System.out.println("[JVM] Memory Total:  " + getNamedSize(Runtime.getRuntime().totalMemory()));
		System.out.println("[JVM] Memory Free:   " + getNamedSize(Runtime.getRuntime().freeMemory()));
		
		bigSieve(-354903);
	}
	
	private static void bigSieve(long maxValue)
	{
		int bytesPerArray;
		long startTime = System.nanoTime(), endTime;
		
		final long maxSqrt = (long)Math.ceil(Math.sqrt(maxValue));
		final long bytesRequired = (long)Math.ceil(maxValue / 16.0);
		
		if(bytesRequired < MAX_CHUNK_SIZE)
			bytesPerArray = (int)bytesRequired + 1;
		else
			bytesPerArray = MAX_CHUNK_SIZE;
		
		final int arrayCount = (int)Math.ceil(((double)bytesRequired) / bytesPerArray);				
		final long totalAllocatedSize = ((long)arrayCount) * bytesPerArray;
		
		System.out.printf(INFO_SIEVE_MEMORY, getNamedSize(totalAllocatedSize, true), 
				arrayCount, getNamedSize(bytesPerArray));
		
		if(bytesRequired > 4611686014132420609L)
			System.out.println(ERR_MAX_VALUE);
		
		else if(bytesRequired / bytesPerArray + 1 > Integer.MAX_VALUE && MAX_CHUNK_SIZE < Integer.MAX_VALUE)
			System.out.println(ERR_CHUNK);
		
		else if(totalAllocatedSize > Runtime.getRuntime().maxMemory())
			System.out.println(ERR_MEMORY);
		
		else if(maxValue < 2)
			System.out.println(ERR_SIZEMATTERS);
		
		else
		{
			System.out.printf("\nSIZEREQ[%s] ALLOCATED[%d B] BYTESPERARRAY[%s] ARRAYS[%d] MAXVALUE[%d] SQRT[%d]\n",
					getNamedSize(bytesRequired), totalAllocatedSize, getNamedSize(bytesPerArray),
					arrayCount, (totalAllocatedSize - 1) * 16 - 1, maxSqrt);
			
			System.out.println("Finding largest prime factor of " + maxValue);
			System.out.println("Initializing the Sieve of Eratosthenes...");
			
			byte[][] sieve = new byte[arrayCount][bytesPerArray];
			
			System.out.print("Calculating primes...");
			
			for(long i = 3; i <= maxSqrt; i += 2)
			{
				long iOver16 	= i / 16;
				
				int arrayIndexI	= (int)(iOver16 / bytesPerArray),
					byteIndexI	= (int)(iOver16 % bytesPerArray),
					bitIndexI	= (int)((i / 2) % 8);
				
				if((sieve[arrayIndexI][byteIndexI] & (1 << bitIndexI)) == 0)
					for(long j=i*i; j <= maxValue; j += i * 2)
					{
						long jOver16 	= j / 16;
						
						int arrayIndexJ = (int)(jOver16 / bytesPerArray),
							byteIndexJ 	= (int)(jOver16 % bytesPerArray),
							bitIndexJ 	= (int)((j / 2) % 8);
												
						sieve[arrayIndexJ][byteIndexJ] |= (1 << bitIndexJ);
					}
			}
			
			System.out.println("done.");
			
			boolean bool = false;
			
			for(long k = (maxValue % 2 == 1)?maxValue:maxValue - 1; k > 0 && !bool; k -= 2)
			{
				long kOver16 	= k / 16;
				
				int arrayIndex 	= (int)(kOver16 / bytesPerArray),
					byteIndex 	= (int)(kOver16 % bytesPerArray),
					bitIndex 	= (int)((k / 2) % 8);
				
				if(maxValue == 2)
					System.out.println("Largest Prime: 2");
				
				else if((sieve[arrayIndex][byteIndex] & (1 << bitIndex)) == 0 && (bool = !bool) && k != 1)
					System.out.println("Largest Prime: " + k);
				
				else
					System.out.println("Strange. No primes were found.");
			}
			
			endTime = System.nanoTime();				
			System.out.println("Computation completed in " + (endTime - startTime) / 1000000000.0 + " seconds.");	
		}		
	}
	
	public static String getNamedSize(long sizeInBytes)
	{	return getNamedSize(sizeInBytes, false);	}
	
	public static String getNamedSize(long sizeInBytes, boolean longName)
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