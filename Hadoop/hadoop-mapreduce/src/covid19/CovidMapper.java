package covid19;

import java.io.IOException;
//import java.text.SimpleDateFormat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CovidMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	Text outputKey = new Text();
	IntWritable outputValue = new IntWritable(1);

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		CovidParser covid = new CovidParser(value);
		outputKey.set(covid.getLocale());
		context.write(outputKey, outputValue);
	}

}