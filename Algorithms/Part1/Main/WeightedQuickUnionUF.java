package Main;

//加权quick-union算法，新建了一个数组sz记录每一棵树的大小，每次总是将较小的树连接到较大的树上。

public class WeightedQuickUnionUF {
	private int[] id;
	private int[] sz;
	private int count;
	public WeightedQuickUnionUF(int N) {
		this.count = N;
		id = new int[N];
		for (int i = 0; i < id.length; i++) {
			id[i]=i;
		}
		sz = new int[N];
		for (int i = 0; i < id.length; i++) {
			sz[i] = 1;
		}
	}
	public int count() {
		return count;
	}
	public boolean connected(int p, int q) {
		return find(p)==find(q);
	}
	private int find(int p) {
		while(p!=id[p]) p = id[p];
		return p;
	}
	public void nuion(int p,int q) {
		int i = find(p);
		int j = find(q);
		if(i==j) return;
		if(sz[i]<sz[j]) {
			id[i]=j;sz[j]+=sz[i];
		}else {
			id[i] = i; sz[i] += sz[j];
		}
		count--;
	}
	
	
}
