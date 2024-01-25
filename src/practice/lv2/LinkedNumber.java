package practice.lv2;
import java.util.*;

public class LinkedNumber {
/*
 * 비내림차순으로 정렬된 수열이 주어질 때, 다음 조건을 만족하는 부분 수열을 찾으려고 합니다.
 * 기존 수열에서 임의의 두 인덱스의 원소와 그 사이의 원소를 모두 포함하는 부분 수열이어야 합니다. 부분 수열의 합은 k입니다.
 * 합이 k인 부분 수열이 여러 개인 경우 길이가 짧은 수열을 찾습니다. 길이가 짧은 수열이 여러 개인 경우 앞쪽(시작 인덱스가 작은)에 나오는 수열을 찾습니다.
 * 수열을 나타내는 정수 배열 sequence와 부분 수열의 합을 나타내는 정수 k가 매개변수로 주어질 때,
 * 위 조건을 만족하는 부분 수열의 시작 인덱스와 마지막 인덱스를 배열에 담아 return 하는 solution 함수를 완성해주세요.
 * 이때 수열의 인덱스는 0부터 시작합니다.
 * */
	private static int _length = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// [1, 2, 3, 4, 5]	7	[2, 3]
//		int[] sequence = {1,2,3,4,5};
//		int k = 7;

		// [1, 1, 1, 2, 3, 4, 5]	5	[6, 6]
//		int[] sequence = {1, 1, 1, 2, 3, 4, 5};
//		int k = 5;

		// [2, 2, 2, 2, 2, 10, 10, 10, 10, 10, 10], 30 [5, 7]
		int[] sequence = {2, 2, 2, 2, 2, 10, 10, 10, 10, 10, 10};
		int k = 30;

		solution(sequence, k);
	}
	private static int min = 1000001;

	public static int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];

        _length = sequence.length;

        answer = bfs(0, sequence, k);

        //System.out.println("["+answer[0]+","+answer[1]+"]");
        return answer;
    }

	public static int[] bfs(int start, int[] sequence, int k) {
		int sum = 0;
		Deque<Integer> deque = new LinkedList<Integer>();
		int[] answer = new int[2];
		while(start <= _length) {
			if(sum >= k) {
				if(sum == k) {
					if(deque.size() < min) {
		    			min = deque.size();
		    			answer[0] = deque.peekFirst();
		    			answer[1] = deque.peekLast();
		    		}
				}
				int idx = deque.pollFirst();
				sum -= sequence[idx];
				continue;
			}

			if(start == _length) break;

			sum += sequence[start];
			deque.addLast(start);

			start++;
		}

		return answer;
	}
}
