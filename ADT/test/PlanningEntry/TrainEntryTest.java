package PlanningEntry;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Exception.BeginEndTimeException;
import Exception.ConflictTimeException;
import Exception.LessThanZeroException;
import Exception.SameLocationException;
import Exception.SameResourceException;
import Factory.CourseEntryFactory;
import Factory.TrainEntryFactory;
import Location.CourseLocation;
import Location.FlightTrainLocation;
import Location.Location;
import Resource.Carriage;
import Resource.Teacher;
import Timeslot.Timeslot;

public class TrainEntryTest {
	
	/**
	 * �ڲ��Ը���Ĺ������Ѿ�����˶���ί�з����Ĳ���
	 * @throws SameLocationException 
	 */

	/* Testing strategy
	 * ����setlocations����
	 * ����λ���Ƿ��ѱ����û��֣�λ���ѱ����ã�λ��δ������
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void setlocationstest() throws SameLocationException {
		String weidu="��γ40��",jingdu="����112��";
		MultipleLacationEntryImpl a7;
		MultipleSortedResourceEntryImpl<Carriage> b7;
		BlockableEntryImpl c7;
		a7=new MultipleLacationEntryImpl();
		b7=new MultipleSortedResourceEntryImpl<Carriage>();
		c7=new BlockableEntryImpl();
		PlanningEntry origintrain=new TrainEntryFactory().getTrainEntry(a7, b7, c7); //�ù���������������
		TrainEntry<Carriage> train=(TrainEntry<Carriage>)origintrain;
		
		List<Location> alllocation=new ArrayList<>();
		alllocation.add(new FlightTrainLocation(weidu,jingdu,"����"));
		alllocation.add(new FlightTrainLocation(weidu,jingdu,"�人"));
		alllocation.add(new FlightTrainLocation(weidu,jingdu,"�Ͼ�"));
      
		assertEquals(true,train.setlocations(alllocation));
		assertEquals(false,train.setlocations(alllocation));
	}

	/* Testing strategy
	 * ����changelocations����
	 * ���Է���ֵ����
     */
	@Test
	public void changelocationstest() throws SameLocationException {
		String weidu="��γ40��",jingdu="����112��";
		MultipleLacationEntryImpl a7;
		MultipleSortedResourceEntryImpl<Carriage> b7;
		BlockableEntryImpl c7;
		a7=new MultipleLacationEntryImpl();
		b7=new MultipleSortedResourceEntryImpl<Carriage>();
		c7=new BlockableEntryImpl();
		PlanningEntry origintrain=new TrainEntryFactory().getTrainEntry(a7, b7, c7); //�ù���������������
		TrainEntry<Carriage> train=(TrainEntry<Carriage>)origintrain;
		
		List<Location> alllocation=new ArrayList<>();
		alllocation.add(new FlightTrainLocation(weidu,jingdu,"����"));
		alllocation.add(new FlightTrainLocation(weidu,jingdu,"�人"));
		alllocation.add(new FlightTrainLocation(weidu,jingdu,"�Ͼ�"));
		
		train.setlocations(alllocation);
		assertEquals(false,train.changelocations(alllocation));	
	
	}
	
	
	/* Testing strategy
	 * ����getlocations����
     * ���Է���ֵ����
     */
	@Test
	public void getlocationstest() throws SameLocationException {
		String weidu="��γ40��",jingdu="����112��";
		MultipleLacationEntryImpl a7;
		MultipleSortedResourceEntryImpl<Carriage> b7;
		BlockableEntryImpl c7;
		a7=new MultipleLacationEntryImpl();
		b7=new MultipleSortedResourceEntryImpl<Carriage>();
		c7=new BlockableEntryImpl();
		PlanningEntry origintrain=new TrainEntryFactory().getTrainEntry(a7, b7, c7); //�ù���������������
		TrainEntry<Carriage> train=(TrainEntry<Carriage>)origintrain;
		
		List<Location> alllocation=new ArrayList<>();
		alllocation.add(new FlightTrainLocation(weidu,jingdu,"����"));
		alllocation.add(new FlightTrainLocation(weidu,jingdu,"�人"));
		alllocation.add(new FlightTrainLocation(weidu,jingdu,"�Ͼ�"));
		
		train.setlocations(alllocation);
		assertEquals(alllocation,train.getlocations());	
	}
	
