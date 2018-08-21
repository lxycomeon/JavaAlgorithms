package utils;

import java.util.Iterator;

public class LinkedQueue<Item> implements Iterable<Item> {
	//利用链表数据结构的队列实现，支持迭代输出  队列先进先出
	private class Node{
		Item item;
		Node next;
	}
	
	private Node first;
	private Node last;
	private int N;
	
	public boolean isEmpty() {
		return first ==null;
	}
	
	public int size() {
		return N;
	}
	
	//进队
	public void enqueue(Item item) {
		Node oldlast = last;
		last = new Node();
		
		last.item = item;
		last.next = null;
		if(isEmpty()) first = last;
		else
			oldlast.next = last;
		N++;
	}
	//出队列
	public Item dequeue() {
		Item item = first.item;
		first = first.next;
		if(isEmpty()) last =null;
		N--;
		return item;
	}

	@Override
	public Iterator<Item> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<Item>{

		private Node current = first;
		@Override
		public boolean hasNext() {
			return current != null;
		}

		public void remove() { }
		@Override
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}
	
}
