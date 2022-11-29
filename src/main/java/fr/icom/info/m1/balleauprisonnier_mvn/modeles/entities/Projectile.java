package fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities;

import fr.icom.info.m1.balleauprisonnier_mvn.maths.Vector2;
import fr.icom.info.m1.balleauprisonnier_mvn.modeles.Field;
import javafx.scene.image.Image;

public class Projectile extends Entity {
	private static Projectile instance;
	Image projectileImage;
	double angle;
	int velocity;
	Sprite sprite;

	private Projectile(Field field, int x, int y, double angle, int velocity) {
		super(field, x, y);
		this.angle = angle;
		this.velocity = velocity;

		projectileImage = new Image("assets/boule.png");
	}
	
	public Projectile init(Field field, int x, int y, double angle, int velocity) {
		instance = new Projectile(field, x, y, angle, velocity);
		return instance;
	}
	
	public static Projectile getInstance() {
		//attention si notre projectile n'a pas été instantié préalablement, retournera null
		return instance;
	}

	public Vector2 updatePosition() {
		this.position.add(1, 1);
		return this.position;
	}
	
	
	@Override
	public void display() {
		gc.drawImage(projectileImage, position.x, position.y);
	}
	

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
}
