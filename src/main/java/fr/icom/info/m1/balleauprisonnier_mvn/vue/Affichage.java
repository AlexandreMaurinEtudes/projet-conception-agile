package fr.icom.info.m1.balleauprisonnier_mvn.vue;

import fr.icom.info.m1.balleauprisonnier_mvn.modeles.Field;
import fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Affichage {
	private static Affichage instance = new Affichage();
	private Field field;
	private GraphicsContext gc;
	
	private Affichage() {}
	
	public static Affichage getInstance() {
		return instance;
	}
	
	public void init(Field field) {
		this.field = field;
		this.gc = field.getGraphicsContext2D();
	}
	
	public void refreshCanvas() {
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, field.width, field.height);
	}
	
	public void entitiesRender(Entity[] entities) {
		for (Entity entity : entities) {
			if (entity.isVisible()) entity.display();
		}
	}
	
}
