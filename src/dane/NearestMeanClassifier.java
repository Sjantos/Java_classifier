package dane;

//Program posiada proste GUI, wystarczy odpaliæ klasê Main.
//Nale¿y podaæ nazwê pliku i mo¿na testowaæ klasyfikatory,
//dla danych 2-wymiarowych w³¹czy siê tak¿e rysunek.
//Wynik klasyfikacji (%poprawnych) pojawi siê na startowym okienku.
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import gui.MainFrame;

public class NearestMeanClassifier 
{
	
	public NearestMeanClassifier(String file, JLabel result)
	{
		CSVin predict = new CSVin(file);
		
		CSVin learn = new CSVin(file, 10);
		predict.removeTestPoints(learn);
		NM NMclassifier = new NM(learn);
		ArrayList<Double> distances = new ArrayList<>();
		double correctRate = 0.0;
		for(int i=0; i<predict.getSize(); i++)
		{
			distances = new ArrayList<>();
			for(int j=0; j<predict.getClassCount(); j++)
			{
				distances.add(predict.getPoint(i).distance(NMclassifier.getClassMean(j).getMean()));
			}
			double mindist = distances.get(0).doubleValue();
			for(int j=0; j<distances.size(); j++)
			{
				if(distances.get(j)<mindist) mindist = distances.get(j);
			}
			predict.getPoint(i).setPredictedClassName(NMclassifier.getClassMean(distances.indexOf(mindist)).getClassName());
			if(predict.getPoint(i).correct()) correctRate+=1;
		}
		correctRate /= predict.getSize();
		
		
		new MainFrame(predict);
		
		result.setText("CORRECT RATE: "+correctRate*100);
	}
}
