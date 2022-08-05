package invenpack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.*;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;

public class Login extends JFrame implements ActionListener{
	JLabel l2,l3;
	JTextField tf1;
	JButton btnlog,btnreg;
	JPasswordField ps;
	
	public Login() {
		setTitle("Login Form");
		setVisible(true);
		setLayout(null);
		setSize(630,370);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		l2 = new JLabel("Email ");
		l3 = new JLabel("Password ");
		
		tf1 = new JTextField();
		
		ps = new JPasswordField();
		
		btnlog = new JButton("Log In");
		btnreg = new JButton("Sign Up");
		
		
		
		add(l2);
		add(l3);
		add(tf1);
		add(ps);
		add(btnlog);
		add(btnreg);
		
		//l1.setBounds(200,30,200,30);
		l2.setBounds(150,90,100,30);
		l3.setBounds(150,150,100,30);
		tf1.setBounds(300,90,200,25);
		ps.setBounds(300,150,200,25);
		btnreg.setBounds(170,210,120,35);
		btnlog.setBounds(350,210,120,35);
		
		btnlog.addActionListener(this);
		btnreg.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnlog) {
			showData();
			}
		else{
			gotoRegister();
		}
	
	}
	
	public void showData() {
		String str1 = tf1.getText();
		char []c = ps.getPassword();
		String str2 = new String(c);
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","oracle");
			
			PreparedStatement st = cn.prepareStatement("select fname, photo from AJIT.logreg where email=? and password=?");
			st.setString(1,str1);
			st.setString(2,str2);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				
				this.dispose();
				new Home();
				//DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/mm/dd HH:mm:ss");
				//LocalDateTime now = LocalDateTime.now();
				//lbltime.setText("Date & Time :"+dt.format(now));
			}
			else {
				JOptionPane.showMessageDialog(null,"invalid email or password please try again!!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void gotoRegister() {
		
		new Register();
		this.dispose();
		
	}
	public static void main(String[] args) {
		new Login();
	}
}
