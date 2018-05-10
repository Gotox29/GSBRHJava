package Vues;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box.Filler;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Message extends JFrame implements ActionListener {

	private String message;
	private Component cible;

	public Message(String titre, String message){

		this.message = message;
		
		//Définir la taille de la fenêtre.
		this.setSize(250, 120);

		//Pour une autre position : this.setLocation(x,y);
		this.setLocationRelativeTo(null);

		//ajout des éléments de la fenêtre
		this.iniComposants();

		//Définir un titre pour la fenêtre.
		this.setTitle(titre);

		//La fenêtre ne peut pas être redimensionnée.
		this.setResizable(true);

		//On peux la fermer
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);

	}
	public Message(String titre, String message, Component cible){

		this.message = message;
		this.cible = cible;
		//Définir la taille de la fenêtre.
		this.setSize(400, 150);

		//Pour une autre position : this.setLocation(x,y);
		this.setLocationRelativeTo(null);

		//ajout des éléments de la fenêtre
		this.iniComposants();

		//Définir un titre pour la fenêtre.
		this.setTitle(titre);

		//La fenêtre ne peut pas être redimensionnée.
		this.setResizable(true);

		//On peux la fermer
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);

	}

	//	Initialisation des composants de la fenêtre
	public void iniComposants(){

		JButton valider = new JButton();
		valider.setText("Valider");
		valider.addActionListener(this);
		JButton annuler = new JButton();
		annuler.addActionListener(this);
		annuler.setText("Annuler");

		// Panneau principale de la fenêtre 
		JPanel pan = new JPanel();
		
		// panneau des boutons
		JPanel boutons = new JPanel();
		
		GridBagLayout grilleB = new GridBagLayout();
		boutons.setLayout(grilleB);
		GridBagConstraints constraints = new GridBagConstraints();
		//constraints.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		//constraints.weighty = 2.0;
		//constraints.weightx = 0.0;
		
		boutons.add(valider,constraints);
		boutons.add(new Filler(new Dimension(10, 10), new Dimension(50, 50), new Dimension(100, 100)),constraints);
		boutons.add(annuler,constraints);

		JLabel text = new JLabel(this.message);
		
		pan.add(text);

		//this.setContentPane(pan);

		//On définit le layout à utiliser sur le content pane
		this.setLayout(new BorderLayout());

		//On ajoute les element au contenant principale
		this.getContentPane().add(boutons, BorderLayout.SOUTH);
		this.getContentPane().add(new Filler(new Dimension(10, 10), new Dimension(10, 5), new Dimension(100, 100)), BorderLayout.NORTH);
		this.getContentPane().add(pan, BorderLayout.CENTER);

	}
	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		System.out.println(action);
		switch(action){
			case "Valider":
				System.out.println("ok");
				this.dispose();
				break;
			case "Annuler":
				System.out.println("retour");
				this.dispose();
				break;
		}
	}

}
