package TCCS;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Time implements Serializable{
private LocalDateTime startTime;
private LocalDateTime endTime;
private int source;
private int destination;

public void setStartTime(){
	this.startTime = LocalDateTime.now();
}

public void setEndTime(){
	this.endTime = LocalDateTime.now();
}
public void setSource(int source){
	this.source = source;
}
public void setDestination(int destination){
	this.destination=destination;
}
public int getSource(){
	return this.source;
}
public int getDestination(){
	return this.destination;
}
public LocalDateTime getStartTime(){
	return this.startTime;
}
public LocalDateTime getEndTime(){
	return this.endTime;
}
}
