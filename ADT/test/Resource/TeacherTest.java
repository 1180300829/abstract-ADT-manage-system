package Resource;

import static org.junit.Assert.*;

import org.junit.Test;

public class TeacherTest {

	/* Testing strategy
	 * ����getidnumber����
     * ������ʦ���֤�ŵķ���ֵ����
     */
	@Test
	public void getidnumbertest() {
		Teacher temp=new Teacher("422823199812254452","����","��","��ʦ");
		assertEquals("422823199812254452",temp.getidnumber());
	}
	
	/* Testing strategy
	 * ����getteachername����
     * ���Խ�ʦ�����ķ���ֵ����
     */
	@Test
	public void getteachernametest() {
		Teacher temp=new Teacher("422823199812254452","����","��","��ʦ");
		assertEquals("����",temp.getteachername());
	}
	
	/* Testing strategy
	 * ����getteachergender����
     * ���Խ�ʦ�Ա�ķ���ֵ����
     */
	@Test
	public void getteachergendertest() {
		Teacher temp=new Teacher("422823199812254452","����","��","��ʦ");
		assertEquals("��",temp.getteachergender());
	}
	
	/* Testing strategy
	 * ����getteachertitle����
     * ���Խ�ʦְ�Ƶķ���ֵ����
     */
	@Test
	public void getteachertitletest() {
		Teacher temp=new Teacher("422823199812254452","����","��","��ʦ");
		assertEquals("��ʦ",temp.getteachertitle());
	}
	
	/* Testing strategy
	 * ����hashcode����
     * ����������ͬ�Ľ�ʦ��hashcode�Ƿ���ͬ����
     */
	@Test
	public void hashcodetest() {
		Teacher temp=new Teacher("422823199812254452","����","��","��ʦ");
		Teacher temp1=new Teacher("422823199812254452","����","��","��ʦ");
		assertEquals(temp.hashCode(),temp1.hashCode());
	}
	
	/* Testing strategy
	 * ����equals����
     * ����������ʦ���Ƿ���ͬ���ֵȼ��ࣺ��ͬ����ͬ
     */
	@Test
	public void equalstest() {
		Teacher temp=new Teacher("422823199812254452","����","��","��ʦ");
		Teacher temp1=new Teacher("422823199812254452","����","��","��ʦ");
		Teacher temp2=new Teacher("422823199812222222","����","��","��ʦ");
		assertEquals(true,temp.equals(temp1));
		assertEquals(false,temp.equals(temp2));
	}

}
