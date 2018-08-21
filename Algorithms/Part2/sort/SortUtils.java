package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class SortUtils {
	
	//冒泡排序
	public static void BubbleSort(Comparable[] a) {
		boolean didSwap;    //保证最好复杂度为O（n），若为正序则直接返回结果
		for (int i = 0; i < a.length-1; i++) {
			didSwap = false;
			for (int j = 1; j < a.length-i; j++) {
				if(less(a[j],a[j-1])) {
					exch(a,j,j-1);
					didSwap = true;
				}
			}
			if(didSwap== false)
				return;
		}
	}
	
	//选择排序
	public static void Selectionsort(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			int min = i;	//最小元素的索引
			for (int j = i+1; j < a.length; j++) {
				if(less(a[j], a[min])) min = j;
				exch(a, i, min);
			}
		}
		
	}
	
	//插入排序
	public static void InsertionSort(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j >0 && less(a[j], a[j-1]); j--) {
				exch(a, j, j-1);
			}
		}
		
	}
	
	//希尔排序
	public static void ShellSort(Comparable[] a) {
		int N = a.length;
		int h = 1;
		while(h<N/3) h = 3*h+1;	//构建一个递增数列h，每排序一次得到一个h有序数组,称为初始增量
		while( h >= 1) {//将数组变为h有序数组
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && less(a[j], a[j-h]); j-=h) {
					exch(a, j, j-h);
				}
			}
			h = h/3;		//递减增量
		}
		
	}
	
	//归并排序
	private static Comparable[] aux;	//归并排序辅助空间

	//归并操作   //将a[lo,mid]和a[mid+1,hi]归并
	public static void merge( Comparable[] a, int lo, int mid,int hi) {
		
		int i = lo,j = mid+1;
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k]; 
		}
		
		for (int k = lo; k <= hi; k++) {
			if	    (i > mid) 				a[k] = aux[j++];
			else if (j > hi) 				a[k] = aux[i++];
			else if (less(aux[j],aux[i])) 	a[k] = aux[j++];
			else  						 	a[k] = aux[i++];
		}
	}
	
	public static void MergeSort(Comparable[] a) {
		aux = new Comparable[a.length];//一次性分配所需要的额外空间
		MergeSort(a,0,a.length-1);
	}
	
	//通过递归的归并排序程序
	private static void MergeSort(Comparable[] a, int lo, int hi) {
		if(hi <= lo) return;
		int mid = lo + (hi-lo)/2;
		MergeSort(a,lo,mid);		//将左半边排序
		MergeSort(a, mid+1, hi);	//将右半边排序
		merge(a, lo, mid, hi);		//将结果归并
	}
	
	//非递归形式的归并排序(自底向上，比较适合用链表组织的数据结构)
	public static void MergeSort1(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];
		for (int sz = 1; sz < N; sz=sz+sz) {
			for (int lo = 0; lo < N-sz; lo += sz+sz) {
				merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
			}
		}
	}
	
	
	//快速排序
	//快速排序的切分
	private static int partition(Comparable[] a, int lo ,int hi) {
		int i = lo ,j = hi+1;	//定义一个左右扫描指针
		Comparable v = a[lo];	//先找到一个切分的元素
		while(true) {	//扫描左右，检查扫描是否结束并交换元素
			while (less(a[++i], v)) 
				if(i == hi) break;
			
			while(less(v, a[--j]))
				if(j == lo) break;
			if(i >= j) break;
			exch(a, i, j);
		}
		
		exch(a, lo, j);	//将v=a[j]放入正确位置
		return j;		//完成切分 ，即a[lo...j-1] <= a[j] <= a[j+1...hi]
	}
	
	
	private static void QuickSort(Comparable[] a) {
		//为了保证快速排序的性能，先将输入的数组进行打乱.消除对输入的依赖性
		List<Object> list = new ArrayList<>();
		for (int i = 0; i < a.length; i++) {
			list.add(a[i]);
		}
		Collections.shuffle(list);
		for (int i = 0; i < a.length; i++) {
			a[i] = (Comparable) list.get(i);
		}
		list = null;	//释放内存
		
		QuickSort(a,0,a.length-1);
	}
	//递归实现快排
	private static void QuickSort(Comparable[] a, int lo, int hi) {
		if(hi <= lo ) return;
		int j = partition(a, lo, hi);//切分
		QuickSort(a, lo, j-1);		//将左半部分进行排序
		QuickSort(a, j+1, hi);		//将切分后的右半部分进行排序
	}
	
	
	//堆排序
	public static int heapSize;		//需要额外定义一个静态变量表示堆大小
	public static int parent(int i) {return (i - 1) / 2;}
	public static int left(int i) {return 2 * i + 1;}
	public static int right(int i) {return 2 * i + 2;}
	public static void maxHeapify(int i, Comparable[] A){
	    int l = left(i);
	    int r = right(i);
	    int largest = i;
	    if (l <= heapSize - 1 && less(A[i], A[l])   )
	        largest = l;
	    if (r <= heapSize - 1 && less(A[largest], A[r]) )
	        largest = r;
	    if (largest != i) {
	        exch(A, i, largest);
	        maxHeapify(largest,A);
	    }
	}
	
	public static void buildMaxHeap(Comparable [] a){
		heapSize =a.length; 
		
	     for (int i = parent(heapSize - 1); i >= 0; i--)
	         maxHeapify(i,a);        
	 }
	
	public static void heapsort(Comparable[] A){
	    buildMaxHeap(A);
	    
	    int step = 1;
	    for (int i = heapSize - 1; i > 0; i--) {
	        exch(A, i, 0);	//将顶堆元素与尾堆元素互换
	        heapSize--;		//堆的尺寸缩小1
	        System.out.println("Step: " + (step++) + Arrays.toString(A));
	        maxHeapify(0,A);	//从新的顶堆元素进行堆调整，重新调整为最大堆
	    }        
	}
	

	//对数组两个元素进行比较  v<w时返回true
	private static boolean less(Comparable v,Comparable w) {
		return v.compareTo(w)<0;
	}
	
	//交换数组元素的位置
	private static void exch(Comparable[] a,int i ,int j) {
		Comparable t = a[i]; a[i] = a[j];a[j]= t;
	}
	
	//展示数组元素以，隔开
	private static void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
	
	//测试数组元素是否有序
	private static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++) {
			if(less(a[i],a[i-1])) return false;
		}
		return true;
	}
	
	//测试sort中的排序算法
	public static void main(String[] args) {
		Integer[] a = {3, 7, 2, 11, 3, 4, 9, 2, 18, 0};
		heapsort(a);
		System.out.println("是否排序成功："+isSorted(a));

	}

}
