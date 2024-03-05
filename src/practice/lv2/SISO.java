package practice.lv2;

import java.util.*;

public class SISO {
/*
 * 어느 공원 놀이터에는 시소가 하나 설치되어 있습니다. 이 시소는 중심으로부터 2(m), 3(m), 4(m) 거리의 지점에 좌석이 하나씩 있습니다.
 * 이 시소를 두 명이 마주 보고 탄다고 할 때, 시소가 평형인 상태에서 각각에 의해 시소에 걸리는 토크의 크기가 서로 상쇄되어 완전한 균형을 이룰 수 있다면
 * 그 두 사람을 시소 짝꿍이라고 합니다. 즉, 탑승한 사람의 무게와 시소 축과 좌석 간의 거리의 곱이 양쪽 다 같다면 시소 짝꿍이라고 할 수 있습니다.
 * 사람들의 몸무게 목록 weights이 주어질 때, 시소 짝꿍이 몇 쌍 존재하는지 구하여 return 하도록 solution 함수를 완성해주세요.
 * */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 4
		//[100 100][180 180][180 360][180 360][180 270][180 270][270 360]
//		int[] weights = {100,180,180,360,100,270};

//		int[] weights = {100, 100, 100, 150, 150};
		int[] weights = {100,120,150,180,270,300,360,540,100, 150, 170, 62, 100,360};
		// 62 100 100 100 120 150 150 170 180 270 300 360 360 540
		/*
		 * [100 100][100 100][100 150][100 150]
		 * [100 100][100 150][100 150]
		 * [100 150][100 150]
		 * [150 150]
		 * */

		// [200 200][200 200][200 200][240 240]
//		int[] weights = {200,200,200,240,240};
		// 2 1
		// 3 3  2
		// 4 6  3
		// 5 10 4

		//[100 100] [100 150] [100 150] [100 150] [100 150] [150 150]
//		int[] weights = {62, 100, 100, 150, 150};

//		int[] weights = {100, 400};

//		int[] weights = {62, 100, 100, 150, 150};
		solution(weights);

	}

	public static long solution(int[] weights) {
        long answer = 0;
        long col = weights.length;

        Arrays.sort(weights);
        // 100 100 180 270 360

        LinkedHashMap<Long, List<Long>> map = new LinkedHashMap<Long, List<Long>>();
        HashMap<Long,Long> count = new HashMap<Long,Long>();
        for(int i = 0; i < col; i++) {
        	if(map.get((long) weights[i]) == null) {
        		map.put((long) weights[i], new ArrayList<Long>());
        		count.put((long) weights[i], 1L);
        		continue;
        	}
//        	Set<Long> temp = new HashSet<Long>();
//        	temp.addAll(map.get((long) weights[i]));
//        	temp.add((long) weights[i]);
//
//        	map.put((long) weights[i],temp);
        	count.put((long) weights[i], count.get((long) weights[i]) + 1);
        }

        System.out.println(map);
        System.out.println(count);

        // 62 100 100 150 150 [100 100] [100 150] [100 150] [100 150] [100 150] [150 150]
        /*
         * for(int i = 0; i < col; i++){
         * 	// 100
         * 	for(int j = i+1; j < col; j++){
         * 		// 100 150 150
         * 		if(weights[i] * 3 == weights[i] * 2)
         * 		else if(weights[i] * 4 == weights[i] * 2)
         * 		else if(weights[i] * 4 == weights[i] * 3)
         * 	}
         * }
         * */
        for(Long num1 : map.keySet()) {
//        	long num1 = weights[i];

        	if((long) (num1*3.0)%2 == 0 && map.get((long) (num1*3.0)/2) != null) {
        		map.get(num1).add((long) (num1*3)/2);
        		answer+=count.get(num1) * count.get((long) (num1*3.0)/2);
        	}

        	if((long) (num1*4.0)%2 == 0 && map.get((long) (num1*4.0)/2) != null) {
        		map.get(num1).add((long) (num1*4)/2);
        		answer+=count.get(num1) * count.get((long) (num1*4.0)/2);
        	}

        	if((long) (num1*4.0)%3 == 0 && map.get((long) (num1*4)/3) != null) {
        		map.get(num1).add((long) (num1*4)/3);
        		answer+=count.get(num1) * count.get((long) (num1*4)/3);
        	}
        }
        System.out.println(map);

//        for(Long key : map.keySet()) {
//        	answer += map.get(key).size()*count.get(key);
//        }

        for(int i = 0; i < col; i++) {
        	long num1 = weights[i];
        	if(count.get(num1) > 1L) {
        		count.put(num1, count.get(num1)-1);
        		answer += count.get(num1);
        	}
        }
        System.out.println(answer);
        return answer;
    }

}
