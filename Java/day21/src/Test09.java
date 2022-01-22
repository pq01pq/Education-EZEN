import java.net.*;
import java.io.*;
class A implements Serializable{
	int a = 10;
}

public class Test09 {
	public static void main(String[] args) throws Exception{
		InetAddress ia = InetAddress.getByName("localhost");
		
		A ap = new A();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(baos);
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		
		oos.writeObject(ap);
		oos.flush();
		byte[] data = baos.toByteArray();
		
		//System.out.println("length = " + data.length);
		
		DatagramPacket dp = new DatagramPacket(data,
			data.length, ia, 12345);
		DatagramSocket ds = new DatagramSocket();
		ds.send(dp);
		ds.close();
	}
}











