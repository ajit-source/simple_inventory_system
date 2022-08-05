package invenpack;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;

import java.sql.*;

public class Home extends JFrame implements MouseListener, ActionListener{
	JLabel lbltitle,lblid,lblname,lbltype,lblqty,lblprice,lblpic,lblgetnm,lblgettp,lblgetqty,lblgetprc,lblgetpic;
	JTextField txtgetid;
	JPanel jpmain,jptop,jpver;
	JButton btnmenu,btnaddi,btnupdtei,btndlti,btnsrch,btnlogout,btnshowdata,btnview;
	//String[] columNames = {"ID", "NAME", "TYPE", "QUANTITY", "PRICE", "PICTURE"};
	Border empty = BorderFactory.createEmptyBorder();
	
	DefaultTableModel model = new DefaultTableModel();
    JTable tbl1 = new JTable(model);

	Home(){
		setSize(1366,768);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//new FlowLayout()
		tbl1 = new JTable();
		jpmain = new JPanel();
		jptop = new JPanel();
		lbltitle = new JLabel("Simple Inventory System");
		lblname = new JLabel();
		jpver = new JPanel();
		
		lblid = new JLabel("ID :");
		lblname = new JLabel("Name :");
		lbltype = new JLabel("Type :");
		lblqty = new JLabel("Quantity :");
		lblprice = new JLabel("Price Per :");
		lblpic = new JLabel("Picture :");
		
		lblgetnm = new JLabel();
		lblgettp = new JLabel();
		lblgetqty = new JLabel();
		lblgetprc = new JLabel();
		lblgetpic = new JLabel();
		
		txtgetid = new JTextField();
		
		
		model = new DefaultTableModel();
		//cnt.setLayout(new FlowLayout(FlowLayout.LEFT));
        model.addColumn("Id");
        model.addColumn("Name");
        model.addColumn("Type");
        model.addColumn("Quantity");
        model.addColumn("Price");
        model.addColumn("Picture");
      
		
		tbl1 = new JTable(model);
		JScrollPane pg = new JScrollPane(tbl1);
        
        //pg = new JScrollPane(tbl1);
       // cnt.add(pg);
		
		jpmain.setLayout(null);
		jpver.setLayout(null);
		jptop.setLayout(null);
		
		add(jpmain);
		
		btnmenu = new JButton("Menu");
		btnaddi = new JButton("Add Item");
		btnupdtei =new JButton("Update Item");
		btndlti = new JButton("Delete Item");
		btnsrch = new JButton("Search Items");
		btnlogout = new JButton("Logout");
		btnshowdata = new JButton("Show Data");
		btnview = new JButton("View");
		
		btnmenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnaddi.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnupdtei.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btndlti.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnsrch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnlogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnshowdata.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnview.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnmenu.setFocusPainted(false);
		btnaddi.setFocusPainted(false);
		btnupdtei.setFocusPainted(false);
		btndlti.setFocusPainted(false);
		btnsrch.setFocusPainted(false);
		btnlogout.setFocusPainted(false);
		btnshowdata.setFocusPainted(false);
		btnview.setFocusPainted(false);
		
		btnmenu.setBorder(empty);
		btnaddi.setBorder(empty);
		btnupdtei.setBorder(empty);
		btndlti.setBorder(empty);
		btnsrch.setBorder(empty);
		btnlogout.setBorder(empty);
		btnshowdata.setBorder(empty);
		btnview.setBorder(empty);
		
		jpmain.setBounds(0,0,1366,768);
		
		lblid.setBounds(126,130,40,30);
		lblname.setBounds(101,200,55,30);
		lbltype.setBounds(107,270,55,30);
		lblqty.setBounds(80,350,70,30);
		lblprice.setBounds(79,420,75,30);
		lblpic.setBounds(92,490,60,30);
		
		txtgetid.setBounds(250,130,200,30);
		lblgetnm.setBounds(250,200,200,30);
		lblgettp.setBounds(250,270,200,30);
		lblgetqty.setBounds(250,350,200,30);
		lblgetprc.setBounds(250,420,200,30);
		lblgetpic.setBounds(250,490,200,200);
		
		jpver.setBounds(30,50,0,0);
		btnaddi.setBounds(50,20,100,30);
		btnupdtei.setBounds(50,60,100,30);
		btndlti.setBounds(50,100,100,30);
		btnsrch.setBounds(50,140,100,30);
		btnlogout.setBounds(50,180,100,30);
		btnshowdata.setBounds(900,580,120,30);
		btnview.setBounds(470,130,60,30);
		jptop.setBounds(0,0,1366,65);
		btnmenu.setBounds(30,20,80,30);
		lbltitle.setBounds(460,20,430,30);
		pg.setBounds(650,125,600,450);
		
		jpver.add(btnaddi);
		jpver.add(btnupdtei);
		jpver.add(btndlti);
		jpver.add(btnsrch);
		jpver.add(btnlogout);
		jpver.add(lbltitle);
		
		jpmain.add(lblid);
		jpmain.add(lblname);
		jpmain.add(lbltype);
		jpmain.add(lblqty);
		jpmain.add(lblprice);
		jpmain.add(lblpic);
		
		jpmain.add(txtgetid);
		jpmain.add(lblgetnm);
		jpmain.add(lblgettp);
		jpmain.add(lblgetqty);
		jpmain.add(lblgetprc);
		jpmain.add(lblgetpic);
		
		jpmain.add(jptop);
		jpmain.add(jpver);
		jpmain.add(pg);
		jpmain.add(btnshowdata);
		jpmain.add(btnview);
		
		
		jptop.add(btnmenu);
		jptop.add(lbltitle);
		
		//jpmain.setBackground(Color.ORANGE);	
		//tbl1.setBackground(Color.green);
		lbltitle.setForeground(Color.WHITE);
		lbltitle.setFont(new Font("TimesRoman",Font.BOLD,28));
		btnmenu.setForeground(new Color(255,255,255));
		btnmenu.setBackground(new Color(0,0,0));
		jptop.setBackground(new Color(51,51,51));
		jpver.setBackground(new Color(51,51,51));
		btnaddi.setBackground(Color.black);
		btnaddi.setBackground(new Color(0,0,0));
		btnaddi.setForeground(new Color(255,255,255));
		btnupdtei.setBackground(Color.black);
		btnupdtei.setBackground(new Color(0,0,0));
		btnupdtei.setForeground(new Color(255,255,255));
		btndlti.setBackground(Color.black);
		btndlti.setBackground(new Color(0,0,0));
		btndlti.setForeground(new Color(255,255,255));
		btnsrch.setBackground(Color.black);
		btnsrch.setBackground(new Color(0,0,0));
		btnsrch.setForeground(new Color(255,255,255));
		btnlogout.setBackground(Color.black);
		btnlogout.setBackground(new Color(0,0,0));
		btnlogout.setForeground(new Color(255,255,255));
		
		btnmenu.addMouseListener(this);
		jpver.addMouseListener(this);
		btnaddi.addMouseListener(this);
		btnupdtei.addMouseListener(this);
		btndlti.addMouseListener(this);
		btnsrch.addMouseListener(this);
		btnlogout.addMouseListener(this);
		//tbl1.addMouseListener(this);
		
		btnaddi.addActionListener(this);
		btnupdtei.addActionListener(this);
		btndlti.addActionListener(this);
		btnshowdata.addActionListener(this);
		btnview.addActionListener(this);
		btnlogout.addActionListener(this);
		btnsrch.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnaddi) {
			gotoAddItem();
		}
		else if(ae.getSource() == btnupdtei) {
			gotoUpdateItem();
		}
		else if(ae.getSource() == btndlti) {
			gotoDeleteItem();
		}
		else if(ae.getSource() == btnshowdata) {
			addDataTbl();
		}
		else if(ae.getSource() == btnview) {
			viewData();
		}
		else if(ae.getSource() == btnlogout) {
			logOut();
		}
		else if(ae.getSource() == btnsrch) {
			gotoSearch();
		}
	}
	
	void gotoAddItem() {
		new AddItem();
		this.dispose();
	}
	
	void gotoUpdateItem() {
		new UpdateItem();
		this.dispose();
	}
	
	void gotoDeleteItem() {
		new DeleteItem();
		this.dispose();
	}
	void logOut() {
		new Login();
		this.dispose();
	}
	void gotoSearch() {
		new SearchItem();
		this.dispose();
	}
	
	void addDataTbl() {
		
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "oracle");
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM AJIT.items");
            ResultSet rs = pstm.executeQuery();
            model.setRowCount(0);
            while(rs.next()){
            	model.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getBytes(6)});
            }
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null,e);
        }
		//TableModel tm ;
		
		//DefaultTableModel(tm.getData1(),tm.getColumnNames());
	}
	
	void viewData() {
		String id = txtgetid.getText();
		BufferedImage bufimg = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","oracle");
			PreparedStatement pst = cn.prepareStatement("select * from AJIT.items where id=?");
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				InputStream in = rs.getBinaryStream("photo");
				bufimg = ImageIO.read(in);
				lblgetnm.setText(rs.getString(2));
				lblgettp.setText(rs.getString(3));
				lblgetqty.setText(rs.getString(4));
				lblgetprc.setText(rs.getString(5));
				lblgetpic.setIcon(new ImageIcon(bufimg.getScaledInstance(lblgetpic.getWidth(), lblgetpic.getHeight(), Image.SCALE_SMOOTH)));
			}
			else {
				JOptionPane.showMessageDialog(null,"Error");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
	}

	

//	public static void main(String[] args) {
//		new Home();
//	}
	
	public void mouseClicked(MouseEvent e) {
		
	}


	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}


	public void mouseEntered(MouseEvent e) {
		jpver.setSize(200, 220);
		
	}
	public void mouseExited(MouseEvent e) {
		jpver.setSize(0, 0);
		
	}
		
}
