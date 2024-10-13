	import java.io.*;
	import java.math.*;
	import java.security.*;
	import java.text.*;
	import java.util.*;
	import java.util.concurrent.*;
	import java.util.function.*;
	import java.util.regex.*;
	import java.util.stream.*;
	import static java.util.stream.Collectors.joining;
	import static java.util.stream.Collectors.toList;

public class HR_DynamicArray {
	class Result {

	    /*
	     * Complete the 'dynamicArray' function below.
	     *
	     * The function is expected to return an INTEGER_ARRAY.
	     * The function accepts following parameters:
	     *  1. INTEGER n
	     *  2. 2D_INTEGER_ARRAY queries
	     */

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
	                lastAnswer = arr.get(idx).get(y%(arr.get(idx).size()));
	                //System.out.println(y % (arr.get(idx).size()));
	                //System.out.println(lastAnswer);
	                answers.add(lastAnswer);
	            }
	        }
	        return answers;
	    }

	}

	public class Solution {
	    public static void main(String[] args) throws IOException {
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

	        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

	        int n = Integer.parseInt(firstMultipleInput[0]);

	        int q = Integer.parseInt(firstMultipleInput[1]);

	        List<List<Integer>> queries = new ArrayList<>();

	        IntStream.range(0, q).forEach(i -> {
	            try {
	                queries.add(
	                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
	                        .map(Integer::parseInt)
	                        .collect(toList())
	                );
	            } catch (IOException ex) {
	                throw new RuntimeException(ex);
	            }
	        });

	        List<Integer> result = Result.dynamicArray(n, queries);

	        bufferedWriter.write(
	            result.stream()
	                .map(Object::toString)
	                .collect(joining("\n"))
	            + "\n"
	        );

	        bufferedReader.close();
	        bufferedWriter.close();
	    }
	}
}
