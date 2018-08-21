package sort;

public class Date implements Comparable<Date>{

	private int day;
	private int month;
	private int year;
	private int tst;
	
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	@Override
	public String toString() {
		return "Date [day=" + day + ", month=" + month + ", year=" + year + "]";
	}

	//比较两个Date对象，返回1证明该对象比传入的对象大，返回-1表示小，返回0表示相等 
	@Override
	public int compareTo(Date o) {
		if(this.year > o.year)	return +1;
		if(this.year < o.year)	return -1;
		if(this.month > o.month)	return +1;
		if(this.month < o.month)	return -1;
		if(this.day > o.day)	return +1;
		if(this.day < o.day)	return -1;
		
		return 0;
	}

}
