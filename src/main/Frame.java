package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Frame  extends JFrame{
	
	Font labelFont = new Font("微软雅黑",Font.PLAIN,36);
	Font sublabelFont = new Font("黑体",Font.PLAIN,22);
	Font subjectFont = new Font("宋体",Font.PLAIN,18);
	public Frame(){
		JFrame f = new JFrame("Skey客户端");
		f.setLayout(null);
		f.setSize(600,400);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 400);
		panel.setLayout(null);
		placeComponents(panel);
		f.add(panel);
		
		f.setVisible(true);
	}
	
	private void placeComponents(JPanel panel) {
		JRadioButton login = new JRadioButton("用户登录");
		JRadioButton signup = new JRadioButton("新建用户");
		login.setBounds(90, 50, 200, 50);
		signup.setBounds(320, 50, 200, 50);
		login.setFont(labelFont);
		signup.setFont(labelFont);
		ButtonGroup g = new ButtonGroup();
	    g.add(login);
	    g.add(signup);
	    panel.add(login);
	    panel.add(signup);
	    
	    JLabel username = new JLabel("用户名：");
	    username.setFont(sublabelFont);
	    username.setBounds(45, 135, 100, 50);
	    username.setVisible(false);
	    panel.add(username);
	    
	    JTextField usernameText = new JTextField(200);
	    usernameText.setFont(subjectFont);
	    usernameText.setBounds(150, 135, 380, 45);
	    usernameText.setVisible(false);
	    panel.add(usernameText);
	    
	    JLabel key = new JLabel("口   令：");
	    key.setFont(sublabelFont);
	    key.setBounds(45, 200, 100, 50);
	    key.setVisible(false);
	    panel.add(key);
	    
	    JTextField keyText = new JTextField(200);
	    keyText.setFont(subjectFont);
	    keyText.setBounds(150, 200, 380, 45);
	    keyText.setVisible(false);
	    panel.add(keyText);
	    
	    JButton l = new JButton("登录");
	    l.setFont(sublabelFont);
	    l.setBounds(45, 265, 484, 40);
	    l.setVisible(false);
	    panel.add(l);
	    
	    JLabel ts1 = new JLabel("");
	    ts1.setFont(subjectFont);
	    ts1.setBounds(50, 300, 480, 50);
	    ts1.setVisible(false);
	    panel.add(ts1);
	    
	    JLabel newusername = new JLabel("用户名：");
	    newusername.setFont(sublabelFont);
	    newusername.setBounds(45, 135, 100, 50);
	    newusername.setVisible(false);
	    panel.add(newusername);
	    JTextField newusernameText = new JTextField(200);
	    newusernameText.setFont(subjectFont);
	    newusernameText.setBounds(150, 135, 380, 45);
	    newusernameText.setVisible(false);
	    panel.add(newusernameText);
	    
	    JButton s = new JButton("创建");
	    s.setFont(sublabelFont);
	    s.setBounds(45, 200, 484, 40);
	    s.setVisible(false);
	    panel.add(s);
	    
	    JLabel ts2 = new JLabel(""); //别忘了检测是否用户名已存在，创建成功要给出文件路径
	    ts2.setFont(subjectFont);
	    ts2.setBounds(50, 260, 480, 50);
	    ts2.setVisible(false);
	    panel.add(ts2);
	    
	    login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				newusername.setVisible(false);
				newusernameText.setVisible(false);
				s.setVisible(false);
				ts2.setVisible(false);
				
				username.setVisible(true);
				usernameText.setVisible(true);
				l.setVisible(true);
				key.setVisible(true);
				keyText.setVisible(true);
				ts1.setVisible(true);
			}
		});
	    signup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				username.setVisible(false);
				usernameText.setVisible(false);
				l.setVisible(false);
				key.setVisible(false);
				keyText.setVisible(false);
				ts1.setVisible(false);
				
				newusername.setVisible(true);
				newusernameText.setVisible(true);
				s.setVisible(true);
				ts2.setVisible(true);
			}
		});
	    l.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				try {
					String result = Client.work(usernameText.getText(), keyText.getText());
					ts1.setText(result);
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	    s.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				try {
					String result = Client.work(newusernameText.getText(), "888");
					ts2.setText(result);
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new Frame();
	}

}
