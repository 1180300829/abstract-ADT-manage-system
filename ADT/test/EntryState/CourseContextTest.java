package EntryState;

import static org.junit.Assert.*;

import org.junit.Test;

public class CourseContextTest {
		
	/* Testing strategy
	 * ����CourseContext���е����з�������
     */
	@Test
	public void allmethodtest() {
		CourseWaitingState state=CourseWaitingState.instance;
		CourseContext temp=new CourseContext(state);
		assertEquals(state,temp.getstate());
		assert temp.move('a')!=null;
	}

}
