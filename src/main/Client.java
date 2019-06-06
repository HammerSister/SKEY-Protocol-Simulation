package main;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Client {
	
	public static String work(String username, String key) throws Exception
	{
		DatagramSocket ds = new DatagramSocket(null);
		InetSocketAddress addr = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 3001);
		ds.bind(addr);
		String online = "Client log in.";
		DatagramPacket dp0 = new DatagramPacket(online.getBytes(),online.length(),
				InetAddress.getByName("127.0.0.1"),3000);
		ds.send(dp0);
		System.out.println(online);
		String sender = username + "splllit" + key;
		DatagramPacket dp1 = new DatagramPacket(sender.getBytes(), sender.length(), 
				InetAddress.getByName("127.0.0.1"), 3000);
		ds.send(dp1);
		System.out.println(sender + " 已发送");
		
		byte[] buf = new byte[1024];
		DatagramPacket dp2 = new DatagramPacket(buf,1024);
		ds.receive(dp2);
		System.out.println(new String(dp2.getData(),0,dp2.getLength()));
		return new String(dp2.getData(),0,dp2.getLength());
	}

	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		work("Alice", "-33");
	}

}
