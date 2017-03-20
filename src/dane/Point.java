package dane;

//Program posiada proste GUI, wystarczy odpaliæ klasê Main.
//Nale¿y podaæ nazwê pliku i mo¿na testowaæ klasyfikatory,
//dla danych 2-wymiarowych w³¹czy siê tak¿e rysunek.
//Wynik klasyfikacji (%poprawnych) pojawi siê na startowym okienku.
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class Point 
{
	private static final BigDecimal TWO = BigDecimal.valueOf(2);
	private BigDecimal[] coordinates = null;
	private String className = "", predictedClassName = className;
	private int dimension = 0;
	
	public Point(String[] line)
	{
		DecimalFormat df = new DecimalFormat();
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator(',');
		df.setDecimalFormatSymbols(symbol);
		dimension = line.length-2;
		coordinates = new BigDecimal[dimension];
		for(int i=1; i<line.length-1; i++)
		{
			line[i] = line[i].replaceAll(",", ".");
				coordinates[i-1] =  new BigDecimal(line[i]);
		}
		className = line[line.length-1];
		predictedClassName = className;
		
	}
	public BigDecimal[] getCoordinates() { return coordinates; }
	public String getClassName() { return className; }
	public String getPredictedClassName() { return predictedClassName; }
	public void setPredictedClassName(String s) { predictedClassName=s; }
	
	@Override
	public String toString()
	{
		StringBuilder tmp = new StringBuilder();
		for(int i=0; i<coordinates.length; i++)
		{
			tmp.append(coordinates[i]+"    ");
		}
		tmp.append("class: "+className);
		return tmp.toString();
	}
	
	public boolean equalTo(Point p)
	{
		for(int i=0; i<coordinates.length; i++)
		{
			if(coordinates[i]!=p.getCoordinates()[i]) return false;
		}
		if(!className.equals(p.getClassName())) return false;
		return true;
	}
	
	public int compareClassName(Point p)
	{
		return this.getClassName().compareTo(p.getClassName());
	}
	
	public double distance(BigDecimal dimension[])
	{
		double d = 0.0;
		for(int i=0; i<dimension.length; i++)
		{
			d+=(((coordinates[i].subtract(dimension[i])).multiply((coordinates[i].subtract(dimension[i])))).doubleValue());
		}
		return Math.sqrt(d);
	}
	
	public boolean correct() { return className.equals(predictedClassName); }
}
