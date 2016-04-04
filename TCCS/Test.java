package TCCS;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {

	public static void main(String[] args) {
		try
	      {
	         FileInputStream fileIn = new FileInputStream("Manager.dat");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         Manager m = (Manager) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(Exception e)
	      {
	         try
	         {
	        	 System.out.println("No Data Found");
		         Manager m = new Manager("Partha Pratim Das","ppd","fuckoff");
		         m.addNewOffice("Visakhapatnam","vskp","pass");
		         m.addNewOffice("Hyderabad","sec","pass");
		         m.addNewOffice("Kolkata","hwh","pass");
		         m.addNewOffice("Bangalore","bgl","pass");
		         m.addNewOffice("Delhi","del","pass");
		         m.addNewOffice("Mumbai","mum","pass");
		         m.addNewOffice("Chennai","che","pass");
		         m.addNewOffice("Allepey","all","pass");
		         m.addNewOffice("Goa","goa","pass");
		         m.addNewOffice("Gurgaon","gur","pass");
		         m.addNewOffice("Kashmir","kas","pass");
		         m.addNewOffice("Lucknow","luk","pass");
		         System.out.println(m.getCountOfOffices());
		         for(int i=0;i<m.getCountOfOffices();i++){
		        	 m.addNewTruck(i);
		         }
		       
		        FileOutputStream fileOut = new FileOutputStream("Manager.dat");
	            ObjectOutputStream out = new ObjectOutputStream(fileOut);
	            out.writeObject(m);
	            out.close();
	            fileOut.close();
	            System.out.printf("Serialized data is saved in Manager.dat");
	         }catch(IOException i)
	         {
	             System.out.println("Data Not Saved");
	         }
	      }
	}

}
