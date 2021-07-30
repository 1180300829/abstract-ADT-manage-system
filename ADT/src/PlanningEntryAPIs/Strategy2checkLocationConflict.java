package PlanningEntryAPIs;

import java.util.ArrayList;
import java.util.List;
import PlanningEntry.CourseEntry;
import PlanningEntry.FlightEntry;
import PlanningEntry.PlanningEntry;
import PlanningEntry.TrainEntry;
import Resource.Teacher;
import Timeslot.Timeslot;

public class Strategy2checkLocationConflict implements StrategycheckLocationConflict{

	/**
	 * ���һ��ƻ���֮���Ƿ����λ�ö�ռ��ͻ
	 * @param entries �ƻ����
	 * @return �Ƿ����λ�ö�ռ��ͻ
	 */
	@Override
	public boolean checkLocationConflict(List<PlanningEntry> entries) {
		int i,j,k;
		Timeslot b1,b2;
		String l1,m1,m2;
		int zongshu=0;  
		boolean flag;
		if(entries.get(0) instanceof CourseEntry) {
			flag=false;
			List<CourseEntry<Teacher>> tempentries=new ArrayList<>();    //��ʱ��list
			List<CourseEntry<Teacher>> oneentries=new ArrayList<>();     //����ת�ͺ��list
			List<List<CourseEntry<Teacher>>> alllist=new ArrayList<>();  //����һ�Ѿ�����ͬ��ַ��Entry��list��list
			List<String> locationentries=new ArrayList<>();     //�������е�ַ��list
	        String templocation;
			for(i=0;i<entries.size();i++) {          //����ת�͵õ�CourseEntry�µ�list
				oneentries.add((CourseEntry<Teacher>)entries.get(i));
			}
			for(i=0;i<oneentries.size();i++) {      //�õ��������е�ַ��list
				l1=oneentries.get(i).getlocations().getlocationname();
				if(!locationentries.contains(l1)) {
					locationentries.add(l1);
				}
			}
			while(zongshu<oneentries.size()) {      //���յ�֪�ȼ��໮������CourseEntry���õ����洢��һ�Ѿ�����ͬ��ַ��Entry��list��list
				for(i=0;i<locationentries.size();i++) {
					templocation=locationentries.get(i);
					tempentries=new ArrayList<>();
					for(j=0;j<oneentries.size();j++) {
						if(oneentries.get(j).getlocations().getlocationname().equals(templocation)) {
							tempentries.add(oneentries.get(j));  //��ʱ�õ�������ͬ��ַ��Entry��list
							zongshu++;
						}
					}
					alllist.add(tempentries);	//������ʱ��list���봢��ȼ����list��
				}	
			}	
			for(i=0;i<alllist.size();i++) {
				tempentries=new ArrayList<>(alllist.get(i));  
				if(tempentries.size()>1) {   //�Ըõȼ���list�е�ÿһ�����ȳ���һ��list�����Ƿ�ʱ���ͻ�ж�
					for(j=0;j<tempentries.size()-1;j++) {
						b1=tempentries.get(j).gettimeslot();
						m1=(String) tempentries.get(j).getplanningentryname();
						for(k=j+1;k<tempentries.size();k++) {
							b2=tempentries.get(k).gettimeslot();
							m2=(String) tempentries.get(k).getplanningentryname();
							if(!(b1.getendtime().compareTo(b2.getbegintime())<=0||b2.getendtime().compareTo(b1.getbegintime())<=0)) {
								System.out.println("�ƻ��"+m1+"����ƻ��"+m2+"������ʱ���ͻ��ʹ����ͬһ�����"+"��"+tempentries.get(k).getlocations().getlocationname()+"��");
								flag=true;
							}
						}
					}
				}
			}
			if(flag==true) {
				return true;
			}
			else {
				System.out.println("û�мƻ������ص�ʱ��ʹ��ͬһ����");
				return false;
			}
		}
		if(entries.get(0) instanceof FlightEntry) {
			System.out.println("����λ�ÿɹ��������ڳ�ͻ");
			return false;
		}
		if(entries.get(0) instanceof TrainEntry) {
			System.out.println("����վλ�ÿɹ��������ڳ�ͻ");
			return false;
		}
	   System.out.println("��������ȷ��Ϣ");
	   return false;
	}

}
