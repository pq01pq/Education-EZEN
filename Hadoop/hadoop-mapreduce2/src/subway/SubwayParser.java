package subway;

import org.apache.hadoop.io.Text;

public class SubwayParser {
	private String date;
	private int line;
	private int num;
	private String staion;
	private String board;
	private int[] count;
	private int total;
	
	public SubwayParser() {super();}
	public SubwayParser(Text text) {
		super();
		String[] dataArray = text.toString().split(",");
		date = dataArray[0];
		line = dataArray[1].charAt(0) - '0';
		num = Integer.parseInt(dataArray[2]);
		staion = dataArray[3];
		board = dataArray[4];
		count = new int[20];
		total = 0;
		for(int i = 0; i < this.count.length && i < dataArray.length + 5; i++) {
			count[i] = Integer.parseInt(dataArray[i + 5]);
			total += count[i];
		}
	}
	
	public String getDate() {
		return date;
	}
	public int getLine() {
		return line;
	}
	public int getNum() {
		return num;
	}
	public String getStaion() {
		return staion;
	}
	public String getBoard() {
		return board;
	}
	public int getTotal() {
		return total;
	}
}
