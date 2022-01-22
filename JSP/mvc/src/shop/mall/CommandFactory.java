package shop.mall;

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
		case "start.mall":{
			cmdIf = new StartCommand();
			break;
		}
		case "prodView.mall":{
			cmdIf = new ProductViewCommand();
			break;
		}
		case "cgProdList.mall":{
			cmdIf = new CategoryProductListCommand();
			break;
		}
		case "cartAdd.mall":{
			cmdIf = new CartAddCommand();
			break;
		}
		case "cartList.mall":{
			cmdIf = new CartListCommand();
			break;
		}
		case "cartEdit.mall":{
			cmdIf = new CartEditCommand();
			break;
		}
		case "cartDel.mall":{
			cmdIf = new CartDeleteCommand();
			break;
		}
		case "order.mall":{
			cmdIf = new OrderCommand();
			break;
		}
		default :{
			
		}
		}
		return cmdIf;
	}
}