	/* Testing strategy
	 * ����setresource����
	 * ������Դ�Ƿ��ѱ����û��֣���Դ�ѱ����ã���Դδ������
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void setresourcetest() throws SameResourceException, LessThanZeroException {
		MultipleLacationEntryImpl a7;
		MultipleSortedResourceEntryImpl<Carriage> b7;
		BlockableEntryImpl c7;
		a7=new MultipleLacationEntryImpl();
		b7=new MultipleSortedResourceEntryImpl<Carriage>();
		c7=new BlockableEntryImpl();
		PlanningEntry origintrain1=new TrainEntryFactory().getTrainEntry(a7, b7, c7); //�ù���������������
		TrainEntry<Carriage> train1=(TrainEntry<Carriage>)origintrain1;
		
		List<Carriage> allcarriage1=new ArrayList<>();
		allcarriage1.add(new Carriage("AS01","������",100,"2011"));
		allcarriage1.add(new Carriage("AS02","������",100,"2011"));
		
		assertEquals(true,train1.setresource(allcarriage1));
		assertEquals(false,train1.setresource(allcarriage1));
	}
	
	/* Testing strategy
	 * ����getresource����
	 * ���Է���ֵ����
     */
	@Test
	public void getresourcetest() throws SameResourceException, LessThanZeroException {
		MultipleLacationEntryImpl a7;
		MultipleSortedResourceEntryImpl<Carriage> b7;
		BlockableEntryImpl c7;
		a7=new MultipleLacationEntryImpl();
		b7=new MultipleSortedResourceEntryImpl<Carriage>();
		c7=new BlockableEntryImpl();
		PlanningEntry origintrain1=new TrainEntryFactory().getTrainEntry(a7, b7, c7); //�ù���������������
		TrainEntry<Carriage> train1=(TrainEntry<Carriage>)origintrain1;
		
		List<Carriage> allcarriage1=new ArrayList<>();
		allcarriage1.add(new Carriage("AS01","������",100,"2011"));
		allcarriage1.add(new Carriage("AS02","������",100,"2011"));
		
		train1.setresource(allcarriage1);
		assertEquals(allcarriage1,train1.getresource());
	}
	
	/* Testing strategy
	 * ����changeresource����
	 * ���ո��ĺ��Ƿ���ԭ�����ظ����֣��ظ������ظ�
	 * ���մ����ĳ����Ƿ���ڻ��֣����ڣ�������
	 * ����ÿ��ȡֵ���£�
     */
	@Test
	public void changeresourcetest() throws SameResourceException, LessThanZeroException {
		MultipleLacationEntryImpl a7;
		MultipleSortedResourceEntryImpl<Carriage> b7;
		BlockableEntryImpl c7;
		a7=new MultipleLacationEntryImpl();
		b7=new MultipleSortedResourceEntryImpl<Carriage>();
		c7=new BlockableEntryImpl();
		PlanningEntry origintrain1=new TrainEntryFactory().getTrainEntry(a7, b7, c7); //�ù���������������
		TrainEntry<Carriage> train1=(TrainEntry<Carriage>)origintrain1;
		
		List<Carriage> allcarriage1=new ArrayList<>();
		allcarriage1.add(new Carriage("AS01","������",100,"2011"));
		allcarriage1.add(new Carriage("AS02","������",100,"2011"));
		
		train1.setresource(allcarriage1);
		assertEquals(false,train1.changeresource(new Carriage("A01","������",100,"2011"), new Carriage("A02","������",100,"2011")));
		assertEquals(false,train1.changeresource(new Carriage("AS01","������",100,"2011"), new Carriage("AS02","������",100,"2011")));
		assertEquals(true,train1.changeresource(new Carriage("AS01","������",100,"2011"), new Carriage("A01","������",100,"2011")));
	}
	
	/* Testing strategy
	 * ����addresource����
	 * ���ӳ����Ƿ��Ѿ����ڻ��֣����ڣ�������
	 * ���մ����ӳ����λ�ã������в�������β��
	 * ����ÿ��ȡֵ���£�
     */
	@Test
	public void addresourcetest() throws SameResourceException, LessThanZeroException {
		MultipleLacationEntryImpl a7;
		MultipleSortedResourceEntryImpl<Carriage> b7;
		BlockableEntryImpl c7;
		a7=new MultipleLacationEntryImpl();
		b7=new MultipleSortedResourceEntryImpl<Carriage>();
		c7=new BlockableEntryImpl();
		PlanningEntry origintrain1=new TrainEntryFactory().getTrainEntry(a7, b7, c7); //�ù���������������
		TrainEntry<Carriage> train1=(TrainEntry<Carriage>)origintrain1;
		
		List<Carriage> allcarriage1=new ArrayList<>();
		allcarriage1.add(new Carriage("AS01","������",100,"2011"));
		allcarriage1.add(new Carriage("AS02","������",100,"2011"));
		
		train1.setresource(allcarriage1);
		assertEquals(false,train1.addresource(new Carriage("AS01","������",100,"2011"), 0));
		assertEquals(true,train1.addresource(new Carriage("A01","������",100,"2011"), 0));
		assertEquals(true,train1.addresource(new Carriage("A02","������",100,"2011"), 3));
	}
	
