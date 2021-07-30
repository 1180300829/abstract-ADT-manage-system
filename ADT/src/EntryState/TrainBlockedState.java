package EntryState;


public class TrainBlockedState implements TrainState{

	static TrainBlockedState instance=new TrainBlockedState();
	private String nowstate="������;����(Blocked)";

	// inmutability��
	// Abstraction function:
	// AF(instance)=��״̬����
	// AF(nowstate)=�����ƻ��ǰ״̬���ַ���
	// Representation invariant:
	// �������ָ����м�飬������Ҫ���׳��쳣
	// Safety from rep exposure:
	// ��instance,nowstate����Ϊprivate
	
	private TrainBlockedState() {
	}
	
	/**
	 * �ı䵱ǰ״̬
	 * @param c �ı�ָ��
	 * @return �µ�״̬
	 */
	@Override
	public TrainState move(char c) {
		switch(c) {
		case 'a':
			return TrainRunningState.instance;
		case 'b':
			return TrainCancelledState.instance;
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * �õ���ǰ״̬
	 * @return ������ǰ״̬���ַ���
	 */
	@Override
	public String gettrainstate() {
		return this.nowstate;
	}

	
}
