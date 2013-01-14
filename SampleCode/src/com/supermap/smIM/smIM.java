package com.supermap.smIM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class smIM{
	public static void main(String args[]) {
		String strName="PC.Java";
		String strExchange = "超图班车";

		if( args.length > 1 )
		{
			strName=args[0];
			strExchange=args[1];
		}
		else
		{
			System.out.println( "缺失参数，参数格式为： 用户名  群组(频道)名" );   // 
			return ;
		}
        
		// 消息服务相关的来啦
		SMBDelegate smbDelegate = new SMBDelegate();
		smbDelegate.setSender(strName); 			// 发送者
		smbDelegate.setInExchange(strExchange);	 	// 接收频道
		smbDelegate.setTargetExchange(strExchange); // 目的频道
		
		System.out.println("检测网络中...");
		
		smbDelegate.Connect("guest", "guest");// 目前只能用guest/guest连接，以后会用strUserName/strPassword来验证用户的账户
		
		try{ 
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in) );  

			String strText = null;
			while( true )
			{
				if( smbDelegate.getStatus() > 0 )
				{
					System.out.println("请输入要发送的信息:"); 
					strText = in.readLine();
					
					System.out.println( "已发送>--" + strText );  
					
					smbDelegate.PublishText( strText ); // 发送文字消息
				}
				else
				{
					try{
						Thread.sleep(20);
					} catch (InterruptedException e1) {
					}							
				}
			}
		}catch(IOException e) { 
			e.printStackTrace(); 
		}
	}
}
