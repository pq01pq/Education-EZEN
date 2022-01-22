import java.io.IOException;

class A06{
	public void disp() throws IOException{
		System.out.print("숫자를 입력 : ");
		int su = System.in.read() - 48;
		System.out.println("입력한 수 : " + su);
	}
}

class Test06{
	public static void main(String[] args) throws IOException{
		A06 ap = new A06();
		ap.disp();
		//disp()메소드에 IOException 이 예외전가로 있어서
		// main에서 처리를 해야하는데,
		//예외 전가로 처리한 예제이다. 
	}
}
