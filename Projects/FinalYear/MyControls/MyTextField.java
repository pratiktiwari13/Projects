package MyControls;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
//import java.swing.*;

public class MyTextField extends JTextField implements KeyListener
{
	public MyTextField(String text,int col,Color c)
	{
		super(text,col);
		System.out.println("DHS");
		changeColor(c);
		addKeyListener(this);
	}
	public void changeColor(Color c)
	{
		setBorder(BorderFactory.createLineBorder(c));
	}
	public Color getBorderColor()
	{
		LineBorder b=(LineBorder)getBorder();
		return b.getLineColor();
	}
	
	public void keyPressed(KeyEvent ke){}
	public void keyReleased(KeyEvent ke){}
	public void keyTyped(KeyEvent ke){
		if(Character.isDigit(ke.getKeyChar()))
			ke.consume();
	}	
}
