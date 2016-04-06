package TCCS;

import java.io.Serializable;
import java.util.ArrayList;

public class Manager implements Serializable{
private String name;
private ArrayList<Truck> trucks = new ArrayList<>();
private ArrayList<Office> offices = new ArrayList<>();
private ArrayList<Consignments> consignments = new ArrayList<>();
private String userid;
private String password;

public Manager(String name,String userid,String password){
	this.name = name;
	this.userid = userid;
	this.password = password;
}
public String getname(){
	return this.name;
}
public void addNewConsignment(Consignments consignment){
	consignments.add(consignment);
}
public void addNewTruck(int office){
    Truck temp = new Truck(this.trucks.size());
    trucks.add(temp);
    offices.get(office).addNewTruck(temp);
}
public void addNewOffice(String name,String userid,String password){
	System.out.println("hey");
	Office temp = new Office(name,userid,password,this,this.offices.size());
	offices.add(temp);
}
public String printConsignment(int consignment){
	for(int i=0;i<consignments.size();i++){
		if(consignments.get(i).getId()==consignment){
			return consignments.get(i).print();
		}
	}
	return null;
}
public String printTruck(int truck){
	for(int i=0;i<trucks.size();i++){
		if(trucks.get(i).getId()==truck){
			long wait = trucks.get(i).getWaitingTime();
			long idle = trucks.get(i).getIdleTime();
			return new String("Idle time : "+Long.valueOf(idle).toString()+" Waiting time : "+Long.valueOf(wait).toString());
		}
	}
	return null;
}
public boolean login(String userid,String password){
	if(this.userid.equals(userid)&&this.password.equals(password)) return true;
	return false;
}
public String printOfficeRevenue(int office){
	for(int i=0;i<offices.size();i++){
		if(offices.get(i).getId()==office){
			return Long.valueOf(offices.get(i).getRevenue()).toString(); 
		}
	}
	return null;
}
public String printOfficeNetVolume(int office){
	for(int i=0;i<offices.size();i++){
		if(offices.get(i).getId()==office){
			return Long.valueOf(offices.get(i).getNetVolume()).toString(); 
		}
	}
	return null;
}
public Truck getTruck(int truck){
	return trucks.get(truck);
}
public Office getOffice(int office){
	return offices.get(office);
}
public int getCountOfOffices(){
	return offices.size();
}
public int getCountOfConsignments(){
	return consignments.size();
}
public ArrayList<Consignments> getConsignments(){
	return this.consignments;
}
public ArrayList<Truck> getTrucks(){
	return this.trucks;
}
}
