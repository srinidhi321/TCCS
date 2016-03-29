package TCCS;

public class Consignments {
private static int count;
private int id;
private Time time;
private int truckAllocated;
private int volume;
private int cost;
private boolean delivered;
private String senderName;
private String senderAddress;
private String receiverName;
private String receiverAddress;

Consignments(String senderName,String senderAddress,String receiverName,String receiverAddress){
	this.senderName = senderName;
	this.senderAddress = senderAddress;
	this.receiverName = receiverName;
	this.receiverAddress = receiverAddress;
	this.id=count++;
}
public void setSource(int source){
	time.setSource(source);
}
public void setDestination(int destination){
	time.setDestination(destination);
}
public void setStatus(){
	//patha nahi kya karna hai
}
public void setTruck(int truckAllocated){
	this.truckAllocated = truckAllocated;
}
public void setVolume(int volume){
	this.volume = volume;
}
public void setCost(int cost){
	this.cost = cost;
}
@Override
public String toString(){
	return new String("id : "+Integer.valueOf(this.id).toString()+" from : "+this.senderName+" to : "+this.receiverAddress);
}
public void print(){
	this.toString();
}
public void printBill(){
	System.out.println("cost : "+Integer.valueOf(this.cost).toString());
}
public int getWaitingTime(){
	//to be modified
	return 0;
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
