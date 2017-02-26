package pro.lvlup.mcshop.callback;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import pro.lvlup.mcshop.basic.Service;
import pro.lvlup.mcshop.managers.Config;

public class CheckThread extends Thread {
	
	private String code;
	private Service service;
	private CodeVerifyEvent event;
	private int result = 2;
	
	public CheckThread(String c, Service s, CodeVerifyEvent e){
		code = c;
		service = s;
		event = e;
	}
	
	public void run(){
		try {
			URL url = new URL("https://lvlup.pro/api/checksms?id=" + Config.USER$ID + "&code=" + code + "&number=" + service.getSmsNumber() + "&desc=" + Config.SERVICE$DESCRIPTION);
			StringBuilder string = new StringBuilder();
			URLConnection urlConnection = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				string.append(inputLine);
			}
			in.close();
			result = string.toString().equals("{\"valid\":1}") ? 1 : 0;
		} catch (Exception e) {
			e.printStackTrace();
			result = -1;
		}
		event.codeVerifyEvent(this);
	}
	
	public int getResult(){
		return result;
	}
}
