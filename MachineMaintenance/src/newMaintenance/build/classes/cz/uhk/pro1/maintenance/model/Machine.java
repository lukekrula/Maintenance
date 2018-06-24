package newMaintenance.build.classes.cz.uhk.pro1.maintenance.model;
import java.io.Serializable;

/**
 *
 * @author krulalu1
 */
public class Machine implements Serializable {
    private static final long serialVersionUID = 0x25a5e;
    private String name;
    private String serialNo;
    private int year;
    private boolean maintenanceDone;
    private String space;
    public Tasks tasks = new Tasks();
    
    public Machine(String name, String serialNo, int year, boolean maintenanceDone, Tasks tasks){
    this.name = name;
    this.serialNo = serialNo;
    this.year = year;
    this.maintenanceDone = maintenanceDone;
    this.tasks = tasks;
    };
    
   
    
    
    public String toString(){
    	int n = 15 - name.length();
    	space = new String(new char[n]).replace("\0", " ");
    	
    return name + space + year + "  " + serialNo;
    }
    
    public String getName(){
    return name;
    }
 
    public Tasks getTasks(){
        return tasks;
        }
    
    public void setName(String newName){
    name = newName;
    }
    
    public String getMaintenance(){
    return serialNo;
    }
    
    public boolean getMaintenanceDone() {
    	return maintenanceDone;
    }
    
    public void setMaintenanceDone(boolean maintenanceDone) {
    	this.maintenanceDone = maintenanceDone;
    }
    
    public void setSerial(String serial){
    this.serialNo = serial;
    }
    
    public String getSerial(){
        return serialNo;
        }
  

    public int getYear() {
        return year;
        
    }

    public void setYear(int year) {
        this.year = year;
    }
}
