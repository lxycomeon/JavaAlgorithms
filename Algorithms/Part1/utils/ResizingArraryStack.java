package utils;

import java.util.Iterator;

/*
 *一种泛型的可迭代Stack API，实现所有集合类抽象数据类型实现的模板，它将所有的元素保存在数组中，并且可以动态的调整数组的大小以保持数组的大小和栈的大小之比为一个常数 
 	下压（LIFO）栈
 */
public class ResizingArraryStack<Item> implements Iterable<Item> {
	
	private Item[] a = (Item[]) new Object[1];
	private int N = 0;
	
	public boolean isEmpty() {
		return N==0;
	}
	
	public int size() {
		return N;
	}
	
	private void resize( int max) {
		//将栈移动到一个大小为max的新的数组中
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++) {
			temp[i]= a[i];
		}
		a = temp;
	}
	
	public void push(Item item) {
		//将元素添加到栈顶
		if(N==a.length) resize(2*a.length);
		a[N++] = item;
	}
	
	public Item pop() {
		//将元素从栈顶删除
		Item item = a[--N];
		a[N]=null;	//避免对象游离，pop后弹出的对象的引用仍然存在于数组中，这个元素实际上已经是一个没有用的孤儿了，但垃圾回收机制却不知道，所以设置为null，此后垃圾回收机制便会回收他的内存
		if(N>0 && N == a.length/4) resize(a.length/2);
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
		private int i = N;
		@Override
		public boolean hasNext() {
			return i>0;
		}

		@Override
		public Item next() {
			return a[--i];
		}
		
		public void remove() {
			
		}
		
	}
}
