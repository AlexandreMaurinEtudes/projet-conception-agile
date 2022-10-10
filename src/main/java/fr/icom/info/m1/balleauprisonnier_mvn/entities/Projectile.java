package fr.icom.info.m1.balleauprisonnier_mvn.entities;

import fr.icom.info.m1.balleauprisonnier_mvn.Field;
import fr.icom.info.m1.balleauprisonnier_mvn.maths.Vector2;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.util.Duration;
//import javafx.util.Duration;

public class Projectile extends Entity {
	Image projectileImage;
	double angle;
	int velocity;
	Sprite sprite;

	public Projectile(Field field, int x, int y, double angle, int velocity) {
		super(field, x, y);
		this.angle = angle;
		this.velocity = velocity;

		projectileImage = new Image("assets/boule.png");
		/*
		 * this.sprite = new Sprite(projectileImage, 0,0, Duration.seconds(.2), "top");
		 * sprite.setX(position.x); sprite.setY(position.y);
		 */
		// field.ajouterElement(sprite);
		gc.drawImage(projectileImage, x, y);
	}

	public Vector2 updatePosition() {
		this.position.add(1, 1);
		return this.position;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
}
