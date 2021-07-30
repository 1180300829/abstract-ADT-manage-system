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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import EntryState.TrainState;
import Location.FlightTrainLocation;
import PlanningEntry.TrainEntry;
import Resource.Carriage;

import javax.swing.JLabel;

public class TrainEntryBoard extends JFrame {

	private JPanel contentPane;
	private JTable table,table_1;
	private Timer time;
	
	private FlightTrainLocation railwaylocation;  //��board���λ���ַ���
	private List<TrainEntry<Carriage>> comeentry=new ArrayList<>(); //����ִ��TrainEntry��һ������
	private List<TrainEntry<Carriage>> toentry=new ArrayList<>(); //������ɵ�TrainEntry��һ������
	private Calendar nowtime; //��ǰʱ��
	private String weidu="��γ40��",jingdu="����112��";
	
	// mutability��
	// Abstraction function:
	// AF(railwaylocation)=��ǰλ��
	// AF(comeentry)=��λ�����еִ�ƻ���
	// AF(toentry)=��λ��������ɼƻ���
	// AF(nowtime)=��ǰʱ��
	// Representation invariant:
	// ʱ��ת�ַ����������׳��쳣
	// Safety from rep exposure:
	// ��railwaylocation,comeentry,toentry,nowtime����Ϊprivate

	
	/**
	 * ���췽��
	 */
	public TrainEntryBoard() {
	}
	
	/**
	 * ����board��λ��
	 * @param mm ��board���λ���ַ���
	 */
	public void setrailwaylocation(String mm) {
		this.railwaylocation=new FlightTrainLocation(weidu,jingdu,mm);
	}
	
	/**
	 * ��TrainEntry��һ�������ҵ���railwaylocationλ����ͬ��TrainEntry������ÿ���ƻ���ĵִ�ʱ������
	 * @param courselist �������TrainEntry����
	 */
	public void getsortcomeentry(List<TrainEntry<Carriage>> trainlist) {
		int i;
		for(i=0;i<trainlist.size();i++) {
			comeentry.add(trainlist.get(i).clone());
		}
		Iterator<TrainEntry<Carriage>> iterator=comeentry.iterator();
		while(iterator.hasNext()) {
			TrainEntry<Carriage> pe=iterator.next();
		    if((!pe.getlocations().contains(railwaylocation))||(pe.getlocations().indexOf(railwaylocation)==0)) {  //������entey��Ҫ��ȥ��վΪ��һ��վ��
		    	iterator.remove();
		    }
		}
		Collections.sort(comeentry,new Comparator<TrainEntry<Carriage>>() {

			@Override
			public int compare(TrainEntry<Carriage> o1, TrainEntry<Carriage> o2) {
				if(o1.gettimeslot().get(o1.getlocations().indexOf(railwaylocation)-1).getendtime().compareTo(o2.gettimeslot().get(o2.getlocations().indexOf(railwaylocation)-1).getendtime())>0) {
					return 1;
				}
				else if(o1.gettimeslot().get(o1.getlocations().indexOf(railwaylocation)-1).getendtime().compareTo(o2.gettimeslot().get(o2.getlocations().indexOf(railwaylocation)-1).getendtime())==0) {
					return 0;
				}
				return -1;
			}
		});
	}
	
	/**
	 * ��TrainEntry��һ�������ҵ���railwaylocationλ����ͬ��TrainEntry������ÿ���ƻ�����뿪ʱ������
	 * @param courselist �������TrainEntry����
	 */
	public void getsorttoentry(List<TrainEntry<Carriage>> trainlist) {
		int i;
		for(i=0;i<trainlist.size();i++) {
			toentry.add(trainlist.get(i).clone());
		}
		Iterator<TrainEntry<Carriage>> iterator=toentry.iterator();
		while(iterator.hasNext()) {
			TrainEntry<Carriage> pe=iterator.next();
		    if(!pe.getlocations().contains(railwaylocation)||(pe.getlocations().indexOf(railwaylocation)==pe.getlocations().size()-1)) {  ////��ȥ��entey��Ҫ��ȥ��վΪ���һ��վ��
		    	iterator.remove();
		    }
		}
		Collections.sort(toentry,new Comparator<TrainEntry<Carriage>>() {

			@Override
			public int compare(TrainEntry<Carriage> o1, TrainEntry<Carriage> o2) {
				if(o1.gettimeslot().get(o1.getlocations().indexOf(railwaylocation)).getbegintime().compareTo(o2.gettimeslot().get(o2.getlocations().indexOf(railwaylocation)).getbegintime())>0) {
					return 1;
				}
				else if(o1.gettimeslot().get(o1.getlocations().indexOf(railwaylocation)).getbegintime().compareTo(o2.gettimeslot().get(o2.getlocations().indexOf(railwaylocation)).getbegintime())==0) {
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
	 * ����frame������TrainEntry���ϵĸ�����Ϣ
	 * @throws ParseException 
	 */
	public List<TrainEntry<Carriage>> createTrainEntryBoard() throws ParseException {
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
			lblNewLabel.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"(����ʱ��)"+","+railwaylocation.getlocationname());
		}  
		});  
		time.start();   
		lblNewLabel.setBounds(0, 0, 600, 30);
		panel.add(lblNewLabel);
		
		nowtime = Calendar.getInstance();  //��ǰʱ��
		String str = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(nowtime.getTime()); 
	    nowtime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str));
		
		
		int i,j;
		TrainEntry<Carriage> train;
		String[][] comebiao=new String[100][4];
		String[][] tobiao=new String[100][4];
		
