package PlanningEntryAPIs;

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
import Factory.FlightEntryFactory;
import Factory.TrainEntryFactory;
import Location.CourseLocation;
import Location.FlightTrainLocation;
import Location.Location;
import PlanningEntry.BlockableEntryImpl;
import PlanningEntry.CommonPlanningEntry;
import PlanningEntry.CourseEntry;
import PlanningEntry.FlightEntry;
import PlanningEntry.MultipleLacationEntryImpl;
import PlanningEntry.MultipleSortedResourceEntryImpl;
import PlanningEntry.NoBlockableEntryImpl;
import PlanningEntry.OneDistinguishResourceEntryImpl;
import PlanningEntry.OneLocationEntryImpl;
import PlanningEntry.PlanningEntry;
import PlanningEntry.TrainEntry;
import PlanningEntry.TwoLocationEntryImpl;
import Resource.Carriage;
import Resource.Flight;
import Resource.Teacher;
import Timeslot.Timeslot;

public class PlanningEntryAPIsTest {
	
	PlanningEntryAPIs myapis=new PlanningEntryAPIs<>();
	
	/**
	 * �ڲ��Ը���Ĺ������Ѿ�����˶���ί�з����Ĳ���
	 * @throws BeginEndTimeException 
	 * @throws SameLocationException 
	 * @throws ConflictTimeException 
	 */

