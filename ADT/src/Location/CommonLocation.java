package Location;

public class CommonLocation implements Location{

	private final String longitude;  //λ�þ���
	private final String latitude;   //λ��γ��
	private final String locationname;  //λ������
	
	// inmutability��
	// Abstraction function:
	// AF(longitude)=��λ�þ���
	// AF(longitude)=��λ��γ��
	// AF(longitude)=��λ������
	// Representation invariant:
	// λ�þ���,λ��γ��,λ�����Ʋ�Ϊ��
	// Safety from rep exposure:
	// ��longitude,latitude,locationname����Ϊprivate final
	// ����SringΪinmutable���ͣ�����Ҫ����defensive copy
	
	// TODO checkRep
    private void checkRep() {  //λ�þ���,λ��γ��,λ�����Ʋ�Ϊ��
          assert longitude!=null;
          assert latitude!=null;
          assert locationname!=null;
    }
	
	/**
	 * ���췽��
	 * @param longitude γ��
	 * @param latitude ����
	 * @param locationname ��λ������
	 */
	public CommonLocation(String longitude,String latitude,String locationname) {
		this.longitude=longitude;
		this.latitude=latitude;
		this.locationname=locationname;
		checkRep();
	}

	/**
	 * �õ���λ�õĵ�γ��
	 * @return ��λ�õ�γ��
	 */
	@Override
	public String getlongitude() {
		checkRep();
		return longitude;
	}

	/**
	 * �õ���λ�õĵľ���
	 * @return ��λ�õľ���
	 */
	@Override
	public String getlatitude() {
		checkRep();
		return latitude;
	}

	/**
	 * �õ���λ�õĵ�����
	 * @return ��λ�õ�����
	 */
	@Override
	public String getlocationname() {
		checkRep();
		return locationname;
	}

	/**
	 * ��дhashcode����
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((locationname == null) ? 0 : locationname.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
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
		CommonLocation other = (CommonLocation) obj;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (locationname == null) {
			if (other.locationname != null)
				return false;
		} else if (!locationname.equals(other.locationname))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}

}
