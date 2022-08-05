package invenpack;

import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;
import java.sql.*;


public class Register extends JFrame implements ActionListener {
	
	JLabel lblfname,lbllname,lblgender,lblemail,lblpass,lblphone,lblpic,lblpicfor,lblcount,lblstate;
	JTextField txtfname,txtlname,txtgender,txtemail,txtpass,txtphone,txtcount,txtstate,txtpath;
	JPasswordField pass;
	JButton btnsub,btnpicupload,btnlogin;
	JRadioButton rbmale,rbfemale,rbother;
	ButtonGroup btngrp;
	
	String filename = null;
	byte[] photo = null; 
	
	Register(){
		
		setSize(580,750);
		setTitle("Registration Form");
		setVisible(true);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblfname = new JLabel("First Name");
		lbllname = new JLabel("Last Name");
		lblgender = new JLabel("Gender");
		lblemail = new JLabel("Email");
		lblpass = new JLabel("Password");
		lblphone = new JLabel("Mobile No");
		lblpic = new JLabel("Picture");
		lblpicfor= new JLabel();
		lblcount = new JLabel("Country");
		lblstate = new JLabel("State");
		
		txtfname =  new JTextField();
		txtlname = new JTextField();
		txtgender = new JTextField();
		txtemail = new JTextField();
		txtpass = new JTextField();
		txtphone = new JTextField();
		txtcount = new JTextField();
		txtstate =new JTextField();
		txtpath = new JTextField();
		
		rbmale = new JRadioButton("Male");
		rbfemale = new JRadioButton("Female");
		rbother = new JRadioButton("Other");
		btngrp = new ButtonGroup();

		pass = new JPasswordField();

		btnsub = new JButton("Sign Up");
		btnlogin = new JButton("Log In");
		btnpicupload = new JButton("Upload");
		

		
		
		
		Border border = BorderFactory.createLineBorder(Color.darkGray,1);
		validate();
		add(lblfname);
		add(lbllname);
		add(lblgender);
		add(lblemail);
		add(lblpass);
		add(lblphone);
		add(lblpic);
		add(lblpicfor);
		add(lblcount);
		add(lblstate);
		add(txtfname);
		add(txtlname);
		add(txtgender);
		add(txtemail);
		add(txtpass);
		add(txtphone);
		add(txtcount);
		add(txtstate);
		add(txtpath);
		add(pass);
		add(btnsub);
		add(btnlogin);
		add(btnpicupload);
		add(rbmale);
		add(rbfemale);
		add(rbother);
		
		btngrp.add(rbmale);
		btngrp.add(rbfemale);
		btngrp.add(rbother);
		
		lblfname.setBounds(100,40,120,25);
		txtfname.setBounds(250,40,200,25);
		lbllname.setBounds(100,80,120,25);
		txtlname.setBounds(250,80,200,25);
		lblgender.setBounds(100,120,120,25);
		rbmale.setBounds(245,120,60,25);
		rbfemale.setBounds(310,120,80,25);
		rbother.setBounds(390,120,80,25);
		lblcount.setBounds(100,160,120,25);
		txtcount.setBounds(250,160,200,25);
		lblstate.setBounds(100,200,120,25);
		txtstate.setBounds(250,200,200,25);
		lblemail.setBounds(100,240,120,25);
		txtemail.setBounds(250,240,200,25);
		lblpass.setBounds(100,280,120,25);
		pass.setBounds(250,280,200,25);
		lblphone.setBounds(100,320,120,25);
		txtphone.setBounds(250,320,200,25);
		lblpic.setBounds(100,360,120,25);
		lblpicfor.setBounds(250,380,200,200);
		//jp.setBounds(450,150,500,500);
		btnpicupload.setBounds(100,400,90,35);
		txtpath.setBounds(100,470,150,25);
		btnlogin.setBounds(190,630,90,35);
		btnsub.setBounds(290,630,90,35);

		
		lblpicfor.setBorder(border);
		
		btnpicupload.addActionListener(this);
		btnsub.addActionListener(this);
		btnlogin.addActionListener(this);

	}
	
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnpicupload) {
			uploadFile();
		}else if(ae.getSource() == btnsub ) {
			storedData();
		}
		else {
			gotoLogin();
		}
		
	}
	
	void uploadFile(){
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select Image");
		chooser.showOpenDialog(null);
		File f = chooser.getSelectedFile();
		try {
			
			Image bi = ImageIO.read(f);
		
			lblpicfor.setIcon(new ImageIcon(bi.getScaledInstance(lblpicfor.getWidth(), lblpicfor.getHeight(), Image.SCALE_SMOOTH)));
			filename = f.getAbsolutePath();
			txtpath.setText(filename.replace('\\','/')); 
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			File image = new File(filename);
			FileInputStream fis = new FileInputStream(image);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buff = new byte[1024];
			for(int readNum; (readNum=fis.read(buff))!= -1;) {
				baos.write(buff,0,readNum);
				
			}
			fis.close();
			photo=baos.toByteArray();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
		
		
	}
	
	void storedData() {
		
		String fname, lname, gender=" ", email,country,state,phone;
		char []p = pass.getPassword();
		fname = txtfname.getText();
		lname = txtlname.getText();
		
		if(rbmale.isSelected())
			gender = "Male";
		else if(rbfemale.isSelected())
			gender = "Female";
		else if(rbother.isSelected())
			gender = "Other";

		country = txtcount.getText();
		state = txtstate.getText();
		email = txtemail.getText();
		String password = new String(p);
		phone = txtphone.getText();
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","oracle");
			
			PreparedStatement pst = cn.prepareStatement("insert into AJIT.logreg values (?,?,?,?,?,?,?,?,?)");
			pst.setString(1, fname);
			pst.setString(2, lname);
			pst.setString(3, gender);
			pst.setString(4, country);
			pst.setString(5, state);
			pst.setString(6, email);
			pst.setString(7, password);
			pst.setString(8, phone);
			pst.setBytes(9, photo);
			
			pst.executeUpdate();
			
			cn.close();
			
			JOptionPane.showMessageDialog(null, "Data Stored Successfully");

			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}
	
	void gotoLogin() {

		new Login();
		this.dispose();
		
	}
}
