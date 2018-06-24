package newMaintenance.build.classes.cz.uhk.pro1.maintenance.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tasks extends Maintenance implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5733764645267138936L;
	public List<Task> tasks = new ArrayList<>();

    public int getSize() {
	   return tasks.size();
	}    	    	     	    	  

    public void add(Task task){
        tasks.add(task);
    }
    
    public List<Task> getTasks(){
        return tasks; 
    } 
    
    public int getMaintenanceSize() {
    	return maintenance.size();
    }
    
}