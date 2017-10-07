package Project;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import de.javasoft.plaf.synthetica.*;
import java.util.*;
import java.sql.*;

public class PreviewPanel extends JDialog implements ActionListener,TableModelListener
{
	JScrollPane scrollPane;
	Vector tableColumns;
	Vector tableData;
	JTable previewTable;
	JButton edit,done;
	JPanel bottom;
	DefaultTableModel defaultModel;
	boolean editFlag=false;
	JLabel previewLabel;
	int updatedRows[]=new int[100];
	String enroll=" ",name="Dhananjay",dob=" ",adharCard=" ",ph=" ",address=" ",fathersName=" ",fathersPh=" ",mothersName=" ",mothersPh=" ",tenth=" ",dsy=" ",caste=" ";
	ResultSet rs[],tempres;
	//static Frame parent=(Frame)SwingUtilities.getAncestorOfClass(Frame.class,this);
	
	public PreviewPanel()
	{
		super((Frame)SwingUtilities.getAncestorOfClass(Frame.class,new Button()),true);
		setLayout(new BorderLayout());
		initializeAll();
		makeUI();
		setSize(1200,450);
		setVisible(true);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				try{
				if (updatedRows.length!=0)
				{
					System.out.println("Updated");
					for (int i=0;i<updatedRows.length-1;i++)
					{
						if (updatedRows[i]==1)
						{
							System.out.println("Dhananjay Pratik");
							refillDataAtIndex(i);
							updateToDatabase();
						}
						//System.out.println("Updatedrows[i]="+updatedRows[i]);
					}
				}
				}catch(Exception e){System.out.println("Exception in closing window"+e);}
			}
			});
	}
	
	void refillDataAtIndex(int index)
	{
		try {
		System.out.println("Refilling");
		System.out.println("Body");
		Vector demo=(Vector)tableData.elementAt(index);
		Enumeration e=demo.elements();
		enroll=(String)e.nextElement();
		System.out.println("Enroll "+enroll);
		name=(String)e.nextElement();
		System.out.println("Name "+name);
		dob=(String)e.nextElement();
		System.out.println("Dob "+dob);
		adharCard=(String)e.nextElement();
		System.out.println("adhar "+adharCard);
		//ph=(String)e.nextElement();
		System.out.println("Phne "+ph);
		//address=(String)e.nextElement();
		System.out.println("address "+address);
		fathersName=(String)e.nextElement();
		fathersPh=(String)e.nextElement();
		System.out.println("fathersph "+fathersPh);
		mothersName=(String)e.nextElement();
		System.out.println("mothersName "+mothersName);
		mothersPh=(String)e.nextElement();
		System.out.println("mothersPh "+mothersPh);
		tenth=(String)e.nextElement();
		System.out.println("tenth "+tenth);
		//dsy=(String)e.nextElement();
		System.out.println("dsy "+dsy);
		//caste=(String)e.nextElement();
		System.out.println("caste "+caste);
		System.out.println("jdfhkdjfhf");
		}catch(Exception e){System.out.println("Project"+e);}
	}
	
	void updateToDatabase()
	{
		//update all the fields in database where enroll ment number is equal to enroll;exception was because  was accessing the fields which i have removed form th
		//table (ph,address,dsy,caste)
		//Jeev de	//enroll="",name="Dhananjay",dob="",adharCard="",ph="",address="",fathersName="",fathersPh="",mothersName="",mothersPh="",tenth="",dsy="",caste=""
		try
		{
			System.out.println("1");
			Connection c=DriverManager.getConnection("jdbc:odbc:StudentDB");
			System.out.println("2");
			PreparedStatement ps1=c.prepareStatement("Update Student set FirstName=?,DOB=?,Adhar=? where RegNo=?");
			System.out.println("3");
			System.out.println("Update Guardian set FatherPhoneNumber=?,MotherPhoneNumber=?,FathersName=? where RegNo=?");
			PreparedStatement ps2=c.prepareStatement("Update Guardian set FatherPhoneNumber=?,MotherPhoneNumber=?,FathersName=?,MothersName=? where RegNo=?");    
			ps1.setString(1,name);
			System.out.println("5");
			ps1.setString(2,dob);
			System.out.println("6");
			ps1.setString(3,adharCard);
			System.out.println("7");
			ps1.setString(4,enroll);
			
			ps2.setString(1,fathersPh);
			ps2.setString(2,mothersPh);
			ps2.setString(3,fathersName);
			ps2.setString(4,mothersName);
			ps2.setString(5,enroll);
			
			
			
			/*try{
			ps2.setString(5,"1");
			}catch(Exception e){System.out.println(e);}
			System.out.println("After p2 1");*/
			
			ps1.executeUpdate();
			System.out.println("14");
			System.out.println("SUCCESSFUL");
			ps2.executeUpdate();
			System.out.println("15");
		}
		catch(Exception se){System.out.println("Hello world"+se);}
	}
	
	void initializeAll()
	{
		for (int i=0;i<100;i++)
			updatedRows[i]=0;
		System.out.println("Initialize all");
		rs=new ResultSet[4];
		tableColumns=new Vector();
		tableData=new Vector();
		addColumns();
		addRows();
		defaultModel=new DefaultTableModel(tableData,tableColumns){
			public boolean isCellEditable(int row, int column)
			{
					if (!editFlag)
					{
						return false;//This causes all cells to be not editable
					}
					else
						return true;
			}	
		};
		defaultModel.addTableModelListener(this);
		previewTable=new JTable(defaultModel);
		//previewTable.setModel(defaultModel);
		edit=new JButton("EDIT");
		done=new JButton("DONE");
		bottom=new JPanel();
		scrollPane=new JScrollPane(previewTable);
	}
	
	void addColumns()
	{
		System.out.println("Add columns");
		tableColumns.add("Enroll No");
		tableColumns.add("Name");
		tableColumns.add("DOB");
		tableColumns.add("Adhar-Card NO");
		//tableColumns.add("Ph");
		//tableColumns.add("Address");
		tableColumns.add("Father's Name");
		tableColumns.add("Father's Ph");
		tableColumns.add("Mother's Name");
		tableColumns.add("Mother's Ph");
		tableColumns.add("10th");
		//tableColumns.add("DSY");
		//tableColumns.add("Caste");
	}
	
	void addRows()
	{
		System.out.println("Adding rows");
		try
		{
			retriveResultSet();
			int cou=getCount();
			for(int i=0;i<cou;i++)
			{
				retriveData();
				addData();
			}
		}
		catch(Exception e)
		{
			System.out.println(e+"addRows");
		}
	}
	
	int getCount()
	{
		int count=0;
		try
		{
		Connection con=DriverManager.getConnection("jdbc:odbc:StudentDB");
		PreparedStatement ps=con.prepareStatement("SELECT * FROM Student");
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			String s=rs.getString("RegNo");
			System.out.println("Yes");
			count++;
		}
		System.out.println("Count ="+count);
		}
		catch(Exception e)
		{
			System.out.println(e+"getCount");
		}
		return count;
	}
	
	public void retriveResultSet()
	{
		System.out.println("Retrieving result set");
		try
		{
			Connection c=DriverManager.getConnection("jdbc:odbc:StudentDB");
			PreparedStatement ps1=c.prepareStatement("SELECT * FROM Student");
			rs[0]=ps1.executeQuery();
			PreparedStatement ps2=c.prepareStatement("SELECT * FROM Marks");
			rs[1]=ps2.executeQuery();
			PreparedStatement ps3=c.prepareStatement("SELECT * FROM Work");
			rs[2]=ps3.executeQuery();
			PreparedStatement ps4=c.prepareStatement("SELECT * FROM Guardian");
			rs[3]=ps4.executeQuery();
		}
		catch(Exception e)
		{
			System.out.println(e+"Retrive Result");
		}
	}
	
	void retriveData()
	{	
		//select count (*) as totalcount;
		System.out.println("retrieve data");
		try
		{
		rs[0].next();
		rs[1].next();
		rs[2].next();
		rs[3].next();
		enroll=rs[0].getString("RegNo");
		System.out.println("REGNO"+enroll);
		name=((rs[0].getString("FirstName"))+" "+(rs[0].getString("MiddleName"))+" "+(rs[0].getString("LastName")));
		System.out.println("NAME"+name);
		dob=rs[0].getString("DOB");
		System.out.println("DOB"+dob);
		adharCard=rs[0].getString("Adhar");
		System.out.println("ADHAR"+adharCard);
		ph=" ";
		System.out.println(" "+ph);
		address=" ";
		System.out.println("ADDRESS"+address);
		fathersName=rs[3].getString("FathersName");
		System.out.println("FATHERSNAME"+fathersName);
		fathersPh=rs[3].getString("FatherPhoneNumber");
		System.out.println("FATHERSNAME"+fathersPh);
		mothersName=rs[3].getString("MothersName");
		System.out.println("MOTHERSNAME"+mothersName);
		mothersPh=rs[3].getString("MotherPhoneNumber");
		System.out.println("MOTHERSNAME"+mothersPh);
		tenth=""+((((Integer.parseInt(rs[1].getString("English")))+(Integer.parseInt(rs[1].getString("Math")))+(Integer.parseInt(rs[1].getString("Science"))))*100)/300)+"%";
		System.out.println("Tenth"+tenth);
		dsy=" ";
		System.out.println("Direct Second year "+dsy);
		caste=" ";
		System.out.println("Caste"+caste);
		}
		catch(Exception e)
		{
			System.out.println(e+" retriveData exception");
		}
	}
	
	void addData()
	{
		System.out.println("Adding data");
		Vector demo=new Vector();
		demo.add(enroll);
		System.out.println(enroll);
		demo.add(name);
		System.out.println(name);
		demo.add(dob);
		System.out.println(dob);
		demo.add(adharCard);
		System.out.println(adharCard);
		//demo.add(ph);
		//demo.add(address);
		demo.add(fathersName);
		System.out.println(fathersName);
		demo.add(fathersPh);
		System.out.println(fathersPh);
		demo.add(mothersName);
		System.out.println(mothersName);
		demo.add(mothersPh);
		System.out.println(mothersPh);
		demo.add(tenth);
		System.out.println(tenth);
		//demo.add(dsy);
		//demo.add(caste);
		tableData.add(demo);
	}
	
	

	
	
	void makeUI()
	{
		add(scrollPane,BorderLayout.CENTER);
		add(previewLabel=new JLabel("Preview"),BorderLayout.NORTH);
		add(bottom,BorderLayout.SOUTH);
		bottom.setLayout(new FlowLayout(FlowLayout.LEFT));
		bottom.add(done);
		bottom.add(edit);
		edit.addActionListener(this);
		done.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if (edit==(JButton)ae.getSource())
		{	
			editFlag=true;
			previewLabel.setText("EDITING");
		}
		else
		{
			editFlag=false;
			previewLabel.setText("Preview");
		}
	}

	public void tableChanged(TableModelEvent tme)
	{
		System.out.println("Updated rows:"+tme.getLastRow());
		updatedRows[tme.getLastRow()]=1;
	}
	
}

class Dhananjay 
{
	public static void main(String args[])
	{
		new PreviewPanel();
	}
}
