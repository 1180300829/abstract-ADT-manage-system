package PlanningEntry;

import Location.*;

public interface OneLocationEntry {

	/**
	 * ���ø�λ��
	 * @param only ��λ��
	 * @return ��λ���Ƿ����óɹ�
	 */
	public boolean setlocations(Location only);
	
	/**
	 * ���ĸ�λ��
	 * @param only ���ĺ��λ��
	 * @return λ���Ƿ���ĳɹ�
	 */
	public boolean changelocations(Location only);
	
	/**
	 * ɾ����λ��
	 * @param waittodelete ��ɾ����λ��
	 * @return λ���Ƿ�ɾ���ɹ�
	 */
	public boolean deletelocations(Location waittodelete);
	
	/**
	 * �õ���λ��
	 * @return ��λ��
	 */
	public Location getlocations();
}
