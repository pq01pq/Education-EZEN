package callTaxi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class TaxiDriver extends Configured implements Tool {
	
	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new TaxiDriver(), args);
	}
	
	@Override
	public int run(String[] args) throws Exception {
		// getRemainingArgs() : -D, workType을 뺀 나머지 args값을 가져올 때 사용하는 메소드
		String[] otherArgs = new GenericOptionsParser(getConf(), args).getRemainingArgs();
		
		if(otherArgs.length != 2) {
			System.out.println("usage : (-[options, option args]) [arg0] [arg1]");
			System.exit(0);
		}
		Job job = Job.getInstance(getConf(), "DelayCount");

		job.setJarByClass(TaxiDriver.class);
		job.setMapperClass(TaxiMapper.class);
		job.setReducerClass(TaxiReducer.class);

		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		job.setMapOutputKeyClass(Text.class); // data
		job.setMapOutputValueClass(IntWritable.class); // count data

		// 실행
		job.waitForCompletion(true);
		
		return 0;
	}
}
