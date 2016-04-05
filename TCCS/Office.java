package TCCS;

import java.io.Serializable;
import java.util.ArrayList;

public class Office implements Serializable{
private static int count;
private int id;
private String userid;
private String password;
private String name;
private long revenue;
private long netVolume;
private ArrayList<Truck> idleTrucks = new ArrayList<>();
private ArrayList<Loading> loadingTrucks = new ArrayList<>();
private ArrayList<Office> otherOffices = new ArrayList<>();
private ArrayList<Consignments> unassignedConsignments = new ArrayList<>();
private ArrayList<Consignments> receivedConsignments = new ArrayList<>();
private Manager m;

Office(String name,String userid,String password,Manager m){
	//constructor
	this.id=count++;
	this.name = name;
	this.userid = userid;
	this.password = password;
	this.m = m;
	for(int i=0;i<this.id;i++) otherOffices.add(m.getOffice(i));
}
public int getId(){
	return this.id;
}
public void setName(String name){
	this.name = name;
}
public String getName(){
	return this.name;
}
public void addConsignment(String string1,String string2,String string3,String string4,int from,int to,int volume){
	Consignments temp = new Consignments(string1,string2,string3,string4,from,to,volume);
	temp.setStatus(ConsignmentStatus.wait);
	m.addNewConsignment(temp);
	unassignedConsignments.add(temp);
	this.reviewConsignments();
}
public void sendTruck(int truck){
	for(int i=0;i<loadingTrucks.size();i++){
		if(loadingTrucks.get(i).getTruckId()==truck){
			m.getTruck(truck).setStatus(TruckStatus.sent);
			loadingTrucks.remove(i);
		}
	}
}
public void unloadTruck(int truck){
	Truck temp = m.getTruck(truck);
	for(Consignments t : temp.getAllConsignments()) {
		revenue+=t.getCost();
		netVolume+=t.getVolume();
	    }
	receivedConsignments.addAll(temp.getAllConsignments());
	temp.setStatus(TruckStatus.idle);
	temp.setSource(this.id);
	temp.setDestination(-1);
	idleTrucks.add(temp);
	this.reviewConsignments();
}
public void reviewConsignments(){
	for(int t=0;t<unassignedConsignments.size();t++){
		boolean added = false;
		Consignments temp = unassignedConsignments.get(t);
		for(int i=0;i<loadingTrucks.size();i++){
			if(loadingTrucks.get(i).getDestination()==temp.getDestination()){
				if(m.getTruck(loadingTrucks.get(i).getTruckId()).addCosignment(temp)) {
					added = true;
					unassignedConsignments.remove(t);
					if(m.getTruck(loadingTrucks.get(i).getTruckId()).isFull()) 
					    {
						this.sendTruck(loadingTrucks.get(i).getTruckId());
						//loadingTrucks.remove(i); being removed in send truck
						}
					break;
				}
			}
		}
		if(!added){
			if(idleTrucks.size()!=0){
				Loading load = new Loading();
				load.setTruckId(idleTrucks.get(0).getId());
				load.setDestination(temp.getDestination());
				idleTrucks.remove(0);
			    m.getTruck(load.getTruckId()).setDestination(load.getDestination());	
			    System.out.println(loadingTrucks.size());
			    loadingTrucks.add(load);
				System.out.println(loadingTrucks.size());
				m.getTruck(load.getTruckId()).addCosignment(unassignedConsignments.get(t));
				unassignedConsignments.remove(t);
				if(m.getTruck(load.getTruckId()).isFull()){
					this.sendTruck(load.getTruckId());
					//loadingTrucks.remove(loadingTrucks.size()-1);
				}
			}
		}
	}
}
public void addNewTruck(Truck truck){
	idleTrucks.add(truck);
	truck.setSource(this.id);
	truck.setDestination(-1);
	truck.setStatus(TruckStatus.idle);
	this.reviewConsignments();
}
public ArrayList<Consignments> getUnassignedConsignment(){
	return unassignedConsignments;
}
public long getRevenue(){
	return revenue;
}
public long getNetVolume(){
	return netVolume;
}
public boolean login(String userid, String password){
	if(userid.equals(this.userid)&&password.equals(this.password)) return true;
	return false;
}
public ArrayList<Loading> getLoadingTrucks(){
	return this.loadingTrucks;
}
public ArrayList<Truck> getIdleTrucks(){
	return this.idleTrucks;
}
}
