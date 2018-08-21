package 笔试;

/*
 * 
 * 
 * 百度之星，初赛第一题
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		while(input.hasNext()) {
			int n = input.nextInt();
			Integer[] a = new Integer[n];
			for (int i = 0; i < n; i++) {
				a[i] = input.nextInt();
			}
			int max = 0;
			// 让i<j<k 这样棍子就不会重复选中了 我一开始没有想到 以后要记住
			Arrays.sort(a,Collections.reverseOrder());
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					for (int k = j + 1; k < n; k++) {
						// 三条边的总和
						int allLen = a[i] + a[j] + a[k];
						// 求三个数的最大值的时候 可以先求任意两个的最大值 然后再把这个最大值和第三个数进行比较得到最大值
						int maxLength = maxValue(a[i], maxValue(a[j], a[k]));
						// 剩下的两边之和 可以有总长度减去那个最大值
						int rest = allLen - maxLength;
						if (rest > maxLength) {
							// 如何可以组成三角形 然后将答案更新
							max = maxValue(max, allLen);
							i = n;
							j = n;
							k = n;
						}
					}
				}
			}
			if (max == 0) {
				System.out.println(-1);
			} else {
				System.out.println(max);
			}

		}
		}
		
	// 求2个数字里面最大的数字
	public static int maxValue(int a, int b) {
		int max = 0;
		if (a > b) {
			max = a;
		} else {
			max = b;
		}
		return max;
	}
}
