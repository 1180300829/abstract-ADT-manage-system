package PlanningEntry;

import Location.Location;
import Resource.Teacher;
import Timeslot.Timeslot;

public class CourseEntry<Teacher> extends CommonPlanningEntry implements Cloneable,CoursePlanningEntry<Teacher>,Comparable<CourseEntry<Teacher>>{

	private OneLocationEntryImpl a;
	private OneDistinguishResourceEntryImpl<Teacher> b;
	private NoBlockableEntryImpl c;
	
	// mutability��
	// Abstraction function:
	// AF(a)=һ��λ��
	// AF(b)=һ����ʦ��Դ
	// AF(c)=����������ʱ���
	// Safety from rep exposure:
	// ��a,b,c����Ϊprivate
	
	/**
	 * ���췽��
	 * @param a һ��λ�õ���
	 * @param b һ����ʦ��Դ����
	 * @param c ����������ʱ��Ե���
	 */
	public CourseEntry(OneLocationEntryImpl a,OneDistinguishResourceEntryImpl<Teacher> b,NoBlockableEntryImpl c) {
		this.a=a;
		this.b=b;
		this.c=c;
	}

	/**
	 * ���ø�λ��
	 * @param only ��λ��
	 * @return ��λ���Ƿ����óɹ�
	 */
	@Override
	public boolean setlocations(Location only) {
		return a.setlocations(only);
	}

	/**
	 * ���ĸ�λ��
	 * @param only ���ĺ��λ��
	 * @return λ���Ƿ���ĳɹ�
	 */
	@Override
	public boolean changelocations(Location only) {
		return a.changelocations(only);
	}

	/**
	 * ɾ����λ��
	 * @param waittodelete ��ɾ����λ��
	 * @return λ���Ƿ�ɾ���ɹ�
	 */
	@Override
	public boolean deletelocations(Location waittodelete) {
		return a.deletelocations(waittodelete);
	}
	
	/**
	 * �õ���λ��
	 * @return ��λ��
	 */
	@Override
	public Location getlocations() {
		return a.getlocations();
	}

	/**
	 * ���øý�ʦ��Դ
	 * @param a �ý�ʦ��Դ
	 * @return �Ƿ�ɹ����øý�ʦ��Դ
	 */
	@Override
	public boolean setresource(Teacher a) {
		return b.setresource(a);
	}

	/**
	 * �õ��ý�ʦ��Դ
	 * @return �ý�ʦ��Դ
	 */
	@Override
	public Teacher getresource() {
		return b.getresource();
	}
	
	/**
	 * ���ĸý�ʦ��Դ
	 * @param a ���ĺ�Ľ�ʦ��Դ
	 * @return �Ƕ��ɹ����ĸý�ʦ��Դ
	 */
	@Override
	public boolean changeresource(Teacher a) {
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
	public int compareTo(CourseEntry<Teacher> o) {
		if(c.gettimeslot().getbegintime().compareTo(o.gettimeslot().getbegintime())>0) {
			return 1;
		}
		else if(c.gettimeslot().getbegintime().compareTo(o.gettimeslot().getbegintime())==0) {
			return 0;
		}
		return -1;
	}
	
	 @Override
	  public CourseEntry<Teacher> clone() { 
		CourseEntry<Teacher> stu = null; 
	    try{ 
	      stu = (CourseEntry<Teacher>)super.clone(); 
	    }catch(CloneNotSupportedException e) { 
	      e.printStackTrace(); 
	    } 
	    stu.a=(OneLocationEntryImpl)a.clone();
	    stu.b=(OneDistinguishResourceEntryImpl<Teacher>)b.clone();
	    stu.c=(NoBlockableEntryImpl)c.clone();
	    return stu; 
	  } 

}
