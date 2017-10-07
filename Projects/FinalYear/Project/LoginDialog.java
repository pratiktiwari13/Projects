package Project;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import de.javasoft.plaf.synthetica.*;
import java.util.*;
import java.sql.*;

public class LoginDialog extends JDialog implements ActionListener
{
	JButton submit=new JButton("Submit");
	JPasswordField passw=new JPasswordField();
	LoginDialog(Frame owner)
	{
		super(owner,"PASSWORD",true);
		setSize(300,300);
		setLayout(new GridLayout(2,1,90,90));
		add(passw);
		add(submit);
		submit.addActionListener(this);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		System.out.println("Yess");
			if(new String(passw.getPassword()).length()>0 && new String(passw.getPassword()).equals("ABC123"))
			{
				System.out.println("Helooooo");
				new PreviewPanel();
				this.dispose();	
			}
	}
	public Insets getInsets()
	{
		return new Insets(50,50,50,50);
	}
}
