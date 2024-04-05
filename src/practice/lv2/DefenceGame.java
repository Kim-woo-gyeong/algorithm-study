package practice.lv2;

import java.util.*;

public class DefenceGame {
/*
 * 준호는 요즘 디펜스 게임에 푹 빠져 있습니다. 디펜스 게임은 준호가 보유한 병사 n명으로 연속되는 적의 공격을 순서대로 막는 게임입니다.
 * 디펜스 게임은 다음과 같은 규칙으로 진행됩니다.
 *
 * 1.준호는 처음에 병사 n명을 가지고 있습니다.
 * 2.매 라운드마다 enemy[i]마리의 적이 등장합니다.
 * 3.남은 병사 중 enemy[i]명 만큼 소모하여 enemy[i]마리의 적을 막을 수 있습니다.
 *   예를 들어 남은 병사가 7명이고, 적의 수가 2마리인 경우, 현재 라운드를 막으면 7 - 2 = 5명의 병사가 남습니다.
 *   남은 병사의 수보다 현재 라운드의 적의 수가 더 많으면 게임이 종료됩니다.
 * 4. 게임에는 무적권이라는 스킬이 있으며, 무적권을 사용하면 병사의 소모없이 한 라운드의 공격을 막을 수 있습니다.
 * 5. 무적권은 최대 k번 사용할 수 있습니다.
 *
 * 준호는 무적권을 적절한 시기에 사용하여 최대한 많은 라운드를 진행하고 싶습니다. 준호가 처음 가지고 있는 병사의 수 n,
 * 사용 가능한 무적권의 횟수 k, 매 라운드마다 공격해오는 적의 수가 순서대로 담긴 정수 배열 enemy가 매개변수로 주어집니다.
 * 준호가 몇 라운드까지 막을 수 있는지 return 하도록 solution 함수를 완성해주세요.
 * */
	private static long start = 0L;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 5
		int n = 7;
		int k = 3;
		int[] enemy = {4, 2, 4, 5, 3, 3, 1};
		/*			   4  6 10
		 *			   0  2  6  11
		 * */
		/* int n = 9;
		 * int k = 3;
		 * int[] enemy = {4, 2, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		 *                X  6  10 11 12 13 14
		 *                   2  6  7  8  9  10
		 *                   X     1  2  3  4  5  6  7  8  9  10
		 * */

//		int n = 2;
//		int k = 4;
//		int[] enemy = {3, 3, 3, 3};

//		int n = 1;
//		int k = 6;
//		int[] enemy = {2, 2, 2, 2, 3, 3, 1};
		//			   2  4
//		int n = 9;
//		int k = 3;
//		int[] enemy = {4, 2, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

//		int n = 10;
//		int k = 2;
//		int[] enemy = {5, 5, 5, 5, 5};
		solution(n, k, enemy);
	}

	public static int solution(int n, int k, int[] enemy) {
        int answer = -1;

        // 1. enemy 만큼 반복문
        int size = enemy.length;
        Queue<Integer> bowl = new LinkedList<Integer>();
        PriorityQueue<Integer> tt = new PriorityQueue<>(Collections.reverseOrder());
        long S = 0L;
        bowl.add(0);

        int[] max = new int[] {enemy[0],0};
        while(true) {

        	int idx = bowl.poll();
        	tt.add(enemy[idx]);
        	S += enemy[idx];

        	if(S > n) {
        		if(k == 0) {
        			answer = idx;
        			break;
        		}
        		S -= tt.poll();
        		bowl.clear();
        		k--;
        	}

        	if(bowl.isEmpty()) {
        		bowl.add(idx+1);
        	}

        	if(idx+1 == size) {
        		break;
        	}
        }

       System.out.println(">>>>>>>>" + bowl);
      if(answer == -1) {
    	  answer = bowl.poll();
      }
      // 3. 그 곳을 무적권을 사용함.
      System.out.println(answer);
        return answer;
    }
}
