package invenpack;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SearchItem extends JFrame implements ActionListener, ItemListener{

	JPanel jpmain;
	JLabel lbltype;
	JComboBox c1;
	//JTextField addtype,rmtype;
	JButton btngb;
	String s1[] = {"Select","Accessories","Mobile","Laptop","Car","Bike"};
	DefaultTableModel model = new DefaultTableModel();
    JTable tbl1 = new JTable(model);
	
	public SearchItem() {
		setSize(780,550);
		setLayout(null);
		setVisible(true);
		setTitle("Search By Type");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jpmain = new JPanel();
		jpmain.setLayout(null);
		add(jpmain);
		
		c1 = new JComboBox(s1);
		lbltype = new JLabel("Select Type :");
		
		//addtype = new JTextField();
		//rmtype = new JTextField();
		
		
		//btnadd = new JButton("Add");
		//btnremove = new JButton("Remove");
		btngb = new JButton("Back To Home");
		
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
		
		jpmain.add(lbltype);

		jpmain.add(c1);
		
		//jpmain.add(addtype);
		//jpmain.add(rmtype);
		
		//jpmain.add(btnadd);
		//jpmain.add(btnremove);
		jpmain.add(btngb);
		jpmain.add(pg);
		
		jpmain.setBounds(0,0,780,550);
		lbltype.setBounds(50,50,150,30);
		
		c1.setBounds(166,50,150,25);
		//addtype.setBounds(320,50,120,25);
		//rmtype.setBounds(510,50,120,25);
		
		//btnadd.setBounds(440,50,69,25);
		//btnremove.setBounds(630,50,90,25);
		btngb.setBounds(310,480,150,30);
		pg.setBounds(130,100,520,380);
		
//		pg.setBackground(Color.green);
		
		btngb.addActionListener(this);
		c1.addItemListener(this);
		//btnadd.addActionListener(this);
		
		//jpmain.setBackground(Color.black);
			
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btngb) {
			gotoHome();
		}
	}	
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == c1) {
			goTypeSearch();
		}
	}
	void gotoHome(){
		new Home();
		this.dispose();
	}
	
	void goTypeSearch() {
		String type = c1.getSelectedItem().toString();
		if(type.equals("Select")) {
			JOptionPane.showMessageDialog(null,"Please Select Item Type First!!");
		}
		else {
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "oracle");
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM AJIT.items where type=?");
            pstm.setString(1, type);
            ResultSet rs = pstm.executeQuery();
            model.setRowCount(0);
            while(rs.next()){
            	model.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getBytes(6)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	}
	
//	public static void main(String[] args) {
//		new SearchItem();
//	}
	

}
