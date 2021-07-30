package PlanningEntry;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import Exception.ConflictTimeException;
import Exception.SameLocationException;
import Exception.SameResourceException;
import Location.Location;
import Resource.Carriage;
import Timeslot.Timeslot;

public class TrainEntry<Carriage> extends CommonPlanningEntry implements Cloneable,TrainPlanningEntry<Carriage>,Comparable<TrainEntry<Carriage>> {

	private MultipleLacationEntryImpl a;
	private MultipleSortedResourceEntryImpl<Carriage> b;
	private BlockableEntryImpl c;
	
	// mutability��
	// Abstraction function:
	// AF(a)=���λ��
	// AF(b)=���������Դ
	// AF(c)=��������ʱ���
	// Safety from rep exposure:
	// ��a,b,c����Ϊprivate
	
	/**
	 * ���췽��
	 * @param a ���λ�õ���
	 * @param b ���������Դ����
	 * @param c ��������ʱ��Ե���
	 */
	public TrainEntry(MultipleLacationEntryImpl a,MultipleSortedResourceEntryImpl<Carriage> b,BlockableEntryImpl c) {
		this.a=a;
		this.b=b;
		this.c=c;
	}

	/**
	 * ����һ��λ��
	 * @param locations �����õ�һ��λ��
	 * @return �Ƿ�ɹ�������һ��λ��
	 * @throws SameLocationException ������ͬ��λ��
	 */
	@Override
	public boolean setlocations(List<Location> mylocations) throws SameLocationException {
		return a.setlocations(mylocations);
	}

	/**
	 * ������һ��λ��
	 * @param mylocations ���ĺ��λ��
	 * @return �Ƕ��ɹ����ĸ�λ��
	 */
	@Override
	public boolean changelocations(List<Location> mylocations) {
		return a.changelocations(mylocations);
	}
	
	/**
	 * �õ���һ��λ��
	 * @return ��һ���λ��
	 */
	@Override
	public List<Location> getlocations() {
		return a.getlocations();
	}

	/**
	 * ���øø�����Դ
	 * @param train ������Դ����
	 * @return �Ƿ�ɹ����øø�����Դ
	 * @throws SameResourceException 
	 */
	@Override
	public boolean setresource(List<Carriage> train) throws SameResourceException {
		return b.setresource(train);
	}

	/**
	 * �õ��ø�����Դ
	 * @return �ø�����Դ
	 */
	@Override
	public List<Carriage> getresource() {
		return b.getresource();
	}

	/**
	 * ����ĳ������Դ
	 * @param precarriage �����ĵĳ�����Դ
	 * @param aftercarriage ���ĺ�ĳ�����Դ
	 * @return �Ƿ�ɹ����ĳ�����Դ
	 */
	@Override
	public boolean changeresource(Carriage mycarriage,Carriage aftercarriage) {
		return b.changeresource(mycarriage,aftercarriage);
	}

	/**
	 * �������Դ�������һ�ڳ���
	 * @param mycarriage ������ĳ���
	 * @param temp ���복���λ��
	 * @return �Ƿ�ɹ����복��
	 */
	@Override
	public boolean addresource(Carriage mycarriage,int temp) {
		return b.addresource(mycarriage,temp);
	}

	/**
	 * ɾ��������Դ�е�ĳ����
	 * @param mycarriage ��ɾ���ĳ���
	 * @return �Ƿ�ɹ�ɾ������
	 */
	@Override
	public boolean deleteresource(Carriage mycarriage) {
		return b.deleteresource(mycarriage);
	}

	/**
	 * �ж��Ƿ������
	 * @return �Ƿ������
	 */
	@Override
	public boolean whetherblockable() {
		return c.whetherblockable();
	}

	/**
     * ������һ��ʱ���
     * @param alltime һ��ʱ��� 
     * @return �Ƿ�ɹ�������һ��ʱ���
	 * @throws ConflictTimeException 
     */
	@Override
	public boolean settimeslot(List<Timeslot> alltime) throws ConflictTimeException {
		return c.settimeslot(alltime);
	}

	/**
	 * �õ���һ��ʱ���
	 * @return ��һ��ʱ���
	 */
	@Override
	public List<Timeslot> gettimeslot() {
		return c.gettimeslot();
	}
	
	/**
	 * ��ĳ��վ���������
	 * @param toblocklocation ��������վ������
	 * @return ����վ��������վ���е�λ��
	 */
	public int trainblock(String toblocklocation) {
		int i;
		if(a.getlocations().size()<=2) {
			System.out.println("û���м�վ��ɹ�����\n");
			return -1;
		}
		else {
			for(i=0;i<a.getlocations().size();i++) {
				if(a.getlocations().get(i).getlocationname().equals(toblocklocation) ){
					break;
				}
			}
			if(i>0&&i<a.getlocations().size()-1) {
				Calendar nowtime = Calendar.getInstance();  //��ǰʱ��
				String kpr = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(nowtime.getTime()); 
				System.out.println("��"+toblocklocation+"����������������ʱ��Ϊ"+kpr);	
				return i;
			}
			System.out.println("���������վ���յ�վ����\n");
			return -1;
		} 
	}

    /**
     * ��дcompareTo��������ɰ���ʱ�����ʼʱ�������Լƻ��������
     */
	@Override
	public int compareTo(TrainEntry<Carriage> o) {
		if(c.gettimeslot().get(0).getbegintime().compareTo(o.gettimeslot().get(0).getbegintime())>0) {
			return 1;
		}
		else if(c.gettimeslot().get(0).getbegintime().compareTo(o.gettimeslot().get(0).getbegintime())==0) {
			return 0;
		}
		return -1;
	}

	@Override
	  public TrainEntry<Carriage> clone() { 
		TrainEntry<Carriage> stu = null; 
	    try{ 
	      stu = (TrainEntry<Carriage>)super.clone(); 
	    }catch(CloneNotSupportedException e) { 
	      e.printStackTrace(); 
	    } 
	    stu.a=(MultipleLacationEntryImpl)a.clone();
	    stu.b=(MultipleSortedResourceEntryImpl<Carriage>)b.clone();
	    stu.c=(BlockableEntryImpl)c.clone();
	    return stu; 
	  } 
}
