package MyControls;
import java.awt.*;
import java.applet.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

//<applet code="Test" width="300" height="300"></applet>

public class NumericTextField extends MyTextField implements KeyListener
{
	private int numDigits;
	public NumericTextField(String msg,int cols,int numDigits)
	{
		super(msg,cols,Color.BLACK);
		this.numDigits=numDigits;
		addKeyListener(this);
	}
	public void keyPressed(KeyEvent ke){}
	public void keyReleased(KeyEvent ke){}
	public void keyTyped(KeyEvent ke){
		if(!(Character.isDigit(ke.getKeyChar() )&& !(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE))||getText().length() == numDigits)
			ke.consume();
	}	
}
