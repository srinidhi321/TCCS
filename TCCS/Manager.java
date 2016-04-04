package TCCS;

import java.io.Serializable;
import java.util.ArrayList;

public class Manager implements Serializable{
private String name;
private static ArrayList<Truck> trucks = new ArrayList<>();
private static ArrayList<Office> offices = new ArrayList<>();
private static ArrayList<Consignments> consignments = new ArrayList<>();
private String userid;
private String password;

Manager(String name,String userid,String password){
	this.name = name;
	this.userid = userid;
	this.password = password;
}
public String getname(){
	return this.name;
}
public static void addNewConsignment(Consignments consignment){
	consignments.add(consignment);
}
public static void addNewTruck(int office){
    Truck temp = new Truck();
    trucks.add(temp);
    offices.get(office).addNewTruck(temp);
}
public static void addNewOffice(String name,String userid,String password){
	Office temp = new Office(name,userid,password);
	offices.add(temp);
}
public static String printConsignment(int consignment){
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
public static Truck getTruck(int truck){
	return trucks.get(truck);
}
public static Office getOffice(int office){
	return offices.get(office);
}
public static int getCountOfOffices(){
	return offices.size();
}
}
