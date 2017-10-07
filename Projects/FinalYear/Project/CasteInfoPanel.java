//JPEG JFIF	Exif TIFF GIF BMP PNG PPM PGM PBM  PNM	WebP HEIF BAT BPG
package Project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.*;
import javax.swing.filechooser.*;
import java.io.*;
import MyControls.*;
import java.sql.*;

/*class CasteDriverDemo extends JFrame
{
	JTabbedPane jtp=new JTabbedPane();
	CasteDriverDemo()
	{
		setIconImage(new ImageIcon("ARC.png").getImage());
		jtp.add("STUDENT1 INFO",new StudentInfo());
		jtp.add("Dhananjay",new CasteInfo());
		add(jtp);
		setSize(700,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	public static void main(String args[])
	{
		new CasteDriverDemo();
	}
}*/

interface CasteInfoConstants
{
	String open="OPEN";
	String minority="MINORITY";
	String caste="CASTE";
	String obc="OBC";
	String st="ST";
	String nt="NT";
	String sc="SC";
	String upload="UPLOAD";
	String minorityCertificate="MINORITY CERTIFICATE";
	String casteCertificate="CASTE CERTIFICATE";
	String nonCreameLayer="NON CREAME-LAYER";
	String casteValidity="CATSE VALIDITY";
	String finish="FINISH";
}
            

public class CasteInfoPanel extends JPanel implements CasteInfoConstants,ActionListener
{
	ImageIcon uploadImage;
	CardLayout cl;
	ButtonGroup bg;
	JPanel top,center,bottom,openPanel,castePanel,minorityPanel;
	JLabel lblUpload,lblMinorityCertificate,lblCasteCertificate,lblNonCreameLayer,lblCasteValidity,label;
	JLabel casteVLabel,casteNLabel,casteCLabel;
	JButton minorityUpload,casteCUpload,casteVUpload,casteNUpload;
	JRadioButton jrbMinority,jrbCaste,jrbOpen;
	JComboBox jcbCaste;
	JButton jbFinish;
	JFileChooser fileSelector;
	File minorityUploadFile,casteCUploadFile,casteVUploadFile,casteNUploadFile;

