package PlanningEntry;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import Exception.BeginEndTimeException;
import Factory.CourseEntryFactory;
import Location.CourseLocation;
import Resource.Teacher;
import Timeslot.Timeslot;

public class CourseEntryTest {
	
	/**
	 * �ڲ��Ը���Ĺ������Ѿ�����˶���ί�з����Ĳ���
	 */

	/* Testing strategy
	 * ����setlocations����
	 * ����λ���Ƿ��ѱ����û��֣�λ���ѱ����ã�λ��δ������
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void setlocationstest() {
		String weidu="��γ40��",jingdu="����112��";
		OneLocationEntryImpl a1;
		OneDistinguishResourceEntryImpl<Teacher> b1;
		NoBlockableEntryImpl c1;
		a1=new OneLocationEntryImpl();
		b1=new OneDistinguishResourceEntryImpl<Teacher>();
		c1=new NoBlockableEntryImpl();
		PlanningEntry origincourse1=new CourseEntryFactory().getCourseEntry(a1, b1, c1); //�ù���������������
		CourseEntry<Teacher> course1=(CourseEntry<Teacher>)origincourse1;
		
		assertEquals(true,course1.setlocations(new CourseLocation(weidu,jingdu,"����¥32")));
		assertEquals(false,course1.setlocations(new CourseLocation(weidu,jingdu,"����¥32")));
	}

	/* Testing strategy
	 * ����changelocations����
	 * ����λ���Ƿ��ԭλ���ظ����֣��ظ������ظ�
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void changelocationstest() {
		String weidu="��γ40��",jingdu="����112��";
		OneLocationEntryImpl a1;
		OneDistinguishResourceEntryImpl<Teacher> b1;
		NoBlockableEntryImpl c1;
		a1=new OneLocationEntryImpl();
		b1=new OneDistinguishResourceEntryImpl<Teacher>();
		c1=new NoBlockableEntryImpl();
		PlanningEntry origincourse1=new CourseEntryFactory().getCourseEntry(a1, b1, c1); //�ù���������������
		CourseEntry<Teacher> course1=(CourseEntry<Teacher>)origincourse1;
		
		course1.setlocations(new CourseLocation(weidu,jingdu,"����¥32"));
		assertEquals(true,course1.changelocations(new CourseLocation(weidu,jingdu,"����¥32")));	
		assertEquals(false,course1.changelocations(new CourseLocation(weidu,jingdu,"����¥32")));
	}
	
	/* Testing strategy
	 * ����deletelocations����
	 * ���մ�ɾ����λ���Ƿ���ڻ��֣����ڣ�������
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void deletelocationstest() {
		String weidu="��γ40��",jingdu="����112��";
		OneLocationEntryImpl a1;
		OneDistinguishResourceEntryImpl<Teacher> b1;
		NoBlockableEntryImpl c1;
		a1=new OneLocationEntryImpl();
		b1=new OneDistinguishResourceEntryImpl<Teacher>();
		c1=new NoBlockableEntryImpl();
		PlanningEntry origincourse1=new CourseEntryFactory().getCourseEntry(a1, b1, c1); //�ù���������������
		CourseEntry<Teacher> course1=(CourseEntry<Teacher>)origincourse1;
		
		course1.setlocations(new CourseLocation(weidu,jingdu,"����¥32"));
		assertEquals(false,course1.deletelocations(new CourseLocation(weidu,jingdu,"����¥32")));	
		assertEquals(true,course1.deletelocations(new CourseLocation(weidu,jingdu,"����¥32")));
	}
	
	/* Testing strategy
	 * ����getlocations����
     * ���Է���ֵ����
     */
	@Test
	public void getlocationstest() {
		String weidu="��γ40��",jingdu="����112��";
		OneLocationEntryImpl a1;
		OneDistinguishResourceEntryImpl<Teacher> b1;
		NoBlockableEntryImpl c1;
		a1=new OneLocationEntryImpl();
		b1=new OneDistinguishResourceEntryImpl<Teacher>();
		c1=new NoBlockableEntryImpl();
		PlanningEntry origincourse1=new CourseEntryFactory().getCourseEntry(a1, b1, c1); //�ù���������������
		CourseEntry<Teacher> course1=(CourseEntry<Teacher>)origincourse1;
		
		course1.setlocations(new CourseLocation(weidu,jingdu,"����¥32"));
		assertEquals(new CourseLocation(weidu,jingdu,"����¥32"),course1.getlocations());	
	}
	
