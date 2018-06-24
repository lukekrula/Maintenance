package newMaintenance.build.classes.cz.uhk.pro1.maintenance.app;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.ListSelectionListener;
import newMaintenance.build.classes.cz.uhk.pro1.maintenance.gui.MachineDialog;
import newMaintenance.build.classes.cz.uhk.pro1.maintenance.gui.MaintenanceDialog;
import newMaintenance.build.classes.cz.uhk.pro1.maintenance.model.Catalog;
import newMaintenance.build.classes.cz.uhk.pro1.maintenance.model.Machine;

public class MaintenanceMain extends JFrame  {

	  /**
	 * 
	 */
	private static final long serialVersionUID = -4107966412645664937L;
	public Catalog catalog = new Catalog();
	
	private JButton btnDelete;
	Font font = new Font("Courier", Font.BOLD, 12);
	public Object[][] data = new Object[15][4] ;
	private Boolean yearDirection = false;
	private Boolean nameDirection = false;
	private Boolean serialDirection = false;
	private Boolean maintenanceDirection = false;
	public int index;
	
    JButton btnLeft = new JButton("<");
    JButton btnRight = new JButton(">");
    JButton btnVeryLeft = new JButton("<<");
    JButton btnVeryRight = new JButton(">>");
    
	public int maxPageView;
	public int pageView = 1;
	
