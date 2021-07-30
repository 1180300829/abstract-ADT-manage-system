package Board;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import EntryState.FlightState;
import PlanningEntry.FlightEntry;
import Resource.Flight;

public class FlightEntryBoard extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private Timer time;

	private String airportlocation;  //��board���λ���ַ���
	private List<FlightEntry<Flight>> comeentry=new ArrayList<>(); //����ִ��FlightEntry��һ������
	private List<FlightEntry<Flight>> toentry=new ArrayList<>(); //������ɵ�FlightEntry��һ������
	private Calendar nowtime; //��ǰʱ��
	
	// mutability��
	// Abstraction function:
	// AF(airportlocation)=��ǰλ��
	// AF(comeentry)=��λ�����еִ�ƻ���
	// AF(toentry)=��λ��������ɼƻ���
	// AF(nowtime)=��ǰʱ��
	// Representation invariant:
	// ʱ��ת�ַ����������׳��쳣
	// Safety from rep exposure:
	// ��airportlocation,comeentry,toentry,nowtime����Ϊprivate

	/**
	 * ���췽��
	 */
	public FlightEntryBoard() {
	}
	
	/**
	 * ����board��λ��
	 * @param mm ��board���λ���ַ���
	 */
	public void setairportlocation(String mm) {
		this.airportlocation=mm;
	}
	
	/**
	 * ��FlightEntry��һ�������ҵ���airportlocationλ����ͬ��FlightEntry������ÿ���ƻ���ĵִ�ʱ������
	 * @param courselist �������FlightEntry����
	 */
	public void getsortcomeentry(List<FlightEntry<Flight>> flightlist) {
		for(int i=0;i<flightlist.size();i++) {
			comeentry.add(flightlist.get(i).clone());
		}
		Iterator<FlightEntry<Flight>> iterator=comeentry.iterator();
		while(iterator.hasNext()) {
			FlightEntry<Flight> pe=iterator.next();
			if(!(pe.gettolocation().getlocationname().equals(airportlocation))) {
				iterator.remove();
			}
		}
		Collections.sort(comeentry,new Comparator<FlightEntry<Flight>>() {

			@Override
			public int compare(FlightEntry<Flight> o1, FlightEntry<Flight> o2) {
				if(o1.gettimeslot().getendtime().compareTo(o2.gettimeslot().getendtime())>0) {
					return 1;
				}
				else if(o1.gettimeslot().getendtime().compareTo(o2.gettimeslot().getendtime())==0) {
					return 0;
				}
				return -1;
			}
		});
	}
	
	/**
	 * ��FlightEntry��һ�������ҵ���airportlocationλ����ͬ��FlightEntry������ÿ���ƻ�������ʱ������
	 * @param courselist �������FlightEntry����
	 */
	public void getsorttoentry(List<FlightEntry<Flight>> flightlist) {
		for(int i=0;i<flightlist.size();i++) {
			toentry.add(flightlist.get(i).clone());
		}
		Iterator<FlightEntry<Flight>> iterator=toentry.iterator();
		while(iterator.hasNext()) {
			FlightEntry<Flight> pe=iterator.next();
			if(!(pe.getfromlocation().getlocationname().equals(airportlocation))) {
				iterator.remove();
			}
		}
		Collections.sort(toentry,new Comparator<FlightEntry<Flight>>() {
			@Override
			public int compare(FlightEntry<Flight> o1, FlightEntry<Flight> o2) {
				if(o1.gettimeslot().getbegintime().compareTo(o2.gettimeslot().getbegintime())>0) {
					return 1;
				}
				else if(o1.gettimeslot().getbegintime().compareTo(o2.gettimeslot().getbegintime())==0) {
					return 0;
				}
				return -1;
			}
		});
	}
	
	/**
	 * ��ʾ��ǰboard
	 */
	public void visualize() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * ����frame������FlightEntry���ϵĸ�����Ϣ
	 * @throws ParseException 
	 */
	public List<FlightEntry<Flight>> createFlightEntryBoard() throws ParseException  {
		setTitle("����״̬��ʾ��");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 630, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 730);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");   
		time = new Timer(1000,new ActionListener() {   
		 
		public void actionPerformed(ActionEvent arg0) {  
			lblNewLabel.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"(����ʱ��)"+","+airportlocation+"����");
		}  
		});  
		time.start();   
		lblNewLabel.setBounds(0, 0, 600, 30);
		panel.add(lblNewLabel);
		
		nowtime = Calendar.getInstance();  //��ǰʱ��
		String str = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(nowtime.getTime()); 
	    nowtime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str));
		
		
		int i,j;
		FlightEntry<Flight> flight;
		String[][] comebiao=new String[100][4];
		String[][] tobiao=new String[100][4];
		
		Iterator<FlightEntry<Flight>> iterator=comeentry.iterator();
		while(iterator.hasNext()) {
			FlightEntry<Flight> pe=iterator.next();
			if(((pe.gettimeslot().getendtime().getTime().getTime()-nowtime.getTime().getTime())/1000)>3600
					||((nowtime.getTime().getTime()-pe.gettimeslot().getendtime().getTime().getTime())/1000)>3600) {
				iterator.remove();
			}
		}
			
		for(i=0;i<100;i++) {
		    for(j=0;j<4;j++) {
				comebiao[i][j]=null;
			}
		}
		
		for(i=0;i<comeentry.size();i++) {
			flight=comeentry.get(i);
			if(flight!=null) {
				comebiao[i][0]=(new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(flight.gettimeslot().getendtime().getTime());
				comebiao[i][1]=(String) flight.getplanningentryname();
			    comebiao[i][2]=flight.getfromlocation().getlocationname()+"��"+flight.gettolocation().getlocationname();
			    comebiao[i][3]=((FlightState)flight.getcurrentstate()).getflightstate();
			}
		}
		
		JLabel lblNewLabel_1 = new JLabel("                                  �ִﺽ��");
		lblNewLabel_1.setBounds(0, 30, 600, 30);
		panel.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 60, 600, 200);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			comebiao,
			new String[] {
				"�ƻ�����ʱ��", "�����", "��ʼ���յ�", "״̬"
			}
		));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2 = new JLabel("                                  ��������");
		lblNewLabel_2.setBounds(0, 280, 600, 30);
		panel.add(lblNewLabel_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 310, 600, 200);
		panel.add(scrollPane_1);
		
		Iterator<FlightEntry<Flight>> iterator1=toentry.iterator();
		while(iterator1.hasNext()) {
			FlightEntry<Flight> pe=iterator1.next();
			if(((pe.gettimeslot().getbegintime().getTime().getTime()-nowtime.getTime().getTime())/1000)>3600||((nowtime.getTime().getTime()-pe.gettimeslot().getbegintime().getTime().getTime())/1000)>3600) {
				iterator1.remove();
			}
		}
			
		for(i=0;i<100;i++) {
		    for(j=0;j<4;j++) {
				tobiao[i][j]=null;
			}
		}
		
		for(i=0;i<toentry.size();i++) {
			flight=toentry.get(i);
			if(flight!=null) {
				tobiao[i][0]=(new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(flight.gettimeslot().getbegintime().getTime());  ;
				tobiao[i][1]=(String) flight.getplanningentryname();
			    tobiao[i][2]=flight.getfromlocation().getlocationname()+"����"+flight.gettolocation().getlocationname();
			    tobiao[i][3]=((FlightState)flight.getcurrentstate()).getflightstate();
			}
		}
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			tobiao,
			new String[] {
					"�ƻ����ʱ��", "�����", "��ʼ��", "״̬"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(150);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(150);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(150);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(150);
		scrollPane_1.setViewportView(table_1);
		
		List<FlightEntry<Flight>> allentry=new ArrayList<>();
		allentry.addAll(comeentry);
		allentry.addAll(toentry);
		return allentry;
	}
}

