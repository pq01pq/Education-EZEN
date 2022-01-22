package hdfsTest;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class SingleFileWriteRead {
	
	public static void main(String[] args) throws IOException {
		if(args.length != 2) {
			System.out.println("사용법이 틀렸음");
			System.exit(0);
		}
		Configuration conf = new Configuration();
		FileSystem hdfs = FileSystem.get(conf);
		Path path = new Path(args[0]);
		if(hdfs.exists(path)) {
			hdfs.delete(path, true);
		}
		
		FSDataOutputStream outputStream = hdfs.create(path);
		outputStream.writeUTF(args[1]);
		outputStream.close();
		
		FSDataInputStream inputStream = hdfs.open(path);
		
		String result = inputStream.readUTF();
		inputStream.close();
		
		System.out.println("파일로부터 읽어온 내용 : " + result);
	}
}
