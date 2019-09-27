package com.webserver.core;
import java.io.InputStream;
/**
 * 该线程负责处理一个客户端的连接
 */
import java.net.Socket;

public class ClientHandler implements Runnable{
	private Socket socket;
	public ClientHandler(Socket socket) {
		this.socket = socket;
	}
	public void run() {
		try {
			InputStream in = socket.getInputStream();
			/*
			 * 测试读取一行字符串，若连续读取的两个字符
			 * 分别为CR，LF就可以停止，然后将读取到
			 * 的字符组成一个字符串
			 */
			StringBuilder builder = new StringBuilder();
			
			int d =-1;
			//c1标示上次读取到的字符，c2表示本次读取到的字符
			char c1 = 'a',c2 ='a';
			while((d=in.read())!=-1) {
				c2 = (char)d;
				//判断是否连续读取到了CR，LF
				if(c1 ==13 && c2==10) {
					break;
				}
				builder.append(c2);
				c1=c2;
			}
			String line = builder.toString().trim();
			System.out.println(line);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
