package TCCS;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

enum TruckStatus{
	idle , wait ,sent }

public class Truck implements Serializable{
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

Truck(int id){
	this.id=id;
	initialTime = LocalDateTime.now();
	destination = -1;
}
public int getId(){
	return this.id;
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
public ArrayList<Consignments> getAllConsignments(){
	return this.consignments;
}
public void setSource(int source){
	this.source = source;
}
public void setDestination(int destination){
	this.destination = destination;
}
public void setStatus(TruckStatus status){
	this.status = status;
	if(status == TruckStatus.sent){
		waitTime.get(waitTime.size()-1).setEndTime();
		travelTime.add(new Time());
		for(int i=0;i<consignments.size();i++){
			consignments.get(i).setStatus(ConsignmentStatus.sent);
		}
	}
	else if(status == TruckStatus.idle && this.isFull()){
		travelTime.get(travelTime.size()-1).setEndTime();
		idleTime.add(new Time());
		for(int i=0;i<consignments.size();i++){
			consignments.get(i).setStatus(ConsignmentStatus.delivered);
		}
		consignments.removeAll(consignments);
		this.volumeFilled=0;
	}
	else if(status == TruckStatus.wait){
		idleTime.get(idleTime.size()-1).setEndTime();
		waitTime.add(new Time());
	}
	else if(status == TruckStatus.idle){
		idleTime.add(new Time());
	}
}
public void setInitialTime(){
	this.initialTime = LocalDateTime.now();
}
public long getWaitingTime(){
	long sum=0;
	for(int i=0;i<waitTime.size();i++){
		sum+=(Duration.between(waitTime.get(i).getStartTime(),waitTime.get(i).getEndTime()).getSeconds()+1800)/3600;
	}
	return sum;
}
public long getIdleTime(){
	long sum=0;
	for(int i=0;i<idleTime.size();i++){
		if(idleTime.get(i).getEndTime()!=null)sum+=(Duration.between(idleTime.get(i).getStartTime(),idleTime.get(i).getEndTime()).getSeconds()+30)/60;
		else sum+=(Duration.between(idleTime.get(i).getStartTime(),LocalDateTime.now()).getSeconds()+30)/60; 
	}
	return sum/idleTime.size();
}
public int getDestination(){
	return this.destination;
}
public int getSource(){
	return this.source;
}
public int getVolumeFilled(){
	return this.volumeFilled;
}
public TruckStatus getStatus(){
	return this.status;
}
public boolean isEmpty(){
	if(this.volumeFilled==0) return true;
	return false;
}
public boolean isFull(){
	if(this.volumeFilled==500) return true;
	return false;
}
public LocalDateTime getIdleStartTime(){
	return idleTime.get(idleTime.size()-1).getStartTime();
}
}
