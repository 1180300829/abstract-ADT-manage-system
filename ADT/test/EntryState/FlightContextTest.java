package EntryState;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlightContextTest {

	/* Testing strategy
	 * ����FlightContext���е����з�������
     */
	@Test
	public void allmethodtest() {
		FlightWaitingState state=FlightWaitingState.instance;
		FlightContext temp=new FlightContext(state);
		assertEquals(state,temp.getstate());
		assert temp.move('a')!=null;
	}

}
