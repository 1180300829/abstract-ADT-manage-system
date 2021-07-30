package PlanningEntry;

import Exception.SameLocationException;
import Location.Location;
import Resource.Flight;
import Timeslot.Timeslot;

public class FlightEntry<Flight> extends CommonPlanningEntry implements Cloneable,FlightPlanningEntry<Flight>,Comparable<FlightEntry<Flight>>{

	private TwoLocationEntryImpl a;
	private OneDistinguishResourceEntryImpl<Flight> b;
	private NoBlockableEntryImpl c;
	
	// mutability��
	// Abstraction function:
	// AF(a)=����λ��
	// AF(b)=һ���ɻ���Դ
	// AF(c)=����������ʱ���
	// Safety from rep exposure:
	// ��a,b,c����Ϊprivate

	/**
	 * ���췽��
	 * @param a ����λ�õ���
	 * @param b һ���ɻ���Դ����
	 * @param c ����������ʱ��Ե���
	 */
	public FlightEntry(TwoLocationEntryImpl a,OneDistinguishResourceEntryImpl<Flight> b,NoBlockableEntryImpl c) {
		this.a=a;
		this.b=b;
		this.c=c;
	}
	

	/**
	 * �������վ���յ�վ��λ��
	 * @param from ���վ��λ��
	 * @param to �յ�վ��λ��
	 * @return �Ƿ����óɹ�
	 * @throws SameLocationException 
	 */
	@Override
	public boolean setlocations(Location from, Location to) throws SameLocationException {
		return a.setlocations(from, to);
	}

	/**
	 * �������վ���յ�վ��λ��
	 * @param from ���վ��λ��
	 * @param to �յ�վ��λ��
	 * @return �Ƿ����óɹ�
	 */
	@Override
	public boolean changelocations(Location from, Location to) {
		return a.changelocations(from, to);
	}
	
	/**
	 * �õ����վ��λ��
	 * @return ���վ
	 */
	@Override
	public Location getfromlocation() {
		return a.getfromlocation();
	}

	/**
	 * �õ��յ�վ��λ��
	 * @return ���վ
	 */
	@Override
	public Location gettolocation() {
		return a.gettolocation();
	}


	/**
	 * ���÷ɻ���Դ
	 * @param a �����õķɻ���Դ
	 * @return �ɻ���Դ�Ƿ����óɹ�
	 */
	@Override
	public boolean setresource(Flight a) {
		return b.setresource(a);
	}

	/**
	 * �õ��÷ɻ���Դ
	 * @return �÷ɻ���Դ
	 */
	@Override
	public Flight getresource() {
		return b.getresource();
	}

	/**
	 * ���ĸ÷ɻ���Դ
	 * @param a ���ĺ����Դ
	 * @return �Ƿ���ĳɹ�
	 */
	@Override
	public boolean changeresource(Flight a) {
		return b.changeresource(a);
	}

	/**
	 * �жϸ����Ƿ������
	 * @return �����Ƿ������
	 */
	@Override
	public boolean whetherblockable() {
		return c.whetherblockable();
	}

	/**
	 * ���ø�ʱ���
	 * @param mytime ʱ���
	 * @return �Ƿ�ɹ����ø�ʱ���
	 */
	@Override
	public boolean settimeslot(Timeslot mytime) {
		return c.settimeslot(mytime);
	}

	/**
	 * �õ���ʱ���
	 * @return ��ʱ���
	 */
	@Override
	public Timeslot gettimeslot() {
		return c.gettimeslot();
	}

	/**
     * ��дcompareTo��������ɰ���ʱ�����ʼʱ�������Լƻ��������
     */
	@Override
	public int compareTo(FlightEntry<Flight> o) {
		if(this.c.gettimeslot().getbegintime().compareTo(o.gettimeslot().getbegintime())>0) {
			return 1;
		}
		else if(this.c.gettimeslot().getbegintime().compareTo(o.gettimeslot().getbegintime())==0) {
			return 0;
		}
		return -1;
	}
		
	 @Override
	  public FlightEntry<Flight> clone() { 
		 FlightEntry<Flight> stu = null; 
	    try{ 
	      stu = (FlightEntry<Flight>)super.clone(); 
	    }catch(CloneNotSupportedException e) { 
	      e.printStackTrace(); 
	    } 
	    stu.a=(TwoLocationEntryImpl)a.clone();
	    stu.b=(OneDistinguishResourceEntryImpl<Flight>)b.clone();
	    stu.c=(NoBlockableEntryImpl)c.clone();
	    return stu; 
	  } 
	
}
