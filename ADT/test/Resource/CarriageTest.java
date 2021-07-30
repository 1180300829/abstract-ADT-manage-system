package Resource;

import static org.junit.Assert.*;
import org.junit.Test;
import Exception.LessThanZeroException;


public class CarriageTest {

	/* Testing strategy
	 * ����getcarriagenumber����
     * ���Գ����ŵķ���ֵ����
     */
	@Test
	public void getcarriagenumbertest() throws LessThanZeroException {
		Carriage temp=new Carriage("AS02","������",100,"2011");
		assertEquals("AS02",temp.getcarriagenumber());
	}
	
	/* Testing strategy
	 * ����getcarriagetype����
     * ���Գ������͵ķ���ֵ����
     */
	@Test
	public void getcarriagetypetest() throws LessThanZeroException {
		Carriage temp=new Carriage("AS02","������",100,"2011");
		assertEquals("������",temp.getcarriagetype());
	}
	
	/* Testing strategy
	 * ����getcarriageallseat����
     * ���Զ�Ա���ķ���ֵ����
     */
	@Test
	public void getcarriageallseattest() throws LessThanZeroException {
		Carriage temp=new Carriage("AS02","������",100,"2011");
		assertEquals(100,temp.getcarriageallseat());
	}
	
	/* Testing strategy
	 * ����getcarriagbirth����
     * ���Գ��������ݵķ���ֵ����
     */
	@Test
	public void getcarriagbirthtest() throws LessThanZeroException {
		Carriage temp=new Carriage("AS02","������",100,"2011");
		assertEquals("2011",temp.getcarriagbirth());
	}
	
	/* Testing strategy
	 * ����hashcode����
     * ����������ͬ�ĳ�����hashcode�Ƿ���ͬ����
     */
	@Test
	public void hashcodetest() throws LessThanZeroException {
		Carriage temp=new Carriage("AS02","������",100,"2011");
		Carriage temp1=new Carriage("AS02","������",100,"2011");
		assertEquals(temp.hashCode(),temp1.hashCode());
	}
	
	/* Testing strategy
	 * ����equals����
     * ���������������Ƿ���ͬ���ֵȼ��ࣺ��ͬ����ͬ
     */
	@Test
	public void equalstest() throws LessThanZeroException {
		Carriage temp=new Carriage("AS02","������",100,"2011");
		Carriage temp1=new Carriage("AS02","������",100,"2011");
		Carriage temp2=new Carriage("A02","һ����",100,"2011");
		assertEquals(true,temp.equals(temp1));
		assertEquals(false,temp.equals(temp2));
	}
	
	/* Testing strategy
	 * ���Դ���ʱ���쳣
     */
	 @Test(expected = LessThanZeroException.class)
	 public void shouldGetLessThanZeroException() throws LessThanZeroException {
		 Carriage temp=new Carriage("AS02","������",-100,"2011");
		 temp.hashCode();
	 }

}
