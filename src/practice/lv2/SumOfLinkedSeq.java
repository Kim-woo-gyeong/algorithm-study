package practice.lv2;

import java.util.HashSet;

public class SumOfLinkedSeq {
/*
 * 철호는 수열을 가지고 놀기 좋아합니다. 어느 날 철호는 어떤 자연수로 이루어진 원형 수열의 연속하는 부분 수열의 합으로 만들 수 있는 수가 모두 몇 가지인지 알아보고 싶어졌습니다. 원형 수열이란 일반적인 수열에서 처음과 끝이 연결된 형태의 수열을 말합니다. 예를 들어 수열 [7, 9, 1, 1, 4] 로 원형 수열을 만들면 다음과 같습니다.
 * 원형 수열은 처음과 끝이 연결되어 끊기는 부분이 없기 때문에 연속하는 부분 수열도 일반적인 수열보다 많아집니다.
원형 수열의 모든 원소 elements가 순서대로 주어질 때, 원형 수열의 연속 부분 수열 합으로 만들 수 있는 수의 개수를 return 하도록 solution 함수를 완성해주세요.
 * */
	public static void main(String[] args) {
		int[] elements = {7,9,1,1,4};
		solution(elements);
	}

	public static int solution(int[] elements) {
        int answer = 0;

        int length = elements.length;

        HashSet<Integer> set = new HashSet<>();
        for(int i = 1; i < length+1; i++) {
        	int idx = 0;
        	while(true) {
        		int sum = 0;
        		for(int j = 0; j < i; j++) {
        			idx = idx % length;
        			sum += elements[idx];
        			idx++;
        		}

        		set.add(sum);

        		if(idx % length == 0) {
        			break;
        		}
        	}
        	System.out.println(set);
        }
        
        answer = set.size();
        System.out.println(answer);
        return answer;
    }

}
