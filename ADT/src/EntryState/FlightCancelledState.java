package EntryState;


public class FlightCancelledState implements FlightState{

	    static FlightCancelledState instance=new FlightCancelledState();
	    private String nowstate="������ȡ��(Cancelled)";
	
	    // inmutability��
		// Abstraction function:
		// AF(instance)=��״̬����
		// AF(nowstate)=�����ƻ��ǰ״̬���ַ���
		// Representation invariant:
		// �������ָ����м�飬������Ҫ���׳��쳣
		// Safety from rep exposure:
		// ��instance,nowstate����Ϊprivate
	    
		private FlightCancelledState() {
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
