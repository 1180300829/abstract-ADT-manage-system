package Location;

public class FlightTrainLocation extends CommonLocation{

	/**
	 * ���췽��
	 * @param longitude γ��
	 * @param latitude ����
	 * @param locationname ��λ������
	 */
	public FlightTrainLocation(String longitude, String latitude, String locationname) {
		super(longitude, latitude, locationname);
	}

	/**
	 * �жϸ�λ���Ƿ�ɹ���
	 * @return �ɹ�����true�����ɹ�����false
	 */
	public boolean whethershare() {
		System.out.println("λ�ÿɹ���");
		return true;
	}
}
