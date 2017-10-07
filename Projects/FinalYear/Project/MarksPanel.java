package Project;
import java.awt.*;
import java.awt.event.*;
import MyControls.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.io.*;
import Project.*;

interface LineImageResource
{
	ImageIcon lineGraphics=new ImageIcon("C:\\Users\\admin\\Downloads\\Line.jpg");
}
public class MarksPanel extends JPanel implements ItemListener,LineImageResource,ActionListener,FocusListener
{
	JLabel lineLabel=new JLabel(lineGraphics);
	JLabel radioButtonStatus,lblEng,lblMath,lblSci,image;
	JRadioButton isTenth,isDSY;
	ButtonGroup bg;
	JPanel tenthPanel,dsyPanel,txtcontainer;
	JPanel tenthdsy,RadPanel;
	NumericTextField txtSci,txtMath,txtEng;
	JButton tenthSheet,dsysheet;
	CardLayout cd=new CardLayout();
	String status=null;
	public MarksPanel()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch (Exception e)
		{
			System.out.println("Class nai hai");
		}
		setLayout(new BorderLayout());
		initComponents();
		//showMainPanel();
	}
	private void initComponents()
	{
		txtSci=new NumericTextField("",10,2);
		txtSci.addFocusListener(this);
		txtMath=new NumericTextField("",10,2);
		txtMath.addFocusListener(this);
		txtEng=new NumericTextField("",10,2);
		txtEng.addFocusListener(this);
		
		lblEng=new JLabel("English");
		lblMath=new JLabel("Maths");
		lblSci=new JLabel("Science");
		
		tenthSheet=new JButton("Add Marksheet");
		tenthSheet.addActionListener(this);
		dsysheet=new JButton("Add Marksheet");
		dsysheet.addActionListener(this);
		bg=new ButtonGroup();
		
		radioButtonStatus=new JLabel("Select appropriate course");
		isTenth=new JRadioButton("10th?",true);
		isDSY=new JRadioButton("DSY?",false);
		isTenth.addItemListener(this);
		isDSY.addItemListener(this);
		bg.add(isTenth);
		bg.add(isDSY);
		tenthPanel=new JPanel(new BorderLayout());
		txtcontainer=new JPanel();
		dsyPanel=new JPanel();
		txtcontainer.setLayout(new GridLayout(7,1));
		
		isDSY.setLayout(new FlowLayout());
		
		txtcontainer.add(lblSci);
		txtcontainer.add(txtSci);
		txtcontainer.add(lblMath);
		txtcontainer.add(txtMath);
		txtcontainer.add(lblEng);
		txtcontainer.add(txtEng);
		
		tenthPanel.add(txtcontainer,BorderLayout.NORTH);
		tenthPanel.add(tenthSheet,BorderLayout.SOUTH);
		
		dsyPanel.add(dsysheet);
		
		tenthdsy=new JPanel();
		tenthdsy.setLayout(cd);
		tenthdsy.add(tenthPanel,"Tenth");
		tenthdsy.add(dsyPanel,"HSC");
		//tenthdsy.setVisible(false);
		
		RadPanel=new JPanel();
		RadPanel.setLayout(new FlowLayout());
		RadPanel.add(isTenth);
		RadPanel.add(isDSY);
		
		status="Tenth";
		
		addAllPanels();
	}
	private void addAllPanels()
	{
		add(radioButtonStatus,BorderLayout.NORTH);
		add(RadPanel,BorderLayout.CENTER);
		add(tenthdsy,BorderLayout.SOUTH);
	}
	public void itemStateChanged(ItemEvent ie)
	{
		JRadioButton jb=(JRadioButton)ie.getSource();
		if(isTenth.isSelected())
		{
			status="Tenth";
			cd.show(tenthdsy,"Tenth");
		}
		else if(isDSY.isSelected())
		{
			status="HSC";
			cd.show(tenthdsy,"HSC");
		}
	}
	public Insets getInsets()
	{
		return new Insets(50,50,50,50);
	}
	
	public void actionPerformed(ActionEvent a)
	{
			
		JButton curr=(JButton)a.getSource();
			try
			{
				FileDialog fd=new FileDialog((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class,this));
				fd.setSize(300,300);
				fd.setVisible(true);
				File f=new File(fd.getFile());
				String filePath=f.getPath();
				ImageResizer.resize(filePath,100,100);
				curr.setIcon(new ImageIcon("Demo"+f.getName()));
			}
			catch(Exception e)
			{
				System.out.println(e.getStackTrace());
			}
			
		}
	public void focusGained(FocusEvent fe)
	{
	}
	public void focusLost(FocusEvent fe)
	{			
	}
	public boolean validateMarks()
	{
		if(txtEng.getText().length()>0 && txtSci.getText().length()>0 && txtMath.getText().length()>0)
			return true;
		else
			return false;
	}
	public void insertInDB()
	{
		System.out.println("Adding data in Marks");
		try
		{
		Connection con=DriverManager.getConnection("jdbc:odbc:StudentDB");
		PreparedStatement ps=con.prepareStatement("insert into Marks values (?,?,?,?,?)");
		ps.setString(1,""+EntryPanel.enroll);
		ps.setString(2,txtMath.getText());
		ps.setString(3,txtSci.getText());
		ps.setString(4,""+isDSY.isSelected());
		ps.setString(5,txtEng.getText());	
		ps.executeUpdate();
		ps.close();
		con.close();
		}
		catch (Exception e)
		{
		}
		finally
		{
		}
		System.out.println("Added Data in marks");
	}
}
class MarksDriver extends Frame
{
	MarksDriver()
	{
		setLayout(new BorderLayout());
		setBounds(0,0,400,400);
		add(new MarksPanel(),BorderLayout.CENTER);
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new MarksDriver();
	}
}