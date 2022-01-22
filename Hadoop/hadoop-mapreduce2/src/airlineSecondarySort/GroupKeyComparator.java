package airlineSecondarySort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupKeyComparator extends WritableComparator {
	
	protected GroupKeyComparator() {
		super(DateKey.class, true);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		DateKey date1 = (DateKey) a;
		DateKey date2 = (DateKey) b;
		return date1.getYear().compareTo(date2.getYear());
	}
	
	
}