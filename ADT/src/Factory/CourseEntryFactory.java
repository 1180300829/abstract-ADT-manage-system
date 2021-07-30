package Factory;

import PlanningEntry.*;
import Resource.*;

public class CourseEntryFactory implements PlanningEntryFactory{

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
	 * д�ɿշ���
	 */
	@Override
	public PlanningEntry getTrainEntry(MultipleLacationEntryImpl a, MultipleSortedResourceEntryImpl<Carriage> b,
			BlockableEntryImpl c) {
		// TODO �Զ����ɵķ������
		return null;
	}

	/**
	 * ����CourseEntry����
	 * @param a һ��λ�õ���
	 * @param b һ����ʦ��Դ����
	 * @param c ����������ʱ��Ե���
	 * @return ������CourseEntry����
	 */
	@Override
	public PlanningEntry getCourseEntry(OneLocationEntryImpl a, OneDistinguishResourceEntryImpl<Teacher> b,
			NoBlockableEntryImpl c) {
		// TODO �Զ����ɵķ������
		return new CourseEntry<Teacher>(a,b,c);
	}

}
