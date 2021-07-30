package EntryState;


public class CourseWaitingState implements CourseState{

	public static CourseWaitingState instance=new CourseWaitingState();
	private String nowstate="�γ�δ������ʦ(Waiting)";
	
	// inmutability��
	// Abstraction function:
	// AF(instance)=��״̬����
	// AF(nowstate)=�����ƻ��ǰ״̬���ַ���
	// Representation invariant:
	// �������ָ����м�飬������Ҫ���׳��쳣
	// Safety from rep exposure:
	// ��instance,nowstate����Ϊprivate
	
	private CourseWaitingState() {
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
			return CourseAllocatedState.instance;
		case 'b':
			return CourseCancelledState.instance;
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
