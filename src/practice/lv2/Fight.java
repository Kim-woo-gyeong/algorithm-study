package practice.lv2;

import java.util.*;

public class Fight {

	/*
	 * A 나라가 B 나라를 침공하였습니다. B 나라의 대부분의 전략 자원은 아이기스 군사 기지에 집중되어 있기 때문에
	 * A 나라는 B 나라의 아이기스 군사 기지에 융단폭격을 가했습니다.
	 * A 나라의 공격에 대항하여 아이기스 군사 기지에서는 무수히 쏟아지는 폭격 미사일들을 요격하려고 합니다.
	 * 이곳에는 백발백중을 자랑하는 요격 시스템이 있지만 운용 비용이 상당하기 때문에
	 * 미사일을 최소로 사용해서 모든 폭격 미사일을 요격하려 합니다. A 나라와 B 나라가 싸우고 있는 이 세계는
	 * 2 차원 공간으로 이루어져 있습니다. A 나라가 발사한 폭격 미사일은 x 축에 평행한 직선 형태의 모양이며
	 * 개구간을 나타내는 정수 쌍 (s, e) 형태로 표현됩니다. B 나라는 특정 x 좌표에서 y 축에 수평이 되도록
	 * 미사일을 발사하며, 발사된 미사일은 해당 x 좌표에 걸쳐있는 모든 폭격 미사일을 관통하여 한 번에 요격할 수 있습니다.
	 * 단, 개구간 (s, e)로 표현되는 폭격 미사일은 s와 e에서 발사하는 요격 미사일로는 요격할 수 없습니다.
	 * 요격 미사일은 실수인 x 좌표에서도 발사할 수 있습니다.
	 * 각 폭격 미사일의 x 좌표 범위 목록 targets이 매개변수로 주어질 때, 모든 폭격 미사일을 요격하기 위해 필요한
	 * 요격 미사일 수의 최솟값을 return 하도록 solution 함수를 완성해 주세요.
	 * */

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			// result = 3
			//int[][] targets = {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}};

			// result = 4
//			int[][] targets = {{1,2},{2,4},{9,5},{3,7},{11,2}};

			// result = 5
//			int[][] targets = {{7,8}, {2, 9}, {6, 9}, {4, 12}, {9, 13}, {6,7}, {3,8}, {3, 6}, {2,4}};

			// result = 3
//			int[][] targets = {{1, 2},{3, 100},{4, 5},{6, 7}};

			// result = 1
//			int[][] targets = {{1, 3},{1, 3},{1, 3},{1, 3}};

			// result = 1
//			int[][] targets = {{1, 5},{2, 6},{3, 7},{4, 8},{4, 5}};

			// result = 5
//			int[][] targets = {{1, 2},{2, 3},{3, 4},{4, 5},{5, 6},{1, 6}};

			//result = 2
			int[][] targets = {{0, 4},{0,1},{2, 3}};
			solution(targets);
		}

		public static int solution(int[][] targets) {
	        int answer = 0;
	        /*
	         * 최소한의 폭탄 수.
	         *
	         * 1     4
	         *     3       7
	         *         5               12
	         *                      11    13
	         *                   10          14
	         *       4       8
	         *       4 5
	         * 1 2 3 4 5 6 7 8 9 10 11 12 13 14
	         * */

	        // 최소한 미사일 수.
	        // ex) [1,4][3,7] 1개. 3.5
	        // [1,4][3,7][4,5][4,8][5,12][10,14][11,13]
	        int height = targets.length;

	        Queue<int[]> bomb = new LinkedList<int[]>();

	        Arrays.sort(targets, (o1,o2) -> {
	        	return o1[1] - o2[1];	// 오름차순
	        });

			int min = targets[0][1];
			for(int i = 0; i < height; i++) {
				if(targets[i][0] < min && min <= targets[i][1]) {
					continue;
				}
				answer++;
				min = targets[i][1];
			}
			System.out.println(answer);
	        return answer;
	    }

}
