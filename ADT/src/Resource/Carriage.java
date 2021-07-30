package Resource;

import Exception.LessThanZeroException;

public class Carriage {

	private final String carriagenumber;  //������
	private final String carriagetype;    //��������
	private final int carriageallseat;    //��Ա��
	private final String carriagbirth;    //�������
	
	// immutability��
	// Abstraction function:
    // AF(carriagenumber)=������
	// AF(carriagetype)=��������
	// AF(carriageallseat)=��Ա��
	// AF(carriagbirth)=�������
	// Representation invariant:
    // ��Ա��������,������,��������,��Ա����Ϊ��
	// Safety from rep exposure:
	// ��carriagenumber,carriagetype,carriageallseat,carriagbirth����Ϊprivate final
	
	// TODO checkRep
    private void checkRep() {  //��Ա��������,������,��������,��Ա����Ϊ��
          assert carriageallseat>0:"��Ա����Ϊ����\n";
          assert carriagenumber!=null;
          assert carriagetype!=null;
          assert carriagbirth!=null;
    }
	
	/**
	 * ��ʼ�����췽��
	 * @param a ������
	 * @param b ��������
	 * @param c ��Ա��:����
	 * @param d �������
	 * @throws LessThanZeroException ��Ա��Ϊ����
	 */
	public Carriage(String a,String b,int c,String d) throws LessThanZeroException {
		if(c<=0) {
			throw new LessThanZeroException();
		}
		this.carriagenumber=a;
		this.carriagetype=b;
		this.carriageallseat=c;
		this.carriagbirth=d;
		checkRep();
	}
	
	/**
	 * ���س�����
	 * @return ������
	 */
	public String getcarriagenumber() {
		checkRep();
		return carriagenumber;
	}
	
	/**
	 * ���س�������
	 * @return ��������
	 */
	public String getcarriagetype() {
		checkRep();
		return carriagetype;
	}
	
	/**
	 * ���ض�Ա��
	 * @return ��Ա��
	 */
	public int getcarriageallseat() {
		checkRep();
		return carriageallseat;
	}
	
	/**
	 * ���س���������
	 * @return ����������
	 */
	public String getcarriagbirth() {
		checkRep();
		return carriagbirth;
	}

	/**
	 * ��дhashcode����
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carriagbirth == null) ? 0 : carriagbirth.hashCode());
		result = prime * result + carriageallseat;
		result = prime * result + ((carriagenumber == null) ? 0 : carriagenumber.hashCode());
		result = prime * result + ((carriagetype == null) ? 0 : carriagetype.hashCode());
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
		Carriage other = (Carriage) obj;
		if (carriagbirth == null) {
			if (other.carriagbirth != null)
				return false;
		} else if (!carriagbirth.equals(other.carriagbirth))
			return false;
		if (carriageallseat != other.carriageallseat)
			return false;
		if (carriagenumber == null) {
			if (other.carriagenumber != null)
				return false;
		} else if (!carriagenumber.equals(other.carriagenumber))
			return false;
		if (carriagetype == null) {
			if (other.carriagetype != null)
				return false;
		} else if (!carriagetype.equals(other.carriagetype))
			return false;
		return true;
	}


}
