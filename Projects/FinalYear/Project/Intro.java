package Project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import MyControls.*;
import de.javasoft.plaf.synthetica.*;
import Project.*;
import java.sql.*;

public class Intro extends JPanel implements ActionListener
{
	JLabel mainLabel;
	JPanel mainPanel,optionPanel,tabbedPanel,viewPanel,buttonPanel,viewsub,viewPanelButton,viewsubContainer,detailsPanel,detailsButtonsPanel,detailsPanelContents,detailsPanelContents2,detailsPanelContentsContainer,txts,mainLabelandbuttonPanel,studentlogin,teacherlogin,studentlogincontainer,teacherlogincontainer,goback1,goback2;
	CardLayout cl,c2,c3,c4,c5;
	JRadioButton view,enroll;
	ButtonGroup bg;
	JButton next,viewPanelButton1,viewPanelButton2,detailsPanelEdit,detailsPanelBack,b[],GOs,GOt,backs,backt;
	MyTextField txtChooseEnrollNum;
	MyTextField teacherID,studentEnroll;
	String radioButtonClickStatus="";
	Frame f;
	String status="";
	JScrollPane scrollPane;
	
	public Intro()
	{
		initComponents();
		setupPanels();
		addComponents();
		//setLayout(new GridLayout(5,1));
	}
	
	public void initComponents()
	{
		goback1=new JPanel();
		goback2=new JPanel();
		
		teacherlogin=new JPanel();
		studentlogin=new JPanel();
		
		teacherlogincontainer=new JPanel();
		studentlogincontainer=new JPanel();
		
		c4=new CardLayout();
		c5=new CardLayout();
		
		mainLabelandbuttonPanel=new JPanel();
		mainLabelandbuttonPanel.setLayout(new GridLayout(2,1));
		txts=new JPanel();
		studentEnroll=new MyTextField("",10,Color.BLACK);
		studentEnroll.addFocusListener(this);
		
		teacherID=new MyTextField("",10,Color.BLACK);
		studentEnroll.addActionListener(this);
		c3=new CardLayout();
		
		b=new JButton[2];
		b[0]=new JButton("Yes");
		b[1]=new JButton("No");
		GOs=new JButton("GO");
		backs=new JButton("Back");
		GOt=new JButton("GO");
		backt=new JButton("Back");
		GOs.addActionListener(this);
		backs.addActionListener(this);
		GOt.addActionListener(this);
		backt.addActionListener(this);
		b[0].addActionListener(this);
		b[1].addActionListener(this);
		
		detailsPanelContentsContainer=new JPanel();
		detailsPanelContentsContainer.setLayout(c3);
		
		detailsPanelContents=new JPanel();
		
		detailsPanelBack=new JButton("Back");
		detailsPanelEdit=new JButton("Edit");
		
		detailsPanelBack.addActionListener(this);
		detailsPanelEdit.addActionListener(this);
		
		detailsPanel=new JPanel();
		detailsButtonsPanel=new JPanel();
		
		viewsub=new JPanel();
		viewsubContainer=new JPanel();
		
		txtChooseEnrollNum=new MyTextField("",10,Color.BLACK);
		txtChooseEnrollNum.addFocusListener(this);
		viewPanelButton1=new JButton("Next");
		viewPanelButton2=new JButton("Back");
		viewPanelButton1.addActionListener(this);
		viewPanelButton2.addActionListener(this);
		
		next=new JButton("Next");
		next.addActionListener(this);
		
		bg=new ButtonGroup();
		
		mainPanel=new JPanel();
		optionPanel=new JPanel();
		tabbedPanel=new JPanel();
		viewPanel=new JPanel();
		viewPanelButton=new JPanel();
		buttonPanel=new JPanel();
		
		view=new JRadioButton("Student",false);
		enroll=new JRadioButton("Teacher",false);
		bg.add(view);
		bg.add(enroll);
		
		view.addItemListener(this);
		enroll.addItemListener(this);
		
		mainLabel=new JLabel("Login as");
	}
	
