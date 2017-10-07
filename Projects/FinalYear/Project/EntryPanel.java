package Project;
//import Project.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import MyControls.*;
import Project.*;
import java.sql.*;

/*class EntryDriver extends JFrame 
{
	EntryDriver() 
	{
		try
		{
			javax.swing.UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		add(new EntryPanel(this));
		setSize(500,500);
		setVisible(true);
	}

	public static void main(String args[])
	{
		new EntryDriver();
	}
}*/

public class EntryPanel extends JTabbedPane implements ChangeListener
{
	int selectedIndex;
	boolean changeAllow=true;
	static StudentInfoPanel student;
	static 	FamPanel parent;//ParentInfoPanel parents;
	static 	MarksPanel mark;//MarkInfoPanel marks;
	static 	CasteInfoPanel caste;
	JFrame obj;
	static int enroll;
	//JScrollPane jsp;
	EntryPanel(Container ref)
	{
		obj=(JFrame)ref;
		initializeAll();
		addPanels();
		addChangeListener(this);
		selectedIndex=0;
	}

	void initializeAll()
	{
		student=new StudentInfoPanel(obj);
		parent=new FamPanel();//new ParentInfoPanel();
		mark=new MarksPanel();//new MarkInfoPanel();
		caste=new CasteInfoPanel();
	}
	void addPanels()
	{
		addTab("Student",student);
		addTab("Parent",parent);
		addTab("Mark",mark);
		addTab("Caste",caste);
	}
	public void stateChanged(ChangeEvent ce)
	{
		check();
		if(changeAllow==true)
		{
			selectedIndex=getSelectedIndex();
		}
		else
			setSelectedIndex(selectedIndex);
	}
	
	public void check()
	{
			if(selectedIndex==0)
			{
					if(student.validateStudent())
						changeAllow=true;
					else
						changeAllow=false;
			}
			else if (selectedIndex==1)
			{
					if(parent.validateParent())
						changeAllow=true;
					else
						changeAllow=false;
			}
			else if (selectedIndex==2)
			{
					if(mark.validateMarks())
						changeAllow=true;
					else
						changeAllow=false;
			}
	}
	
	public static void insertAll()
	{
		System.out.println("Inserting started");
		parent.insertInDB();
		student.insertInDB();
		mark.insertInDB();
		//caste.insertInDB();
	}
	
	public Insets getInsets()
	{
		return new Insets(3,20,40,20);
	}
}