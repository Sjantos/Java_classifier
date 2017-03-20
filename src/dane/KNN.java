package dane;

//Program posiada proste GUI, wystarczy odpaliæ klasê Main.
//Nale¿y podaæ nazwê pliku i mo¿na testowaæ klasyfikatory,
//dla danych 2-wymiarowych w³¹czy siê tak¿e rysunek.
//Wynik klasyfikacji (%poprawnych) pojawi siê na startowym okienku.
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import gui.MainFrame;

public class KNN 
{
	static class Dist
	{
		double dist = 0.0;
		String className = "";
		
		public Dist(double d, String s)
		{
			dist = d;
			className = s;
		}
		public int compareTo(Dist d)
		{
			if(dist<d.dist) return -1;
			if(dist>d.dist) return 1;
			return 0;
		}
		public int compareClass(Dist d)
		{
			return className.compareTo(d.className);
		}
	}
	
	
	public KNN(String file, JLabel result)
	{
		Integer k = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter K:", "OK", JOptionPane.PLAIN_MESSAGE));
		CSVin predict = new CSVin(file);
		
		CSVin learn = new CSVin(file, 10);
		predict.removeTestPoints(learn);

		ArrayList<Dist> distances;
		double correctRate = 0.0;
		
		for(int i=0; i<predict.getSize(); i++)
		{
//			{//Chyba minimalnie szybsza metoda, ale daje gorszy correctRate
//				distances = new ArrayList<>();
//				for(int j=0; j<learn.getSize(); j++)
//					distances.add(new Dist(predict.getPoint(i).distance(learn.getCoordArray(j)), learn.getPoint(j).getClassName()));
//				ArrayList<Dist> kNearest = new ArrayList<>();
//				for(int n=0; n<k; n++)
//				{
//					Dist mindist = distances.get(0);
//					int index = 0;
//					for(int j=0; j<distances.size(); j++)
//					{
//						if(distances.get(j).dist<mindist.dist) 
//						{
//							mindist = distances.get(j);
//							index = j;
//						}
//					}
//					kNearest.add(distances.remove(index));
//				}
//				HashMap<String, Integer> map = new HashMap<>();
//				for(int j=0; j<kNearest.size(); j++)
//				{
//					if(!map.containsKey(kNearest.get(j).className)) 
//					{
//						map.put(kNearest.get(j).className, 1);
//					}
//					else
//					{
//						map.replace(kNearest.get(j).className, map.get(kNearest.get(j).className), map.get(kNearest.get(j).className)+1);
//					}
//				}
//				int max = 0;
//				String predictedClass = "";
//				Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
//				while(it.hasNext())
//				{
//					Map.Entry<String, Integer> pair = it.next();
//					if(pair.getValue()>max) predictedClass = pair.getKey();
//				}
//				predict.getPoint(i).setPredictedClassName(predictedClass);
//				if(predict.getPoint(i).getClassName().equals(predict.getPoint(i).getPredictedClassName())) correctRate+=1;
//			}
			
			
			{	//Ma³o optymalna metoda z sortowaniem, ale daje lepszy correctRate
				distances = new ArrayList<>();
				for(int j=0; j<learn.getSize(); j++)
					distances.add(new Dist(predict.getPoint(i).distance(learn.getCoordArray(j)), learn.getPoint(j).getClassName()));
				distances.sort(new Comparator<Dist>() {
					@Override
					public int compare(Dist o1, Dist o2) {
						return o1.compareTo(o2);
					}
				});
				ArrayList<Integer> classCounter = new ArrayList<>();
				for(int j=0; j<learn.getClassCount(); j++)
					classCounter.add(0);
				for(int j=0; j<k; j++)
				{
					classCounter.set(learn.getIndexOfClass(distances.get(j).className), 
															classCounter.get(learn.getIndexOfClass(distances.get(j).className))+1);
				}
				int indexofMax = 0;
				int max = classCounter.get(0);
				for(int j=1; j<classCounter.size(); j++)
				{
					if(classCounter.get(j)>max)
					{
						max = classCounter.get(j);
						indexofMax = j;
					}
				}
				predict.getPoint(i).setPredictedClassName(learn.getClass(indexofMax));
				if(predict.getPoint(i).getClassName().equals(predict.getPoint(i).getPredictedClassName())) correctRate+=1;
			}
			
		}
		correctRate/=predict.getSize();
		
		new MainFrame(predict);
		
		result.setText("CORRECT RATE: "+correctRate*100);
	}
}
