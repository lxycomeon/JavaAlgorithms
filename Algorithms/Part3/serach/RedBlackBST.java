package serach;

import java.util.ArrayDeque;
import java.util.Queue;

//可以表示2-3树（平衡查找树，保证了树的平衡性，允许了一个结点保存多个键）的红黑树数据结构，因为2-3树的代码实现较为难
//
public class RedBlackBST<Key extends Comparable<Key>,Value> {
	private Node root;
	private static final boolean RED = true;
	private static final boolean BLACK = true;
	
	
	private class Node{
		private Key key;
		private Value val;
		private Node left,right;
		private int N;
		boolean color;
		public Node(Key key,Value val,int N,boolean color) {
			this.key = key; this.val = val; this.N = N;this.color = color;
		}
	}
	
	private boolean isRed(Node x) {
		if(x == null) return false;
		return x.color == RED;
	}
	
	//左旋转，将h结点的变为其父结点的左红连接
	Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	//右旋转，将h结点的变为其父结点的右红连接,并返回旋转后h结点的父节点
	Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	void flipColors(Node h) {
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
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
	
	//结点插入操作
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}
	
	private Node put(Node x,Key key,Value val) {
		if(x == null) return new Node(key, val, 1,RED);
		int cmp = key.compareTo(x.key);
		if 	   (cmp < 0) x.left = put(x.left, key, val);
		else if(cmp > 0) x.right = put(x.right, key, val);
		else x.val = val;
		if(isRed(x.right) && !isRed(x.left))  		x = rotateLeft(x);
		if(isRed(x.left) && !isRed(x.left.left))    x = rotateRight(x);
		if(isRed(x.left) && !isRed(x.right))  		flipColors(x);
		
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
	
	private Node moveRedLeft( Node h) {
		//假设结点h为红色，h.left和h.left.left都是黑色
		//将h.left或者h。left的子结点之一变为红色
		flipColors(h);
		if(isRed(h.right.left)) {
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
		}
		return h;
	}
	
	//删除键值最小的结点
	public void deleteMin() {
		if(!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = deleteMin(root);
		if(!(size() == 0)) root.color = BLACK;
		
	}
	
	private Node deleteMin (Node h) {

		if(h.left == null)
			return null;
		if(!isRed(h.left) && !isRed(h.left.left))
			h = moveRedLeft(h);
		h.left = deleteMin(h.left);
		return balance(h);
	}

	private Node balance(Node h) {

		if (isRed(h.right)) {
			h = rotateLeft(h);
		}
		if(isRed(h.right) && !isRed(h.left))  		h = rotateLeft(h);
		if(isRed(h.left) && !isRed(h.left.left))    h = rotateRight(h);
		if(isRed(h.left) && !isRed(h.right))  		flipColors(h);
		
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}
	
	private Node moveRedRight(Node h) {
		//假设结点h为红色，h.right和h.right.left都是黑色
		//将h.right或者h。right的子结点之一变为红色
		flipColors(h);
		if (!isRed(h.left.left)) {
			h = rotateRight(h);
		}
		return h;
	}
	//删除键值最大的结点
	public void deleteMax() {
		if(!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = deleteMax(root);
		if(!(size() == 0)) root.color = BLACK;
	}
	
	private Node deleteMax(Node h) {

		if(isRed(h.left))
			h = rotateRight(h);
		if(h.right == null)
			return null;
		if(!isRed(h.right) && !isRed(h.right.left))
			h = moveRedRight(h);
		h.right = deleteMax(h.right);
		return balance(h);
	}
	
	//删除指定的key的结点
	public void delet(Key key) {
		if(!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = delete(root,key);
		if (!(size() == 0)) {
			root.color =BLACK;
		}
	}
	
	private Node delete(Node h, Key key) {

		if (key.compareTo(h.key) < 0) {
			if(!isRed(h.left) && !isRed(h.left.left))
				h = moveRedLeft(h);
			h.left =delete(h.left, key);
		} else {
			if(isRed(h.left))
				h = rotateRight(h);
			if(key.compareTo(h.key) == 0 && (h.right == null))
				return null;
			if(!isRed(h.right) && !isRed(h.right.left))
				h = moveRedRight(h);
			if (key.compareTo(h.key) == 0) {
				h.val = get(h.right, min(h.right).key);
				h.key = min(h.right).key;
				h.right = deleteMin(h.right);
			} else {
				h.right = delete(h.right, key);
			}
		}
		return balance(h);
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
