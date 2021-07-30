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

import Board.FlightEntryBoard;
import EntryState.*;
import Exception.*;
import Factory.FlightEntryFactory;
import Location.*;
import Parser.Parser;
import PlanningEntry.*;
import PlanningEntryAPIs.PlanningEntryAPIs;
import Resource.Flight;
import Timeslot.*;

public class FlightScheduleApp {

	private static List<FlightEntry<Flight>> flightlist=new ArrayList<>(); //��������FlightEntry�ļ���
	private final static Logger logger = Logger.getLogger(FlightScheduleApp.class.getName());
	
	 /**
     * �˵�
     */
	public static void menu() {
		System.out.println("1.����һ���µĺ���ƻ��������Ϣ(Ϊ�˷�������Ϣ���⣬����ʱ��ʱ������Ϊ��ǰʱ��һСʱǰ���ʱ��)");
		System.out.println("2.Ϊĳ������ƻ��������Դ");
		System.out.println("3.���ĳ������ƻ����λ��");
		System.out.println("4.���ĳ������ƻ������Դ");
		System.out.println("5.����ĳ������ƻ���");
		System.out.println("6.����ĳ������ƻ���");
		System.out.println("7.ȡ��ĳ������ƻ���");
		System.out.println("8.�鿴ĳ������ƻ����״̬");
		System.out.println("9.���ļ��ж��뺽��ƻ������ƻ������(��ȡ����ļ����ܺ�ʱ�ܳ�)");
		System.out.println("****(ע�⣺ִ��10,11,12�Ĳ�����ҪΪÿһ���ƻ��������Դ��ִ��,�����쳣)****");
		System.out.println("10.��⺽��ƻ������Ƿ����λ�ú���Դ��ռ��ͻ");
		System.out.println("11.���ĳ���ɻ���Դ���г�����ʹ�ø���Դ�ĺ���ƻ���");
		System.out.println("12.���ĳ���ɻ���Դ��ѡ�к��и���Դ��ĳ������ƻ���г�����ǰ��ƻ���(���һ������)");
		System.out.println("13.ѡ��ĳ��λ�ã�չʾ��ǰʱ�̸�λ�õ���Ϣ��");
		System.out.println("14.��ʾ��ǰ���к���ƻ���ĸ���");
		System.out.println("15.���չ�������������־��ѯ");
		System.out.println("16.д����־���ļ�����������");
	}
	
	
	/**
	 * 
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args)   {
		TwoLocationEntryImpl a;
		OneDistinguishResourceEntryImpl<Flight> b;
		NoBlockableEntryImpl c;
		String choice,weidu="��γ40��",jingdu="����112��";
		Scanner scanner=new Scanner(System.in);
		FlightState state;
		Flight myflight;
		Calendar canceltimeone;
		PlanningEntryAPIs myapis=new PlanningEntryAPIs<>();
		String fileentry,fileoneline;
		List<String> allfileentry=new ArrayList<>();
		System.out.println("�ʼ��ִ�е�һ��");
		
		File file=new File("log/FlightScheduleLog.txt");
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
				String[] temp;
				String yixie;
				menu();
				Locale.setDefault(new Locale("en", "EN"));
				logger.setLevel(Level.INFO);
				FileHandler fileHandler;	
				fileHandler = new FileHandler("log/FlightScheduleLog.txt", true);
				fileHandler.setFormatter(new SimpleFormatter());
				logger.addHandler(fileHandler);	
				logger.setUseParentHandlers(false);
				System.out.println("��������Ĳ�����");
				choice=scanner.nextLine();
				switch(choice) {
				case "1":
					a=new TwoLocationEntryImpl();
					b=new OneDistinguishResourceEntryImpl<Flight>();
					c=new NoBlockableEntryImpl();
					PlanningEntry originflight=new FlightEntryFactory().getFlightEntry(a, b, c); //�ù���������������
					FlightEntry<Flight> flight=(FlightEntry<Flight>)originflight;
					System.out.println("�˺���ƻ����Ѿ��������,��������һЩ��Ϣ��ɳ�ʼ״̬�Ľ�����");
					System.out.println("�����뺽���(eg AH3567)��");
					String flightname=scanner.nextLine();
					flight.setplanningentryname(flightname);
					logger.log(Level.INFO,"true:setplanningentryname,�ƻ���"+flightname);
				    System.out.println("�ƻ����������óɹ�");
					System.out.println("���������վ����(eg ����)��");
					String tempname;
					tempname=scanner.nextLine();
					FlightTrainLocation from=new FlightTrainLocation(weidu,jingdu,tempname);
					System.out.println("�������յ�վ����(eg �人)��");
					tempname=scanner.nextLine();
					FlightTrainLocation to=new FlightTrainLocation(weidu,jingdu,tempname);
					try {
						if(flight.setlocations(from, to)) {
							logger.log(Level.INFO,"true:setlocation,�ƻ���"+flightname);
						}
					} catch (SameLocationException e2) {
						logger.log(Level.SEVERE,"flase:setlocation,SameLocationException->operation again,�ƻ���"+flightname,e2);
						System.out.println("����������ͬ�ɻ���λ�ã�������ִ��1����������ȷ��ʽ����\n");
						break;
					}
					System.out.println("��������ɺͽ���ʱ��(�ö��Ÿ���)(eg 2020-01-01 12:00,2020-01-01 14:00)��");
					try {
						temp=(scanner.nextLine()).split(",");
						Timeslot mytime=new Timeslot(temp[0],temp[1]);
						if(flight.settimeslot(mytime)) {
							logger.log(Level.INFO,"true:settimeslot,�ƻ���"+flightname);
							System.out.println("һ��ʱ������óɹ�");
						}
					}catch(ArrayIndexOutOfBoundsException e) {
						 logger.log(Level.SEVERE,"flase:settimeslot,ArrayIndexOutOfBoundsException->operation again,�ƻ���"+flightname,e);
						 System.out.println("δ��,������������ִ��1����������ȷ��ʽ����\n");
						 break;
					}catch(ParseException e) {
						logger.log(Level.SEVERE,"flase:settimeslot,ParseException->operation again,�ƻ���"+flightname,e);
						System.out.println("ʱ�䲻����yyyy-MM-dd HH:mm��Ҫ��������ִ��1����������ȷ��ʽ����\n");
						break;
					} catch (BeginEndTimeException e) {
						logger.log(Level.SEVERE,"flase:settimeslot,BeginEndTimeException->operation again,�ƻ���"+flightname,e);
						System.out.println("��ʼʱ��������ֹʱ�䣬������ִ��1����������ȷ��ʽ����\n");
						break;
					}
					state = FlightWaitingState.instance;
					flight.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					System.out.println("��Ϣ�������");
					flightlist.add(flight);
					System.out.println("\n");
					break;
				case "2":
					System.out.println("���������������Դ�ĺ���ƻ���ĺ����(eg AH3567)��");
					tempname=scanner.nextLine();
					int i;
					for(i=0;i<flightlist.size();i++) {
						if(flightlist.get(i).getplanningentryname().equals(tempname) ){
							break;
						}
					}
					if(i==flightlist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println();
						break;
					}
					List<FlightEntry<Flight>> tempflightlist=new ArrayList<>();
					for(int oo=0;oo<flightlist.size();oo++) {
						tempflightlist.add(flightlist.get(oo).clone());
					}
					FlightEntry<Flight> tempentry=flightlist.get(i).clone();
					if(((FlightState)tempentry.getcurrentstate()).getflightstate().equals("����δ����ɻ�(Waiting)")) {
						System.out.println("�����������ɻ���ţ����ͺţ���λ��������(eg N8981,C88,100,2.5)��");
						try {
							temp=(scanner.nextLine()).split(",");
							myflight=new Flight(temp[0],temp[1],Integer.parseInt(temp[2]),Double.parseDouble(temp[3]));
							tempentry.setresource(myflight);
						}catch(ArrayIndexOutOfBoundsException e) {
							 logger.log(Level.SEVERE,"flase:setresource,ArrayIndexOutOfBoundsException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
							 System.out.println("δ��,������������ִ��2����������ȷ��ʽ����\n");
							 break;
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (LessThanZeroException e) {
							 logger.log(Level.SEVERE,"flase:setresource,LessThanZeroException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
							 System.out.println("��λ��Ϊ�����������Ϊ������������ִ��2����������ȷ��ʽ����\n");
							 break;
						}
						state=((FlightState)tempentry.getcurrentstate()).move('a');
						tempentry.setcurrentstate(state);
						System.out.println("�ƻ��ǰ״̬���óɹ�");
						tempflightlist.set(i, tempentry);
						try {
							if(myapis.checkResourceExclusiveConflict(tempflightlist)) {
								throw new ResourceExclusiveConflictException();
							}
							else {
								flightlist.set(i, tempentry);
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
					System.out.println("���������յ�λ�ò��ɱ��");
					System.out.println("\n");
					break;
				case "4":
					System.out.println("��������������Դ�ĺ���ƻ���ĺ����(eg AH3567)��");
					tempname=scanner.nextLine();
					for(i=0;i<flightlist.size();i++) {
						if(flightlist.get(i).getplanningentryname().equals(tempname)) {
							break;
						}
					}
					if(i==flightlist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					tempentry=flightlist.get(i);
					if(((FlightState)tempentry.getcurrentstate()).getflightstate().equals("�����ѷ���ɻ�(Allocated)")) {
						System.out.println("�������µķɻ���ţ����ͺţ���λ��������(eg B1104,A22,100,2.5)��");
						try {
							temp=(scanner.nextLine()).split(",");
							myflight=new Flight(temp[0],temp[1],Integer.parseInt(temp[2]),Double.parseDouble(temp[3]));
							if(tempentry.changeresource(myflight)) {
								logger.log(Level.INFO,"true:changeresource,�ƻ���"+tempentry.getplanningentryname());
							}
							else {
								logger.log(Level.INFO,"false:changeresource,�ƻ���"+tempentry.getplanningentryname());
								break;
							}
						}catch(ArrayIndexOutOfBoundsException e) {
							 logger.log(Level.SEVERE,"flase:changeresource,ArrayIndexOutOfBoundsException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
							 System.out.println("δ��,������������ִ��4����������ȷ��ʽ����\n");
							 break;
						} catch (NumberFormatException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						} catch (LessThanZeroException e) {
							 logger.log(Level.SEVERE,"flase:setresource,LessThanZeroException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
							 System.out.println("��λ��Ϊ�����������Ϊ������������ִ��4����������ȷ��ʽ����\n");
							 break;
						}
						flightlist.set(i, tempentry);
						System.out.println("\n");
					}
					else {
						logger.log(Level.INFO,"false:no correct state->operation again,�ƻ���"+tempentry.getplanningentryname());
						System.out.println("��ǰ״̬�²���ִ�б���ɻ���Դ����\n");
					}
					break;
				case "5":
					System.out.println("����������Ҫ�����ĺ���ƻ���ĺ����(eg AH3567)��");
					tempname=scanner.nextLine();
					for(i=0;i<flightlist.size();i++) {
						if(flightlist.get(i).getplanningentryname().equals(tempname)) {
							break;
						}
					}
					if(i==flightlist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					tempentry=flightlist.get(i);
					if(((FlightState)tempentry.getcurrentstate()).getflightstate().equals("�����ѷ���ɻ�(Allocated)")) {	
						System.out.println("������ָ�����");
						yixie=scanner.nextLine();
						if(tempentry.launch(yixie)) {
						logger.log(Level.INFO,"true:launch,�ƻ���"+tempentry.getplanningentryname());
						state=((FlightState)tempentry.getcurrentstate()).move('a');
						tempentry.setcurrentstate(state);
						System.out.println("�ƻ��ǰ״̬���óɹ�");
						flightlist.set(i, tempentry);
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
						System.out.println("��ǰ״̬�²���ִ����ɲ���\n");
					}
					break;
				case "6":
					System.out.println("����������Ҫ�����ĺ���ƻ���ĺ����(eg AH3567)��");
					tempname=scanner.nextLine();
					for(i=0;i<flightlist.size();i++) {
						if(flightlist.get(i).getplanningentryname().equals(tempname)) {
							break;
						}
					}
					if(i==flightlist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					tempentry=flightlist.get(i);
					if(((FlightState)tempentry.getcurrentstate()).getflightstate().equals("���������(Running)")) {
						System.out.println("������ָ�����");
						yixie=scanner.nextLine();
						if(tempentry.finish(yixie)) {
						logger.log(Level.INFO,"true:finish,�ƻ���"+tempentry.getplanningentryname());
						state=((FlightState)tempentry.getcurrentstate()).move('a');
						tempentry.setcurrentstate(state);
						System.out.println("�ƻ��ǰ״̬���óɹ�");
						flightlist.set(i, tempentry);
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
						System.out.println("��ǰ״̬�²���ִ�н������\n");
					}
					break;
				case "7":
					System.out.println("����������Ҫȡ���ĺ���ƻ���ĺ����(eg AH3567)��");
					tempname=scanner.nextLine();
					for(i=0;i<flightlist.size();i++) {
						if(flightlist.get(i).getplanningentryname().equals(tempname)) {
							break;
						}
					}
					if(i==flightlist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					tempentry=flightlist.get(i);
					try {
						if(((FlightState)tempentry.getcurrentstate()).getflightstate().equals("����δ����ɻ�(Waiting)")
								||((FlightState)tempentry.getcurrentstate()).getflightstate().equals("�����ѷ���ɻ�(Allocated)")) {
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
							state=((FlightState)tempentry.getcurrentstate()).move('b');
							tempentry.setcurrentstate(state);
							System.out.println("�ƻ��ǰ״̬���óɹ�");
							flightlist.remove(i);
							System.out.println("\n");
							}
							else {
								System.out.println("\n");
								break;
							}
						}
						else {
							throw new NoCancelStateException();
						}
					}catch(NoCancelStateException e) {
						logger.log(Level.SEVERE,"false:cancel,NoCancelStateException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
						System.out.println("��������ɣ��޷�ȡ��\n");
						break;
					}
				case "8":
					System.out.println("����������Ҫ�鿴�ĺ���ƻ���ĺ����(eg AH3567)��");
					tempname=scanner.nextLine();
					for(i=0;i<flightlist.size();i++) {
						if(flightlist.get(i).getplanningentryname().equals(tempname)) {
							break;
						}
					}
					if(i==flightlist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ�������ѱ�ȡ��");
						System.out.println("\n");
						break;
					}
					tempentry=flightlist.get(i);
					System.out.println("�ú���ƻ����״̬Ϊ��"+((FlightState)tempentry.getcurrentstate()).getflightstate());
					logger.log(Level.INFO,"true:getcurrentstate,�ƻ���"+tempentry.getplanningentryname());
					System.out.println("\n");
					break;
				case "9":
					System.out.println("�����ṩ����ļ��ɹ���ȡ�� �ļ����ֱ�Ϊ��FlightSchedule_1.txt,FlightSchedule_2.txt,FlightSchedule_3.txt"
							+ "FlightSchedule_4.txt,FlightSchedule_5.txt");
					System.out.println("����������Ҫ��ȡ����ƻ�����ļ���(eg FlightSchedule_1.txt)��");
					tempname=scanner.nextLine();
					BufferedReader thisfile;
					try {
						thisfile = new BufferedReader(new FileReader("src/txt/"+tempname));
					} catch (FileNotFoundException e1) {
						logger.log(Level.SEVERE,"false:findfile,FileNotFoundException->operation again",e1);
						System.out.println("�ļ������ڣ������������ļ���\n");
						break;
					}
					i=0;
					int qq=0;
					fileentry="";
					allfileentry=new ArrayList<>();
					Parser tempparser=new Parser();
					tempflightlist=new ArrayList<>();
					boolean flag=true;
					try {
						while((fileoneline=thisfile.readLine())!=null) { //��������ÿʮ���кϳ�һ���ַ���
							if(fileoneline.equals("")) {  //��������
								continue;
							}
							else {
								fileentry=fileentry+fileoneline+"\n"; //�ǵü��ϻ��з�
								if(i==12) {
									allfileentry.add(fileentry);
									i=-1;
									fileentry="";
								}
								i++;
							}
						}
						if(i!=0) {
							throw new ComponentsNumberException();
						}
					} catch (IOException e1) {
						logger.log(Level.SEVERE,"false:readfile,IOException->operation again",e1);
						System.out.println("�ļ�������ִ���\n");
						break;
					} catch (ComponentsNumberException e) {
						logger.log(Level.SEVERE,"false:addfileentry,ComponentsNumberException->operation again",e);
						System.out.println("���ں���ƻ���Ԫ�ض���ķ�����Ŀ��������������ļ�\n");
						break;
					}
					try {
						thisfile.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					for(i=0;i<allfileentry.size();i++) {  //���μ��ÿ���ַ����Ƿ���ϸ�ʽҪ��
						try {
							tempparser.checkwhethercorrect(allfileentry.get(i));
						} catch (ArrayIndexOutOfBoundsException e) {
							i++;
							logger.log(Level.SEVERE,"false:addfileentry,ComponentsNumberException->operation again",e);
							System.out.println("��"+i+"������ƻ���ȱ�����ڻ򺽰�ţ�����������ļ�\n");
							flag=false;
							break;
						} catch (DateException e) {
							i++;
							logger.log(Level.SEVERE,"false:addfileentry,DateException->operation again",e);
							System.out.println("��"+i+"������ƻ�����������ǰ����ֻ������ڸ�ʽ������yyyy-MM-dd��Ҫ������������ļ�\n");
							flag=false;
							break;
						} catch (FlightNumberException e) {
							i++;
							logger.log(Level.SEVERE,"false:addfileentry,FlightNumberException->operation again",e);
							System.out.println("��"+i+"������ƻ����Ų�������λ��д��ĸ��2-4λ���ֹ��ɵ�Ҫ������������ļ�\n");
							flag=false;
							break;
						} catch (FromTimeException e) {
							i++;
							logger.log(Level.SEVERE,"false:addfileentry,FromTimeException->operation again",e);
							System.out.println("��"+i+"������ƻ������ʱ�䲻����yyyy-MM-dd HH:mm��Ҫ������������ļ�\n");
							flag=false;
							break;
						} catch (ToTimeException e) {
							i++;
							logger.log(Level.SEVERE,"false:addfileentry,ToTimeException->operation again",e);
							System.out.println("��"+i+"������ƻ���ִ�ʱ�䲻����yyyy-MM-dd HH:mm��Ҫ������������ļ�\n");
							flag=false;
							break;
						} catch (PlaneIdException e) {
							i++;
							logger.log(Level.SEVERE,"false:addfileentry,PlaneIdException->operation again",e);
							System.out.println("��"+i+"������ƻ���ɻ���Ų����ϵ�һλΪN��B����������λ���ֵ�Ҫ������������ļ�\n");
							flag=false;
							break;
						} catch (PlaneTypeException e) {
							i++;
							logger.log(Level.SEVERE,"false:addfileentry,PlaneTypeException->operation again",e);
							System.out.println("��"+i+"������ƻ�����Ͳ����ϴ�Сд��ĸ�����ֹ��ɣ������пո���������ŵ�Ҫ������������ļ�\n");
							flag=false;
							break;
						} catch (PlaneSeatsException e) {
							i++;
							logger.log(Level.SEVERE,"false:addfileentry,PlaneSeatsException->operation again",e);
							System.out.println("��"+i+"������ƻ�����λ���������������ҷ�ΧΪ[50,600]��Ҫ������������ļ�\n");
							flag=false;
							break;
						} catch (PlaneAgeException e) {
							i++;
							logger.log(Level.SEVERE,"false:addfileentry,PlaneAgeException->operation again",e);
							System.out.println("��"+i+"������ƻ�����䲻���Ϸ�ΧΪ[50,600]�����Ϊ1λС������С����Ҫ������������ļ�\n");
							flag=false;
							break;
						}		
					}
					if(flag==false) {
						break;
					}
					else {          //���ڷ���Ҫ����ļ������ζ��뺽��ƻ�������뺽��ƻ������
						for(i=0;i<allfileentry.size();i++) {
							fileentry=allfileentry.get(i);
							a=new TwoLocationEntryImpl();
							b=new OneDistinguishResourceEntryImpl<Flight>();
							c=new NoBlockableEntryImpl();
							originflight=new FlightEntryFactory().getFlightEntry(a, b, c); //�ù���������������
							flight=(FlightEntry<Flight>)originflight;
							
							temp=tempparser.getAllinformation("Flight:", fileentry).split(",");  
							
							Calendar readytime= Calendar.getInstance(); 
							try {
								readytime.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(temp[0]));
								tempname=temp[1];  //�õ������
								flight.setplanningentryname(tempname);
								
								from=new FlightTrainLocation(weidu,jingdu,tempparser.getAllinformation("DepartureAirport:",fileentry)); //�õ����
								to=new FlightTrainLocation(weidu,jingdu,tempparser.getAllinformation("ArrivalAirport:",fileentry));  //�õ��յ�
								try {
									flight.setlocations(from, to);
									logger.log(Level.INFO,"true:setlocation,�ƻ���"+tempname);
								} catch (SameLocationException e2) {
									logger.log(Level.SEVERE,"false:setlocation,SameLocationException->operation again,�ƻ���"+tempname,e2);
									System.out.println("����������ͬ�ɻ���λ��");
									flag=false;
									break;
								}
								
								Calendar begintime= Calendar.getInstance(); 
								begintime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(tempparser.getAllinformation("DepatureTime:",fileentry))); //���ʱ��
								Calendar endtime= Calendar.getInstance(); 
								endtime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(tempparser.getAllinformation("ArrivalTime:",fileentry)));  //�ִ�ʱ��
								
								Timeslot mytime;
								try {
									mytime = new Timeslot(tempparser.getAllinformation("DepatureTime:",fileentry),tempparser.getAllinformation("ArrivalTime:",fileentry));
									flight.settimeslot(mytime);
									logger.log(Level.INFO,"true:settimeslot,�ƻ���"+tempname);
								} catch (BeginEndTimeException e1) {
									logger.log(Level.SEVERE,"false:settimeslot,BeginEndTimeException->operation again,�ƻ���"+tempname,e1);
									System.out.println("��ʼʱ��������ֹʱ�䣬����������ļ�\n");
									flag=false;
									break;
								}  //�õ�ʱ���
								String number=tempparser.getAllinformation("Plane:",fileentry);  //�õ��ɻ��ĸ�����Ϣ
								String type=tempparser.getAllinformation("Type:",fileentry);
								int seats= Integer.parseInt(tempparser.getAllinformation("Seats:",fileentry));
								Double age=Double.parseDouble(tempparser.getAllinformation("Age:",fileentry));
								
								myflight=new Flight(number,type,seats,age);
								flight.setresource(myflight);
								
								state = FlightWaitingState.instance;
								state=state.move('a');
								flight.setcurrentstate(state);
								
								//��ȥ���зǷ����
								try {
									if(!((begintime.get(Calendar.YEAR)==(readytime.get(Calendar.YEAR))) 
											&&(begintime.get(Calendar.MONTH)==readytime.get(Calendar.MONTH))&&
											(begintime.get(Calendar.DAY_OF_MONTH)==readytime.get(Calendar.DAY_OF_MONTH)))) {
										throw new OneLineDiferDateException();
									}
								}catch(OneLineDiferDateException e) {
									logger.log(Level.SEVERE,"false:addfileentry,OneLineDiferDateException->operation again,�ƻ���"+tempname,e);
									System.out.println("����ƻ���"+tempname+"����������һ�����ڲ�һ��");
									flag=false;
								}	
								try {
									if((endtime.get(Calendar.YEAR)!=begintime.get(Calendar.YEAR))||
											(endtime.get(Calendar.MONTH)!=begintime.get(Calendar.MONTH))||
									       (endtime.get(Calendar.DAY_OF_MONTH)-begintime.get(Calendar.DAY_OF_MONTH)>1)){
										throw new MoreOneDayException();
									}
								}catch(MoreOneDayException e) {
									logger.log(Level.SEVERE,"false:addfileentry,MoreOneDayException->operation again,�ƻ���"+tempname,e);
									System.out.println("����ƻ���"+tempname+"���������������������һ��");
									flag=false;
								}
								for(int m=0;m<tempflightlist.size();m++) {
									FlightEntry<Flight> tempflight=tempflightlist.get(m);
									try {
										if(((changeformat(tempflight.getplanningentryname()).equals(changeformat(flight.getplanningentryname()))))){
											if((begintime.get(Calendar.YEAR)==(tempflight.gettimeslot().getbegintime().get(Calendar.YEAR)))
													&&(begintime.get(Calendar.MONTH)==tempflight.gettimeslot().getbegintime().get(Calendar.MONTH))
													&&(begintime.get(Calendar.DAY_OF_MONTH)==tempflight.gettimeslot().getbegintime().get(Calendar.DAY_OF_MONTH))) {
												throw new SameDateFlightNumberException();
											}
											else if(!(tempflight.getfromlocation().getlocationname().equals(from.getlocationname())&&
													tempflight.gettolocation().getlocationname().equals(to.getlocationname())&&
													tempflight.gettimeslot().getbegintime().get(Calendar.HOUR_OF_DAY)==begintime.get(Calendar.HOUR_OF_DAY)&&
													tempflight.gettimeslot().getendtime().get(Calendar.HOUR_OF_DAY)==endtime.get(Calendar.HOUR_OF_DAY)&&
													tempflight.gettimeslot().getbegintime().get(Calendar.MINUTE)==begintime.get(Calendar.MINUTE)&&
													tempflight.gettimeslot().getendtime().get(Calendar.MINUTE)==endtime.get(Calendar.MINUTE))) {
												throw new DifferAirpotFromToTimeException();
											}
										}
										if(tempflight.getresource().getflightnumber().equals(flight.getresource().getflightnumber())) {
											if((!(tempflight.getresource().getflightage()-flight.getresource().getflightage()<0.0000001))||
													(!(tempflight.getresource().getflightallseat()==flight.getresource().getflightallseat()))||
													(!(tempflight.getresource().getflighttype().equals(flight.getresource().getflighttype())))) {
												throw new NoCompleteSamePlane();
											}
										}
									}catch(SameDateFlightNumberException e) {
										i++;
										logger.log(Level.SEVERE,"false:addfileentry,SameDateFlightNumberException->operation again",e);
										System.out.println("��"+i+"������ƻ���"+tempname+"���Ѵ����б���ĳ�ƻ����ź�������ͬ");
										flag=false;
										break;
									}catch(DifferAirpotFromToTimeException e) {
										i++;
										logger.log(Level.SEVERE,"false:addfileentry,DifferAirpotFromToTimeException->operation again",e);
										System.out.println("��"+i+"������ƻ���"+tempname+"���Ѵ����б���ĳ�ƻ�������ͬ������������"
												+ "�ͽ�������������͵���ʱ���г����˲�ͬ");
										flag=false;
										break;
									} catch (NoCompleteSamePlane e) {
										i++;
										logger.log(Level.SEVERE,"false:addfileentry,NoCompleteSamePlane->operation again",e);
										System.out.println("��"+i+"������ƻ���"+tempname+"���Ѵ����б���ĳ�ƻ���ɻ������ͬ�����ɻ������͡�"
												+ "��λ���������г����˲�ͬ");
										flag=false;
										break;
									}
								}
								if(flag==true) {
									tempflightlist.add(flight);
									qq++;
								}
								if(flag==false) {
									System.out.println("����������ļ�\n");
									break;
								}
							} catch (ParseException e) {
								logger.log(Level.SEVERE,"ParseException->operation again",e);
								System.out.println("ʱ���ʽ��������������ļ�\n");
								break;
							} catch (LessThanZeroException e1) {
								System.out.println("��λ��Ϊ�����������Ϊ����������������ļ�\n");
							} 
						}
						//�����Դ��ͻ�Ĵ���
						try {
							if(myapis.checkResourceExclusiveConflict(tempflightlist)) {
								throw new ResourceExclusiveConflictException();
							}
						}catch(ResourceExclusiveConflictException e) {
							logger.log(Level.SEVERE,"ResourceExclusiveConflictException->operation again",e);
							System.out.println("������Դ��ռ��ͻ�����ȡ�����ļ�\n");
							flag=false;
						}
						if(flag==false) {
							logger.log(Level.INFO,"false:addfileentry");
							tempflightlist=new ArrayList<FlightEntry<Flight>>();
						}
						if(flag==true) {
							logger.log(Level.INFO,"true:addfileentry");
							flightlist.addAll(tempflightlist);
							System.out.println("������"+qq+"���ƻ�������ļ��мƻ�����¼��ɻ��ƻ������\n");
						}
					}
					break;
				case "10":
					System.out.println("����λ�ö�ռ��ͻ�������ѡ�������㷨�������ж�(����1ʹ�õ�һ�֣�����2ʹ�õڶ���)����ѡ������(eg 1)");
					tempname=scanner.nextLine();
					System.out.println("���мƻ�����λ�ö�ռ��ͻ������£�");
					if(myapis.checkLocationConflict(flightlist,tempname)) {
						logger.log(Level.INFO,"true:LocationConflict");
					}
					else {
						logger.log(Level.INFO,"false:LocationConflict");
					}
					System.out.println("���мƻ�������Դ��ռ��ͻ������£�");
					if(myapis.checkResourceExclusiveConflict(flightlist)) {
						logger.log(Level.INFO,"true:ResourceExclusiveConflict");
					}
					else {
						logger.log(Level.INFO,"false:ResourceExclusiveConflict");
					}
					System.out.println("\n");
					break;
				case "11":
					System.out.println("����������Ҫ�鿴�ķɻ���Դ�ķɻ����(eg N8981)��");
					tempname=scanner.nextLine();
					for(i=0;i<flightlist.size();i++) {
						if(flightlist.get(i).getresource().getflightnumber().equals(tempname)) {
							break;
						}
					}
					if(i==flightlist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���ӵ�и���Դ");
						System.out.println();
						break;
					}
					tempentry=flightlist.get(i);
					myapis.findEntryPerResource(tempentry.getresource(), flightlist);
					logger.log(Level.INFO,"findEntryPerResource");
					System.out.println("\n");
					break;
				case "12":
					System.out.println("����������Ҫ�鿴��ǰ��ƻ���ĺ����(eg AH3567)��");
					tempname=scanner.nextLine();
					for(i=0;i<flightlist.size();i++) {
						if(flightlist.get(i).getplanningentryname().equals(tempname)) {
							break;
						}
					}
					if(i==flightlist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println();
						break;
					}
					tempentry=flightlist.get(i);
					myapis.findPreEntryPerResource(tempentry.getresource(),tempentry,flightlist);
					logger.log(Level.INFO,"findPreEntryPerResource");
					System.out.println("\n");
					break;
				case "13":
					System.out.println("����������Ҫչʾ��ǰʱ�̵���Ϣ���λ��(eg ����)");
					tempname=scanner.nextLine();
					FlightEntryBoard flightboard=new FlightEntryBoard();	
					flightboard.setairportlocation(tempname);
					flightboard.getsortcomeentry(flightlist);
					flightboard.getsorttoentry(flightlist);
					try {
						flightboard.createFlightEntryBoard();
						logger.log(Level.INFO,"true:FlightEntryBoard");
					} catch (ParseException e) {
						logger.log(Level.SEVERE,"false:FlightEntryBoard,ParseException->operation again",e);
						System.out.println("ʱ���ʽ����");
						break;
					}
					flightboard.visualize();	
					System.out.println("\n");
					break;
				case "14":
					logger.log(Level.INFO,"true:get the number of allentrys");
					System.out.println("��ǰ���к���ƻ���ĸ���Ϊ��"+flightlist.size()+"\n");
					break;
				case "15":
					fileHandler.close();
					try {
						thisfile = new BufferedReader(new FileReader("log/FlightScheduleLog.txt"));
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
					int ll;
					System.out.println("�����ѡ�����ֹ�����������һ��Ϊ���������Ͳ�ѯ���ڶ���Ϊ���ƻ������ֲ�ѯ��������Ϊ����ʱ��β�ѯ(����1ʹ�õ�һ�֣�����2ʹ�õڶ��֣�����3ʹ�õ�����)����ѡ������(eg 1)");
					tempname=scanner.nextLine();
					if(tempname.equals("1")) {
						System.out.println("һ�������²������ͣ�����ѡһ�ֽ��в�ѯ��(eg setlocation)");
						System.out.println("setplanningentryname,setlocation,settimeslot,setresource,changelocation,changeresource,launch,finish"
								+ ",cancel,getcurrentstate,addfileentry,LocationConflict,ResourceExclusiveConflict,findEntryPerResource,findPreEntryPerResource,"
								+ "FlightEntryBoard,get the number of allentrys");
						mychoice=scanner.nextLine();
						ll=1;
						System.out.println("�ò�����������־Ϊ��");
						for(String h:alllog) {
							if(h.contains(mychoice)) {
								 System.out.print("��־"+ll+"Ϊ��"+h+"\n");
								 ll++;
							}
						}
						if(ll==1) {
							System.out.print("û�иò�������־\n");
						}
					}
					if(tempname.equals("2")) {
						System.out.println("������ƻ������֣�(eg AH3567)");
						mychoice=scanner.nextLine();
						System.out.println("��üƻ����йص�������־Ϊ��");
						ll=1;
						for(String h:alllog) {
							if(h.contains(mychoice)) {
								 System.out.print("��־"+ll+"Ϊ��"+h+"\n");
								 ll++;
							}
						}
						if(ll==1) {
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
							ll=1;
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
										System.out.print("��־"+ll+"Ϊ��"+h+"\n");
										ll++;
									}
									
								}
							}
							if(ll==1) {
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
				case "16":
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
		} catch (SecurityException e3) {
			// TODO �Զ����ɵ� catch ��
			e3.printStackTrace();
		} catch (IOException e3) {
			// TODO �Զ����ɵ� catch ��
			e3.printStackTrace();
		}
	}
	
	/**
	 * ������Ž���ת�����ڱȽ�
	 * @param a ��ת�������
	 * @return ת����ĺ����
	 */
	public static String changeformat(String a) {  //ת������Ž����ж�
		if(a.length()==6) {
			return a;
		}
		else if(a.length()==4) {
			return a.substring(0, 2)+"00"+a.substring(2);	
		}
		else{
			return a.substring(0, 2)+"0"+a.substring(2);
		}
	}
}
