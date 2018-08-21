package sort;


//基于堆的优先队列（优先队列是0个或者多个元素的集合，每个元素都有一个优先权或者值，），
//应用：比如删除十亿个数据中的最大的前十个数据。如果要先排序再找到前十个，无论是时间复杂度还是空间复杂度都是一个巨大的开销。使用优先队列，只需要建立一个长度为10的最大堆
public class MaxPQ<Date extends Comparable<Date>> {

	private Date[] pq;
	private int N = 0;
	public MaxPQ(int maxN) {
		pq = (Date[]) new Comparable[maxN+1];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void insert(Date d) {
		pq[++N] = d;
		swim(N);
	}
	

	public Date delMax() {
		Date max = pq[1];
		exch(1,N--);
		pq[N+1] = null;
		sink(1);
		return max;
	}
	
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	private void swim(int k) {
		while(k >1 && less(k/2,k)) {
			exch(k/2, k);
			k = k/2;
		}
	}
	

	private void exch(int i, int j) {
		Date t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}

	private void sink(int k) {
		while(2*k <= N) {
			int j = 2*k;
			if(j<N && less(j, j+1)) j++;
			if(!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
