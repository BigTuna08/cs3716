package scheduler;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.function.Consumer;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import jdatepicker.JDatePicker;
import scheduler.TimePeriod.Period;

class TestingFrame extends JFrame{
	public void refit() {
		pack();
	}
	JPanel main=new JPanel();
	JTabbedPane tabs=new JTabbedPane();
	static class RoomSelectorComponent extends JComboBox  {
		class SpaceWrapper{
			public Space s;
			public SpaceWrapper(Space sp) {
				s=sp;
			}
			public String toString() {
				return s.name;
			}
		};
		RoomSelectorComponent(){
			super();
			Observer roomSelectorUpdater=new Observer() {
				public void update() {
					Space x=getRoom();
					removeAllItems();
					for(Space s:MasterSchedule.getInstance().getSpaces()) {
						SpaceWrapper o=new SpaceWrapper(s);
						addItem(o);
						if(x==s) {//restore previous selection
							setSelectedItem(o);
						}
					}
				}
			};
			roomSelectorUpdater.update();
			
			MasterSchedule.getInstance().subscribe(roomSelectorUpdater);
		}
		Space getRoom(){
			Object o=getSelectedItem();
			if(o!=null) {
				return ((SpaceWrapper)getSelectedItem()).s;
			}else {
				return null;
			}
		}


	}
	static class AddRoomTab extends JPanel{
		static class RoomDeletionPane extends JPanel{
			RoomDeletionPane(){
				setLayout(new FlowLayout());
				Observer updater=new Observer() {
					public void update() {
						removeAll();
						for(Space s:MasterSchedule.getInstance().getSpaces()) {
							JButton b=new JButton(s.name);
							b.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									Controller.INSTANCE.deleteSpace(s);
								}
							});
							add(b);
						}
						updateUI();
					}
				
				};
				updater.update();
				MasterSchedule.getInstance().subscribe(updater);
	
			}
		}
		public AddRoomTab() {
			
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			JPanel addRoomPanelRow1=new JPanel();
			JPanel addRoomPanelRow2=new JPanel();
			JPanel row3=new JPanel();
			row3.add(new JLabel("delete rooms"));
			add(addRoomPanelRow1);
			add(addRoomPanelRow2);
			add(row3);
			add(new RoomDeletionPane());
			roomNameField=new JTextField(10);
			roomDescriptionField=new JTextField(20);
			roomSubmitBtn=new JButton("add room");
			roomSubmitBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Controller.INSTANCE.addSpace(roomNameField.getText(), roomDescriptionField.getText());
				}	
			});
			addRoomPanelRow1.add(new JLabel("name:"));
			addRoomPanelRow1.add(roomNameField);
			addRoomPanelRow1.add(new JLabel("description:"));
			addRoomPanelRow1.add(roomDescriptionField);
			addRoomPanelRow2.add(roomSubmitBtn);

			
		}
		JTextField roomNameField,roomDescriptionField;
		JButton roomSubmitBtn;
	}
	static class AddTimeTab extends JPanel{
		JCheckBox[] dayCheckBoxes=new JCheckBox[7];
		JSpinner syear,smonth,sday,eyear,emonth,eday,shr,smin,ehr,emin;
		JDatePicker start,end;
		RoomSelectorComponent roomSel=new RoomSelectorComponent();
		
		ButtonGroup roomActionGrp;
		AddTimeTab(){
		
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			JPanel roomSelectorPanel=new JPanel();
			add(roomSelectorPanel);
			
			roomSelectorPanel.add(new JLabel("Room:"));
			roomSelectorPanel.add(roomSel);
			roomSelectorPanel.add(new JLabel("Action:"));
			
			JRadioButton roomActionAddAvail=new JRadioButton("add availability");
			JRadioButton roomActionBlackOut=new JRadioButton("blackout");
			roomActionGrp=new ButtonGroup();
			roomActionGrp.add(roomActionAddAvail);
			roomActionGrp.add(roomActionBlackOut);
			roomSelectorPanel.add(roomActionAddAvail);
			roomSelectorPanel.add(roomActionBlackOut);

			JPanel dayCheckPanel=new JPanel();
			add(dayCheckPanel);
			for(int i=0;i<7;i++) {
				JLabel l=new JLabel(Utils.days[i]);
				dayCheckPanel.add(l);
				dayCheckBoxes[i]=new JCheckBox();
				dayCheckPanel.add(dayCheckBoxes[i]);
			}
			
			/*syear=new JSpinner(new SpinnerNumberModel(2017,1900,3000,1));
			eyear=new JSpinner(new SpinnerNumberModel(2018,1900,3000,1));
			smonth=new JSpinner(new SpinnerNumberModel(1,1,12,1));
			emonth=new JSpinner(new SpinnerNumberModel(1,1,12,1));
			sday=new JSpinner(new SpinnerNumberModel(1,1,31,1));
			eday=new JSpinner(new SpinnerNumberModel(1,1,31,1));*/
			shr=new JSpinner(new SpinnerNumberModel(9,0,23,1));
			ehr=new JSpinner(new SpinnerNumberModel(10,0,23,1));
			smin=new JSpinner(new SpinnerNumberModel(0,0,59,1));
			emin=new JSpinner(new SpinnerNumberModel(0,0,59,1));
		
			JPanel dtPanel1=new JPanel();
			JPanel dtPanel2=new JPanel();
			JPanel dtPanel3=new JPanel();
			JPanel dtPanel4=new JPanel();
			add(dtPanel1);
			add(dtPanel2);
			add(dtPanel3);
			add(dtPanel4);
			
			dtPanel1.add(new JLabel("date range:"));
			start=new JDatePicker(new Date(2017,01,01));
			end=new JDatePicker(new Date(2018,01,01));
			
			start=new JDatePicker(new Date(2018,01,01));
			dtPanel1.add(start);
			dtPanel1.add(new JLabel("to"));
			dtPanel1.add(end);
			
			
			dtPanel3.add(new JLabel("time range (HH-MM 24 hr clock)"));
			dtPanel3.add(shr);
			dtPanel3.add(smin);
			dtPanel3.add(new JLabel(" to "));
			dtPanel3.add(ehr);
			dtPanel3.add(emin);
			
			JButton roomTimeBtn=new JButton("enter period");
			roomTimeBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					LocalDate sdate=Utils.pickerToLocalDate(start);
					LocalDate edate=Utils.pickerToLocalDate(end);

					LocalTime stime=LocalTime.of((int)shr.getValue(), (int)smin.getValue());
					LocalTime etime=LocalTime.of((int)ehr.getValue(), (int)emin.getValue());
					System.out.println(edate+" "+sdate);
					DayMask dm=new DayMask(false);
					for(int i=0;i<7;i++) {
						if(dayCheckBoxes[i].isSelected()) {
							dm.setDay(i);
						}
					}
					TimePeriod tp=new TimePeriod(sdate, edate, stime, etime,dm,Period.WEEKLY);
					Space s=roomSel.getRoom();
					if(roomActionAddAvail.isSelected()) {
						Controller.INSTANCE.addTimePeriod(s, tp);
					}else if(roomActionBlackOut.isSelected()) {
						Controller.INSTANCE.addBlackOut(s, tp);
					}
				}
			});
			dtPanel4.add(roomTimeBtn);
		}
	}
	static class ConsoleTab extends JPanel{
		JTextArea consoleOutput;
		ConsoleTab(){
			setLayout(new BorderLayout());
			consoleOutput=new JTextArea();
			Observer consoleOutputUpdater=new Observer() {
				public void update() {
					consoleOutput.setText(MasterSchedule.getInstance().toString()+RequestQueue.getInstance().toString());
				}
			};
			MasterSchedule.getInstance().subscribe(consoleOutputUpdater);
			RequestQueue.getInstance().subscribe(consoleOutputUpdater);

			consoleOutputUpdater.update();
			
			consoleOutput.setLineWrap(true);
			add(new JScrollPane(consoleOutput));

		}
	}
	static class EditTimesTab extends JPanel{
		RoomSelectorComponent roomSel=new RoomSelectorComponent();
		
		EditTimesTab(){
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			JPanel top=new JPanel();
			top.add(roomSel);
			add(top);
			JPanel bottom=new JPanel();
			bottom.setLayout(new BoxLayout(bottom,BoxLayout.Y_AXIS));
			add(bottom);

			Observer updater=new Observer() {
				public void update() {
					bottom.removeAll();
					Space s=roomSel.getRoom();
					if(s==null)return;//no room selected
					System.out.println("drawing list for "+s);
					for(TimePeriod t:s.schedule.availabilities) {
						JPanel row=new JPanel();
						row.add(new JLabel(t.toString()));
						JButton remove=new JButton("delete");
						remove.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								System.out.println("deleting time"+s+" "+t);
								Controller.INSTANCE.removeTimePeriod(s, t);
							}
						});
						row.add(remove);
						bottom.add(row);
					}
					updateUI();;
				}
			};
			MasterSchedule.getInstance().subscribe(updater);
			roomSel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("changing room selection");

					updater.update();
				}
			});
			updater.update();
		}
	}
 	static class RoomRequestLineComponent extends JPanel{
 		RoomSelectorComponent rsc=new RoomSelectorComponent();
 		JSpinner shr,ehr,smin,emin;
 		JDatePicker sdate,edate;
 		JComboBox PeriodSel=new JComboBox();
 		RoomRequestLineComponent(int i){
 			for(Period P:TimePeriod.Period.values()) {
 				PeriodSel.addItem(P);
 			}
			sdate=new JDatePicker(new Date(2017,1,1));
			edate=new JDatePicker(new Date(2018,1,1));
			shr=new JSpinner(new SpinnerNumberModel(9,0,23,1));
			ehr=new JSpinner(new SpinnerNumberModel(10,0,23,1));
			smin=new JSpinner(new SpinnerNumberModel(0,0,59,1));
			emin=new JSpinner(new SpinnerNumberModel(0,0,59,1));
			
			//JPanel dtPanel1=new JPanel();
//			JPanel dtPanel2=new JPanel();
//			JPanel dtPanel3=new JPanel();
//			JPanel dtPanel4=new JPanel();
//			add(dtPanel1);
//			add(dtPanel2);
//			add(dtPanel3);
//			add(dtPanel4);
			add(new JLabel("#"+i+":"));
			add(PeriodSel);
			add(new JLabel("start date"));
			add(sdate);
			
			add(new JLabel("to"));
			add(edate);
			
			add(new JLabel("HH-MM"));
			add(shr);
			add(smin);
			add(new JLabel("to")); 
			add(ehr);
			add(emin);
 			
 		}
 	}
	class RequestRoomTab extends JPanel{
 		ArrayList<RoomRequestLineComponent> priorities=new ArrayList();;
 		JTextField name=new JTextField(10);
 		JTextField description=new JTextField(20);
 		RoomSelectorComponent rsc=new RoomSelectorComponent();
 		RequestRoomTab(){
 			setLayout(new BorderLayout());
 			JPanel infoPanel=new JPanel();
 			infoPanel.add(rsc);
 			infoPanel.add(new JLabel("event name:"));
 			infoPanel.add(name);
 			infoPanel.add(new JLabel("description:"));
 			infoPanel.add(description);
 			add(infoPanel,BorderLayout.NORTH);
 			JPanel inner=new JPanel();
 			inner.setLayout(new BoxLayout(inner,BoxLayout.Y_AXIS));
 			class RowAdder implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent e) {
					int i=priorities.size();
					RoomRequestLineComponent rrlc = new RoomRequestLineComponent(i);
	 				priorities.add(rrlc);
	 				inner.add(rrlc);
	 				//refit();
	 				//updateUI();
				}
 				
 			}
 			JPanel lower=new JPanel();
 			add(new JScrollPane(inner),BorderLayout.CENTER);
 			add(lower,BorderLayout.SOUTH);
 			
 			JButton addPriority=new JButton("define more alternatives");
 			JButton submitbtn=new JButton("submit");

 			lower.add(addPriority);
 			lower.add(submitbtn);
 			submitbtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					String evtName=name.getText();
					String desc=description.getText();
					ArrayList<TimePeriod> al=new ArrayList(priorities.size());
					Space room=rsc.getRoom();
					for(RoomRequestLineComponent rrlc:priorities) {
						LocalTime startTime=LocalTime.of((int)rrlc.shr.getValue(), (int)rrlc.smin.getValue());
						LocalTime endTime=LocalTime.of((int)rrlc.shr.getValue(), (int)rrlc.smin.getValue());
						
						LocalDate startDate=Utils.pickerToLocalDate(rrlc.sdate);
						LocalDate endDate=Utils.pickerToLocalDate(rrlc.edate);
						DayMask days=new DayMask(false);
						days.setDay(startDate.getDayOfWeek().getValue());
						TimePeriod.Period period=(Period) rrlc.PeriodSel.getSelectedItem();
						TimePeriod tp=new TimePeriod(startDate,endDate,startTime,endTime,new DayMask(true),period);
						al.add(tp);
					}
					Request r=new Request(evtName,desc,room,al);
					System.out.println("adding request");
					RequestQueue.getInstance().add(r);
				}
 				
 			});
 			RowAdder ra=new RowAdder();
 			addPriority.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("HELLO!");
					ra.actionPerformed(null);
				}
 				
 			});
 			ra.actionPerformed(null);
 		}
 	}
	public TestingFrame() {
		super("Schedule Creation");
		setPreferredSize(new Dimension(1024,768));
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {//write to db on exit
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.out.println("saving the database...\nsched:"+MasterSchedule.getInstance().toString());
        		try {
        			ObjectOutputStream objectWriter=new ObjectOutputStream(new FileOutputStream("masterschedule.data"));
        			objectWriter.writeObject(MasterSchedule.getInstance());
        			ObjectOutputStream objectWriter1=new ObjectOutputStream(new FileOutputStream("requestqueue.data"));
        			objectWriter1.writeObject(RequestQueue.getInstance());
        			objectWriter.close();
        			objectWriter1.close();
        		} catch (Exception e1) {
        			e1.printStackTrace();
        		}
			}
        });
		
		
		this.add(tabs);
		tabs.addTab("Room Creation", new AddRoomTab());
		tabs.addTab("add times", new AddTimeTab());
		tabs.addTab("display", new ConsoleTab());
		tabs.addTab("remove times", new EditTimesTab());
		tabs.addTab("Rquest", new RequestRoomTab());
		this.pack();
		//System.out.println(displayPanel.getSize());
		//System.out.println(consoleOutput.getSize());
		this.setVisible(true);
		
	}
	
}