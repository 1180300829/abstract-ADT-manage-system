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

import Board.CourseEntryBoard;
import EntryState.*;
import Exception.*;
import Factory.CourseEntryFactory;
import Location.CourseLocation;
import PlanningEntry.*;

import PlanningEntryAPIs.PlanningEntryAPIs;
import Resource.*;
import Timeslot.Timeslot;

public class CourseCalendarApp {

    private static List<CourseEntry<Teacher>> courselist=new ArrayList<>();  //��������CourseEntry�ļ���
    private final static Logger logger = Logger.getLogger(CourseCalendarApp.class.getName());
   	
    /**
     * �˵�
     */
	public static void menu() {
		System.out.println("1.����һ���µĿγ̼ƻ��������Ϣ(Ϊ�˷�������Ϣ���⣬����ʱ��ʱ������Ϊ�����ʱ��)");
		System.out.println("2.Ϊĳ���γ̼ƻ��������Դ");
		System.out.println("3.���(ɾ��λ�ú���������)ĳ���γ̼ƻ����λ��");
		System.out.println("4.ɾ��ĳ���γ̼ƻ����λ��(ɾ��λ�ú������������λ��Ȼ��ִ������Ĳ���)");	
		System.out.println("5.���ĳ���γ̼ƻ������Դ");
		System.out.println("6.����ĳ���γ̼ƻ���");
		System.out.println("7.����ĳ���γ̼ƻ���");
		System.out.println("8.ȡ��ĳ���γ̼ƻ���");
		System.out.println("9.�鿴ĳ���γ̼ƻ����״̬");
		System.out.println("****(ע�⣺ִ��10,11,12�Ĳ�����ҪΪÿһ���ƻ��������Դ��ִ��,�����쳣)****");
		System.out.println("10.���γ̼ƻ������Ƿ����λ�ú���Դ��ռ��ͻ");
		System.out.println("11.���ĳ����ʦ��Դ���г�����ʹ�ø���Դ�Ŀγ̼ƻ���");
		System.out.println("12.���ĳ����ʦ��Դ��ѡ�к��и���Դ��ĳ���γ̼ƻ���г�����ǰ��ƻ���(���һ������)");
		System.out.println("13.ѡ��ĳ��λ�ã�չʾ��ǰʱ�̸�λ�õ���Ϣ��");
		System.out.println("14.��ʾ��ǰ���пγ̼ƻ���ĸ���");
		System.out.println("15.���չ�������������־��ѯ");
		System.out.println("16.д����־���ļ�����������");
	}
	
