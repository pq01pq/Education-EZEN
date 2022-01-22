
class MyException07 extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public MyException07(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		this.message = super.getMessage() + "오류 내용";
		return message;
	}
	
}

public class Test07 {

	public static void main(String[] args) {
		MyException07 me = new MyException07("오류코드 : ");
		try {
			throw me;
		} catch(MyException07 e) {
			System.err.println(e.getMessage());
		}
	}

}
