package PlanningEntry;

import Timeslot.Timeslot;

public interface NoBlockableEntry {

	/**
	 * �жϸ����Ƿ������
	 * @return �����Ƿ������
	 */
	public boolean whetherblockable();
	
	/**
	 * ���ø�ʱ���
	 * @param mytime ʱ���
	 * @return �Ƿ�ɹ����ø�ʱ���
	 */
	public boolean settimeslot(Timeslot mytime);
	
	/**
	 * �õ���ʱ���
	 * @return ��ʱ���
	 */
	public Timeslot gettimeslot();
}
