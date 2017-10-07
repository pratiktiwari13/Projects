import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
//import MyControls.*;

class PageReplacer extends Frame implements ActionListener,ItemListener
{
	JLabel lblNumofbox,lblEnterNumbers;
	JSpinner txtNumofbox;
	JTextField allTheTextfields[];
	JButton submitNumOfBox,submitAllTheNumbers;
	int sampleNumbers[];
	JPanel masterControlPanel,boxAccepterPanel,boxDisplayPanel,acceptNumbersOfBoxPanel,boxAccepterPanelWrapper,boxDisplayPanelWrapper,acceptNumbersOfBoxPanelWrapper;
	CardLayout cl=new CardLayout();
	JSpinner jtf[];
	int boxes,inputArray[];
	JTable table;
	Frame frame;
	PageReplacer()
	{
		frame=this;
		initComponents();
		setLayouts();
		makePanels();
		finalAddition();
	}
	public void initComponents()
	{
		lblEnterNumbers=new JLabel("Enter number of boxes");
		lblNumofbox=new JLabel("Enter all the numbers");
		
		submitAllTheNumbers=new JButton("Submit");
		submitAllTheNumbers.addActionListener(this);
		
		submitNumOfBox=new JButton("Submit");
		submitNumOfBox.addActionListener(this);
		
		txtNumofbox=new JSpinner();
		
		boxAccepterPanel=new JPanel();
		boxDisplayPanel=new JPanel();
		acceptNumbersOfBoxPanel=new JPanel();
		boxAccepterPanelWrapper=new JPanel();
		boxDisplayPanelWrapper=new JPanel();
		acceptNumbersOfBoxPanelWrapper=new JPanel();
		masterControlPanel=new JPanel();
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==submitNumOfBox)
		{
			boxes=Integer.parseInt(txtNumofbox.getValue().toString());
			if(boxes>0&&boxes<=10)
				setupTheSpinners(boxes);
		}
		else if(ae.getSource()==submitAllTheNumbers)
		{
			if(allFieldsFilled())
			{
				inputArray=getInputArray();
				setOutput(inputArray);
			}
		}	
	}
	public boolean allFieldsFilled()
	{
		int i;
		for(i=0;i<boxes;i++)
		{
			if(jtf[i].getValue().toString().equals("0"))
				break;
		}
		if(i==boxes)
			return true;
		else
			return false;
	}
	public int[] getInputArray()
	{
		int inputArray[]=new int[boxes];
		for(int i=0;i<boxes;i++)
			inputArray[i]=Integer.parseInt(jtf[i].getValue().toString());
		return inputArray;
	}
	public void setOutput(int[] inputArray)
	{
		boxDisplayPanel.setLayout(new BorderLayout());
		addOptions();
		addFifoTable(inputArray);
		//addLruTable(inputArray);
		cl.show(masterControlPanel,"Display Box");
	}
	public void addOptions()
	{
		JComboBox jcb=new JComboBox();
		jcb.addItem(new String("FIFO"));
		jcb.addItem(new String("LRU"));
		boxDisplayPanel.add(jcb,BorderLayout.NORTH);
		jcb.addItemListener(this);
	}
	public void addFifoTable(int[] inputArray)
	{	
		makeFifoTable(inputArray);
		boxDisplayPanel.add(new JScrollPane(table),BorderLayout.CENTER);
	}
	public void addLruTable(int[] inputArray)
	{
		makeLruTable(inputArray);
		boxDisplayPanel.add(new JScrollPane(table),BorderLayout.CENTER);
	}
	public void makeLruTable(int[] inputArray)
	{
		int sampleNumbers[]=inputArray;
		int container[]=new int[3];
		int pagehit=0;
		int frameNumber=3;
		Object tableData[][]=new Object[frameNumber][boxes];
		Object columnNames[]=new Object[boxes];
		for(int i=0;i<3;i++)
		{
			container[i]=sampleNumbers[i];
			columnNames[i]=""+sampleNumbers[i];
		}
		for(int i=3;i<sampleNumbers.length;i++)
		{
			int j=0;
			for(j=0;j<3;j++)
			{
				if(container[j]==sampleNumbers[i])
					break;
			}
			if(j==3)
			{
				int toAdd=sampleNumbers[i];
				int last=container.length-1;
				for(int p=last;p>0;p--)
					container[p]=container[p-1];
				container[0]=toAdd;
			}
			else
			{
				int top=container[j];
				int temp[]=new int[2];
				int count=0;
				for(int k=0;k<3;k++)
				{
					if(k==j)
						continue;
					else
					{
						temp[count]=container[k];
						count++;
					}
				}
				container[0]=top;
				count=0;
				for(int l=1;l<3;l++)
				{
					container[l]=temp[count];
					count++;
				}
				pagehit++;
			}
			for(int t=0;t<3;t++)
				tableData[t][i]=container[t];
			columnNames[i]=""+sampleNumbers[i];
		}
		table=new JTable(tableData,columnNames);
	}
	public void makeFifoTable(int[] inputArray)
	{
		int numbers[]=inputArray;
		int container[]=new int[3];
		int pointer=0;
		int pagehit=0;
		int j=0;
		int frameNumber=3;
		Object tableData[][]=new Object[frameNumber][boxes];
		Object columnNames[]=new Object[boxes];
		
		for(int i=0;i<numbers.length;i++)
		{
			for(j=0;j<frameNumber;j++)
			{
				if(container[j]!=numbers[i])
					continue;
				else
					break;
			}
			if(j==frameNumber)
			{
				container[pointer]=numbers[i];
				pointer++;
				if(pointer==frameNumber)
					pointer=0;
			}
			else
				pagehit++;
			for(int t=0;t<3;t++)
				tableData[t][i]=container[t];
			columnNames[i]=""+numbers[i];
		}
		table=new JTable(tableData,columnNames);
	}
	public void itemStateChanged(ItemEvent ie)
	{
		boxDisplayPanel.remove(table);
		table=null;
		System.out.println("Removed");
		frame.setSize(frame.getHeight()+1,frame.getWidth()+1);
		frame.setSize(frame.getHeight()-1,frame.getWidth()-1);
		System.out.println("Frame obtained");
		if(((JComboBox)ie.getSource()).getSelectedIndex()==1)
		{
			addLruTable(inputArray);
			frame.setSize(frame.getHeight()+1,frame.getWidth()+1);
			frame.setSize(frame.getHeight()-1,frame.getWidth()-1);
		}
		else
		{
			addFifoTable(inputArray);
			frame.setSize(frame.getHeight()+1,frame.getWidth()+1);
			frame.setSize(frame.getHeight()-1,frame.getWidth()-1);
		}
	}
	public void setupTheSpinners(int boxes)
	{
		acceptNumbersOfBoxPanel.setLayout(new GridLayout(boxes+1,0));
		jtf=new JSpinner[boxes];
		for(int i=0;i<boxes;i++)
		{
			jtf[i]=new JSpinner();
			acceptNumbersOfBoxPanel.add(jtf[i]);
		}
		acceptNumbersOfBoxPanel.add(submitAllTheNumbers);
		acceptNumbersOfBoxPanelWrapper.add(acceptNumbersOfBoxPanel);
		cl.show(masterControlPanel,"Accept all the boxes");
	}
	public void finalAddition()
	{
		setSize(500,500);
		add(masterControlPanel,BorderLayout.CENTER);
		setVisible(true);
	}
	public void setLayouts()
	{
		masterControlPanel.setLayout(cl);
		boxAccepterPanel.setLayout(new GridLayout(2,1));
	}
	public void makePanels()
	{
		boxDisplayPanelWrapper.add(boxDisplayPanel);
		boxAccepterPanel.add(txtNumofbox);
		boxAccepterPanel.add(submitNumOfBox);
		boxAccepterPanelWrapper.add(boxAccepterPanel);
		masterControlPanel.add(boxAccepterPanelWrapper,"Accept Number of Boxes");
		masterControlPanel.add(acceptNumbersOfBoxPanelWrapper,"Accept all the boxes");
		masterControlPanel.add(boxDisplayPanelWrapper,"Display Box");
	}
	public static void main(String[] args)
	{
		new PageReplacer();
	}
}