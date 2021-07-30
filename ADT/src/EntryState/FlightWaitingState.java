package EntryState;


public class FlightWaitingState implements FlightState{

	public static FlightWaitingState instance=new FlightWaitingState();
	private String nowstate="����δ����ɻ�(Waiting)";
	
	// inmutability��
	// Abstraction function:
	// AF(instance)=��״̬����
	// AF(nowstate)=�����ƻ��ǰ״̬���ַ���
	// Representation invariant:
	// �������ָ����м�飬������Ҫ���׳��쳣
	// Safety from rep exposure:
	// ��instance,nowstate����Ϊprivate
	
	private FlightWaitingState() {
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
			return FlightAllocatedState.instance;
		case 'b':
			return FlightCancelledState.instance;
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
