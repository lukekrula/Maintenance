package newMaintenance.build.classes.cz.uhk.pro1.maintenance.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import newMaintenance.build.classes.cz.uhk.pro1.maintenance.model.Machine;
import newMaintenance.build.classes.cz.uhk.pro1.maintenance.model.Tasks;

public class MaintenanceDialog extends JDialog{



/**
	 * 
	 */
	private static final long serialVersionUID = -3652570058503299915L;

	public JLabel lblMachine = new JLabel();
	private JList<String> tasksList;
	public int taskIndex;
	public MaintenanceDialog(Machine machine) {
	 setModal(true);
     setTitle("Machine");
     setLayout(new BorderLayout());
        
   	 add(lblMachine, BorderLayout.NORTH);
     
   	 //create the model and add elements
     DefaultListModel<String> listModel = new DefaultListModel<>();
     
     
   
     
     //create the list
   
     for (int i = 0; i < machine.getTasks().maintenance.size(); i++) {
    	 listModel.addElement(machine.getTasks().maintenance.get(i).toString());
     }
     tasksList = new JList<>(listModel);
     
     add(tasksList, BorderLayout.CENTER);
     
     
     pack();
     setLocationRelativeTo(null);
  }
 }

