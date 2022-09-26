package fr.icom.info.m1.balleauprisonnier_fx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Projectile {
	Image projectileImage;
	double x;
	double y;
	double angle;
	
	Projectile(GraphicsContext gc, double x,double y, double angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		
		projectileImage = new Image("assets/orc.png");
		gc.drawImage(projectileImage, x, y);
	}
}
