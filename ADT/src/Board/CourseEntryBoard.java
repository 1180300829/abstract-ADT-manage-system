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
import javax.swing.table.DefaultTableModel;

import EntryState.CourseState;
import PlanningEntry.CourseEntry;
import Resource.Teacher;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

public class CourseEntryBoard extends JFrame {

	private JPanel contentPane;
	private JTable table;
    private Timer time;

    private String classroomlocation;  //��board���λ���ַ���
	private List<CourseEntry<Teacher>> allentry=new ArrayList<>();  //����CourseEntry��һ������
	private Calendar nowtime; //��ǰʱ��
	
	// mutability��
 	// Abstraction function:
 	// AF(classroomlocation)=��ǰλ��
 	// AF(allentry)=��λ�����мƻ���
	// AF(nowtime)=��ǰʱ��
 	// Representation invariant:
 	// ʱ��ת�ַ����������׳��쳣
 	// Safety from rep exposure:
 	// ��classroomlocation,allentry,nowtime����Ϊprivate
    
	/**
	 * ���췽��
	 */
	public CourseEntryBoard() {
	}
	
	/**
	 * ����board��λ��
	 * @param mm ��board���λ���ַ���
	 */
	public void setclassroomlocation(String mm) {
		this.classroomlocation=mm;
	}
	
	/**
	 * ��CourseEntry��һ�������ҵ���classroomlocationλ����ͬ��CourseEntry������ÿ���ƻ������ʼʱ������
	 * @param courselist �������CourseEntry����
	 */
	public void getsortallentry(List<CourseEntry<Teacher>> courselist) {
		for(int i=0;i<courselist.size();i++) {
			allentry.add(courselist.get(i).clone());
		}
		Iterator<CourseEntry<Teacher>> iterator=allentry.iterator();
		while(iterator.hasNext()) {
			CourseEntry<Teacher> pe=iterator.next();
			if(!(pe.getlocations().getlocationname().equals(classroomlocation))) {
				iterator.remove();
			}
		}
		Collections.sort(allentry,new Comparator<CourseEntry<Teacher>>() {

			@Override
			public int compare(CourseEntry<Teacher> o1, CourseEntry<Teacher> o2) {
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
	 * ����frame������CourseEntry���ϵĸ�����Ϣ
	 * @throws ParseException 
	 */
	public List<CourseEntry<Teacher>> createCourseEntryBoard() throws ParseException {
	
		setTitle("����ռ�������");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 630, 500);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 480);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");   
		time = new Timer(1000,new ActionListener() {   
		 
		public void actionPerformed(ActionEvent arg0) {  
			lblNewLabel.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+","+classroomlocation);
		}  
		});  
		time.start();   
		lblNewLabel.setBounds(0, 0, 600, 30);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 60, 600, 250);
		panel.add(scrollPane);
		
		int i,j;
		CourseEntry<Teacher> course;
			
		String[][] biao=new String[100][4];
		
		nowtime = Calendar.getInstance();  //��ǰʱ��
		String str = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(nowtime.getTime()); 
	    nowtime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str));
	    Calendar begintime,endtime;

	    
		Iterator<CourseEntry<Teacher>> iterator=allentry.iterator();
		while(iterator.hasNext()) {
			CourseEntry<Teacher> pe=iterator.next();
			begintime=pe.gettimeslot().getbegintime();
			if(!((begintime.get(Calendar.YEAR)==(nowtime.get(Calendar.YEAR)))&&
					(begintime.get(Calendar.MONTH)==nowtime.get(Calendar.MONTH))&&
					(begintime.get(Calendar.DAY_OF_MONTH)==nowtime.get(Calendar.DAY_OF_MONTH)))) {
				iterator.remove();
			}
		}
		
		for(i=0;i<100;i++) {
		    for(j=0;j<4;j++) {
				biao[i][j]=null;
			}
		}
		
		
		for(i=0;i<allentry.size();i++) {
			course=allentry.get(i);
			begintime=course.gettimeslot().getbegintime();
			endtime=course.gettimeslot().getendtime();
			//if(course!=null) {
				biao[i][0]=Integer.toString(begintime.get(Calendar.HOUR_OF_DAY))+":"
			            +Integer.toString(begintime.get(Calendar.MINUTE))+"-"+
						Integer.toString(endtime.get(Calendar.HOUR_OF_DAY))
			            +":"+Integer.toString(endtime.get(Calendar.MINUTE));
				biao[i][1]=(String) course.getplanningentryname();
				if(course.getresource()==null) {
					 biao[i][2]="δ������ʦ��Դ";
				}
				else {
					 biao[i][2]=course.getresource().getteachername();
				}
			    biao[i][3]=((CourseState)course.getcurrentstate()).getcoursestate();
			//}
		}
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			biao,
			new String[] {
				"�γ�ʱ��", "�γ���", "��ʦ", "״̬"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		scrollPane.setViewportView(table);
	
		return allentry;
	}	
}

