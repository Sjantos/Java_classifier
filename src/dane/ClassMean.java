package dane;

//Program posiada proste GUI, wystarczy odpaliæ klasê Main.
//Nale¿y podaæ nazwê pliku i mo¿na testowaæ klasyfikatory,
//dla danych 2-wymiarowych w³¹czy siê tak¿e rysunek.
//Wynik klasyfikacji (%poprawnych) pojawi siê na startowym okienku.

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;

public class ClassMean 
{
	private BigDecimal[] mean = null;
	private String className = "";
	private ArrayList<ArrayList<BigDecimal>> medianList = new ArrayList<>();
	
	public ClassMean(CSVin dane, int x)
	{
		className = dane.getClass(x);
		
		int dim = dane.getCoordArray(0).length;
		mean = new BigDecimal[dim];
		for(int i=0; i<dim; i++)
		{
			medianList.add(new ArrayList<>());
			
			for(int j=0; j<dane.getSize(); j++)
			{
				if(dane.getPointClass(j).equals(className))
				{
					medianList.get(i).add(dane.getCoordArray(j)[i]);
				}
			}
			
			medianList.get(i).sort(new Comparator<BigDecimal>() {
				@Override
				public int compare(BigDecimal o1, BigDecimal o2) {
					return o1.compareTo(o2);}});
			int medianIndex = Math.floorDiv(medianList.get(i).size(), 2);
			mean[i] = medianList.get(i).get(medianIndex);
			mean[i].divide(BigDecimal.valueOf(dane.getSize()), RoundingMode.HALF_UP);
		}
	}
	
	public BigDecimal[] getMean() { return mean; } 
	public String getClassName() { return className; }
	
	@Override
	public String toString()
	{
		StringBuilder tmp = new StringBuilder();
		tmp.append(className+" - mean: \n");
		for(int i=0; i<mean.length; i++)
		{
			tmp.append(mean[i]+"\n");
		}
		return tmp.toString();
	}
}