	String[] columnNames = {"Name",
            "Serial",
            "Year",
            "Maintenance"};
	JTable table = new JTable();	
	public MaintenanceMain(){
		
			
		
	           setDefaultCloseOperation(EXIT_ON_CLOSE);
	           setTitle("Maintenance");
        

	           load();
	           printMachine();

	           JPanel pnlWest =  new JPanel();
	           add(pnlWest, BorderLayout.WEST);
	           
	            pnlWest.add(btnVeryLeft, BorderLayout.WEST);
		        btnVeryLeft.addActionListener(e -> beginning());
	          
		        pnlWest.add(btnLeft, BorderLayout.WEST);
		        btnLeft.addActionListener(e -> backward());

		        JPanel pnlEast =  new JPanel();
		           add(pnlEast, BorderLayout.EAST);
		           
		        
		           pnlEast.add(btnRight , BorderLayout.EAST);
		        btnRight.addActionListener(e -> forward());
		        
		        pnlEast.add(btnVeryRight , BorderLayout.EAST);
		        btnVeryRight.addActionListener(e -> end());
		        
		        
		    	table.setModel(new DefaultTableModel(data, columnNames));
		    	
		    	JScrollPane scrollPane = new JScrollPane(table);
		    	table.setFillsViewportHeight(true);
		    	TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		    	table.setRowSorter(sorter);
		    	table.getTableHeader().setReorderingAllowed(false);
		    	add(scrollPane, BorderLayout.CENTER);
		    	
		    	table.addMouseListener(new MouseAdapter() {
		    	    public void mousePressed(MouseEvent mouseEvent) {
		    	        JTable table =(JTable) mouseEvent.getSource();
		    	        Point point = mouseEvent.getPoint();
		    	        int row = table.rowAtPoint(point);
		    	        
		    	        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		    	         if (row + (pageView-1)*15 < catalog.getSize()) {
		    	        	machineOverview(row + (pageView-1)*15,catalog.machines.get(row  + (pageView-1)*15));
		    	 	         
		    	         }
		    	        			    	        	
		    	        	System.out.println(catalog.machines.get(row  + (pageView-1)*15).tasks);
		    	        }
		    	    }
		    	});
		    	
		    	
		    	table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		            public void valueChanged(ListSelectionEvent event) {
		            	index = table.getSelectedRow();
		            	showBtnDel(index);
		              
		            }
		        });
		    	
		    	table.getTableHeader().addMouseListener(new MouseAdapter() {
		    	    @Override
		    	    public void mouseClicked(MouseEvent e) {
		    	        int col = table.columnAtPoint(e.getPoint());
		    	        table.setModel(new DefaultTableModel(data, columnNames));
		    	        sortTable(col);
		    	       
		    	    }
		    	});
	           
	           
	           JPanel pnl =  new JPanel();
	           add(pnl, BorderLayout.SOUTH);

	           JButton btnAdd = new JButton("Add machine");
	           pnl.add(btnAdd); // pridej btn na pnl

	         
	           btnAdd.addActionListener(new ActionListener() {
	               @Override
	               public void actionPerformed(ActionEvent e) {
	                   addMachine();
	                  
	               }
	           });
	           
	           
	            JButton btnSave = new JButton("Save");
	           pnl.add(btnSave); // pridej btn na pnl
	           
	           btnSave.addActionListener(e -> save());
	           
	           
	           JButton btnLoad = new JButton("Load");
	           pnl.add(btnLoad); // pridej btn na pnl
	           
	           btnLoad.addActionListener(e -> load());
	           
	           btnDelete = new JButton("Delete");
	           pnl.add(btnDelete); // pridej btn na pnl
	           
	           btnDelete.addActionListener(e -> deleteMachine());
	        
	           pack();
	}
	
        private void pagination() {
        	  maxPageView = catalog.getSize()/15;
			  if ((catalog.getSize() % 15) != 0) {
				  maxPageView++;
			  }
			  if (maxPageView>1 && pageView<maxPageView) {
				  rightEnable();
				  leftDisable();
			  }else if(maxPageView==0){
				  rightDisable();
				  leftDisable();
			  }else if(maxPageView>1 && pageView>1) {
				  leftEnable();
			  }else if(maxPageView>1 && pageView<=1) {
				  leftDisable();
			  }
        }
        
        private void actualPage() {
        	catalog.setDataOffset((pageView-1)*15);
        }
        
        private void beginning() {
        	pageView=1;
        	leftDisable();
        	actualPage();
        	printMachine();
        }
        
        private void end() {
        	pageView=maxPageView;
        	rightDisable();
        	actualPage();
        	printMachine();
        }
        
        
        
        
        private void forward() {
        	pageView++;
        	
        	if (pageView>=maxPageView) {
        		rightDisable();
        	}
        	if (pageView!=1) {
        		leftEnable();
        	}
        	actualPage();
        	printMachine();
        }
        private void backward() {
        	pageView--;
        	if (pageView<=1) {
        		leftDisable();
        	}
        	if (pageView!=maxPageView) {
        		rightEnable();
        	}
        	actualPage();
        	printMachine();
        }
        
	    private void machineOverview(int row, Machine machine) {
	    	  MaintenanceDialog d = new MaintenanceDialog(machine); //vytvor dialog
	 	         d.lblMachine.setText(catalog.machines.get(row).toString());
	        	 d.taskIndex =row; 
	 	         d.setVisible(true);
	    }
	
	    private void addMachine(){
	        
	         MachineDialog d = new MachineDialog(); //vytvor dialog
	         d.setVisible(true); // zobraz dialog, zde se program zastavi u modalniho dialogu
	        
	         //ziskat stroj a vytvor stroj (instance tridy Machine)
	         Machine m = d.createMachine();
	         //pridat stroj do katalogu
	         catalog.add(m);
	         
	         printMachine();
	       
	    }
	    
	    private void sortTable(int col) {
	    	if (col==0) {
	    		sortTableByName(col);
	    	}else if(col==1){
	    		sortTableBySerial(col);	
	    	}else if(col==2){
	    		sortTableByYear(col);
    	    }else if(col==3){
    	    	sortTableByMaintenance(col);
    	    }
	    }
	    
	    private void sortTableByName(int col) {
	    	catalog.sortByName(nameDirection);
	    	  
     	   printMachine();
     	   if(nameDirection) {
            	table.getColumnModel().getColumn(col).setHeaderValue("Name A-Z");
            }else {
            	table.getColumnModel().getColumn(col).setHeaderValue("Name Z-A");
            }
     	   nameDirection ^= true;
     	   
	    }
	    private void sortTableBySerial(int col) {
	    	catalog.sortBySerial(serialDirection);
       	  
       	  	printMachine();
       	  
       	  	if(serialDirection) {
                  	table.getColumnModel().getColumn(col).setHeaderValue("Serial A-Z");
                  }else {
                  	table.getColumnModel().getColumn(col).setHeaderValue("Serial Z-A");
                  }
       	  	serialDirection ^= true;
       	  
	    }
		private void sortTableByYear(int col) {
			catalog.sortByYear(yearDirection);
     	   
        	   printMachine();
        	   
        	 if(yearDirection) {
                	table.getColumnModel().getColumn(col).setHeaderValue("Year 0-9");
                }else {
                	table.getColumnModel().getColumn(col).setHeaderValue("Year 9-0");
                }
        	yearDirection ^= true;
		}
		private void sortTableByMaintenance(int col) {
			 catalog.sortByMaintenance(maintenanceDirection);
        	 
       	  printMachine();
       	  
       	  if(maintenanceDirection) {
                  	table.getColumnModel().getColumn(col).setHeaderValue("Done");
                  }else {
                  	table.getColumnModel().getColumn(col).setHeaderValue("Not done");
                  }
       	  maintenanceDirection ^= true;
		}
		
	    private void showBtnDel(int index) {
	    	 if(index + 1 <=catalog.getSize()) {
          	   btnDelete.setEnabled(true);
     	        
     	        }else {
     	        	btnDelete.setEnabled(false);
     	        }
	    }
	    
	   private void leftEnable() {
	    	btnLeft.setEnabled(true);
	    	btnVeryLeft.setEnabled(true);
	    }
	   private void leftDisable() {
	    	btnLeft.setEnabled(false);
	    	btnVeryLeft.setEnabled(false);
	    }
	   private void rightEnable() {
	    	btnRight.setEnabled(true);
	    	btnVeryRight.setEnabled(true);
	    }
	   private void rightDisable() {
	    	btnRight.setEnabled(false);
	    	btnVeryRight.setEnabled(false);
	    }
	   
	    
	    private void deleteMachine(){
	    	
	        catalog.deleteByIndex(index);
	       
	        printMachine();
	     
	    }
	    
	    private void printMachine(){
	    
	    	data =  catalog.getTableData();
	    	
	    	table.setModel(new DefaultTableModel(data, columnNames));
	    	pagination();
	    }
	    
	   
	    private void save(){
	        try(ObjectOutputStream s = new ObjectOutputStream(new FileOutputStream("data.bin"))){
	          //ulozit objekt do streamu
	          s.writeObject(catalog);
	        } catch (IOException ex) {
	          Logger.getLogger(MaintenanceMain.class.getName()).log(Level.SEVERE, null, ex);
	      }
	      // dialog pro lozeno ("Ulozeno");  
	    }
	     private void load(){
	       try( ObjectInputStream s =new ObjectInputStream( new FileInputStream("data.bin"))){
	           catalog =(Catalog)s.readObject();
	       } catch (IOException | ClassNotFoundException ex) {
	          Logger.getLogger(MaintenanceMain.class.getName()).log(Level.SEVERE, null, ex);
	      }
	       printMachine();
	     }
	   
	    /**
	     * @param args the command line arguments
	     */
	    public static void main(String[] args) {
	 
	        SwingUtilities.invokeLater(new Runnable() {
	             @Override
	             public void run() {
	                    MaintenanceMain window = new MaintenanceMain();
	                   
	                    window.pack();
	                    window.setLocationRelativeTo(null);
	                    window.setVisible(true);
	                  
	                 }
	         });
	   }
     }