package Factory;

import PlanningEntry.*;
import Resource.Carriage;
import Resource.Flight;
import Resource.Teacher;

public class FlightEntryFactory implements PlanningEntryFactory{

	/**
	 * ����FlightEntry����
	 * @param a ����λ�õ���
	 * @param b һ���ɻ���Դ����
	 * @param c ����������ʱ��Ե���
	 * @return ������FlightEntry����
	 */
	@Override
	public PlanningEntry getFlightEntry(TwoLocationEntryImpl a,OneDistinguishResourceEntryImpl<Flight> b,NoBlockableEntryImpl c) {
		return new FlightEntry<Flight>(a,b,c);
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
	 * д�ɿշ���
	 */
	@Override
	public PlanningEntry getCourseEntry(OneLocationEntryImpl a, OneDistinguishResourceEntryImpl<Teacher> b,
			NoBlockableEntryImpl c) {
		// TODO �Զ����ɵķ������
		return null;
	}

}
