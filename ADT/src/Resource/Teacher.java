package Resource;

public class Teacher {

	private final String idnumber;       //��ʦ���֤��
	private final String teachername;    //��ʦ����
	private final String teachergender;  //��ʦ�Ա�
	private final String teachertitle;   //��ʦְ��
	
	// immutability��
	// Abstraction function:
    // AF(idnumber)=��ʦ���֤��
	// AF(teachername)=��ʦ����
	// AF(teachergender)=��ʦ�Ա�
	// AF(teachertitle)=��ʦְ��
	// Representation invariant:
	// ��ʦ���֤��,��ʦ����,��ʦ�Ա�,��ʦְ�Ʋ�Ϊ��
	// Safety from rep exposure:
	// ��idnumber,teachername,teachergender,teachertitle����Ϊprivate final

	// TODO checkRep
    private void checkRep() {  //��ʦ���֤��,��ʦ����,��ʦ�Ա�,��ʦְ�Ʋ�Ϊ��
          assert idnumber!=null;
          assert teachername!=null;
          assert teachergender!=null;
          assert teachertitle!=null;
    }
	
	/**
	 * ��ʼ�����췽��
	 * @param a ��ʦ���֤��
	 * @param b ��ʦ����
	 * @param c ��ʦ�Ա�
	 * @param d ��ʦְ��
	 */
	public Teacher(String a,String b,String c,String d) {
		this.idnumber=a;
		this.teachername=b;
		this.teachergender=c;
		this.teachertitle=d;
		checkRep();
	}
	
	/**
	 * ���ؽ�ʦ���֤��
	 * @return ��ʦ���֤��
	 */
	public String getidnumber() {
		checkRep();
		return idnumber;
	}
	
	/**
	 * ���ؽ�ʦ����
	 * @return ��ʦ����
	 */
	public String getteachername() {
		checkRep();
		return teachername;
	}
	
	/**
	 * ���ؽ�ʦ�Ա�
	 * @return ��ʦ�Ա�
	 */
	public String getteachergender() {
		checkRep();
		return teachergender;
	}
	
	/**
	 * ���ؽ�ʦְ��
	 * @return ��ʦְ��
	 */
	public String getteachertitle() {
		checkRep();
		return teachertitle;
	}

	/**
	 * ��дhashcode����
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idnumber == null) ? 0 : idnumber.hashCode());
		result = prime * result + ((teachergender == null) ? 0 : teachergender.hashCode());
		result = prime * result + ((teachername == null) ? 0 : teachername.hashCode());
		result = prime * result + ((teachertitle == null) ? 0 : teachertitle.hashCode());
		return result;
	}

	/**
	 * ��дequals����
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		if (idnumber == null) {
			if (other.idnumber != null)
				return false;
		} else if (!idnumber.equals(other.idnumber))
			return false;
		if (teachergender == null) {
			if (other.teachergender != null)
				return false;
		} else if (!teachergender.equals(other.teachergender))
			return false;
		if (teachername == null) {
			if (other.teachername != null)
				return false;
		} else if (!teachername.equals(other.teachername))
			return false;
		if (teachertitle == null) {
			if (other.teachertitle != null)
				return false;
		} else if (!teachertitle.equals(other.teachertitle))
			return false;
		return true;
	}
}
