package covid19;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.Text;

public class CovidParser {
	private Date infectedDate;
	private Integer year;
	private Integer month;
	private Integer day;
	private String locale;
	
	public CovidParser(Text text) {
		String[] dataArray = text.toString().split(",");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[] dateArray;
		try {
			this.infectedDate = sdf.parse(dataArray[1]);
			dateArray = dataArray[1].split("-");
			this.year = Integer.valueOf(dateArray[0]);
			this.month = Integer.valueOf(dateArray[1]);
			this.day = Integer.valueOf(dateArray[2]);
		} catch(ParseException e) {
			this.infectedDate = new Date(0L);
		}
		
		this.locale = dataArray[5];
	}
	
	public Integer getInfectedYear() {
		return this.year;
	}
	public Integer getInfectedMonth() {
		return this.month;
	}
	public Integer getInfectedDay() {
		return this.day;
	}
	

	public Date getInfectedDate() {
		return infectedDate;
	}

	public void setInfectedDate(Date infectedDate) {
		this.infectedDate = infectedDate;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
	
}
