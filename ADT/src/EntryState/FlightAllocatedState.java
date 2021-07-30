package EntryState;



public class FlightAllocatedState implements FlightState{

    static FlightAllocatedState instance=new FlightAllocatedState();
    private String nowstate="�����ѷ���ɻ�(Allocated)";
 
    // inmutability��
 	// Abstraction function:
 	// AF(instance)=��״̬����
 	// AF(nowstate)=�����ƻ��ǰ״̬���ַ���
 	// Representation invariant:
 	// �������ָ����м�飬������Ҫ���׳��쳣
 	// Safety from rep exposure:
 	// ��instance,nowstate����Ϊprivate
    
	private FlightAllocatedState() {
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
			return FlightRunningState.instance;
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
