package covid19;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.Text;

public class CovidParser {
	private Date infectedDate;
	private String year;
	private String month;
	private String day;
	private String locale;
	
	public CovidParser(Text text) {
		String[] dataArray = text.toString().split(",");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[] dateArray;
		try {
			this.infectedDate = sdf.parse(dataArray[1]);
			dateArray = dataArray[1].split("-");
			this.year = dateArray[0];
			this.month = dateArray[1];
			this.day = dateArray[2];
		} catch(ParseException e) {
			this.infectedDate = new Date(0L);
		}
		
		this.locale = dataArray[5];
	}
	
	public String getInfectedYear() {
		return this.year;
	}
	public String getInfectedMonth() {
		return this.month;
	}
	public String getInfectedDay() {
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
