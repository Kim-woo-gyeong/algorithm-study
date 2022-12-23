package practice.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BigNumMake2_BACKTRACK {

	public static void main(String[] args) {
		String number = "1231234";
		int k = 3;

		String[] newArray = number.split("");

		boolean[] visited = new boolean[newArray.length];
		List<Integer> newList = new ArrayList<Integer>();

		newList = MakeNum(newList, newArray, visited, 0, newArray.length, newArray.length-k);

		Collections.sort(newList,Collections.reverseOrder());
		System.out.println(newList.get(0));
	}

	public static List<Integer> MakeNum(List<Integer> newList, String[] newArray, boolean[] visited, int start, int n, int r) {
		if(r == 0) {
			StringBuilder newInt = new StringBuilder();
			for (int i = 0; i < n; i++) {
	            if (visited[i]) {
	            	newInt.append(newArray[i]);
	            }
	        }

			newList.add(Integer.parseInt(newInt.toString()));
			return newList;
		}

		for(int i = start; i < n; i++) {
			visited[i] = true;
			newList = MakeNum(newList, newArray, visited,i+1,n,r-1);
			visited[i] = false;
		}

		return newList;

	}

}
