package EntryState;


public class CourseCancelledState implements CourseState{

	static CourseCancelledState instance=new CourseCancelledState();
    private String nowstate="�γ���ȡ��(Cancelled)";
 
    // inmutability��
 	// Abstraction function:
 	// AF(instance)=��״̬����
 	// AF(nowstate)=�����ƻ��ǰ״̬���ַ���
 	// Representation invariant:
 	// �������ָ����м�飬������Ҫ���׳��쳣
 	// Safety from rep exposure:
 	// ��instance,nowstate����Ϊprivate
    
	private CourseCancelledState() {
	};
	
	/**
	 * �ı䵱ǰ״̬
	 * @param c �ı�ָ��
	 * @return �µ�״̬
	 */
	@Override
	public CourseState move(char c) {
		return null;
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