	public CasteInfoPanel()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch (Exception e)
		{
			System.out.println("Class nai hai");
		}
		setLayout(new BorderLayout());
		initializeAll();
		createAndMakePanels();
		//setBackground(new Color(200,200,200));
	}
	void initializeAll()
	{
		fileSelector=new JFileChooser();
		
		uploadImage=new ImageIcon("uploadIcon.png");

		top=new JPanel();
		bg=new ButtonGroup();
		jrbMinority=new JRadioButton(minority);
		jrbCaste=new JRadioButton(caste);
		jrbOpen=new JRadioButton(open);

		center=new JPanel();

		minorityPanel=new JPanel(){public Insets getInsets()
		{
			return new Insets(6,6,6,6);
		}};
		lblMinorityCertificate=new JLabel(minorityCertificate);
		minorityUpload=new JButton(upload,uploadImage);

		castePanel=new JPanel(){public Insets getInsets()
		{	
			return new Insets(20,20,20,20);
		}};
		lblCasteCertificate=new JLabel(casteCertificate,JLabel.CENTER);
		lblNonCreameLayer=new JLabel(nonCreameLayer,JLabel.CENTER);
		lblCasteValidity=new JLabel(casteValidity,JLabel.CENTER);
		String arr[]={obc,nt,sc,st};
		jcbCaste=new JComboBox(arr);
		casteCUpload=new JButton(upload,uploadImage);
		casteVUpload=new JButton(upload,uploadImage);
		casteNUpload=new JButton(upload,uploadImage);
		casteCLabel=new JLabel();
		casteVLabel=new JLabel();
		casteNLabel=new JLabel();
		
		openPanel=new JPanel();

		bottom=new JPanel();
		jbFinish=new JButton(finish);
		jbFinish.addActionListener(this);
	}
	void createAndMakePanels()
	{
		FileNameExtensionFilter fs;
		fileSelector.addChoosableFileFilter(fs=new FileNameExtensionFilter("ImageFiles","jpg","JPEG","JFIF","Exif","TIFF","GIF","BMP","PNG","PPM","PGM","PBM","PNM","WebP","HEIF","BAT","BPG"));
		fileSelector.setFileFilter(fs);
		bg.add(jrbMinority);
		bg.add(jrbCaste);
		bg.add(jrbOpen);
		top.add(jrbMinority);
		top.add(jrbCaste);
		top.add(jrbOpen);
		jrbMinority.addActionListener(this);
		jrbCaste.addActionListener(this);
		jrbOpen.addActionListener(this);
		add(top,BorderLayout.NORTH);

		castePanel.setLayout(new BorderLayout());
		JPanel c1,c2;c1=new JPanel();
		c1.setLayout(new FlowLayout());
		c1.add(jcbCaste);
		c2=new JPanel(){
			public Insets getInsets()
			{
				return new Insets(10,10,10,10);
			}
		};
		c2.setLayout(new GridLayout(3,3,50,20));
		c2.add(casteCLabel);
		c2.add(casteVLabel);
		c2.add(casteNLabel);
		c2.add(lblCasteCertificate);
		c2.add(lblCasteValidity);
		c2.add(lblNonCreameLayer);
		c2.add(casteCUpload);
		c2.add(casteVUpload);
		c2.add(casteNUpload);
		castePanel.add(c1,BorderLayout.NORTH);
		castePanel.add(c2,BorderLayout.CENTER);
		
		GridBagLayout gridbag=new GridBagLayout();
		minorityPanel.setLayout(gridbag);
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gbc.gridheight=2;
		gbc.ipady=40;
		gbc.anchor=GridBagConstraints.NORTH;
		gbc.fill= GridBagConstraints.BOTH;
		label=new JLabel(new ImageIcon("images.png"));
		gridbag.setConstraints(label,gbc);
        minorityPanel.add(label);

		gbc.gridwidth=GridBagConstraints.RELATIVE;
		gbc.gridheight=2;
		gbc.anchor=GridBagConstraints.SOUTH;
		gbc.ipadx=20;
		gridbag.setConstraints(lblMinorityCertificate,gbc);
        minorityPanel.add(lblMinorityCertificate);
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gridbag.setConstraints(minorityUpload,gbc);
		minorityPanel.add(minorityUpload);

		center.setLayout(cl=new CardLayout());
		center.add("o",openPanel);
		center.add("m",minorityPanel);
		center.add("c",castePanel);
		add(center,BorderLayout.CENTER);
	
		bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bottom.add(jbFinish);
		add(bottom,BorderLayout.SOUTH);


		minorityUpload.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(fileSelector.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
				{
					try
					{
						minorityUploadFile=fileSelector.getSelectedFile();
						ImageResizer.resize(minorityUploadFile.getPath(),100,100);
						label.setIcon(new ImageIcon("Demo"+minorityUploadFile.getName()));
					}
					catch (Exception e)
					{
					}
				}
			}
		});

		casteCUpload.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(fileSelector.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
				{
					try
					{				
						casteCUploadFile=fileSelector.getSelectedFile();
						ImageResizer.resize(casteCUploadFile.getPath(),100,100);
						casteCLabel.setIcon(new ImageIcon("Demo"+casteCUploadFile.getName()));
					}
					catch(Exception e)
					{
						System.out.println("EXPT");
					}
				}
			}
		});
		casteVUpload.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(fileSelector.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
				{
					try
					{				
						casteVUploadFile=fileSelector.getSelectedFile();
						ImageResizer.resize(casteVUploadFile.getPath(),100,100);
						casteVLabel.setIcon(new ImageIcon("Demo"+casteVUploadFile.getName()));
					}
					catch(Exception e)
					{
						System.out.println("Exception");
					}
				}
			}
		});
		casteNUpload.addActionListener(new ActionListener()
		{		
			public void actionPerformed(ActionEvent ae)
			{
				if(fileSelector.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
				{		
					try
					{				
						casteNUploadFile=fileSelector.getSelectedFile();
						ImageResizer.resize(casteNUploadFile.getPath(),100,100);
						casteNLabel.setIcon(new ImageIcon("Demo"+casteNUploadFile.getName()));
					}
					catch(Exception e)
					{
						System.out.println("Exception");
					}
				}
			}
		});
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(jrbMinority.isSelected())
		{
			cl.show(center,"m");
		}
		if(jrbCaste.isSelected())
		{
			cl.show(center,"c");
		}
		if(jrbOpen.isSelected())
		{
			cl.show(center,"o");
		}
		if(ae.getSource()==jbFinish)
		{
			System.out.println("Hello");
				{
					System.out.println("Inserting");
					EntryPanel.insertAll();
					System.out.println("Inserted");
				}
		}
	}
	public Insets getInsets()
	{
		return new Insets(6,6,6,6);
	}
}