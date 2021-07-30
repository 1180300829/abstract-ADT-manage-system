package EntryState;



public class CourseAllocatedState implements CourseState{

	static CourseAllocatedState instance=new CourseAllocatedState();
    private String nowstate="�γ��ѷ�����ʦ(Allocated)";
 
    // inmutability��
 	// Abstraction function:
 	// AF(instance)=��״̬����
 	// AF(nowstate)=�����ƻ��ǰ״̬���ַ���
 	// Representation invariant:
 	// �������ָ����м�飬������Ҫ���׳��쳣
 	// Safety from rep exposure:
 	// ��instance,nowstate����Ϊprivate
    
	private CourseAllocatedState() {
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
			return CourseRunningState.instance;
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
		return nowstate;
	}

	
}
