package practice.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	public static void main(String[] args) {
		int[] nums = {-1,0,1,2,-1,-4};
		int j = 0;
		int idx = 0;
		int result;
		List<List<Object>> test = new ArrayList<>();
		
		for(int i = 0; i < nums.length-2; i++) {			
			j = i + 1;
			for (;j<nums.length-1;j++) {
				result = nums[i];
				result += nums[j] + nums[j+1];
				System.out.println(result);
				if(result == 0) {
					System.err.println("i : " + nums[i] + "/ j : "+ nums[j] + "/ j+1 : " + nums[j+1]);
					System.out.println(Arrays.asList(nums[i],nums[j],nums[j+1]));
					test.forEach(x -> System.err.println(x + "???"));
					if(test.size() > 1 && test.contains(Arrays.asList(nums[i],nums[j],nums[j+1]))) {
						test.remove(test.get(--idx));
					} else {
						test.add(Arrays.asList(nums[i],nums[j],nums[j+1]));
						idx++;
					}
				}
			}
		}
		System.err.println(test);
		
	}

}
