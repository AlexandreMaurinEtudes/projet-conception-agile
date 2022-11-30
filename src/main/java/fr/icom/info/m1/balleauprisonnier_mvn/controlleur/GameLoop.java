package fr.icom.info.m1.balleauprisonnier_mvn.controlleur;

import java.util.ArrayList;

import fr.icom.info.m1.balleauprisonnier_mvn.modeles.Field;
import fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities.Projectile;
import fr.icom.info.m1.balleauprisonnier_mvn.vue.VueLoop;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class GameLoop {
	/** Tableau traçant les evenements **/
	private ArrayList<String> input = new ArrayList<String>();

	public GameLoop(Field field) {
		Player[] joueurs = field.joueurs;
		// Player[] joueursIA = field.joueursIA;
		Projectile balle = field.balle;
		
		VueLoop vue = VueLoop.getInstance();
		vue.init(field);
		Controles controles = Controles.getInstance();
		controles.init(joueurs, input);
		
		/**event listener touches  ajout à input**/
		/* touche pressée */
		field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				// only add once... prevent duplicates
				if (!input.contains(code))
					input.add(code);
			}
		});
		/* touche relachée */
		field.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				input.remove(code);
			}
		});

		
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				// On nettoie le canvas a chaque frame
				vue.refreshCanvas();
				//touches claviers
				controles.gameControles();
				//on met à jour la position de la balle
				balle.updatePosition();
				//affichage joueurs
				vue.entitiesRender(joueurs);
				//affichage balle
				balle.display();
				
			}
		}.start(); // On lance la boucle de rafraichissement
	}
}
