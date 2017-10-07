package Project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import MyControls.*;
import Project.*;
import java.sql.*;
public class View1 extends JPanel implements ActionListener
{
	JPanel  jpn_top;
	JLabel lbl_login_as; 
	
	//Image Labels
	
	JLabel lbl_img, lbl_lgin_img, lbl_student_img, lbl_teacher_img;
	
	JButton btn_next;
	JRadioButton rd_btn_student, rd_btn_teacher;
	ButtonGroup bg1;

	//Gives Font
	
	Font f = new Font("Lucida Sans",Font.BOLD, 36);
	Font f1 = new Font("Calibri",Font.PLAIN, 14);
	Font f2 = new Font("Calibri",Font.BOLD,16);
	Font f3 = new Font("Cambria",Font.BOLD,16);
	
	//Gives Color

	Color c1 =  new Color(120,120,120);
	Color c2 =  new Color(2,3,62);
	Color c3 = new Color(255,255,255);
	
	//Gives Image

	ImageIcon i = new ImageIcon("C:\\Users\\admin\\Desktop\\ProjectContainer\\Project\\lappy_dhs.png");
	ImageIcon i1 = new ImageIcon("C:\\Users\\admin\\Desktop\\ProjectContainer\\Project\\image_login_dhs.png");
	ImageIcon i2 = new ImageIcon("C:\\Users\\admin\\Desktop\\ProjectContainer\\Project\\image_student_dhs.png");
	ImageIcon i3 = new ImageIcon("C:\\Users\\admin\\Desktop\\ProjectContainer\\Project\\image_teacher_dhs.png");
	ImageIcon i4 = new ImageIcon("C:\\Users\\admin\\Desktop\\ProjectContainer\\Project\\image_next_dhs.png");

	IntroDemo jpn;
	public View1(IntroDemo obj)
	{	
		//System.out.println("DHSDHSHSHDHS");
		jpn=obj;
		initializeAll();
		addAll();
		setOpaque(true);
		//setBackground(c3);
		
	}

	public void initializeAll()
	{
	
		lbl_img = new JLabel(i);
		lbl_login_as =  new JLabel("Login as");
		lbl_lgin_img =  new JLabel(i1);
		lbl_student_img =  new JLabel(i2);
		lbl_teacher_img =  new JLabel(i3);
		
		btn_next =  new JButton(i4);
		btn_next.addActionListener(this);
		
		bg1 = new ButtonGroup();

		rd_btn_student = new JRadioButton("Student");
		rd_btn_teacher =  new JRadioButton("Teacher");
	}
	public void addAll()
	{
		setLayout(null);
	
		lbl_img.setBounds(10,20,600,700);
		add(lbl_img);
	
		lbl_lgin_img.setBounds(170,25,50,50);
		add(lbl_lgin_img);

		lbl_login_as.setBounds(10,20,300,50);
		lbl_login_as.setFont(f);
		//lbl_login_as.setForeground(c1);
		add(lbl_login_as);
		
		lbl_student_img.setBounds(250,90,20,20);
		add(lbl_student_img);
		rd_btn_student.setBounds(150,90,100,20);
		rd_btn_student.setFont(f3);
		//rd_btn_student.setBackground(c3);
		//rd_btn_student.setForeground(c2);
		add(rd_btn_student);

		lbl_teacher_img.setBounds(430,90,20,20);
		add(lbl_teacher_img);
		rd_btn_teacher.setBounds(330,90,90,20);
		rd_btn_teacher.setFont(f3);
		//rd_btn_teacher.setBackground(c3);
		//rd_btn_teacher.setForeground(c2);
		add(rd_btn_teacher);

		bg1.add(rd_btn_student);
		bg1.add(rd_btn_teacher);

		btn_next.setBorder(BorderFactory.createEmptyBorder());
		btn_next.setBounds(285,130,30,30);
		//btn_next.setBackground(c3);
		add(btn_next);
	}

	public void actionPerformed(ActionEvent ae)
	{
		System.out.println(jpn);
		if(rd_btn_student.isSelected())
			jpn.forward();
		else
			new LoginDialog((Frame)SwingUtilities.getAncestorOfClass(Frame.class,this));
	}
}

class DriverDemo extends JFrame
{
	DriverDemo()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//add(new View1());
		setSize(600,700);
		setVisible(true);
	}
	public static void main(String args[])
	{
		new DriverDemo();
	}
}