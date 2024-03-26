 package practice.lv2;

import java.util.*;

public class MagicElevator {
/*
 * 마법의 세계에 사는 민수는 아주 높은 탑에 살고 있습니다. 탑이 너무 높아서 걸어 다니기 힘든 민수는
 * 마법의 엘리베이터를 만들었습니다. 마법의 엘리베이터의 버튼은 특별합니다.
 * 마법의 엘리베이터에는 -1, +1, -10, +10, -100, +100 등과 같이 절댓값이 10c
 * (c ≥ 0 인 정수) 형태인 정수들이 적힌 버튼이 있습니다. 마법의 엘리베이터의 버튼을 누르면
 * 현재 층 수에 버튼에 적혀 있는 값을 더한 층으로 이동하게 됩니다. 단, 엘리베이터가 위치해 있는 층과
 * 버튼의 값을 더한 결과가 0보다 작으면 엘리베이터는 움직이지 않습니다. 민수의 세계에서는 0층이 가장
 * 아래층이며 엘리베이터는 현재 민수가 있는 층에 있습니다.
 *
 * 마법의 엘리베이터를 움직이기 위해서 버튼 한 번당 마법의 돌 한 개를 사용하게 됩니다.
 * 예를 들어, 16층에 있는 민수가 0층으로 가려면 -1이 적힌 버튼을 6번, -10이 적힌 버튼을 1번 눌러 마법의 돌
 * 7개를 소모하여 0층으로 갈 수 있습니다. 하지만, +1이 적힌 버튼을 4번, -10이 적힌 버튼 2번을 누르면 마법의 돌
 * 6개를 소모하여 0층으로 갈 수 있습니다.
 *
 * 마법의 돌을 아끼기 위해 민수는 항상 최소한의 버튼을 눌러서 이동하려고 합니다. 민수가 어떤 층에서 엘리베이터를
 * 타고 0층으로 내려가는데 필요한 마법의 돌의 최소 개수를 알고 싶습니다. 민수와 마법의 엘리베이터가 있는 층을
 * 나타내는 정수 storey 가 주어졌을 때, 0층으로 가기 위해 필요한 마법의 돌의 최소값을 return 하도록
 * solution 함수를 완성하세요.
 * */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int storey = 16; // 6
//		int storey = 2554; // 16
//		int storey = 2999;

		// 2554 1000 * 2
		// 554  100 * 5
		// 54   -1 * 4
		// 50   10 * 5

		/* 3000 - 2554 = 446 -> 1000*3
		 * 446 = 100 * 4
		 * 46 = 1 * 4 + 10 * 5
		 * */

		/*
		 * 2999
		 *
		 * 3000 - 2999 = 1 1000 * 3 + 1
		 * */

//		int storey = 2944;
		/*
		 * 2944
		 *
		 * 3000 - 2944 = 56 : 1000 * 3
		 * 56 : 1*4 = 60 10*6 ==> 13개
		 *
		 * */

//		int storey = 165; // 10
		/*
		 * 200층 100*2
		 * -35층 -30층 -10*3
		 * -5층 -1*5
		 *
		 * */

//		int storey = 95; // 6
		int storey = 555; // 14
		/*
		 * 100 * 1 = 100 - 95 = 5
		 * */
		solution(storey);
	}

	private static int result = 0;
	public static int solution(int storey) {
        int answer = 0;
        int cnt = 0;
//        int number = storey;
//        // 1. 자릿수구하기
//        while(number > 0) {
//        	number /= 10;
//        	cnt++;
//        }
//
//
//        // 2. 절반인지 아닌지 체크.
//        String middle = "";
//        for(int i = 0; i < cnt; i++) {
//        	middle += "5";
//        }
//
//        long targetNum = 0;
//        if(storey >= Long.parseLong(middle)) {
//        	targetNum = (long) Math.pow(10, cnt);
//        	result +=1;
//        } else {
//        	int mod = (int) (storey / Math.pow(10, cnt-1));
//        	targetNum = (long) (mod * Math.pow(10, cnt-1));
//        	result += mod;
//        }


        getTargetNum(storey, 0, answer);
        System.out.println(result);
        return result;
    }

	public static void getTargetNum(int storey, long targetNum, int answer) {
		storey = (int) Math.abs(storey - targetNum);

		if(storey == 5) {
			result += (answer + 5);
			return;
		}
		if(storey == 0) {
			result += answer;
			return;
		}
		int number = storey;
		int cnt = 0;
        // 1. 자릿수구하기
        while(number > 0) {
        	number /= 10;
        	cnt++;
        }

		// 2. 절반인지 아닌지 체크.
        String middle = "";
        for(int i = 0; i < cnt; i++) {
        	middle += "5";
        }

        if(storey >= Long.parseLong(middle)) {
        	targetNum = (long) Math.pow(10, cnt);
        	// +
        	answer += 1;
        } else {
        	int mod = (int) (storey / Math.pow(10, cnt-1));
        	targetNum = (long) (mod * Math.pow(10, cnt-1));
        	// -
        	answer += (long) mod;
        }
        getTargetNum(storey, targetNum, answer);
	}

}
