package fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities;

import fr.icom.info.m1.balleauprisonnier_mvn.maths.Vector2;
import fr.icom.info.m1.balleauprisonnier_mvn.modeles.Field;
import javafx.scene.image.Image;

public class Projectile extends Entity {
	private static Projectile instance;
	private Image projectileImage;
	private Vector2 velocity = new Vector2(0.0, 0.0);
	private Player player;
	private String side;
	/* offset de la balle quand tenue par un joueur (en y)*/
	private int offset = 20;
	
	private Projectile(Field field, int x, int y) {
		super(field, x, y);

		projectileImage = new Image("assets/boule.png");	
	}
	
	public static Projectile init(Field field, int x, int y) {
		instance = new Projectile(field, x, y);
		return instance;
	}
	
	public static Projectile getInstance() {
		//attention si notre projectile n'a pas été instantié préalablement, retournera null
		return instance;
	}

	public void updatePosition() {
		if (player != null) {
			this.position.x = player.position.x;
			this.position.y = player.position.y + ((player.side == "bottom") ? -offset : offset);;
		}
		else {
			if (this.position.y > field.height - 100) {
				return;
			}
			this.position.add(velocity.x, velocity.y);
		}
	}
	
	
	public void attach(Player player) {
		//method pour attacher la balle à un joueur
		this.player = player;
		this.side = player.side;
	}
	
	public void dettach() {
		//method pour déttacher la balle d'un joueur
		this.player = null;
	}
	
	public void setVelocity(double x, double y) {
		this.velocity.x = x;
		this.velocity.y = y;
	}
	
	public void setVelocity(Vector2 v) {
		this.velocity.x = v.x;
		this.velocity.y = v.y;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public boolean collision(Player joueur) {
		if (joueur.side == this.side) { // on empêche les joueurs d'une même équipe de se toucher entre eux
			return false;
		}
		return joueur.getSprite().getBoundsInParent().intersects(position.x,position.y,projectileImage.getWidth(),projectileImage.getHeight());
	}
	
	@Override
	public void display() {
		gc.drawImage(projectileImage, position.x, position.y);
	}
}
