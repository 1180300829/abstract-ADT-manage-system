package Location;

import static org.junit.Assert.*;

import org.junit.Test;

import Resource.Flight;

public class CommonLocationTest {
	
	
    CommonLocation temp=new CommonLocation("��γ112","����20","����");
    
	/* Testing strategy
	 * ����getlongitude����
     * ���Է���ֵ����
     */
	@Test
	public void getlongitudetest() {
		assertEquals("��γ112",temp.getlongitude());
	}
	
	/* Testing strategy
	 * ����getlatitude����
     * ���Է���ֵ����
     */
	@Test
	public void getlatitudetest() {
		assertEquals("����20",temp.getlatitude());
	}
	
	/* Testing strategy
	 * ����getlocationname����
     * ���Է���ֵ����
     */
	@Test
	public void getlocationnametest() {
		assertEquals("����",temp.getlocationname());
	}
	
	/* Testing strategy
	 * ����hashcode����
     * ����������ͬ��λ����hashcode�Ƿ���ͬ����
     */
	@Test
	public void hashcodetest() {
		CommonLocation temp1=new CommonLocation("��γ112","����20","����");
		assertEquals(temp.hashCode(),temp1.hashCode());
	}
	
	/* Testing strategy
	 * ����equals����
     * ��������λ�����Ƿ���ͬ���ֵȼ��ࣺ��ͬ����ͬ
     */
	@Test
	public void equalstest() {
		CommonLocation temp1=new CommonLocation("��γ112","����20","����");
		CommonLocation temp2=new CommonLocation("��γ11","����2","����");
		assertEquals(true,temp.equals(temp1));
		assertEquals(false,temp.equals(temp2));
	}

}
