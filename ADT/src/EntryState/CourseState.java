package EntryState;


public interface CourseState {
	
	/**
	 * �ı䵱ǰ״̬
	 * @param c �ı�ָ��
	 * @return �µ�״̬
	 */
    public CourseState move(char c);
	
    /**
	 * �õ�������ǰ״̬���ַ���
	 * @return ������ǰ״̬���ַ���
	 */
	public String getcoursestate();

}
