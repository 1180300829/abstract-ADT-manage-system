package PlanningEntryAPIs;

import java.util.List;

import PlanningEntry.PlanningEntry;

public interface StrategycheckLocationConflict {
	
	/**
	 * ���һ��ƻ���֮���Ƿ����λ�ö�ռ��ͻ
	 * @param entries �ƻ����
	 * @return �Ƿ����λ�ö�ռ��ͻ
	 */
	public boolean checkLocationConflict(List<PlanningEntry> entries);

}
