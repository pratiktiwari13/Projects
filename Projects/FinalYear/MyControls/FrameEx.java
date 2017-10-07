import java.awt.*;
import java.awt.event.*;

 public class MyContainer extends Container {         
 public void paint(Graphics g) 
 {             
 // paint my contents first...             // then, make sure lightweight children paint                                      
 super.paint(g);          
 }     
 }                                        
