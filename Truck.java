package TCCS;

import java.time.LocalDateTime;
import java.util.ArrayList;

enum TruckStatus{
	idle (0), wait (1),sent(2);
	private int status;
	TruckStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}
};

public class Truck {
private static int count;
private int id;
private int source;
private int destination;
private int volumeFilled;
private LocalDateTime initialTime;
private ArrayList<Time> idleTime = new ArrayList<>();
private ArrayList<Time> waitTime = new ArrayList<>();
private ArrayList<Time> travelTime = new ArrayList<>();
private ArrayList<Consignments> consignments = new ArrayList<>();
private TruckStatus status;

Truck(){
	this.id=count++;
}
public boolean addCosignment(Consignments consignment){
	if(this.volumeFilled+consignment.getVolume()<=500) {
		if(this.isEmpty()==true){
			idleTime.get(idleTime.size()-1).setEndTime();
			waitTime.add(new Time());
		}
		consignments.add(consignment);
		this.volumeFilled+=consignment.getVolume();
		return true;
	}
	return false;
}
public void setSource(int source){
	this.source = source;
}
public void setDestination(int destination){
	this.destination = destination;
}
public void setStatus(TruckStatus status){
	this.status = status;
}
public void setInitialTime(){
	this.initialTime = LocalDateTime.now();
}
public int getWaitingTime(){
	//to be modified
	return 0;
}
public int getIdleTime(){
	//to be modified
	return 0;
}
public boolean isEmpty(){
	if(this.volumeFilled==0) return true;
	return false;
}
public boolean isFull(){
	if(this.volumeFilled==500) return true;
	return false;
}
}
