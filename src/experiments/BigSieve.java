package experiments;

public class BigSieve 
{
	private static final long INPUT_NUMBER = 2233582;
	private static final int MAX_CHUNK_SIZE = 536870912;
	
	public static void main(String[] args) 
	{
		System.out.println("[JVM] Memory Max:    " + getNamedSize(Runtime.getRuntime().maxMemory()));
		System.out.println("[JVM] Memory Total:  " + getNamedSize(Runtime.getRuntime().totalMemory()));
		System.out.println("[JVM] Memory Free:   " + getNamedSize(Runtime.getRuntime().freeMemory()));
		
		System.out.println("\nFinding largest prime factor of " + INPUT_NUMBER);
		
		bigSieve(INPUT_NUMBER);
	}
	
	private static void bigSieve(long inputNumber)
	{
		long startTime = System.nanoTime(), endTime;
		
		final long sqrtTN = (long)Math.sqrt(inputNumber) + 1;
		final long totalBytes = inputNumber / 16;
		
		final int bytesPerArray = MAX_CHUNK_SIZE;
		final int arrayCount = (int)(totalBytes / bytesPerArray + 1);
		final int lastArrayCount = (int)(inputNumber / 16 % bytesPerArray + 1);
		
		final long totalAllocatedSize = (long)Math.round((arrayCount-1)) * bytesPerArray + lastArrayCount;
		
		System.out.println("The sieve will require " + getNamedSize(totalAllocatedSize) + " of memory (" 
		+ (arrayCount - 1) + "x" +  getNamedSize(bytesPerArray, true) + " + " + getNamedSize(lastArrayCount,true) + ")");
		
		if(totalBytes > 4611686014132420609L)
			System.out.println("You should never see this. Though the maximum value this implementation can handle "
					+ "is 36,893,488,113,059,364,872; the maximum value a 64-bit signed long can have is 9,223,372,036,854,775,807");
		
		else if(totalBytes / bytesPerArray + 1 > Integer.MAX_VALUE && MAX_CHUNK_SIZE < Integer.MAX_VALUE)
			System.out.println("The arrays cannot be initialized. Please increase the MAX_CHUNK_SIZE.");
		
		else if(totalAllocatedSize > Runtime.getRuntime().maxMemory())
			System.out.println("The JVM does not have enough memory available to complete this computation.");
		
		else
		{
			System.out.println("Initializing the Sieve of Eratosthenes...");
			
			byte[][] sieve = new byte[arrayCount][bytesPerArray];
			sieve[sieve.length-1] = new byte[lastArrayCount];
						
			System.out.println("Calculating primes...");
			
			for(long i = 3; i <= sqrtTN; i+=2)
			{
				long iOver16 	= i / 16;
				
				int arrayIndex 	= (int)(iOver16 / bytesPerArray),
					byteIndex 	= (int)(iOver16 % bytesPerArray),
					bitIndex 	= (int)((i / 2) % 8);
				
				if((sieve[arrayIndex][byteIndex] & (1 << bitIndex)) == 0)
					for(long j=i*i; j <= inputNumber; j += i * 2)
					{
						long jOver16 	= j / 16;
						
						int arrayIndexJ = (int)(jOver16 / bytesPerArray),
							byteIndexJ 	= (int)(jOver16 % bytesPerArray),
							bitIndexJ 	= (int)((j / 2) % 8);
						
						if(j == 7) System.out.println("Marking composite: " + j);
						
						sieve[arrayIndexJ][byteIndexJ] |= (1 << bitIndexJ);
					}
			}
			
			boolean bool = false;
			
			for(long k = inputNumber; k > 0 && !bool; k--)
			{
				long kOver16 	= k / 16;
				
				int arrayIndex 	= (int)(kOver16 / bytesPerArray),
					byteIndex 	= (int)(kOver16 % bytesPerArray),
					bitIndex 	= (int)((k / 2) % 8);
				
				if((sieve[arrayIndex][byteIndex] & (1 << bitIndex)) == 0 && (bool = !bool))
					System.out.println("Largest prime found: " + k);
			}
			
			endTime = System.nanoTime();				
			System.out.println("Computation completed in " + (endTime - startTime) / 1000000000.0 + " seconds.");
		}
	}
	
	public static String getNamedSize(long sizeInBytes)
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