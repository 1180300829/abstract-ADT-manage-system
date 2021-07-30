package EntryState;


public class CourseEndedState implements CourseState{

	static CourseEndedState instance=new CourseEndedState();
    private String nowstate="�γ����¿�(Ended)";
 
    // inmutability��
 	// Abstraction function:
 	// AF(instance)=��״̬����
 	// AF(nowstate)=�����ƻ��ǰ״̬���ַ���
 	// Representation invariant:
 	// �������ָ����м�飬������Ҫ���׳��쳣
 	// Safety from rep exposure:
 	// ��instance,nowstate����Ϊprivate
    
	private CourseEndedState() {
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
