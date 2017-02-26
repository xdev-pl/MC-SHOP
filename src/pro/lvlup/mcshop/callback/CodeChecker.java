package pro.lvlup.mcshop.callback;

import pro.lvlup.mcshop.basic.Service;

public class CodeChecker {
	
	private CodeVerifyEvent e;
	
	public CodeChecker(CodeVerifyEvent event){
		e = event;
	}
	public void checkCode(String code, Service service){
		CheckThread t = new CheckThread(code, service, e);
		t.start();
	}
}
