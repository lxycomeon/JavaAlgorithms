package Main;

import java.util.Iterator;
import utils.ResizingArraryStack;

public class TestResizingArrayStack {

	public static void main(String[] args) {
		ResizingArraryStack<Integer> resArr = new ResizingArraryStack<Integer>();
		
		for (int i = 0; i < 10; i++) {
			resArr.push((int)( Math.random()*100));
		}
		Iterator<Integer> i = resArr.iterator();
		while(i.hasNext()) {
			
			System.out.println(i.next());
		}
		
	}

}
