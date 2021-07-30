package PlanningEntry;

import java.util.ArrayList;
import java.util.List;

import Exception.ConflictTimeException;
import Timeslot.Timeslot;

public class BlockableEntryImpl implements Cloneable,BlockableEntry {

	private List<Timeslot> alltime; //һ��ʱ���
	
	// mutability��
	// Abstraction function:
	// AF(mytrain)=һ��ʱ���
	// Representation invariant:
	// �����ʱ��Լ��ϲ���Ϊ�գ�ÿ��վ�ĵִ�ʱ�����ڳ���ʱ��
	// Safety from rep exposure:
	// ��alltime����Ϊprivate
	
	// TODO checkRep
    private void checkRep() {  //��֤ÿ��վ�ִ�ʱ�����ڳ���ʱ��
    	assert alltime!=null;
    	boolean result=true;
    	for(int i=0;i<alltime.size()-1;i++) {
			if(alltime.get(i).getendtime().compareTo(alltime.get(i+1).getbegintime())>0) {
				result=false;
			}
		}
        assert result==true:"ĳ��վ�ִ�ʱ�����ڳ���ʱ��\n";
    }
	
	/**
	 * �ж��Ƿ������
	 * @return �Ƿ������
	 */
	@Override
	public boolean whetherblockable() {
		System.out.println("������");
		return true;
	}

	 /**
     * ������һ��ʱ���
     * @param alltime һ��ʱ��ԣ�ÿ��վ�ִ�ʱ�����ڳ���ʱ��
     * @return �Ƿ�ɹ�������һ��ʱ���
	 * @throws ConflictTimeException ĳ��վ�ִ�ʱ�����ڳ���ʱ��
     */
	@Override
	public boolean settimeslot(List<Timeslot> time) throws ConflictTimeException {
		for(int i=0;i<time.size()-1;i++) {
			if(time.get(i).getendtime().compareTo(time.get(i+1).getbegintime())>0) {
				throw new ConflictTimeException();
			}
		}
		if(alltime==null) {
			alltime=new ArrayList<>();
			this.alltime=time;
			System.out.println("һ��ʱ������óɹ�");
			checkRep();
			return true;
		}
		System.out.println("ʱ��ֻ������һ��");
		return false;
	}

	
	/**
	 * �õ���һ��ʱ���
	 * @return ��һ��ʱ���
	 */
	@Override
	public List<Timeslot> gettimeslot() {
		checkRep();
		return alltime;
	}
	
	@Override
	  public BlockableEntryImpl clone() { 
		BlockableEntryImpl stu = null; 
	    try{ 
	      stu = (BlockableEntryImpl)super.clone(); 
	    }catch(CloneNotSupportedException e) { 
	      e.printStackTrace(); 
	    } 
	    return stu; 
	  } 
}
