package Location;

import static org.junit.Assert.*;

import org.junit.Test;

public class CourseLocationTest {

	/* Testing strategy
	 * ����whethershare����
     * ���Է���ֵ����
     */
	@Test
	public void whethersharetest() {
		CourseLocation temp=new CourseLocation("��γ112","����20","����32");
		assertEquals(false,temp.whethershare());
	}

}
