package practice.lv2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class ConversNumbers {

	/*
	 * 자연수 x를 y로 변환하려고 합니다. 사용할 수 있는 연산은 다음과 같습니다.
		x에 n을 더합니다
		x에 2를 곱합니다.
		x에 3을 곱합니다.
		자연수 x, y, n이 매개변수로 주어질 때, x를 y로 변환하기 위해 필요한 최소 연산 횟수를 return하도록
		solution 함수를 완성해주세요. 이때 x를 y로 만들 수 없다면 -1을 return 해주세요.
	 * */
	private static int min = -1;
	private static int YY = 0;
	private static int NN = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 10; int y = 40; int n = 30;
		// 10*2 = 20*2 = 40 2번
		// x * 2 * 2
		// 10 + 30 = 40 1번
		/*
		 * 40을 만들 수 있는 조건.
		 * x*2로 y가 인수분해 되어야함.
		 * x*3로y가 인수분해되어야함.
		 * (x+n)으로 인수분해되어야함.
		 * */

		x = 2; y = 5; n = 4;
		// -1
		// 1.x*2  2.x + n 3.x*3
		//

//		x = 5; y = 35; n = 5;
		/*
		 * 5 * 3 = 15 * 2 = 30 + 5
		 *
		 * */

//		x = 10; y = 40; n = 5;
		solution(x, y, n);
	}

	public static int solution(int x, int y, int n) {
        int answer = 0;

//        if(!(y % (x*2) == 0 || y % (x*3) == 0 || y % (x+n) == 0)) {
//        	answer = -1;
//        	return answer;
//        }

//        int num1 = x;
//        int num2 = x;
//        int num3 = x;
//        while(y != num1 && y != num2 && y != num3) {
//        	num1 *= 2;
//        	num2 *= 3;
//        	num3 += n;
//        	answer++;
//        }
        Queue<Integer> bowl = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
//        bowl.add(x);
        YY = y;
        NN = n;
        set.add(x);
        DFS(0, set);
        System.out.println(min);
        return answer;
    }

	public static void DFS(int count, Set<Integer> set) {
		System.out.println(set);
		Iterator<Integer> temp = set.iterator();
		Queue<Integer> bowl = new LinkedList<>();
		if(set.isEmpty()) return;
		while(temp.hasNext()) {
			int curNum = temp.next();
			if(min > -1 && count > min) {
				return;
			}

			if(curNum == YY) {
				if(min == -1) min = count;
				min = Math.min(min, count);
				return;
			}

			if(curNum*2 <= YY) {
				bowl.add(curNum*2);
			}

			if(curNum*3 <= YY) {
				bowl.add(curNum*3);
			}

			if(curNum+NN <= YY) {
				bowl.add(curNum+NN);
			}

		}
		System.out.println(bowl);
		set = new HashSet<>();
		while(!bowl.isEmpty()) {
			int num = bowl.poll();
			set.add(num);
		}

		DFS(count+1, set);
	}

}
