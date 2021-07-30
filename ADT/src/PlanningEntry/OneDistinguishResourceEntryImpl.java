package PlanningEntry;


public class OneDistinguishResourceEntryImpl<R> implements Cloneable, OneDistinguishResourceEntry<R>{

	private R mycource; //��Դ
	
	// mutability��
	// Abstraction function:
	// AF(mycource)=����Դ
	// Representation invariant:
	// �������Դ����Ϊ��
	// Safety from rep exposure:
	// ��mycource����Ϊprivate
	
	// TODO checkRep
    private void checkRep() {  //��֤��Դ��Ϊ��
    	assert mycource!=null;
    }
	
	/**
	 * ���ø���Դ
	 * @param a ����Դ
	 * @return �Ƿ�ɹ����ø���Դ
	 */
	@Override
	public boolean setresource(R a) {
		if(mycource==null) {
		    this.mycource=a;	
		    System.out.println("��Դ���óɹ�");
		    checkRep();
		    return true;
		}
		System.out.println("��Դֻ������һ��");
		return false;	
	}

	/**
	 * �õ��ý�ʦ��Դ
	 * @return �ý�ʦ��Դ
	 */
	@Override
	public R getresource() {
		return mycource;
	}

	/**
	 * ���ĸ���Դ
	 * @param a ���ĺ����Դ
	 * @return �Ƕ��ɹ����ĸ���Դ
	 */
	@Override
	public boolean changeresource(R a) {
		checkRep();
		if(a.equals(mycource)) {
			System.out.println("��ԭ��Դ�ظ�\n");
			return false;
		}
		this.mycource=a;
		System.out.println("��Դ���ĳɹ�\n");
		return true;
	}

	 @Override
	  public OneDistinguishResourceEntryImpl<R> clone() { 
		 OneDistinguishResourceEntryImpl<R> stu = null; 
	    try{ 
	      stu = (OneDistinguishResourceEntryImpl<R>)super.clone(); 
	    }catch(CloneNotSupportedException e) { 
	      e.printStackTrace(); 
	    } 
	    return stu; 
	  } 
}
