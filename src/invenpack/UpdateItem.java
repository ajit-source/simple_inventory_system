package invenpack;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.*;
import java.awt.event.*;

public class UpdateItem extends JFrame implements ActionListener {
	
	JPanel jpmain;
	JLabel lblid,lblname,lbltype,lblqty,lblprice,lblpic;
	JTextField txtid,txtname,txttype,txtqty,txtprice,txtpicpath;
	JButton btnupdte,btnpic,btngb;
	String picpath=null;
	byte[] img=null;
	
	public UpdateItem() {
		setSize(600,650);
		setLayout(null);
		setVisible(true);
		setTitle("Update Items");
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
		
		btnupdte = new JButton("Update Item");
		btnpic = new JButton("Change Picture");
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
		
		jpmain.add(btnupdte);
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
		btnupdte.setBounds(100,500,150,30);
		btngb.setBounds(290,500,150,30);
		
		btngb.addActionListener(this);
		btnpic.addActionListener(this);
		btnupdte.addActionListener(this);
		
		//jpmain.setBackground(Color.black);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btngb) {
			gotoHome();
		}
		else if(ae.getSource() == btnpic) {
			uploadPic();
		}
		else if(ae.getSource() == btnupdte) {
			updateData();
		}
	}
	void uploadPic() {
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
	void updateData() {
		String id = txtid.getText();
		String name = txtname.getText();
		String type = txttype.getText();
		int qty = Integer.parseInt(txtqty.getText());
		int price = Integer.parseInt(txtprice.getText());
		System.out.println(qty);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			PreparedStatement pst = cn.prepareStatement("update AJIT.items set name=?,type=?,qty=?,price=?,photo=?" +"where id=?");
			//
			pst.setString(1,name);
			pst.setString(2,type);
			pst.setInt(3, qty);
			pst.setInt(4,price);
			pst.setBytes(5, img);
			pst.setString(6,id);
			
			pst.executeUpdate();
			cn.close();
			
			JOptionPane.showMessageDialog(null, "Youre Data Updated Successfully..");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
	}
	
	void gotoHome(){
		new Home();
		this.dispose();
	}

}
