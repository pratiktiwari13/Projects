import java.awt.*;
import java.awt.event.*;
import java.applet.*;
//<applet code="AddRemComponents" width="300" height="300"></applet>

public class AddRemComponents extends Applet implements ActionListener
{
	Button add,remove;
	Label anyText;
	public void init()
	{
		setLayout(new GridLayout(3,1));
		add=new Button("Add");
		remove=new Button("Remove");
		add.addActionListener(this);
		remove.addActionListener(this);
		anyText=new Label("Any Text");
		add(add);
		add(remove);
		remove.setEnabled(false);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==add)
		{
			add(anyText);
			add.setEnabled(false);
			remove.setEnabled(true);
			update();
		}
		else if(ae.getSource()==remove)
		{
			add.setEnabled(true);
			remove(anyText);
			remove.setEnabled(false);
			update();
		}
	}
}