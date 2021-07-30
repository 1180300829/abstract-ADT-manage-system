package EntryState;

public class CourseContext {

    private CourseState state;
    
    // mutability��
 	// Abstraction function:
 	// AF(state)=һ��״̬
 	// Representation invariant:
 	// �������ָ����м�飬������Ҫ���׳��쳣
 	// Safety from rep exposure:
 	// ��state����Ϊprivate
	
    /**
     * ���췽��
     * @param s ��ʼ״̬
     */
	public CourseContext(CourseState s) {
		state=s;
	}
	
	/**
	 * �ı䵱ǰ״̬
	 * @param c �ı�ָ��
	 * @return �µ�״̬
	 */
	public CourseState move(char c) {
		state=state.move(c);
		return state;
	}
	
	/**
	 * �õ���ǰ״̬
	 * @return ��ǰ״̬
	 */
	public CourseState getstate() {
		return this.state;
	}
}
