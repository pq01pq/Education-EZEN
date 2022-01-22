package wordCount;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	private Text outputKey = new Text();
	private IntWritable outputValue = new IntWritable(1);
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		StringTokenizer st = new StringTokenizer(value.toString());
		while(st.hasMoreTokens()) {
			outputKey.set(st.nextToken());
			context.write(outputKey, outputValue);
		}
	}
	
	
	
}
