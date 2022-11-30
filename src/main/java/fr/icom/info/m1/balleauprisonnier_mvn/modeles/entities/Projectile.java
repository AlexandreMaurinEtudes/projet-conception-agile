package fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities;

import fr.icom.info.m1.balleauprisonnier_mvn.modeles.Field;
import javafx.scene.image.Image;

public class Projectile extends Entity {
	private static Projectile instance;
	private Image projectileImage;
	private double angle;
	private double velocity;
	private Player player;
	private String side;
	/* offset de la balle quand tenue par un joueur (en y)*/
	private int offset = 20;
	
	private Projectile(Field field, int x, int y, double angle, int velocity) {
		super(field, x, y);
		this.angle = angle;
		this.velocity = velocity;

		projectileImage = new Image("assets/boule.png");
	}
	
	public static Projectile init(Field field, int x, int y, double angle, int velocity) {
		instance = new Projectile(field, x, y, angle, velocity);
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
			if (side == "bottom") {
				this.position.add(Math.cos(Math.toRadians(90-angle)) * velocity, -Math.sin(Math.toRadians(90-angle)) * velocity);
			}
			else {
				this.position.add(-Math.cos(Math.toRadians(90-angle)) * velocity, Math.sin(Math.toRadians(90-angle)) * velocity);
			}
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
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	@Override
	public void display() {
		gc.drawImage(projectileImage, position.x, position.y);
	}
}
