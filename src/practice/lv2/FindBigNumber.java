package practice.lv2;

import java.util.*;

public class FindBigNumber {
/*
 * 정수로 이루어진 배열 numbers가 있습니다. 배열 의 각 원소들에 대해 자신보다 뒤에 있는 숫자 중에서
 * 자신보다 크면서 가장 가까이 있는 수를 뒷 큰수라고 합니다. 정수 배열 numbers가 매개변수로
 * 주어질 때, 모든 원소에 대한 뒷 큰수들을 차례로 담은 배열을 return 하도록
 * solution 함수를 완성해주세요. 단, 뒷 큰수가 존재하지 않는 원소는 -1을 담습니다.
 * */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 3, 5, 5, -1
		int[] numbers = {2, 3, 3, 5};
		/*
		 * 5           -1     5
		 * 3  5 > 3?   5      5 3
		 * 3  3 >= 3?
		 *    5 > 3?   5      5 3
		 * */

		// -1, 5, 6, 6, -1, -1
		numbers = new int[]{9, 1, 5, 3, 6, 2};
		// queue = 9 1 1 1 1 2
		// 9 1 5 3 7 8 7 6 3 4 8

		/*
		 * 8 		    -1
		 * 4   8 > 4     8     8
		 * 3   4 > 3     4     8 4 3
		 * 6   3 > 6?
		 *     4 > 6?
		 *     8 > 6?    8     8 6
		 * 7   6 > 7?
		 *     8 > 7?    8     8 7
		 * 8   7 > 8?
		 *     8 > 8?    -1    8
		 * 7   8 > 7?    8     8 7
		 * 3   7 > 3?    7     8 7 3
		 *
		 * */


		numbers = new int[] {9, 5, 3, 3, 3, 3, 3, 3, 9};

		solution(numbers);
	}

	public static int[] solution(int[] numbers) {
        int[] answer = {};

        int size = numbers.length;

        int[] temp = new int[size];
        for(int i = 0; i < size; i++) {
        	temp[i] = numbers[i];
        }

        Arrays.sort(temp);

        int max = temp[size-1];

        int[] largeNum = new int[size];

        Stack<Integer> stack = new Stack<Integer>();
        Queue<Integer> nQueue = new LinkedList<Integer>();


        largeNum[size-1] = -1;
        stack.add(numbers[size-1]);
        for(int idx = size-2; idx > -1; idx--) {
        	boolean chk = false;
        	largeNum[idx] = -1;
        	System.out.print(stack);
        	while(!stack.isEmpty()) {
        		int num = stack.peek();
        		System.out.println("\t" + num + " > " +numbers[idx]);
        		if(num > numbers[idx]) {
        			stack.add(numbers[idx]);
        			largeNum[idx] = num;
        			chk = true;
        			break;
        		} else {
        			stack.pop();
        		}
        	}
        	if(!chk) {
        		stack.add(numbers[idx]);
        	}
        }

        System.out.println();
        for(int i = 0; i < size; i++) {
        	System.out.println(largeNum[i] + " ");
        }
        return answer;
    }

}
