package PlanningEntry;

import java.util.List;

import Exception.SameLocationException;
import Location.*;

public interface MultipleLocationEntry {

	/**
	 * ����һ��λ��
	 * @param locations �����õ�һ��λ��
	 * @return �Ƿ�ɹ�������һ��λ��
	 * @throws SameLocationException ������ͬλ��
	 */
	public boolean setlocations(List<Location> locations) throws SameLocationException;
	
	/**
	 * ������һ��λ��
	 * @param mylocations ���ĺ��λ��
	 * @return �Ƕ��ɹ����ĸ�λ��
	 */
	public boolean changelocations(List<Location> mylocations);
	
	/**
	 * �õ���һ��λ��
	 * @return ��һ���λ��
	 */
	public List<Location> getlocations();
	
}
