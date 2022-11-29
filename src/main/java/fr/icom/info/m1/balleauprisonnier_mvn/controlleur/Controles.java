package fr.icom.info.m1.balleauprisonnier_mvn.controlleur;

import java.util.ArrayList;

import fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities.Player;

public class Controles {
	private static Controles instance = new Controles();
	private Player[] joueurs;
	private ArrayList<String> input;
	
	private Controles() {}
	
	public static Controles getInstance() {
		return instance;
	}
	
	public void init(Player[] joueurs, ArrayList<String> input) {
		this.joueurs = joueurs;
		this.input = input;
	}
	
	public void gameControles() {
		//touche de contrôles en jeu
		//menu pause
		if (input.contains("ESCAPE")) {
			System.out.println("PAUSE"); //TODO:Mettre le jeu en pause
			return; //on retourne pour ne pas exécuter le code après
		}
		// touches de deplacement des joueurs
		for (int i = 0; i < joueurs.length; i++) {
			if (i == 0 && input.contains("LEFT")) {
				joueurs[i].moveLeft();
			}
			if (i == 0 && input.contains("RIGHT")) {
				joueurs[i].moveRight();
			}
			if (i == 0 && input.contains("UP")) {
				joueurs[i].turnLeft();
			}
			if (i == 0 && input.contains("DOWN")) {
				joueurs[i].turnRight();
			}
			if (i == 0 && input.contains("SPACE")) {
				joueurs[i].shoot();
			}
			if (i == 1 && input.contains("Q")) {
				joueurs[i].moveLeft();
			}
			if (i == 1 && input.contains("D")) {
				joueurs[i].moveRight();
			}
			if (i == 1 && input.contains("Z")) {
				joueurs[i].turnLeft();
			}
			if (i == 1 && input.contains("S")) {
				joueurs[i].turnRight();
			}
			if (i == 1 && input.contains("A")) {
				joueurs[i].shoot();
			}
		}
	}
}
