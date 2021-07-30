package EntryState;


public class FlightEndedState implements FlightState{

	    static FlightEndedState instance=new FlightEndedState();
	    private String nowstate="�����ѽ���(Ended)";
	   
	    // inmutability��
		// Abstraction function:
		// AF(instance)=��״̬����
		// AF(nowstate)=�����ƻ��ǰ״̬���ַ���
		// Representation invariant:
		// �������ָ����м�飬������Ҫ���׳��쳣
		// Safety from rep exposure:
		// ��instance,nowstate����Ϊprivate
	    
		private FlightEndedState() {
		};
		
		/**
		 * �ı䵱ǰ״̬
		 * @param c �ı�ָ��
		 * @return �µ�״̬
		 */
		@Override
		public FlightState move(char c) {
			return null;
		}

		/**
		 * �õ���ǰ״̬
		 * @return ������ǰ״̬���ַ���
		 */
		@Override
		public String getflightstate() {
			// TODO �Զ����ɵķ������
			return this.nowstate;
		}


}
	
