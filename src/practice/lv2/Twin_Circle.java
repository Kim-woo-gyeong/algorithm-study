package practice.lv2;

import java.util.*;

public class Twin_Circle {
/*
 * x축과 y축으로 이루어진 2차원 직교 좌표계에 중심이 원점인 서로 다른 크기의 원이 두 개 주어집니다.
 * 반지름을 나타내는 두 정수 r1, r2가 매개변수로 주어질 때, 두 원 사이의 공간에 x좌표와 y좌표가 모두 정수인 점의 개수를 return하도록
 * solution 함수를 완성해주세요.
 * ※ 각 원 위의 점도 포함하여 셉니다.
 * */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// result = 20
//		int r1 = 2; int r2 = 3;

		// result = 40
//		int r1 = 2; int r2 = 4;

		// result = 952
		int r1 = 10; int r2 = 20;
		solution(r1, r2);
	}

	public static long solution(int r1, int r2) {
        long answer = 0;

        for(long x = 1; x < r2; x++) {
        	double aa = Math.floor(Math.sqrt(Math.pow(r2, 2) - Math.pow(x, 2)));
        	double bb = Math.ceil(Math.sqrt(Math.max(Math.pow(r1, 2) - Math.pow(x,2), 1)));
        	System.out.println("[x = " + x +"] " + aa + " ~ " + bb + "\t갯수 = " + ((long) aa - (long) bb + 1));
        	answer += (long) aa - (long) bb + 1;
        }

//        long[] max = {r2-1, r2-1};
//
//        Set<String> visited = new HashSet<>();
//
//        int[] dx = {-1,0,1,0}; int[] dy = {0,-1,0,1};
//
//        Queue<long[]> queue = new LinkedList<long[]>();
//        queue.offer(max);
//
//        if(Math.sqrt(Math.pow(max[0], 2) + Math.pow(max[1], 2)) <= r2
//				&& Math.sqrt(Math.pow(max[0], 2) + Math.pow(max[1], 2)) >= r1) {
//        	visited.add(max[0]+""+max[1]);
//        	answer++;
//        }
//
//        while(!queue.isEmpty()) {
//        	long[] pos = queue.poll();
//
//        	for(int i = 0; i < 4; i++) {
//        		long nx = pos[0] + dx[i];
//        		long ny = pos[1] + dy[i];
//
//        		if(!visited.contains(nx+""+ny) && nx >= -r2 && ny >= -r2 && nx <= r2 && ny <= r2) {
//        			queue.offer(new long[] {nx, ny});
//        			visited.add(nx+""+ny);
//
//        			if(Math.sqrt(Math.pow(nx, 2) + Math.pow(ny, 2)) <= r2
//        				&& Math.sqrt(Math.pow(nx, 2) + Math.pow(ny, 2)) >= r1) {
//        				System.out.println("["+nx+","+ny+"]");
//            			answer++;
//            		}
//        		}
//        	}
//        }
        System.out.println(answer);
        System.out.println(answer*4 + (r2-r1+1)*4);
        return answer;
    }

}
