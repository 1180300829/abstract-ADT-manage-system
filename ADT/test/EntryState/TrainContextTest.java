package EntryState;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrainContextTest {

	/* Testing strategy
	 * ����TrainContext���е����з�������
     */
	@Test
	public void allmethodtest() {
		TrainWaitingState state=TrainWaitingState.instance;
		TrainContext temp=new TrainContext(state);
		assertEquals(state,temp.getstate());
		assert temp.move('a')!=null;
	}
}
