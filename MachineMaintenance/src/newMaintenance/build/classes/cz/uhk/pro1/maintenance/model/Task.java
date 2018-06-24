package newMaintenance.build.classes.cz.uhk.pro1.maintenance.model;

import java.io.Serializable;

public class Task implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 770642974840714855L;
	/**
	 * 
	 */
	 
	private String description;
	private String brief;
	private boolean done;
	
	public Task(String description, String brief, boolean done) {
		this.description = description;
		this.brief = brief;
		this.done = done;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	
	public String toString(){
    	
    return description + " " + brief;
    }
	
}
