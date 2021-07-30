package PlanningEntry;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import Exception.BeginEndTimeException;
import Exception.LessThanZeroException;
import Exception.SameLocationException;
import Factory.FlightEntryFactory;
import Location.FlightTrainLocation;
import Resource.Flight;
import Timeslot.Timeslot;

public class FlightEntryTest {
	
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
		TwoLocationEntryImpl a4;
		OneDistinguishResourceEntryImpl<Flight> b4;
		NoBlockableEntryImpl c4;
		a4=new TwoLocationEntryImpl();
		b4=new OneDistinguishResourceEntryImpl<Flight>();
		c4=new NoBlockableEntryImpl();
		PlanningEntry originflight=new FlightEntryFactory().getFlightEntry(a4, b4, c4); //�ù���������������
		FlightEntry<Flight> flight=(FlightEntry<Flight>)originflight;
		assertEquals(true,flight.setlocations(new FlightTrainLocation(weidu,jingdu,"����"), new FlightTrainLocation(weidu,jingdu,"�人")));
		assertEquals(false,flight.setlocations(new FlightTrainLocation(weidu,jingdu,"����"), new FlightTrainLocation(weidu,jingdu,"�人")));
	}
	
	/* Testing strategy
	 * ����changelocations����
     * ���Է���ֵ����
     */
	@Test
	public void changelocationstest() throws SameLocationException {
		String weidu="��γ40��",jingdu="����112��";
		TwoLocationEntryImpl a4;
		OneDistinguishResourceEntryImpl<Flight> b4;
		NoBlockableEntryImpl c4;
		a4=new TwoLocationEntryImpl();
		b4=new OneDistinguishResourceEntryImpl<Flight>();
		c4=new NoBlockableEntryImpl();
		PlanningEntry originflight=new FlightEntryFactory().getFlightEntry(a4, b4, c4); //�ù���������������
		FlightEntry<Flight> flight=(FlightEntry<Flight>)originflight;
		flight.setlocations(new FlightTrainLocation(weidu,jingdu,"����"), new FlightTrainLocation(weidu,jingdu,"�人"));
		assertEquals(false,flight.changelocations(new FlightTrainLocation(weidu,jingdu,"����"), new FlightTrainLocation(weidu,jingdu,"�人")));
	}
	
	/* Testing strategy
	 * ����getfromlocation����
     * ���Է���ֵ����
     */
	@Test
	public void getfromlocationtest() throws SameLocationException {
		String weidu="��γ40��",jingdu="����112��";
		TwoLocationEntryImpl a4;
		OneDistinguishResourceEntryImpl<Flight> b4;
		NoBlockableEntryImpl c4;
		a4=new TwoLocationEntryImpl();
		b4=new OneDistinguishResourceEntryImpl<Flight>();
		c4=new NoBlockableEntryImpl();
		PlanningEntry originflight=new FlightEntryFactory().getFlightEntry(a4, b4, c4); //�ù���������������
		FlightEntry<Flight> flight=(FlightEntry<Flight>)originflight;
		flight.setlocations(new FlightTrainLocation(weidu,jingdu,"����"), new FlightTrainLocation(weidu,jingdu,"�人"));
		assertEquals(new FlightTrainLocation(weidu,jingdu,"����"),flight.getfromlocation());
	}
	
	/* Testing strategy
	 * ����gettolocation����
     * ���Է���ֵ����
     */
	@Test
	public void gettolocationtest() throws SameLocationException {
		String weidu="��γ40��",jingdu="����112��";
		TwoLocationEntryImpl a4;
		OneDistinguishResourceEntryImpl<Flight> b4;
		NoBlockableEntryImpl c4;
		a4=new TwoLocationEntryImpl();
		b4=new OneDistinguishResourceEntryImpl<Flight>();
		c4=new NoBlockableEntryImpl();
		PlanningEntry originflight=new FlightEntryFactory().getFlightEntry(a4, b4, c4); //�ù���������������
		FlightEntry<Flight> flight=(FlightEntry<Flight>)originflight;
		flight.setlocations(new FlightTrainLocation(weidu,jingdu,"����"), new FlightTrainLocation(weidu,jingdu,"�人"));
		assertEquals(new FlightTrainLocation(weidu,jingdu,"�人"),flight.gettolocation());
	}
	
	/* Testing strategy
	 * ����setresource����
	 * ������Դ�Ƿ��ѱ����û��֣���Դ�ѱ����ã���Դδ������
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void setresourcetest() throws LessThanZeroException {
		TwoLocationEntryImpl a4;
		OneDistinguishResourceEntryImpl<Flight> b4;
		NoBlockableEntryImpl c4;
		a4=new TwoLocationEntryImpl();
		b4=new OneDistinguishResourceEntryImpl<Flight>();
		c4=new NoBlockableEntryImpl();
		PlanningEntry originflight=new FlightEntryFactory().getFlightEntry(a4, b4, c4); //�ù���������������
		FlightEntry<Flight> flight=(FlightEntry<Flight>)originflight;
		assertEquals(true,flight.setresource(new Flight("A678","A28",100,2.5)));
		assertEquals(false,flight.setresource(new Flight("A678","A28",100,2.5)));
	}
	
	/* Testing strategy
	 * ����getresource����
	 * ���Է���ֵ����
     */
	@Test
	public void getresourcetest() throws LessThanZeroException {
		TwoLocationEntryImpl a4;
		OneDistinguishResourceEntryImpl<Flight> b4;
		NoBlockableEntryImpl c4;
		a4=new TwoLocationEntryImpl();
		b4=new OneDistinguishResourceEntryImpl<Flight>();
		c4=new NoBlockableEntryImpl();
		PlanningEntry originflight=new FlightEntryFactory().getFlightEntry(a4, b4, c4); //�ù���������������
		FlightEntry<Flight> flight=(FlightEntry<Flight>)originflight;
		flight.setresource(new Flight("A678","A28",100,2.5));
		assertEquals(new Flight("A678","A28",100,2.5),flight.getresource());
	}
	
	/* Testing strategy
	 * ����changeresource����
	 * �����Ƿ���ԭ�ɻ��ظ����֣��ظ������ظ�
	 * ����ÿ��ȡֵ���£�
     */
	@Test
	public void changeresourcetest() throws LessThanZeroException {
		TwoLocationEntryImpl a4;
		OneDistinguishResourceEntryImpl<Flight> b4;
		NoBlockableEntryImpl c4;
		a4=new TwoLocationEntryImpl();
		b4=new OneDistinguishResourceEntryImpl<Flight>();
		c4=new NoBlockableEntryImpl();
		PlanningEntry originflight=new FlightEntryFactory().getFlightEntry(a4, b4, c4); //�ù���������������
		FlightEntry<Flight> flight=(FlightEntry<Flight>)originflight;
		flight.setresource(new Flight("A678","A28",100,2.5));
		assertEquals(false,flight.changeresource(new Flight("A678","A28",100,2.5)));
		assertEquals(true,flight.changeresource(new Flight("A424","A11",100,2.5)));
	}
	
	/* Testing strategy
	 * ����whetherblockable����
	 * ���Է���ֵ����
     */
	@Test
	public void whetherblockabletest() {
		TwoLocationEntryImpl a4;
		OneDistinguishResourceEntryImpl<Flight> b4;
		NoBlockableEntryImpl c4;
		a4=new TwoLocationEntryImpl();
		b4=new OneDistinguishResourceEntryImpl<Flight>();
		c4=new NoBlockableEntryImpl();
		PlanningEntry originflight=new FlightEntryFactory().getFlightEntry(a4, b4, c4); //�ù���������������
		FlightEntry<Flight> flight=(FlightEntry<Flight>)originflight;
		assertEquals(false,flight.whetherblockable());
	}
	
	/* Testing strategy
	 * ����settimeslot����
	 * ����ʱ���Ƿ��ѱ����û��֣��ѱ����ã�δ������
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void settimeslottest() throws ParseException, BeginEndTimeException {
		TwoLocationEntryImpl a4;
		OneDistinguishResourceEntryImpl<Flight> b4;
		NoBlockableEntryImpl c4;
		a4=new TwoLocationEntryImpl();
		b4=new OneDistinguishResourceEntryImpl<Flight>();
		c4=new NoBlockableEntryImpl();
		PlanningEntry originflight=new FlightEntryFactory().getFlightEntry(a4, b4, c4); //�ù���������������
		FlightEntry<Flight> flight=(FlightEntry<Flight>)originflight;
		assertEquals(true,flight.settimeslot(new Timeslot("2020-01-02 16:45","2020-01-02 18:30")));
		assertEquals(false,flight.settimeslot(new Timeslot("2020-01-02 16:45","2020-01-02 18:30")));
	}
	
	/* Testing strategy
	 * ����gettimeslot����
	 * ���Է���ֵ����
     */
	@Test
	public void gettimeslottest() throws ParseException, BeginEndTimeException {
		TwoLocationEntryImpl a4;
		OneDistinguishResourceEntryImpl<Flight> b4;
		NoBlockableEntryImpl c4;
		a4=new TwoLocationEntryImpl();
		b4=new OneDistinguishResourceEntryImpl<Flight>();
		c4=new NoBlockableEntryImpl();
		PlanningEntry originflight=new FlightEntryFactory().getFlightEntry(a4, b4, c4); //�ù���������������
		FlightEntry<Flight> flight=(FlightEntry<Flight>)originflight;
		flight.settimeslot(new Timeslot("2020-01-02 16:45","2020-01-02 18:30"));
		assertEquals(new Timeslot("2020-01-02 16:45","2020-01-02 18:30"),flight.gettimeslot());
	}
	
	/* Testing strategy
	 * ����compareTo����
	 * ������ʼʱ���С���֣����ڣ����ڣ�С��
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void compareTotest() throws ParseException, BeginEndTimeException {
		TwoLocationEntryImpl a4;
		OneDistinguishResourceEntryImpl<Flight> b4;
		NoBlockableEntryImpl c4;
		a4=new TwoLocationEntryImpl();
		b4=new OneDistinguishResourceEntryImpl<Flight>();
		c4=new NoBlockableEntryImpl();
		PlanningEntry originflight1=new FlightEntryFactory().getFlightEntry(a4, b4, c4); //�ù���������������
		FlightEntry<Flight> flight1=(FlightEntry<Flight>)originflight1;
		flight1.settimeslot(new Timeslot("2020-01-01 16:45","2020-01-01 18:30"));
		
		TwoLocationEntryImpl a5;
		OneDistinguishResourceEntryImpl<Flight> b5;
		NoBlockableEntryImpl c5;
		a5=new TwoLocationEntryImpl();
		b5=new OneDistinguishResourceEntryImpl<Flight>();
		c5=new NoBlockableEntryImpl();
		PlanningEntry originflight2=new FlightEntryFactory().getFlightEntry(a5, b5, c5); //�ù���������������
		FlightEntry<Flight> flight2=(FlightEntry<Flight>)originflight2;	
		flight2.settimeslot(new Timeslot("2020-01-01 17:45","2020-01-01 19:30"));
		
		TwoLocationEntryImpl a6;
		OneDistinguishResourceEntryImpl<Flight> b6;
		NoBlockableEntryImpl c6;
		a6=new TwoLocationEntryImpl();
		b6=new OneDistinguishResourceEntryImpl<Flight>();
		c6=new NoBlockableEntryImpl();
		PlanningEntry originflight3=new FlightEntryFactory().getFlightEntry(a6, b6, c6); //�ù���������������
		FlightEntry<Flight> flight3=(FlightEntry<Flight>)originflight3;
		flight3.settimeslot(new Timeslot("2020-01-01 16:45","2020-01-01 18:30"));
		
		assertEquals(1,flight2.compareTo(flight1));
		assertEquals(0,flight3.compareTo(flight1));
		assertEquals(-1,flight1.compareTo(flight2));
	}
}
