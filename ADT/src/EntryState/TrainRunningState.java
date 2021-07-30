package EntryState;

public class TrainRunningState implements TrainState{

	static TrainRunningState instance=new TrainRunningState();
	private String nowstate="�����Ѵ���ʼվ����(Running)";
	
	// inmutability��
	// Abstraction function:
	// AF(instance)=��״̬����
	// AF(nowstate)=�����ƻ��ǰ״̬���ַ���
	// Representation invariant:
	// �������ָ����м�飬������Ҫ���׳��쳣
	// Safety from rep exposure:
	// ��instance,nowstate����Ϊprivate
	
	private TrainRunningState() {
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
			return TrainEndedState.instance;
		case 'b':
			return TrainBlockedState.instance;
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
