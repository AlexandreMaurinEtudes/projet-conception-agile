package fr.icom.info.m1.balleauprisonnier_mvn.modeles;

import fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities.Player;
import fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities.PlayerIA;
import fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities.Projectile;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class Field extends Canvas {

	/** Joueurs */
	public Player[] joueurs = new Player[2];
	public Player[] joueursIA = new Player[4];
	/** balle */
	public Projectile balle;
	/** Couleurs possibles */
	String[] colorMap = new String[] { "blue", "green", "orange", "purple", "yellow" };

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
		joueurs[1] = new Player(this, colorMap[1], w / 2, 45, "top", 2);

		/* IA */
		joueursIA[0] = new PlayerIA(this, colorMap[0], w / 3, h - 70, "bottom", 5);
		joueursIA[2] = new PlayerIA(this, colorMap[0], w - w / 3, h - 70, "bottom", 5);

		joueursIA[1] = new PlayerIA(this, colorMap[1], w / 3, 20, "top", 2);
		joueursIA[3] = new PlayerIA(this, colorMap[1], w - w / 3, 20, "top", 2);
		
		/* balle */
		balle = Projectile.init(this, 0, 0);
		balle.attach(joueurs[1]); //on attache la balle au joueur 1
		
	}
	
	public Player[] getJoueurs() {
		return joueurs;
	}

	public Player[] getJoueursIA() {
		return joueursIA;
	}
}
