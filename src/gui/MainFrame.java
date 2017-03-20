package gui;

//Program posiada proste GUI, wystarczy odpaliæ klasê Main.
//Nale¿y podaæ nazwê pliku i mo¿na testowaæ klasyfikatory,
//dla danych 2-wymiarowych w³¹czy siê tak¿e rysunek.
//Wynik klasyfikacji (%poprawnych) pojawi siê na startowym okienku.
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import dane.CSVin;

public class MainFrame extends JFrame
{
	public MainFrame(CSVin dane)
	{
		super("Klasyfikatory");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height-35);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		PaintPanel p = new PaintPanel(dane);
		JScrollPane scrolledPaint = new JScrollPane(p);
		scrolledPaint.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrolledPaint.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scrolledPaint);
		
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new MainFrame(new CSVin("ring2D.csv"));
	}
}
