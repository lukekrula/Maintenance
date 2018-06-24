package newMaintenance.build.classes.cz.uhk.pro1.maintenance.model;

import java.util.Calendar;

abstract class Scheduler {

	private static int period=2;
	private boolean scheduled;
	private int startTime= Calendar.getInstance().get(Calendar.MONTH)+1;
	
	public boolean isScheduled() {
		if (((Calendar.getInstance().get(Calendar.MONTH)+1))%period == 0) {
			scheduled = true;
		}else {
			scheduled = false;
		}
		return scheduled;
	}

	public int getStartTime() {
		return startTime;
	}
 	
	
	
}
