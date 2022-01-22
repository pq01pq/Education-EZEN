package subway;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class SubwayCountLineKeyReducer extends Reducer<LineKey, IntWritable, LineKey, IntWritable> {
	
	private MultipleOutputs<LineKey, IntWritable> mos;
	private LineKey outputKey = new LineKey();
	private IntWritable result = new IntWritable();
	
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		mos = new MultipleOutputs<>(context);
	}
	
	@Override
	protected void reduce(LineKey key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		String board = key.getBoard();
		String outputFile = "subway" + (board.equals("B") ? "Board" : (board.equals("U") ? "Unboard" : "Junk"));
		
		for(IntWritable data : values) {
			sum += data.get();
		}
		
		result.set(sum);
		outputKey.setData(key.getData());
		mos.write(outputFile, outputKey, result);
	}
}
