package TCCS;

import java.io.Serializable;
import java.time.Duration;

enum ConsignmentStatus{
	wait,sent,delivered
}

public class Consignments implements Serializable{
private static int count;
private int id;
private Time time = new Time();
private int truckAllocated;
private int volume;
private long cost;
private ConsignmentStatus status;
private String senderName;
private String senderAddress;
private String receiverName;
private String receiverAddress;

Consignments(String senderName,String senderAddress,String receiverName,String receiverAddress,int from,int to,int volume){
	this.senderName = senderName;
	this.senderAddress = senderAddress;
	this.receiverName = receiverName;
	this.receiverAddress = receiverAddress;
	this.setSource(from);
	this.setDestination(to);
	this.setVolume(volume);
	this.setCost();
	this.id=count++;
}
public int getId(){
	return this.id;
}
public void setSource(int source){
	time.setSource(source);
}
public void setDestination(int destination){
	time.setDestination(destination);
}
public void setStatus(ConsignmentStatus status){
	this.status = status;
	if(status == ConsignmentStatus.wait){
		this.time.setStartTime();
	}
	else if(status == ConsignmentStatus.sent){
		this.time.setEndTime();
	}
}
public void setTruck(int truckAllocated){
	this.truckAllocated = truckAllocated;
}
public void setVolume(int volume){
	this.volume = volume;
}
public void setCost(){
	//modify
}
public long getCost(){
	return this.cost;
}
@Override
public String toString(){
	return new String("id : "+Integer.valueOf(this.id).toString()+" from : "+this.senderName+" to : "+this.receiverAddress);
}
public String print(){
	return this.toString();
}
public String printBill(){
	return new String("cost : "+Long.valueOf(this.cost).toString());
}
public long getWaitingTime(){
	Duration dur = Duration.between(time.getStartTime(),time.getEndTime());
	if((dur.getSeconds()%3600)/60<30) return dur.getSeconds()/3600;
	return dur.getSeconds()/3600+1;
}
public int getDestination(){
	return time.getDestination();
}
public int  getSource(){
	return time.getSource();
}
public int getVolume(){
	return this.volume;
}
}
