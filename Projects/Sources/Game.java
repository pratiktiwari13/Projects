import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;
import javax.swing.*;
//<applet code="Game" width="600" height="600"></applet>
public class Game extends Applet implements ActionListener
{
	Panel mainContainer,tempPanel;
	int init=0;
	Button b[]=new Button[16];
	Button left,right,top,bottom;
	public void init()
	{
		//int count=0;
		left=new Button("Left");
		right=new Button("Right");
		top=new Button("Up");
		bottom=new Button("Down");
		left.addActionListener(this);
		right.addActionListener(this);
		top.addActionListener(this);
		bottom.addActionListener(this);
		if(init==0)
		{
			mainContainer = new Panel();
			setLayout(new BorderLayout());
			mainContainer.setLayout(new GridLayout(4,4));
			for(int i=0;i<16;i++)
			{
				b[i]=new Button("");
				mainContainer.add(b[i]);
				b[i].setActionCommand(""+i);
				//mainContainer.add(new Label(""));
			}	
			tempPanel=new Panel();
			tempPanel.setLayout(new GridLayout(1,4));
			tempPanel.add(left);
			tempPanel.add(right);
			tempPanel.add(top);
			tempPanel.add(bottom);
			add(tempPanel,BorderLayout.NORTH);
			add(mainContainer,BorderLayout.CENTER);
			System.out.println("Calling");
			randomAdder();
		}
	}
	public Insets getInsets()
	{
		return new Insets(50,50,50,50);
	}
	public void randomAdder()
	{
		int i=0;
		for(i=0;i<16;i++)
		{
			if(b[i].getLabel().equals(""))
				break;
		}
		if(i<16)
		{
			int count=0;
			int num=0;
		
			if(init==0)
			{
				num=2;
				init=1;
			}
			else
				num=1;
				while(count<num)
				{
					int idx=new Random().nextInt(16);
					if(b[idx].getLabel().equals("")==true)
					{
						System.out.println("Contains "+b[idx].getLabel()+" still added at "+idx);
						//if(new Random().nextInt(10)%2==0)
							colorAndDisplayNum(b[idx],2);
						/*else
							colorAndDisplayNum(b[idx],4);*/
						count++;
					}
				}
		}
		else
			System.out.println("Game Over");
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("Left"))
			handleLeftOperation();
		else if(ae.getActionCommand().equals("Up"))
			handleUpOperation();
		else if(ae.getActionCommand().equals("Right"))
			handleRightOperation();
		else if(ae.getActionCommand().equals("Down"))
			handleDownOperation();
	}
	void handleLeftOperation()
	{
		int seed=-4;
		int count=0;
		int arr[];
		while(count<16)
		{
			arr=new int[4];
			int idx=0;
			seed=seed+4;
			for(int i=0;i<4;i++)
			{
				if(b[seed+i].getLabel().equals("")==false)
				{	
					arr[idx]=Integer.parseInt(b[seed+i].getLabel());
					idx++;
				}
				count++;
			}
			arr=manipLeft(arr);
			for(int j=0;j<4;j++)
			{
				if(arr[j]==0)
					b[seed+j].setLabel(null);
				else
					colorAndDisplayNum(b[seed+j],arr[j]);
			}
		}
		randomAdder();
	}
	void handleUpOperation()
	{
		int seed=-1;
		int idx=0;
		int count=0;
		int arr[];
		while(seed<3)
		{
			idx=0;
			arr=new int[4];
			seed++;
			count=0;
			for(int i=0;i<4;i++)
			{
				//System.out.println("seed "+seed+" and count "+count);
				if(b[seed+count].getLabel().equals("")==false)
				{	
					arr[idx]=Integer.parseInt(b[seed+count].getLabel());
					idx++;
				}
				count+=4;
			}
			arr=manipLeft(arr);
			count=0;
			//System.out.println("Set label");
			for(int j=0;j<4;j++)
			{
			//	System.out.println("Set Label at "+(seed+count));
				if(arr[j]==0)
					b[seed+count].setLabel("");
				else
					colorAndDisplayNum(b[seed+count],arr[j]);
				count+=4;
			}
		}
		randomAdder();
	}
	void handleRightOperation()
	{
		int seed=-4;
		int count=0;
		int arr[];
		while(count<16)
		{
			arr=new int[4];
			int idx=0;
			seed=seed+4;
			for(int i=0;i<4;i++)
			{
				if(b[seed+i].getLabel().equals("")==false)
				{	
					arr[idx]=Integer.parseInt(b[seed+i].getLabel());
					idx++;
				}
				count++;
			}
			arr=manipRight(arr);
			for(int j=0;j<4;j++)
			{
				if(arr[j]==0)
					b[seed+j].setLabel("");
				else
					colorAndDisplayNum(b[seed+j],arr[j]);
			}
		}
		randomAdder();
	}
	void handleDownOperation()
	{
		int seed=-1;
		int idx=0;
		int count=0;
		int arr[];
		while(seed<3)
		{
			idx=0;
			arr=new int[4];
			seed++;
			count=0;
			for(int i=0;i<4;i++)
			{
				//System.out.println("seed "+seed+" and count "+count);
				if(b[seed+count].getLabel().equals("")==false)
				{	
					arr[idx]=Integer.parseInt(b[seed+count].getLabel());
					idx++;
				}
				count+=4;
			}
			arr=manipRight(arr);
			count=0;
			//System.out.println("Set label");
			for(int j=0;j<4;j++)
			{
			//	System.out.println("Set Label at "+(seed+count));
				if(arr[j]==0)
					b[seed+count].setLabel("");
				else
					colorAndDisplayNum(b[seed+count],arr[j]);
				count+=4;
			}
		}
		randomAdder();
	}
	int[] manipLeft(int a[])
	{
		int[] res=new int[4];
		int idx=0;
		for(int i=0;i<3;i++)
		{
			if(i==0)
				res[idx]=a[0];
			else
			{
				if(res[idx]==a[i])
					res[idx]=res[idx]+a[i];
				else
				{
					idx++;
					res[idx]=a[i];
				}
			}
		}
		return res;
	}
	int[] manipRight(int a[])
	{
		int tempres[]= manipLeft(a);
		int numc=0;
		for(int n:tempres)
		{
			if(n!=0)
				numc++;
		}
		int numzeros=4-numc;
		int res[]=new int[4];
		int i;
		for(i=0;i<numzeros;i++)
			res[i]=0;
		int count=0;
		for(int j=i;j<4;j++)
		{
			res[j]=tempres[count];
			count++;
		}
		return res;
	}
	void colorAndDisplayNum(Button b,int num)
	{
		if(num==2)
			b.setBackground(Color.white);
		else if(num==4)
			b.setBackground(Color.yellow);
		else if(num==8)
			b.setBackground(Color.orange);
		else if(num==16)
			b.setBackground(Color.red);
		else if(num==32)
			b.setBackground(Color.cyan);
		else if(num==64)
			b.setBackground(Color.orange);
		else if(num==128)
			b.setBackground(Color.yellow);
		b.setLabel(""+num);
		setColorToDefaults();
	}
	void setColorToDefaults()
	{
		for(int i=0;i<16;i++)
		{
			if(b[i].getLabel().equals(""))
				b[i].setBackground(UIManager.getColor(new Button()));
		}
	}
}