package subway;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SubwayCountLineKeyMapper extends Mapper<LongWritable, Text, LineKey, IntWritable> {

	private LineKey outputKey = new LineKey();
	private IntWritable outputValue = new IntWritable();

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		SubwayParser subway = new SubwayParser(value);
		String[] dateArray = subway.getDate().split("-");
		String data = dateArray[0] + ":" + dateArray[1] + ":" + dateArray[2] + ":" + subway.getLine() + ":" + subway.getStaion();
		String board;
		if(subway.getBoard() == null) {
			board = "N";
		} else {
			switch(subway.getBoard()) {
			case "승차":
				board = "B";
				break;
			case "하차":
				board = "U";
				break;
			default :
				board = "N";
			}
		}
		data += ":" + board;
		
		outputKey.setData(data);
		outputValue.set(subway.getTotal());
		
		context.write(outputKey, outputValue);
	}

}
