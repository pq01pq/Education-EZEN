package covidMultiple;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MultipleCovidMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private Text outputKey = new Text();
	private final static IntWritable outputValue = new IntWritable(1);

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		CovidParser covid = new CovidParser(value);
		outputKey.set("D:" + covid.getInfectedYear() + "-" + covid.getInfectedDay());
		context.write(outputKey, outputValue);
		outputKey.set("L:" + covid.getLocale());
		context.write(outputKey, outputValue);
	}

}