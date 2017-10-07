import java.awt.*;
import java.awt.event.*;
import java.applet.*;

//<applet code="DatePicker.java" width="500" height="500"></applet>

public class DatePicker extends Applet
{
	public void init()
	{
		add(new JDatePicker(new JDatePanelImpl(new UtilDateModel)));
	}
}