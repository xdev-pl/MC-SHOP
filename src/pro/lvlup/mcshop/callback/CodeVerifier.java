package pro.lvlup.mcshop.callback;

import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;

import pro.lvlup.mcshop.basic.Service;

public class CodeVerifier implements CodeVerifyEvent {

	private CodeChecker cc;
	private Service s;
	private Player p;
	private SignChangeEvent e;
	
	public CodeVerifier(String code, Service service, Player player, SignChangeEvent event){
		cc = new CodeChecker(this);
		s = service;
		p = player;
		e = event;
		cc.checkCode(code, service);
	}
	
	@Override
	public void codeVerifyEvent(CheckThread t) {
		s.verifyCode(t.getResult(), p, e);
	}
}
