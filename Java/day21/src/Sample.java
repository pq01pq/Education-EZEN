import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFileChooser;

public class Sample {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		// 1. 파일 선택
		JFileChooser jfc = new JFileChooser(".");
		int res = jfc.showOpenDialog(null);
		System.out.println("res = " + res);
		
		File file = null;
		if(res == 0) {
			file = jfc.getSelectedFile();
		} else {
			System.out.println("파일 선택 안함");
			System.exit(0);
		}
		
		System.out.println("선택한 파일명: " + file.getName());
		
		// 2. 파일 내용 꺼내기
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
		byte[] bs = new byte[65508];
		int b;
//		while(true) {
//			b = dis.read(bs, 0, bs.length);
//			if(b == -1) break;
//		}
		while(dis.read(bs, 0, bs.length) != -1);
		
		System.out.println(new String(bs));
		dis.close();
	}

}
