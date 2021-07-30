package PlanningEntry;

import static org.junit.Assert.*;

import org.junit.Test;

import EntryState.CourseWaitingState;
import Resource.Carriage;

public class CommonPlanningEntryTest {

	/* Testing strategy
	 * ����CommonPlanningEntry�����ܼ򵥣�����Ҫ����
	 * ֱ�Ӵ���һ��CommonPlanningEntry�࣬��ɶ��������з����Ĳ���
     */
	@Test
	public void allmethodstest() {
		CommonPlanningEntry tempentry=new CommonPlanningEntry<>();
		tempentry=tempentry.createplanningentry();
		tempentry.setplanningentryname("�������");
		CourseWaitingState haha=CourseWaitingState.instance;
		tempentry.setcurrentstate(haha);
		
		assertEquals(true,tempentry.launch("����"));
		assertEquals(false,tempentry.launch("����"));
		assertEquals(true,tempentry.cancel("ȡ��"));
		assertEquals(false,tempentry.cancel("����"));
		assertEquals(true,tempentry.finish("����"));
		assertEquals(false,tempentry.finish("����"));
		
		assertEquals("�������",tempentry.getplanningentryname());
		assertEquals(haha,tempentry.getcurrentstate());
	}

}
