package subway;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

//복합키를 사용하기 위해서는 인터페이스 WritableComparable 사용
public class LineKey implements WritableComparable<LineKey> {
	
	private String data;
	// year:month:day:line:station:board
	private String[] dataArray;
	
	public LineKey() {}
//	public LineKey(Integer line, String board) {
//		this.line = line;
//		this.board = board;
//	}
	
	// 입력스트림에서 년도와 월을 조회
	@Override
	public void readFields(DataInput input) throws IOException {
		data = WritableUtils.readString(input);
		dataArray = data.split(":");
	}
	
	// 출력스트림의 년도와 월을 출력
	@Override
	public void write(DataOutput output) throws IOException {
		WritableUtils.writeString(output, data);
	}
	
	// 복합키와 복합키를 비교해 순서를 정할 때 사용
	@Override
	public int compareTo(LineKey other) {
		int result = 0;
		if(result == 0) {
			result = this.getYear().compareTo(other.getYear());
		}
		if(result == 0) {
			result = this.getMonth().compareTo(other.getMonth());
		}
		if(result == 0) {
			result = this.getDay().compareTo(other.getDay());
		}
		if(result == 0) {
			result = this.getLine().compareTo(other.getLine());
		}
		if(result == 0) {
			result = this.getBoard().compareTo(other.getBoard());
		}
		return result;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append(this.getMonth() + "월")
				.append(this.getDay() + "일")
				.append("-")
				.append(this.getLine() + "호선")
//				.append("-")
//				.append(this.getStation())
				.append("-")
				.append(this.getBoard().equals("B") ? "승차" : (this.getBoard().equals("U") ? "하차" : ""))
				.toString();
	}
	
	public void setData(String data) {
		this.data = data;
		this.dataArray = data.split(":");
	}
	public String getData() {
		return data;
	}
	public String getYear() {
		return dataArray[0];
	}
	public String getMonth() {
		return dataArray[1];
	}
	public String getDay() {
		return dataArray[2];
	}
	public String getLine() {
		return dataArray[3];
	}
	public String getStation() {
		return dataArray[4];
	}
	public String getBoard() {
		return dataArray[5];
	}

}
