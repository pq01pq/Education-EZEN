package covid19;

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


public class CovidCount {
   public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
      if(args.length != 2) {
         System.out.println("사용법이 틀림. 다시 실행하시오.");
         System.exit(0);
      }
      
      Configuration conf = new Configuration();
      Job job = Job.getInstance(conf, "TaxiCount");
      
      job.setJarByClass(CovidCount.class);
      job.setMapperClass(CovidMapper.class);
      job.setReducerClass(CovidReducer.class);
      
      FileInputFormat.addInputPath(job, new Path(args[0]));
      FileOutputFormat.setOutputPath(job, new Path(args[1]));
      
      job.setInputFormatClass(TextInputFormat.class);
      job.setOutputFormatClass(TextOutputFormat.class);
      
      job.setMapOutputKeyClass(Text.class); // data
      job.setMapOutputValueClass(IntWritable.class); // count data
      
      // 실행
      job.waitForCompletion(true);
   }

}