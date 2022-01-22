import java.io.*;
import java.net.*;

public class Test10 {
	public static void main(String[] args) throws Exception {
		DatagramPacket dp = 
				new DatagramPacket(new byte[65508], 65508);
		DatagramSocket ds = new DatagramSocket(12345);
		ds.receive(dp);
	
		ByteArrayInputStream bais = 
				new ByteArrayInputStream(dp.getData());
		BufferedInputStream bis = new BufferedInputStream(bais);
		ObjectInputStream ois = new ObjectInputStream(bis);
		
		A ap = (A)ois.readObject();
		System.out.println("ap.a = " + ap.a);
		
		ds.close();
	}
}
