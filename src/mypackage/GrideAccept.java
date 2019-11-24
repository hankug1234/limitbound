package mypackage;

import java.util.LinkedList;

public class GrideAccept {
	public LinkedList<Data> list;
	public double weight;
	public LinkedList<SeatCondition> seatList;
	public int limit;
	public double sum = 0.0;
	double total = 0.0;

	
	public GrideAccept(LinkedList<Data>l,double weight,LinkedList<SeatCondition> seatList,int limit)
	{
		this.weight = weight;
		this.limit = limit;
		this.seatList = seatList;
		this.list = l;
		
		this.sort(l);
		this.start();
		
	}
	
	public void start()
	{System.out.println("--------------------------------------");
		for(int i=0;i<list.size();i++)
		{
			if(this.checkLimitCondition(list.get(i), this.limit)==true)
			{
				this.total+=list.get(i).value;
				System.out.println("price:"+list.get(i).price+"weight:"+list.get(i).weight);
			}
		}
		System.out.println("value:"+this.total);
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
	
	
	
	private boolean checkLimitCondition(Data d, int limit)
	{
		boolean result = false;
	    double num = d.weight;
	    double devide = 0.0;
	    
	    
	    for(int j=1;j<=limit;j++)
	    {
	    	devide = (double)j;
	    	num =Math.round(num/devide);
	    	if(num == 0.0)
	    		break;
	    	
	     
		for(int i=0;i<this.seatList.size();i++)
		{
			if(this.seatList.get(i).size >= num)
			{
				if(this.seatList.get(i).number>=devide)
				{
					this.seatList.get(i).number=this.seatList.get(i).number-devide;
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
	    
	    return result;
				
	}
	
}
