package covid19;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CovidMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

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
		CovidParser covid = new CovidParser(value);
		switch(workType) {
		case "date":
			outputKey.set(covid.getInfectedYear() + "-" + covid.getInfectedDay());
			context.write(outputKey, outputValue);
			break;
		case "locale":
			outputKey.set(covid.getLocale());
			context.write(outputKey, outputValue);
			break;
		default :
		}
	}

}