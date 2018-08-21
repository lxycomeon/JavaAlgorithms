package 笔试;
/*
 * 度度熊最近似乎在研究图论。给定一个有 NN 个点 (vertex) 以及 MM 条边 (edge) 的无向简单图 (undirected simple graph)，此图中保证没有任何圈 (cycle) 存在。
现在你可以对此图依序进行以下的操作：移除至多 KK 条边。在保持此图是没有圈的无向简单图的条件下，自由的添加边至此图中。
请问最后此图中度数 (degree) 最大的点的度数可以多大呢？

Input
输入的第一行有一个正整数 TT，代表接下来有几笔测试资料。

对于每笔测试资料： 第一行有三个整数 NN, MM, KK。 接下来的 MM 行每行有两个整数 aa 及 bb，代表点 aa 及 bb 之间有一条边。 点的编号由 00 开始至 N - 1N−1。
Output
对于每一笔测试资料，请依序各自在一行内输出一个整数，代表按照规定操作后可能出现的最大度数。

Sample Input
2
3 1 1
1 2
8 6 0
1 2
3 1
5 6
4 1
6 4
7 0
Sample Output
2
4
 * 
 */



/*
 * 1001 degree 題解
	沒有圈 (cycle) 的簡單圖 (undirected simple graph)，等價於由多顆樹 (tree) 組成的森林 (forest)。這裡用 VV 代表點的數量，EE 代表邊的數量 (取代題目中的 NN 以及 MM)，CC 代表森林中樹的數量。
	
	K = 0K=0 的 case
	不妨先化簡一下題目，在 K = 0K=0 的狀態下要達到 degree 最大化，可以貪心的把森林中所有樹各自接一條邊到已知 degree 最大的點上。答案是 C + 已知最大的 degree - 1。
	
	K \ge 0K≥0 的 case
	回到原題，題目中規定一定要在添加邊之前把拔邊的操作作完，但是實際上任意調換添加邊以及拔掉邊的順序不會影響最後的結果。 考慮貪心添加完邊的樹，可以多拔掉一條邊再重新接上的效果等價於把答案 +1+1，要注意的是如果答案已經到達最大值 V-1V−1 的話，那拔邊再接上並不會影響答案。 所以答案為min(V - 1, K + C + 已知最大的 degree - 1)。
	
	樹的數量 C = V - E
	由於給定的圖是面數 (face) 為 11 的平面圖 (planar graph)，所以根據平面圖的公式 V - E + F = C + 1V−E+F=C+1 整理後 C = V-EC=V−E。另外一個證明：圖中每個 connected component 都是由樹所組成，也就是說每個 component 中邊數會是點數 - 1，直接可推得 C = V - EC=V−E。
	
	有了 C = V - EC=V−E 後，答案就變成 min(V - 1, K + V - E + 已知最大的 degree - 1)。也就是，我們其實不用真正的寫出計算 connected component 的算法，只要統計 degree 最大的點有多大就可以計算出答案了。
	
	整體時間複雜度為 O(V)O(V)，注意這題中有 O(V) = O(E)O(V)=O(E)。
 */



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Graph{
		private int V;
		private int E;
		private ArrayList<Integer>[] adj;
		public Graph(int V) {
			this.V=V;this.E = 0;
			adj = (ArrayList<Integer>[])new ArrayList[V];
			for (int i = 0; i < adj.length; i++) {
				adj[i]= new ArrayList<Integer>();
			}
			
		}
		
		public Graph() {
			// TODO Auto-generated constructor stub
		}

		public int getV() {
			return V;
		}

		public void setV(int v) {
			V = v;
		}

		public int getE() {
			return E;
		}

		public void setE(int e) {
			E = e;
		}

		public void addEdge(int v,int w) {
			adj[v].add(w);
			adj[w].add(v);
			E++;
		}
		public Iterable<Integer> adj(int v) {
			return adj[v];
			
		}
	}
	public static int degree(Graph G,int v) {
		int degree = 0;
		for (int w : G.adj(v)) {
			degree++;
		}
		return degree;
	}
	public static int maxDegree(Graph G) {
		int max = 0;
		for (int i = 0; i < G.V; i++) {
			if(degree(G, i)>max)
				max = degree(G, i);
		}
		return max;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		while(n>0) {
			n--;
			int N = input.nextInt();
			int M = input.nextInt();
			int K = input.nextInt();
			Graph graph = new Graph(N);
			for (int i = 0; i < M; i++) {
				graph.addEdge(input.nextInt(), input.nextInt());
			}
			System.out.println(maxDegree(graph)+K+N-M-1);
		}
		
	
		
	}	
		
}
