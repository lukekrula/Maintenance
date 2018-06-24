package newMaintenance.build.classes.cz.uhk.pro1.maintenance.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

  abstract class Maintenance extends Scheduler  implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 6500188835527788046L;
private static String check = "Check ";	
private static String safety = check + "safety";
private static String valves = check + "valves and fittings";
private static String cables = check + "wires, cables and connectors";
private static String grease = check + "bearings, drives and linear guides";
private static String sensor = check + "sensors, detectors and receptors";
private static String covers = check + "covers(metal, glass)";
private static String safetyDescription = "Ensure that initiation of any safety feature voids the motion of any moving part of the machine. Check the safety barier with a calibrated checking stick.";
private static String valvesDescription = "Check all the valves and fittings for leakage. Ensure that every valve opens and closes at will. Replace leaking parts. The list of valves is a part of a machine manual. ";
private static String cablesDescription = "Ensure that any wire/cable is not broken. Replace broken or cut wires/cables. Every konector has to have no loose pins or wires.";
private static String greaseDescription = "Check bearings, drives and linear guides for grease. Remove excesive grease/add grease if dry. Use only recomended grease and follow the grease plan.";
private static String sensorDescription = "Every sensor has to fulfil its purpose, check wether it is capable of detecting particular materials/behaviours. ";
private static String coversDescription = "Ensure all the covers metal or glass have no cracks or are not loose. Also check wether any of covers is not missing. Check the drawings.";



private Task safetyCheck = new Task(safetyDescription, safety, false);
private Task valvesCheck = new Task(valvesDescription, valves, false);
private Task cablesCheck = new Task(cablesDescription, cables, false);
private Task greaseCheck = new Task(greaseDescription, grease, false);
private Task sensorCheck = new Task(sensorDescription, sensor, false);
private Task coversCheck = new Task(coversDescription, covers, false);

public List<Object> maintenance = new ArrayList<>();



public void initMaintenance() {
	maintenance.add(safetyCheck);
	maintenance.add(valvesCheck);
	maintenance.add(cablesCheck);
	maintenance.add(greaseCheck);
	maintenance.add(sensorCheck);
	maintenance.add(coversCheck);	
}


public List<Object> getMaintenance() {

	return maintenance;
}






public Task getSafetyCheck() {
	return safetyCheck;
}






public void setSafetyCheck(Task safetyCheck) {
	this.safetyCheck = safetyCheck;
}






public Task getValvesCheck() {
	return valvesCheck;
}






public void setValvesCheck(Task valvesCheck) {
	this.valvesCheck = valvesCheck;
}






public Task getCablesCheck() {
	return cablesCheck;
}






public void setCablesCheck(Task cablesCheck) {
	this.cablesCheck = cablesCheck;
}






public Task getGreaseCheck() {
	return greaseCheck;
}






public void setGreaseCheck(Task greaseCheck) {
	this.greaseCheck = greaseCheck;
}






public Task getSensorCheck() {
	return sensorCheck;
}






public void setSensorCheck(Task sensorCheck) {
	this.sensorCheck = sensorCheck;
}






public Task getCoversCheck() {
	return coversCheck;
}






public void setCoversCheck(Task coversCheck) {
	this.coversCheck = coversCheck;
}



}
