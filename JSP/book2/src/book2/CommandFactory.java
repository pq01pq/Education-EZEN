package book2;

public class CommandFactory {
	private CommandFactory() {}
	private static CommandFactory factory = new CommandFactory();
	public static CommandFactory getInstance() {
		return factory;
	}
	
	public CommandIf createCommand(String command) {
		CommandIf cmdIf = null;
		if(command != null) {
			switch(command) {
			case "insert":{
				cmdIf = new InsertCommand();
				break;
			}
			case "search":{
				cmdIf = new SearchCommand();
				break;
			}
			case "delete":{
				cmdIf = new DeleteCommand();
				break;
			}
			default :{
				cmdIf = new SearchCommand();
				break;
			}
			}
		} else {
			cmdIf = new SearchCommand();
		}
		return cmdIf;
	}
}
