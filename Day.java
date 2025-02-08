public class Day implements Cloneable, Comparable<Day>{
	
	private int year;
	private int month;
	private int day;
	private static final String MonthNames="JanFebMarAprMayJunJulAugSepOctNovDec";
	
	public Day(int y, int m, int d) {
		this.year=y;
		this.month=m;
		this.day=d;		
	}

	public Day(String sDay) {
		set(sDay);
	}

	public void set(String sDay){
		String[] sDayParts = sDay.split("-");
		this.day = Integer.parseInt(sDayParts[0]); 
		this.year = Integer.parseInt(sDayParts[2]); 
		this.month = MonthNames.indexOf(sDayParts[1])/3+1;
	}


	@Override
	public String toString() {
		return day+"-"+ MonthNames.substring((month-1)*3,(month)*3) + "-"+ year;
	}

	@Override
	public Day clone(){ 
		Day copy=null;
		try{
			copy = (Day) super.clone();
		}
		catch (CloneNotSupportedException e){
			e.printStackTrace();
		}
		return copy;
	}

	@Override
    public int compareTo(Day another) {
		Integer thisDate = (this.year * 10000) + (this.month * 100) + this.day;
		Integer anotherDate = (another.year * 10000) + (another.month * 100) + another.day;
		return thisDate.compareTo(anotherDate);
    }

	static public boolean isLeapYear(int y) {
		if (y%400==0)
			return true;
		else if (y%100==0)
			return false;
		else if (y%4==0)
			return true;
		else
			return false;
	}
	
	static public boolean valid(int y, int m, int d) {
		if (m<1 || m>12 || d<1) return false;
		switch(m){
			case 1: case 3: case 5: case 7:
			case 8: case 10: case 12:
					 return d<=31; 
			case 4: case 6: case 9: case 11:
					 return d<=30; 
			case 2:
					 if (isLeapYear(y))
						 return d<=29; 
					 else
						 return d<=28; 
		}
		return false;
	}

	static public void validString(String d) throws ExInvalidDate{
		String[] sDayParts = d.split("-");
		if (sDayParts.length !=3)
			throw new ExInvalidDate();
		
		int day;
		int year;
		try{day = Integer.parseInt(sDayParts[0]);}catch (NumberFormatException e){throw new ExInvalidDate();}
		try{year = Integer.parseInt(sDayParts[2]);}catch (NumberFormatException e){throw new ExInvalidDate();}

		int month = MonthNames.indexOf(sDayParts[1]);
		if (month == -1)
			throw new ExInvalidDate();
		month = month/3+1;
		if (!Day.valid(year, month, day))
			throw new ExInvalidDate();
		if (!((SystemDate.getInstance().compareTo(new Day(d)))<0))
			throw new ExInvalidDate(SystemDate.getInstance().toString());
	}

	public Day incrementByDays(int incrementDays){
		int iday = this.day;
		int iyear = this.year;
		int imonth = this.month;
		for (int i=1; i<=incrementDays;i++){
			if (valid(iyear,imonth,iday+1)){
				iday++;
			}
			else if (valid(iyear,imonth+1,1)){
				iday = 1;
				imonth++;
			}
			else{
				iday = 1;
				imonth = 1;
				iyear++;
			}
		}
		return new Day(iyear,imonth,iday);
	}
}