		Iterator<TrainEntry<Carriage>> iterator=comeentry.iterator();
		while(iterator.hasNext()) {
			TrainEntry<Carriage> pe=iterator.next();
			if(((pe.gettimeslot().get(pe.getlocations().indexOf(railwaylocation)-1).getendtime().getTime().getTime()-nowtime.getTime().getTime())/1000)>3600
				||((nowtime.getTime().getTime()-pe.gettimeslot().get(pe.getlocations().indexOf(railwaylocation)-1).getendtime().getTime().getTime())/1000)>3600) {
				iterator.remove();
		    }
		
		}
			
		for(i=0;i<100;i++) {
		    for(j=0;j<4;j++) {
				comebiao[i][j]=null;
			}
		}
		
		for(i=0;i<comeentry.size();i++) {
			train=comeentry.get(i);
			if(train!=null) {
				comebiao[i][0]=(new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(train.gettimeslot().get(train.getlocations().indexOf(railwaylocation)-1).getendtime().getTime());  ;
				comebiao[i][1]=(String) train.getplanningentryname();
			    comebiao[i][2]=train.getlocations().get(0).getlocationname()+"��"+train.getlocations().get(train.getlocations().size()-1).getlocationname();
			    comebiao[i][3]=((TrainState)train.getcurrentstate()).gettrainstate();
			}
		}
		
		JLabel lblNewLabel_1 = new JLabel("                                  �ִﳵ��");
		lblNewLabel_1.setBounds(0, 30, 600, 30);
		panel.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 60, 600, 200);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			comebiao,
			new String[] {
				"�ƻ��ִ�ʱ��", "���κ�", "��ʼ���յ�", "״̬"
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
		
		Iterator<TrainEntry<Carriage>> iterator1=toentry.iterator();
		while(iterator1.hasNext()) {
			TrainEntry<Carriage> lpe=iterator1.next();
			if(((lpe.gettimeslot().get(lpe.getlocations().indexOf(railwaylocation)).getbegintime().getTime().getTime()-nowtime.getTime().getTime())/1000)>3600
				||((nowtime.getTime().getTime()-lpe.gettimeslot().get(lpe.getlocations().indexOf(railwaylocation)).getbegintime().getTime().getTime())/1000)>3600) {
				iterator1.remove();
			}
			
		}
		
		for(i=0;i<100;i++) {
		    for(j=0;j<4;j++) {
				tobiao[i][j]=null;
			}
		}
		
		for(i=0;i<toentry.size();i++) {
			train=toentry.get(i);
			if(train!=null) {
				tobiao[i][0]=(new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(train.gettimeslot().get(train.getlocations().indexOf(railwaylocation)).getbegintime().getTime());  ;
				tobiao[i][1]=(String) train.getplanningentryname();
			    tobiao[i][2]=train.getlocations().get(0).getlocationname()+"��"+train.getlocations().get(train.getlocations().size()-1).getlocationname();
			    tobiao[i][3]=((TrainState)train.getcurrentstate()).gettrainstate();
			}
		}
		
		
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			tobiao,
			new String[] {
					"�ƻ�����ʱ��", "���κ�", "��ʼ���յ�", "״̬"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(150);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(150);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(150);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(150);
		scrollPane_1.setViewportView(table_1);
		
		List<TrainEntry<Carriage>> allentry=new ArrayList<>();
		allentry.addAll(comeentry);
		allentry.addAll(toentry);
		return allentry;
	}
}