	/* Testing strategy
	 * ����checkLocationConflict����������ί�й�ϵҲ������Strategy1checkLocationConflict���Strategy2checkLocationConflict
	 * ����PlanningEntry�����໮�֣�CourseEntry,FlightEntry,TrainEntry
	 * �����Ƿ����λ�ö�ռ��ͻ���֣�����λ�ö�ռ��ͻ��������λ�ö�ռ��ͻ
	 * ���������ָ��֣�ָ��Ϊ1��ָ��Ϊ2��ָ��Ϊ����
	 * ��������ļ��ϻ��֣�Ϊ�ƻ���ϣ���Ϊ�ƻ����
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void checkLocationConflicttest() throws ParseException, BeginEndTimeException, SameLocationException, ConflictTimeException {
		
		String weidu="��γ40��",jingdu="����112��";
		//
		List<CourseEntry<Teacher>> courselist1=new ArrayList<>();
		List<CourseEntry<Teacher>> courselist2=new ArrayList<>();
		
		OneLocationEntryImpl a1;
		OneDistinguishResourceEntryImpl<Teacher> b1;
		NoBlockableEntryImpl c1;
		a1=new OneLocationEntryImpl();
		b1=new OneDistinguishResourceEntryImpl<Teacher>();
		c1=new NoBlockableEntryImpl();
		PlanningEntry origincourse1=new CourseEntryFactory().getCourseEntry(a1, b1, c1); //�ù���������������
		CourseEntry<Teacher> course1=(CourseEntry<Teacher>)origincourse1;
		
		course1.setlocations(new CourseLocation(weidu,jingdu,"����¥32"));
		course1.settimeslot(new Timeslot("2020-01-01 15:45","2020-01-01 17:30"));
		course1.setplanningentryname("�������");
		
		OneLocationEntryImpl a2;
		OneDistinguishResourceEntryImpl<Teacher> b2;
		NoBlockableEntryImpl c2;
		a2=new OneLocationEntryImpl();
		b2=new OneDistinguishResourceEntryImpl<Teacher>();
		c2=new NoBlockableEntryImpl();
		PlanningEntry origincourse2=new CourseEntryFactory().getCourseEntry(a2, b2, c2); //�ù���������������
		CourseEntry<Teacher> course2=(CourseEntry<Teacher>)origincourse2;
		
		course2.setlocations(new CourseLocation(weidu,jingdu,"����¥32"));
		course2.settimeslot(new Timeslot("2020-01-01 16:45","2020-01-01 18:30"));
		course2.setplanningentryname("�����ϵͳ");
		
		OneLocationEntryImpl a3;
		OneDistinguishResourceEntryImpl<Teacher> b3;
		NoBlockableEntryImpl c3;
		a3=new OneLocationEntryImpl();
		b3=new OneDistinguishResourceEntryImpl<Teacher>();
		c3=new NoBlockableEntryImpl();
		PlanningEntry origincourse3=new CourseEntryFactory().getCourseEntry(a3, b3, c3); //�ù���������������
		CourseEntry<Teacher> course3=(CourseEntry<Teacher>)origincourse3;
		
		course3.setlocations(new CourseLocation(weidu,jingdu,"����¥32"));
		course3.settimeslot(new Timeslot("2020-02-01 16:45","2020-02-01 18:30"));
		course3.setplanningentryname("���ݽṹ");
	
		courselist1.add(course1);
		courselist1.add(course2);
		courselist2.add(course1);
		courselist2.add(course3);
		
		assertEquals(true,myapis.checkLocationConflict(courselist1, "1"));
		assertEquals(false,myapis.checkLocationConflict(courselist2, "1"));
		assertEquals(true,myapis.checkLocationConflict(courselist1, "2"));
		assertEquals(false,myapis.checkLocationConflict(courselist2, "2"));
		assertEquals(false,myapis.checkLocationConflict(courselist2, "3"));
		
		//
		List<FlightEntry<Flight>> flightlist1=new ArrayList<>();
		
		TwoLocationEntryImpl a4;
		OneDistinguishResourceEntryImpl<Flight> b4;
		NoBlockableEntryImpl c4;
		a4=new TwoLocationEntryImpl();
		b4=new OneDistinguishResourceEntryImpl<Flight>();
		c4=new NoBlockableEntryImpl();
		PlanningEntry originflight=new FlightEntryFactory().getFlightEntry(a4, b4, c4); //�ù���������������
		FlightEntry<Flight> flight=(FlightEntry<Flight>)originflight;
		
		flight.setlocations(new FlightTrainLocation(weidu,jingdu,"����"), new FlightTrainLocation(weidu,jingdu,"�人"));
		flight.settimeslot(new Timeslot("2020-01-01 16:45","2020-01-01 18:30"));
		flight.setplanningentryname("AS1234");
		
		flightlist1.add(flight);
		
		assertEquals(false,myapis.checkLocationConflict(flightlist1, "1"));
		assertEquals(false,myapis.checkLocationConflict(flightlist1, "2"));
		
		//
		List<TrainEntry<Carriage>> trainlist1=new ArrayList<>();
		
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
		List<Timeslot> alltime=new ArrayList<>();
		alltime.add(new Timeslot("2020-01-01 16:45","2020-01-01 18:30"));
		alltime.add(new Timeslot("2020-01-01 18:40","2020-01-01 20:30"));
		train.settimeslot(alltime);
		train.setplanningentryname("G109");
		
		trainlist1.add(train);
		
		assertEquals(false,myapis.checkLocationConflict(trainlist1, "1"));
		assertEquals(false,myapis.checkLocationConflict(trainlist1, "2"));
		
		//
		List<CommonPlanningEntry> templist=new ArrayList<>();
		
		CommonPlanningEntry tempentry=new CommonPlanningEntry<>();
		templist.add(tempentry);
		
		assertEquals(false,myapis.checkLocationConflict(templist, "1"));
		assertEquals(false,myapis.checkLocationConflict(templist, "2"));	
	}
	
	
	/* Testing strategy
	 * ����checkResourceExclusiveConflict����
	 * ����PlanningEntry�����໮�֣�CourseEntry,FlightEntry,TrainEntry
	 * �����Ƿ������Դ��ռ��ͻ���֣�������Դ��ռ��ͻ����������Դ��ռ��ͻ
	 * ��������ļ��ϻ��֣�Ϊ�ƻ���ϣ���Ϊ�ƻ����
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void checkResourceExclusiveConflicttest() throws ParseException, BeginEndTimeException, SameLocationException, SameResourceException, ConflictTimeException, LessThanZeroException{
		String weidu="��γ40��",jingdu="����112��";
		//
		List<CourseEntry<Teacher>> courselist1=new ArrayList<>();
		List<CourseEntry<Teacher>> courselist2=new ArrayList<>();
		
		OneLocationEntryImpl a1;
		OneDistinguishResourceEntryImpl<Teacher> b1;
		NoBlockableEntryImpl c1;
		a1=new OneLocationEntryImpl();
		b1=new OneDistinguishResourceEntryImpl<Teacher>();
		c1=new NoBlockableEntryImpl();
		PlanningEntry origincourse1=new CourseEntryFactory().getCourseEntry(a1, b1, c1); //�ù���������������
		CourseEntry<Teacher> course1=(CourseEntry<Teacher>)origincourse1;
		
		course1.setlocations(new CourseLocation(weidu,jingdu,"����¥32"));
		course1.settimeslot(new Timeslot("2020-01-01 15:45","2020-01-01 17:30"));
		course1.setplanningentryname("�������");
		course1.setresource(new Teacher("422823199812254452","����","��","��ʦ"));
		
		OneLocationEntryImpl a2;
		OneDistinguishResourceEntryImpl<Teacher> b2;
		NoBlockableEntryImpl c2;
		a2=new OneLocationEntryImpl();
		b2=new OneDistinguishResourceEntryImpl<Teacher>();
		c2=new NoBlockableEntryImpl();
		PlanningEntry origincourse2=new CourseEntryFactory().getCourseEntry(a2, b2, c2); //�ù���������������
		CourseEntry<Teacher> course2=(CourseEntry<Teacher>)origincourse2;
		
		course2.setlocations(new CourseLocation(weidu,jingdu,"����¥32"));
		course2.settimeslot(new Timeslot("2020-01-01 16:45","2020-01-01 18:30"));
		course2.setplanningentryname("�����ϵͳ");
		course2.setresource(new Teacher("422823199812254452","����","��","��ʦ"));
		
		OneLocationEntryImpl a3;
		OneDistinguishResourceEntryImpl<Teacher> b3;
		NoBlockableEntryImpl c3;
		a3=new OneLocationEntryImpl();
		b3=new OneDistinguishResourceEntryImpl<Teacher>();
		c3=new NoBlockableEntryImpl();
		PlanningEntry origincourse3=new CourseEntryFactory().getCourseEntry(a3, b3, c3); //�ù���������������
		CourseEntry<Teacher> course3=(CourseEntry<Teacher>)origincourse3;
		
		course3.setlocations(new CourseLocation(weidu,jingdu,"����¥32"));
		course3.settimeslot(new Timeslot("2020-02-01 16:45","2020-02-01 18:30"));
		course3.setplanningentryname("���ݽṹ");
		course3.setresource(new Teacher("123823199812251172","����","��","��ʦ"));
		
		courselist1.add(course1);
		courselist1.add(course2);
		courselist2.add(course1);
		courselist2.add(course3);
		
		assertEquals(true,myapis.checkResourceExclusiveConflict(courselist1));
		assertEquals(false,myapis.checkResourceExclusiveConflict(courselist2));
		
	    //
        List<FlightEntry<Flight>> flightlist1=new ArrayList<>();
        List<FlightEntry<Flight>> flightlist2=new ArrayList<>();
		
		TwoLocationEntryImpl a4;
		OneDistinguishResourceEntryImpl<Flight> b4;
		NoBlockableEntryImpl c4;
		a4=new TwoLocationEntryImpl();
		b4=new OneDistinguishResourceEntryImpl<Flight>();
		c4=new NoBlockableEntryImpl();
		PlanningEntry originflight1=new FlightEntryFactory().getFlightEntry(a4, b4, c4); //�ù���������������
		FlightEntry<Flight> flight1=(FlightEntry<Flight>)originflight1;
		
		flight1.setlocations(new FlightTrainLocation(weidu,jingdu,"����"), new FlightTrainLocation(weidu,jingdu,"�人"));
		flight1.settimeslot(new Timeslot("2020-01-01 16:45","2020-01-01 18:30"));
		flight1.setplanningentryname("AS1234");
		flight1.setresource(new Flight("A898","C88",100,2.5));
		
		TwoLocationEntryImpl a5;
		OneDistinguishResourceEntryImpl<Flight> b5;
		NoBlockableEntryImpl c5;
		a5=new TwoLocationEntryImpl();
		b5=new OneDistinguishResourceEntryImpl<Flight>();
		c5=new NoBlockableEntryImpl();
		PlanningEntry originflight2=new FlightEntryFactory().getFlightEntry(a5, b5, c5); //�ù���������������
		FlightEntry<Flight> flight2=(FlightEntry<Flight>)originflight2;
		
		flight2.setlocations(new FlightTrainLocation(weidu,jingdu,"����"), new FlightTrainLocation(weidu,jingdu,"�人"));
		flight2.settimeslot(new Timeslot("2020-01-01 17:45","2020-01-01 19:30"));
		flight2.setplanningentryname("AS1000");
		flight2.setresource(new Flight("A898","C88",100,2.5));
		
		TwoLocationEntryImpl a6;
		OneDistinguishResourceEntryImpl<Flight> b6;
		NoBlockableEntryImpl c6;
		a6=new TwoLocationEntryImpl();
		b6=new OneDistinguishResourceEntryImpl<Flight>();
		c6=new NoBlockableEntryImpl();
		PlanningEntry originflight3=new FlightEntryFactory().getFlightEntry(a6, b6, c6); //�ù���������������
		FlightEntry<Flight> flight3=(FlightEntry<Flight>)originflight3;
		
		flight3.setlocations(new FlightTrainLocation(weidu,jingdu,"����"), new FlightTrainLocation(weidu,jingdu,"�人"));
		flight3.settimeslot(new Timeslot("2020-01-02 16:45","2020-01-02 18:30"));
		flight3.setplanningentryname("AS0999");
		flight3.setresource(new Flight("A678","A28",100,2.5));
		
		flightlist1.add(flight1);
		flightlist1.add(flight2);
		flightlist2.add(flight1);
		flightlist2.add(flight3);
		
		assertEquals(true,myapis.checkResourceExclusiveConflict(flightlist1));
		assertEquals(false,myapis.checkResourceExclusiveConflict(flightlist2));
		
		//
        List<TrainEntry<Carriage>> trainlist1=new ArrayList<>();
        List<TrainEntry<Carriage>> trainlist2=new ArrayList<>();
		
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
		alllocation1.add(new FlightTrainLocation(weidu,jingdu,"�Ͼ�"));
		train1.setlocations(alllocation1);
		List<Timeslot> alltime1=new ArrayList<>();
		alltime1.add(new Timeslot("2020-01-01 16:45","2020-01-01 18:30"));
		alltime1.add(new Timeslot("2020-01-01 18:40","2020-01-01 20:30"));
		train1.settimeslot(alltime1);
		train1.setplanningentryname("G109");
		List<Carriage> allcarriage1=new ArrayList<>();
		allcarriage1.add(new Carriage("AS01","������",100,"2011"));
		allcarriage1.add(new Carriage("AS02","������",100,"2011"));
		train1.setresource(allcarriage1);
		
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
		List<Timeslot> alltime2=new ArrayList<>();
		alltime2.add(new Timeslot("2020-01-01 14:45","2020-01-01 18:30"));
		alltime2.add(new Timeslot("2020-01-01 18:40","2020-01-01 20:30"));
		train2.settimeslot(alltime2);
		train2.setplanningentryname("G320");
		List<Carriage> allcarriage2=new ArrayList<>();
		allcarriage2.add(new Carriage("AS01","������",100,"2011"));
		allcarriage2.add(new Carriage("AS03","������",100,"2011"));
		train2.setresource(allcarriage2);
		
		MultipleLacationEntryImpl a9;
		MultipleSortedResourceEntryImpl<Carriage> b9;
		BlockableEntryImpl c9;
		a9=new MultipleLacationEntryImpl();
		b9=new MultipleSortedResourceEntryImpl<Carriage>();
		c9=new BlockableEntryImpl();
		PlanningEntry origintrain3=new TrainEntryFactory().getTrainEntry(a9, b9, c9); //�ù���������������
		TrainEntry<Carriage> train3=(TrainEntry<Carriage>)origintrain3;
		
		List<Location> alllocation3=new ArrayList<>();
		alllocation3.add(new FlightTrainLocation(weidu,jingdu,"����"));
		alllocation3.add(new FlightTrainLocation(weidu,jingdu,"�人"));
		alllocation3.add(new FlightTrainLocation(weidu,jingdu,"�Ͼ�"));
		train3.setlocations(alllocation3);
		List<Timeslot> alltime3=new ArrayList<>();
		alltime3.add(new Timeslot("2020-01-01 14:45","2020-01-01 18:30"));
		alltime3.add(new Timeslot("2020-01-01 18:40","2020-01-01 20:30"));
		train3.settimeslot(alltime3);
		train3.setplanningentryname("G1000");
		List<Carriage> allcarriage3=new ArrayList<>();
		allcarriage3.add(new Carriage("AS05","������",100,"2011"));
		allcarriage3.add(new Carriage("AS06","������",100,"2011"));
		train3.setresource(allcarriage3);
		
		trainlist1.add(train1);
		trainlist1.add(train2);
		trainlist2.add(train1);
		trainlist2.add(train3);
		
		assertEquals(true,myapis.checkResourceExclusiveConflict(trainlist1));
		assertEquals(false,myapis.checkResourceExclusiveConflict(trainlist2));
		
		//
        List<CommonPlanningEntry> templist=new ArrayList<>();
		
		CommonPlanningEntry tempentry=new CommonPlanningEntry<>();
		templist.add(tempentry);
		
		assertEquals(false,myapis.checkResourceExclusiveConflict(templist));
	}
	
	/* Testing strategy
	 * ����findEntryPerResource����
	 * ����PlanningEntry�����໮�֣�CourseEntry,FlightEntry,TrainEntry
	 * ��������Դ�ļƻ���������֣�һ�������
	 * ��������ļ��ϻ��֣�Ϊ�ƻ���ϣ���Ϊ�ƻ����
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void findEntryPerResourcetest() throws ParseException, BeginEndTimeException, SameLocationException, SameResourceException, ConflictTimeException, LessThanZeroException{
		String weidu="��γ40��",jingdu="����112��";
		//
		List<CourseEntry<Teacher>> courselist1=new ArrayList<>();
		List<CourseEntry<Teacher>> courselist2=new ArrayList<>();
		
		OneLocationEntryImpl a1;
		OneDistinguishResourceEntryImpl<Teacher> b1;
		NoBlockableEntryImpl c1;
		a1=new OneLocationEntryImpl();
		b1=new OneDistinguishResourceEntryImpl<Teacher>();
		c1=new NoBlockableEntryImpl();
		PlanningEntry origincourse1=new CourseEntryFactory().getCourseEntry(a1, b1, c1); //�ù���������������
		CourseEntry<Teacher> course1=(CourseEntry<Teacher>)origincourse1;
		
		course1.setlocations(new CourseLocation(weidu,jingdu,"����¥32"));
		course1.settimeslot(new Timeslot("2020-01-01 15:45","2020-01-01 17:30"));
		course1.setplanningentryname("�������");
		course1.setresource(new Teacher("422823199812254452","����","��","��ʦ"));
		
		OneLocationEntryImpl a2;
		OneDistinguishResourceEntryImpl<Teacher> b2;
		NoBlockableEntryImpl c2;
		a2=new OneLocationEntryImpl();
		b2=new OneDistinguishResourceEntryImpl<Teacher>();
		c2=new NoBlockableEntryImpl();
		PlanningEntry origincourse2=new CourseEntryFactory().getCourseEntry(a2, b2, c2); //�ù���������������
		CourseEntry<Teacher> course2=(CourseEntry<Teacher>)origincourse2;
		
		course2.setlocations(new CourseLocation(weidu,jingdu,"����¥32"));
		course2.settimeslot(new Timeslot("2020-01-01 16:45","2020-01-01 18:30"));
		course2.setplanningentryname("�����ϵͳ");
		course2.setresource(new Teacher("422823199812254452","����","��","��ʦ"));
		
		OneLocationEntryImpl a3;
		OneDistinguishResourceEntryImpl<Teacher> b3;
		NoBlockableEntryImpl c3;
		a3=new OneLocationEntryImpl();
		b3=new OneDistinguishResourceEntryImpl<Teacher>();
		c3=new NoBlockableEntryImpl();
		PlanningEntry origincourse3=new CourseEntryFactory().getCourseEntry(a3, b3, c3); //�ù���������������
		CourseEntry<Teacher> course3=(CourseEntry<Teacher>)origincourse3;
		
		course3.setlocations(new CourseLocation(weidu,jingdu,"����¥32"));
		course3.settimeslot(new Timeslot("2020-02-01 16:45","2020-02-01 18:30"));
		course3.setplanningentryname("���ݽṹ");
		course3.setresource(new Teacher("123823199812251172","����","��","��ʦ"));
		
		courselist1.add(course1);
		courselist1.add(course2);
		courselist2.add(course1);
		courselist2.add(course3);
		
		List<CourseEntry<Teacher>> tempcourselist1=new ArrayList<>();
		tempcourselist1.add(course1);
		tempcourselist1.add(course2);
		List<CourseEntry<Teacher>> tempcourselist2=new ArrayList<>();
		tempcourselist2.add(course1);

		
		assertEquals(tempcourselist1,myapis.findEntryPerResource(new Teacher("422823199812254452","����","��","��ʦ"), courselist1));
		assertEquals(tempcourselist2,myapis.findEntryPerResource(new Teacher("422823199812254452","����","��","��ʦ"), courselist2));
		
	    //
        List<FlightEntry<Flight>> flightlist1=new ArrayList<>();
        List<FlightEntry<Flight>> flightlist2=new ArrayList<>();
		
		TwoLocationEntryImpl a4;
		OneDistinguishResourceEntryImpl<Flight> b4;
		NoBlockableEntryImpl c4;
		a4=new TwoLocationEntryImpl();
		b4=new OneDistinguishResourceEntryImpl<Flight>();
		c4=new NoBlockableEntryImpl();
		PlanningEntry originflight1=new FlightEntryFactory().getFlightEntry(a4, b4, c4); //�ù���������������
		FlightEntry<Flight> flight1=(FlightEntry<Flight>)originflight1;
		
		flight1.setlocations(new FlightTrainLocation(weidu,jingdu,"����"), new FlightTrainLocation(weidu,jingdu,"�人"));
		flight1.settimeslot(new Timeslot("2020-01-01 16:45","2020-01-01 18:30"));
		flight1.setplanningentryname("AS1234");
		flight1.setresource(new Flight("A898","C88",100,2.5));
		
		TwoLocationEntryImpl a5;
		OneDistinguishResourceEntryImpl<Flight> b5;
		NoBlockableEntryImpl c5;
		a5=new TwoLocationEntryImpl();
		b5=new OneDistinguishResourceEntryImpl<Flight>();
		c5=new NoBlockableEntryImpl();
		PlanningEntry originflight2=new FlightEntryFactory().getFlightEntry(a5, b5, c5); //�ù���������������
		FlightEntry<Flight> flight2=(FlightEntry<Flight>)originflight2;
		
		flight2.setlocations(new FlightTrainLocation(weidu,jingdu,"����"), new FlightTrainLocation(weidu,jingdu,"�人"));
		flight2.settimeslot(new Timeslot("2020-01-01 17:45","2020-01-01 19:30"));
		flight2.setplanningentryname("AS1000");
		flight2.setresource(new Flight("A898","C88",100,2.5));
		
		TwoLocationEntryImpl a6;
		OneDistinguishResourceEntryImpl<Flight> b6;
		NoBlockableEntryImpl c6;
		a6=new TwoLocationEntryImpl();
		b6=new OneDistinguishResourceEntryImpl<Flight>();
		c6=new NoBlockableEntryImpl();
		PlanningEntry originflight3=new FlightEntryFactory().getFlightEntry(a6, b6, c6); //�ù���������������
		FlightEntry<Flight> flight3=(FlightEntry<Flight>)originflight3;
		
		flight3.setlocations(new FlightTrainLocation(weidu,jingdu,"����"), new FlightTrainLocation(weidu,jingdu,"�人"));
		flight3.settimeslot(new Timeslot("2020-01-02 16:45","2020-01-02 18:30"));
		flight3.setplanningentryname("AS0999");
		flight3.setresource(new Flight("A678","A28",100,2.5));
		
		flightlist1.add(flight1);
		flightlist1.add(flight2);
		flightlist2.add(flight1);
		flightlist2.add(flight3);
		
		List<FlightEntry<Flight>> tempflightlist1=new ArrayList<>();
		tempflightlist1.add(flight1);
		tempflightlist1.add(flight2);
		List<FlightEntry<Flight>> tempflightlist2=new ArrayList<>();
		tempflightlist2.add(flight1);

		
		assertEquals(tempflightlist1,myapis.findEntryPerResource(new Flight("A898","C88",100,2.5), flightlist1));
		assertEquals(tempflightlist2,myapis.findEntryPerResource(new Flight("A898","C88",100,2.5), flightlist2));
		
		//
        List<TrainEntry<Carriage>> trainlist1=new ArrayList<>();
        List<TrainEntry<Carriage>> trainlist2=new ArrayList<>();
		
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
		alllocation1.add(new FlightTrainLocation(weidu,jingdu,"�Ͼ�"));
		train1.setlocations(alllocation1);
		List<Timeslot> alltime1=new ArrayList<>();
		alltime1.add(new Timeslot("2020-01-01 16:45","2020-01-01 18:30"));
		alltime1.add(new Timeslot("2020-01-01 18:40","2020-01-01 20:30"));
		train1.settimeslot(alltime1);
		train1.setplanningentryname("G109");
		List<Carriage> allcarriage1=new ArrayList<>();
		allcarriage1.add(new Carriage("AS01","������",100,"2011"));
		allcarriage1.add(new Carriage("AS02","������",100,"2011"));
		train1.setresource(allcarriage1);
		
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
		List<Timeslot> alltime2=new ArrayList<>();
		alltime2.add(new Timeslot("2020-01-01 14:45","2020-01-01 18:30"));
		alltime2.add(new Timeslot("2020-01-01 18:40","2020-01-01 20:30"));
		train2.settimeslot(alltime2);
		train2.setplanningentryname("G320");
		List<Carriage> allcarriage2=new ArrayList<>();
		allcarriage2.add(new Carriage("AS01","������",100,"2011"));
		allcarriage2.add(new Carriage("AS03","������",100,"2011"));
		train2.setresource(allcarriage2);
		
		MultipleLacationEntryImpl a9;
		MultipleSortedResourceEntryImpl<Carriage> b9;
		BlockableEntryImpl c9;
		a9=new MultipleLacationEntryImpl();
		b9=new MultipleSortedResourceEntryImpl<Carriage>();
		c9=new BlockableEntryImpl();
		PlanningEntry origintrain3=new TrainEntryFactory().getTrainEntry(a9, b9, c9); //�ù���������������
		TrainEntry<Carriage> train3=(TrainEntry<Carriage>)origintrain3;
		
		List<Location> alllocation3=new ArrayList<>();
		alllocation3.add(new FlightTrainLocation(weidu,jingdu,"����"));
		alllocation3.add(new FlightTrainLocation(weidu,jingdu,"�人"));
		alllocation3.add(new FlightTrainLocation(weidu,jingdu,"�Ͼ�"));
		train3.setlocations(alllocation3);
		List<Timeslot> alltime3=new ArrayList<>();
		alltime3.add(new Timeslot("2020-01-01 14:45","2020-01-01 18:30"));
		alltime3.add(new Timeslot("2020-01-01 18:40","2020-01-01 20:30"));
		train3.settimeslot(alltime3);
		train3.setplanningentryname("G1000");
		List<Carriage> allcarriage3=new ArrayList<>();
		allcarriage3.add(new Carriage("AS05","������",100,"2011"));
		allcarriage3.add(new Carriage("AS06","������",100,"2011"));
		train3.setresource(allcarriage3);
		
		trainlist1.add(train1);
		trainlist1.add(train2);
		trainlist2.add(train1);
		trainlist2.add(train3);
		
		List<TrainEntry<Carriage>> temptrainlist1=new ArrayList<>();
		temptrainlist1.add(train1);
		temptrainlist1.add(train2);
		List<TrainEntry<Carriage>> temptrainlist2=new ArrayList<>();
		temptrainlist2.add(train1);

		
		assertEquals(temptrainlist1,myapis.findEntryPerResource(new Carriage("AS01","������",100,"2011"), trainlist1));
		assertEquals(temptrainlist2,myapis.findEntryPerResource(new Carriage("AS01","������",100,"2011"), trainlist2));
		
		//
        List<CommonPlanningEntry> templist=new ArrayList<>();
		
		CommonPlanningEntry tempentry=new CommonPlanningEntry<>();
		templist.add(tempentry);
		
		assertEquals(null,myapis.findEntryPerResource("abc",templist));
	}
	
	/* Testing strategy
	 * ����findPreEntryPerResource����
	 * ����PlanningEntry�����໮�֣�CourseEntry,FlightEntry,TrainEntry
	 * �����Ƿ���ǰ��ƻ���֣���ǰ��ƻ����ǰ��ƻ���
	 * ��������ļ��ϻ��֣�Ϊ�ƻ���ϣ���Ϊ�ƻ����
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void findPreEntryPerResourcetest() throws ParseException, BeginEndTimeException, SameLocationException, SameResourceException, ConflictTimeException, LessThanZeroException{
		String weidu="��γ40��",jingdu="����112��";
		//
		List<CourseEntry<Teacher>> courselist1=new ArrayList<>();
		List<CourseEntry<Teacher>> courselist2=new ArrayList<>();
		
		OneLocationEntryImpl a1;
		OneDistinguishResourceEntryImpl<Teacher> b1;
		NoBlockableEntryImpl c1;
		a1=new OneLocationEntryImpl();
		b1=new OneDistinguishResourceEntryImpl<Teacher>();
		c1=new NoBlockableEntryImpl();
		PlanningEntry origincourse1=new CourseEntryFactory().getCourseEntry(a1, b1, c1); //�ù���������������
		CourseEntry<Teacher> course1=(CourseEntry<Teacher>)origincourse1;
		
		course1.setlocations(new CourseLocation(weidu,jingdu,"����¥32"));
		course1.settimeslot(new Timeslot("2020-01-01 15:45","2020-01-01 17:30"));
		course1.setplanningentryname("�������");
		course1.setresource(new Teacher("422823199812254452","����","��","��ʦ"));
		
		OneLocationEntryImpl a2;
		OneDistinguishResourceEntryImpl<Teacher> b2;
		NoBlockableEntryImpl c2;
		a2=new OneLocationEntryImpl();
		b2=new OneDistinguishResourceEntryImpl<Teacher>();
		c2=new NoBlockableEntryImpl();
		PlanningEntry origincourse2=new CourseEntryFactory().getCourseEntry(a2, b2, c2); //�ù���������������
		CourseEntry<Teacher> course2=(CourseEntry<Teacher>)origincourse2;
		
		course2.setlocations(new CourseLocation(weidu,jingdu,"����¥32"));
		course2.settimeslot(new Timeslot("2020-01-01 16:45","2020-01-01 18:30"));
		course2.setplanningentryname("�����ϵͳ");
		course2.setresource(new Teacher("422823199812254452","����","��","��ʦ"));
		
		OneLocationEntryImpl a3;
		OneDistinguishResourceEntryImpl<Teacher> b3;
		NoBlockableEntryImpl c3;
		a3=new OneLocationEntryImpl();
		b3=new OneDistinguishResourceEntryImpl<Teacher>();
		c3=new NoBlockableEntryImpl();
		PlanningEntry origincourse3=new CourseEntryFactory().getCourseEntry(a3, b3, c3); //�ù���������������
		CourseEntry<Teacher> course3=(CourseEntry<Teacher>)origincourse3;
		
		course3.setlocations(new CourseLocation(weidu,jingdu,"����¥32"));
		course3.settimeslot(new Timeslot("2020-02-01 16:45","2020-02-01 18:30"));
		course3.setplanningentryname("���ݽṹ");
		course3.setresource(new Teacher("123823199812251172","����","��","��ʦ"));
		
		courselist1.add(course1);
		courselist1.add(course2);
		courselist2.add(course1);
		courselist2.add(course3);
		
		assertEquals(course1,myapis.findPreEntryPerResource(new Teacher("422823199812254452","����","��","��ʦ"), course2, courselist1));
		assertEquals(null,myapis.findPreEntryPerResource(new Teacher("422823199812254452","����","��","��ʦ"), course1, courselist1));
		
	    //
        List<FlightEntry<Flight>> flightlist1=new ArrayList<>();
        List<FlightEntry<Flight>> flightlist2=new ArrayList<>();
		
		TwoLocationEntryImpl a4;
		OneDistinguishResourceEntryImpl<Flight> b4;
		NoBlockableEntryImpl c4;
		a4=new TwoLocationEntryImpl();
		b4=new OneDistinguishResourceEntryImpl<Flight>();
		c4=new NoBlockableEntryImpl();
		PlanningEntry originflight1=new FlightEntryFactory().getFlightEntry(a4, b4, c4); //�ù���������������
		FlightEntry<Flight> flight1=(FlightEntry<Flight>)originflight1;
		
		flight1.setlocations(new FlightTrainLocation(weidu,jingdu,"����"), new FlightTrainLocation(weidu,jingdu,"�人"));
		flight1.settimeslot(new Timeslot("2020-01-01 16:45","2020-01-01 18:30"));
		flight1.setplanningentryname("AS1234");
		flight1.setresource(new Flight("A898","C88",100,2.5));
		
		TwoLocationEntryImpl a5;
		OneDistinguishResourceEntryImpl<Flight> b5;
		NoBlockableEntryImpl c5;
		a5=new TwoLocationEntryImpl();
		b5=new OneDistinguishResourceEntryImpl<Flight>();
		c5=new NoBlockableEntryImpl();
		PlanningEntry originflight2=new FlightEntryFactory().getFlightEntry(a5, b5, c5); //�ù���������������
		FlightEntry<Flight> flight2=(FlightEntry<Flight>)originflight2;
		
		flight2.setlocations(new FlightTrainLocation(weidu,jingdu,"����"), new FlightTrainLocation(weidu,jingdu,"�人"));
		flight2.settimeslot(new Timeslot("2020-01-01 17:45","2020-01-01 19:30"));
		flight2.setplanningentryname("AS1000");
		flight2.setresource(new Flight("A898","C88",100,2.5));
		
		TwoLocationEntryImpl a6;
		OneDistinguishResourceEntryImpl<Flight> b6;
		NoBlockableEntryImpl c6;
		a6=new TwoLocationEntryImpl();
		b6=new OneDistinguishResourceEntryImpl<Flight>();
		c6=new NoBlockableEntryImpl();
		PlanningEntry originflight3=new FlightEntryFactory().getFlightEntry(a6, b6, c6); //�ù���������������
		FlightEntry<Flight> flight3=(FlightEntry<Flight>)originflight3;
		
		flight3.setlocations(new FlightTrainLocation(weidu,jingdu,"����"), new FlightTrainLocation(weidu,jingdu,"�人"));
		flight3.settimeslot(new Timeslot("2020-01-02 16:45","2020-01-02 18:30"));
		flight3.setplanningentryname("AS0999");
		flight3.setresource(new Flight("A678","A28",100,2.5));
		
		flightlist1.add(flight1);
		flightlist1.add(flight2);
		flightlist2.add(flight1);
		flightlist2.add(flight3);
		
		assertEquals(flight1,myapis.findPreEntryPerResource(new Flight("A898","C88",100,2.5), flight2, flightlist1));
		assertEquals(null,myapis.findPreEntryPerResource(new Flight("A898","C88",100,2.5), flight1, flightlist1));
		
		//
        List<TrainEntry<Carriage>> trainlist1=new ArrayList<>();
        List<TrainEntry<Carriage>> trainlist2=new ArrayList<>();
		
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
		alllocation1.add(new FlightTrainLocation(weidu,jingdu,"�Ͼ�"));
		train1.setlocations(alllocation1);
		List<Timeslot> alltime1=new ArrayList<>();
		alltime1.add(new Timeslot("2020-01-01 16:45","2020-01-01 18:30"));
		alltime1.add(new Timeslot("2020-01-01 18:40","2020-01-01 20:30"));
		train1.settimeslot(alltime1);
		train1.setplanningentryname("G109");
		List<Carriage> allcarriage1=new ArrayList<>();
		allcarriage1.add(new Carriage("AS01","������",100,"2011"));
		allcarriage1.add(new Carriage("AS02","������",100,"2011"));
		train1.setresource(allcarriage1);
		
		MultipleLacationEntryImpl a8;
		MultipleSortedResourceEntryImpl<Carriage> b8;
		BlockableEntryImpl c8;
		a8=new MultipleLacationEntryImpl();
		b8=new MultipleSortedResourceEntryImpl<Carriage>();
		c8=new BlockableEntryImpl();
		PlanningEntry origintrain2=new TrainEntryFactory().getTrainEntry(a8, b8, c8); //�ù���������������
		TrainEntry<Carriage> train2=(TrainEntry)origintrain2;
		
		List<Location> alllocation2=new ArrayList<>();
		alllocation2.add(new FlightTrainLocation(weidu,jingdu,"����"));
		alllocation2.add(new FlightTrainLocation(weidu,jingdu,"�人"));
		alllocation2.add(new FlightTrainLocation(weidu,jingdu,"�Ͼ�"));
		train2.setlocations(alllocation2);
		List<Timeslot> alltime2=new ArrayList<>();
		alltime2.add(new Timeslot("2020-01-01 14:45","2020-01-01 18:30"));
		alltime2.add(new Timeslot("2020-01-01 18:40","2020-01-01 20:30"));
		train2.settimeslot(alltime2);
		train2.setplanningentryname("G320");
		List<Carriage> allcarriage2=new ArrayList<>();
		allcarriage2.add(new Carriage("AS01","������",100,"2011"));
		allcarriage2.add(new Carriage("AS03","������",100,"2011"));
		train2.setresource(allcarriage2);
		
		MultipleLacationEntryImpl a9;
		MultipleSortedResourceEntryImpl<Carriage> b9;
		BlockableEntryImpl c9;
		a9=new MultipleLacationEntryImpl();
		b9=new MultipleSortedResourceEntryImpl<Carriage>();
		c9=new BlockableEntryImpl();
		PlanningEntry origintrain3=new TrainEntryFactory().getTrainEntry(a9, b9, c9); //�ù���������������
		TrainEntry<Carriage> train3=(TrainEntry<Carriage>)origintrain3;
		
		List<Location> alllocation3=new ArrayList<>();
		alllocation3.add(new FlightTrainLocation(weidu,jingdu,"����"));
		alllocation3.add(new FlightTrainLocation(weidu,jingdu,"�人"));
		alllocation3.add(new FlightTrainLocation(weidu,jingdu,"�Ͼ�"));
		train3.setlocations(alllocation3);
		List<Timeslot> alltime3=new ArrayList<>();
		alltime3.add(new Timeslot("2020-01-01 14:45","2020-01-01 18:30"));
		alltime3.add(new Timeslot("2020-01-01 18:40","2020-01-01 20:30"));
		train3.settimeslot(alltime3);
		train3.setplanningentryname("G1000");
		List<Carriage> allcarriage3=new ArrayList<>();
		allcarriage3.add(new Carriage("AS05","������",100,"2011"));
		allcarriage3.add(new Carriage("AS06","������",100,"2011"));
		train3.setresource(allcarriage3);
		
		trainlist1.add(train1);
		trainlist1.add(train2);
		trainlist2.add(train1);
		trainlist2.add(train3);
		
		assertEquals(train2,myapis.findPreEntryPerResource(new Carriage("AS01","������",100,"2011"), train1, trainlist1));
		assertEquals(null,myapis.findPreEntryPerResource(new Carriage("AS01","������",100,"2011"), train2, trainlist1));
		
		//
        List<CommonPlanningEntry> templist=new ArrayList<>();
		
		CommonPlanningEntry tempentry=new CommonPlanningEntry<>();
		templist.add(tempentry);
		
		assertEquals(null,myapis.findPreEntryPerResource("abc", tempentry, templist));
	}
}
