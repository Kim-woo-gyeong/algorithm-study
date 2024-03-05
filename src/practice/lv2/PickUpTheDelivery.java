package practice.lv2;

import java.util.*;

public class PickUpTheDelivery {
/*
 * 당신은 일렬로 나열된 n개의 집에 택배를 배달하려 합니다. 배달할 물건은 모두 크기가 같은 재활용 택배 상자에 담아 배달하며,
 * 배달을 다니면서 빈 재활용 택배 상자들을 수거하려 합니다. 배달할 택배들은 모두 재활용 택배 상자에 담겨서 물류창고에
 * 보관되어 있고, i번째 집은 물류창고에서 거리 i만큼 떨어져 있습니다. 또한 i번째 집은 j번째 집과 거리 j - i만큼
 * 떨어져 있습니다. (1 ≤ i ≤ j ≤ n)
 * 트럭에는 재활용 택배 상자를 최대 cap개 실을 수 있습니다. 트럭은 배달할 재활용 택배 상자들을 실어 물류창고에서 출발해
 * 각 집에 배달하면서, 빈 재활용 택배 상자들을 수거해 물류창고에 내립니다.
 * 각 집마다 배달할 재활용 택배 상자의 개수와 수거할 빈 재활용 택배 상자의 개수를 알고 있을 때,
 * 트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리를 구하려 합니다. 각 집에 배달 및 수거할 때,
 * 원하는 개수만큼 택배를 배달 및 수거할 수 있습니다.
 * 다음은 cap=4 일 때, 최소 거리로 이동하면서 5개의 집에 배달 및 수거하는 과정을 나타낸 예시입니다.
 *
 * */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 16
//		int cap = 4; int n = 5; int[] deliveries = {1,0,3,1,2}; int[] pickups = {0,3,0,4,0};

		int cap = 2; int n = 7; int[] deliveries = {1, 0, 4, 0, 1, 0, 4}; int[] pickups = {0, 2, 0, 1, 0, 2, 0};
		solution(cap, n, deliveries, pickups);

	}

	private static long min = 0;
	public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = -1;

        int[] house = new int[n];
        for(int i = 0; i < n; i++) {
        	house[i] = i;
        }
        println(deliveries);
		System.out.println();
		println(pickups);
        Greedy(cap, n, 0, house, pickups, deliveries);

        System.out.println(min);

        return answer;
    }

	public static void Greedy(int cap, int n, int depth, int[] house, int[] pickups, int[] deliveries) {
		System.out.println();
		System.out.println();
		Queue<Integer> bowl = new LinkedList<Integer>();
		for(int i = 0; i < cap; i++) {
			bowl.add(1);
		}

		int start = -1;

		int d = 0; int p = 0;
		for(int i = n-1; i > -1; i--) {
			int count = 0;

			d -= deliveries[i];
			p -= pickups[i];

			while(d < 0 || p < 0) {
				d += cap;
				p += cap;

				deliveries[i] = Math.max(deliveries[i] - cap, 0);
				pickups[i] = Math.max(pickups[i] - cap, 0);

				count++;
				println(deliveries);
				System.out.println();
				println(pickups);
				System.out.println(" >>>> " + count);
			}
			min += (i + 1) * 2 * count;
			System.out.println(min);
		}

//		if(start == -1) return;
//		min+=(start+1)*2;
//		println(deliveries);
//		System.out.println(" >>>> " + start);
//		println(pickups);
//
//		Greedy(cap, n, depth, house, pickups, deliveries);
	}

	public static void println(int[] target) {

		for(int item : target) {
			System.out.print(item + "\t");
		}
	}

}
