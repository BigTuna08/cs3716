import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

class TestingFrame extends JFrame{
	JTabbedPane tabs;
	//panel 1: room creation
	JPanel addRoomPanel;
	JTextField roomNameField,roomDescriptionField;
	JButton roomSubmitBtn;
	//panel 2: time period creation
	JPanel timePeriodCreation;
	JCheckBox[] dayCheckBoxes=new JCheckBox[7];
	JSpinner syear,smonth,sday,eyear,emonth,eday,shr,smin,ehr,emin;
	JComboBox roomSelector;
	ButtonGroup roomActionGrp;
	
	//panel 3 display
	JTextArea consoleOutput;
	
	JPanel main=new JPanel();
	public TestingFrame() {
		super("Schedule Creation");
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
        			objectWriter.close();
        		} catch (Exception e1) {
        			e1.printStackTrace();
        		}
			}
        });
		
		JLabel label = new JLabel("Hello World");
		this.add(label);
		tabs=new JTabbedPane();
		this.add(tabs);
		//panel 1
		addRoomPanel=new JPanel();
		addRoomPanel.setLayout(new BoxLayout(addRoomPanel, BoxLayout.Y_AXIS));
		JPanel addRoomPanelRow1=new JPanel();
		JPanel addRoomPanelRow2=new JPanel();
		addRoomPanel.add(addRoomPanelRow1);
		addRoomPanel.add(addRoomPanelRow2);
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

		tabs.addTab("Room Creation", addRoomPanel);
		
		//panel 2
		
		timePeriodCreation=new JPanel();
		timePeriodCreation.setLayout(new BoxLayout(timePeriodCreation, BoxLayout.Y_AXIS));
		JPanel roomSelectorPanel=new JPanel();
		timePeriodCreation.add(roomSelectorPanel);
		class SpaceWrapper{
			public Space s;
			public SpaceWrapper(Space sp) {
				s=sp;
			}
			public String toString() {
				return s.name;
			}
		}
		roomSelector=new JComboBox<SpaceWrapper>();

		Observer roomSelectorUpdater=new Observer() {
			public void update() {
				roomSelector.removeAllItems();
				for(Space s:MasterSchedule.getInstance().getSpaces()) {
					roomSelector.addItem(new SpaceWrapper(s));
				}
			}
		};
		roomSelectorUpdater.update();
		MasterSchedule.getInstance().subscribe(roomSelectorUpdater);
		roomSelectorPanel.add(new JLabel("Room:"));
		roomSelectorPanel.add(roomSelector);
		roomSelectorPanel.add(new JLabel("Action:"));
		
		JRadioButton roomActionAddAvail=new JRadioButton("add availability");
		JRadioButton roomActionBlackOut=new JRadioButton("blackout");
		roomActionGrp=new ButtonGroup();
		roomActionGrp.add(roomActionAddAvail);
		roomActionGrp.add(roomActionBlackOut);
		roomSelectorPanel.add(roomActionAddAvail);
		roomSelectorPanel.add(roomActionBlackOut);

		JPanel dayCheckPanel=new JPanel();
		timePeriodCreation.add(dayCheckPanel);
		for(int i=0;i<7;i++) {
			JLabel l=new JLabel(Utils.days[i]);
			dayCheckPanel.add(l);
			dayCheckBoxes[i]=new JCheckBox();
			dayCheckPanel.add(dayCheckBoxes[i]);
		}
		
		SpinnerNumberModel yearModel=new SpinnerNumberModel(2017,1900,3000,1);
		SpinnerNumberModel monthModel=new SpinnerNumberModel(1,1,12,1);
		//TODO update dayModel with  month change
		SpinnerNumberModel dayModel=new SpinnerNumberModel(1,1,31,1);
		SpinnerNumberModel minuteModel=new SpinnerNumberModel(0,0,59,1);
		SpinnerNumberModel hourModel=new SpinnerNumberModel(9,0,23,1);

		syear=new JSpinner(yearModel);
		eyear=new JSpinner(yearModel);
		smonth=new JSpinner(monthModel);
		emonth=new JSpinner(monthModel);
		sday=new JSpinner(dayModel);
		eday=new JSpinner(dayModel);
		shr=new JSpinner(hourModel);
		ehr=new JSpinner(hourModel);
		smin=new JSpinner(minuteModel);
		emin=new JSpinner(minuteModel);
	
		JPanel dtPanel1=new JPanel();
		JPanel dtPanel2=new JPanel();
		JPanel dtPanel3=new JPanel();
		JPanel dtPanel4=new JPanel();
		timePeriodCreation.add(dtPanel1);
		timePeriodCreation.add(dtPanel2);
		timePeriodCreation.add(dtPanel3);
		timePeriodCreation.add(dtPanel4);
		
		dtPanel1.add(new JLabel("start date (YYYY-MM-DD)"));
		dtPanel1.add(syear);
		dtPanel1.add(smonth);
		dtPanel1.add(sday);
		
		dtPanel2.add(new JLabel("end date (YYYY-MM-DD)"));
		dtPanel2.add(eyear);
		dtPanel2.add(emonth);
		dtPanel2.add(eday);
		
		dtPanel3.add(new JLabel("time range (HH-MM 24 hr clock)"));
		dtPanel3.add(shr);
		dtPanel3.add(smin);
		dtPanel3.add(new JLabel(" to "));
		dtPanel3.add(ehr);
		dtPanel3.add(emin);
		
		JButton roomTimeBtn=new JButton("enter period");
		roomTimeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate sdate=LocalDate.of((int)syear.getValue(), (int)smonth.getValue(), (int)sday.getValue());
				LocalDate edate=LocalDate.of((int)eyear.getValue(), (int)emonth.getValue(), (int)eday.getValue());
				LocalTime stime=LocalTime.of((int)shr.getValue(), (int)smin.getValue());
				LocalTime etime=LocalTime.of((int)ehr.getValue(), (int)emin.getValue());
				DayMask dm=new DayMask(false);
				for(int i=0;i<7;i++) {
					if(dayCheckBoxes[i].isSelected()) {
						dm.setDay(i);
					}
				}
				TimePeriod tp=new TimePeriod(sdate, edate, stime, etime,dm);
				Space s=((SpaceWrapper)roomSelector.getSelectedItem()).s;
				if(roomActionAddAvail.isSelected()) {
					Controller.INSTANCE.addTimePeriod(s, tp);
				}else if(roomActionBlackOut.isSelected()) {
					Controller.INSTANCE.addBlackOut(s, tp);
				}
			}
		});
		dtPanel4.add(roomTimeBtn);
		tabs.add("add times", timePeriodCreation);
				
		//panel 3
		consoleOutput=new JTextArea();
		Observer consoleOutputUpdater=new Observer() {
			public void update() {
				consoleOutput.setText(MasterSchedule.getInstance().toString());
			}
		};
		MasterSchedule.getInstance().subscribe(consoleOutputUpdater);
		consoleOutputUpdater.update();
		
		JPanel displayPanel=new JPanel();
		displayPanel.add(consoleOutput);
		tabs.add("display", displayPanel);
		
		this.pack();
		this.setVisible(true);
		
	}
	
}