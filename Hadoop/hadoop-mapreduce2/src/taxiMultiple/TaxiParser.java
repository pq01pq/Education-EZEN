package taxiMultiple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.Text;

public class TaxiParser {
	private Date date;
	private String si;
	private String gu;
	private String dong;
	private Integer count;
	
	public TaxiParser(Text text) {
		String[] dataArray = text.toString().split(",");
		
		// 1. date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			this.date = sdf.parse(dataArray[0]);
		} catch (ParseException e) {
			e.printStackTrace();
			this.date = new Date(0L);
		}
		
		this.si = dataArray[3];
		this.gu = dataArray[4];
		this.dong = dataArray[5];
		
		// 3. count
		try {
			this.count = Integer.parseInt(dataArray[6]);
		} catch(NumberFormatException | NullPointerException e) {
			e.printStackTrace();
			this.count = 0;
		}
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getAddress() {
		return this.si + " " + this.gu + " " + this.dong;
	}
	
	public String getSi() {
		return si;
	}
	public void setSi(String si) {
		this.si = si;
	}
	public String getGu() {
		return gu;
	}
	public void setGu(String gu) {
		this.gu = gu;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
