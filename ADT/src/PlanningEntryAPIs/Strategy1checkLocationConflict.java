package PlanningEntryAPIs;

import java.util.ArrayList;
import java.util.List;

import PlanningEntry.CourseEntry;
import PlanningEntry.FlightEntry;
import PlanningEntry.PlanningEntry;
import PlanningEntry.TrainEntry;
import Resource.Teacher;
import Timeslot.Timeslot;

public class Strategy1checkLocationConflict implements StrategycheckLocationConflict{

	/**
	 * ���һ��ƻ���֮���Ƿ����λ�ö�ռ��ͻ
	 * @param entries �ƻ����
	 * @return �Ƿ����λ�ö�ռ��ͻ
	 */
	@Override
	public boolean checkLocationConflict(List<PlanningEntry> entries) {
		int i,j;
		Timeslot b1,b2;
		String l1,l2,m1,m2;
		boolean flag;
		if(entries.get(0) instanceof CourseEntry) {
			flag=false;
			List<CourseEntry<Teacher>> courseentries=new ArrayList<>();  //����ת�ͺ��list
			for(i=0;i<entries.size();i++) {
				courseentries.add((CourseEntry<Teacher>)entries.get(i));
			}
			for(i=0;i<courseentries.size()-1;i++) {   //������Entry��listÿһ�����������������ĵ�ַ��ʱ����бȽϣ��ж��Ƿ�ʱ���ͻ
				b1=courseentries.get(i).gettimeslot();
				m1=(String) courseentries.get(i).getplanningentryname();
				l1=courseentries.get(i).getlocations().getlocationname();
				for(j=i+1;j<courseentries.size();j++) {
					b2=courseentries.get(j).gettimeslot();
					m2=(String) courseentries.get(j).getplanningentryname();
					l2=courseentries.get(j).getlocations().getlocationname();
					if(l1.equals(l2)) {
						if(!(b1.getendtime().compareTo(b2.getbegintime())<=0||b2.getendtime().compareTo(b1.getbegintime())<=0)) {
							System.out.println("�ƻ��"+m1+"����ƻ��"+m2+"������ʱ���ͻ��ʹ����ͬһ�����"+"��"+l2+"��");
							flag=true;
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
