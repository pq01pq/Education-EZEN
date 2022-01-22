package wordCount;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	
	private IntWritable result = new IntWritable();

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int sum = 0;
		for(IntWritable data : values) {
			sum += data.get();
		}
		result.set(sum);
		context.write(key, result);
	}
	
	
}