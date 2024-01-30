package practice.lv2;

import java.util.*;

public class ProjectProgress {
/*
 * 과제를 받은 루는 다음과 같은 순서대로 과제를 하려고 계획을 세웠습니다.
 *
 * 과제는 시작하기로 한 시각이 되면 시작합니다.
 * 새로운 과제를 시작할 시각이 되었을 때, 기존에 진행 중이던 과제가 있다면 진행 중이던 과제를 멈추고 새로운 과제를 시작합니다.
 * 진행중이던 과제를 끝냈을 때, 잠시 멈춘 과제가 있다면, 멈춰둔 과제를 이어서 진행합니다.
 * 만약, 과제를 끝낸 시각에 새로 시작해야 되는 과제와 잠시 멈춰둔 과제가 모두 있다면, 새로 시작해야 하는 과제부터 진행합니다.
 * 멈춰둔 과제가 여러 개일 경우, 가장 최근에 멈춘 과제부터 시작합니다.
 * 과제 계획을 담은 이차원 문자열 배열 plans가 매개변수로 주어질 때,
 * 과제를 끝낸 순서대로 이름을 배열에 담아 return 하는 solution 함수를 완성해주세요.
 * */
	private static int width = 0;
	private static int height = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * [["korean", "11:40", "30"],
		 *  ["english", "12:10", "20"],
		 *  ["math", "12:30", "40"]]
		 *
		 *  ["korean", "english", "math"]
		 * */
//		String[][] plans = {{"english", "12:10", "20"},{"korean", "11:40", "30"}, {"math", "12:30", "40"}};

//		String[][] plans = {{"AA", "11:30", "30"},{"CC", "12:50", "20"}, {"BB", "11:40", "20"}, {"DD", "12:55", "10"}};
//		String[][] plans = {{"a", "09:00", "10"},{"e", "09:35", "10"},{"b", "09:10", "10"},{"c", "09:15", "10"},{"d", "09:30", "10"}};
		String[][] plans = {{"a", "09:00", "30"},{"b", "09:20", "10"},{"c", "09:40", "10"}};
		solution(plans);
	}

	public static String[] solution(String[][] plans) {
        String[] answer = {};
        width = plans[0].length;
        height = plans.length;

        Arrays.sort(plans, (o1,o2) -> {
        	return o1[1].compareTo(o2[1]);	// 오름차순
        });

        Stack<Integer> stack = new Stack<>();
        Queue<String> queue = new LinkedList<>();
        stack.add(0);
        int idx = 0;
        int curTime = Integer.parseInt(plans[0][1].split(":")[0]) * 60 + Integer.parseInt(plans[0][1].split(":")[1]);
        System.out.println("curTime = " + curTime);
        while(!stack.isEmpty()) {
        	int nx = idx + 1;
        	if(nx < height) {

        		String[] time = plans[nx][1].split(":");
        		int tt = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
        		if(curTime + Integer.parseInt(plans[idx][2]) <= tt) {
        			int pos = stack.pop();
        			queue.add(plans[pos][0]);
        			curTime += Integer.parseInt(plans[idx][2]);
        			plans[idx][2] = "0";
        		}else {
        			plans[idx][2] = ( Integer.parseInt(plans[idx][2]) - (tt - curTime) ) + "";
        			curTime += (tt - curTime);
        		}
        		for(int i = 0; i < height; i++) {
	            	for(int j = 0; j < width; j++) {
	            		System.out.print(plans[i][j] + "\t");
	            	}
	            	System.out.println();
	            }
        		System.out.println("curTime = " + curTime);
        		curTime = bfs(stack, queue, curTime, tt, plans);
        		stack.add(nx);
        		System.out.println(stack);
        	} else {
        		while(!stack.isEmpty()) {
        			int pos = stack.pop();
        			queue.add(plans[pos][0]);
        		}
        	}
        	idx++;
        }
        System.out.println(queue);
        return answer;
    }

	public static int bfs(Stack<Integer> stack, Queue<String> queue, int curTime, int targetTime, String[][] plans) {
		while(!stack.isEmpty()) {
			int idx = stack.peek();
			if(curTime + Integer.parseInt(plans[idx][2]) <= targetTime) {
				stack.pop();
				queue.add(plans[idx][0]);
				curTime += Integer.parseInt(plans[idx][2]);
				System.out.println("curTime = " + curTime);
				continue;
			}
			plans[idx][2] = (Integer.parseInt(plans[idx][2]) - (targetTime - curTime)) + "";
			break;
		}

		return targetTime;
	}

}
