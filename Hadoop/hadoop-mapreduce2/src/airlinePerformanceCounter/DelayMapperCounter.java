package airlinePerformanceCounter;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DelayMapperCounter extends Mapper<LongWritable, Text, Text, IntWritable> {

	private Text outputKey = new Text();
	private final static IntWritable outputValue = new IntWritable(1);
	private String workType;

	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		workType = context.getConfiguration().get("workType");
	}

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		AirlineParser airline = new AirlineParser(value);
		switch(workType) {
		case "departure":
			if(airline.isDepartureDelayAvailable()) {
				if(airline.getDepartureDelaytime() > 0) {
					outputKey.set(airline.getYear() +
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
			break;
		case "arrival":
			if(airline.isArriveDelayAvailable()) {
				if(airline.getArriveDelaytime() > 0) {
					outputKey.set(airline.getYear() +
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
			break;
		default :
		}
	}

}