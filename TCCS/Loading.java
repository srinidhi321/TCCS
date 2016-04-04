package TCCS;

import java.io.Serializable;

public class Loading implements Serializable{
private int destination;
private int truckId;
Loading(){
//constructor	
}

public void setDestination(int destination){
	this.destination = destination;
}
public void setTruckId(int truckId){
	this.truckId = truckId;
}
public int getDestination(){
	return this.destination;
}
public int getTruckId(){
	return this.truckId;
}
}