	/* Testing strategy
	 * ����deleteresource����
	 * ���Ӵ��Ƴ������Ƿ��Ѿ����ڻ��֣����ڣ�������
	 * ����ÿ��ȡֵ���£�
     */
	@Test
	public void deleteresourcetest() throws SameResourceException, LessThanZeroException {
		MultipleLacationEntryImpl a7;
		MultipleSortedResourceEntryImpl<Carriage> b7;
		BlockableEntryImpl c7;
		a7=new MultipleLacationEntryImpl();
		b7=new MultipleSortedResourceEntryImpl<Carriage>();
		c7=new BlockableEntryImpl();
		PlanningEntry origintrain1=new TrainEntryFactory().getTrainEntry(a7, b7, c7); //�ù���������������
		TrainEntry<Carriage> train1=(TrainEntry<Carriage>)origintrain1;
		
		List<Carriage> allcarriage1=new ArrayList<>();
		allcarriage1.add(new Carriage("AS01","������",100,"2011"));
		allcarriage1.add(new Carriage("AS02","������",100,"2011"));
		
		train1.setresource(allcarriage1);
		assertEquals(false,train1.deleteresource(new Carriage("A01","������",100,"2011")));
		assertEquals(true,train1.deleteresource(new Carriage("AS02","������",100,"2011")));
	}
	
	
	/* Testing strategy
	 * ����whetherblockable����
	 * ���Է���ֵ����
     */
	@Test
	public void whetherblockabletest() {
		MultipleLacationEntryImpl a7;
		MultipleSortedResourceEntryImpl<Carriage> b7;
		BlockableEntryImpl c7;
		a7=new MultipleLacationEntryImpl();
		b7=new MultipleSortedResourceEntryImpl<Carriage>();
		c7=new BlockableEntryImpl();
		PlanningEntry origintrain1=new TrainEntryFactory().getTrainEntry(a7, b7, c7); //�ù���������������
		TrainEntry<Carriage> train1=(TrainEntry<Carriage>)origintrain1;
		assertEquals(true,train1.whetherblockable());
	}
	
	/* Testing strategy
	 * ����settimeslot����
	 * ����ʱ���Ƿ��ѱ����û��֣��ѱ����ã�δ������
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void settimeslottest() throws ParseException, BeginEndTimeException, ConflictTimeException{
		MultipleLacationEntryImpl a7;
		MultipleSortedResourceEntryImpl<Carriage> b7;
		BlockableEntryImpl c7;
		a7=new MultipleLacationEntryImpl();
		b7=new MultipleSortedResourceEntryImpl<Carriage>();
		c7=new BlockableEntryImpl();
		PlanningEntry origintrain1=new TrainEntryFactory().getTrainEntry(a7, b7, c7); //�ù���������������
		TrainEntry<Carriage> train1=(TrainEntry<Carriage>)origintrain1;
		
		List<Timeslot> alltime1=new ArrayList<>();
		alltime1.add(new Timeslot("2020-01-01 16:45","2020-01-01 18:30"));
		alltime1.add(new Timeslot("2020-01-01 18:40","2020-01-01 20:30"));
	
		assertEquals(true,train1.settimeslot(alltime1));
		assertEquals(false,train1.settimeslot(alltime1));
	}
	
	/* Testing strategy
	 * ����gettimeslot����
	 * ���Է���ֵ����
     */
	@Test
	public void gettimeslottest() throws ParseException, BeginEndTimeException, ConflictTimeException{
		MultipleLacationEntryImpl a7;
		MultipleSortedResourceEntryImpl<Carriage> b7;
		BlockableEntryImpl c7;
		a7=new MultipleLacationEntryImpl();
		b7=new MultipleSortedResourceEntryImpl<Carriage>();
		c7=new BlockableEntryImpl();
		PlanningEntry origintrain1=new TrainEntryFactory().getTrainEntry(a7, b7, c7); //�ù���������������
		TrainEntry<Carriage> train1=(TrainEntry<Carriage>)origintrain1;
		
		List<Timeslot> alltime1=new ArrayList<>();
		alltime1.add(new Timeslot("2020-01-01 16:45","2020-01-01 18:30"));
		alltime1.add(new Timeslot("2020-01-01 18:40","2020-01-01 20:30"));
		train1.settimeslot(alltime1);
	
		assertEquals(alltime1,train1.gettimeslot());
	}
	
