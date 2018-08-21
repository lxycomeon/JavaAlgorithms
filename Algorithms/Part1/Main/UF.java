package Main;

import java.util.Scanner;
//1.5节案列研究代码，动态连通性，unionFind
public class UF {
	private int[] id;
	private int count;
	
	public UF(int N) {
		count = N;
		id = new int[N];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
		}
	}
	
	public int count() {
		return count;
	}
	
	public boolean connected(int p,int q) {
		return find(p) == find(q);
	}
	//quick-find算法
	private int find(int p) {
		return id[p];
	}
	
	public void union(int p,int q) {
		int pID = find(p);
		int qID = find(q);
		
		if(pID == qID) return ;
		for (int i = 0; i < id.length; i++) {
			if(id[i] == pID) id[i] = qID;
		}
		count--;
	}
	
	//quick-union算法
	private int find1(int p) {	//找出分量的名称
		while(p!= id[p]) p = id[p];
		return p;
	}
	private void union1(int p,int q) {	//将p和q的根节点连接，统一起来
		int pRoot = find(p);
		int qRoot = find(q);
		if(pRoot == qRoot) return;		//不用每次都遍历一遍，相对于union（）
		
		id[pRoot] = qRoot;
		
		count--;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		UF uf = new UF(N);
		
		while(sc.hasNext()) {
			int p = sc.nextInt();
			int q = sc.nextInt();
			if(uf.connected(p, q)) continue;
			uf.union(p, q);
			System.out.println(p + " "+q);
		}
		
		
		System.out.println(uf.count() + "components");
		
	}
}
