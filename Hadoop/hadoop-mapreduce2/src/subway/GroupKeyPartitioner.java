package subway;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class GroupKeyPartitioner extends Partitioner<LineKey, IntWritable> {

	@Override
	public int getPartition(LineKey key, IntWritable value, int partitionNumber) {
		int hash = key.getMonth().hashCode();
		int partition = hash % partitionNumber;
		return partition;
	}
}
