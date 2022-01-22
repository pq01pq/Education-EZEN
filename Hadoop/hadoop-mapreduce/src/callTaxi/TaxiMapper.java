package callTaxi;

import java.io.IOException;
//import java.text.SimpleDateFormat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TaxiMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	Text outputKey = new Text();
	IntWritable outputValue = new IntWritable();

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		TaxiParser taxi = new TaxiParser(value);
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		outputKey.set(taxi.getAddress());
		outputValue.set(taxi.getCount());
		context.write(outputKey, outputValue);
	}

}