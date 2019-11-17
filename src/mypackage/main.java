package mypackage;
import java.util.*;
public class main {

	public static void main(String[] args) {
		LinkedList<Data> list = new LinkedList<Data>();
		for(int i=0;i<25;i++)
		{
		list.add(new Data(0,i+1,10+(5*i)));
		}
		long s =  System.currentTimeMillis();
		
		LimitSearch test = new LimitSearch(list,80);
		long e = System.currentTimeMillis();
         System.out.println("실행시간:"+(e-s)/1000.0);	
         
	

	}

}
