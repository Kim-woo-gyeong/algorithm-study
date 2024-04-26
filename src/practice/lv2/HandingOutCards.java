package practice.lv2;

import java.util.*;

public class HandingOutCards {
/*
 * 철수와 영희는 선생님으로부터 숫자가 하나씩 적힌 카드들을 절반씩 나눠서 가진 후, 다음 두 조건 중 하나를
 * 만족하는 가장 큰 양의 정수 a의 값을 구하려고 합니다.
 * 1.철수가 가진 카드들에 적힌 모든 숫자를 나눌 수 있고 영희가 가진 카드들에 적힌 모든 숫자들 중 하나도
 * 나눌 수 없는 양의 정수 a
 * 2.영희가 가진 카드들에 적힌 모든 숫자를 나눌 수 있고, 철수가 가진 카드들에 적힌 모든 숫자들 중
 * 하나도 나눌 수 없는 양의 정수 a
 *
 * 예를 들어, 카드들에 10, 5, 20, 17이 적혀 있는 경우에 대해 생각해 봅시다.
 * 만약, 철수가 [10, 17]이 적힌 카드를 갖고, 영희가 [5, 20]이 적힌 카드를 갖는다면 두 조건 중
 * 하나를 만족하는 양의 정수 a는 존재하지 않습니다. 하지만, 철수가 [10, 20]이 적힌 카드를 갖고,
 * 영희가 [5, 17]이 적힌 카드를 갖는다면, 철수가 가진 카드들의 숫자는 모두 10으로 나눌 수 있고,
 * 영희가 가진 카드들의 숫자는 모두 10으로 나눌 수 없습니다.
 * 따라서 철수와 영희는 각각 [10, 20]이 적힌 카드, [5, 17]이 적힌 카드로 나눠 가졌다면
 * 조건에 해당하는 양의 정수 a는 10이 됩니다.
 * 철수가 가진 카드에 적힌 숫자들을 나타내는 정수 배열 arrayA와 영희가 가진 카드에 적힌 숫자들을
 * 나타내는 정수 배열 arrayB가 주어졌을 때, 주어진 조건을 만족하는 가장 큰 양의 정수 a를 return하도록 solution 함수를 완성해 주세요.
 * 만약, 조건을 만족하는 a가 없다면, 0을 return 해 주세요.
 * */
	public static void main(String[] args) {
		// 0
//		int[] arrayA = {10,17};
//		int[] arrayB = {5,20};

//		int[] arrayA = {14, 35, 119};
//		int[] arrayB = {18, 30, 102};

		int[] arrayA = {10, 20};
		int[] arrayB = {5, 17};

//		int[] arrayA = {14};
//		int[] arrayB = {49};

        solution(arrayA, arrayB);
	}

	public static int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        long max = 0;
        PriorityQueue<Long> bowlA = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Long> bowlB = new PriorityQueue<>(Collections.reverseOrder());

        PriorityQueue<Long> dividedA = new PriorityQueue<>(Collections.reverseOrder());
        dividedA.addAll(getAvailableDivided(arrayA));
        bowlA.addAll(check(arrayA, dividedA));

        System.out.println(bowlA);

        PriorityQueue<Long> dividedB = new PriorityQueue<>(Collections.reverseOrder());
        dividedB.addAll(getAvailableDivided(arrayB));
        bowlB.addAll(check(arrayB, dividedB));

        System.out.println(bowlB);
        if(!bowlA.isEmpty()) {
        	int num = getMax(arrayB, bowlA);
        	max = num;
        	System.out.println(num);
        }

        if(!bowlB.isEmpty()) {
          int num = getMax(arrayA, bowlB);
          max = Math.max(max, num);
          System.out.println(num);
        }
        answer = (int) max;
        System.out.println(answer);
        return answer;
    }

	public static PriorityQueue<Long> getAvailableDivided(int[] array) {
		PriorityQueue<Long> bowl = new PriorityQueue<>(Collections.reverseOrder());
        long num = 2;
        long x = array[0];
        long min = array[0];

        bowl.add(x);
        while(num <= x) {
        	if(min == num) break;
    		if(x%num == 0) {
    			bowl.add(num);

    			min = x / num;

    			if(min == num) break;
    			bowl.add(min);

    		}
    		num++;
    	}

        return bowl;
	}

	public static PriorityQueue<Long> check(int[] array, PriorityQueue<Long> bowl) {
		long num = 0L;
        for(long xx : array) {
        	PriorityQueue<Long> temp = new PriorityQueue<>();
        	while(!bowl.isEmpty()) {
        		num = bowl.poll();
        		if(xx % num == 0) {
        			temp.add(num);
        		}
        	}
        	bowl.addAll(temp);
        }

        return bowl;
	}

	public static int getMax(int[] array, PriorityQueue<Long> bowl) {
		int possible = -1;
        long num = 0L;
        while(!bowl.isEmpty()) {
      	  num = bowl.poll();
        	for(long xx : array) {
        		if(xx%num == 0) {
        			possible = 1;
        			break;
        		}
        	}

        	if(possible == 1) {
        		num = 0L;
        		break;
        	}

        	if(possible == -1) {
        		break;
        	}
        }

        return (int) num;
	}

}
