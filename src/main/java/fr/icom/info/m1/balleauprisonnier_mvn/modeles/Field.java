package fr.icom.info.m1.balleauprisonnier_mvn.modeles;

import java.util.ArrayList;

import fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities.PlayerIA;
import fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities.Projectile;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class Field extends Canvas {

	/** Joueurs */
	public Player[] joueurs = new Player[2];
	Player[] joueursIA = new Player[4];
	Projectile balle = new Projectile(this, 300, 300, 0, 1);
	/** Couleurs possibles */
	String[] colorMap = new String[] { "blue", "green", "orange", "purple", "yellow" };
	/** Tableau traçant les evenements */
	public ArrayList<String> input = new ArrayList<String>();

	Scene scene;
	final GraphicsContext gc;
	public final int width;
	public final int height;

	/**
	 * Canvas dans lequel on va dessiner le jeu.
	 * 
	 * @param scene Scene principale du jeu a laquelle on va ajouter notre Canvas
	 * @param w     largeur du canvas
	 * @param h     hauteur du canvas
	 */
	public Field(Scene scene, int w, int h) {
		super(w, h);
		this.scene = scene;
		width = w;
		height = h;

		/** permet de capturer le focus et donc les evenements clavier et souris */
		this.setFocusTraversable(true);

		gc = this.getGraphicsContext2D();

		/** On initialise le terrain de jeu */
		joueurs[0] = new Player(this, colorMap[0], w / 2, h - 95, "bottom", 3);
		joueurs[0].display();

		joueurs[1] = new Player(this, colorMap[1], w / 2, 45, "top", 2);
		joueurs[1].display();

		/* IA */
		joueursIA[0] = new PlayerIA(this, colorMap[0], w / 3, h - 70, "bottom", 5);
		// joueursIA[0].display();
		joueursIA[2] = new PlayerIA(this, colorMap[0], w - w / 3, h - 70, "bottom", 5);
		joueursIA[2].display();

		joueursIA[1] = new PlayerIA(this, colorMap[1], w / 3, 20, "top", 2);
		// joueursIA[1].display();
		joueursIA[3] = new PlayerIA(this, colorMap[1], w - w / 3, 20, "top", 2);
		// joueursIA[3].display();

		/**
		 * Event Listener du clavier quand une touche est pressee on la rajoute a la
		 * liste d'input
		 * 
		 */
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				// only add once... prevent duplicates
				if (!input.contains(code))
					input.add(code);
			}
		});

		/**
		 * Event Listener du clavier quand une touche est relachee on l'enleve de la
		 * liste d'input
		 * 
		 */
		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				input.remove(code);
			}
		});

		/**
		 * 
		 * Boucle principale du jeu
		 * 
		 * handle() est appelee a chaque rafraichissement de frame soit environ 60 fois
		 * par seconde.
		 * 
		 */

	}

	public Player[] getJoueurs() {
		return joueurs;
	}

	public Player[] getJoueursIA() {
		return joueursIA;
	}

	// ------- TEST -------

	// TODO:supprimer fonctions tests
	public void supprimerJoueur() {
		joueurs[0] = null;
	}

	public void ajouterElement(Node e) { // supprimer ou refaire
		Platform.runLater(() -> this.scene.getRoot().getChildrenUnmodifiable().add(e));
		// this.scene.getRoot().getChildrenUnmodifiable().add(e);
		// //.getChildren().add(e);
	} // javafx de ses morts
}
