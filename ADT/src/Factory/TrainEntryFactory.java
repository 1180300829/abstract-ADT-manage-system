package Factory;

import PlanningEntry.*;
import Resource.*;


public class TrainEntryFactory implements PlanningEntryFactory{

	/**
	 * д�ɿշ���
	 */
	@Override
	public PlanningEntry getFlightEntry(TwoLocationEntryImpl a, OneDistinguishResourceEntryImpl<Flight> b,
			NoBlockableEntryImpl c) {
		// TODO �Զ����ɵķ������
		return null;
	}

	/**
	 * ����TrainEntry����
	 * @param a ���λ�õ���
	 * @param b ���������Դ����
	 * @param c ��������ʱ��Ե���
	 * @return ������TrainEntry����
	 */
	@Override
	public PlanningEntry getTrainEntry(MultipleLacationEntryImpl a, MultipleSortedResourceEntryImpl<Carriage> b,
			BlockableEntryImpl c) {
		return new TrainEntry(a,b,c);
	}

	/**
	 * д�ɿշ���
	 */
	@Override
	public PlanningEntry getCourseEntry(OneLocationEntryImpl a, OneDistinguishResourceEntryImpl<Teacher> b,
			NoBlockableEntryImpl c) {
		// TODO �Զ����ɵķ������
		return null;
	}

}