	/* Testing strategy
	 * ����setresource����
	 * ������Դ�Ƿ��ѱ����û��֣���Դ�ѱ����ã���Դδ������
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void setresourcetest() {
		OneLocationEntryImpl a1;
		OneDistinguishResourceEntryImpl<Teacher> b1;
		NoBlockableEntryImpl c1;
		a1=new OneLocationEntryImpl();
		b1=new OneDistinguishResourceEntryImpl<Teacher>();
		c1=new NoBlockableEntryImpl();
		PlanningEntry origincourse1=new CourseEntryFactory().getCourseEntry(a1, b1, c1); //�ù���������������
		CourseEntry<Teacher> course1=(CourseEntry<Teacher>)origincourse1;
		
		assertEquals(true,course1.setresource(new Teacher("422823199812254452","����","��","��ʦ")));
		assertEquals(false,course1.setresource(new Teacher("422823199812254452","����","��","��ʦ")));
	}
	
	/* Testing strategy
	 * ����getresource����
	 * ���Է���ֵ����
     */
	@Test
	public void getresourcetest() {
		OneLocationEntryImpl a1;
		OneDistinguishResourceEntryImpl<Teacher> b1;
		NoBlockableEntryImpl c1;
		a1=new OneLocationEntryImpl();
		b1=new OneDistinguishResourceEntryImpl<Teacher>();
		c1=new NoBlockableEntryImpl();
		PlanningEntry origincourse1=new CourseEntryFactory().getCourseEntry(a1, b1, c1); //�ù���������������
		CourseEntry<Teacher> course1=(CourseEntry<Teacher>)origincourse1;
		
		course1.setresource(new Teacher("422823199812254452","����","��","��ʦ"));
		assertEquals(new Teacher("422823199812254452","����","��","��ʦ"),course1.getresource());
	}
	
	/* Testing strategy
	 * ����changeresource����
	 * �����Ƿ���ԭ��ʦ�ظ����֣��ظ������ظ�
	 * ����ÿ��ȡֵ���£�
     */
	@Test
	public void changeresourcetest() {
		OneLocationEntryImpl a1;
		OneDistinguishResourceEntryImpl<Teacher> b1;
		NoBlockableEntryImpl c1;
		a1=new OneLocationEntryImpl();
		b1=new OneDistinguishResourceEntryImpl<Teacher>();
		c1=new NoBlockableEntryImpl();
		PlanningEntry origincourse1=new CourseEntryFactory().getCourseEntry(a1, b1, c1); //�ù���������������
		CourseEntry<Teacher> course1=(CourseEntry<Teacher>)origincourse1;
		
		course1.setresource(new Teacher("422823199812254452","����","��","��ʦ"));
		assertEquals(false,course1.changeresource(new Teacher("422823199812254452","����","��","��ʦ")));
		assertEquals(true,course1.changeresource(new Teacher("422823199812211111","����","��","��ʦ")));
	}
	
	/* Testing strategy
	 * ����whetherblockable����
	 * ���Է���ֵ����
     */
	@Test
	public void whetherblockabletest() {
		OneLocationEntryImpl a1;
		OneDistinguishResourceEntryImpl<Teacher> b1;
		NoBlockableEntryImpl c1;
		a1=new OneLocationEntryImpl();
		b1=new OneDistinguishResourceEntryImpl<Teacher>();
		c1=new NoBlockableEntryImpl();
		PlanningEntry origincourse1=new CourseEntryFactory().getCourseEntry(a1, b1, c1); //�ù���������������
		CourseEntry<Teacher> course1=(CourseEntry<Teacher>)origincourse1;
		assertEquals(false,course1.whetherblockable());
	}
	
