package PlanningEntry;

import java.util.List;

import Exception.SameResourceException;



public interface MultipleSortedResourceEntry<R> {

	/**
	 * ���ø���Դ����
	 * @param source ��Դ����
	 * @return �Ƿ�ɹ����ø���Դ����
	 * @throws SameResourceException ������ͬ����Դ
	 */
	public boolean setresource(List<R> source) throws SameResourceException;
	
	/**
	 * �õ�����Դ����
	 * @return ����Դ����
	 */
	public List<R> getresource();
	
	/**
	 * ����ĳ��Դ
	 * @param presource �����ĵ���Դ
	 * @param aftersource ���ĺ����Դ
	 * @return �Ƿ�ɹ�������Դ
	 */
	public boolean changeresource(R presource,R aftersource);
	
	/**
	 * ����Դ�����������һ����Դ
	 * @param source ���������Դ
	 * @param temp ������Դ��λ��
	 * @return �Ƿ�ɹ�������Դ
	 */
	public boolean addresource(R source,int temp);
	
	/**
	 * ɾ����Դ�����е�ĳ��Դ
	 * @param source ��ɾ������Դ
	 * @return �Ƿ�ɹ�ɾ����Դ
	 */
	public boolean deleteresource(R source);

	
}
