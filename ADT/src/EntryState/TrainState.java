package EntryState;


public interface TrainState {
	
	/**
	 * �ı䵱ǰ״̬
	 * @param c �ı�ָ��
	 * @return �µ�״̬
	 */
    public TrainState move(char c);
	
    /**
	 * �õ�������ǰ״̬���ַ���
	 * @return ������ǰ״̬���ַ���
	 */
	public String gettrainstate();
	
}
