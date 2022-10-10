package fr.icom.info.m1.balleauprisonnier_mvn;

import java.util.ArrayList;

import fr.icom.info.m1.balleauprisonnier_mvn.entities.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Affichage {
	GraphicsContext gc;
	ArrayList<String> input = new ArrayList<String>();

	Affichage(Field field) {
		this.gc = field.getGraphicsContext2D();
		this.input = field.input;
		Player[] joueurs = field.joueurs;
		// Player[] joueursIA = field.joueursIA;

		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				// On nettoie le canvas a chaque frame
				gc.setFill(Color.LIGHTGRAY);
				gc.fillRect(0, 0, field.width, field.height);

				// Deplacement et affichage des joueurs
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

					joueurs[i].display();
				}

				/*
				 * for (int i = 0; i < joueursIA.length; i++) { joueursIA[i].display(); }
				 */

				// balle.display();

			}
		}.start(); // On lance la boucle de rafraichissement
	}
}
