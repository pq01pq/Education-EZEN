package view;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public abstract class AbsViewTemplate extends AbsView {

	@Override
	public void show() {
		showMenu();
		String input = getKeyInputString("번호");
		if(isValidNumber(input)) {
			execute(NumberUtils.toInt(input));
		}
	}
	
	protected boolean isValidNumber(String str) {
		if(StringUtils.isBlank(str) || !StringUtils.isNumeric(str)) {
			return false;
		}
		int num = NumberUtils.toInt(str);
		if(num < getMinMenuNumber() || num > getMaxMenuNumber()) {
			return false;
		}
		return true;
	}
	
	protected abstract void showMenu(); // 실제 화면 구현
	protected abstract int getMaxMenuNumber(); // 메뉴의 번호 중 제일 큰 번호
	protected abstract int getMinMenuNumber(); // 메뉴의 번호 중 제일 작은 번호
	protected abstract void execute(int num);
}
