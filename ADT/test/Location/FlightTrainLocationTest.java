package Location;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlightTrainLocationTest {

	/* Testing strategy
	 * ����whethershare����
     * ���Է���ֵ����
     */
	@Test
	public void whethersharetest() {
		FlightTrainLocation temp=new FlightTrainLocation("��γ112","����20","�人");
		assertEquals(true,temp.whethershare());
	}

}
