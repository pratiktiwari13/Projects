import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class View1 extends JPanel
{
	JPanel jpn, jpn_top;
	JLabel lbl_login_as, lbl_img, lbl_lgin_img, lbl_student_img, lbl_teacher_img;
	JButton btn_next;
	JRadioButton rd_btn_student, rd_btn_teacher;
	ButtonGroup bg1;

	//Gives Font
	
	Font f = new Font("Lucida Sans",Font.BOLD, 36);
	Font f1 = new Font("Calibri",Font.PLAIN, 14);
	Font f2 = new Font("Calibri",Font.BOLD,16);
	Font f3 = new Font("Cambria",Font.BOLD,16);
	
	//Gives Color

	Color c1 =  new Color(0,0,0);
	Color c2 =  new Color(2,3,62);
	Color c3 = new Color(255,255,255);
	
	//Gives Image

	ImageIcon i = new ImageIcon("Image1.jpg");
	ImageIcon i1 = new ImageIcon("Image2.jpg");
	ImageIcon i2 = new ImageIcon("Image3.jpg");
	ImageIcon i3 = new ImageIcon("Image4.jpg");

	public View()
	{	
		initializeAll();
		addAll();
		setOpaque(true);
		setBackground(Color.WHITE);
		
	}

	public void initializeAll()
	{
		jpn = new JPanel();
	
		lbl_img = new JLabel(i);
		lbl_login_as =  new JLabel("Login as");
		lbl_lgin_img =  new JLabel(i1);
		lbl_student_img =  new JLabel(i2);
		lbl_teacher_img =  new JLabel(i3);
		
		btn_next =  new JButton("Next");
		
		bg1 = new ButtonGroup();

		rd_btn_student = new JRadioButton("Student");
		rd_btn_teacher =  new JRadioButton("Teacher");
	}
	public void addAll()
	{
		setLayout(null);
	
		lbl_img.setBounds(10,20,600,700);
		add(lbl_img);
	
		lbl_lgin_img.setBounds(220,20,50,50);
		add(lbl_lgin_img);

		lbl_login_as.setBounds(50,20,300,50);
		lbl_login_as.setFont(f);
		lbl_login_as.setForeground(c1);
		add(lbl_login_as);
		
		lbl_student_img.setBounds(250,90,20,20);
		add(lbl_student_img);
		rd_btn_student.setBounds(150,90,100,20);
		rd_btn_student.setFont(f3);
		rd_btn_student.setBackground(c3);
		rd_btn_student.setForeground(c2);
		add(rd_btn_student);

		lbl_teacher_img.setBounds(430,90,20,20);
		add(lbl_teacher_img);
		rd_btn_teacher.setBounds(330,90,90,20);
		rd_btn_teacher.setFont(f3);
		rd_btn_teacher.setBackground(c3);
		rd_btn_teacher.setForeground(c2);
		add(rd_btn_teacher);

		bg1.add(rd_btn_student);
		bg1.add(rd_btn_teacher);

		btn_next.setBounds(275,130,80,30);
		btn_next.setFont(f2);
		btn_next.setBackground(c3);
		btn_next.setForeground(c1);
		add(btn_next);
	}

}

class DriverDemo extends JFrame
{
	DriverDemo()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new View());
		setSize(600,700);
		setVisible(true);
	}
	public static void main(String args[])
	{
		new DriverDemo();
	}
}