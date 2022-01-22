package airlineMultipleCounter;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MultipleDelayCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private Text outputKey = new Text();
	private final static IntWritable outputValue = new IntWritable(1);

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		AirlineParser airline = new AirlineParser(value);

		if(airline.isDepartureDelayAvailable()) {
			if(airline.getDepartureDelaytime() > 0) {
				outputKey.set("D:" + airline.getYear() +
						"-" + (airline.getMonth() < 10 ? "0" + airline.getMonth() : airline.getMonth()));
				context.write(outputKey, outputValue);
			} else if(airline.getDepartureDelaytime() == 0) {
				context.getCounter(DelayCounters.SCHEDULED_DEPARTURE).increment(1L);
			} else {
				context.getCounter(DelayCounters.EARLY_DEPARTURE).increment(1L);
			}
		} else {
			context.getCounter(DelayCounters.NOT_AVAILABLE_DEPARTURE).increment(1L);
		}

		if(airline.isArriveDelayAvailable()) {
			if(airline.getArriveDelaytime() > 0) {
				outputKey.set("A:" + airline.getYear() +
						"-" + (airline.getMonth() < 10 ? "0" + airline.getMonth() : airline.getMonth()));
				context.write(outputKey, outputValue);
			} else if(airline.getDepartureDelaytime() == 0) {
				context.getCounter(DelayCounters.SCHEDULED_ARRIVAL).increment(1L);
			} else {
				context.getCounter(DelayCounters.EARLY_ARRIVAL).increment(1L);
			}
		} else {
			context.getCounter(DelayCounters.NOT_AVAILABLE_ARRIVAL).increment(1L);
		}
	}

}