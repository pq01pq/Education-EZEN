package shop.admin;

import java.io.IOException;

import javax.servlet.ServletException;

import shop.CommandIf;

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
		case "start.admin":{
			cmdIf = new StartCommand();
			break;
		}
		case "cate_input.admin":{
			cmdIf = new CategoryInputCommand();
			break;
		}
		case "cate_insert.admin":{
			cmdIf = new CategoryInsertCommand();
			break;
		}
		case "cate_list.admin":{
			cmdIf = new CategoryListCommand();
			break;
		}
		case "cate_delete.admin":{
			cmdIf = new CategoryDeleteCommand();
			break;
		}
		
		case "prod_input.admin":{
			cmdIf = new ProductInputCommand();
			break;
		}
		case "prod_insert.admin":{
			cmdIf = new ProductInsertCommand();
			break;
		}
		case "prod_list.admin":{
			cmdIf = new ProductListCommand();
			break;
		}
		case "prod_view.admin":{
			cmdIf = new ProductViewCommand();
			break;
		}
		case "prod_update_input.admin":{
			cmdIf = new ProductUpdateInputCommand();
			break;
		}
		case "prod_update.admin":{
			cmdIf = new ProductUpdateCommand();
			break;
		}
		case "prod_delete.admin":{
			cmdIf = new ProductDeleteCommand();
			break;
		}
		default :{
			
		}
		}
		return cmdIf;
	}
}
