package covidMultiple;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class MultipleCovidReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	
	private MultipleOutputs<Text, IntWritable> mos;
	private Text outputKey = new Text();
	private IntWritable result = new IntWritable();
	
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		mos = new MultipleOutputs<>(context);
	}
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		String[] columns = key.toString().split(":");
		outputKey.set(columns[1]);
		switch(columns[0]) {
		case "D":{
			int sum = 0;
			for (IntWritable data : values) {
				sum += data.get();
			}
			result.set(sum);
			mos.write("date", outputKey, result);
			break;
		}
		case "L":{
			int sum = 0;
			for (IntWritable data : values) {
				sum += data.get();
			}
			result.set(sum);
			mos.write("locale", outputKey, result);
			break;
		}
		}
		
		
	}

}
