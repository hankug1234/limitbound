package mypackage;
import java.util.*;

public class LimitSearch {
	public LinkedList<Data> list;
	public Heap heapQueue = new Heap();
	public double weight;
	public LinkedList<SeatCondition> seatList;
	public int limit;
	
	public LimitSearch(LinkedList<Data> l,double weight, LinkedList<SeatCondition> seatList, int limit)
	{
		list = l;
		sort(l);
		for(int i=0;i<list.size();i++)
		{
			System.out.println("weight"+list.get(i).weight+" price"+list.get(i).price);
		}
		this.weight = weight;
		this.seatList = seatList;
		this.limit = limit;
		Data dd = start();
		System.out.println("index:"+dd.index+" weight:"+dd.weight+" value:"+dd.value);
		for(int i=0;i<dd.seatList.size();i++)
		{
			System.out.println("size:"+dd.seatList.get(i).size+" number:"+dd.seatList.get(i).number);
		}
		
		this.find(dd.index);
	}
	
	private void sort(LinkedList<Data> l)
	{
		double weight, value, priority;
		LinkedList<Data> New = new LinkedList<Data>();
		int i;
		while(!list.isEmpty())
		{
			if(New.isEmpty())
			{
				New.addFirst(list.removeFirst());
			}
			else
			{
				priority = list.getFirst()
						.value/list.getFirst().weight;
				for( i = 0;i<New.size();i++)
				{
					if(priority>(New.get(i).value/New.get(i).weight))
						{New.add(i,list.removeFirst());
						break;
						}
				}
				if(i == New.size())
					New.add(i,list.removeFirst());
					
			}	
		}
		list.clear();
		list = New;
		
	}
	
	private Data start()
	{Data dd,ll,rr,re;
	double weight,value,best,best2;
	dd = new Data(searchBound(1,0,0),0,0);
	dd.seatList = new LinkedList<SeatCondition>();
	this.copy(this.seatList, dd.seatList);
	dd.index = 1;
	best = dd.value;
	heapQueue.insert(dd);
	re = dd;
	boolean check = false;
		while(!heapQueue.heap.isEmpty())
		{
			dd = heapQueue.delete();
				weight = list.get(height(dd.index)).weight;
				value = list.get(height(dd.index)).value;
				ll = new Data(searchBound(2*dd.index,dd.weight+weight,dd.value+value),dd.weight+weight,dd.value+value);
				rr = new Data(searchBound(2*dd.index+1,dd.weight,dd.value),dd.weight,dd.value);
				ll.index = 2*dd.index;
				rr.index = 2*dd.index+1;
				rr.seatList = new LinkedList<SeatCondition>();
				ll.seatList = new LinkedList<SeatCondition>();
				this.copy(dd.seatList, rr.seatList);
				this.copy(dd.seatList, ll.seatList);
			    if(ll.value>best)
			    {
			    	if(ll.weight<=this.weight)
			    	{
			         if(this.checkLimitCondition(ll, this.limit))
			         {
			    	 best = ll.value;
			    	 re = ll;
			    	 check = true;
			         }
			    	}
			    }
			    if(rr.value>best)
			    {
			    	if(rr.weight<=this.weight)
			    	{	
			    	best = rr.value;
			    	re = rr;
			    	}
			    }
			    
			    if(ll.bound>best&&check)
			    {
			    	heapQueue.insert(ll);
			    	System.out.println("index:"+ll.index+"weight:"+ll.weight);
			    	for(int i=0;i<dd.seatList.size();i++)
					{
						System.out.println("size:"+ll.seatList.get(i).size+" number:"+ll.seatList.get(i).number);
					}
			    	check = false;
			    }
			    if(rr.bound>best&& rr.weight<this.weight)
			    {
			    	heapQueue.insert(rr);
			    	System.out.println("index:"+rr.index+"weight:"+rr.weight);
			    	for(int i=0;i<dd.seatList.size();i++)
					{
						System.out.println("size:"+rr.seatList.get(i).size+" number:"+rr.seatList.get(i).number);
					}
			    }
			
		}
	 	return re;
	}
	
	private double searchBound(int index,double weight,double value)
	{
		int count = height(index);
		double w=weight,v=value;
		int num = index;
		if(weight>this.weight)
			return 0;
		
		while(height(2*index)<list.size() && w+list.get(count).weight<this.weight)
		{
			index = 2*index;v+=list.get(count).value; w+=list.get(count).weight; count = height(index);
		}
		if(height(2*index)>=list.size())
		{ 
			 if(height(2*num)>list.size())
				return v;
			  else
				v+=(this.weight-w)*(list.get(count).value/list.get(count).weight);
			
		}
		else
		{
			v+=(this.weight-w)*(list.get(count).value/list.get(count).weight);
		}
		return v;
	}
	
	private int height(int index)
	{
		return (int)(Math.log(index)/Math.log(2));
	}
	
	private boolean checkLimitCondition(Data d, int limit)
	{
		int h = this.height(d.index);
		Data current = list.get(h-1);
		boolean result = false;
	    double num = current.weight;
	    double devide = 0.0;
	    
	    
	    for(int j=1;j<=limit;j++)
	    {
	    	devide = (double)j;
	    	num =Math.round(num/devide);
	    	if(num == 0.0)
	    		break;
	    	
	     
		for(int i=0;i<d.seatList.size();i++)
		{
			if(d.seatList.get(i).size >= num)
			{
				if(d.seatList.get(i).number>=devide)
				{
					d.seatList.get(i).number=d.seatList.get(i).number-devide;
					result = true;
					break;
				}
			}
			
		}
		
		if(result == true)
		{
			break;
		}
		
	    }
	    
		if(result==false)
		{
			d.weight = Double.POSITIVE_INFINITY;
		}
		else
		{
			double total = 0.0;
			for(int i=0;i<d.seatList.size();i++)
			{
				total+=(d.seatList.get(i).number)*(d.seatList.get(i).size);
			}
			total = this.weight - total; 
			d.weight = total;
		}
		System.out.println("weight:"+d.weight+"devide"+devide);
		return result;
				
	}
	
	public void find(int index)
	{
		int h = 1;
		while(h !=0)
		{
			h = (int)(this.height(index)-1);
			if(index%2 == 0)
			{
			System.out.println("price:"+list.get(h).price+"weight:"+list.get(h).weight);}
			index = (int)(index/2);
		}
	}
	
	public void copy(LinkedList<SeatCondition>a,LinkedList<SeatCondition> b)
	{
		for(int i=0;i<a.size();i++)
		{
			SeatCondition data = new SeatCondition(a.get(i).size,a.get(i).number);
			b.add(data);
			
		}
	}

}
