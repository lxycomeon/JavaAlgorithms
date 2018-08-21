package utils;

import java.util.Iterator;

public class LinkedStack<Item> implements Iterable<Item> {
	//使用链表实现下压堆栈
	private class Node{
		Item item;
		Node next;
	}
	private Node first;
	private int N;
	
	public boolean isEmpty() {
		return first ==null;
	}

	public int size() {
		return N;
	}
	public void push( Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	public Item pop() {
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
		//使其能够使用foreach遍历栈中的每一个元素
	}

	private class ReverseArrayIterator implements Iterator<Item> {
		//嵌套类可以访问包含此类的类的实例变量，比如a[],和N。
			//使用迭代器按照后进先出的顺序遍历该数组
		private Node current = first;
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
		
		public void remove() {
			
		}
		
	}
	
}
