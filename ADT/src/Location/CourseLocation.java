package Location;

public class CourseLocation extends CommonLocation{
	
	/**
	 * ���췽��
	 * @param longitude γ��
	 * @param latitude ����
	 * @param locationname ��λ������
	 */
    public CourseLocation(String longitude, String latitude, String locationname) {
		super(longitude, latitude, locationname);
	}

    /**
	 * �жϸ�λ���Ƿ�ɹ���
	 * @return �ɹ�����true�����ɹ�����false
	 */
	public boolean whethershare() {
		System.out.println("λ�ò��ɹ���");
		return false;
	}
}
