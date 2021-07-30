package Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Exception.*;

public class Parser {

	/**
	 * ���һ���ļ��ж���ĺ����ַ�����ʽ�Ƿ���ϱ���Ҫ��
	 * @param S ���жϵ��ַ���
	 * @return ����Ҫ�󷵻�true�������Ϸ���false
	 * @throws ArrayIndexOutOfBoundsException ��ȱ�����ڻ򺽰���׳�ArrayIndexOutOfBoundsException�쳣
	 * @throws DateException �����������ǰ����ֻ������ڸ�ʽ������yyyy-MM-dd 
	 * @throws FlightNumberException ����Ų�������λ��д��ĸ��2-4λ���ֹ���
	 * @throws FromTimeException ����ʱ�䲻����yyyy-MM-dd HH:mm
	 * @throws ToTimeException �ִ�ʱ�䲻����yyyy-MM-dd HH:mm
	 * @throws PlaneIdException ����Ų����ϵ�һλΪN��B����������λ����
	 * @throws PlaneTypeException ���Ͳ����ϴ�Сд��ĸ�����ֹ��ɣ������пո����������
	 * @throws PlaneSeatsException ��λ���������������ҷ�ΧΪ[50,600]
	 * @throws PlaneAgeException ���䲻���Ϸ�ΧΪ[5,30]�����Ϊ1λС������С��
	 */
	 public void checkwhethercorrect(String S)throws ArrayIndexOutOfBoundsException, DateException, FlightNumberException, FromTimeException, ToTimeException, PlaneIdException, PlaneTypeException, PlaneSeatsException, PlaneAgeException{
		
		 String[] all=S.split("\n");
		 String[] temp=all[0].split(",");  //��ȱ�����ڻ򺽰���׳�ArrayIndexOutOfBoundsException�쳣
		 Pattern pattern00=Pattern.compile("(Flight):(20[012][0-9]-[01][0-9]-[0123][0-9])");  
		 Pattern pattern01=Pattern.compile("([A-Z][A-Z]((\\d{2})|(\\d{3})|(\\d{4})))");
		 Pattern pattern4=Pattern.compile("(DepatureTime):(20[012][0-9]-[01][0-9]-[0123][0-9])(\\s[012][0-9]:[0-6][0-9])");
		 Pattern pattern5=Pattern.compile("(ArrivalTime):(20[012][0-9]-[01][0-9]-[0123][0-9])(\\s[012][0-9]:[0-6][0-9])");
		 Pattern pattern6=Pattern.compile("(Plane):([BN]\\d{4})");
		 Pattern pattern8=Pattern.compile("(Type):([A-Za-z0-9]+)");
		 Pattern pattern9=Pattern.compile("(Seats):(([5-9][0-9])|([1-5][0-9][0-9])|(600))");
		 Pattern pattern10=Pattern.compile("(Age):(([0-9]|([1-2][0-9])|(30))(\\.[0-9])?)");
		 Matcher tomacher = pattern00.matcher(temp[0]);
		 if(!tomacher.matches()) {  //�����������ǰ����ֻ������ڸ�ʽ������yyyy-MM-dd 
			 throw new DateException();
		 }
		 tomacher=pattern01.matcher(temp[1]);
		 if(!tomacher.matches()) {  //����Ų�������λ��д��ĸ��2-4λ���ֹ���
			 throw new FlightNumberException();
		 }
		 tomacher=pattern4.matcher(all[4]);
		 if(!tomacher.matches()) {  //����ʱ�䲻����yyyy-MM-dd HH:mm
			 throw new FromTimeException();
		 }
		 tomacher=pattern5.matcher(all[5]);
		 if(!tomacher.matches()) {  //�ִ�ʱ�䲻����yyyy-MM-dd HH:mm
			 throw new ToTimeException();
		 }
		 tomacher=pattern6.matcher(all[6]);
		 if(!tomacher.matches()) {  //�ɻ���Ų����ϵ�һλΪN��B����������λ����
			 throw new PlaneIdException();
		 }
		 tomacher=pattern8.matcher(all[8]);
		 if(!tomacher.matches()) {  //���Ͳ����ϴ�Сд��ĸ�����ֹ��ɣ������пո����������
			 throw new PlaneTypeException();
		 }
		 tomacher=pattern9.matcher(all[9]);
		 if(!tomacher.matches()) {  //��λ���������������ҷ�ΧΪ[50,600]
			 throw new PlaneSeatsException();
		 }
		 tomacher=pattern10.matcher(all[10]);
		 if(!tomacher.matches()) {  //���䲻���Ϸ�ΧΪ[0,30]�����Ϊ1λС������С��
			 throw new PlaneAgeException();
		 }
	 }	 	
	 
	 /**
	  * �õ�һ���ַ�������������ַ���
	  * @param name ǰ����ַ���
	  * @param S �����ַ���
	  * @return �����ַ�����ȥǰ���ַ�������ַ���
	  */
	 public String getAllinformation(String name,String S) {
			Pattern pattern = Pattern.compile("(?<="+name+").+");
			Matcher mc = pattern.matcher(S);
			while(mc.find())
				return mc.group();
			return "";
		}
}
