package App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Board.TrainEntryBoard;
import EntryState.*;
import Exception.*;
import Factory.TrainEntryFactory;
import Location.*;
import PlanningEntry.BlockableEntryImpl;
import PlanningEntry.MultipleLacationEntryImpl;
import PlanningEntry.MultipleSortedResourceEntryImpl;
import PlanningEntry.PlanningEntry;
import PlanningEntry.TrainEntry;
import PlanningEntryAPIs.PlanningEntryAPIs;

import Resource.Carriage;
import Timeslot.Timeslot;

public class TrainScheduleApp {

    private static List<TrainEntry<Carriage>> trainlist=new ArrayList<>(); //��������TrainEntry�ļ���
    private final static Logger logger = Logger.getLogger(TrainScheduleApp.class.getName());
	
	 /**
     * �˵�
     */
	public static void menu() {
		System.out.println("1.����һ���µĸ����ƻ��������Ϣ(Ϊ�˷�������Ϣ���⣬����ʱ��ʱ������Ϊ��ǰʱ��һСʱǰ���ʱ��)");
		System.out.println("2.Ϊĳ�������ƻ��������Դ");
		System.out.println("3.���ĳ�������ƻ����λ��");
		System.out.println("4.���ĳ�������ƻ������Դ");
		System.out.println("5.����ĳ�������ƻ������Դ");
		System.out.println("6.ɾ��ĳ�������ƻ������Դ");
		System.out.println("7.(����)����ĳ�������ƻ���");
		System.out.println("8.����ĳ�������ƻ���");
		System.out.println("9.����ĳ�������ƻ���");
		System.out.println("10.ȡ��ĳ�������ƻ���");
		System.out.println("11.�鿴ĳ�������ƻ����״̬");
		System.out.println("****(ע�⣺ִ��12,13,14�Ĳ�����ҪΪÿһ���ƻ��������Դ��ִ��,�����쳣)****");
		System.out.println("12.�������ƻ������Ƿ����λ�ú���Դ��ռ��ͻ");
		System.out.println("13.���ĳ��������Դ���г�����ʹ�ø���Դ�ĸ����ƻ���");
		System.out.println("14.���ĳ��������Դ��ѡ�к��и���Դ��ĳ�������ƻ���г�����ǰ��ƻ���(���һ������)");
		System.out.println("15.ѡ��ĳ��λ�ã�չʾ��ǰʱ�̸�λ�õ���Ϣ��");
		System.out.println("16.��ʾ��ǰ���и����ƻ���ĸ���");
		System.out.println("17.���չ�������������־��ѯ");
		System.out.println("18.д����־����������");
	}
	
