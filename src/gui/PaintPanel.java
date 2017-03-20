package gui;

//Program posiada proste GUI, wystarczy odpaliæ klasê Main.
//Nale¿y podaæ nazwê pliku i mo¿na testowaæ klasyfikatory,
//dla danych 2-wymiarowych w³¹czy siê tak¿e rysunek.
//Wynik klasyfikacji (%poprawnych) pojawi siê na startowym okienku.
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import dane.CSVin;
import dane.NM;

public class PaintPanel  extends JPanel
{
	private CSVin dane;
	private ArrayList<Color> colorList = new ArrayList<>();
	
	public PaintPanel(CSVin d)
	{
		super();
		dane = d;
		for(int i=0; i<dane.getClassCount(); i++)
		{
			colorList.add(new Color(200*i));
		}
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		double xScale = (screen.getWidth()-50)/(dane.getMinHorizontal().abs().add(dane.getMaxHorizontal().abs())).doubleValue();
		double yScale = (screen.getHeight()-50)/(dane.getMinVertical().abs().add(dane.getMaxVertical().abs())).doubleValue();
		
		for(int i=0; i<dane.getSize(); i++)
		{
			g2d.setColor(colorList.get(dane.getIndexOfClass(dane.getPointClass(i))));
			double x = 0.0, y = 0.0;
			if(dane.getMinHorizontal().signum()<0)
				x = dane.getCoordArray(i)[0].add(dane.getMinHorizontal().abs()).doubleValue();
			if(dane.getMinHorizontal().signum()>0)
				x = dane.getCoordArray(i)[0].subtract(dane.getMinHorizontal().abs()).doubleValue();
			if(dane.getMinVertical().signum()<0)
				y = dane.getCoordArray(i)[1].add(dane.getMinVertical().abs()).doubleValue();
			if(dane.getMinVertical().signum()>0)
				y = dane.getCoordArray(i)[1].subtract(dane.getMinVertical().abs()).doubleValue();
			Ellipse2D e = new Ellipse2D.Double(xScale*x, yScale*y, 3, 3);
			g2d.fill(e);
			g2d.draw(e);
		}
	}
}
