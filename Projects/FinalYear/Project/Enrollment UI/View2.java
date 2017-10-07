import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class View2 extends JPanel implements FocusListener 
{
	JPanel jpn;
	JLabel lbl_enroll, lbl_enroll_img, lbl_btn_go, lbl_enrollment_img;// lbl_teacher_img;
	JButton btn_back, btn_go;
	JTextField jtf;
	
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

	ImageIcon i = new ImageIcon("image_enroll_next.png");
	ImageIcon i1 = new ImageIcon("image_go.png");
	ImageIcon i2 = new ImageIcon("image_back.png");
	ImageIcon i3 = new ImageIcon("image_enroll.jpg");

	public View2(JPanel obj)
	{	
		jpn=obj;
		initializeAll();
		addAll();
		setOpaque(true);
		setBackground(c3);
		
	}

	public void initializeAll()
	{
		jpn = new JPanel();
	
		lbl_enroll =  new JLabel("Enrollment No.");
		lbl_enroll_img = new JLabel(i);
		lbl_enrollment_img = new JLabel(i3);

		jtf = new JTextField("Enrollment No.");

		jtf.addFocusListener(this);

		btn_go = new JButton(i1);
		btn_back =  new JButton(i2);
	}
	public void addAll()
	{
		setLayout(null);

		lbl_enroll.setBounds(50,20,300,50);
		lbl_enroll.setFont(f);
		lbl_enroll.setForeground(c1);
		add(lbl_enroll);

		lbl_enroll_img.setBounds(330,20,50,50);
		add(lbl_enroll_img);

		jtf.setBounds(190,100,150,30);
		jtf.setFont(f2);
		jtf.setForeground(new Color(200,200,200));
		add(jtf);
		
		btn_go.setBorder(BorderFactory.createEmptyBorder());
		btn_go.setBounds(180,160,30,30);
		add(btn_go);

		btn_back.setBorder(BorderFactory.createEmptyBorder());
		btn_back.setBounds(320,160,30,30);
		add(btn_back);

		lbl_enrollment_img.setBounds(10,100,600,700);
		add(lbl_enrollment_img);
	}
	public void focusGained(FocusEvent fe)
	{
		if(jtf.getText().equals("Enrollment No."));
		{
			jtf.setText("");
		}
		
	}

	public void focusLost(FocusEvent fe)
	{
		if(jtf.getText().length()==0)
		{
			jtf.setText("Enrollment No.");
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		jpn.forward();
	}
}

class DriverDemo1 extends JFrame
{
	DriverDemo1()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new View2());
		setSize(600,700);
		setVisible(true);
	}
	public static void main(String args[])
	{
		new DriverDemo1();
	}
}