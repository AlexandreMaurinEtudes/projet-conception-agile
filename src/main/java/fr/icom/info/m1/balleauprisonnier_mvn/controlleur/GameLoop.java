package fr.icom.info.m1.balleauprisonnier_mvn.controlleur;

import java.util.ArrayList;

import fr.icom.info.m1.balleauprisonnier_mvn.modeles.Field;
import fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.vue.VueLoop;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class GameLoop {
	GraphicsContext gc;
	ArrayList<String> input = new ArrayList<String>();

	public GameLoop(Field field) {
		this.gc = field.getGraphicsContext2D();
		this.input = field.input;
		Player[] joueurs = field.joueurs;
		// Player[] joueursIA = field.joueursIA;
		
		VueLoop vue = VueLoop.getInstance();
		vue.init(field);
		Controles controles = Controles.getInstance();
		controles.init(joueurs, input);
		
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				// On nettoie le canvas a chaque frame
				vue.refreshCanvas();
				//touches claviers
				controles.gameControles();
				//affichage joueur
				vue.entitiesRender(joueurs);
			}
		}.start(); // On lance la boucle de rafraichissement
	}
}
