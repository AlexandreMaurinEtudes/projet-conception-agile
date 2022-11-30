package fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities;

import fr.icom.info.m1.balleauprisonnier_mvn.modeles.Field;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * 
 * Classe gerant un joueur
 *
 */
public class Player extends Entity {
	private double angle = 90; // rotation du joueur, devrait toujour être en 0 et 180
	private double step; // pas d'un joueur
	final String playerColor;
	public final String side;
	private double force = 1.0;

	// On une image globale du joueur
	Image directionArrow;
	private Sprite sprite;
	ImageView PlayerDirectionArrow;

	GraphicsContext graphicsContext;

	/**
	 * Constructeur du Joueur
	 * 
	 * @param gc    ContextGraphic dans lequel on va afficher le joueur
	 * @param color couleur du joueur
	 * @param yInit position verticale
	 */
	public Player(Field field, String color, int xInit, int yInit, String side, double defStep) {
		super(field, xInit, yInit);
		graphicsContext = this.gc;
		playerColor = color;
		this.side = side;

		angle = 0;

		// On charge la representation du joueur
		if (side == "top") {
			directionArrow = new Image("assets/PlayerArrowDown.png");
		} else {
			directionArrow = new Image("assets/PlayerArrowUp.png");
		}

		PlayerDirectionArrow = new ImageView();
		PlayerDirectionArrow.setImage(directionArrow);
		PlayerDirectionArrow.setFitWidth(10);
		PlayerDirectionArrow.setPreserveRatio(true);
		PlayerDirectionArrow.setSmooth(true);
		PlayerDirectionArrow.setCache(true);

		Image tilesheetImage = new Image("assets/orc.png");
		setSprite(new Sprite(tilesheetImage, 0, 0, Duration.seconds(.2), side));
		getSprite().setX(position.x);
		getSprite().setY(position.y);
		// directionArrow = sprite.getClip().;

		// Tous les joueurs ont une vitesse aleatoire entre 0.0 et 1.0
		// Random randomGenerator = new Random();
		// step = randomGenerator.nextFloat();

		// Pour commencer les joueurs ont une vitesse / un pas fixe
		step = defStep;

	}

	/**
	 * GameLoop du joueur
	 */
	@Override
	public void display() {
		graphicsContext.save(); // saves the current state on stack, including the current transform
		rotate(graphicsContext, angle, position.x + directionArrow.getWidth() / 2, position.y + directionArrow.getHeight() / 2);
		graphicsContext.drawImage(directionArrow, position.x, position.y);
		graphicsContext.restore(); // back to original state (before rotation)
	}

	private void rotate(GraphicsContext gc, double angle, double px, double py) {
		Rotate r = new Rotate(angle, px, py);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}

	/**
	 * Deplacement du joueur vers la gauche, on cantonne le joueur sur le plateau de
	 * jeu
	 */

	public void moveLeft() {
		if (position.x > 10 && position.x <= field.width) {
			spriteAnimate();
			position.x -= step;
		}
	}

	/**
	 * Deplacement du joueur vers la droite
	 */
	public void moveRight() {
		if (position.x >= 0 && position.x < 520) {
			spriteAnimate();
			position.x += step;
		}
	}

	/**
	 * Rotation du joueur vers la gauche
	 */
	public void turnLeft() {
		if (angle > 0 && angle < 180) {
			angle += 1;
		} else {
			angle += 1;
		}

	}

	/**
	 * Rotation du joueur vers la droite
	 */
	public void turnRight() {
		if (angle > 0 && angle < 180) {
			angle -= 1;
		} else {
			angle -= 1;
		}
	}

	public void shoot() {
		Projectile balle = Projectile.getInstance();
		//le joueur ne peut tirer que s'il a la balle
		if (balle.getPlayer() == this) {
			getSprite().playShoot();
			if (side == "bottom") {
				balle.setVelocity(Math.cos(Math.toRadians(90-angle)) * force, -Math.sin(Math.toRadians(90-angle)) * force);
			}
			else {
				balle.setVelocity(-Math.cos(Math.toRadians(90-angle)) * force, Math.sin(Math.toRadians(90-angle)) * force);
			}
			
			balle.dettach();
		}
		
	}

	/**
	 * Deplacement en mode boost
	 */
	public void boost() {
		position.x += step * 2;
		spriteAnimate();
	}

	public void spriteAnimate() {
		// System.out.println("Animating sprite");
		if (!getSprite().isRunning) {
			getSprite().playContinuously();
		}
		getSprite().setX(position.x);
		getSprite().setY(position.y);
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

}
