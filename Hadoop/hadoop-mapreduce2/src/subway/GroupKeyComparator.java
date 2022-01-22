package subway;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupKeyComparator extends WritableComparator {
	
	protected GroupKeyComparator() {
		super(LineKey.class, true);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		LineKey line1 = (LineKey) a;
		LineKey line2 = (LineKey) b;
		return line1.compareTo(line2);
	}
	
	
}