	/**
	 * 
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		MultipleLacationEntryImpl a;
		MultipleSortedResourceEntryImpl<Carriage> b;
		BlockableEntryImpl c;
		String choice,weidu="��γ40��",jingdu="����112��";
		Scanner scanner=new Scanner(System.in);
		TrainState state;
		Carriage mycarriage;
		List<Carriage> allcarriage=new ArrayList<>();
		Calendar canceltimeone;
		PlanningEntryAPIs myapis=new PlanningEntryAPIs<>();
		int blockflag;
		String[] temp;
		String yixie;
		System.out.println("�ʼ��ִ�е�һ��");
		
		File file=new File("log/TrainScheduleLog.txt");
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter=new FileWriter(file);
			fileWriter.write("");
			fileWriter.flush();
			fileWriter.close();
		}catch(IOException e) {
			System.out.println("�����ļ�ʧ��");
		}
		try {
			while(true) {
				menu();
				Locale.setDefault(new Locale("en", "EN"));
				logger.setLevel(Level.INFO);
				FileHandler fileHandler;	
				fileHandler = new FileHandler("log/TrainScheduleLog.txt", true);
				fileHandler.setFormatter(new SimpleFormatter());
				logger.addHandler(fileHandler);	
				logger.setUseParentHandlers(false);
				System.out.println("��������Ĳ�����");
				choice=scanner.nextLine();
				switch(choice) {
				case "1":
					a=new MultipleLacationEntryImpl();
					b=new MultipleSortedResourceEntryImpl<Carriage>();
					c=new BlockableEntryImpl();
					PlanningEntry origintrain=new TrainEntryFactory().getTrainEntry(a, b, c); //�ù���������������
					TrainEntry<Carriage> train=(TrainEntry<Carriage>)origintrain;
					System.out.println("�˸����ƻ����Ѿ��������,��������һЩ��Ϣ��ɳ�ʼ״̬�Ľ�����");
					System.out.println("�����복�κ�(eg G1020)��");
					String trainname=scanner.nextLine();
					train.setplanningentryname(trainname);
					logger.log(Level.INFO,"true:setplanningentryname,�ƻ���"+trainname);
					System.out.println("�ƻ����������óɹ�");
					List<Location> alllocation=new ArrayList<>();
					int ll=0;
					System.out.println("������������г����и���վ���ƣ����롰������ֹͣ����(ÿ����һ����һ�»س�)(eg ����)��");
					String tempname;
					while(!(tempname=scanner.nextLine()).equals("����")) {
						FlightTrainLocation from=new FlightTrainLocation(weidu,jingdu,tempname);
						alllocation.add(from);
						ll++;
					}
	                try {
	                	if(train.setlocations(alllocation)){
	                		logger.log(Level.INFO,"true:setlocation,�ƻ���"+trainname);
	                	}
					} catch (SameLocationException e1) {
						logger.log(Level.SEVERE,"flase:setlocation,SameLocationException->operation again,�ƻ���"+trainname,e1);
						System.out.println("�����ظ�λ�ã�������ִ��1����������ȷ��ʽ����\n");
						break;
					}
	                List<Timeslot> alltime=new ArrayList<>();
	                boolean hh=false;
					System.out.println("������������г�����ʱ���(ʱ�����Ӧ�ñȸ���վ����һ)(�ö��Ÿ�����ÿ����һ�԰�һ�»س�)(eg 2020-01-01 12:00,2020-01-01 14:00)��");
					for(int pp=0;pp<ll-1;pp++) {
						try {
							tempname=scanner.nextLine();
							temp=tempname.split(",");
							Timeslot mytime=new Timeslot(temp[0],temp[1]);
							alltime.add(mytime);
						}catch(ArrayIndexOutOfBoundsException e) {
							 logger.log(Level.SEVERE,"flase:settimeslot,ArrayIndexOutOfBoundsException->operation again,�ƻ���"+trainname,e);
							 System.out.println("δ��,������������ִ��1����������ȷ��ʽ����\n");
							 hh=true;
							 break;
						} catch (ParseException e) {
							logger.log(Level.SEVERE,"flase:settimeslot,ParseException->operation again,�ƻ���"+trainname,e);
							System.out.println("ʱ�䲻����yyyy-MM-dd HH:mm��Ҫ��������ִ��1����������ȷ��ʽ����\n");
							break;
						} catch (BeginEndTimeException e) {
							logger.log(Level.SEVERE,"flase:settimeslot,BeginEndTimeException->operation again,�ƻ���"+trainname,e);
							System.out.println("ĳ��ʱ�����ʼʱ��������ֹʱ�䣬������ִ��1����������ȷ��ʽ����\n");
							hh=true;
							break;
						}	
					}
					if(hh==true) {
						break;
					}
					try {
						if(train.settimeslot(alltime)) {
							logger.log(Level.INFO,"true:settimeslot,�ƻ���"+trainname);
						}
					} catch (ConflictTimeException e2) {
						logger.log(Level.SEVERE,"flase:settimeslot,ConflictTimeException->operation again,�ƻ���"+trainname,e2);
						System.out.println("ĳ��վ�ִ�ʱ�����ڳ���ʱ�䣬������ִ��1����������ȷ��ʽ����\n");
						break;
					}
					state = TrainWaitingState.instance;
					train.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					System.out.println("��Ϣ�������");
					trainlist.add(train);
					System.out.println("\n");
					break;
				case "2":
					System.out.println("���������������Դ�ĸ����ƻ���ĳ��κ�(eg G1020)��");
					tempname=scanner.nextLine();
					int i;
					for(i=0;i<trainlist.size();i++) {
						if(trainlist.get(i).getplanningentryname().equals(tempname) ){
							break;
						}
					}
					if(i==trainlist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					List<TrainEntry<Carriage>> temptrainlist=new ArrayList<>();
					for(int oo=0;oo<trainlist.size();oo++) {
						temptrainlist.add(trainlist.get(oo).clone());
					}
					TrainEntry<Carriage> tempentry=trainlist.get(i).clone();
					boolean gg=false;
					allcarriage=new ArrayList<>();
					if(((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("����δ���䳵��(Waiting)")) {
						System.out.println("�밴�մ�����������һ�鳵��ı��(��ͬ���᲻ͬ)�����ͣ���Ա����������ݣ����롰������ֹͣ����(�ö��Ÿ�����ÿ����һ�����ᰴһ�»س�)(eg A01,һ����,100,2012)��");
						while(!(tempname=scanner.nextLine()).equals("����")) {
							try {
								temp=tempname.split(",");
								mycarriage=new Carriage(temp[0],temp[1],Integer.parseInt(temp[2]),temp[3]);
								allcarriage.add(mycarriage);
							}catch(ArrayIndexOutOfBoundsException e) {
								 logger.log(Level.SEVERE,"flase:setresource,ArrayIndexOutOfBoundsException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
								 System.out.println("δ��,������������ִ��2����������ȷ��ʽ����\n");
								 gg=true;
								 break;
							} catch (NumberFormatException e) {
								// TODO �Զ����ɵ� catch ��
								e.printStackTrace();
							} catch (LessThanZeroException e) {
								logger.log(Level.SEVERE,"flase:setresource,LessThanZeroException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
								 System.out.println("��Ա��Ϊ��������������ִ��2����������ȷ��ʽ����\n");
								 gg=true;
								 break;
							}
						}
						if(gg==true) {
							break;
						}
						try {
							tempentry.setresource(allcarriage);
						} catch (SameResourceException e1) {
							 logger.log(Level.SEVERE,"flase:setresource,SameResourceException->operation again,�ƻ���"+tempentry.getplanningentryname(),e1);
							 System.out.println("������ͬ�ĳ��ᣬ������ִ��2����������ȷ��ʽ����\n");
							 break;
						}
						state=((TrainState)tempentry.getcurrentstate()).move('a');
						tempentry.setcurrentstate(state);
						System.out.println("�ƻ��ǰ״̬���óɹ�");
						temptrainlist.set(i, tempentry);
						try {
							if(myapis.checkResourceExclusiveConflict(temptrainlist)) {
								throw new ResourceExclusiveConflictException();
							}
							else {
								trainlist.set(i, tempentry);
								logger.log(Level.INFO,"true:setresource,�ƻ���"+tempentry.getplanningentryname());
								System.out.println("�ɻ���Դ�������\n");
							}
						}catch(ResourceExclusiveConflictException e) {
							logger.log(Level.SEVERE,"flase:setresource,ResourceExclusiveConflictException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
							System.out.println("����Դ�����������е������ƻ������Դ��ռ��ͻ�������·�����Դ\n");
						}
					}
					else {
						logger.log(Level.INFO,"false:no correct state->operation again,�ƻ���"+tempentry.getplanningentryname());
						System.out.println("��ǰ״̬�²���ִ�����÷ɻ���Դ����\n");
					}
					break;
				case "3":	
					logger.log(Level.INFO,"false:changelocation,no changeable location->operation again");
					System.out.println("�����ƻ���λ�ò��ɱ��");
					System.out.println();
					break;
				case "4":	
					System.out.println("��������������Դ�ĸ����ƻ���ĳ��κ�(eg G1020)��");
					tempname=scanner.nextLine();
					for(i=0;i<trainlist.size();i++) {
						if(trainlist.get(i).getplanningentryname().equals(tempname) ){
							break;
						}
					}
					if(i==trainlist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					tempentry=trainlist.get(i);
					allcarriage=tempentry.getresource();
					int j;
					if(((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("�����ѷ���һ�鳵��(Allocated)")) {
						System.out.println("����������Ҫ����ĳ���ı��(eg A01)��");
						tempname=scanner.nextLine();
						for(j=0;j<allcarriage.size();j++) {
							if(allcarriage.get(j).getcarriagenumber().equals(tempname)) {
								break;
							}
						}
						if(j==allcarriage.size()) {
							logger.log(Level.INFO,"false:can not find this carriage->operation again");
							System.out.println("û�иó���");
							System.out.println("\n");
							break;
						}
						Carriage tempcarriage=allcarriage.get(j);
						System.out.println("����������ĳ���ı�ţ����ͣ���Ա�����������(�ö��Ÿ���)(eg NB02,������,100,2011)��");
						try {
							temp=(scanner.nextLine()).split(",");
							mycarriage=new Carriage(temp[0],temp[1],Integer.parseInt(temp[2]),temp[3]);
							if(tempentry.changeresource(tempcarriage,mycarriage)) {
								logger.log(Level.INFO,"true:changeresource,�ƻ���"+tempentry.getplanningentryname());
							}
							else {
								logger.log(Level.INFO,"false:changeresource,�ƻ���"+tempentry.getplanningentryname());
								break;
							}
							trainlist.set(i, tempentry);
						}catch(ArrayIndexOutOfBoundsException e) {
							 logger.log(Level.SEVERE,"flase:changeresource,ArrayIndexOutOfBoundsException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
							 System.out.println("δ��,������������ִ��4����������ȷ��ʽ����\n");
							 break;
						} catch (NumberFormatException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						} catch (LessThanZeroException e) {
							 logger.log(Level.SEVERE,"flase:changeresource,LessThanZeroException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
							 System.out.println("��Ա��Ϊ��������������ִ��4����������ȷ��ʽ����\n");
							 break;
						}
					}
					else {
						logger.log(Level.INFO,"false:no correct state->operation again,�ƻ���"+tempentry.getplanningentryname());
						System.out.println("��ǰ״̬�²���ִ�б���������\n");
					}
					break;
				case "5":
					System.out.println("����������������Դ�ĸ����ƻ���ĳ��κ�(eg G1020)��");
					tempname=scanner.nextLine();
					for(i=0;i<trainlist.size();i++) {
						if(trainlist.get(i).getplanningentryname().equals(tempname) ){
							break;
						}
					}
					if(i==trainlist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					tempentry=trainlist.get(i);
					allcarriage=tempentry.getresource();
					if(((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("�����ѷ���һ�鳵��(Allocated)")) {
						System.out.println("���������ӵĳ���ı�ţ����ͣ���Ա�����������(�ö��Ÿ���)(eg AS02,������,100,2011)��");
						try {
							temp=(scanner.nextLine()).split(",");
							mycarriage=new Carriage(temp[0],temp[1],Integer.parseInt(temp[2]),temp[3]);
							System.out.println("���������ӵĳ�����һ�鳵���е�λ��(��һ�ڳ���Ĭ��λ��Ϊ1)(eg 3)��");
							tempname=scanner.nextLine();
							int weizhi=Integer.parseInt(tempname);
							if(weizhi>tempentry.getresource().size()+1||weizhi<1) {
								logger.log(Level.INFO,"false:addresource,�ƻ���"+tempentry.getplanningentryname());
								System.out.println("���ӵĳ���λ�ò��Ϸ�\n");
								break;
							}
							if(tempentry.addresource(mycarriage,weizhi-1)) {
								logger.log(Level.INFO,"true:addresource,�ƻ���"+tempentry.getplanningentryname());
							}
							else {
								logger.log(Level.INFO,"false:addresource,�ƻ���"+tempentry.getplanningentryname());
							}
							trainlist.set(i, tempentry);
						}catch(ArrayIndexOutOfBoundsException e) {
							 logger.log(Level.SEVERE,"flase:addresource,ArrayIndexOutOfBoundsException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
							 System.out.println("δ��,������������ִ��5����������ȷ��ʽ����\n");
							 break;
						} catch (NumberFormatException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						} catch (LessThanZeroException e) {
							 logger.log(Level.SEVERE,"flase:addresource,LessThanZeroException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
							 System.out.println("��Ա��Ϊ��������������ִ��5����������ȷ��ʽ����\n");
							 break;
						}
					}
					else {
						logger.log(Level.INFO,"false:no correct state->operation again,�ƻ���"+tempentry.getplanningentryname());
						System.out.println("��ǰ״̬�²���ִ��������Դ����\n");
					}
					break;
				case "6":
					System.out.println("����������ɾ����Դ�ĸ����ƻ���ĳ��κ�(eg G1020)��");
					tempname=scanner.nextLine();
					for(i=0;i<trainlist.size();i++) {
						if(trainlist.get(i).getplanningentryname().equals(tempname) ){
							break;
						}
					}
					if(i==trainlist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					tempentry=trainlist.get(i);
					allcarriage=tempentry.getresource();
					if(((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("�����ѷ���һ�鳵��(Allocated)")) {
						System.out.println("����������Ҫɾ���ĳ���ı��(eg A01)��");
						tempname=scanner.nextLine();
						for(j=0;j<allcarriage.size();j++) {
							if(allcarriage.get(j).getcarriagenumber().equals(tempname)) {
								break;
							}
						}
						if(j==allcarriage.size()) {
							logger.log(Level.INFO,"false:can not find this carriage->operation again");
							System.out.println("û�иó���");
							System.out.println("\n");
							break;
						}
						Carriage tempcarriage=allcarriage.get(j);
						try {
							for(int kk=0;kk<trainlist.size();kk++) {
								if(trainlist.get(kk).getresource().contains(tempcarriage)&&
										!((TrainState)trainlist.get(kk).getcurrentstate()).gettrainstate().equals("�����ѵִ��յ�վ(Ended)")) {
									throw new NoendedCarriageException();
								}
							}
						}catch(NoendedCarriageException e) {
							logger.log(Level.SEVERE,"deleteresource,NoendedCarriageException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
							System.out.println("����δ�����ļƻ�������ռ�ø���Դ���޷�ɾ������Դ\n");
							break;
						}
						if(tempentry.deleteresource(tempcarriage)) {
							logger.log(Level.INFO,"true:deleteresource,�ƻ���"+tempentry.getplanningentryname());
						}
						else {
							logger.log(Level.INFO,"false:deleteresource,�ƻ���"+tempentry.getplanningentryname());
						}
						trainlist.set(i, tempentry);
					}
					else {
						logger.log(Level.INFO,"false:no correct state->operation again,�ƻ���"+tempentry.getplanningentryname());
						System.out.println("��ǰ״̬�²���ִ��ɾ����Դ����\n");
					}
					break;
				case "7":
					System.out.println("����������Ҫ�����ĸ����ƻ���ĳ��κ�(eg G1020)��");
					tempname=scanner.nextLine();
					for(i=0;i<trainlist.size();i++) {
						if(trainlist.get(i).getplanningentryname().equals(tempname) ){
							break;
						}
					}
					if(i==trainlist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					tempentry=trainlist.get(i);
					if(((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("�����ѷ���һ�鳵��(Allocated)")||
							((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("������;����(Blocked)")) {	
						System.out.println("������ָ�����");
						yixie=scanner.nextLine();
						if(tempentry.launch(yixie)) {
						logger.log(Level.INFO,"true:launch,�ƻ���"+tempentry.getplanningentryname());
						state=((TrainState)tempentry.getcurrentstate()).move('a');
						tempentry.setcurrentstate(state);
						System.out.println("�ƻ��ǰ״̬���óɹ�");
						trainlist.set(i, tempentry);
						System.out.println("\n");
						}
						else {
							logger.log(Level.INFO,"false:launch,�ƻ���"+tempentry.getplanningentryname());
							System.out.println("\n");
							break;
						}
					}
					else {
						logger.log(Level.INFO,"false:no correct state->operation again,�ƻ���"+tempentry.getplanningentryname());
						System.out.println("��ǰ״̬�²���ִ��������������\n");
					}
					break;
				case "8":
					System.out.println("����������Ҫ�����ĸ����ƻ���ĳ��κ�(eg G1020)��");
					tempname=scanner.nextLine();
					for(i=0;i<trainlist.size();i++) {
						if(trainlist.get(i).getplanningentryname().equals(tempname) ){
							break;
						}
					}
					if(i==trainlist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					tempentry=trainlist.get(i);
					if(((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("�����Ѵ���ʼվ����(Running)")) {	
						System.out.println("����������Ҫ�����ĸ���վλ�ã�(eg �人)");
						yixie=scanner.nextLine();
						blockflag=tempentry.trainblock(yixie);
						if(blockflag==-1) {
							logger.log(Level.INFO,"false:trainblock,�ƻ���"+tempentry.getplanningentryname());
							break;
						}
						logger.log(Level.INFO,"true:trainblock,�ƻ���"+tempentry.getplanningentryname());
						state=((TrainState)tempentry.getcurrentstate()).move('b');
						tempentry.setcurrentstate(state);
						System.out.println("�ƻ��ǰ״̬���óɹ�");
						trainlist.set(i, tempentry);
						System.out.println("\n");
					}
					else {
						logger.log(Level.INFO,"false:no correct state->operation again,�ƻ���"+tempentry.getplanningentryname());
						System.out.println("��ǰ״̬�²���ִ��������������\n");
					}
					break;
				case "9":
					System.out.println("����������Ҫ�����ĸ����ƻ���ĳ��κ�(eg G1020)��");
					tempname=scanner.nextLine();
					for(i=0;i<trainlist.size();i++) {
						if(trainlist.get(i).getplanningentryname().equals(tempname) ){
							break;
						}
					}
					if(i==trainlist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					tempentry=trainlist.get(i);
						if(((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("�����Ѵ���ʼվ����(Running)")) {	
							System.out.println("������ָ�����");
							yixie=scanner.nextLine();
							if(tempentry.finish(yixie)) {
							logger.log(Level.INFO,"true:finish,�ƻ���"+tempentry.getplanningentryname());
							state=((TrainState)tempentry.getcurrentstate()).move('a');
							tempentry.setcurrentstate(state);
							System.out.println("�ƻ��ǰ״̬���óɹ�");
							trainlist.set(i, tempentry);
							System.out.println("\n");
							}
							else {
								logger.log(Level.INFO,"false:finish,�ƻ���"+tempentry.getplanningentryname());
								System.out.println("\n");
								break;
							}
						}
						else {
							logger.log(Level.INFO,"false:no correct state->operation again,�ƻ���"+tempentry.getplanningentryname());
							System.out.println("��ǰ״̬�²���ִ�н�����������\n");
							break;
						}	
					break;
				case "10":
					System.out.println("����������Ҫȡ���ĸ����ƻ���ĳ��κ�(eg G1020)��");
					tempname=scanner.nextLine();
					for(i=0;i<trainlist.size();i++) {
						if(trainlist.get(i).getplanningentryname().equals(tempname) ){
							break;
						}
					}
					if(i==trainlist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					tempentry=trainlist.get(i);
					try {
						if(((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("�����ѷ���һ�鳵��(Allocated)")||
								((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("������;����(Blocked)")||
								((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("����δ���䳵��(Waiting)")) {	
									System.out.println("������ָ�ȡ��");
									yixie=scanner.nextLine();	
									if(tempentry.cancel(yixie)) {
									canceltimeone=Calendar.getInstance();
									String str = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(canceltimeone.getTime()); 
									try {
										canceltimeone.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str));
										logger.log(Level.INFO,"true:cancel,�ƻ���"+tempentry.getplanningentryname());
									} catch (ParseException e) {
										logger.log(Level.SEVERE,"false:cancel,ParseException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
										System.out.println("ȡ��ʱ�����\n");
										break;					
										}
									System.out.println("�ú���ƻ���ȡ����ʱ��Ϊ"+str);
									state=((TrainState)tempentry.getcurrentstate()).move('b');
									tempentry.setcurrentstate(state);
									System.out.println("�ƻ��ǰ״̬���óɹ�");
									trainlist.remove(i);
									System.out.println("\n");
									}
						}
						else {
							throw new NoCancelStateException();
						}	
					}catch(NoCancelStateException e) {
						logger.log(Level.SEVERE,"false:cancel,NoCancelStateException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
						System.out.println("��ǰ״̬�²���ִ��ȡ����������\n");
						break;
					}
					break;
	             case "11":
	            	System.out.println("����������Ҫ�鿴�ĸ����ƻ���ĳ��κ�(eg G1020)��");
	 				tempname=scanner.nextLine();
	 				for(i=0;i<trainlist.size();i++) {
						if(trainlist.get(i).getplanningentryname().equals(tempname) ){
							break;
						}
					}
					if(i==trainlist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ�������ѱ�ȡ��");
						System.out.println("\n");
						break;
					}
					tempentry=trainlist.get(i);
	 				System.out.println("�ø����ƻ����״̬Ϊ��"+((TrainState)tempentry.getcurrentstate()).gettrainstate());
	 				logger.log(Level.INFO,"true:getcurrentstate,�ƻ���"+tempentry.getplanningentryname());
	 				System.out.println("\n");
					break;
	             case "12":
	            	System.out.println("����λ�ö�ռ��ͻ�������ѡ�������㷨�������ж�(����1ʹ�õ�һ�֣�����2ʹ�õڶ���)����ѡ������(eg 1)");
	    			tempname=scanner.nextLine();
	            	System.out.println("���мƻ�����λ�ö�ռ��ͻ������£�");
	    			if(myapis.checkLocationConflict(trainlist,tempname)) {
	    				logger.log(Level.INFO,"true:LocationConflict");
	    			}
	    			else {
	    				logger.log(Level.INFO,"false:LocationConflict");
	    			}
	    			System.out.println("���мƻ�������Դ��ռ��ͻ������£�");
	    			if(myapis.checkResourceExclusiveConflict(trainlist)) {
	    				logger.log(Level.INFO,"true:ResourceExclusiveConflict");
	    			}
	    			else {
	    				logger.log(Level.INFO,"false:ResourceExclusiveConflict");
	    			}
	    			System.out.println("\n");
	            	 break;
	             case "13":
	            	System.out.println("����������Ҫ�鿴�ĳ�����Դ�ĳ���ı�ţ����ͣ���Ա�����������(�ö��Ÿ���)(eg A01,������,100,2011)��");
	            	try {
	            		temp=(scanner.nextLine()).split(",");
	    				mycarriage=new Carriage(temp[0],temp[1],Integer.parseInt(temp[2]),temp[3]);
	    				myapis.findEntryPerResource(mycarriage, trainlist);
		 				logger.log(Level.INFO,"findEntryPerResource");
					}catch(ArrayIndexOutOfBoundsException e) {
						logger.log(Level.SEVERE,"flase:setresource,ArrayIndexOutOfBoundsException->operation again");
						 System.out.println("δ��,������������ִ��13����������ȷ��ʽ����\n");
						 break;
					} catch (NumberFormatException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					} catch (LessThanZeroException e) {
						logger.log(Level.SEVERE,"flase:setresource,LessThanZeroException->operation again");
						 System.out.println("��Ա��Ϊ��������������ִ��13����������ȷ��ʽ����\n");
						 break;
					}
	 				System.out.println("\n");
	            	break;
	             case "14":
	            	System.out.println("����������Ҫ�鿴��ǰ��ƻ���ĳ��κ�(eg G1020)��");
	            	tempname=scanner.nextLine();
	  				for(i=0;i<trainlist.size();i++) {
	 					if(trainlist.get(i).getplanningentryname().equals(tempname) ){
	 						break;
	 					}
	 				}
	 				if(i==trainlist.size()) {
	 					logger.log(Level.INFO,"false:can not find this entry->operation again");
	 					System.out.println("û�иüƻ���");
	 					System.out.println("\n");
	 					break;
	 				}
	 				tempentry=trainlist.get(i);
	 				System.out.println("��������Ҫ�鿴ǰ��ƻ���ĸø����ƻ�����ӵ�еĳ�����Դ�ı��(eg A01)��");
	 				tempname=scanner.nextLine();
	 				for(j=0;j<tempentry.getresource().size();j++) {
	 					if(tempentry.getresource().get(j).getcarriagenumber().equals(tempname)) {
	 						break;
	 					}
	 				}
	 				if(j==tempentry.getresource().size()) {
	 					logger.log(Level.INFO,"false:can not find this carriage->operation again");
	 					System.out.println("û�иó�����Դ");
	 					System.out.println("\n");
	 					break;
	 				}
	 				mycarriage=tempentry.getresource().get(j);
	                myapis.findPreEntryPerResource(mycarriage,tempentry,trainlist);	
	                logger.log(Level.INFO,"findPreEntryPerResource");
	                System.out.println("\n");	
	            	break;
	             case "15":
	            	System.out.println("����������Ҫչʾ��ǰʱ�̵���Ϣ���λ��(eg ����)");
	    			tempname=scanner.nextLine();
	    			TrainEntryBoard trainboard=new TrainEntryBoard();	
	    			trainboard.setrailwaylocation(tempname);
	    			trainboard.getsortcomeentry(trainlist);
	    			trainboard.getsorttoentry(trainlist);
	    			try {
						trainboard.createTrainEntryBoard();
						logger.log(Level.INFO,"true:TrainEntryBoard");
					} catch (ParseException e) {
						logger.log(Level.SEVERE,"false:FlightEntryBoard,ParseException->operation again",e);
						System.out.println("ʱ���ʽ����");
						break;
					}
	    			trainboard.visualize();	
	    			System.out.println("\n");
	            	 break;
	             case "16":
	            	logger.log(Level.INFO,"true:get the number of allentrys");
	                System.out.println("��ǰ���и����ƻ���ĸ���Ϊ��"+trainlist.size()+"\n");
	 				break;
	             case "17":
	            	 fileHandler.close();
						BufferedReader thisfile;
						String fileoneline;
						try {
							thisfile = new BufferedReader(new FileReader("log/TrainScheduleLog.txt"));
						} catch (FileNotFoundException e1) {
							logger.log(Level.SEVERE,"false:findfile,FileNotFoundException->operation again",e1);
							System.out.println("�ļ�������\n");
							break;
						}
						fileoneline=null;
						List<String> alllog=new ArrayList<>();
						String onelog="";
						try {
							while((fileoneline=thisfile.readLine())!=null) { 
								onelog=onelog+fileoneline+"\n"; //�ǵü��ϻ��з�
								if(fileoneline.contains("INFO")||fileoneline.equals("")) {
									alllog.add(onelog);
									onelog="";
								}
							}
						} catch (IOException e1) {
							System.out.println("�����ļ��쳣\n");
						}finally{
							thisfile.close();
						}
						String mychoice;
						int lll;
						System.out.println("�����ѡ�����ֹ�����������һ��Ϊ���������Ͳ�ѯ���ڶ���Ϊ���ƻ������ֲ�ѯ��������Ϊ����ʱ��β�ѯ(����1ʹ�õ�һ�֣�����2ʹ�õڶ��֣�����3ʹ�õ�����)����ѡ������(eg 1)");
						tempname=scanner.nextLine();
						if(tempname.equals("1")) {
							System.out.println("һ�������²������ͣ�����ѡһ�ֽ��в�ѯ��(eg setlocation)");
							System.out.println("setplanningentryname,setlocation,settimeslot,setresource,changelocation,changeresource,addresource,deleteresource,launch,trainblock,finish"
									+ ",cancel,getcurrentstate,LocationConflict,ResourceExclusiveConflict,findEntryPerResource,findPreEntryPerResource,"
									+ "TrainEntryBoard,get the number of allentrys");
							mychoice=scanner.nextLine();
							lll=1;
							System.out.println("�ò�����������־Ϊ��");
							for(String h:alllog) {
								if(h.contains(mychoice)) {
									 System.out.print("��־"+lll+"Ϊ��"+h+"\n");
									 lll++;
								}
							}
							if(lll==1) {
								System.out.print("û�иò�������־\n");
							}
						}
						if(tempname.equals("2")) {
							System.out.println("������ƻ������֣�(eg G1020)");
							mychoice=scanner.nextLine();
							System.out.println("��üƻ����йص�������־Ϊ��");
							lll=1;
							for(String h:alllog) {
								if(h.contains(mychoice)) {
									 System.out.print("��־"+lll+"Ϊ��"+h+"\n");
									 lll++;
								}
							}
							if(lll==1) {
								System.out.print("û�иüƻ������־\n");
							}
						}
						if(tempname.equals("3")) {
							Pattern pattern1 = Pattern.compile("([A-Z|a-z][A-Z|a-z][A-Z|a-z])\\s(\\d{2}),\\s(\\d{4})\\s(\\d{2}|[1-9]):(\\d{2}):(\\d{2})\\s(PM|AM)");
							Matcher tomatcher;
							String month,day,year,hour,minute,panduan,time;
							System.out.println("������������ҵ�ʱ���(�ö��Ÿ���)(eg 2020-06-04 19:00,2020-06-04 19:01)��");
							try {
								temp=(scanner.nextLine()).split(",");
								Timeslot mytime=new Timeslot(temp[0],temp[1]);
								lll=1;
								System.out.println("��ʱ��ε�������־Ϊ��");
								for(String h:alllog) {
									tomatcher=pattern1.matcher(h);
									if(tomatcher.find()) {  //�������ʱ����Ϣ
										month=tomatcher.group(1);
										day=tomatcher.group(2);
										year=tomatcher.group(3);
										hour=tomatcher.group(4);
										minute=tomatcher.group(5);
										panduan=tomatcher.group(7);
										switch(month) {   //���·�ת��Ϊ������ʽ
										case "Jan":
											month="01";
											break;
										case "Feb":
											month="02";
											break;
										case "Mar":
											month="03";
											break;
										case "Apr":
											month="04";
											break;
										case "May":
											month="05";
											break;
										case "Jun":
											month="06";
											break;
										case "Jul":
											month="07";
											break;
										case "Aug":
											month="08";
											break;
										case "Sep":
											month="09";
											break;
										case "Oct":
											month="10";
											break;
										case "Nov":
											month="11";
											break;
										case "Dec":
											month="12";
											break;	
										}
										if(panduan.equals("PM")) {  //����PM�ģ�Сʱ����12
											hour=Integer.toString(Integer.parseInt(hour)+12);
										}
										if(panduan.equals("AM")&&Integer.parseInt(hour)<10) {  //����AM�ģ�Сʱ��С��10�ģ�ǰ��Ӹ�0
											hour="0"+hour;
										}
										time=year+"-"+month+"-"+day+" "+hour+":"+minute;
										Calendar temptime= Calendar.getInstance(); 
										temptime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(time));
										if(temptime.compareTo(mytime.getbegintime())>0&&temptime.compareTo(mytime.getendtime())<0) {  
											System.out.print("��־"+lll+"Ϊ��"+h+"\n");
											lll++;
										}
										
									}
								}
								if(lll==1) {
									System.out.print("û�и�ʱ��ε���־\n");
								}
							}catch(ArrayIndexOutOfBoundsException e) {
								 System.out.println("δ��,������������ִ�в���������ȷ��ʽ����\n");
								 break;
							}catch(ParseException e) {
								System.out.println("ʱ�䲻����yyyy-MM-dd HH:mm��Ҫ��������ִ�в���������ȷ��ʽ����\n");
								break;
							} catch (BeginEndTimeException e) {
								System.out.println("��ʼʱ��������ֹʱ�䣬������ִ�в���������ȷ��ʽ����\n");
								break;
							}
						}
						System.out.print("\n");
						break;
	         	 case "18":
	         		logger.log(Level.INFO,"true:over program");
					fileHandler.close();
					System.out.println("��־��д���ҳ����ѽ���");
					System.exit(0);
					break;
	             default:
	 				System.out.println("��������ȷָ��\n");
	 				break;		
				}	
				fileHandler.close();
			}
		}catch (SecurityException e3) {
			e3.printStackTrace();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
	}
}
