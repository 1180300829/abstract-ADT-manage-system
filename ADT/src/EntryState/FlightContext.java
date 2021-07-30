package EntryState;

public class FlightContext {

	private FlightState state;
	
	// mutability��
 	// Abstraction function:
 	// AF(state)=һ��״̬
 	// Representation invariant:
 	// �������ָ����м�飬������Ҫ���׳��쳣
 	// Safety from rep exposure:
 	// ��state����Ϊprivate
	
	  /**
     * ���췽��
     * @param s ��ʼ״̬
     */
	public FlightContext(FlightState s) {
		state=s;
	}

	/**
	 * �ı䵱ǰ״̬
	 * @param c �ı�ָ��
	 * @return �µ�״̬
	 */
	public FlightState move(char c) {
		state=state.move(c);
		return state;
	}
	
	/**
	 * �õ���ǰ״̬
	 * @return ��ǰ״̬
	 */
	public FlightState getstate() {
		return this.state;
	}
}
