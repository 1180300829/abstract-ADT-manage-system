package PlanningEntry;

public interface OneDistinguishResourceEntry<R> {

	/**
	 * ���ø���Դ
	 * @param a ����Դ
	 * @return �Ƿ�ɹ����ø���Դ
	 */
	public boolean setresource(R a);
	
	/**
	 * �õ�����Դ
	 * @return ����Դ
	 */
	public R getresource();
	
	/**
	 * ���ĸ���Դ
	 * @param a ���ĺ����Դ
	 * @return �Ƕ��ɹ����ĸ���Դ
	 */
	public boolean changeresource(R a);


}
