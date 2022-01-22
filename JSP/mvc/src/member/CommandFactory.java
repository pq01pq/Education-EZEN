package member;

import java.io.IOException;

import javax.servlet.ServletException;

// 싱글톤 객체
public class CommandFactory {
	private CommandFactory() {}	// 내부에서만 객체를 생성
	private static CommandFactory instance = new CommandFactory();
	public static CommandFactory getInstance() {
		return instance;
	}
	
	public CommandIf createCommand(String cmd) throws ServletException, IOException {
		CommandIf cmdIf = null;
		switch(cmd) {
		case "input.mem": {
			cmdIf = new InputCommand();
			break;
		}
		case "insert.mem": {
			cmdIf = new InsertCommand();
			break;
		}
		case "search.mem": {
			cmdIf = new SearchCommand();
			break;
		}
		case "update_input.mem": {
			cmdIf = new UpdateInputCommand();
			break;
		}
		case "update.mem": {
			cmdIf = new UpdateCommand();
			break;
		}
		case "delete.mem": {
			cmdIf = new DeleteCommand();
			break;
		}
		case "start.mem": {
			cmdIf = new SearchCommand();
			break;
		}
		case "ssn.mem": {
			cmdIf = new SsnCommand();
			break;
		}
		case "check.mem": {
			cmdIf = new CheckCommand();
			break;
		}
		case "login.mem": {
			cmdIf = new LoginCommand();
			break;
		}
		case "loginOk.mem": {
			cmdIf = new LoginOkCommand();
			break;
		}
		case "logout.mem": {
			cmdIf = new LogoutCommand();
			break;
		}
		default : {
			
		}
		}
		return cmdIf;
	}
}
