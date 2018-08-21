package 笔试;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Test3 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		while(n>0) {
			n--;
			int N = input.nextInt();
			double[] x = new double[N];
			for (int i = 0; i < N; i++) {
				x[i]=input.nextDouble();
			}
			System.out.println((int)x[findMin(findStable(x))]);
		}
	}	
	public static double[] findStable(double[] nums) {
		int max = findMax(nums);
		int min = findMin(nums);
		int count =0;
		if(nums[max]-nums[min]>3) {
			count = (int)(nums[max]-nums[min])/3;
			nums[max] -= count*2;
			nums[min] += count;
		}
		if(nums[max]-nums[min]<=1)
			return nums;
		if(nums[min]<0)
			return null;
		nums[max] -=2;
		nums[min] ++;
		findStable(nums);
		return nums;
	}
	
	
	public static int findMax(double[] nums) {
		double maxIndex=nums[0];
		int maxSign=0;
		for (int i = 0; i < nums.length; i++) {
            if(maxIndex < nums[i]){
                maxIndex = nums[i];
                maxSign = i;
            }
           
        }
		return maxSign;
		
	}
	public static int findMin(double[] nums) {
		double minIndex = nums[0];
		int minSign = 0;
		for (int i = 0; i < nums.length; i++) {
			 if(minIndex > nums[i]){
	             minIndex = nums[i];
	             minSign = i;
	         }
        }
		return minSign;
	}
		
}
