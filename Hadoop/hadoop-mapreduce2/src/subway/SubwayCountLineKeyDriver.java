package subway;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class SubwayCountLineKeyDriver extends Configured implements Tool {
	
	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new SubwayCountLineKeyDriver(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		// getRemainingArgs() : -D, workType을 뺀 나머지 args값을 가져올 때 사용하는 메소드
		String[] otherArgs = new GenericOptionsParser(getConf(), args).getRemainingArgs();
		
		if(otherArgs.length != 2) {
			System.out.println("usage : (-[options, option args]) [arg0] [arg1]");
			System.exit(0);
		}
		Job job = Job.getInstance(getConf(), "SecondarySortSubwayCount");
		
		job.setJarByClass(SubwayCountLineKeyDriver.class);
		job.setPartitionerClass(GroupKeyPartitioner.class);
		job.setGroupingComparatorClass(GroupKeyComparator.class);
		job.setSortComparatorClass(LineKeyComparator.class);
		job.setMapperClass(SubwayCountLineKeyMapper.class);
		job.setReducerClass(SubwayCountLineKeyReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setOutputKeyClass(LineKey.class); // data
		job.setOutputValueClass(IntWritable.class); // count data

		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		
		// multiple outputs setting
		// 작업공간, 나갈 데이터의 이름, 출력파일 포맷, 출력키, 출력값
		MultipleOutputs.addNamedOutput(job, "subwayBoard", TextOutputFormat.class, LineKey.class, IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "subwayUnboard", TextOutputFormat.class, LineKey.class, IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "subwayJunk", TextOutputFormat.class, LineKey.class, IntWritable.class);
		
		// 실행
		job.waitForCompletion(true);
		
		return 0;
	}
}
