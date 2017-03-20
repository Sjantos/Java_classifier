package gui;

//Program posiada proste GUI, wystarczy odpaliæ klasê Main.
//Nale¿y podaæ nazwê pliku i mo¿na testowaæ klasyfikatory,
//dla danych 2-wymiarowych w³¹czy siê tak¿e rysunek.
//Wynik klasyfikacji (%poprawnych) pojawi siê na startowym okienku.
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dane.NearestMeanClassifier;

public class Main extends JFrame
{
	public Main()
	{
		super("Klasyfikatory");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(6, 1));
		JLabel text = new JLabel("Podaj nazwê pliku");
		container.add(text);
		JTextField fileInput = new JTextField();
		container.add(fileInput);
		JLabel info = new JLabel("Procent poprawnych przypisañ");
		container.add(info);
		JLabel result = new JLabel();
		container.add(result);
		JButton NM = new JButton("Klasyfikator NM");
		NM.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new dane.NearestMeanClassifier(fileInput.getText(), result);
			}
		});
		container.add(NM);
		
		JButton KNN = new JButton("Klasyfikator KNN");
		KNN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new dane.KNN(fileInput.getText(), result);
			}
		});
		container.add(KNN);
		add(container);
		
		
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new Main();
	}
}
