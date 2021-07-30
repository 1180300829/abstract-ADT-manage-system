package PlanningEntry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import Exception.SameLocationException;
import Location.*;

public class MultipleLacationEntryImpl implements Cloneable,MultipleLocationEntry {

	private List<Location> locations; //һ��λ��
	
	// mutability��
	// Abstraction function:
	// AF(mytrain)=һ��λ��
	// Representation invariant:
	// ����λ��Ӧ�ò�ͬ
	// Safety from rep exposure:
	// ��locations����Ϊprivate
	
	// TODO checkRep
    private void checkRep() {  //��֤λ�ö���ͬ,һ��λ�ò�Ϊ��
    	assert locations!=null;
    	boolean result=true;
		for(int i=0;i<locations.size()-1;i++) {
			for(int j=i+1;j<locations.size();j++) {
				if(locations.get(i).equals(locations.get(j))) {
					result=false;
					break;
				}
			}
		} 
        assert result==true:"�����ظ�λ��\n";
    }
	
	/**
	 * ����һ��λ��
	 * @param locations �����õ�һ��λ�ã����ܴ�����ͬλ��
	 * @return �Ƿ�ɹ�������һ��λ��
	 * @throws SameLocationException ������ͬλ��
	 */
	@Override
	public boolean setlocations(List<Location> mylocations) throws SameLocationException {
		boolean result=true;
		for(int i=0;i<mylocations.size()-1;i++) {
			for(int j=i+1;j<mylocations.size();j++) {
				if(mylocations.get(i).equals(mylocations.get(j))) {
					result=false;
					break;
				}
			}
		} 
		if(result==false) {   //������ͬ��λ���׳��쳣
			throw new SameLocationException();
		}
		if(locations==null) {
			this.locations=new ArrayList<>(mylocations);
			System.out.println("λ�����óɹ�");
			checkRep();
			return true;
		}
		System.out.println("λ��ֻ������һ��");
		return false;
	}

	/**
	 * ������һ��λ��
	 * @param mylocations ���ĺ��λ��
	 * @return �Ƕ��ɹ����ĸ�λ��
	 */
	@Override
	public boolean changelocations(List<Location> mylocations) {
		checkRep();
		System.out.println("λ�ò��ɸ���");
		return false;
	}

	/**
	 * �õ���һ��λ��
	 * @return ��һ���λ��
	 */
	@Override
	public List<Location> getlocations() {
		checkRep();
		return this.locations;
	}
	
	@Override
	  public MultipleLacationEntryImpl clone() { 
		MultipleLacationEntryImpl stu = null; 
	    try{ 
	      stu = (MultipleLacationEntryImpl)super.clone(); 
	    }catch(CloneNotSupportedException e) { 
	      e.printStackTrace(); 
	    } 
	    return stu; 
	  } 
}
