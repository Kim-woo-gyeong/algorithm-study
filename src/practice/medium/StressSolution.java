package practice.medium;

import java.util.Arrays;

public class StressSolution {
	/*
	 * 더 깔끔한 소스
	 * */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int k = 80;
//		int[][] dungeons = {{80,20},{50,40},{30,10}};

		int k = 80;
		int[][] dungeons = {{80,20},{50,40},{30,10},{40, 20}};

//		int k = 40;
//		int[][] dungeons = {{40, 20},{10, 10},{10, 10},{10, 10},{10, 10}};
		//answer = 4
		solutions(k, dungeons);
	}

	public static void change(int[][] dungeons, int preIdx, int curIdx) {
		int temp = 0;

		for(int i = 0; i < 2; i++) {
			temp = dungeons[curIdx][i];
			dungeons[curIdx][i] = dungeons[preIdx][i];
			dungeons[preIdx][i] = temp;
		}
	}


	public static void println(int[][] dungeons) {
		for(int j = 0; j < dungeons.length; j++) {
			for(int i = 0; i < 2; i++) {
				System.out.print(dungeons[j][i] + "\t");
			}
			System.out.println();
		}

	}

	public static void solutions(int k , int[][] dungeons) {

		int answer = -1;
 		System.out.println(dfs(k, dungeons));
	}

	public static int dfs(int k, int [][] dungeons) {
		int cnt = 0;
		int idx = 0;
		for(int[] d : dungeons) {
			int a = d[0]; int b = d[1];
			System.out.println("idx : " + idx + " dungeons : " +d[0] + " k = " + k);
			if(a <= k) {
				d[0] = 9999;
				cnt = Math.max(1 + dfs(k - b, dungeons), cnt);
				d[0] = a;
			}
			idx++;
		}
		System.err.println("idx : " + idx + " cnt : " + cnt);
		return cnt;
	}


}
