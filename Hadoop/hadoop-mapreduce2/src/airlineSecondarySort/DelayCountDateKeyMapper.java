package airlineSecondarySort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DelayCountDateKeyMapper extends Mapper<LongWritable, Text, DateKey, IntWritable> {

	private DateKey outputKey = new DateKey();
	private final static IntWritable outputValue = new IntWritable(1);

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		AirlineParser airline = new AirlineParser(value);
		
		if(airline.isDepartureDelayAvailable()) {
			if(airline.getDepartureDelaytime() > 0) {
				outputKey.setYear("D:" + airline.getYear());
				outputKey.setMonth(airline.getMonth());
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
				outputKey.setYear("A:" + airline.getYear());
				outputKey.setMonth(airline.getMonth());
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
