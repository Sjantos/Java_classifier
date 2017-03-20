package dane;

//Program posiada proste GUI, wystarczy odpaliæ klasê Main.
//Nale¿y podaæ nazwê pliku i mo¿na testowaæ klasyfikatory,
//dla danych 2-wymiarowych w³¹czy siê tak¿e rysunek.
//Wynik klasyfikacji (%poprawnych) pojawi siê na startowym okienku.
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class CSVin 
{
	private ArrayList<Point> points = new ArrayList<>();
	private ArrayList<String> classes = new ArrayList<>();
	private int dimension = 0;
	private int classID = 0, classCount = 0;
	private BigDecimal maxHorizontal = BigDecimal.valueOf(0.0), maxVertical = BigDecimal.valueOf(0.0),
			minHorizontal = BigDecimal.valueOf(0.0), minVertical = BigDecimal.valueOf(0.0);
	
	public CSVin(String file)
	{
		File in = new File(file);
		Scanner fileRead = null;
		String tmp = "";
		int numRows = 0;
		try {
			fileRead = new Scanner(in);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		tmp=fileRead.nextLine();
		String[] header = tmp.split(";");
		dimension = header.length-2;
		classID = dimension+1;
		
		boolean first = true;
		while(fileRead.hasNextLine())
		{
			String line = fileRead.nextLine();
			Point p = new Point(line.split(";"));
			if(first)
			{
				first=false;
				maxHorizontal = p.getCoordinates()[0];
				maxVertical = p.getCoordinates()[1];
				minHorizontal = p.getCoordinates()[0];
				minVertical = p.getCoordinates()[1];
			}
			
			if(!classes.contains(p.getClassName()))
			{
				classes.add(p.getClassName());
				classCount++;
			}
				
			points.add(p);
			numRows++;
		}
		points.sort(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return o1.compareClassName(o2);
			}
		});
		
		for(int i=0; i<points.size(); i++)
		{
			if(points.get(i).getCoordinates()[0].compareTo(maxHorizontal)>0) maxHorizontal = points.get(i).getCoordinates()[0];
			if(points.get(i).getCoordinates()[1].compareTo(maxVertical)>0) maxVertical= points.get(i).getCoordinates()[1];
			if(points.get(i).getCoordinates()[0].compareTo(minHorizontal)<0) minHorizontal = points.get(i).getCoordinates()[0];
			if(points.get(i).getCoordinates()[1].compareTo(minVertical)<0) minVertical= points.get(i).getCoordinates()[1];
		}
	}
	
	public CSVin(String file, int num)
	{
		File in = new File(file);
		Scanner fileRead = null;
		String tmp = "";
		int numRows = 0;
		try {
			fileRead = new Scanner(in);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		tmp=fileRead.nextLine();
		String[] header = tmp.split(";");
		dimension = header.length-2;
		classID = dimension+1;
		
		boolean first = true;
		while(fileRead.hasNextLine())
		{
			String line = fileRead.nextLine();
			Point p = new Point(line.split(";"));
			if(first)
			{
				first=false;
				maxHorizontal = p.getCoordinates()[0];
				maxVertical = p.getCoordinates()[1];
				minHorizontal = p.getCoordinates()[0];
				minVertical = p.getCoordinates()[1];
			}
			
			if(!classes.contains(p.getClassName()))
			{
				classes.add(p.getClassName());
				classCount++;
			}
				
			points.add(p);
			numRows++;
		}
		
		points.sort(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return o1.compareClassName(o2);
			}
		});
		for(int i=points.size()-1; i>0; i--)
		{
			if(i%num!=0) points.remove(i);
		}
		for(int i=0; i<points.size(); i++)
		{
		}
		
		for(int i=0; i<points.size(); i++)
		{
			if(points.get(i).getCoordinates()[0].compareTo(maxHorizontal)>0) maxHorizontal = points.get(i).getCoordinates()[0];
			if(points.get(i).getCoordinates()[1].compareTo(maxVertical)>0) maxVertical= points.get(i).getCoordinates()[1];
			if(points.get(i).getCoordinates()[0].compareTo(minHorizontal)<0) minHorizontal = points.get(i).getCoordinates()[0];
			if(points.get(i).getCoordinates()[1].compareTo(minVertical)<0) minVertical= points.get(i).getCoordinates()[1];
		}
	}
	public int getSize() { return points.size(); }
	public String getPointClass(int i) { return points.get(i).getPredictedClassName(); }//ClassName(); }
	public BigDecimal[] getCoordArray(int i) { return points.get(i).getCoordinates(); } 
	public BigDecimal getMaxHorizontal() { return maxHorizontal; }
	public BigDecimal getMaxVertical() { return maxVertical; }
	public BigDecimal getMinHorizontal() { return minHorizontal; }
	public BigDecimal getMinVertical() { return minVertical; }
	public int getClassCount() { return classCount; }
	public String getClass(int i) { return classes.get(i); }
	public Point getPoint(int i) { return points.get(i); }
	public int getIndexOfClass(String s)
	{
		for(int i=0; i<classes.size(); i++)
		{
			if(classes.get(i).equals(s)) return i;
		}
		return -1;
	}
	
	@Override
	public String toString()
	{
		StringBuilder tmp = new StringBuilder();
		for(int i=0; i<points.size(); i++)
			tmp.append(points.get(i).toString()+"\n");
		return tmp.toString();
	}
	
	public void removeTestPoints(CSVin test)
	{
		for(int i=0; i<test.getSize(); i++)
		{
			points.remove(test.getPoint(i));
		}
	}
	
	public static void main(String[] args)
	{
		CSVin dane = new CSVin("banana2D.csv");
	}
}
