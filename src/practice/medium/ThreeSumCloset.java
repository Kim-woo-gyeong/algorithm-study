package practice.medium;

import java.util.Arrays;

public class ThreeSumCloset {
	
	/*
	 * 15. ThreeSum Coloset
	 * https://leetcode.com/problems/3sum-closest/
	 * 
	 * */
	public static void main(String[] args) {
		int [] nums = {1,2,5,10,11};
		int target = 12;
		int start = 0;
		int end = 0;
		int min = nums[0] + nums[1] + nums[2]; int max = 0;

		Arrays.sort(nums);
		
		for(int i = 0; i < nums.length - 2; i++) {
			start = i+1;
			end = nums.length - 1;
			while(start < end) {
				System.out.println("min : " + min + " max : " + max);
				if(Math.abs(target-min) >= Math.abs(target - (nums[i] + nums[start] + nums[end]))) {
					min = nums[i] + nums[start] + nums[end];
				}
				if(nums[i] + nums[start] + nums[end] == target) {
					min = nums[i] + nums[start] + nums[end];
					break;
				}else if(nums[i] + nums[start] + nums[end] < target) {
//					max = nums[i] + nums[start] + nums[end];
					start++;
				} else if (nums[i] + nums[start] + nums[end] > target) {
//					max = nums[i] + nums[start] + nums[end];
					end--;
				}
			}
			if(min == target) {
				break;
			}
		}
		
		System.err.println("result : " + min);
	}

}
