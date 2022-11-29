package fr.icom.info.m1.balleauprisonnier_mvn.vue;

import fr.icom.info.m1.balleauprisonnier_mvn.modeles.Field;
import fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class VueLoop {
	private static VueLoop instance;
	Field field;
	GraphicsContext gc;
	
	private VueLoop() {
	}
	
	public static VueLoop getInstance() {
		if (instance==null) {
			instance = new VueLoop();
		}
		return instance;
	}
	
	public void init(Field field) {
		this.field = field;
		this.gc = field.getGraphicsContext2D();
	}
	
	public void refreshCanvas() {
		// On nettoie le canvas a chaque frame
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, field.width, field.height);
	}
	
	public void entitiesRender(Entity[] entities) {
		for (Entity entity : entities) {
			entity.display();
		}
	}
	
}