	/* Testing strategy
	 * ����trainblocklot����
	 * ����վ���������֣�������2������2
	 * �����Ƿ����м�վ�㻮�֣����м�վ�㣬�����м�վ��
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void trainblocktest() throws SameLocationException {
		String weidu="��γ40��",jingdu="����112��";
		MultipleLacationEntryImpl a7;
		MultipleSortedResourceEntryImpl<Carriage> b7;
		BlockableEntryImpl c7;
		a7=new MultipleLacationEntryImpl();
		b7=new MultipleSortedResourceEntryImpl<Carriage>();
		c7=new BlockableEntryImpl();
		PlanningEntry origintrain1=new TrainEntryFactory().getTrainEntry(a7, b7, c7); //�ù���������������
		TrainEntry<Carriage> train1=(TrainEntry<Carriage>)origintrain1;
		
		List<Location> alllocation1=new ArrayList<>();
		alllocation1.add(new FlightTrainLocation(weidu,jingdu,"����"));
		alllocation1.add(new FlightTrainLocation(weidu,jingdu,"�人"));
		train1.setlocations(alllocation1);
		
		assertEquals(-1,train1.trainblock("�人"));
		
		MultipleLacationEntryImpl a8;
		MultipleSortedResourceEntryImpl<Carriage> b8;
		BlockableEntryImpl c8;
		a8=new MultipleLacationEntryImpl();
		b8=new MultipleSortedResourceEntryImpl<Carriage>();
		c8=new BlockableEntryImpl();
		PlanningEntry origintrain2=new TrainEntryFactory().getTrainEntry(a8, b8, c8); //�ù���������������
		TrainEntry<Carriage> train2=(TrainEntry<Carriage>)origintrain2;
		
		List<Location> alllocation2=new ArrayList<>();
		alllocation2.add(new FlightTrainLocation(weidu,jingdu,"����"));
		alllocation2.add(new FlightTrainLocation(weidu,jingdu,"�人"));
		alllocation2.add(new FlightTrainLocation(weidu,jingdu,"�Ͼ�"));
		train2.setlocations(alllocation2);
		
		assertEquals(-1,train2.trainblock("����"));
		assertEquals(-1,train2.trainblock("�Ͼ�"));
		assertEquals(1,train2.trainblock("�人"));
	}
	
	/* Testing strategy
	 * ����compareTo����
	 * ������ʼʱ���С���֣����ڣ����ڣ�С��
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void compareTotest() throws ParseException, BeginEndTimeException, ConflictTimeException{
		MultipleLacationEntryImpl a7;
		MultipleSortedResourceEntryImpl<Carriage> b7;
		BlockableEntryImpl c7;
		a7=new MultipleLacationEntryImpl();
		b7=new MultipleSortedResourceEntryImpl<Carriage>();
		c7=new BlockableEntryImpl();
		PlanningEntry origintrain1=new TrainEntryFactory().getTrainEntry(a7, b7, c7); //�ù���������������
		TrainEntry<Carriage> train1=(TrainEntry<Carriage>)origintrain1;
		
		List<Timeslot> alltime1=new ArrayList<>();
		alltime1.add(new Timeslot("2020-01-01 16:45","2020-01-01 18:30"));
		alltime1.add(new Timeslot("2020-01-01 18:40","2020-01-01 20:30"));
		train1.settimeslot(alltime1);
		
		MultipleLacationEntryImpl a8;
		MultipleSortedResourceEntryImpl<Carriage> b8;
		BlockableEntryImpl c8;
		a8=new MultipleLacationEntryImpl();
		b8=new MultipleSortedResourceEntryImpl<Carriage>();
		c8=new BlockableEntryImpl();
		PlanningEntry origintrain2=new TrainEntryFactory().getTrainEntry(a8, b8, c8); //�ù���������������
		TrainEntry<Carriage> train2=(TrainEntry<Carriage>)origintrain2;
		
		List<Timeslot> alltime2=new ArrayList<>();
		alltime2.add(new Timeslot("2020-01-01 14:45","2020-01-01 18:30"));
		alltime2.add(new Timeslot("2020-01-01 18:40","2020-01-01 20:30"));
		train2.settimeslot(alltime2);
		
		MultipleLacationEntryImpl a9;
		MultipleSortedResourceEntryImpl<Carriage> b9;
		BlockableEntryImpl c9;
		a9=new MultipleLacationEntryImpl();
		b9=new MultipleSortedResourceEntryImpl<Carriage>();
		c9=new BlockableEntryImpl();
		PlanningEntry origintrain3=new TrainEntryFactory().getTrainEntry(a9, b9, c9); //�ù���������������
		TrainEntry<Carriage> train3=(TrainEntry<Carriage>)origintrain3;

		List<Timeslot> alltime3=new ArrayList<>();
		alltime3.add(new Timeslot("2020-01-01 14:45","2020-01-01 18:30"));
		alltime3.add(new Timeslot("2020-01-01 18:40","2020-01-01 20:30"));
		train3.settimeslot(alltime3);
		
		assertEquals(1,train1.compareTo(train2));
		assertEquals(0,train2.compareTo(train3));
		assertEquals(-1,train2.compareTo(train1));
	}

}
