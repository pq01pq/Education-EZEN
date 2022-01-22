package airlinePerformanceWorkType;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DelayCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private Text outputKey = new Text();
	private final static IntWritable outputValue = new IntWritable(1);
	private String workType;

	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		workType = context.getConfiguration().get("workType");
	}

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		AirlinePerformanceParser parser = new AirlinePerformanceParser(value);
		if(workType.equals("departure")) {
			outputKey.set(parser.getYear() + "-" + (parser.getMonth() < 10 ? "0" + parser.getMonth() : parser.getMonth()));
			context.write(outputKey, outputValue);
		} else if(parser.getArriveDelaytime() > 0){
			outputKey.set(parser.getYear() + "-" + (parser.getMonth() < 10 ? "0" + parser.getMonth() : parser.getMonth()));
			context.write(outputKey, outputValue);
		}
		
	}

}