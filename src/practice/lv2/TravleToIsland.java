package practice.lv2;

import java.util.*;

public class TravleToIsland {
/*
 * 메리는 여름을 맞아 무인도로 여행을 가기 위해 지도를 보고 있습니다. 지도에는 바다와 무인도들에 대한 정보가 표시돼 있습니다.
 * 지도는 1 x 1크기의 사각형들로 이루어진 직사각형 격자 형태이며, 격자의 각 칸에는 'X' 또는 1에서 9 사이의 자연수가
 * 적혀있습니다. 지도의 'X'는 바다를 나타내며, 숫자는 무인도를 나타냅니다.
 * 이때, 상, 하, 좌, 우로 연결되는 땅들은 하나의 무인도를 이룹니다. 지도의 각 칸에 적힌 숫자는 식량을 나타내는데,
 * 상, 하, 좌, 우로 연결되는 칸에 적힌 숫자를 모두 합한 값은 해당 무인도에서 최대 며칠동안 머물 수 있는지를 나타냅니다.
 * 어떤 섬으로 놀러 갈지 못 정한 메리는 우선 각 섬에서 최대 며칠씩 머물 수 있는지 알아본 후 놀러갈 섬을 결정하려 합니다.
 * 지도를 나타내는 문자열 배열 maps가 매개변수로 주어질 때, 각 섬에서 최대 며칠씩 머무를 수 있는지 배열에
 * 오름차순으로 담아 return 하는 solution 함수를 완성해주세요.
 * 만약 지낼 수 있는 무인도가 없다면 -1을 배열에 담아 return 해주세요.
 * */
	public static void main(String[] args) {
		//[1, 1, 27]
//		String[] maps = {"X591X","X1X5X","X231X", "1XXX1"};
		String[] maps = {"XXX","XXX","XXX"};
		System.out.println(solution(maps));
	}

	private static int sum = 0;
	public static int[] solution(String[] maps) {
        int[] answer = {};
        print(maps);

        int row = maps.length;
        int col = maps[0].length();

        String[][] island = new String[row][col];
        for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				island[i][j] = String.valueOf(maps[i].charAt(j));
			}
		}

        int[] count = new int[row * col];
        boolean[][] visited = new boolean[row][col];
        BFS(island,count,0, visited);

        int[] result = new int[sum];
        if(sum == 0) return new int[] {-1};
        for(int i = 0; i < sum; i++) {
        	if(count[i] == 0) break;
        	result[i] = count[i];
        }

        Arrays.sort(result);

        for(int i = 0; i < sum; i++) {
        	System.out.println(result[i]);
        }

        return answer;
    }

	public static void BFS(String[][] island, int[] count, int num, boolean[][] visited) {
		int col = island[0].length;
		int row = island.length;

		int[] dx = {-1,0,1,0};
		int[] dy = {0,-1,0,1};

		Queue<int[]> queue = new LinkedList<int[]>();
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(!"X".equals(island[i][j]) && !visited[i][j]) {
					queue.add(new int[] {i,j});
					visited[i][j] = true;

					count[num] = Integer.parseInt(island[i][j]);
					visited[i][j] = true;
					break;
				}
			}

			if(!queue.isEmpty()) break;
		}

		if(queue.isEmpty()) {
			sum = num;
			return;
		}

		while(!queue.isEmpty()) {
			int[] pos = queue.poll();
			for(int dir = 0; dir < 4; dir++) {
				int nx = pos[0] + dx[dir];
				int ny = pos[1] + dy[dir];

				if(nx > -1 && nx < row && ny > -1 && ny < col) {
					if(!"X".equals(island[nx][ny]) && !visited[nx][ny]){
						count[num] += Integer.parseInt(island[nx][ny]);
						visited[nx][ny] = true;
						queue.add(new int[]{nx,ny});
					}
				}
			}
		}
		BFS(island, count, num+1, visited);
	}

	public static void print(String[] maps) {
		for(String map : maps) {
			String[] item = map.split("");
			for(String x : item) {
				System.out.print(x + "\t");
			}
			System.out.println();
		}
	}
}
