package practice.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
	/*
	 * 15. ThreeSum
	 * https://leetcode.com/problems/3sum/
	 * hint : 정렬 중요 & 합계 < 0 인지 합계 > 0 인지 알아채는게 중요함.
	 * */
	public static void main(String[] args) {
		int[] nums = {14,4,6,-1,10,9,-8,7,-13,14,-13,-11,-8,-9,11,14,-8,-14,-13,7,-10,-15,-13,-11,-11,11,14,13,2,-14,1,-7,-2,14,-1,-15,9,7,-1,3,6,1,7,5,-1,-5,4,-2,-4,-1,-9,-7,-1,-7,-11,3,12,10,-7,-1,12,1,8,-13,1,14,9,-13,6,-7,-3,-11,2,-11,10,-14,-1,-9,0,2,5,6,3,-11,6,7,0,3,3,0,-12,-8,-13,3,-14,-5,2,10,-11,-14,-12,1,-10,5,5,7,-1,11,14,6,-10,-4,-3,8,-7,10,1,8,-1,-11,-15,-6,-12,-13,12,-11};
		int j = 0;
		int z = 0;
		int result;
		
		List<List<Integer>> test = new ArrayList<>();
//	    if(nums.length < 3) return test;
	    Arrays.sort(nums);
	    
		for(int i = 0; i < nums.length-2; i++) {
			
			if(i > 0 && nums[i] == nums[i-1]) continue;
	        int ask = -nums[i];
	        int start = i + 1;
	        int end = nums.length -1;
	        System.err.println("i : " + i + " start : " + start + " end : " + end);
	        while(start < end) {
	        	System.err.println("i : " + i + " start : " + start + " end : " + end);
	            if(nums[start] + nums[end] == ask) {
	            	System.out.println("add : " + test);
	            	test.add(Arrays.asList(nums[i], nums[start], nums[end]));
	                while(start < end && nums[start] == nums[++start]);
	            }
	            if(nums[start] + nums[end] < ask) {
	                start ++;
	            }
	            if(nums[start] + nums[end] > ask) {
	                end--;
	            }
	        }
//			z = i + 1;
//			j = nums.length - 1;
//			while(z < nums.length-1) {
//				System.err.println("i : " + i + " z : " + z + " j : " + j);
//				result = nums[i] + nums[z] + nums[j];
////				test.forEach(arr -> arr.sort(null));
////				List<Object> temp = new ArrayList<Object>();
////				temp = Arrays.asList(nums[i],nums[z],nums[j]);
////			 	temp.sort(null);
//			 	if(result == 0) {
//					if(test.size() > 0 && test.contains(Arrays.asList(nums[i],nums[z],nums[j]))) {
//	//						test.remove(test.get(--idx));
//	//						test.remove(temp);
//						System.out.println("remove : " + test);
//					} else {
//						test.add(Arrays.asList(nums[i],nums[z],nums[j]));
//						System.out.println("add : " + test);
//					}
//			 	}
//				j--;
//				if(j > nums.length-1) {
//					z++;
//					j = z+1;
//				}
//			}
//			for (;z<nums.length-1;z++) {
//				for(j=z+1;j<nums.length;j++) {
//					result = nums[i] + nums[z] + nums[j];
//					System.err.println("i : " + nums[i] + "/ j : "+ nums[z] + "/ j+1 : " + nums[j]);
//					System.out.println(result);
//					if(result == 0) {
//						
//						test.forEach(arr -> arr.sort(null));
//						List<Object> temp = new ArrayList<Object>();
//						temp = Arrays.asList(nums[i],nums[z],nums[j]);
//					 	temp.sort(null);
//					 	System.out.println("size : " + test.size() + "temp : " + temp + " test : " + test + " result ? " + test.contains(temp));
//						if(test.size() > 0 && test.contains(temp)) {
//	//						test.remove(test.get(--idx));
//	//						test.remove(temp);
//							System.out.println("remove : " + test);
//						} else {
//							test.add(temp);
//							System.out.println("add : " + test);
//						}
//					}
//				}
//			}
		}
		System.out.println("final >>> " + test);
		
	}

}
