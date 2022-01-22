package subway;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class LineKeyComparator extends WritableComparator {
	
	protected LineKeyComparator() {
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
