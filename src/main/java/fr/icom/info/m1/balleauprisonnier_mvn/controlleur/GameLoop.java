package fr.icom.info.m1.balleauprisonnier_mvn.controlleur;

import java.util.ArrayList;

import fr.icom.info.m1.balleauprisonnier_mvn.modeles.Field;
import fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.vue.VueLoop;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameLoop {
	GraphicsContext gc;
	ArrayList<String> input = new ArrayList<String>();

	public GameLoop(Field field) {
		this.gc = field.getGraphicsContext2D();
		this.input = field.input;
		Player[] joueurs = field.joueurs;
		// Player[] joueursIA = field.joueursIA;
		
		VueLoop vue = new VueLoop(gc); //attention devra être changé en singleton
		Controles controles = new Controles(joueurs, input); //idem
		
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				vue.refreshCanvas(field.width, field.height);
				controles.mouvements();
			}
		}.start(); // On lance la boucle de rafraichissement
	}
}
