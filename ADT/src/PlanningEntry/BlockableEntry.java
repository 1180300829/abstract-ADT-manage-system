package PlanningEntry;

import java.util.List;

import Exception.ConflictTimeException;
import Timeslot.Timeslot;

public interface BlockableEntry {

	/**
	 * �ж��Ƿ������
	 * @return �Ƿ������
	 */
    public boolean whetherblockable();
	
    /**
     * ������һ��ʱ���
     * @param alltime һ��ʱ��� 
     * @return �Ƿ�ɹ�������һ��ʱ���
     * @throws ConflictTimeException ĳ��վ�ִ�ʱ�����ڳ���ʱ��
     */
	public boolean settimeslot(List<Timeslot> alltime) throws ConflictTimeException;
	
	/**
	 * �õ���һ��ʱ���
	 * @return ��һ��ʱ���
	 */
	public List<Timeslot> gettimeslot();
	
	
}
