var i=1;
var options=document.getElementsByName("options");
var currSelected;
var count=9;
for(i=1;i<=9;i++)
{
    document.getElementById("b"+i).onclick=function()
   	{
        for(i=0;i<options.length;i++)
        {
            if(options[i].checked)
            {
                currSelected=options[i];
                break;
            }
        }
        this.innerHTML=currSelected.value=="xoption"?"X":"0";
		
        this.onclick=null;
		var series=process();
        if(series!=null)
        {
			//console.log(series);
			repaint(series);
			deactivatelisteners();
			return;
		}
    };
}
function repaint(series)
{
	for(i=0;i<series.length;i++)
		document.getElementById(""+series[i]).style.color="green";
}
function deactivatelisteners()
{
	for(i=1;i<=9;i++)
		document.getElementById("b"+i).onclick=null;
}
function process()
{
	var series=[];
                                                   //Diagonals
    //top-left to bottom-right
    for(i=1;i<=5;i+=0)
    {
		//console.log(" diagonal test1 started");
        if(document.getElementById("b"+i).innerHTML == document.getElementById("b"+(i+4)).innerHTML && (document.getElementById("b"+i).innerHTML == "X" || document.getElementById("b"+i).innerHTML == "0"))
		{
			//console.log("comparing "+"b"+i+" with "+"b"+(i+4));
			series.push("b"+i);
			i+=4;
		}
        else
		{
			series=[];
			//console.log(" diagonal test 1 ended");
			break;
		}
    }
    if(i==9)
	{
		series.push("b"+i);
        return series;
	}
    //top-right to bottom-left
    for(i=3;i<=5;i+=0)
    {
		//console.log(" diagonal test2 started");
        if(document.getElementById("b"+i).innerHTML == document.getElementById("b"+(i+2)).innerHTML && (document.getElementById("b"+i).innerHTML == "X" || document.getElementById("b"+i).innerHTML == "0"))
		{
			series.push("b"+i);
			i+=2;
		}
        else
		{
			series=[];
			//console.log(" diagonal test2 ended");
			break;
		}
    }
    if(i==7)
	{
		series.push("b"+i);
        return series;                                                
	}
                                                    //Vertical    
    for(j=1;j<=3;j++)
    {
        for(i=j;i<=(j+3);i+=0)
        {
			//console.log("vertical test started");
            if(document.getElementById("b"+i).innerHTML == document.getElementById("b"+(i+3)).innerHTML && (document.getElementById("b"+i).innerHTML == "X" || document.getElementById("b"+i).innerHTML == "0"))
			{
				series.push("b"+i);
				i+=3;
			}
            else
			{
				//console.log(" vertical test ended");
				series=[];
				break;
			}
        }
        if(i==((j+3)+3))
		{
			series.push("b"+i);
			return series;
		}
    }
                                                    //Horizontal
    for(i=1;i<=3;i+=0)
    {
        for(j=i;j<(i+3);j++)
        {
			//console.log("horizontal test started");
            if(document.getElementById("b"+j).innerHTML == document.getElementById("b"+(j+1)).innerHTML && (document.getElementById("b"+j).innerHTML == "X" || document.getElementById("b"+j).innerHTML == "0"))
			{
				//console.log("comparing "+"b"+j+" with "+"b"+(j+1));
				series.push("b"+j);
			}
            else
                break;
        }
        if(j==(i+2))
		{
			series.push("b"+j);	
           return series;
		}
		i+=3;
    }
    return null;
}