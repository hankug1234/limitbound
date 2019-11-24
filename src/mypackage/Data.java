package mypackage;
import java.util.*;

public class Data {
	double bound;
	double weight;
	double value;
	double price;
	double time;
	int index;
	LinkedList<SeatCondition> seatList;
	
	public Data(double bound, double weight, double value)
	{
		this.bound = bound; this.weight = weight; this.value = value;
	}
    public void calculateInitialValue(double time, double price)
    {
    	this.time = time; this.price = price;
    	this.value = (30/(this.time+1))*(this.price/1000)*this.weight;
    }
}
