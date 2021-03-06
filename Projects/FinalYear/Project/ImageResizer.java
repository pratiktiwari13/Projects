package Project;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class ImageResizer
{	
	public static void resize(String inputImagePath, int scaledWidth, int scaledHeight) throws IOException 
	{
		int index=inputImagePath.lastIndexOf('.');
		String format=inputImagePath.substring(index+1);
		//System.out.println(format);
		File inputFile = new File(inputImagePath);
		BufferedImage inputImage = ImageIO.read(inputFile);
		BufferedImage outputImage = new BufferedImage(scaledWidth,scaledHeight, inputImage.getType());
		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(inputImage,0,0,scaledWidth, scaledHeight, null);
		g2d.dispose();
		ImageIO.write(outputImage,format,new File("Demo"+inputFile.getName()));
	}		
}

/*class Main
{
	public static void main(String args[])
	{try
	{
		ImageResizer.resize("Demoniks.png",50,50);
	}
	catch (Exception e)
	{
	}
		
	}
}*/