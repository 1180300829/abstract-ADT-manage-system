package EntryState;



public class FlightRunningState implements FlightState{

    static FlightRunningState instance=new FlightRunningState();
    private String nowstate="���������(Running)";
    
    // inmutability��
 	// Abstraction function:
 	// AF(instance)=��״̬����
 	// AF(nowstate)=�����ƻ��ǰ״̬���ַ���
 	// Representation invariant:
 	// �������ָ����м�飬������Ҫ���׳��쳣
 	// Safety from rep exposure:
 	// ��instance,nowstate����Ϊprivate
    
	private FlightRunningState() {
	};
	
	/**
	 * �ı䵱ǰ״̬
	 * @param c �ı�ָ��
	 * @return �µ�״̬
	 */
	@Override
	public FlightState move(char c) {
		switch(c) {
		case 'a':
			return FlightEndedState.instance;
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * �õ���ǰ״̬
	 * @return ������ǰ״̬���ַ���
	 */
	@Override
	public String getflightstate() {
		return this.nowstate;
	}

	

}