	/* Testing strategy
	 * ����settimeslot����
	 * ����ʱ���Ƿ��ѱ����û��֣��ѱ����ã�δ������
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void settimeslottest() throws ParseException, BeginEndTimeException{
		OneLocationEntryImpl a1;
		OneDistinguishResourceEntryImpl<Teacher> b1;
		NoBlockableEntryImpl c1;
		a1=new OneLocationEntryImpl();
		b1=new OneDistinguishResourceEntryImpl<Teacher>();
		c1=new NoBlockableEntryImpl();
		PlanningEntry origincourse1=new CourseEntryFactory().getCourseEntry(a1, b1, c1); //�ù���������������
		CourseEntry<Teacher> course1=(CourseEntry<Teacher>)origincourse1;
		
		assertEquals(true,course1.settimeslot(new Timeslot("2020-01-01 15:45","2020-01-01 17:30")));
		assertEquals(false,course1.settimeslot(new Timeslot("2020-01-01 15:45","2020-01-01 17:30")));
	}
	
	/* Testing strategy
	 * ����gettimeslot����
	 * ���Է���ֵ����
     */
	@Test
	public void gettimeslottest() throws ParseException, BeginEndTimeException{
		OneLocationEntryImpl a1;
		OneDistinguishResourceEntryImpl<Teacher> b1;
		NoBlockableEntryImpl c1;
		a1=new OneLocationEntryImpl();
		b1=new OneDistinguishResourceEntryImpl<Teacher>();
		c1=new NoBlockableEntryImpl();
		PlanningEntry origincourse1=new CourseEntryFactory().getCourseEntry(a1, b1, c1); //�ù���������������
		CourseEntry<Teacher> course1=(CourseEntry<Teacher>)origincourse1;
		
		course1.settimeslot(new Timeslot("2020-01-01 15:45","2020-01-01 17:30"));
		assertEquals(new Timeslot("2020-01-01 15:45","2020-01-01 17:30"),course1.gettimeslot());
	}
	
	/* Testing strategy
	 * ����compareTo����
	 * ������ʼʱ���С���֣����ڣ����ڣ�С��
     * ����ÿ��ȡֵ���£�
     */
	@Test
	public void compareTotest() throws ParseException, BeginEndTimeException{
		OneLocationEntryImpl a1;
		OneDistinguishResourceEntryImpl<Teacher> b1;
		NoBlockableEntryImpl c1;
		a1=new OneLocationEntryImpl();
		b1=new OneDistinguishResourceEntryImpl<Teacher>();
		c1=new NoBlockableEntryImpl();
		PlanningEntry origincourse1=new CourseEntryFactory().getCourseEntry(a1, b1, c1); //�ù���������������
		CourseEntry<Teacher> course1=(CourseEntry<Teacher>)origincourse1;
		course1.settimeslot(new Timeslot("2020-01-01 15:45","2020-01-01 17:30"));

		OneLocationEntryImpl a2;
		OneDistinguishResourceEntryImpl<Teacher> b2;
		NoBlockableEntryImpl c2;
		a2=new OneLocationEntryImpl();
		b2=new OneDistinguishResourceEntryImpl<Teacher>();
		c2=new NoBlockableEntryImpl();
		PlanningEntry origincourse2=new CourseEntryFactory().getCourseEntry(a2, b2, c2); //�ù���������������
		CourseEntry<Teacher> course2=(CourseEntry<Teacher>)origincourse2;
		course2.settimeslot(new Timeslot("2020-01-01 15:45","2020-01-01 17:30"));

		
		OneLocationEntryImpl a3;
		OneDistinguishResourceEntryImpl<Teacher> b3;
		NoBlockableEntryImpl c3;
		a3=new OneLocationEntryImpl();
		b3=new OneDistinguishResourceEntryImpl<Teacher>();
		c3=new NoBlockableEntryImpl();
		PlanningEntry origincourse3=new CourseEntryFactory().getCourseEntry(a3, b3, c3); //�ù���������������
		CourseEntry<Teacher> course3=(CourseEntry<Teacher>)origincourse3;
		course3.settimeslot(new Timeslot("2020-02-01 16:45","2020-02-01 18:30"));
		
		assertEquals(1,course3.compareTo(course1));
		assertEquals(0,course1.compareTo(course2));
		assertEquals(-1,course1.compareTo(course3));
	
	}
}
