package invenpack;

import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.sql.*;

public class AddItem extends JFrame implements ActionListener {
	JPanel jpmain;
	JLabel lblid,lblname,lbltype,lblqty,lblprice,lblpic;
	JTextField txtid,txtname,txttype,txtqty,txtprice,txtpicpath;
	JButton btnadd,btnpic,btngb;
	
	String picpath = null;
	byte[] img = null;
	
	public AddItem() {
		setSize(600,650);
		setLayout(null);
		setVisible(true);
		setTitle("Add New Items");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jpmain = new JPanel();
		jpmain.setLayout(null);
		add(jpmain);
		
		lblid = new JLabel("ID :");
		lblname = new JLabel("Name :");
		lbltype = new JLabel("Type :");
		lblqty = new JLabel("Quantity :");
		lblprice = new JLabel("Price :");
		
		txtid = new JTextField();
		txtname = new JTextField();
		txttype = new JTextField();
		txtqty = new JTextField();
		txtprice = new JTextField();
		txtpicpath = new JTextField();
		
		btnadd = new JButton("Add Item");
		btnpic = new JButton("Upload Picture");
		btngb = new JButton("Back To Home");
		
		jpmain.add(lblid);
		jpmain.add(lblname);
		jpmain.add(lbltype);
		jpmain.add(lblqty);
		jpmain.add(lblprice);
		
		jpmain.add(txtid);
		jpmain.add(txtname);
		jpmain.add(txttype);
		jpmain.add(txtqty);
		jpmain.add(txtprice);
		jpmain.add(txtpicpath);
		
		jpmain.add(btnadd);
		jpmain.add(btnpic);
		jpmain.add(btngb);
		
		jpmain.setBounds(0,0,600,650);
		lblid.setBounds(126,50,40,30);
		lblname.setBounds(101,120,55,30);
		lbltype.setBounds(107,190,55,30);
		lblqty.setBounds(80,260,70,30);
		lblprice.setBounds(105,330,60,30);
		
		txtid.setBounds(250,50,200,30);
		txtname.setBounds(250,120,200,30);
		txttype.setBounds(250,190,200,30);
		txtqty.setBounds(250,260,200,30);
		txtprice.setBounds(250,330,200,30);
		txtpicpath.setBounds(250,400,200,30);
		
		btnpic.setBounds(80,400,150,30);
		btnadd.setBounds(130,500,120,30);
		btngb.setBounds(290,500,150,30);
		
		btngb.addActionListener(this);
		btnadd.addActionListener(this);
		btnpic.addActionListener(this);
		
		//jpmain.setBackground(Color.black);
			
	}
	

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btngb) {
			gotoHome();	
		}
		else if(ae.getSource() == btnpic) {
			picAdd();
		}
		else if(ae.getSource() == btnadd) {
			addItem();
		}
		
	}
	void addItem() {
		String id = txtid.getText();
		String name = txtname.getText();
		String type = txttype.getText();
		int qty = Integer.parseInt(txtqty.getText());
		int price = Integer.parseInt(txtprice.getText());
		//System.out.println(qty);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			PreparedStatement pst = cn.prepareStatement("insert into AJIT.items values (?,?,?,?,?,?)");
			pst.setString(1,id);
			pst.setString(2,name);
			pst.setString(3,type);
			pst.setInt(4, qty);
			pst.setInt(5,price);
			pst.setBytes(6, img);
			
			pst.executeUpdate();
			cn.close();
			
			JOptionPane.showMessageDialog(null, "Youre Data Added Successfully..");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	void picAdd() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select Image");
		chooser.showOpenDialog(null);
		File f = chooser.getSelectedFile();
			
			//Image bi = ImageIO.read(f);
		
			
			picpath = f.getAbsolutePath();
			txtpicpath.setText(picpath.replace('\\','/')); 
		
		try {
			File image = new File(picpath);
			FileInputStream fis = new FileInputStream(image);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buff = new byte[1024];
			for(int readNum; (readNum=fis.read(buff))!= -1;) {
				baos.write(buff,0,readNum);
				
			}
			fis.close();
			img=baos.toByteArray();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
		
	}
	void gotoHome(){
		new Home();
		this.dispose();
	}
}
