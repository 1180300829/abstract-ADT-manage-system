package PlanningEntry;

public interface PlanningEntry<R> {

	/**
	 * ����һ���ƻ���
	 * @return ����ļƻ���
	 */
	public CommonPlanningEntry<R> createplanningentry();
	
	/**
	 * ����һ���ƻ���
	 * @param a ������ָ��
	 * @return �Ƿ�ɹ�����
	 */
	public boolean launch(R a);
	
	/**
	 * ȡ��һ���ƻ���
	 * @param a ȡ����ָ��
	 * @return �Ƿ�ɹ�ȡ��
	 */
	public boolean cancel(R a);
	
	/**
	 * ����һ���ƻ���
	 * @param a ������ָ��
	 * @return �Ƿ�ɹ�����
	 */
	public boolean finish(R a);
	
	/**
	 * �õ��ƻ��������
	 * @return �ƻ��������
	 */
	public String getplanningentryname();
	
	/**
	 * �õ���ǰ�ƻ����״̬
	 * @return
	 */
	public R getcurrentstate();
	
	/**
	 * ���üƻ��������
	 * @param planningentryname �����õ�����
	 */
	public void setplanningentryname(String planningentryname);

	/**
	 * ���üƻ��ǰ��״̬
	 * @param currentstate �����õ�״̬
	 */
	public void setcurrentstate(R currentstate);

}
