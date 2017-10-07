import java.awt.*;
import java.awt.event.*;
import java.applet.*;
//<applet code="CalcBODMAS" width=250 height=250></applet>
public class CalcBODMAS extends Applet implements TextListener,ActionListener
{
	Panel txt;
	Panel btns;
	TextField input,output;
	Button b[];
	Button add,mul,div,sub,clr,bck;
	String temp="",equ="",pequ="",disp="";
	int count=0,back=1,addFlag=1;
	int l=1;
	//String op="";
	double num1,num2,tempnum;
	int exceptionFlag=1;
	public void init()
	{
		bck=new Button("Back");
		b=new Button[10];
		//equ=new Button("=");
		clr=new Button("CLR");
		setLayout(new BorderLayout(20,20));
		input = new TextField(70);
		input.addTextListener(this);
		input.setEditable(false);
		output = new TextField(70);
		output.addTextListener(this);
		output.setEditable(false);
		txt=new Panel();
		txt.setLayout(new GridLayout(2,1,10,0));
		txt.add(input);
		txt.add(output);
		add(txt,BorderLayout.NORTH);
		btns=new Panel();
		btns.setLayout(new GridLayout(4,5,10,10));
		for(int i=0;i<=9;i++)
		{	
			b[i]=new Button(""+i);
			b[i].addActionListener(this);
			btns.add(b[i]);
		}
		add=new Button("+");
		btns.add(add);
		add.addActionListener(this);
		mul=new Button("x");
		btns.add(mul);
		mul.addActionListener(this);
		div=new Button("/");
		btns.add(div);
		div.addActionListener(this);
		sub=new Button("-");
		btns.add(sub);
		btns.add(clr);
		btns.add(bck);
		//btns.add(equ);
		clr.addActionListener(this);
		sub.addActionListener(this);
		add(btns,BorderLayout.CENTER);
		bck.addActionListener(this);
	}
	public Insets getInsets()
	{
		return new Insets(20,20,20,20);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(addFlag==1)
		{
			if(ae.getActionCommand()=="Back")
			{
				if(input.getText().equals(""));
				else{
				input.setText((input.getText()).substring(0,(input.getText()).length()-1));
				pequ=pequ.substring(0,pequ.length()-1);
				System.out.println(pequ);
				equ=pequ;
				}
			}
			else
			{
				pequ=pequ+ae.getActionCommand();
				System.out.println(pequ);
				equ=pequ;
				if(ae.getActionCommand()=="x"||ae.getActionCommand()=="+"||ae.getActionCommand()=="-"||ae.getActionCommand()=="/")
				{
					disp="";
					input.setText("");
					input.setText(ae.getActionCommand());
				}
				else
				{
					disp+=ae.getActionCommand();
					input.setText(disp);
				}
			}
		}
		String strn1="";
		String strn2="";
			if(equ.indexOf("/")!=-1||equ.indexOf("x")!=-1)
			{
				addFlag=0;
				if(equ.indexOf("/")!=-1 && equ.indexOf("x")!=-1)
				{
				int idx1=equ.indexOf("/");
				int idx2=equ.indexOf("x");
				
				if(idx1<idx2)
				{
					int idx=equ.indexOf('/');
					int s=idx-1;
					int e=idx+1;
					while(s>-1)
					{
						if(equ.charAt(s) == '+'||equ.charAt(s) == 'x'||equ.charAt(s) == '-')
							break;
						strn1=strn1+equ.charAt(s);
						s--;
					}
					strn1=new String(new StringBuffer(strn1).reverse());
					//System.out.println(strn1);
					while(e<equ.length())
					{
						if(equ.charAt(e) == '+'||equ.charAt(e) == 'x'||equ.charAt(e) == '-')
							break;
						strn2=strn2+equ.charAt(e);
						e++;
					}
					//System.out.println(strn2);
					String res;
			if(strn1.equals(""))
				res=""+(Double.parseDouble("0")/Double.parseDouble(strn2));
			else if(strn2.equals(""))
				res=""+(Double.parseDouble(strn1)/Double.parseDouble("1"));
			else
				res=""+(Double.parseDouble(strn1)/Double.parseDouble(strn2));
					String replacee=strn1+equ.charAt(idx)+strn2;
					equ=equ.replace(replacee,res);
					System.out.println(equ);
					actionPerformed(ae);
				}
				else if(idx1>idx2)
				{
					int idx=equ.indexOf('x');
					int s=idx-1;
					int e=idx+1;
					while(s>-1)
					{
						if(equ.charAt(s) == '+'||equ.charAt(s) == '/'||equ.charAt(s) == '-')
							break;
						strn1=strn1+equ.charAt(s);
						s--;
					}
					strn1=new String(new StringBuffer(strn1).reverse());
					//System.out.println(strn1);
					while(e<equ.length())
					{
						if(equ.charAt(e) == '+'||equ.charAt(e) == '/'||equ.charAt(e) == '-')
							break;
						strn2=strn2+equ.charAt(e);
						e++;
					}
					//System.out.println(strn2);
					String res;
			if(strn1.equals(""))
				res=""+(Double.parseDouble("1")*Double.parseDouble(strn2));
			else if(strn2.equals(""))
				res=""+(Double.parseDouble(strn1)*Double.parseDouble("1"));
			else
				res=""+(Double.parseDouble(strn1)*Double.parseDouble(strn2));

					String replacee=strn1+equ.charAt(idx)+strn2;
					equ=equ.replace(replacee,res);
					System.out.println(equ);
					actionPerformed(ae);
				}
			}
			else if(equ.indexOf("/")!=-1 && equ.indexOf("x")==-1)
			{
				addFlag=0;
				int idx=equ.indexOf('/');
					int s=idx-1;
					int e=idx+1;
					while(s>-1)
					{
						if(equ.charAt(s) == '+'||equ.charAt(s) == 'x'||equ.charAt(s) == '-')
							break;
						strn1=strn1+equ.charAt(s);
						s--;
					}
					strn1=new String(new StringBuffer(strn1).reverse());
					//System.out.println(strn1);
					while(e<equ.length())
					{
						if(equ.charAt(e) == '+'||equ.charAt(e) == 'x'||equ.charAt(e) == '-')
							break;
						strn2=strn2+equ.charAt(e);
						e++;
					}
					//System.out.println(strn2);
					String res;
			if(strn1.equals(""))
				res=""+(Double.parseDouble("0")/Double.parseDouble(strn2));
			else if(strn2.equals(""))
				res=""+(Double.parseDouble(strn1)/Double.parseDouble("1"));
			else
				res=""+(Double.parseDouble(strn1)/Double.parseDouble(strn2));

					String replacee=strn1+equ.charAt(idx)+strn2;
					equ=equ.replace(replacee,res);
					System.out.println(equ);
					actionPerformed(ae);
			}
			else if(equ.indexOf("/")==-1 && equ.indexOf("x")!=-1)
			{
				addFlag=0;
				int idx=equ.indexOf('x');
					int s=idx-1;
					int e=idx+1;
					while(s>-1)
					{
						if(equ.charAt(s) == '+'||equ.charAt(s) == '/'||equ.charAt(s) == '-')
							break;
						strn1=strn1+equ.charAt(s);
						s--;
					}
					strn1=new String(new StringBuffer(strn1).reverse());
					//System.out.println(strn1);
					while(e<equ.length())
					{
						if(equ.charAt(e) == '+'||equ.charAt(e) == '/'||equ.charAt(e) == '-')
							break;
						strn2=strn2+equ.charAt(e);
						e++;
					}
					//System.out.println(strn2);
			String res;
			if(strn1.equals(""))
				res=""+(Double.parseDouble("1")*Double.parseDouble(strn2));
			else if(strn2.equals(""))
				res=""+(Double.parseDouble(strn1)*Double.parseDouble("1"));
			else
				res=""+(Double.parseDouble(strn1)*Double.parseDouble(strn2));

					String replacee=strn1+equ.charAt(idx)+strn2;
					equ=equ.replace(replacee,res);
					System.out.println(equ);
					actionPerformed(ae);
			}
		}
		else if(equ.indexOf("+")!=-1||equ.indexOf("-")!=-1)
		{
			addFlag=0;
			if(equ.indexOf("+")!=-1 && equ.indexOf("-")!=-1)
			{
				int idx1=equ.indexOf("+");
				int idx2=equ.indexOf("-");
				
				if(idx1<idx2)
				{
					int idx=equ.indexOf('+');
					int s=idx-1;
					int e=idx+1;
					while(s>-1)
					{
						if(equ.charAt(s) == '-')
							break;
						strn1=strn1+equ.charAt(s);
						s--;
					}
					strn1=new String(new StringBuffer(strn1).reverse());
					//System.out.println(strn1);
					while(e<equ.length())
					{
						if(equ.charAt(e) == '-')
							break;
						strn2=strn2+equ.charAt(e);
						e++;
					}
					//System.out.println(strn2);
					String res;
			if(strn1.equals(""))
				res=""+(Double.parseDouble("0")+Double.parseDouble(strn2));
			else if(strn2.equals(""))
				res=""+(Double.parseDouble(strn1)+Double.parseDouble("0"));
			else
				res=""+(Double.parseDouble(strn1)+Double.parseDouble(strn2));

					String replacee=strn1+equ.charAt(idx)+strn2;
					equ=equ.replace(replacee,res);
					System.out.println(equ);
					actionPerformed(ae);
				}
				else if(idx1>idx2)
				{
					int idx=equ.indexOf('-');
					int s=idx-1;
					int e=idx+1;
					while(s>-1)
					{
						if(equ.charAt(s) == '+')
							break;
						strn1=strn1+equ.charAt(s);
						s--;
					}
					strn1=new String(new StringBuffer(strn1).reverse());
					//System.out.println(strn1);
					while(e<equ.length())
					{
						if(equ.charAt(e) == '+')
							break;
						strn2=strn2+equ.charAt(e);
						e++;
					}
					//System.out.println(strn2);
					String res;
			if(strn1.equals(""))
				res=""+(Double.parseDouble("0")-Double.parseDouble(strn2));
			else if(strn2.equals(""))
				res=""+(Double.parseDouble(strn1)-Double.parseDouble("0"));
			else
				res=""+(Double.parseDouble(strn1)-Double.parseDouble(strn2));

					String replacee=strn1+equ.charAt(idx)+strn2;
					equ=equ.replace(replacee,res);
					System.out.println(equ);
					actionPerformed(ae);
				}
			}
			else if(equ.indexOf("+")==-1 && equ.indexOf("-")!=-1)
			{
				addFlag=0;
				int idx=equ.indexOf('-');
				int s=idx-1;
					int e=idx+1;
					while(s>-1)
					{
						if(equ.charAt(s) == '+')
							break;
						strn1=strn1+equ.charAt(s);
						s--;
					}
					strn1=new String(new StringBuffer(strn1).reverse());
					//System.out.println(strn1);
					while(e<equ.length())
					{
						if(equ.charAt(e) == '+')
							break;
						strn2=strn2+equ.charAt(e);
						e++;
					}
					//System.out.println(strn2);
					String res;
			if(strn1.equals(""))
				res=""+(Double.parseDouble("0")-Double.parseDouble(strn2));
			else if(strn2.equals(""))
				res=""+(Double.parseDouble(strn1)-Double.parseDouble("0"));
			else
				res=""+(Double.parseDouble(strn1)-Double.parseDouble(strn2));

					String replacee=strn1+equ.charAt(idx)+strn2;
					equ=equ.replace(replacee,res);
					System.out.println(equ);
					actionPerformed(ae);
			}
			else if(equ.indexOf("+")!=-1 && equ.indexOf("-")==-1)
			{
				addFlag=0;
				System.out.println("Hello");
				int idx=equ.indexOf('+');
				int s=idx-1;
					int e=idx+1;
					//System.out.println("Valid char "+e);
					while(s>-1)
					{
						if(equ.charAt(s) == '-')
							break;
						strn1=strn1+equ.charAt(s);
						s--;
					}
					strn1=new String(new StringBuffer(strn1).reverse());
					System.out.println(strn1);
					System.out.println(e+" "+equ.length());
					while(e<equ.length())
					{
						System.out.println("Iterator");
						if(equ.charAt(e) == '-')
							break;
						strn2=strn2+equ.charAt(e);
						e++;
					}
					System.out.println("String 2 "+strn2);
					String res;
			if(strn1.equals(""))
				res=""+(Double.parseDouble("0")+Double.parseDouble(strn2));
			else if(strn2.equals(""))
				res=""+(Double.parseDouble(strn1)+Double.parseDouble("0"));
			else
				res=""+(Double.parseDouble(strn1)+Double.parseDouble(strn2));

					String replacee=strn1+equ.charAt(idx)+strn2;
					equ=equ.replace(replacee,res);
					System.out.println("Changed equ "+equ);
					actionPerformed(ae);
			}
		}
		else
		{
			//f=0;
			addFlag=1;
			output.setText(equ);
			equ="";
		}
	}
	public void textValueChanged(TextEvent te)
	{
		
	}
}