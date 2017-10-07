package Project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
//import Project.controls.*;
import MyControls.*;
import java.util.*;
import java.sql.*;
import Project.*;
import java.io.*;

interface StudentInfoConstants
{
	String firstname="FIRST NAME:";
	String middleName="MIDDLE NAME:";
	String lastName="LAST NAME:";
	String dob="DATA OF BIRTH:";
	String adhar="ADHAR-CARD NO:";
	String phone="PH";
	String address="ADDRESS:";
	String flat="FLAT/ROOM";
	String building="BUILDING";
	String street="STREET";
	String landMark="LANDMARK";
	String city="CITY";
	String pin="PINCODE";
	String next="NEXT";
	String back="BACK";
	String day="DD";
	String month="MM";
	String year="YYYY";
}

public class StudentInfoPanel extends JPanel implements StudentInfoConstants,FocusListener,ActionListener
{
	JLabel lblFirstName,lblMiddleName,lblLastName,lblDob,lblAdhar,lblPhone,lblAddress,lblFlat,lblBuilding,lblStreet,lblLandMark,lblCity,lblPin,lblDay,lblMonth,lblYear;
	MyTextField firstNameTxt,middleNameTxt,lastNameTxt,flatTxt,buildingTxt,streetTxt,landMarkTxt,cityTxt;
	NumericTextField phoneTxt,adharTxt,pinTxt;
	JButton nextBt,backBt;
	JSpinner spDay,spMonth,spYear;
	SpinnerNumberModel spmD,spmM;
	SpinnerListModel spmY;
	JFrame obj;
	String yearArr[]=new String[50];
	int enrollNum;	
	JButton jb=new JButton("Upload your Photo");
	JButton jb2=new JButton("Upload your AdharCard");
	JLabel image=new JLabel(" ");
	JLabel emptyLabel=new JLabel(" ");
	JPanel FormPanel=new JPanel();
	JPanel jbPanel,imagePanel;
	public StudentInfoPanel(JFrame ref)
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch (Exception e)
		{
			System.out.println("Class nai hai");
		}
		ref=obj;
		setLayout(new BorderLayout());
		FormPanel.setLayout(new GridLayout(19,3,10,2));
		initializeAll();
		createAndMakePanels();
		setBackground(new Color(204,204,204));
	}
	void initializeAll()
	{
		//NAME PANEL
		lblFirstName=new JLabel(firstname);
		lblMiddleName=new JLabel(middleName);
		lblLastName=new JLabel(lastName);
		firstNameTxt=new MyTextField("",10,Color.BLACK);
		firstNameTxt.addFocusListener(this);
		middleNameTxt=new MyTextField("",10,Color.BLACK);
		middleNameTxt.addFocusListener(this);
		lastNameTxt=new MyTextField("",10,Color.BLACK);
		lastNameTxt.addFocusListener(this);

		jb.addActionListener(this);
		jb2.addActionListener(this);
		
		//DOB PANEL
		lblDob=new JLabel(dob);
		lblDay=new JLabel(day);
		lblMonth=new JLabel(month);
		lblYear=new JLabel(year);
		spmD=new SpinnerNumberModel(1,1,31,1);
		spmM=new SpinnerNumberModel(1,1,12,1);
		for (int i=1990,j=0;i<2017;i++,j++)
			yearArr[j]=new Integer(i).toString();
		spmY=new SpinnerListModel(yearArr);
		spDay=new JSpinner(spmD);
		spMonth=new JSpinner(spmM);
		spYear=new JSpinner(spmY);

		//ADHAR PANEL
		adharTxt=new NumericTextField("",10,16);
		adharTxt.addFocusListener(this);
		lblAdhar=new JLabel(adhar);

		//PHONE PANEL
		lblPhone=new JLabel(phone);
		phoneTxt=new NumericTextField("",10,10);
		phoneTxt.addFocusListener(this);

		//ADDRESS PANE
		lblAddress=new JLabel(address);
		lblFlat=new JLabel(flat);
		lblBuilding=new JLabel(building);
		lblStreet=new JLabel(street);
		lblLandMark=new JLabel(landMark);
		lblCity=new JLabel(city);
		lblPin=new JLabel(pin);
		flatTxt=new MyTextField("",10,Color.BLACK);
		flatTxt.addFocusListener(this);		
		buildingTxt=new MyTextField("",10,Color.BLACK);
		buildingTxt.addFocusListener(this);
		streetTxt=new MyTextField("",10,Color.BLACK);
		streetTxt.addFocusListener(this);
		landMarkTxt=new MyTextField("",10,Color.BLACK);
		landMarkTxt.addFocusListener(this);
		cityTxt=new MyTextField("",10,Color.BLACK);
		cityTxt.addFocusListener(this);		
		pinTxt=new NumericTextField("",10,6);
		pinTxt.addFocusListener(this);		
	}
	public void focusGained(FocusEvent fe)
	{
		MyTextField mtf12=(MyTextField)fe.getSource();
		mtf12.changeColor(Color.BLUE);
	}
	public void focusLost(FocusEvent fe)
	{
		MyTextField mtf12=(MyTextField)fe.getSource();
		if(mtf12.getText().length()==0)
			mtf12.changeColor(Color.RED);
		else
			mtf12.changeColor(Color.GREEN);			
	}
	void createAndMakePanels()
	{		
	
		
		jbPanel=new JPanel(new BorderLayout());
		jbPanel.add(jb,BorderLayout.WEST);
		jbPanel.add(jb2,BorderLayout.EAST);
		
		imagePanel=new JPanel(new BorderLayout());
		imagePanel.add(image,BorderLayout.WEST);
		imagePanel.add(emptyLabel,BorderLayout.EAST);
		
		add(imagePanel,BorderLayout.NORTH);
		add(jbPanel,BorderLayout.SOUTH);
		
		FormPanel.add(lblLastName);
		FormPanel.add(lblFirstName);
		FormPanel.add(lblMiddleName);
		FormPanel.add(lastNameTxt);
		FormPanel.add(firstNameTxt);
		FormPanel.add(middleNameTxt);

		FormPanel.add(new JLabel(new ImageIcon("Line.png")));
		FormPanel.add(new JLabel(new ImageIcon("Line.png")));
		FormPanel.add(new JLabel(new ImageIcon("Line.png")));

		FormPanel.add(lblDob);
		FormPanel.add(new JLabel());
		FormPanel.add(new JLabel());
		FormPanel.add(lblDay);
		FormPanel.add(lblMonth);
		FormPanel.add(lblYear);
		FormPanel.add(spDay);
		((JSpinner.DefaultEditor) spDay.getEditor()).getTextField().setEditable(false);
		FormPanel.add(spMonth);
		((JSpinner.DefaultEditor) spMonth.getEditor()).getTextField().setEditable(false);
		FormPanel.add(spYear);
		((JSpinner.DefaultEditor) spYear.getEditor()).getTextField().setEditable(false);
		FormPanel.add(new JLabel(new ImageIcon("Line.png")));
		FormPanel.add(new JLabel(new ImageIcon("Line.png")));
		FormPanel.add(new JLabel(new ImageIcon("Line.png")));
		
		FormPanel.add(lblAdhar);
		FormPanel.add(new JLabel());
		FormPanel.add(new JLabel());
		FormPanel.add(adharTxt);
		FormPanel.add(new JLabel());
		FormPanel.add(new JLabel());

		FormPanel.add(new JLabel(new ImageIcon("Line.png")));
		FormPanel.add(new JLabel(new ImageIcon("Line.png")));
		FormPanel.add(new JLabel(new ImageIcon("Line.png")));

		FormPanel.add(lblPhone);
		FormPanel.add(new JLabel());
		FormPanel.add(new JLabel());
		FormPanel.add(phoneTxt);
		FormPanel.add(new JLabel());
		FormPanel.add(new JLabel());

		FormPanel.add(new JLabel(new ImageIcon("Line.png")));
		FormPanel.add(new JLabel(new ImageIcon("Line.png")));
		FormPanel.add(new JLabel(new ImageIcon("Line.png")));


		FormPanel.add(lblAddress);
		FormPanel.add(new JLabel());
		FormPanel.add(new JLabel());
		FormPanel.add(lblFlat);
		FormPanel.add(lblBuilding);
		FormPanel.add(lblStreet);
		FormPanel.add(flatTxt);
		FormPanel.add(buildingTxt);
		FormPanel.add(streetTxt);
		FormPanel.add(lblLandMark);
		FormPanel.add(lblCity);
		FormPanel.add(lblPin);
		FormPanel.add(landMarkTxt);
		FormPanel.add(cityTxt);
		FormPanel.add(pinTxt);
		add(FormPanel,BorderLayout.CENTER);
	}
	public Insets getInsets()
	{
		return new Insets(10,10,10,10);
	}
	public boolean validateStudent()
	{
		if(firstNameTxt.getText().length()>0 && middleNameTxt.getText().length()>0 && lastNameTxt.getText().length()>0 && adharTxt.getText().length()>0 && phoneTxt.getText().length()>0 && flatTxt.getText().length()>0 && buildingTxt.getText().length()>0 && streetTxt.getText().length()>0 && landMarkTxt.getText().length()>0 && cityTxt.getText().length()>0 && pinTxt.getText().length()>0&&image.getIcon()!=null&&emptyLabel.getIcon()!=null)
		{
		return true;}
		else
		{
		return false;}
	}
	public void insertInDB()
	{
		System.out.println("Adding in Stduent");
		try
		{
			Connection con=DriverManager.getConnection("jdbc:odbc:StudentDB");
		PreparedStatement ps=con.prepareStatement("insert into Student values (?,?,?,?,?,?)");
		ps.setString(1,""+EntryPanel.enroll);
		ps.setString(2,lastNameTxt.getText());
		ps.setString(3,firstNameTxt.getText());
		ps.setString(4,middleNameTxt.getText());
		String s1=(spDay.getValue().toString())+"/"+(spMonth.getValue().toString())+"/"+(spYear.getValue().toString());
		ps.setString(5,s1);
		ps.setString(6,adharTxt.getText());	
		ps.executeUpdate();
		ps.close();
		con.close();
		}
		catch (Exception s)
		{
		}
		finally
		{
		}
				System.out.println("Added in Stduent");
	}
	public void setEnrollNum(int num)
	{
		enrollNum=num;
	}
	public void actionPerformed(ActionEvent ae)
	{
		System.out.println("Action Performed");
		try
		{
			FileDialog fd=new FileDialog((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class,this));
			fd.setSize(300,300);
			fd.setVisible(true);
			File f=new File(fd.getFile());
			String filePath=f.getPath();
			ImageResizer.resize(filePath,100,100);
			if(ae.getSource()==jb)
			{
			image.setIcon(new ImageIcon("Demo"+f.getName()));
			image.setBorder(BorderFactory.createEtchedBorder(new Color(111,111,111),new Color(11,11,11)));
			}
			else
			{
			System.out.println("abcd");
			emptyLabel.setIcon(new ImageIcon("Demo"+f.getName()));	
			}
		}
		catch(Exception e)
		{
			
		}
	}
}