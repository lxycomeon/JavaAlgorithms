package serach;

import java.util.ArrayDeque;
import java.util.Queue;

//二叉查找树
//所有的操作在最坏情况下需要的时间都和树的高度成正比
public class BST<Key extends Comparable<Key>,Value> {
	private Node root;
	
	private class Node{
		private Key key;
		private Value val;
		private Node left,right;
		private int N;
		public Node(Key key,Value val,int N) {
			this.key = key; this.val = val; this.N = N;
		}
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node x) {
		if(x == null) return 0;
		else 		  return x.N;
	}
	
	public Value get(Key key) {
		return get(root,key);
	}

	private Value get(Node x, Key key) {

		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) return get(x.left, key);
		else if (cmp>0)  return get(x.right, key );
		else return x.val;
	}
	
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}
	
	private Node put(Node x,Key key,Value val) {
		if(x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if 	   (cmp < 0) x.left = put(x.left, key, val);
		else if(cmp > 0) x.right = put(x.right, key, val);
		else x.val = val;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	//返回最小键值
	public Key min() {
		return min(root).key;
	}
	
	private Node min(Node x ) {
		if(x.left == null) return x;
		return min(x.left);
	}
	
	//求小于等于key的最大键
	public Key floor(Key key) {
		Node x = floor(root,key);
		if(x == null) return null;
		return x.key;
	}

	private Node floor(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if(cmp < 0) return floor(x.left, key );
		Node t = floor(x.right, key);
		if(t != null) return t;
		else		  return x;
	}
	
	//返回最大键值
	public Key max() {
		return max(root).key;
	}
	
	private Node max(Node x ) {
		if(x.right == null) return x;
		return max(x.right);
	}
	
	//求大于等于key的最小键
	public Key ceil(Key key) {
		Node x = ceil(root,key);
		if(x == null) return null;
		return x.key;
	}

	private Node ceil(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if(cmp > 0) return ceil(x.right, key );
		Node t = ceil(x.right, key);
		if(t != null) return t;
		else		  return x;
	}
	
	//返回排名为k的键值
	public Key select(int k) {
		return select(root,k).key ;
	}
	//返回排名为k的结点
	private Node select( Node x,int k) {
		if(x == null) return null;
		int t = size(x.left);
		if     (t > k) return select(x.left, k);
		else if(t < k) return select(x.right, k);
		else  		   return x;
	}
	
	//返回该Key值所在的排名
	public int rank(Key key ) {
		return rank(key,root);
	}

	//返回以x为结点的子树种小于x。key的键的数量
	private int rank(Key key, Node x) {
		if(x == null) return 0;
		int cmp = key.compareTo(x.key);
		if		(cmp < 0) return rank(key, x.left);
		else if (cmp > 0) return 1 + size(x.left) +rank(key, x.right);
		else 			  return size(x.left);
	}
	
	//删除最小key结点
	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.left == null)  return x.right;//即把x结点删除了
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) +1;
		return x;
	}
	
	//删除键值为key的结点
	public void delete(Key key) {
		root = delete(root,key);
	}
	//删除在结点x上键值为key的结点
	private Node delete(Node x, Key key) {
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if		(cmp < 0) x.left  = delete(x.left, key);
		else if (cmp > 0) x.right = delete(x.right, key);
		else {
			if (x.right == null) return x.left;
			if (x.left  == null) return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) +size(x.right) + 1;
		return x;
	}
	
	//顺序打印所有键值对信息（中序遍历）
	public void print(Node x) {
		if (x == null) return;
		print(x.left);
		System.out.println("<"+x.key+"::"+x.val+">");
		print(x.right);
	}
	
	//返回指定范围内键 的keys的方法
	public Iterable<Key> keys() {
		return keys(min(),max());
	}

	private Iterable<Key> keys(Key lo, Key hi) {

		Queue<Key> queue = new ArrayDeque<Key>();	//queue是一个接口，new时要new一个它的实现类
		keys(root,queue,lo,hi);
		return queue;
	}

	private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {

		if (x == null) return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if(cmplo < 0) keys(x.left, queue, lo, hi);
		if(cmplo <= 0 && cmphi >= 0) queue.add(x.key);
		if(cmphi > 0) keys(x.right, queue, lo, hi);
	}
	
	
}