	/**
	 * 
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args)  {
		OneLocationEntryImpl a;
		OneDistinguishResourceEntryImpl<Teacher> b;
		NoBlockableEntryImpl c;
		String choice,weidu="��γ40��",jingdu="����112��";
		Scanner scanner=new Scanner(System.in);
		CourseState state;
		Teacher myteacher;
		Calendar canceltimeone;
		PlanningEntryAPIs myapis=new PlanningEntryAPIs<>();
		System.out.println("�ʼ��ִ�е�һ��");
		
		File file=new File("log/CourseCalendarLog.txt");
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
				fileHandler = new FileHandler("log/CourseCalendarLog.txt", true);
				fileHandler.setFormatter(new SimpleFormatter());
				logger.addHandler(fileHandler);	
				logger.setUseParentHandlers(false);
				System.out.println("��������Ĳ�����");
				choice=scanner.nextLine();
				switch(choice) {
				case "1":
					a=new OneLocationEntryImpl();
					b=new OneDistinguishResourceEntryImpl<Teacher>();
					c=new NoBlockableEntryImpl();
					PlanningEntry origincourse=new CourseEntryFactory().getCourseEntry(a, b, c); //�ù���������������
					CourseEntry<Teacher> course=(CourseEntry<Teacher>)origincourse;
					System.out.println("�˿γ̼ƻ����Ѿ��������,��������һЩ��Ϣ��ɳ�ʼ״̬�Ľ�����");
					System.out.println("������γ���(eg �������)��");
					String coursename=scanner.nextLine();
					course.setplanningentryname(coursename);
					logger.log(Level.INFO,"true:setplanningentryname");
					System.out.println("�ƻ����������óɹ�");
					System.out.println("�������������(eg ����¥32)��");
					String tempname;
					tempname=scanner.nextLine();
					CourseLocation from=new CourseLocation(weidu,jingdu,tempname);
					if(course.setlocations(from)) {
						logger.log(Level.INFO,"true:setlocation,�ƻ���"+coursename);
					}
					else {
						logger.log(Level.INFO,"false:setlocation,�ƻ���"+coursename);
					}
					System.out.println("������γ̵��Ͽκ��¿�ʱ��(�ö��Ÿ���)(eg 2020-01-01 15:45,2020-01-01 17:30)��");
					try {
						temp=(scanner.nextLine()).split(",");
						Timeslot mytime=new Timeslot(temp[0],temp[1]);
						if(course.settimeslot(mytime)) {
							logger.log(Level.INFO,"true:settimeslot,�ƻ���"+coursename);
						}
					}catch(ArrayIndexOutOfBoundsException e) {
						 logger.log(Level.SEVERE,"false:settimeslot,ArrayIndexOutOfBoundsException->operation again,�ƻ���"+coursename,e);
						 System.out.println("δ��,������������ִ��1����������ȷ��ʽ����\n");
						 break;
					} catch (ParseException e) {
						logger.log(Level.SEVERE,"false:settimeslot,ParseException->operation again,�ƻ���"+coursename,e);
						System.out.println("ʱ�䲻����yyyy-MM-dd HH:mm��Ҫ��������ִ��1����������ȷ��ʽ����\n");
						break;
					} catch (BeginEndTimeException e) {
						logger.log(Level.SEVERE,"false:settimeslot,BeginEndTimeException->operation again,�ƻ���"+coursename,e);
						System.out.println("��ʼʱ��������ֹʱ�䣬������ִ��1����������ȷ��ʽ����\n");
						break;
					}
					state = CourseWaitingState.instance;
					course.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					List<CourseEntry<Teacher>> tempcourselist=new ArrayList<>();
					for(int oo=0;oo<courselist.size();oo++) {
						tempcourselist.add(courselist.get(oo).clone());
					}
					tempcourselist.add(course.clone());
					try {
						if(myapis.checkLocationConflict(tempcourselist,"1")) {
							throw new LocationConflictException();
						}
					}catch(LocationConflictException e) {
						logger.log(Level.SEVERE,"false:setlocation,LocationConflictException->operation again,�ƻ���"+coursename,e);
						System.out.println("��λ�÷����������е������ƻ����λ�ö�ռ��ͻ��������ִ��1����\n");
						break;
					}
					System.out.println("��Ϣ�������");
					courselist.add(course);
					System.out.println("\n");
					break;
				case "2":
					System.out.println("���������������Դ�ĵĿγ���(eg �������)��");
					tempname=scanner.nextLine();
					int i;
					for(i=0;i<courselist.size();i++) {
						if(courselist.get(i).getplanningentryname().equals(tempname) ){
							break;
						}
					}
					if(i==courselist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println();
						break;
					}
					tempcourselist=new ArrayList<>();
					for(int oo=0;oo<courselist.size();oo++) {
						tempcourselist.add(courselist.get(oo).clone());
					}
					CourseEntry<Teacher> tempentry=tempcourselist.get(i).clone();
					if(((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ�δ������ʦ(Waiting)")) {
						System.out.println("�������������ʦ�����֤�ţ��������Ա�ְ��(eg 422823199812254452,����,��,��ʦ)��");
						try {
							temp=(scanner.nextLine()).split(",");
							myteacher=new Teacher(temp[0],temp[1],temp[2],temp[3]);
							tempentry.setresource(myteacher);
						}catch(ArrayIndexOutOfBoundsException e) {
							 logger.log(Level.SEVERE,"false:setresource,ArrayIndexOutOfBoundsException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
							 System.out.println("δ��,������������ִ��2����������ȷ��ʽ����\n");
							 break;
						}	
						state=((CourseState)tempentry.getcurrentstate()).move('a');
						tempentry.setcurrentstate(state);
						System.out.println("�ƻ��ǰ״̬���óɹ�");
						tempcourselist.set(i, tempentry);
						try {
							if(myapis.checkResourceExclusiveConflict(tempcourselist)) {
								throw new ResourceExclusiveConflictException();
							}
							else {
								courselist.set(i, tempentry);
								logger.log(Level.INFO,"true:setresource,�ƻ���"+tempentry.getplanningentryname());
								System.out.println("��ʦ��Դ�������\n");
							}
						}catch(ResourceExclusiveConflictException e) {
							logger.log(Level.SEVERE,"false:setresource,ResourceExclusiveConflictException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
							System.out.println("����Դ�����������е������ƻ������Դ��ռ��ͻ�������·�����Դ\n");
						}
					}
					else {
						logger.log(Level.INFO,"false:no correct state->operation again,�ƻ���"+tempentry.getplanningentryname());
						System.out.println("��ǰ״̬�²���ִ��������ʦ��Դ����\n");
					}
					break;
				case "3":
					System.out.println("������������(��������)λ�õĿγ�����(eg �������)��");
					tempname=scanner.nextLine();
					for(i=0;i<courselist.size();i++) {
						if(courselist.get(i).getplanningentryname().equals(tempname) ){
							break;
						}
					}
					if(i==courselist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					tempcourselist=new ArrayList<>();
					for(int oo=0;oo<courselist.size();oo++) {
						tempcourselist.add(courselist.get(oo).clone());
					}
	                tempentry=tempcourselist.get(i).clone();
					if(((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ�δ������ʦ(Waiting)")
							||((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ��ѷ�����ʦ(Allocated)")) {
						System.out.println("����������(�������õ�)λ�ý��ҵ�����(eg ��֪¥15)��");
						tempname=scanner.nextLine();
						from=new CourseLocation(weidu,jingdu,tempname);
						tempentry.changelocations(from);
						tempcourselist.set(i, tempentry);
						try {
							if(myapis.checkLocationConflict(tempcourselist,"1")) {
								throw new LocationConflictException();
							}
							else {
								logger.log(Level.INFO,"true:changelocation,�ƻ���"+tempentry.getplanningentryname());
								courselist.set(i, tempentry);
								System.out.println("\n");
							}
						}catch(LocationConflictException e) {
							logger.log(Level.SEVERE,"false:changelocations,LocationConflictException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
							System.out.println("��λ�ñ����������е������ƻ����λ�ö�ռ��ͻ�������·���λ��\n");
						}
					}
					else {
						logger.log(Level.INFO,"false:no correct state->operation again,�ƻ���"+tempentry.getplanningentryname());
						System.out.println("��ǰ״̬�²���ִ�б��(��������)λ�ò���\n");
					}
					break;
				case "4":
					System.out.println("����������ɾ��λ�õĿγ�����(eg �������)��");
					tempname=scanner.nextLine();
					for(i=0;i<courselist.size();i++) {
						if(courselist.get(i).getplanningentryname().equals(tempname) ){
							break;
						}
					}
					if(i==courselist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					tempentry=courselist.get(i);
					if(((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ�δ������ʦ(Waiting)")
							||((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ��ѷ�����ʦ(Allocated)")) {
						System.out.println("����������ɾ��λ�ý��ҵ�����(eg ��֪¥15)��");
						tempname=scanner.nextLine();
						from=new CourseLocation(weidu,jingdu,tempname);
						try {
							for(int kk=0;kk<courselist.size();kk++) {
								if(courselist.get(kk).getlocations().equals(from)&&
										!((CourseState)courselist.get(kk).getcurrentstate()).getcoursestate().equals("�γ����¿�(Ended)")) {
									throw new NoendedLocationException();
								}
							}
						}catch(NoendedLocationException e) {
							logger.log(Level.SEVERE,"false:deletelocation,NoendedLocationException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
							System.out.println("����δ�����ļƻ����ڸ�λ��ִ�У��޷�ɾ����λ��\n");
							break;
						}
						if(tempentry.deletelocations(from)) {
							logger.log(Level.INFO,"true:deletelocation,�ƻ���"+tempentry.getplanningentryname());
						}
						else {
							logger.log(Level.INFO,"false:deletelocation,�ƻ���"+tempentry.getplanningentryname());
							break;
						}
						courselist.set(i, tempentry);
						System.out.println("\n");
					}
					else {
						logger.log(Level.INFO,"false:no correct state->operation again,�ƻ���"+tempentry.getplanningentryname());
						System.out.println("��ǰ״̬�²���ִ��ɾ��λ�ò���\n");
					}
					break;
				case "5":
					System.out.println("��������������Դ�Ŀγ�����(eg �������)��");
					tempname=scanner.nextLine();
					for(i=0;i<courselist.size();i++) {
						if(courselist.get(i).getplanningentryname().equals(tempname)) {
							break;
						}
					}
					if(i==courselist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println();
						break;
					}
					tempentry=courselist.get(i);
					if(((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ��ѷ�����ʦ(Allocated)")) {
						System.out.println("����������ʦ�����֤�ţ��������Ա�ְ��(eg 422823199812257777,����,��,��ʦ)��");
						try {
							temp=(scanner.nextLine()).split(",");
							myteacher=new Teacher(temp[0],temp[1],temp[2],temp[3]);
							if(tempentry.changeresource(myteacher)) {
								logger.log(Level.INFO,"true:changeresource,�ƻ���"+tempentry.getplanningentryname());
							}
							else {
								logger.log(Level.INFO,"false:changeresource,�ƻ���"+tempentry.getplanningentryname());
								break;
							}
						}catch(ArrayIndexOutOfBoundsException e) {
							 logger.log(Level.SEVERE,"false:changeresource,ArrayIndexOutOfBoundsException->operation again,�ƻ���"+tempentry.getplanningentryname(),e);
							 System.out.println("δ��,������������ִ��5����������ȷ��ʽ����\n");
							 //e.printStackTrace();
							 break;
						}	
						courselist.set(i, tempentry);
						System.out.println("\n");
					}
					else {
						logger.log(Level.INFO,"false:no correct state->operation again,�ƻ���"+tempentry.getplanningentryname());
						System.out.println("��ǰ״̬�²���ִ�б����Դ����\n");
					}
					break;
				case "6":
					System.out.println("����������Ҫ�����Ŀγ̼ƻ���Ŀγ�����(eg �������)��");
					tempname=scanner.nextLine();
					for(i=0;i<courselist.size();i++) {
						if(courselist.get(i).getplanningentryname().equals(tempname)) {
							break;
						}
					}
					if(i==courselist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					tempentry=courselist.get(i);
					if(((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ��ѷ�����ʦ(Allocated)")) {	
						System.out.println("������ָ�����");
						yixie=scanner.nextLine();
						if(tempentry.launch(yixie)) {
						logger.log(Level.INFO,"true:launch,�ƻ���"+tempentry.getplanningentryname());
						state=((CourseState)tempentry.getcurrentstate()).move('a');
						tempentry.setcurrentstate(state);
						System.out.println("�ƻ��ǰ״̬���óɹ�");
						courselist.set(i, tempentry);
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
						System.out.println("��ǰ״̬�²���ִ���Ͽβ���\n");
					}
					break;
				case "7":
					System.out.println("����������Ҫ�����Ŀγ̼ƻ���Ŀγ�����(eg �������)��");
					tempname=scanner.nextLine();
					for(i=0;i<courselist.size();i++) {
						if(courselist.get(i).getplanningentryname().equals(tempname)) {
							break;
						}
					}
					if(i==courselist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					tempentry=courselist.get(i);
					if(((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ��ѿ�ʼ�Ͽ�(Running)")) {
						System.out.println("������ָ�����");
						yixie=scanner.nextLine();
						if(tempentry.finish(yixie)) {
						logger.log(Level.INFO,"true:finish,�ƻ���"+tempentry.getplanningentryname());
						state=((CourseState)tempentry.getcurrentstate()).move('a');
						tempentry.setcurrentstate(state);
						System.out.println("�ƻ��ǰ״̬���óɹ�");
						courselist.set(i, tempentry);
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
						System.out.println("��ǰ״̬�²���ִ���¿β���\n");
					}
					break;
				case "8":
					System.out.println("����������Ҫȡ���Ŀγ̼ƻ���Ŀγ�����(eg �������)��");
					tempname=scanner.nextLine();
					for(i=0;i<courselist.size();i++) {
						if(courselist.get(i).getplanningentryname().equals(tempname)) {
							break;
						}
					}
					if(i==courselist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					tempentry=courselist.get(i);
					try {
						if(((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ�δ������ʦ(Waiting)")
								||((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ��ѷ�����ʦ(Allocated)")) {
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
							System.out.println("�ÿγ̼ƻ���ȡ����ʱ��Ϊ"+str);
							state=((CourseState)tempentry.getcurrentstate()).move('b');
							tempentry.setcurrentstate(state);
							System.out.println("�ƻ��ǰ״̬���óɹ�");
							courselist.remove(i);
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
						System.out.println("�γ����ϿΣ��޷�ȡ��\n");
						break;
					}

				case "9":
					System.out.println("����������Ҫ�鿴�Ŀγ̼ƻ���Ŀγ�����(eg �������)��");
					tempname=scanner.nextLine();
					for(i=0;i<courselist.size();i++) {
						if(courselist.get(i).getplanningentryname().equals(tempname)) {
							break;
						}
					}
					if(i==courselist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ�������ѱ�ȡ��");
						System.out.println();
						break;
					}
					tempentry=courselist.get(i);
					System.out.println("�ÿγ̼ƻ����״̬Ϊ��"+((CourseState)tempentry.getcurrentstate()).getcoursestate());
					logger.log(Level.INFO,"true:getcurrentstate,�ƻ���"+tempentry.getplanningentryname());
					System.out.println("\n");
					break;
				case "10":
					System.out.println("����λ�ö�ռ��ͻ�������ѡ�������㷨�������ж�(����1ʹ�õ�һ�֣�����2ʹ�õڶ���)����ѡ������(eg 1)");
					tempname=scanner.nextLine();
					System.out.println("���мƻ�����λ�ö�ռ��ͻ������£�");
					if(myapis.checkLocationConflict(courselist,tempname)) {
						logger.log(Level.INFO,"true:LocationConflict");
					}
					else {
						logger.log(Level.INFO,"false:LocationConflict");
					}
					System.out.println("���мƻ�������Դ��ռ��ͻ������£�");
					if(myapis.checkResourceExclusiveConflict(courselist)) {
						logger.log(Level.INFO,"true:ResourceExclusiveConflict");
					}
					else {
						logger.log(Level.INFO,"false:ResourceExclusiveConflict");
					}
					System.out.println("\n");
					break;
				case "11":
					System.out.println("����������Ҫ�鿴����ʦ��Դ������(eg ����)��");
					tempname=scanner.nextLine();
					for(i=0;i<courselist.size();i++) {
						if(courselist.get(i).getresource().getteachername().equals(tempname)) {
							break;
						}
					}
					if(i==courselist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���ӵ�и���Դ");
						System.out.println("\n");
						break;
					}
					tempentry=courselist.get(i);
					myapis.findEntryPerResource(tempentry.getresource(), courselist);
					logger.log(Level.INFO,"findEntryPerResource");
					System.out.println("\n");
					break;
				case "12":
					System.out.println("����������Ҫ�鿴��ǰ��ƻ���Ŀγ�����(eg �������)��");
					tempname=scanner.nextLine();
					for(i=0;i<courselist.size();i++) {
						if(courselist.get(i).getplanningentryname().equals(tempname)) {
							break;
						}
					}
					if(i==courselist.size()) {
						logger.log(Level.INFO,"false:can not find this entry->operation again");
						System.out.println("û�иüƻ���");
						System.out.println("\n");
						break;
					}
					tempentry=courselist.get(i);
					myapis.findPreEntryPerResource(tempentry.getresource(),tempentry,courselist);
					logger.log(Level.INFO,"findPreEntryPerResource");
					System.out.println("\n");
					break;
				case "13":
					System.out.println("����������Ҫչʾ��ǰʱ�̵���Ϣ���λ��(eg ����¥32)");
					tempname=scanner.nextLine();
					CourseEntryBoard flightboard=new CourseEntryBoard();
					flightboard.setclassroomlocation(tempname);
					flightboard.getsortallentry(courselist);
					try {
						flightboard.createCourseEntryBoard();
						logger.log(Level.INFO,"true:CourseEntryBoard");
					} catch (ParseException e) {
						logger.log(Level.SEVERE,"false:CourseEntryBoard,ParseException->operation again",e);
						System.out.println("ʱ���ʽ����");
						break;
					}
					flightboard.visualize();
					System.out.println("\n");
					break;
				case "14":
					logger.log(Level.INFO,"true:get the number of allentrys");
					 System.out.println("��ǰ���пγ̼ƻ���ĸ���Ϊ��"+courselist.size()+"\n");
		 			break;
				case "15":
					fileHandler.close();
					BufferedReader thisfile;
					String fileoneline;
					try {
						thisfile = new BufferedReader(new FileReader("log/CourseCalendarLog.txt"));
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
						System.out.println("setplanningentryname,setlocation,settimeslot,setresource,changelocation,deletelocation,changeresource,launch,finish"
								+ ",cancel,getcurrentstate,LocationConflict,ResourceExclusiveConflict,findEntryPerResource,findPreEntryPerResource,"
								+ "CourseEntryBoard,get the number of allentrys");
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
						System.out.println("������ƻ������֣�(eg �������)");
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
			
		}catch (SecurityException e3) {
			// TODO �Զ����ɵ� catch ��
			e3.printStackTrace();
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		} 		
	}
}
