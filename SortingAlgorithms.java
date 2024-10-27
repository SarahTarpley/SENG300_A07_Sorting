import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


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
	
	private static void arrangeMedian(List<Integer> inputArray, int l_idx, int r_idx) {
		int m_idx = (l_idx + r_idx)/2;
		// find median of 3: sort among the 3 points
		if(inputArray.get(l_idx) > inputArray.get(r_idx)) {
			swap(inputArray, l_idx, r_idx);
		}
		if(inputArray.get(m_idx) < inputArray.get(l_idx)) {
			swap(inputArray, l_idx, m_idx);	
		}
		if(inputArray.get(m_idx) > inputArray.get(r_idx)) {
			swap(inputArray, m_idx, r_idx);	
		}
		// assign median of 3 to leftmost position
		swap(inputArray, m_idx, l_idx);
	}
	
	private static void swap(List<Integer> inputArray, int l_idx, int r_idx) {
		Integer memVal = inputArray.get(r_idx);
		inputArray.set(r_idx, inputArray.get(l_idx));
		inputArray.set(l_idx, memVal);
	}
	
	public static void quickSort(List<Integer> inputArray) {
		int arrLen = inputArray.size();
		// cannot sort array of 1 item
		if(arrLen == 1) {
			return;
		}
		quickSort(inputArray, 0, arrLen - 1);
	}
	
	private static void quickSort(List<Integer> inputArray, int l_idx, int r_idx) {
		// end recursion once pointers have met
		if(r_idx > l_idx) {	
			arrangeMedian(inputArray, l_idx, r_idx);
			int pivot = partition(inputArray, l_idx, r_idx);
			if(r_idx - l_idx > 1) {
				//left
				quickSort(inputArray, l_idx, pivot - 1);
				//right
				quickSort(inputArray, pivot + 1, r_idx);
			}
		}
	}
	
	private static int partition(List<Integer> inputArray, int l_idx, int r_idx) {		
		int pivot = l_idx++;
		//don't partition without 2+ elements
		if(r_idx <= l_idx) {
			return r_idx;
		}
		for(int scan = l_idx; l_idx < r_idx && scan < inputArray.size(); scan++) {
			while(l_idx < inputArray.size() && inputArray.get(l_idx) <= inputArray.get(pivot)) {
				l_idx++;
			}
			while(r_idx > pivot && inputArray.get(r_idx) >= inputArray.get(pivot)) {
				r_idx--;
			}
			if(r_idx > l_idx) {
				swap(inputArray, l_idx, r_idx);
			}			
		}
//		while(r_idx > l_idx) {
//			while(inputArray.get(l_idx) < inputArray.get(pivot)) {
//				l_idx++;
//			}
//			while(inputArray.get(r_idx) > inputArray.get(pivot)) {
//				r_idx--;
//			}
//			if(r_idx > l_idx) {
//				swap(inputArray, l_idx, r_idx);
//			}
//		}
		swap(inputArray, pivot, r_idx);
		
		return r_idx;
	}
	
	
	public static void testArrayPerformance(int arrayLength) {
		
		Random rand = new Random();
		//rand.nextInt
		//rand.nextInt(0, 30100);
		//Class<?> classObj = rand.getClass();
		//Method randMethod = classObj.getDeclaredMethod("nextInt", int.class, int.class);
		
		ArrayList<Integer> TestIntArray = new ArrayList<>()//(Arrays.asList(7, 1, 14, 13, 14, 7, 13, 13, 9, 9, 14, 12, 13, 14, 14));
		{{
			for(int i = 0; i < arrayLength; i++) {
				add(rand.nextInt(i, arrayLength));
				//add(arrayLength-i);
			}
		}};
		
		ArrayList<Integer> OriginalArray = (ArrayList)TestIntArray.clone();
		
		String[] testAlgs = { "bubbleSort", "insertionSort", "selectionSort", "mergeSort", "quickSort", "javaSort" };
		//String[] testAlgs = {"mergeSort", "quickSort"};
		//String[] testAlgs = { "javaSort" };

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
			case "quickSort":
				quickSort(TestIntArray);
			case "javaSort":
				Collections.sort(TestIntArray);
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
		testArrayPerformance(100);
		testArrayPerformance(500);
		testArrayPerformance(1500);
		testArrayPerformance(30000);
		testArrayPerformance(50000);
		testArrayPerformance(100000);
		//testArrayPerformance(1000000);
	}
}
