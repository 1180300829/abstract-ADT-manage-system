package EntryState;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlightStateTest {

	/* Testing strategy
	 * ͨ��״̬��ת�����������Flight��state�ķ����Ĳ���
     */
	@Test
	public void allmethodtest() {
		FlightState state=FlightWaitingState.instance;
	    assertEquals("����δ����ɻ�(Waiting)",state.getflightstate());
	    
	    state=state.move('a');
	    assertEquals("�����ѷ���ɻ�(Allocated)",state.getflightstate());
	    
	    state=state.move('a');
	    assertEquals("���������(Running)",state.getflightstate());
	    
	    state=state.move('a');
	    assertEquals("�����ѽ���(Ended)",state.getflightstate());
	    
	    assertEquals(null,state.move('a'));
	    
	    state=FlightWaitingState.instance;
	    state=state.move('b');
	    assertEquals("������ȡ��(Cancelled)",state.getflightstate());
	    
	    assertEquals(null,state.move('a'));
	    
	    state=FlightWaitingState.instance;
	    state=state.move('a');
	    state=state.move('b');
	    assertEquals("������ȡ��(Cancelled)",state.getflightstate());
	}
}
