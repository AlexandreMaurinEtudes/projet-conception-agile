package fr.icom.info.m1.balleauprisonnier_mvn.controlleur;

import java.util.ArrayList;

import fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities.Player;

public class Controles {
	private static Controles instance = new Controles();
	private Player[] joueurs;
	private ArrayList<String> input;
	private boolean pause = false;
	
	
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
		//check pause
		pauseControle();
		// touches de deplacement des joueurs
		for (int i = 0; i < joueurs.length; i++) {
			//si le joueur est mort on passe directement au suivant
			if (!joueurs[i].isAlive()) continue;
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
	public boolean getPaused() {
		return pause;
	}

	public void pauseControle() {
		if (input.contains("ESCAPE") || input.contains("P")) {
			if (pause) pause = false;
			else pause = true;
			input.removeAll(input);
		}
		
	}
}
