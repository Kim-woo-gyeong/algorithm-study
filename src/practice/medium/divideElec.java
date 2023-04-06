package practice.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class divideElec {
/*
 * 문제 설명
n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다. 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를
2개로 분할하려고 합니다. 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.

송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다. 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록
두 전력망으로 나누었을 때, 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return 하도록 solution 함수를 완성해주세요.

제한사항
n은 2 이상 100 이하인 자연수입니다.
wires는 길이가 n-1인 정수형 2차원 배열입니다.
wires의 각 원소는 [v1, v2] 2개의 자연수로 이루어져 있으며, 이는 전력망의 v1번 송전탑과 v2번 송전탑이 전선으로
연결되어 있다는 것을 의미합니다.
1 ≤ v1 < v2 ≤ n 입니다.
전력망 네트워크가 하나의 트리 형태가 아닌 경우는 입력으로 주어지지 않습니다.
입출력 예
n	wires	result
9	[[1,3],[2,3],[3,4],[4,5],[4,6],[4,7],[7,8],[7,9]]	3
4	[[1,2],[2,3],[3,4]]	0
7	[[1,2],[2,7],[3,7],[3,4],[4,5],[6,7]]	1
입출력 예 설명
입출력 예 #1

다음 그림은 주어진 입력을 해결하는 방법 중 하나를 나타낸 것입니다.
ex1.png
4번과 7번을 연결하는 전선을 끊으면 두 전력망은 각 6개와 3개의 송전탑을 가지며, 이보다 더 비슷한 개수로 전력망을 나눌 수 없습니다.
또 다른 방법으로는 3번과 4번을 연결하는 전선을 끊어도 최선의 정답을 도출할 수 있습니다.
입출력 예 #2

다음 그림은 주어진 입력을 해결하는 방법을 나타낸 것입니다.
ex2.png
2번과 3번을 연결하는 전선을 끊으면 두 전력망이 모두 2개의 송전탑을 가지게 되며, 이 방법이 최선입니다.
입출력 예 #3

다음 그림은 주어진 입력을 해결하는 방법을 나타낸 것입니다.
ex3.png
3번과 7번을 연결하는 전선을 끊으면 두 전력망이 각각 4개와 3개의 송전탑을 가지게 되며, 이 방법이 최선입니다.
 * */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int n = 9;
//		int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};

//		int n = 4;
//		int[][] wires = {{1,2},{2,3},{3,4}};

		int n = 7;
		int[][] wires = {{1,2},{2,7},{3,7},{3,4},{4,5},{6,7}};
		solutions(n, wires);
	}

	public static void solutions(int n, int[][] wires) {

        ArrayList<Integer>[] list = new ArrayList[n+1];

        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            list[v1].add(v2);
            list[v2].add(v1);
        }


        int min = 9999;
		for(int[] item : wires) {
			boolean[] visited = new boolean[n+1];
			visited[1] = true;
			getParent(list,visited, 1, item[0], item[1]);
//			println(list, visited);
			int cnt = 0;
			for(boolean visit : visited) {
				if(visit) cnt++;
			}

			min = Math.min(min, Math.abs((n - cnt) - cnt));
		}
		System.out.println(min);

	}
	public static void dfs2(int[][] wires, int n) {
		for(int i = 1; i <= wires.length; i++) {
			System.out.println(wires[i][0] + " , " + wires[i][1] + " ");
		}
	}
	public static void println(ArrayList<Integer>[] list, boolean[] visited) {
		for(int i = 1; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
		System.out.println();
		System.out.println();

		for(int i = 1; i < visited.length; i++) {
			System.out.print(visited[i] + " ");
		}
		System.out.println();
	}
	public static void getParent(ArrayList<Integer>[] list,boolean[] visited, int start, int a, int b) {
		// 1 == a
		for(int num : list[start]) {
			if(((start == a && num == b) || (start == b && num == a))) continue;
			if(!visited[num]) {
				visited[num] = true;
				getParent(list, visited, num, a, b);
			}
		}
	}
}
