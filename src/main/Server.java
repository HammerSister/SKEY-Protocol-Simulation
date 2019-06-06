package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

public class Server {
	
	public static String[] readFile(String filePath) {
        File file = new File(filePath);
        String[] k = new String[100];
        int q = 0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
//	                System.out.println(tempString);
            	k[q] = tempString;
            	q++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        file.delete();
        return k;
    }
		
	public static void createFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            //System.out.println("文件已存在");
        } else {
            try {
                File fileParent = file.getParentFile();
                if (fileParent != null) {
                    if (!fileParent.exists()) {
                        fileParent.mkdirs();
                    }
                }
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
   
    public static void writeFileByFileWriter(String filePath, String content) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(filePath), true);
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	public static void work() throws Exception {
		DatagramSocket ds = new DatagramSocket(3000);
		while(true)
		{
			byte[] buf0 = new byte[1024];
			DatagramPacket dp0 = new DatagramPacket(buf0,1024);
			ds.receive(dp0);
			System.out.println(new String(dp0.getData(),0,dp0.getLength()));
			
			byte[] buf1 = new byte[1024];
			DatagramPacket dp1 = new DatagramPacket(buf1,1024);
			ds.receive(dp1);
			try {
				String username = new String(dp1.getData(),0,dp1.getLength()).split("splllit")[0];
				String key = new String(dp1.getData(),0,dp1.getLength()).split("splllit")[1];
				String[] k = new String[100];
				String filePath = "G:/java-photon/workplace/Skey/src/main/keys_" + username + ".txt";
				if(key.equals("888"))
				{
					Random r = new Random();
					char t1 = (char)(r.nextInt(26) + 97);
					char t2 = (char)(r.nextInt(26) + 97);
					int A = ("" + t1 + t2).hashCode();
					
					for(int q = 0; q < 100; q++)
					{
						k[99 - q] = Integer.toString(A);
						A = Integer.toString(A).hashCode();
					}
					createFile(filePath);
					for(int q = 0; q < 100; q++)
					{
						writeFileByFileWriter(filePath, k[q] + "\n");
					}
					SimpleDateFormat sdf = new SimpleDateFormat();
			        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
			        Date date = new Date();
					String log = "用户：" + username + "   创建成功   " + sdf.format(date);
					String logPath = "G:/java-photon/workplace/Skey/src/main/log.txt";
					writeFileByFileWriter(logPath, log + "\n");
					String result = "Creare Successfully.";
					DatagramPacket dp2 = new DatagramPacket(result.getBytes(),result.length(),
							dp1.getAddress(),dp1.getPort());
					ds.send(dp2);
					System.out.println(result);
				}
				else
				{
					System.out.println("收到信息：\n用户名：" + username + "\n密钥：" + key);
					k = readFile(filePath);
					Boolean flag = true;
					String result = "";
					for(int q = 0; q < 100; q++)
					{
						if(!k[q].equals("none"))
						{
							if(k[q].equals(key))
							{
								k[q] = "none";
								result += "Login Successfully.";
								SimpleDateFormat sdf = new SimpleDateFormat();
						        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
						        Date date = new Date();
								String log = "用户：" + username + "   第" + (q + 1) + "次登录   " + sdf.format(date);
								String logPath = "G:/java-photon/workplace/Skey/src/main/log.txt";
								writeFileByFileWriter(logPath, log + "\n");
								flag = false;
							}
						}
					}
					if(flag)
					{
						result += "Wrong Key.";
						SimpleDateFormat sdf = new SimpleDateFormat();
				        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
				        Date date = new Date();
						String log = "用户：" + username + "   口令输入错误   " + sdf.format(date);
						String logPath = "G:/java-photon/workplace/Skey/src/main/log.txt";
						writeFileByFileWriter(logPath, log + "\n");
					}
					createFile(filePath);
					for(int q = 0; q < 100; q++)
					{
						writeFileByFileWriter(filePath, k[q] + "\n");
					}
					DatagramPacket dp2 = new DatagramPacket(result.getBytes(),result.length(),
							dp1.getAddress(),dp1.getPort());
					ds.send(dp2);
					System.out.println(result);
				}
				
				
				
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("Client log out.\n");
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		work();
//		Random r = new Random();
//		char t1 = (char)(r.nextInt(26) + 97);
//		char t2 = (char)(r.nextInt(26) + 97);
	}

}
