package hdfsTest;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Test01 {
	
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		Configuration conf = new Configuration();
		FileSystem hdfs = FileSystem.get(conf);
		
		while(true) {
			System.out.println("1.파일 읽기 2.파일 저장 3.종료");
			System.out.print("번호 입력: ");
			int index;
			try {
				index = Integer.parseInt(scan.next());
			} catch(NumberFormatException e) {
				System.out.println("숫자로 입력하시오.");
				continue;
			}
			
			switch(index) {
			case 1:
				System.out.print("파일명: ");
				String inputFileName = scan.next();
				Path inputPath = new Path(inputFileName);
				if(!hdfs.exists(inputPath)) {
					System.out.println("없는 파일입니다.");
					continue;
				}
				
				FSDataInputStream inputStream = hdfs.open(inputPath);
				String inputContext = inputStream.readUTF();	inputStream.close();
				System.out.println("내용: ");
				System.out.println(inputContext);
				break;
			case 2:
				System.out.print("파일명: ");
				String outputFileName = scan.next();
				Path path = new Path(outputFileName);
				if(hdfs.exists(path)) {
					System.out.println("존재하는 파일입니다.");
					continue;
				}
				System.out.print("내용: ");
				String outputContext = scan.nextLine();
				FSDataOutputStream outputStream = hdfs.create(path);
				outputStream.writeUTF(outputContext);	outputStream.close();
				break;
			case 3:
				System.exit(0);
			default :
				System.out.println("잘 보고 입력하라고!!");
			}
			
		}
	}
}
