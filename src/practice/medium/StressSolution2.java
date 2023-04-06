package practice.medium;

import java.util.Arrays;

public class StressSolution2 {
	/*
	 * 직접푼것
	 * */

	int count;

	StressSolution2(int answer) {
		this.count = answer;
	}
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
		int temp = 0;;

		for(int i = 0; i < 2; i++) {
			temp = dungeons[curIdx][i];
			dungeons[curIdx][i] = dungeons[preIdx][i];
			dungeons[preIdx][i] = temp;
		}
	}

	public static void dfs(int k, int[][] dungeons, int pick, int depth,StressSolution2 temp,
			boolean[] visited) {

		if(pick == depth) {
			int count = 0;
			for(int i = 0; i < pick; i++) {
//				if(dungeons[i][0] > k) break;
//
//				k -= dungeons[i][1];
//				count++;
				System.out.print(dungeons[i][0] + "\t");
			}
			System.out.println();
			if(pick == count) {
				temp.count = count;
			}
			return ;
		}

		for(int i = depth; i < dungeons.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				change(dungeons, depth, i);
				dfs(k , dungeons, pick, depth+1, temp,visited);
				change(dungeons, depth, i);
				visited[i] = false;
			}
//			change(dungeons, depth, i);
		}

		return ;
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
		int _length = dungeons.length;
		StressSolution2 temp = new StressSolution2(answer);

		for(int i = _length -1 ; i >= 0; i--) {
			System.out.println((i+1)+"개 pick");
			boolean[] visited = new boolean[_length];
			dfs(k, dungeons, i+1, 0, temp, visited);
			if(temp.count != -1) break;
		}

		answer = temp.count;
		System.out.println(answer);
	}

}

