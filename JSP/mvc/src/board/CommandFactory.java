package board;

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
		case "start.board":{
			cmdIf = new ListCommand();
			break;
		}
		case "list.board":{
			cmdIf = new ListCommand();
			break;
		}
		case "write.board":{
			cmdIf = new WriteCommand();
			break;
		}
		case "insert.board":{
			cmdIf = new InsertCommand();
			break;
		}
		case "context.board":{
			cmdIf = new ContextCommand();
			break;
		}
		case "check.board":{
			cmdIf = new CheckCommand();
			break;
		}
		case "updateInput.board":{
			cmdIf = new UpdateInputCommand();
			break;
		}
		case "update.board":{
			cmdIf = new UpdateCommand();
			break;
		}
		case "delete.board":{
			cmdIf = new DeleteCommand();
			break;
		}
		default :{
			
		}
		}
		return cmdIf;
	}
}
