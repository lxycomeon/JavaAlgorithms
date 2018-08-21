package 笔试;

/*  网易笔试题目
小易觉得高数课太无聊了，决定睡觉。不过他对课上的一些内容挺感兴趣，所以希望你在老师讲到有趣的部分的时候叫醒他一下。你知道了小易对一堂课每分钟知识点的感兴趣程度，并以分数量化，以及他在这堂课上每分钟是否会睡着，你可以叫醒他一次，这会使得他在接下来的k分钟内保持清醒。你需要选择一种方案最大化小易这堂课听到的知识点分值。 
输入描述:
第一行 n, k (1 <= n, k <= 105) ，表示这堂课持续多少分钟，以及叫醒小易一次使他能够保持清醒的时间。
第二行 n 个数，a1, a2, ... , an(1 <= ai <= 104) 表示小易对每分钟知识点的感兴趣评分。
第三行 n 个数，t1, t2, ... , tn 表示每分钟小易是否清醒, 1表示清醒。

输出描述:
小易这堂课听到的知识点的最大兴趣值。
输入例子1:
6 3
1 3 5 2 5 4
1 1 0 1 0 0
输出例子1:
16
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MainTest {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int k = input.nextInt();
		Integer[] intrMark = new Integer[n];
		for (int i = 0; i < n; i++) {
			intrMark[i] = input.nextInt();
		}
		Integer[] Xing = new Integer[n];
		Integer[] Xing2 = new Integer[n];
		for (int i = 0; i < n; i++) {
			Xing[i] = input.nextInt();
			Xing2[i] = Xing[i];
		}
		int result = 0;
		for (int i = 0; i < Xing.length; i++) {
			if(Xing[i]==0) {
				for (int q = 0; q <Xing.length; q++) {
					Xing2[q] = Xing[q];
				}
				int tmpResult = 0;
				for (int j = 0; j < k; j++) {
					if(i+j<Xing.length) Xing2[i+j]=1;
				}
				
				for (int j = 0; j < Xing2.length; j++) {
					if(Xing2[j]==1)
						tmpResult += intrMark[j];
				}
				result = Math.max(result, tmpResult);
			}
		}
		System.out.println(result);
		
	}	
		
}
/*		上面的代码由于时间复杂度太高，case：50%。下面是AC的代码
public static void main(String[] args) {
	
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
    int k = in.nextInt();
    int[] point = new int[n];
    for (int i = 0; i < n; i++) {
		point[i]=in.nextInt();
	}
    int[] weakFlag= new int[n];
    for(int i=0;i<n;i++) {
    	weakFlag[i]=in.nextInt();
    }
    long base=0;
    if (n==k) {
		for(int i=0;i<n;i++) {
			base+=point[i];
					
		}
		System.out.println(base);
		return;
	}
	for(int i=0;i<n;i++) {
		if(weakFlag[i]==1) {
			base+=point[i];
		}
	}
	
	long addMax=0;
	for(int i=0;i<n-k+1;i++) {			
			int add = 0;
			for(int j=i;j<i+k;j++) {
				if(weakFlag[j]==0) {
					add+=point[j];
				}
			}
			if (add>addMax) {
				addMax=add;
			}			
	}
	System.out.println(base+addMax);
	in.close();
}

*/

