package taxiMultiple;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MultipleTaxiMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private Text outputKey = new Text();
	private final static IntWritable outputValue = new IntWritable(1);

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		TaxiParser taxi = new TaxiParser(value);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		outputKey.set("D:" + sdf.format(taxi.getDate()));
		context.write(outputKey, outputValue);
		outputKey.set("L:" + taxi.getAddress());
		context.write(outputKey, outputValue);
	}

}