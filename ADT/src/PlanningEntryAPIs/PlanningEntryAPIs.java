package PlanningEntryAPIs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import Location.*;
import PlanningEntry.*;
import Resource.*;
import Timeslot.Timeslot;

public class PlanningEntryAPIs<R> {
	
	
	private StrategycheckLocationConflict choice;
	
	    // mutability��
		// Abstraction function:
	    // AF(choice)=���ʱ���ͻ��strategy�����ӿ�
		// Safety from rep exposure:
		// ��choice����Ϊprivate
	
	/**
	 * ���һ��ƻ���֮���Ƿ����λ�ö�ռ��ͻ
	 * @param entries �ƻ����
	 * @param a strategyģʽ��ѡ�����
	 * @return �Ƿ����λ�ö�ռ��ͻ
	 */
	public boolean checkLocationConflict(List<PlanningEntry> entries,String a) {
		switch(a) {
		case "1":
			choice=new Strategy1checkLocationConflict();
		    return choice.checkLocationConflict(entries);
		case "2":
			choice=new Strategy2checkLocationConflict();
		    return choice.checkLocationConflict(entries);
		default:
		    System.out.println("��������ȷָ��");
		    return false;
		}
	}
	
	
	/**
	 * ���һ��ƻ���֮���Ƿ������Դ��ռ��ͻ
	 * @param entries �ƻ����
	 * @return �Ƿ������Դ��ռ��ͻ
	 */
	public boolean checkResourceExclusiveConflict(List<PlanningEntry> entries) {
		int i,j,k,l;
		String m1,m2;
		boolean flag;
		Timeslot t1,t2;
		List<Timeslot> changt1,changt2;
		if(entries.get(0) instanceof CourseEntry) {
			flag=false;
			Teacher l1,l2;
			List<CourseEntry<Teacher>> courseentries=new ArrayList<>();
			for(i=0;i<entries.size();i++) {
				courseentries.add((CourseEntry<Teacher>)entries.get(i));
			}
			for(i=0;i<courseentries.size()-1;i++) {
				t1=courseentries.get(i).gettimeslot();
				m1=(String) courseentries.get(i).getplanningentryname();
				l1=(Teacher) courseentries.get(i).getresource();
				for(j=i+1;j<courseentries.size();j++) {
					t2=courseentries.get(j).gettimeslot();
					m2=(String) courseentries.get(j).getplanningentryname();
					l2=(Teacher) courseentries.get(j).getresource();
					if(l1.equals(l2)) {
						if(!(t1.getendtime().compareTo(t2.getbegintime())<=0||t2.getendtime().compareTo(t1.getbegintime())<=0)) {
							System.out.println("�ƻ��"+m1+"����ƻ��"+m2+"������ʱ���ͻ��ʹ����ͬһ����ʦ"+"��"+l2.getteachername()+"��");
							flag=true;
						}
					}
				}
			}
			if(flag) {
				return true;
			}
			else {
				System.out.println("û�мƻ������ص�ʱ��ʹ��ͬһ��ʦ");
				return false;
			}
			
		}
		
		if(entries.get(0) instanceof FlightEntry) {
			flag=false;
			Flight f1,f2;
			List<FlightEntry<Flight>> flightentries=new ArrayList<>();
			for(i=0;i<entries.size();i++) {
				flightentries.add((FlightEntry<Flight>)entries.get(i));
			}
			for(i=0;i<flightentries.size()-1;i++) {
				t1=flightentries.get(i).gettimeslot();
				m1=(String) flightentries.get(i).getplanningentryname();
				f1=(Flight) flightentries.get(i).getresource();
				for(j=i+1;j<flightentries.size();j++) {
					t2=flightentries.get(j).gettimeslot();
					m2=(String) flightentries.get(j).getplanningentryname();
					f2=(Flight) flightentries.get(j).getresource();
					if(f1.equals(f2)) {
						if(!(t1.getendtime().compareTo(t2.getbegintime())<=0||t2.getendtime().compareTo(t1.getbegintime())<=0)) {
							System.out.println("�ƻ��"+m1+"����ƻ��"+m2+"������ʱ���ͻ��ʹ����ͬһ���ɻ�"+"��"+f2.getflightnumber()+"��");
							flag=true;
						}
					}
				}
			}
			if(flag) {
				return true;
			}
			else {
				System.out.println("û�г�ͻ��Դ");
				return false;
			}
		}
		
		if(entries.get(0) instanceof TrainEntry) {
			flag=false;
			List<Carriage> b1=new ArrayList<>();
			List<Carriage> b2=new ArrayList<>();
			List<TrainEntry<Carriage>> trainentries=new ArrayList<>();
			Carriage c1,c2 ;          
			for(i=0;i<entries.size();i++) {
				trainentries.add((TrainEntry<Carriage>)entries.get(i));
			}
			for(i=0;i<trainentries.size()-1;i++) {
					changt1=trainentries.get(i).gettimeslot();
			        b1=trainentries.get(i).getresource();
					m1=(String) trainentries.get(i).getplanningentryname();
					for(j=i+1;j<trainentries.size();j++) {
						changt2=trainentries.get(j).gettimeslot();
						b2=trainentries.get(j).getresource();
						m2=(String) trainentries.get(j).getplanningentryname();
						for(k=0;k<b1.size();k++) {
							c1=b1.get(k);
							for(l=0;l<b2.size();l++) {
								c2=b2.get(l);
								if(c1.equals(c2)) {
									if(!(changt1.get(trainentries.size()-1).getendtime().compareTo(changt2.get(0).getbegintime())<=0||
											changt2.get(trainentries.size()-1).getendtime().compareTo(changt1.get(0).getbegintime())<=0)) { 
										System.out.println("�ƻ��"+m1+"����ƻ��"+m2+"������ʱ���ͻ��ʹ����ͬһ������"+"��"+c2.getcarriagenumber()+"��");
										flag=true;
									}
								}
							}
						}
					}
				}
			if(flag) {
				return true;
			}
			else {
				System.out.println("û�г�ͻ��Դ");
				return false;
			}
		}
		System.out.println("��������ȷ��Ϣ");
		return false;
	}
	
	
	/**
	 * �õ�һ��ƻ�����ʹ��ĳһ��Դ�����мƻ����
	 * @param r �����ҵ���Դ
	 * @param entries �ƻ����
	 * @return ʹ�ø���Դ�����мƻ����
	 */
	public List<PlanningEntry> findEntryPerResource(R r,List<PlanningEntry> entries) {
		String l1;
		int i,k;
		if(entries.get(0) instanceof CourseEntry) {
			List<CourseEntry<Teacher>> courseentries=new ArrayList<>();
			List<PlanningEntry> fanhuientries=new ArrayList<>();
			Teacher myteacher=(Teacher) r;
			for(i=0;i<entries.size();i++) {
				courseentries.add((CourseEntry<Teacher>)entries.get(i));
			}
			//Collections.sort(courseentries);
			for(i=0;i<courseentries.size();i++) {
				if(courseentries.get(i).getresource().getteachername().equals(myteacher.getteachername())) {
					l1=(String)courseentries.get(i).getplanningentryname();
					System.out.println("�ƻ��"+l1+"��ʹ���˸���Դ");
					fanhuientries.add(courseentries.get(i));
				}
			}	
			return fanhuientries;
		}
		if(entries.get(0) instanceof FlightEntry) {
			List<FlightEntry<Flight>> flightentries=new ArrayList<>();
			List<PlanningEntry> fanhuientries=new ArrayList<>();
			Flight myflight=(Flight) r;
			for(i=0;i<entries.size();i++) {
				flightentries.add((FlightEntry<Flight>)entries.get(i));
			}
			//Collections.sort(flightentries);
			for(i=0;i<flightentries.size();i++) { 
				if(flightentries.get(i).getresource().getflightnumber().equals(myflight.getflightnumber())) {
					l1=(String)flightentries.get(i).getplanningentryname();
					System.out.println("�ƻ��"+l1+"��ʹ���˸���Դ");
					fanhuientries.add(flightentries.get(i));
				}
			}	
			return fanhuientries;
		}
		if(entries.get(0) instanceof TrainEntry) {
			List<TrainEntry<Carriage>> trainentries=new ArrayList<>();
			List<PlanningEntry> fanhuientries=new ArrayList<>();
			Carriage mycarriage=(Carriage) r;
			for(i=0;i<entries.size();i++) {
				trainentries.add((TrainEntry<Carriage>)entries.get(i));
			}
			//Collections.sort(trainentries);
			for(i=0;i<trainentries.size();i++) { 
				for(k=0;k<trainentries.get(i).getresource().size();k++) {
						if(trainentries.get(i).getresource().get(k).getcarriagenumber().equals(mycarriage.getcarriagenumber())) {
							l1=(String)trainentries.get(i).getplanningentryname();
							System.out.println("�ƻ��"+l1+"��ʹ���˸���Դ");
							fanhuientries.add(trainentries.get(i));
						
					}
				}
			}
			return fanhuientries;	
		}
		System.out.println("��������ȷ��Ϣ");
		   return null;
	}
	
