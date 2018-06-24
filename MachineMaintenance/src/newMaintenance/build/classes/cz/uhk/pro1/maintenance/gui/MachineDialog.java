package newMaintenance.build.classes.cz.uhk.pro1.maintenance.gui;


import java.util.List;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import newMaintenance.build.classes.cz.uhk.pro1.maintenance.model.Machine;
import newMaintenance.build.classes.cz.uhk.pro1.maintenance.model.Task;
import newMaintenance.build.classes.cz.uhk.pro1.maintenance.model.Tasks;

public class MachineDialog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtName = new JTextField(15);
	private JTextField txtSerialNo = new JTextField(15);
	private JTextField txtYear = new JTextField(15);
	
	private JTextField txtDescription = new JTextField(15);
	private JTextField txtBrief = new JTextField(15);
	
	private JCheckBox chkValve = new JCheckBox();
	private JCheckBox chkLightCurtain = new JCheckBox();
	private JCheckBox chkExtruder = new JCheckBox();
	private JCheckBox chkMould = new JCheckBox();
	

	    public MachineDialog(){
	
	        setModal(true);
	        setTitle("Machine");
	        setLayout(new BorderLayout());
	        
	        JPanel pnl =  new JPanel();
	           add(pnl, BorderLayout.NORTH);
	        
	        pnl.add(new JLabel("Name"));
	        pnl.add(txtName);
	        
	        
	        pnl.add(new JLabel("Serial No.:"));
	        pnl.add(txtSerialNo);
	        
	        
	        pnl.add(new JLabel("Year"));
	        pnl.add(txtYear);
	        
	        JPanel pnlDetail =  new JPanel();
	           add(pnlDetail, BorderLayout.CENTER);
	        
	           pnlDetail.add(new JLabel("Description"));
	        pnlDetail.add(txtDescription);
	        
	        pnlDetail.add(new JLabel("Brief"));
	        pnlDetail.add(txtBrief);
	        
 
	        
	        
	        JButton btnCreateMachine = new JButton("Add Machine");
	        add(btnCreateMachine, BorderLayout.SOUTH);
	        btnCreateMachine.addActionListener(new ActionListener() {
	               @Override
	               public void actionPerformed(ActionEvent e) {
	            	   if(checkName() && checkYear()) {
	       	       	        
	       		        createMachine();
	       		    }	
	       	        else {
	       	        	warning();
	       	        }
	                  
	               }
	           });
	        
	        pack();
	        setLocationRelativeTo(null);
	    }
	    public boolean isNumber() {
	
	    	  try  
	    	  {  
	    	    double d = Double.parseDouble(txtYear.getText());  
	    	  }  
	    	  catch(NumberFormatException nfe)  
	    	  {  
	    	    return false;  
	    	  }  
	    	  return true;  
	    	
	    }
	    
	    public boolean checkYear() {
	    	if((txtYear.getText().length()==4) && (isNumber())){
	    		return true;
	    	}else {
	    		return false;
	    	}
	    	
	    }
	    
	    public void warning() {
	    	Component panel = null;
	        	String warning = "";
	        	if(checkName()==false && checkYear()==false) {
	        		warning = "Fill in propper Year(number of 4 digits) and Name(14 characters) ";
	        	}else if(checkName()==false){
	        		warning = "Enter a propper value to the field Name(1 to 14 characters)";
	        	}else {
	        		warning = "Enter a propper value to the field Year(number of 4 digits)";
	        	}
				JOptionPane.showMessageDialog(panel, warning, "Warning",
	        	    JOptionPane.WARNING_MESSAGE);
	    }
	    
	    
	    public boolean checkName() {
	    	if((txtName.getText().length()>14)||(txtName.getText().length()<1)) {
	    		return false;
	    	}else {
	    		return true;
	    	}
	    	
	    	
	    	
	    }
	    
		public Machine createMachine(){
	      
	        	setVisible(false);
		        
	        	
	        	Tasks tasks = new Tasks();
	        	tasks.initMaintenance();
	        	
	        	 System.out.println(tasks.maintenance.toString());
		        return new Machine(txtName.getText(),
		                            txtSerialNo.getText(),
		                            Integer.valueOf(txtYear.getText()),
		                            false, tasks);
		          
				
		       
	}
}


