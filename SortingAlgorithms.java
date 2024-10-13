import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SortingAlgorithms {
	private static long startTime;
	private static long endTime;
	public static long elapsedTime;

	
	public static String isSorted(List<Integer> inputArray) {
		
		List<Integer> sortedArray = new ArrayList<Integer>(inputArray);
		Collections.sort(sortedArray);
		for(int i = 0; i < inputArray.size(); i++) {
			if(inputArray.get(i) != sortedArray.get(i)) {
				return "!!Not sorted correctly";
			}
		}
		return "Sorted correctly";
	}
	
	public static void bubbleSort(List<Integer> inputArray){
		//System.out.println("Bubble sort");
		//Compare two items and move the larger one to the right
		boolean swapped;
		//Iterate as many times as objects in array
		for(int i = 0; i < inputArray.size() - 1; i++){
			swapped = false;
			//Iterate through the actual objects starting at the 2nd place & look backwards
			for(int ii = 0; ii < inputArray.size() - i - 1; ii++) {
				Integer key = inputArray.get(ii);
				if(key > inputArray.get(ii+1)) {
					inputArray.set(ii, inputArray.get(ii+1));
					inputArray.set(ii+1, key);
					swapped = true;
				}				
			}
			if(swapped == false) {
				//Optimize to end early if the array is fully sorted
				break;
			}
		}
	}
	
	public static void insertionSort(List<Integer> inputArray){
		//System.out.println("Insertion sort");
		//Iterate as many times as objects in the array
		//Starts 2nd position
		for(int i = 1; i < inputArray.size(); i++){
			Integer key = inputArray.get(i);
			int ii;
			//Iterating backwards rather than forward as in bubblesort
			//Keep moving items forward until finding where the key goes
			for(ii = i - 1;
				ii >= 0 && inputArray.get(ii) > key;
				ii--	
			){
				inputArray.set(ii + 1, inputArray.get(ii));
			}
			//If key <= previous element, stop clearing space & place key
			inputArray.set(ii + 1, key);
		}
	}
	
	public static void selectionSort(List<Integer> inputArray){
		//System.out.println("Selection sort");
		//Iterate as many times as objects in the array
		for(int i = 0; i < inputArray.size()-1; i++) {
			//Find the smallest element in unsorted section ( i..end )
			//Starts 2nd position Scanning forward
			Integer key = inputArray.get(i);
			int keyIndex = i;
			for(int ii = i + 1; ii < inputArray.size(); ii++) {
				if(inputArray.get(ii) < key) {
					key = inputArray.get(ii);
					keyIndex = ii;
				}
			}
			//Swap smallest element (key) with end of the sorted section ( 0..i )
			inputArray.set(keyIndex, inputArray.get(i));
			inputArray.set(i, key);
		}
	}
	
	public static List<Integer> mergeSort(List<Integer> inputArray){
		//System.out.println(inputArray);
		int capacity = inputArray.size();
		int halfPoint = capacity / 2;
		// once we're down to two singleton arrays
		if(capacity == 2) {
			//System.out.println("Merge sort");
			Integer key = inputArray.get(0);
			if(inputArray.get(1) < key) {
				inputArray.set(0, inputArray.get(1));
				inputArray.set(1, key);
			}
//			inputArray = new ArrayList<>();
//			if(arrR[0] > arrL[0]) {
//				arrR[0] = arrL[0];
//				arrL[0] = key;
//			}
//			inputArray.add(arrL[0]);
//			inputArray.add(arrR[0]);
		}
		else if(capacity > 2){
			Integer[] arrL = mergeSort(inputArray.subList(0, halfPoint))
					.toArray(new Integer[0]);
			Integer[] arrR = mergeSort(inputArray.subList(halfPoint, capacity))
					.toArray(new Integer[0]);
					//Arrays.copyOfRange(arrL, halfPoint + 1, capacity);
//			inputArray = (
//					Stream.of(
//					mergeSort(Arrays.asList(arrL)),
//					mergeSort(Arrays.asList(arrR))
//					)
//					.flatMap(Collection::stream)
//					.collect(Collectors.toList())
//					);
			//inputArray = merge(inputArray);
			inputArray = merge(arrL, arrR);
		}
		// anything less than 2 is automatically returned
		//System.out.println(inputArray);
		return inputArray;
	}
	
	public static List<Integer> merge(Integer[] arrL, Integer[] arrR){

		int left_i = 0;
		int right_i = 0;
		List<Integer> outputArray = new ArrayList<>();
		//Loop until both arrays are exhausted
		//First two conditions protect against OOB
		while(left_i < arrL.length || right_i < arrR.length) {
			if(left_i == arrL.length) {
				outputArray.add(arrR[right_i++]);
				//ends here
			}
			else if(right_i == arrR.length) {
				outputArray.add(arrL[left_i++]);
				//or ends here
			}
			
			else if(arrL[left_i] <= arrR[right_i]) {
				outputArray.add(arrL[left_i++]);
			}
			else {
				outputArray.add(arrR[right_i++]);
			}
		}
		//System.out.println("Merged array");
		//System.out.println(outputArray);
		return outputArray;
	}
	
	public static void testArrayPerformance(int arrayLength) {
		
		Random rand = new Random();
		//rand.nextInt
		//rand.nextInt(0, 30100);
		//Class<?> classObj = rand.getClass();
		//Method randMethod = classObj.getDeclaredMethod("nextInt", int.class, int.class);
		
		ArrayList<Integer> TestIntArray = new ArrayList<>() {{
			for(int i = 0; i < arrayLength; i++) {
				add(rand.nextInt(i, arrayLength));
			}
		}};
		
		ArrayList<Integer> OriginalArray = (ArrayList)TestIntArray.clone();
		
		String[] testAlgs = { "bubbleSort", "insertionSort", "selectionSort", "mergeSort" };
		//System.out.println("Unsorted");
		//System.out.println(TestIntArray);
		System.out.println("Array length: " + String.valueOf(TestIntArray.size()));
		System.out.println();
		for(String alg : testAlgs) {
			TestIntArray = (ArrayList)OriginalArray.clone();
			System.out.println(alg);
			
			startTime = System.currentTimeMillis();
			switch(alg) {
			case "bubbleSort":
				bubbleSort(TestIntArray);
			case "insertionSort":
				insertionSort(TestIntArray);
			case "selectionSort":
				selectionSort(TestIntArray);
			case "mergeSort":
				TestIntArray = (ArrayList)mergeSort(TestIntArray);				
			}
			endTime = System.currentTimeMillis();

			//System.out.println(TestIntArray);
			elapsedTime = endTime - startTime;
			System.out.println("Elapsed time: " + String.valueOf(elapsedTime) + " milliseconds");
			System.out.println(isSorted(TestIntArray));
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		testArrayPerformance(500);
		testArrayPerformance(1500);
		testArrayPerformance(30000);
		testArrayPerformance(50000);
		testArrayPerformance(100000);
	}
}