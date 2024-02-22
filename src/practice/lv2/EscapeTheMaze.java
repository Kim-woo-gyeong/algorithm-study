package practice.lv2;

import java.util.*;

public class EscapeTheMaze {
    /*
     * 1 x 1 크기의 칸들로 이루어진 직사각형 격자 형태의 미로에서 탈출하려고 합니다. 각 칸은 통로 또는 벽으로 구성되어 있으며,
     * 벽으로 된 칸은 지나갈 수 없고 통로로 된 칸으로만 이동할 수 있습니다. 통로들 중 한 칸에는 미로를 빠져나가는 문이 있는데,
     * 이 문은 레버를 당겨서만 열 수 있습니다. 레버 또한 통로들 중 한 칸에 있습니다. 따라서, 출발 지점에서 먼저 레버가 있는 칸으로
     * 이동하여 레버를 당긴 후 미로를 빠져나가는 문이 있는 칸으로 이동하면 됩니다. 이때 아직 레버를 당기지 않았더라도 출구가 있는 칸을
     * 지나갈 수 있습니다. 미로에서 한 칸을 이동하는데 1초가 걸린다고 할 때,
     * 최대한 빠르게 미로를 빠져나가는데 걸리는 시간을 구하려 합니다.
     * 미로를 나타낸 문자열 배열 maps가 매개변수로 주어질 때, 미로를 탈출하는데 필요한 최소 시간을 return 하는
     * solution 함수를 완성해주세요. 만약, 탈출할 수 없다면 -1을 return 해주세요.
     * */
    public static void main(String[] args) {
        /*
         * S : 시작 지점  E : 출구  L : 레버  O : 통로  X : 벽
         * */

        // 16
//        String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};

        //-1
//      String[] maps = {"LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"};

//        String[] maps = {"OOOXX","XXOXE","XSXOX","XOOOX"};

        String[] maps = {"OOSOX","XXEOO","OOOXX","XLOXX","XOOOO"};
        solution(maps);

    }

    private static int width = 0;
    private static int height = 0;
    private static int min = -1;

    public static int solution(String[] maps) {
        int answer = 0;

        width = maps[0].length();
        height = maps.length;

        String[][] newB = new String[height][width];
        String[][] count = new String[height][width];

        int[] start = new int[2];
        int[] startL = new int[2];

        int i = 0;
        for(String map : maps) {
            String[] item = map.split("");
            for(int j = 0; j < width; j++) {
                newB[i][j] = item[j];
                count[i][j] = item[j];

                if("S".equals(item[j])) {
                    start = new int[] {i,j};
                } else if("L".equals(item[j])) {
                	startL = new int[] {i,j};
                }
            }
            i++;
        }
        boolean[][] visited = new boolean[height][width];
        BFS(newB, start, visited, count, "L", 0);
        if(min == -1) return min;
        System.out.println(min + "   L START");
        answer = min;
        min = -1;
        visited = new boolean[height][width];
        BFS(newB, startL, visited, count, "E", answer);
        System.out.println("정답 === > " + min);
        return answer;
    }

    public static void print(String[][] newB) {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                System.out.print(newB[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void BFS(String[][] maps, int[] start, boolean[][] visited, String[][] count,String turn, int num) {
        int[] dx = {-1,0,1,0};
        int[] dy = {0,-1,0,1};

        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[] {start[0], start[1]});
        visited[start[0]][start[1]] = true;
        count[start[0]][start[1]] = num + "";

        while(!queue.isEmpty()) {
        	int[] pos = queue.poll();
        	for(int i = 0; i < 4; i++) {
        		print(count);
        		int nx = pos[0] + dx[i];
        		int ny = pos[1] + dy[i];

        		if(nx > -1 && nx < height && ny > -1 && ny < width) {
	        		if(turn.equals(maps[nx][ny])) {
	        			count[nx][ny] = (Integer.parseInt(count[pos[0]][pos[1]]) + 1) + "";
						if(min == -1){
							min = Integer.parseInt(count[nx][ny]);
						}

						if(min > Integer.parseInt(count[nx][ny])){
							min = Integer.parseInt(count[nx][ny]);
						}
						count[nx][ny] = (Integer.parseInt(count[pos[0]][pos[1]]) + 1) + "";
						visited[nx][ny] = true;
						queue.clear();
					}

        			if(!visited[nx][ny] && !"X".equals(maps[nx][ny])) {
        				visited[nx][ny] = true;
        				count[nx][ny] = (Integer.parseInt(count[pos[0]][pos[1]]) + 1) + "";
        				queue.add(new int[] {nx,ny});
        			}
        		}
        	}
        }
    }
}