package Project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import MyControls.*;
import de.javasoft.plaf.synthetica.*;
import Project.*;
import java.sql.*;

class EntryPanelWithBackButton extends JPanel implements ActionListener
{
		JPanel d;
		JButton bck;
		IntroDemo obj;
		EntryPanelWithBackButton(IntroDemo ref)
		{
			obj=ref;
			bck=new JButton("BACK TO LOGIN");
			d=new JPanel();
			bck.addActionListener(this);
			setLayout(new BorderLayout());
			add(new EntryPanel(new JFrame()),BorderLayout.CENTER);
			d.setLayout(new FlowLayout(FlowLayout.LEFT));
			d.add(bck);
			add(d,BorderLayout.SOUTH);
		}
		
		public void actionPerformed(ActionEvent ae)
		{
			if(JOptionPane.OK_OPTION==JOptionPane.showOptionDialog(EntryPanelWithBackButton.this,"Current Data would be lost","WARNING",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,new ImageIcon(),null,null))
			{
				obj.showV1();
			}
		}
}
public class IntroDemo extends JPanel 
{
	CardLayout c;
	View1 v1;
	View2 v2;
	EntryPanelWithBackButton ep;
	//JButton back=new JButton("Back");
	public IntroDemo()
	{
		initAll(); 
		addPanel();
	}
	
	void initAll()
	{
		c=new CardLayout();
		setLayout(c);
		//back.setVisible(false);
		v1=new View1(this);
		v2=new View2(this);
		//c=new CardLayout();
		ep=new EntryPanelWithBackButton(this);
	}
	
	void addPanel()
	{
		//add(back);
		add("V1",v1);
		
		
	}
	
	void back()
	{
		c.previous(this);
	}
	
	void forward()
	{
		v2=new View2(this);
		add("V2",v2);
		c.next(this);
	}
	
	void showEntryPanel()
	{
		ep=new EntryPanelWithBackButton(this);
		add("Entry",ep);
		c.show(this,"Entry");
		//back.setVisible(true);
	}
	
	void showV1()
	{
		c.show(this,"V1");
	}
}
class IntroDemoDriver extends JFrame
{
	JScrollPane jsp;
	IntroDemoDriver()
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
		IntroDemo id;
		add(id=new IntroDemo(),BorderLayout.CENTER);
		setIconImage((new ImageIcon("Project/Database.png")).getImage());
		//id.showEntryPanel();
		setVisible(true);
	}
	public static void main(String[] args)
	{
		new IntroDemoDriver();
	}
}
