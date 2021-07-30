package Timeslot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import Exception.BeginEndTimeException;


public class Timeslot {

	private final Calendar begintime;  //һ��ʱ��Եĳ�ʼʱ��
	private final Calendar endtime;    //һ��ʱ��Ե���ֹʱ��
	
    //immutability��
	// Abstraction function:
    // AF(begintime)=һ��ʱ��Եĳ�ʼʱ��
	// AF(endtime)=һ��ʱ��Ե���ֹʱ��
	// Representation invariant:
    // ��ʼʱ��Ҫ������ֹʱ��
    // Safety from rep exposure:
    // ��begintime,endtime����Ϊprivate final
	
	// TODO checkRep
    private void checkRep() {  //��֤��ʼʱ��Ҫ������ֹʱ��
          assert begintime.compareTo(endtime)<0:"��ʼʱ��Ҫ������ֹʱ��\n";
    }
	
	/**
	 * ���췽��
	 * @param begin ��ʼʱ���ַ���
	 * @param end   ��ֹʱ���ַ���
	 * @throws ParseException ʱ���ʽ��Ϊyyyy-MM-dd HH:mm
	 * @throws BeginEndTimeException ��ʼʱ��������ֹʱ��
	 */
	public Timeslot(String begin,String end) throws ParseException, BeginEndTimeException{
		this.begintime= Calendar.getInstance(); 
		begintime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(begin));
		this.endtime= Calendar.getInstance(); 
		endtime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(end));
		if(begintime.compareTo(endtime)>0) {  //��ʼʱ��������ֹʱ���׳��쳣
			throw new BeginEndTimeException();
		}
		checkRep();
	}
	
	/**
	 * ���س�ʼʱ��
	 * @return ��ʼʱ��
	 */
	public Calendar getbegintime() {
		checkRep();
		return begintime;
	}
	
	/**
	 * ������ֹʱ��
	 * @return ��ֹʱ��
	 */
	public Calendar getendtime() {
		checkRep();
		return endtime;
	}

	/**
	 * ��дhashCode����
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((begintime == null) ? 0 : begintime.hashCode());
		result = prime * result + ((endtime == null) ? 0 : endtime.hashCode());
		return result;
	}

	/**
	 * ��дequals����
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timeslot other = (Timeslot) obj;
		if (begintime == null) {
			if (other.begintime != null)
				return false;
		} else if (!begintime.equals(other.begintime))
			return false;
		if (endtime == null) {
			if (other.endtime != null)
				return false;
		} else if (!endtime.equals(other.endtime))
			return false;
		return true;
	}

	
}
