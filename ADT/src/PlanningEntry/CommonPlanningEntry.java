package PlanningEntry;


public class CommonPlanningEntry<R> implements PlanningEntry<R> {

	private String planningentryname; //�ƻ�������
	private R currentstate;  //�ƻ��ǰ״̬
	
	// mutability��
	// Abstraction function:
	// AF(planningentryname)=�ƻ�������
	// AF(currentstate)=�ƻ��ǰ״̬
	// Representation invariant:
	// �������ָ����м��
	// Safety from rep exposure:
	// ��planningentryname,currentstate����Ϊprivate
	
	/**
	 * ���췽��
	 */
	public CommonPlanningEntry(){
	}
	
	/**
	 * ���üƻ��������
	 * @param planningentryname �����õ�����
	 */
	@Override
	public void setplanningentryname(String planningentryname) {
		this.planningentryname=planningentryname;
	}
	
	/**
	 * ���üƻ��ǰ��״̬
	 * @param currentstate �����õ�״̬
	 */
	@Override
	public void setcurrentstate(R currentstate) {
		this.currentstate=currentstate;
	}
	
	/**
	 * ����һ���ƻ���
	 * @return ����ļƻ���
	 */
	@Override
	public CommonPlanningEntry<R> createplanningentry() {
		return new CommonPlanningEntry<R>();
	}

	/**
	 * ����һ���ƻ���
	 * @param a ������ָ��
	 * @return �Ƿ�ɹ�����
	 */
	@Override
	public boolean launch(R a) {
		if(a.equals("����")) {
			System.out.println("�ƻ���������");
			return true;
		}
		else {
			System.out.println("��������ȷָ��");
			return false;
		}
	}

	/**
	 * ȡ��һ���ƻ���
	 * @param a ȡ����ָ��
	 * @return �Ƿ�ɹ�ȡ��
	 */
	@Override
	public boolean cancel(R a) {
		if(a.equals("ȡ��")) {
			System.out.println("�ƻ�����ȡ��");
			return true;
		}
		else {
			System.out.println("��������ȷָ��");
			return false;
		}
	}

	/**
	 * ����һ���ƻ���
	 * @param a ������ָ��
	 * @return �Ƿ�ɹ�����
	 */
	@Override
	public boolean finish(R a) {
		if(a.equals("����")) {
			System.out.println("�ƻ����ѽ���");
			return true;
		}
		else {
			System.out.println("��������ȷָ��");
			return false;
		}
	}

	/**
	 * �õ��ƻ��������
	 * @return �ƻ��������
	 */
	@Override
	public String getplanningentryname() {
		return planningentryname;
	}

	/**
	 * �õ���ǰ�ƻ����״̬
	 * @return
	 */
	@Override
	public R getcurrentstate() {
		return currentstate;
	}

}
