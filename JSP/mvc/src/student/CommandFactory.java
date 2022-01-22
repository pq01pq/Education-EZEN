package student;

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
		case "/insert.st": {
			cmdIf = new InsertCommand();
			break;
		}
		case "/delete.st": {
			cmdIf = new DeleteCommand();
			break;
		}
		case "/find.st": {
			cmdIf = new FindCommand();
			break;
		}
		case "/list.st": {
			cmdIf = new ListCommand();
			break;
		}
		case "/start.st":{
			cmdIf = new ListCommand();
			break;
		}
		}
		return cmdIf;
	}
}
