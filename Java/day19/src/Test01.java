import java.io.File;

public class Test01 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		File f1 = new File("C:\\JAVA\\EZEN\\workspace\\day19\\dir\\aaa.txt");
		File f2 = new File("C:\\JAVA\\EZEN\\workspace\\day19\\dir", "aaa.txt");
		
		File dir = new File("C:\\JAVA\\EZEN\\workspace\\day19\\dir");
		File f3 = new File(dir, "aaa.txt");
		
		System.out.println("File.separator = " + File.separator);
		System.out.println("File.separatorChar = " + File.separatorChar);
		System.out.println("File.pathSeparator = " + File.pathSeparator);
		System.out.println("File.pathSeparatorChar = " + File.pathSeparatorChar);
		
		File f4 = new File("C:" + File.separator + "JAVA" + File.separator + "workspace"
				+ "day19" + File.separator + "dir" + File.separator + "aaa.txt");
		
	}

}
