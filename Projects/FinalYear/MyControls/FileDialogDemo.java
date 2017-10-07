import java.awt.*;
import java.awt.event.*;

public class FileDialogDemo extends Frame implements ActionListener
{
	Button b;
	FileDialog f;
	FileDialogDemo(String msg)
	{
		super(msg);
		setLayout(new FlowLayout());
		b=new Button("File Dialog");
		add(b);
		b.addActionListener(this);
		setBounds(20,20,400,400);
		setVisible(true);
	}
	public static void main(String[] args)
	{
		new FileDialogDemo("FileDialogDemo");
	}
	public void actionPerformed(ActionEvent ae)
	{
		System.out.println("Hello");
		f=new FileDialog(this,"Save",FileDialog.SAVE);
		f.setVisible(true);
	}
}