package mypackage;
import java.util.*;

public class Heap {
	public LinkedList<Data> heap = new LinkedList();
	Data best;
	
	public void insert(Data n)
	{int index,pre;
	 Data change;
		if(heap.isEmpty())
		{
			heap.addFirst(n);
		}
		else
		{   heap.addLast(n);
			index = heap.size()-1;
			
			while((index != 0) && (n.bound > heap.get((int)(index/2)).bound))
			{  
			
				pre = index;
				index = (int)(index/2);
				change =  heap.remove(index);
				n = heap.remove(pre-1);
				heap.add(pre-1, change);
				heap.add(index, n);
				
			}
				
			
		}	
	}
	
	public Data delete()
	{Data del;
	int index = 0,pre = 0;
	Data c1;
		if(heap.size() == 0)
			return null;
		else if(heap.size() == 1)
			return heap.removeFirst();
		else if(heap.size() == 2)
		{
			if(heap.getFirst().bound>heap.getLast().bound)
				return heap.removeFirst();
			else
				return heap.removeLast();
		}
		else 
		{
			del = heap.removeFirst();
			heap.addFirst(heap.removeLast());
			while(2*pre+2<heap.size())
			{
				if(heap.get(2*pre+1).bound>heap.get(2*pre+2).bound)
				{
					if(heap.get(2*pre+1).bound>heap.get(pre).bound)
					{
						index = pre;
						pre = 2*pre+1;
						c1 = heap.remove(pre);
						heap.add(pre, heap.get(index));
						heap.remove(index);
						heap.add(index, c1);
					}
					else
					{
						return del;
					}	
				}
				else
				{
					if(heap.get(2*pre+2).bound>heap.get(pre).bound)
					{
						index = pre;
						pre = 2*pre+2;
						c1 = heap.remove(pre);
						heap.add(pre, heap.get(index));
						heap.remove(index);
						heap.add(index, c1);
					}
					else
					{
						return del;
					}
				}
			}
			
			
		}
		return del;
	}
	

}

