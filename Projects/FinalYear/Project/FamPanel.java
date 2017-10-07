package Project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.border.*;
import MyControls.*;
import java.sql.*;

interface FamConstants 
{
	String FANAME="Father's Name";
	String MANAME="Mother's Name";
	String FMID="Father's Middle Name";
	String NMID="Middle Name";
	String SUR="Surname";
	String OCCUPATION="Occupation";
	String FATHERWORKADD="Father's occupation Address";
	String OCCUTITLE="Occupation Details";
	String GUARDIANTITLE="Guardian Details";
	String FOCCUNUM="Father's Office Number";
	String FNUM="Father's Number";
	String MNUM="Mother's Number";
}

public class FamPanel extends JPanel implements ActionListener ,FamConstants,FocusListener,ItemListener
{
	JPanel FatherPanel,MotherPanel,BPanel;
	JLabel Oculbl,FatherOccuLabel;
	MyTextField txtfname,txtmname,txtoccupation,txtfsname,txtfmname,mothersname;
	NumericTextField fNum,mnum,offNum,mothersnum;
	JButton next,back;
	JCheckBox working;
	Border bf,tbf,bm,tbm;
	public FamPanel()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch (Exception e)
		{
			System.out.println("Class nai hai");
		}
		manageGuardianPanel();
	}
	public void focusGained(FocusEvent fe)
	{
		MyTextField temp=(MyTextField)fe.getSource();
		temp.changeColor(Color.BLUE);
	}
	public void focusLost(FocusEvent fe)
	{
		//System.out.println("Focus Lost");
		MyTextField temp=(MyTextField)fe.getSource();
		if(isEmpty(temp)==false)
			temp.changeColor(Color.GREEN);
		else
		{
			//temp.requestFocus();
			temp.changeColor(Color.RED);
		}
	}
	public boolean isEmpty(MyTextField mtf)
	{
		if(mtf.getText().length()==0)
			return true;
		else
			return false;
	}
	public void manageGuardianPanel()
	{
		setLayout(new BorderLayout());
		bf=BorderFactory.createEtchedBorder();
		tbf=BorderFactory.createTitledBorder(bf,"Guardain-Father");
		bm=BorderFactory.createEtchedBorder();
		tbm=BorderFactory.createTitledBorder(bm,"Guardain-Mother");
		initAll();
	}
	public boolean validateParent()
	{
			if(working.isSelected())
			{
				if(txtfmname.getBorderColor()==Color.GREEN && txtfname.getBorderColor()==Color.GREEN && txtfsname.getBorderColor()==Color.GREEN && txtoccupation.getBorderColor()==Color.GREEN && mothersname.getBorderColor()==Color.GREEN && mothersnum.getBorderColor()==Color.GREEN && offNum.getBorderColor()==Color.GREEN )
				{
				//JOptionPane.showMessageDialog(this,"Successfully Validated","Validated",JOptionPane.INFORMATION_MESSAGE);
				return true;
				}
				else
				{
					//JOptionPane.showMessageDialog(this,"Fill All The Fields","Error in Validation",JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			else 
			{
				if(txtfmname.getBorderColor()==Color.GREEN && txtfname.getBorderColor()==Color.GREEN && txtfsname.getBorderColor()==Color.GREEN /*&&txtoccupation.getBorderColor()==Color.GREEN*/ && mothersname.getBorderColor()==Color.GREEN && mothersnum.getBorderColor()==Color.GREEN /*&&offNum.getBorderColor()==Color.GREEN*/ )
				{
				//JOptionPane.showMessageDialog(this,"Successfully Validated","Validated",JOptionPane.INFORMATION_MESSAGE);
				return true;
				}
				else
				{
					//JOptionPane.showMessageDialog(this,"Fill All The Fields","Error in Validation",JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
	}
	public void insertInDB()
	{
				System.out.println("Adding in Guardian and Work");
		try
		{
		Connection con=DriverManager.getConnection("jdbc:odbc:StudentDB");
		PreparedStatement ps=con.prepareStatement("insert into Guardian values (?,?,?,?,?)");
		ps.setString(1,""+EntryPanel.enroll);
		ps.setString(2,txtfname.getText()+" "+txtfmname.getText()+" "+txtfsname.getText());
		ps.setString(3,mothersname.getText()+" "+txtfname.getText()+" "+txtfsname.getText());
		ps.setString(4,mothersnum.getText());
		ps.setString(5,fNum.getText());
		ps.executeUpdate();
		PreparedStatement ps2=con.prepareStatement("insert into Work values (?,?,?)");
		ps2.setString(1,""+EntryPanel.enroll);
		System.out.println("Work Panel enroll "+EntryPanel.enroll);
		ps2.setString(2,txtoccupation.getText());
		ps2.setString(3,offNum.getText());
		ps2.executeUpdate();
		ps.close();
		ps2.close();
		}
		catch (Exception e)
		{
		}
				System.out.println("Added in Work and Guardian");
	}
	public void initAll()
	{
		mothersname=new MyTextField("",10,Color.BLACK);
		mothersnum=new NumericTextField("",10,10);
		MotherPanel=new JPanel();
		MotherPanel.setLayout(new GridLayout(4,1));
		MotherPanel.add(new JLabel("Mother's Name"));
		MotherPanel.add(mothersname);
		MotherPanel.add(new JLabel("Mother's Ph Number"));
		MotherPanel.add(mothersnum);
		Oculbl=new JLabel("Occupation");
		FatherOccuLabel=new JLabel("Father's occupation");
		next=new JButton("Next");
		back=new JButton("Back");
		fNum=new NumericTextField("",10,10);
		fNum.addFocusListener(this);
		offNum=new NumericTextField("",10,10);
		//offNum.addFocusListener(this);
		working=new JCheckBox("Working?",false);
		next.addActionListener(this);
		back.addActionListener(this);
		working.addItemListener(this);
		txtfmname=new MyTextField("",10,Color.BLACK);
		txtfname=new MyTextField("",10,Color.BLACK);
		txtfsname=new MyTextField("",10,Color.BLACK);
		txtmname=new MyTextField("",10,Color.BLACK);
		txtoccupation=new MyTextField("",10,Color.BLACK);
		txtfmname.addFocusListener(this);
		txtfname.addFocusListener(this);
		txtfsname.addFocusListener(this);
		txtmname.addFocusListener(this);
		mothersname.addFocusListener(this);
		mothersnum.addFocusListener(this);
		//txtoccupation.addFocusListener(this);
		FatherPanel=new JPanel();
		FatherPanel.setLayout(new GridLayout(13,1));
		BPanel=new JPanel(new BorderLayout());
		//BPanel.add(next,BorderLayout.EAST);
		//BPanel.add(back,BorderLayout.WEST);
		FatherPanel.add(new Label(SUR));
		FatherPanel.add(txtfsname);
		FatherPanel.add(new Label(FANAME));
		FatherPanel.add(txtfname);
		FatherPanel.add(new Label(FMID));
		FatherPanel.add(txtfmname);
		FatherPanel.add(new Label("Father's Number"));
		FatherPanel.add(fNum);
		FatherPanel.add(working);
		FatherPanel.add(Oculbl);
		Oculbl.setVisible(false);
		FatherPanel.add(txtoccupation);
		txtoccupation.setVisible(false);
		FatherPanel.add(FatherOccuLabel);
		FatherOccuLabel.setVisible(false);
		FatherPanel.add(offNum);
		offNum.setVisible(false);
		FatherPanel.setBorder(tbf);
		MotherPanel.setBorder(tbm);
		add(FatherPanel,BorderLayout.NORTH);
		add(MotherPanel,BorderLayout.CENTER);
		add(BPanel,BorderLayout.SOUTH);
	}
	public void itemStateChanged(ItemEvent ie)
	{
		JCheckBox jb=(JCheckBox)ie.getSource();
		if(jb.isSelected())
		{
			Oculbl.setVisible(true);
			txtoccupation.setVisible(true);
			FatherOccuLabel.setVisible(true);
			offNum.setVisible(true);
			txtoccupation.addFocusListener(this);
			offNum.addFocusListener(this);
		}
		else
		{
			Oculbl.setVisible(false);
			txtoccupation.setVisible(false);
			FatherOccuLabel.setVisible(false);
			offNum.setVisible(false);
			txtoccupation.removeFocusListener(this);
			offNum.removeFocusListener(this);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==next)
		{
			if(working.isSelected())
			{
				if(txtfmname.getBorderColor()==Color.GREEN && txtfname.getBorderColor()==Color.GREEN && txtfsname.getBorderColor()==Color.GREEN && txtoccupation.getBorderColor()==Color.GREEN && mothersname.getBorderColor()==Color.GREEN && mothersnum.getBorderColor()==Color.GREEN && offNum.getBorderColor()==Color.GREEN )
				JOptionPane.showMessageDialog(this,"Successfully Validated","Validated",JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(this,"Fill All The Fields","Error in Validation",JOptionPane.ERROR_MESSAGE);
			}
			else 
			{
				if(txtfmname.getBorderColor()==Color.GREEN && txtfname.getBorderColor()==Color.GREEN && txtfsname.getBorderColor()==Color.GREEN /*&&txtoccupation.getBorderColor()==Color.GREEN*/ && mothersname.getBorderColor()==Color.GREEN && mothersnum.getBorderColor()==Color.GREEN /*&&offNum.getBorderColor()==Color.GREEN*/ )
				JOptionPane.showMessageDialog(this,"Successfully Validated","Validated",JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(this,"Fill All The Fields","Error in Validation",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public Insets getInsets()
	{
		return new Insets(30,30,30,30);
	}
}
class FamDriver extends Frame
{
	FamDriver()
	{
		setLayout(new GridLayout(1,1));
		setBounds(0,0,1000,1000);
		add(new FamPanel());
		setVisible(true);
	}
	public static void main(String[] args)
	{
		new FamDriver();
	}
}