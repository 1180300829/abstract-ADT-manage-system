package EntryState;


public class TrainEndedState implements TrainState{


	static TrainEndedState instance=new TrainEndedState();
	private String nowstate="�����ѵִ��յ�վ(Ended)";
	
	// inmutability��
	// Abstraction function:
	// AF(instance)=��״̬����
	// AF(nowstate)=�����ƻ��ǰ״̬���ַ���
	// Representation invariant:
	// �������ָ����м�飬������Ҫ���׳��쳣
	// Safety from rep exposure:
	// ��instance,nowstate����Ϊprivate
	
	private TrainEndedState() {
	}

	/**
	 * �ı䵱ǰ״̬
	 * @param c �ı�ָ��
	 * @return �µ�״̬
	 */
	@Override
	public TrainState move(char c) {
		// TODO �Զ����ɵķ������
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
