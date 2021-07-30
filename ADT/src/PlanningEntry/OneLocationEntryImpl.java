package PlanningEntry;

import Location.*;

public class OneLocationEntryImpl implements Cloneable,OneLocationEntry {

	private Location onlylocation; //��λ��
	
	    // mutability��
		// Abstraction function:
		// AF(onlylocation)=��λ��
		// Representation invariant:
		// �����λ�ò���Ϊ��
		// Safety from rep exposure:
		// ��onlylocation����Ϊprivate
	
	// TODO checkRep
    private void checkRep() {  //��֤λ�ò�Ϊ��
    	assert onlylocation!=null:"����λ��Ϊ��";
    }
	
	/**
	 * ���ø�λ��
	 * @param only ��λ��
	 * @return ��λ���Ƿ����óɹ�
	 */
	@Override
	public boolean setlocations(Location only) {
		if(onlylocation==null&&only!=null) {
			this.onlylocation=only;	
			System.out.println("λ�����óɹ�");
			checkRep();
			return true;
		}
		System.out.println("λ��ֻ������һ��");
		return false;
	}

	/**
	 * ���ĸ�λ��
	 * @param only ���ĺ��λ��
	 * @return λ���Ƿ���ĳɹ�
	 */
	@Override
	public boolean changelocations(Location only) {
		    checkRep();
		    if(only.equals(onlylocation)) {
		    	System.out.println("��ԭλ���ظ�");
		    	return false;
		    }
			this.onlylocation=only;
			System.out.println("����λ���޸�(��������)�ɹ�");
			return true;
	}

	/**
	 * ɾ����λ��
	 * @param waittodelete ��ɾ����λ��
	 * @return λ���Ƿ�ɾ���ɹ�
	 */
	@Override
	public boolean deletelocations(Location waittodelete) {
		if(onlylocation.getlocationname().equals(waittodelete.getlocationname())) {
			onlylocation=null;
			System.out.println("����λ��ɾ���ɹ�");
			return true;
		}
		System.out.println("û�иý���");
		return false;
	}

	/**
	 * �õ���λ��
	 * @return ��λ��
	 */
	@Override
	public Location getlocations() {
		checkRep();
		return this.onlylocation;
	}

	 @Override
	  public OneLocationEntryImpl clone() { 
		 OneLocationEntryImpl stu = null; 
	    try{ 
	      stu = (OneLocationEntryImpl)super.clone(); 
	    }catch(CloneNotSupportedException e) { 
	      e.printStackTrace(); 
	    } 
	    return stu; 
	  } 
}
