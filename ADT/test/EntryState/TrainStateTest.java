package EntryState;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrainStateTest {

	/* Testing strategy
	 * ͨ��״̬��ת�����������Train��state�ķ����Ĳ���
     */
	@Test
	public void allmethodtest() {
		TrainState state=TrainWaitingState.instance;
	    assertEquals("����δ���䳵��(Waiting)",state.gettrainstate());
	    
	    state=state.move('a');
	    assertEquals("�����ѷ���һ�鳵��(Allocated)",state.gettrainstate());
	    
	    state=state.move('a');
	    assertEquals("�����Ѵ���ʼվ����(Running)",state.gettrainstate());
	    
	    state=state.move('a');
	    assertEquals("�����ѵִ��յ�վ(Ended)",state.gettrainstate());
	    
	    assertEquals(null,state.move('a'));
	    
	    state=TrainWaitingState.instance;
	    state=state.move('b');
	    assertEquals("������ȡ��(Cancelled)",state.gettrainstate());
	    
	    state=TrainWaitingState.instance;
	    state=state.move('a');
	    state=state.move('b');
	    assertEquals("������ȡ��(Cancelled)",state.gettrainstate());
	    
	    assertEquals(null,state.move('a'));
	    
	    state=TrainWaitingState.instance;
	    state=state.move('a');
	    state=state.move('a');
	    state=state.move('b');
	    assertEquals("������;����(Blocked)",state.gettrainstate());
	    state=state.move('a');
	    assertEquals("�����Ѵ���ʼվ����(Running)",state.gettrainstate());
	    state=state.move('b');
	    assertEquals("������;����(Blocked)",state.gettrainstate());
	    state=state.move('b');
	    assertEquals("������ȡ��(Cancelled)",state.gettrainstate());
	    
	}

}
