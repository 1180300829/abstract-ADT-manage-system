package EntryState;



public interface FlightState {

	/**
	 * �ı䵱ǰ״̬
	 * @param c �ı�ָ��
	 * @return �µ�״̬
	 */
	public FlightState move(char c);
	
	/**
	 * �õ�������ǰ״̬���ַ���
	 * @return ������ǰ״̬���ַ���
	 */
	public String getflightstate();
	
}