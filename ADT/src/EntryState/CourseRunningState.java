package EntryState;


public class CourseRunningState implements CourseState{

	    static CourseRunningState instance=new CourseRunningState();
	    private String nowstate="�γ��ѿ�ʼ�Ͽ�(Running)";
	 
	    // inmutability��
		// Abstraction function:
		// AF(instance)=��״̬����
		// AF(nowstate)=�����ƻ��ǰ״̬���ַ���
		// Representation invariant:
		// �������ָ����м�飬������Ҫ���׳��쳣
		// Safety from rep exposure:
		// ��instance,nowstate����Ϊprivate
	    
		private CourseRunningState() {
		};
		
		/**
		 * �ı䵱ǰ״̬
		 * @param c �ı�ָ��
		 * @return �µ�״̬
		 */
		@Override
		public CourseState move(char c) {
			switch(c) {
			case 'a':
				return CourseEndedState.instance;
			default:
				throw new IllegalArgumentException();
			}
		}

		/**
		 * �õ���ǰ״̬
		 * @return ������ǰ״̬���ַ���
		 */
		@Override
		public String getcoursestate() {
			return this.nowstate;
		}

	
}
