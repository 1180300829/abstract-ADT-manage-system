package EntryState;



public class TrainCancelledState implements TrainState{

	static TrainCancelledState instance=new TrainCancelledState();
	private String nowstate="������ȡ��(Cancelled)";
	
	// inmutability��
	// Abstraction function:
	// AF(instance)=��״̬����
	// AF(nowstate)=�����ƻ��ǰ״̬���ַ���
	// Representation invariant:
	// �������ָ����м�飬������Ҫ���׳��쳣
	// Safety from rep exposure:
	// ��instance,nowstate����Ϊprivate
	
	private TrainCancelledState() {
	}

	/**
	 * �ı䵱ǰ״̬
	 * @param c �ı�ָ��
	 * @return �µ�״̬
	 */
	@Override
	public TrainState move(char c) {
		return null;
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
