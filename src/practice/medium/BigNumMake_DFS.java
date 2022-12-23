package practice.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BigNumMake_DFS {

	public static void main(String[] args) {
		String number = "1231234"; //
		int k = 3;
		makeBigNum(number,k);
	}

	public static void makeBigNum(String number, int k) {
		String [] arrNum = number.split("");
		k = arrNum.length - k;

		List<Integer> newList = new ArrayList<Integer>();
		newList = dfs(newList, arrNum ,0, k);

		Collections.sort(newList, Collections.reverseOrder());
		System.out.println(newList);

	}

	public static List<Integer> dfs(List<Integer> newList, String [] arrNum, int dept, int k) {
		if(dept == k) {
			StringBuilder newNum = new StringBuilder();
			for(int i = 0; i < dept; i++) {
				newNum.append(arrNum[i]);
			}

			newList.add(Integer.parseInt(newNum.toString()));
			return newList;
		}

		for(int i = dept; i < arrNum.length; i++) {
			swap(arrNum,dept,i);
			newList = dfs(newList, arrNum, dept+1, k);
			swap(arrNum,dept,i);
		}

		return newList;
	}

	public static void swap(String[] arrNum, int dept, int i) {
		String temp = arrNum[dept];
		arrNum[dept] = arrNum[i];
		arrNum[i] = temp;
	}

}
