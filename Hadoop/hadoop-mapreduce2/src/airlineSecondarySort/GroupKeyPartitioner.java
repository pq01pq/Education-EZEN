package airlineSecondarySort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class GroupKeyPartitioner extends Partitioner<DateKey, IntWritable> {

	@Override
	public int getPartition(DateKey key, IntWritable value, int partitionNumber) {
		int hash = key.getYear().hashCode();
		int partition = hash % partitionNumber;
		return partition;
	}
}
