package dane;

//Program posiada proste GUI, wystarczy odpaliæ klasê Main.
//Nale¿y podaæ nazwê pliku i mo¿na testowaæ klasyfikatory,
//dla danych 2-wymiarowych w³¹czy siê tak¿e rysunek.
//Wynik klasyfikacji (%poprawnych) pojawi siê na startowym okienku.
import java.util.ArrayList;

public class NM 
{
	private ArrayList<ClassMean> classes = new ArrayList<>();
	
	public NM(CSVin dane)
	{
		for(int i=0; i<dane.getClassCount(); i++)
		{
//			ClassMean ma tablice wspó³rzêdnych œrodkowego punktu dla danej klasy
			classes.add(new ClassMean(dane, i));
		}
	}
	
	@Override
	public String toString()
	{
		StringBuilder tmp = new StringBuilder();
		for(int i=0; i<classes.size(); i++)
		{
			tmp.append(classes.get(i).toString());
		}
		return tmp.toString();
	}
	
	public ClassMean getClassMean(int i) { return classes.get(i); }
	public ArrayList<ClassMean> getclasses() { return classes; }
}
