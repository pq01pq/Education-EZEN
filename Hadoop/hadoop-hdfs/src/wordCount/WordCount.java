package wordCount;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class WordCount {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		if(args.length != 2) {
			System.out.println("사용법이 틀림. 다시 실행하시오.");
			System.exit(0);
		}
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "WordCount");
		
		job.setJarByClass(WordCount.class);
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// set format
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		// set key, value class
		job.setMapOutputKeyClass(Text.class); // data
		job.setMapOutputValueClass(IntWritable.class); // count data
		
		// execute
		job.waitForCompletion(true);
	}

}
