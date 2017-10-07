package MyControls;
import java.awt.*;
import java.applet.*;
import java.swing.*;
import java.awt.event.*;

//<applet code="Test" width="300" height="300"></applet>

class NumericTextField extends JTextField implements KeyListener
{
	private int numDigits;
	NumericTextField(String msg,int cols,int numDigits)
	{
		super(str,cols);
		this.numDigits=numDigits;
		addKeyListener(this);
	}
	public void keyPressed(KeyEvent ke){}
	public void keyReleased(KeyEvent ke){}
	public void keyTyped(KeyEvent ke){
		if(!Character.isDigit(ke.getKeyChar() && !(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE)||getText().length() == numDigits)
			ke.consume();
	}	
}