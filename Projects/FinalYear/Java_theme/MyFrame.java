import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import de.javasoft.plaf.synthetica.*;
class MyFrame extends JFrame 
{
	public static void main(String[] args)
	{
		try
		{
			javax.swing.UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		MyFrame ob = new MyFrame();		
		ob.setVisible(true);
		ob.setSize(300, 300);
		ob.setLayout(new FlowLayout());
		ob.add(new JLabel("JLabel"));
		ob.add(new JTextField("TextField", 20));
		ob.add(new JButton("Click Me!!"));		
	}
}