package airlineSecondarySort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

//복합키를 사용하기 위해서는 인터페이스 WritableComparable 사용
public class DateKey implements WritableComparable<DateKey> {
	
	private String year;
	private Integer month;
	
	public DateKey() {}
	public DateKey(String year, Integer month) {
		this.year = year;
		this.month = month;
	}
	
	// 입력스트림에서 년도와 월을 조회
	@Override
	public void readFields(DataInput input) throws IOException {
		year = WritableUtils.readString(input);
		month = input.readInt();
	}
	
	// 출력스트림의 년도와 월을 출력
	@Override
	public void write(DataOutput output) throws IOException {
		WritableUtils.writeString(output, year);
		output.writeInt(month);
	}
	
	// 복합키와 복합키를 비교해 순서를 정할 때 사용
	@Override
	public int compareTo(DateKey other) {
		int result = this.year.compareTo(other.year);
		if(result == 0) {
			result = this.month.compareTo(other.month);
		}
		return result;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(year).append("-").append(month < 10 ? "0" + month : month).toString();
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
}
