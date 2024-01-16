package practice.lv2;

import java.util.*;
public class Oil_drilling {

	/*
	 * 세로길이가 n 가로길이가 m인 격자 모양의 땅 속에서 석유가 발견되었습니다.
	 * 석유는 여러 덩어리로 나누어 묻혀있습니다.
	 * 당신이 시추관을 수직으로 단 하나만 뚫을 수 있을 때, 가장 많은 석유를 뽑을 수 있는 시추관의 위치를 찾으려고 합니다.
	 * 시추관은 열 하나를 관통하는 형태여야 하며, 열과 열 사이에 시추관을 뚫을 수 없습니다.
	 *
	 * 예를 들어 가로가 8, 세로가 5인 격자 모양의 땅 속에 위 그림처럼 석유가 발견되었다고 가정하겠습니다.
	 * 상, 하, 좌, 우로 연결된 석유는 하나의 덩어리이며, 석유 덩어리의 크기는 덩어리에 포함된 칸의 수입니다.
	 * 그림에서 석유 덩어리의 크기는 왼쪽부터 8, 7, 2입니다.
	 * 시추관은 위 그림처럼 설치한 위치 아래로 끝까지 뻗어나갑니다. 만약 시추관이 석유 덩어리의 일부를 지나면
	 * 해당 덩어리에 속한 모든 석유를 뽑을 수 있습니다. 시추관이 뽑을 수 있는 석유량은 시추관이 지나는 석유 덩어리들의
	 * 크기를 모두 합한 값입니다. 시추관을 설치한 위치에 따라 뽑을 수 있는 석유량은 다음과 같습니다.

	시추관의 위치	획득한 덩어리	총 석유량
		1			[8]			8
		2			[8]			8
		3			[8]			8
		4			[7]			7
		5			[7]			7
		6			[7]			7
		7			[7, 2]		9
		8			[2]			2
	오른쪽 그림처럼 7번 열에 시추관을 설치하면 크기가 7, 2인 덩어리의 석유를 얻어 뽑을 수 있는 석유량이
	9로 가장 많습니다. 석유가 묻힌 땅과 석유 덩어리를 나타내는 2차원 정수 배열 land가 매개변수로 주어집니다.
	이때 시추관 하나를 설치해 뽑을 수 있는 가장 많은 석유량을 return 하도록 solution 함수를 완성해 주세요.*/
	    private static int width = 0;
	    private static int height = 0;
		public static void main(String[] args) {
			int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0}
						   ,{0, 0, 0, 0, 1, 1, 0, 0}
						   ,{1, 1, 0, 0, 0, 1, 1, 0}
						   ,{1, 1, 1, 0, 0, 0, 0, 0}
						   ,{1, 1, 1, 0, 0, 0, 1, 1}};

			solution(land);

		}

	    public static int solution(int[][] land) {
	        int answer = 0;

	        width = land[0].length;
	        height = land.length;
	        boolean[][] visited = new boolean[height][width];

	        int size = 0;
	        int oilId = 1;

	        Map<Integer, Integer> map = new HashMap<>();

	        for(int j = 0; j < width; j++) {
	        	for(int i = 0; i < height; i++) {
	        		if(land[i][j] == 1 && !visited[i][j]) {
	        			size = bfs(i, j, land, 1, visited, oilId);
	        			map.put(oilId, size);
	        			oilId++;
	        		}
	        	}
	        }

	        int[] oilSum = new int[width];
	       	for(int j = 0; j < width; j++) {
	        	Set<Integer> oilSet = new HashSet<>();
	        	for(int i = 0; i < height; i++) {
	        		if(land[i][j] != 0) oilSet.add(land[i][j]);
	        	}
	        	for(int item : oilSet) {
	        		oilSum[j] += map.get(item);
	        	}

	        	answer = Math.max(oilSum[j], answer);
	        }

	        return answer;
	    }

	    public static int bfs(int x, int y, int[][] land, int sum, boolean[][] visited, int oilId) {
	    	Queue<int[]> queue = new LinkedList<>();
	    	queue.offer(new int[] {x,y});

	    	int[] dx = {-1, 0 , 1, 0};
	    	int[] dy = {0, -1, 0, 1};

	    	visited[x][y] = true;
	    	land[x][y] = oilId;

	    	while(!queue.isEmpty()) {
	    		int[] current = queue.poll();
	    		for(int i = 0; i < 4; i++) {
	    			int nx = current[0] + dx[i];
	    			int ny = current[1] + dy[i];

	    			if(nx >= 0 && nx < height && ny >= 0 && ny < width && land[nx][ny] == 1 && !visited[nx][ny]) {
	    				queue.offer(new int[] {nx,ny});
	    				visited[nx][ny] = true;
	    				land[nx][ny] = oilId;
	    				sum++;
	    			}
	    		}
	    	}

	    	return sum;
	    }

}
