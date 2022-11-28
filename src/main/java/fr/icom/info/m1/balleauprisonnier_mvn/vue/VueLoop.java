package fr.icom.info.m1.balleauprisonnier_mvn.vue;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
//mettre en singleton pour appel dans GameLoop
public class VueLoop {
	GraphicsContext gc;
	
	public VueLoop(GraphicsContext gc) {
		this.gc = gc;
	}
	
	public void refreshCanvas(int width, int height) {
		// On nettoie le canvas a chaque frame
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, width, height);
	}
	
}
