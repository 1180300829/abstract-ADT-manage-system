package PlanningEntry;

import Exception.SameLocationException;
import Location.*;

public interface TwoLocationEntry {
	
	/**
	 * �������վ���յ�վ��λ��
	 * @param from ���վ��λ��
	 * @param to �յ�վ��λ��
	 * @return �Ƿ����óɹ�
	 * @throws SameLocationException ��������λ����ͬ
	 */
	public boolean setlocations(Location from,Location to) throws SameLocationException;

	/**
	 * �������վ���յ�վ��λ��
	 * @param from ���վ��λ��
	 * @param to �յ�վ��λ��
	 * @return �Ƿ����óɹ�
	 */
	public boolean changelocations(Location from,Location to);
	
	/**
	 * �õ����վ��λ��
	 * @return ���վ
	 */
	public Location getfromlocation();
	
	/**
	 * �õ��յ�վ��λ��
	 * @return ���վ
	 */
	public Location gettolocation();
}