	public void setupPanels()
	{
		goback1.setLayout(new BorderLayout());
		goback2.setLayout(new BorderLayout());
		
		studentlogincontainer.setLayout(c3);
		teacherlogincontainer.setLayout(c4);
		
		studentlogin.setLayout(new GridLayout(3,1,70,70));
		teacherlogin.setLayout(new GridLayout(3,1,70,70));
		
		detailsPanelContents=new JPanel();
		detailsPanelContents.setLayout(new GridLayout(4,1));
		
		detailsPanelContents2=new JPanel();
		detailsPanelContents2.setLayout(new GridLayout(1,1));
		
		detailsPanel.setLayout(new GridLayout(2,0));
		detailsButtonsPanel.setLayout(new FlowLayout());
		
		c2=new CardLayout();
		viewsubContainer.setLayout(c2);
		
		buttonPanel.setLayout(new FlowLayout());
		cl=new CardLayout();
		
		mainPanel.setLayout(cl);
		
		optionPanel.setLayout(new BorderLayout());
		
		tabbedPanel.setLayout(new BorderLayout());
		viewPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		viewsub.setLayout(new GridLayout(4,1,0,40));
		viewPanelButton.setLayout(new BorderLayout());
		txts.setLayout(new GridLayout(2,1));
	}
	public void addComponents()
	{
		
		goback1.add(GOs,BorderLayout.EAST);
		goback1.add(backs,BorderLayout.WEST);
		
		goback2.add(GOt,BorderLayout.EAST);
		goback2.add(backt,BorderLayout.WEST);
		
		studentlogin.add(new JLabel("Enter the Enrollment Number"));
		studentlogin.add(studentEnroll);
		studentlogin.add(goback1);
	
			
		teacherlogin.add(new JLabel("Enter the teacherID"));
		teacherlogin.add(teacherID);
		teacherlogin.add(goback2);
		
		studentlogincontainer.add(studentlogin,"Example1");
		teacherlogincontainer.add(teacherlogin,"Example2");

		
		buttonPanel.add(view);
		buttonPanel.add(enroll);
		
		mainLabelandbuttonPanel.add(mainLabel);
		mainLabelandbuttonPanel.add(buttonPanel);
		
		optionPanel.add(mainLabelandbuttonPanel,BorderLayout.NORTH);
		//optionPanel.add(buttonPanel,BorderLayout.CENTER);
		
		
		//making the next and back buttons of viewsub panel
		viewPanelButton.add(viewPanelButton1,BorderLayout.WEST);
		viewPanelButton.add(viewPanelButton2,BorderLayout.EAST);
		
		//adding in view panel
		viewsub.add(new JLabel("Enter the roll Number"));
		viewsub.add(txtChooseEnrollNum);
		viewsub.add(viewPanelButton);
		viewsub.add(new Label(""));
		/*viewsub.add(new Label(""));
		viewsub.add(new Label(""));*/
		viewPanel.add(viewsub);
		
		viewsubContainer.add(viewPanel,"student");
		
		mainPanel.add(optionPanel,"option");
		//mainPanel.add(tabbedPanel,"Tabbed Pane");
		mainPanel.add(viewsubContainer,"View Student Info");
		mainPanel.add(teacherlogincontainer,"TeacherLogin");
		mainPanel.add(studentlogincontainer,"StudentLogin");
		
		detailsButtonsPanel.add(detailsPanelBack,BorderLayout.EAST);
		detailsButtonsPanel.add(detailsPanelEdit,BorderLayout.WEST);
		
		//ScrollPane initializer
		//scrollPane=new JScrollPane(detailsPanelContents,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//detailsPanelContents.setPreferredSize(new Dimension(400,400));
		//Details Displayer 
		detailsPanel.add(detailsButtonsPanel,BorderLayout.NORTH);
		/*detailsPanelContents.add(new JLabel("asass"));
		detailsPanelContents.add(new JLabel("asass"));
		detailsPanelContents.add(new JLabel("asass"));
		detailsPanelContents.add(new JLabel("asass"));
		detailsPanelContents.add(new JLabel("asass"));
		detailsPanelContents.add(new JLabel("asass"));
		detailsPanelContents.add(new JLabel("asass"));
		detailsPanelContents.add(new JLabel("asass"));
		detailsPanelContents.add(new JLabel("asass"));
		detailsPanelContents.add(new JLabel("asass"));
		detailsPanelContents2.add(new JLabel("NonEditable"));
		scrollPane.add(detailsPanelContents);*/
		status="NonEditable";
		detailsPanelContentsContainer.add(detailsPanelContents,"Editable");
		detailsPanelContentsContainer.add(detailsPanelContents2,"NonEditable");
		detailsPanel.add(detailsPanelContentsContainer);
		
		viewsubContainer.add(detailsPanel,"details");
		add(mainPanel,BorderLayout.CENTER);
		//add(next,BorderLayout.SOUTH);
		optionPanel.add(next,BorderLayout.SOUTH);
	}
	public void itemStateChanged(ItemEvent ie)
	{
		/*optionPanel.add(txts,BorderLayout.SOUTH);
		JRadioButton jrb=(JRadioButton)ie.getSource();
		if(jrb==view && jrb.isSelected() && enroll.isSelected()==false)
		{
			txts.add(studentEnroll);
			txts.remove(teacherID);
		}
		else if(jrb==enroll && jrb.isSelected() && view.isSelected()==false)
		{
			txts.remove(studentEnroll);
			txts.add(teacherID);
		}
		Frame f=(Frame)SwingUtilities.getAncestorOfClass(Frame.class,(Component)this);
		f.setSize((int)f.getSize().getWidth()-1,(int)f.getSize().getHeight()-1);
		f.setSize((int)f.getSize().getWidth()+1,(int)f.getSize().getHeight()+1);*/
	}
	public void actionPerformed(ActionEvent ae)
	{
		//JButton b=(JButton)ae.getSource();
		if(ae.getSource()==next)
		{
			if(view.isSelected())
			{	
				cl.show(mainPanel,"StudentLogin");
				next.setVisible(false);
			}
			else if(enroll.isSelected())
			{
				Frame f=(Frame)SwingUtilities.getAncestorOfClass(Frame.class,this);
				new PreviewPanel();
				/*d.addWindowListener(new WindowAdapter()
				{
					public void windowClosing(WindowEvent we)
					{
						this.setVisible(false);
					}
				}
				);*/
				next.setVisible(true);
			}
		}
		else if(ae.getSource()==GOs)
		{
			EntryPanel e=new EntryPanel(SwingUtilities.getAncestorOfClass(Frame.class,this));
			//tabbedPanel.add(e);
			JPanel tmp=new JPanel();
			tmp.add(e);
			JScrollPane jp=new JScrollPane(tmp);
			tabbedPanel.add(jp);
			mainPanel.add(jp,"Tabbed Pane");
			Frame changeSize=(Frame)SwingUtilities.getAncestorOfClass(Frame.class,this);
			cl.show(mainPanel,"Tabbed Pane");
			e.enroll=Integer.parseInt(studentEnroll.getText());
			next.setVisible(true);
		}
		else if(ae.getSource()==GOt)
		{
			cl.show(mainPanel,"View Student Info");
			next.setVisible(true);
		}
		else if(ae.getSource()==backs || ae.getSource()==backt)
		{
			cl.show(mainPanel,"option");
			next.setVisible(true);
		}
		else if(ae.getSource()==viewPanelButton1)
		{
			if(txtChooseEnrollNum.getText().length()==0)
				JOptionPane.showMessageDialog(this,"Fill the field","ERROR",JOptionPane.ERROR_MESSAGE);
			else
			{
				/*
					THIS  IS THE AREA WHERE YOU WOULD NEED TO DO THE INTERACTION BETWEEN THE DATABASE AND UI
					PICK THE REQUIRED RECORD FROM THE DB IF EXISTING
				*/
				/*CONDITION TO CHECK IF THE USER IS EXISTING*/
				/*IF NOT EXISTING*/
				/*
				---------------
				---------------
				---------------
				*/
				/*ELSE*/
				c2.show(viewsubContainer,"details");
			}
		}
		else if(ae.getSource()==viewPanelButton2)
		{
			cl.show(mainPanel,"option");
			next.setVisible(true);
		}
		else if(ae.getSource()==detailsPanelEdit)
		{
			if(status.equals("Editable"))
			{
				detailsPanelEdit.setLabel("Edit");
				status="NonEditable";
				c3.show(detailsPanelContentsContainer,"NonEditable");
			}
			else if(status.equals("NonEditable"))
			{
				detailsPanelEdit.setLabel("Confirm");
				status="Editable";
				c3.show(detailsPanelContentsContainer,"Editable");
			}
		}
		else if(ae.getSource()==detailsPanelBack)
		{
			if(status.equals("Editable"))
			{
				int st=JOptionPane.showConfirmDialog(this, "Do you want to save the current settings","Save",JOptionPane.YES_NO_CANCEL_OPTION);
				if(st==JOptionPane.YES_OPTION)
				{
					//Update the database
				}
				else if(st==JOptionPane.NO_OPTION)
				{
					cl.show(mainPanel,"option");
					next.setVisible(true);
				}
				else if(st==JOptionPane.CANCEL_OPTION)
				{
					;
				}
			}
			else if(status.equals("NonEditable"))
			{
				cl.show(mainPanel,"option");
				next.setVisible(true);
			}
			detailsPanelEdit.setLabel("Edit");
			status="NonEditable";
		}
	}
}
class IntroDriver extends JFrame
{
	JScrollPane jsp;
	IntroDriver()
	{
		super("Intro");
		try
		{
			javax.swing.UIManager.setLookAndFeel(new SyntheticaClassyLookAndFeel());
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		setLayout(new BorderLayout());
		setBounds(20,20,600,700);
		setResizable(false);
		add(new Intro(),BorderLayout.CENTER);
		setIconImage((new ImageIcon("Project/Database.png")).getImage());
		setVisible(true);
	}
	public static void main(String[] args)
	{
		new IntroDriver();
	}
}