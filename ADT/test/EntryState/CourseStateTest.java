package EntryState;

import static org.junit.Assert.*;

import org.junit.Test;

public class CourseStateTest {

	/* Testing strategy
	 * ͨ��״̬��ת�����������Course��state�ķ����Ĳ���
     */
	@Test
	public void allmethodtest() {
		CourseState state=CourseWaitingState.instance;
	    assertEquals("�γ�δ������ʦ(Waiting)",state.getcoursestate());
	    
	    state=state.move('a');
	    assertEquals("�γ��ѷ�����ʦ(Allocated)",state.getcoursestate());
	    
	    state=state.move('a');
	    assertEquals("�γ��ѿ�ʼ�Ͽ�(Running)",state.getcoursestate());
	    
	    state=state.move('a');
	    assertEquals("�γ����¿�(Ended)",state.getcoursestate());
	    
	    assertEquals(null,state.move('a'));
	    
	    state=CourseWaitingState.instance;
	    state=state.move('b');
	    assertEquals("�γ���ȡ��(Cancelled)",state.getcoursestate());
	    
	    assertEquals(null,state.move('a'));
	    
	    state=CourseWaitingState.instance;
	    state=state.move('a');
	    state=state.move('b');
	    assertEquals("�γ���ȡ��(Cancelled)",state.getcoursestate());
	}

}
