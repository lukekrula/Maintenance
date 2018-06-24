package newMaintenance.build.classes.cz.uhk.pro1.maintenance.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author krulalu1
 */
public class Catalog implements Serializable{
    private static final long serialVersionUID = 0x89d54;
    private String maintenanceName;
    public List<Machine> machines = new ArrayList<>();
    public Object[][] tableDataNew = new Object[15][4];
    private int dataOffset;
    
	public static Comparator<Machine> YearComparatorAsc = new Comparator<Machine>()
    {
    // This is where the sorting happens.
        public int compare(Machine o1, Machine o2)
        {
        	 return o1.getYear() - o2.getYear();
        }
    };

	public static Comparator<Machine> YearComparatorDes = new Comparator<Machine>()
    {
    // This is where the sorting happens.
        public int compare(Machine o1, Machine o2)
        {
        	 return o2.getYear() - o1.getYear();
        }
    };   
    
    public static Comparator<Machine> NameComparatorAsc  = new Comparator<Machine>() {

    	public int compare(Machine n1, Machine n2) {

    		String machineName1 = n1.getName().toUpperCase();
    		String machineName2 = n2.getName().toUpperCase();

    		//ascending order
    		return machineName1.compareTo(machineName2);

    	}  
    };
    
    public static Comparator<Machine> NameComparatorDes  = new Comparator<Machine>() {

    	public int compare(Machine n1, Machine n2) {

    	String machineName1 = n1.toString().toUpperCase();
    	String machineName2 = n2.getName().toUpperCase();
    	
    	//descending order
    	return machineName2.compareTo(machineName1);
    	}  
    	    }; 
    
    
    public static Comparator<Machine> SerialComparatorDes  = new Comparator<Machine>() {

    	public int compare(Machine m1, Machine m2) {

    	String machineSerial1 = m1.getSerial().toUpperCase();
    	String machineSerial2 = m2.getSerial().toUpperCase();
    	
    	//descending order
    	return machineSerial2.compareTo(machineSerial1);
    	}  
    	    };
    	    
    	    
    	  public static Comparator<Machine> SerialComparatorAsc  = new Comparator<Machine>() {

    	  public int compare(Machine m1, Machine m2) {

    	  String machineSerial1 = m1.getSerial().toUpperCase();
    	  String machineSerial2 = m2.getSerial().toUpperCase();

    	 //ascending order
    	  return machineSerial1.compareTo(machineSerial2);

    	  }  
    };
    	    	    
    	    	      	    
    	 public static Comparator<Machine> MaintenanceComparatorDes  = new Comparator<Machine>() {

    	 public int compare(Machine l1, Machine l2) {

    	 String machineMaintenance1 = String.valueOf(l1.getMaintenanceDone());
    	 String machineMaintenance2 = String.valueOf(l2.getMaintenanceDone());
    	    	     	
    	 //descending order
    	 return machineMaintenance2.compareTo(machineMaintenance1);
    	}  
    };
    	    	     	    
    	    	     	    
    	public static Comparator<Machine> MaintenanceComparatorAsc  = new Comparator<Machine>() {

    	public int compare(Machine l1, Machine l2) {

    	String machineMaintenance1 = String.valueOf(l1.getMaintenanceDone());
    	String machineMaintenance2 = String.valueOf(l2.getMaintenanceDone());

    	//ascending order
    	return machineMaintenance1.compareTo(machineMaintenance2);

    	}  
    };
    
    
   public int getSize() {
	   return machines.size();
   }    	    	     	    	    
    	    	     	    	    
    public String getMaintenanceName() {
        return maintenanceName;
    }

    public void setMaintenanceName(String shopName) {
        this.maintenanceName = shopName;
    }
    
    public void add(Machine machine){
        machines.add(machine);
    }
    
    public List<Machine> getMachines(){
        return machines; 
    }
    
    public Object[][] getTableData() {
    	if(tableDataNew == null) {
    	      tableDataNew = new Object[15][4]; 
    	    }
    	
    	prepareData();
    	return tableDataNew;
    }
    
    
 
    
     public void deleteByIndex(int  index){
        clearData();
    	machines.remove(index);
     }
     
 	public void sortByName(Boolean direction) {
 	 	if(direction) {
 	 
 	 Collections.sort(getMachines(), NameComparatorAsc);
 	 	}else {
 	 Collections.sort(getMachines(), NameComparatorDes); 		
 	 	}
 	 prepareData();
    }
 
     public void sortByYear(Boolean direction) {
    	 	if(direction) {
    	 
    	 Collections.sort(getMachines(), YearComparatorAsc);
    	 	}else {
    	 Collections.sort(getMachines(), YearComparatorDes); 		
    	 	}
     prepareData();	 
     }
     
     public void sortBySerial(Boolean direction) {
 	 	if(direction) {
 	 
 	 Collections.sort(getMachines(), SerialComparatorAsc);
 	 	}else {
 	 Collections.sort(getMachines(), SerialComparatorDes); 		
 	 	}
  prepareData();	 
  }
     
     public void sortByMaintenance(Boolean direction) {
 	 	if(direction) {
 	 
 	 Collections.sort(getMachines(), MaintenanceComparatorAsc);
 	 	}else {
 	 Collections.sort(getMachines(), MaintenanceComparatorDes); 		
 	 	}
  prepareData();	 
  }
     
     private void prepareData() {
    	 clearData();
    	 int length;
		 
		 if(machines.size()<=15) {
	 			length = machines.size();
	 		}else if(dataOffset + 15 >= machines.size() ){
	 			length = machines.size()%15;
	 		}else {
	 			length=15;
	 		}
	    
   

    	 for(int i=0;i< length;i++) {
    		 tableDataNew[i][0]=machines.get(dataOffset + i).getName();
			 tableDataNew[i][1]=machines.get(dataOffset + i).getSerial();
			 tableDataNew[i][2]=machines.get(dataOffset + i).getYear();
	         tableDataNew[i][3]= String.valueOf(machines.get(dataOffset + i).getMaintenanceDone()); 
		
    	 }
		
	}
     private void clearData() {
 		int length;
     	 
     	 if(machines.size()<=15) {
 			length = machines.size();
 		}else {
 			length = 15;
 		}
    
     	
     	 for(int i=0;i< length;i++) {
 			tableDataNew[i][0]="";
 			tableDataNew[i][1]="";
 			tableDataNew[i][2]="";
 	        tableDataNew[i][3]=""; 
 		
     	 }
 		
 	}

	public int getDataOffset() {
		return dataOffset;
	}

	public void setDataOffset(int dataOffset) {
		this.dataOffset = dataOffset;
	}


     
     
     
     
     
}
