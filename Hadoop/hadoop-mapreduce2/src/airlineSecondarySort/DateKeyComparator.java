package airlineSecondarySort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class DateKeyComparator extends WritableComparator {
	
	protected DateKeyComparator() {
		super(DateKey.class, true);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		DateKey date1 = (DateKey) a;
		DateKey date2 = (DateKey) b;
		int cmp = date1.getYear().compareTo(date2.getYear());
		if(cmp != 0) {
			return cmp;
		}
		return Integer.compare(date1.getMonth(), date2.getMonth());
	}
	
	
}
