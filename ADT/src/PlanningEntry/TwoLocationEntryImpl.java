package PlanningEntry;


import Exception.SameLocationException;
import Location.*;

public class TwoLocationEntryImpl implements Cloneable,TwoLocationEntry{

	private Location fromlocation;  //���վ
	private Location tolocation;   //�յ�վ
	
	    // mutability��
		// Abstraction function:
	    // AF(fromlocation)=���վ
		// AF(tolocation)=�յ�վ
		// Representation invariant:
	    // ֻ������һ�����վ���յ�վ����������λ��Ӧ�ò�ͬ,λ�ò�Ϊ��
	    // Safety from rep exposure:
	    // ��fromlocation,tolocation����Ϊprivate
	
	// TODO checkRep
    private void checkRep() {  //��֤�����ɻ���λ�ò�ͬ
    	assert fromlocation!=null;
    	assert tolocation!=null;
        assert !fromlocation.equals(tolocation):"�����ظ��������ɻ���λ��\n";
    }
	
	/**
	 * �������վ���յ�վ��λ��
	 * @param from ���վ��λ��
	 * @param to �յ�վ��λ��
	 * @return �Ƿ����óɹ�
	 * @throws SameLocationException �����ظ��������ɻ���λ��
	 */
	@Override
	public boolean setlocations(Location from, Location to) throws SameLocationException {
		if(from.equals(to)) {
			throw new SameLocationException();  //λ����ͬ�׳��쳣
		}
		if(fromlocation==null&&tolocation==null) {
			this.fromlocation=from;
			this.tolocation=to;
			System.out.println("λ�����óɹ�");
			checkRep();
			return true;
		}
		System.out.println("λ��ֻ������һ��");
		return false;
	}

	
	/**
	 * �������վ���յ�վ��λ��
	 * @param from ���վ��λ��
	 * @param to �յ�վ��λ��
	 * @return �Ƿ����óɹ�
	 */
	@Override
	public boolean changelocations(Location from, Location to) {
		    checkRep();
			System.out.println("λ�ò��ɸ���");
			return false;
	}

	/**
	 * �õ����վ��λ��
	 * @return ���վ
	 */
	@Override
	public Location getfromlocation() {
		checkRep();
		return fromlocation;
	}

	/**
	 * �õ��յ�վ��λ��
	 * @return ���վ
	 */
	@Override
	public Location gettolocation() {
		checkRep();
		return tolocation;
	}
	
	 @Override
	  public TwoLocationEntryImpl clone() { 
		 TwoLocationEntryImpl stu = null; 
	    try{ 
	      stu = (TwoLocationEntryImpl)super.clone(); 
	    }catch(CloneNotSupportedException e) { 
	      e.printStackTrace(); 
	    } 
	    return stu; 
	  } 
	

}
