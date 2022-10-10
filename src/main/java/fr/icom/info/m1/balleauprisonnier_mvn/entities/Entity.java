package fr.icom.info.m1.balleauprisonnier_mvn.entities;

import fr.icom.info.m1.balleauprisonnier_mvn.Field;
import fr.icom.info.m1.balleauprisonnier_mvn.maths.Vector2;
import javafx.scene.canvas.GraphicsContext;

public class Entity {
	// une entité charge sur un terrain (cela nous permet notamment de supprimer
	// notre entité plus simplement)
	Field field;
	// une entité se situe dans un contexte graphique (contexte de notre Canvas --
	// field)
	GraphicsContext gc;
	// une entité possède une position sur le Canvas
	Vector2 position = new Vector2();
	// une entité possède un angle d'inclinaison sur le Canvas
	// **

	protected Entity(Field field, int x, int y) {
		this.field = field;
		this.gc = field.getGraphicsContext2D();
		this.position.x = x;
		this.position.y = y;
	}

	public void display() {
		this.gc.save(); // saves the current state on stack, including the current transform
		// gc.drawImage(new Image("assets/boule.png"), this.position.x,
		// this.position.y);
		// rotate(graphicsContext, angle, position.x + directionArrow.getWidth() / 2,
		// position.y + directionArrow.getHeight() / 2);
		// graphicsContext.drawImage(directionArrow, position.x, position.y);
		this.gc.restore(); // back to original state (before rotation)
	}

	protected void move(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}

	public void remove() {
		this.field.supprimerJoueur();
	}
}
