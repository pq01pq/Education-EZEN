package callTaxi;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TaxiMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

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
		TaxiParser taxi = new TaxiParser(value);
		switch(workType) {
		case "date":
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			outputKey.set(sdf.format(taxi.getDate()));
			context.write(outputKey, outputValue);
			break;
		case "locale":
			outputKey.set(taxi.getAddress());
			context.write(outputKey, outputValue);
			break;
		default :
		}
	}

}