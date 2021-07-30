package EntryState;

public class TrainContext {

    private TrainState state;
    
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
	public TrainContext(TrainState s) {
		state=s;
	}
	
	/**
	 * �ı䵱ǰ״̬
	 * @param c �ı�ָ��
	 * @return �µ�״̬
	 */
	public TrainState move(char c) {
		state=state.move(c);
		return state;
	}
	
	/**
	 * �õ���ǰ״̬
	 * @return ��ǰ״̬
	 */
	public TrainState getstate() {
		return this.state;
	}
}
