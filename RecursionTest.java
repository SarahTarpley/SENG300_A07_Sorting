import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RecursionTest {
	
	public static int fun1( int x, int y) {
		System.out.print(x);
		System.out.print(" ");
		System.out.println(y);

		if (x == 0)

		return y;

		else

		return fun1(x - 1, x + y);

		}
	
	public static int firstLower(String input, int startPosition) {
		char next;
		next = input.charAt(startPosition);
		if(Character.isLowerCase(next)) {
			return startPosition;
		}
		else if(startPosition < (input.length() - 1)) {
			return firstLower(input, startPosition + 1);
		}
		else {
			return -1;
		}
	}
	
    public static List<Integer> reverseArray(List<Integer> a) {
        // Store the size of the max index
        int arrLen = a.size() - 1;
        // Iterate on half of the array, swapping the end with beginning
        // Odd numbered arrays will simply leave the middle alone
        for(int i = 0; i <= arrLen/2; i++){
            Integer key = a.get(i);
            a.set(i, a.get(arrLen - i));
            a.set(arrLen - i, key);
        }
        return a;
    }
    
    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
    // Write your code here
        int queryCount = queries.size();
        List<List<Integer>> arr = new ArrayList<>(){{
            for(int i = 0; i < n; i++){
                add(new ArrayList<Integer>());
            }
        }};
        List<Integer> answers = new ArrayList<>();
        int x;
        int y;
        int lastAnswer = 0;
        int idx;
        int type;
        for(int i = 0; i < queryCount; i++){
            type = queries.get(i).get(0);
            x = queries.get(i).get(1);
            y = queries.get(i).get(2);
            //inner list will always contain 3 integers
            if(type == 1){
                idx = (( x ^ lastAnswer ) % n);
                arr.get(idx).add(y);
            }
            else if(type == 2){
                idx = (( x ^ lastAnswer ) % n);
                lastAnswer = arr.get(idx).get(y % (arr.get(idx).size()));
                //System.out.println(y % (arr.get(idx).size()));
                //System.out.println(lastAnswer);
                answers.add(lastAnswer);
            }
        }
        return answers;
    }
	public static void main(String[] args) {
		
		//System.out.println(fun1(10, 5));
		
		String testInput = "INPUT WI9tH SpAce";
		
		System.out.println(firstLower(testInput, 0));
		List<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		
		System.out.println(reverseArray(a));
	}
	
}
