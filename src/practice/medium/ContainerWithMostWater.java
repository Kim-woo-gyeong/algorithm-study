package practice.medium;

public class ContainerWithMostWater {

	/*
	 * 11. Container With Most Water
	 * https://leetcode.com/problems/container-with-most-water/
	 * 
	 * */
	public static void main(String[] args) {
		int[] height = {2,3,4,5,18,17,6};
		
		int j = 0;
		int num = 0;
		int max = 0;
		int min = 0;
		
		for(int i = 0; i < height.length; i++) {
			System.err.println(height[j] + " >> " + height[height.length - 1 - num]);
			if(height[j] < height[height.length - 1 - num]) {
				max = height[j] * (height.length - num - 1 - j);
				j++;
			} else {
				max = height[height.length - 1 - num] * (height.length - num - 1 - j);
				num++;
			}
			if(max >= min)
				min = max;
		}
		
		System.err.println(" ?? " + min);
	}

}
