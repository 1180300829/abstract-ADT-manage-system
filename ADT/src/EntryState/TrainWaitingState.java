package EntryState;


public class TrainWaitingState implements TrainState{

	public static TrainWaitingState instance=new TrainWaitingState();
	private String nowstate="����δ���䳵��(Waiting)";
	
	// inmutability��
	// Abstraction function:
	// AF(instance)=��״̬����
	// AF(nowstate)=�����ƻ��ǰ״̬���ַ���
	// Representation invariant:
	// �������ָ����м�飬������Ҫ���׳��쳣
	// Safety from rep exposure:
	// ��instance,nowstate����Ϊprivate
	
	private TrainWaitingState() {
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
			return TrainAllocatedState.instance;
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
