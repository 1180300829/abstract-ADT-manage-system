package PlanningEntry;

import Timeslot.Timeslot;

public class NoBlockableEntryImpl implements Cloneable,NoBlockableEntry {

	private Timeslot mytime;
	
	// mutability��
	// Abstraction function:
	// AF(mytime)=��ʱ���
	// Representation invariant:
	// �����ʱ��Բ���Ϊ��
	// Safety from rep exposure:
	// ��mytime����Ϊprivate

	// TODO checkRep
    private void checkRep() {  //��֤ʱ��Բ�Ϊ��
    	assert mytime!=null;
    }
	
	/**
	 * �жϸ����Ƿ������
	 * @return �����Ƿ������
	 */
	@Override
	public boolean whetherblockable() {
		System.out.println("��������");
		return false;
	}

	/**
	 * ���ø�ʱ���
	 * @param mytime ʱ���
	 * @return �Ƿ�ɹ����ø�ʱ���
	 */
	@Override
	public boolean settimeslot(Timeslot time) {
		if(mytime==null){
			this.mytime=time;
			System.out.println("ʱ�����óɹ�");
			checkRep();
			return true;
		}
		System.out.println("ʱ��ֻ������һ��");
		return false;
	}

	/**
	 * �õ���ʱ���
	 * @return ��ʱ���
	 */
	@Override
	public Timeslot gettimeslot() {
		checkRep();
		return mytime;
	}
	
	@Override
	  public NoBlockableEntryImpl clone() { 
		NoBlockableEntryImpl stu = null; 
	    try{ 
	      stu = (NoBlockableEntryImpl)super.clone(); 
	    }catch(CloneNotSupportedException e) { 
	      e.printStackTrace(); 
	    } 
	    return stu; 
	  } 

}