	/**
	 * ��ȡ�����ض���Դ��һ��ǰ��ƻ���
	 * @param r ��Դ
	 * @param e ���и���Դ��ĳ���ƻ���
	 * @param entries  �ƻ����
	 * @return �üƻ����r��ĳ��ǰ��ƻ��û�з���null
	 */
	public PlanningEntry findPreEntryPerResource(R r,PlanningEntry e,List<PlanningEntry> entries) {
		String l1,l2;
		int i,k,l;
		if(entries.get(0) instanceof CourseEntry) {
			List<CourseEntry<Teacher>> courseentries=new ArrayList<>();
			CourseEntry<Teacher> eentries=(CourseEntry<Teacher>)e;
			for(i=0;i<entries.size();i++) {
				courseentries.add((CourseEntry<Teacher>)entries.get(i));
			}
			Collections.sort(courseentries);
			int temp=courseentries.indexOf(eentries);
			for(i=0;i<temp;i++) { //�ҳ�e������ǰ����
				if(courseentries.get(i).getresource().equals(eentries.getresource())) {
					l1=(String)courseentries.get(i).getplanningentryname();
					l2=(String)eentries.getplanningentryname();
					System.out.println("ǰ��ƻ��"+l1+"����ƻ��"+l2+"����������Դ"+"��"+eentries.getresource().getteachername()+"��");
					return courseentries.get(i);
				}
			}	
			System.out.println("û��ǰ��ƻ������Դ");
			return null;
		}
		if(entries.get(0) instanceof FlightEntry) {
			List<FlightEntry<Flight>> flightentries=new ArrayList<>();
			FlightEntry<Flight> eentries=(FlightEntry<Flight>)e;
			for(i=0;i<entries.size();i++) {
				flightentries.add((FlightEntry<Flight>)entries.get(i));
			}
			Collections.sort(flightentries);
			int temp=flightentries.indexOf(eentries);
			for(i=0;i<temp;i++) { //�ҳ�e������ǰ����
				if(flightentries.get(i).getresource().equals(eentries.getresource())) {
					l1=(String)flightentries.get(i).getplanningentryname();
					l2=(String)eentries.getplanningentryname();
					System.out.println("ǰ��ƻ��"+l1+"����ƻ��"+l2+"����������Դ"+"��"+eentries.getresource().getflightnumber()+"��");
					return flightentries.get(i);
				}
			}	
			System.out.println("û��ǰ��ƻ������Դ");
			return null;
		}
		if(entries.get(0) instanceof TrainEntry) {
			List<TrainEntry<Carriage>> trainentries=new ArrayList<>();
			TrainEntry<Carriage> eentries=(TrainEntry<Carriage>)e;
			for(i=0;i<entries.size();i++) {
				trainentries.add((TrainEntry<Carriage>)entries.get(i));
			}
			Carriage mycarriage=(Carriage) r;
			Collections.sort(trainentries);
			int temp=trainentries.indexOf(eentries);
			for(i=0;i<temp;i++) { //�ҳ�e������ǰ����
				for(k=0;k<trainentries.get(i).getresource().size();k++) {
						if(trainentries.get(i).getresource().get(k).equals(mycarriage)) {
							l1=(String)trainentries.get(i).getplanningentryname();
							l2=(String)eentries.getplanningentryname();
							System.out.println("ǰ��ƻ��"+l1+"����ƻ��"+l2+"�������˸���Դ");
							return trainentries.get(i);
						}
				}
			}
			System.out.println("û��ǰ��ƻ������Դ");
			return null;
		}
		System.out.println("��������ȷ��Ϣ");
		return null;
	}
}
