package airlineSecondarySort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class DelayCountDateKeyReducer extends Reducer<DateKey, IntWritable, DateKey, IntWritable> {
	
	private MultipleOutputs<DateKey, IntWritable> mos;
	private DateKey outputKey = new DateKey();
	private IntWritable result = new IntWritable();
	
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		mos = new MultipleOutputs<>(context);
	}
	
	@Override
	protected void reduce(DateKey key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		String[] columns = key.getYear().split(":");
		int sum = 0;
		Integer month = key.getMonth();
		String outputFile = columns[0].equals("D") ? "departure" : (columns[0].equals("A") ? "arrival" : "");
		
		for(IntWritable data : values) {
//			if(month != key.getMonth()) {
//				result.set(sum);
//				outputKey.setYear(columns[1]);
//				outputKey.setMonth(month);
//				mos.write(outputFile, outputKey, result);
//			}
			sum += data.get();
		}
		
//		if(key.getMonth() == month) {
			result.set(sum);
			outputKey.setYear(columns[1]);
			outputKey.setMonth(month);
			mos.write(outputFile, outputKey, result);
//		}
	}
}
