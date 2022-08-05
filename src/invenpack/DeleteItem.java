package invenpack;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class DeleteItem extends JFrame implements ActionListener{

	JPanel jpmain;
	JLabel lblid,lblname;
	JTextField txtid;
	JButton btndlt,btngb;
	
	public DeleteItem() {
		setSize(550,350);
		setLayout(null);
		setVisible(true);
		setTitle("Delete Items");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jpmain = new JPanel();
		jpmain.setLayout(null);
		add(jpmain);
		
		lblid = new JLabel("ID :");
		lblname = new JLabel();
		
		txtid = new JTextField();
		//txtname = new JTextField();
		
		
		btndlt = new JButton("Delete Item");
		btngb = new JButton("Back To Home");
		
		jpmain.add(lblid);
		jpmain.add(lblname);
		
		jpmain.add(txtid);
		//jpmain.add(txtname);
		
		jpmain.add(btndlt);
		jpmain.add(btngb);
		
		jpmain.setBounds(0,0,550,350);
		lblid.setBounds(126,50,40,30);
		lblname.setBounds(240,140,120,30);
		
		txtid.setBounds(250,50,200,30);
		//txtname.setBounds(250,120,200,30);
		
		btndlt.setBounds(120,200,120,30);
		btngb.setBounds(270,200,150,30);
		
		btngb.addActionListener(this);
		btndlt.addActionListener(this);
		
		//jpmain.setBackground(Color.black);
			
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btngb) {
			gotoHome();
		}
		else if(ae.getSource() == btndlt) {
			deleteData();
		}
	}	
	void gotoHome(){
		new Home();
		this.dispose();
	}
	
	void deleteData() {
		String id = txtid.getText();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","oracle");
			PreparedStatement pst = cn.prepareStatement("delete from AJIT.items where id=?");
			pst.setString(1, id);
			pst.executeUpdate();
			lblname.setText("Deleted "+id);
			//JOptionPane.showMessageDialog(null,"Item Deleted Successfully.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

}
