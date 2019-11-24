package mypackage;
import java.util.*;
public class main {

	public static void main(String[] args) {
		double input;
		LinkedList<Data> list = new LinkedList<Data>();
		LinkedList<Data> list2 = new LinkedList<Data>();
		LinkedList<SeatCondition> seat = new LinkedList<SeatCondition>();
		LinkedList<SeatCondition> seat2 = new LinkedList<SeatCondition>();
		seat.add(new SeatCondition(1,5));
		seat.add(new SeatCondition(1,5));
		seat.add(new SeatCondition(2,12));
		seat2.add(new SeatCondition(1,5));
		seat2.add(new SeatCondition(1,5));
		seat2.add(new SeatCondition(2,12));
		for(int i=0;i<50;i++)
		{
		input = (double)(randomRange(1,4));	
		double r = randomRange(1,4);
		list.add(new Data(0.0,input,0.0));
		list.get(i).calculateInitialValue((double)i, 1000.0*r);
		
		list2.add(new Data(0.0,input,0.0));
		list2.get(i).calculateInitialValue((double)i, 1000.0*r);
		}
		
		long s =  System.currentTimeMillis();
		LimitSearch test = new LimitSearch(list,34,seat,4);
		GrideAccept test2 = new GrideAccept(list2,34,seat2,4);
		long e = System.currentTimeMillis();
         System.out.println("실행시간:"+(e-s)/1000.0);	
         
		
		
	}
	
	  public static int randomRange(int n1, int n2) {
		    return (int) (Math.random() * (n2 - n1 + 1)) + n1;
		  }

}
