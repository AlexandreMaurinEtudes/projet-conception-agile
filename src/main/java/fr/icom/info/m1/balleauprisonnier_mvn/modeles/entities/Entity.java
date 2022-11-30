package fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities;

import fr.icom.info.m1.balleauprisonnier_mvn.maths.Vector2;
import fr.icom.info.m1.balleauprisonnier_mvn.modeles.Field;
import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
	// une entité charge sur un terrain
	Field field;
	// une entité se situe dans un contexte graphique (contexte de notre Canvas --
	// field)
	GraphicsContext gc;
	// une entité possède une position sur le Canvas
	Vector2 position = new Vector2();
	// la visibilité d'une entité
	private boolean visible = true;

	protected Entity(Field field, int x, int y) {
		this.field = field;
		this.gc = field.getGraphicsContext2D();
		this.position.x = x;
		this.position.y = y;
	}
	
	// une entité doit posséder une methode permettant sont affichage
	public abstract void display();
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean value) {
		visible = value;
	}
}
