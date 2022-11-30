package fr.icom.info.m1.balleauprisonnier_mvn.modeles.entities;

import fr.icom.info.m1.balleauprisonnier_mvn.modeles.Field;

public class PlayerIA extends Player {
	private java.util.Random random = new java.util.Random(System.currentTimeMillis());
	/* sert principalement pour la strategy "facile" pour avoir un déplacement moins erratique */
	private int clock = 0;
	private int choice = 0;
	private String strategy; //si on entre une stratégie non valide, ce sera easy par défaut (constructeur)
	
	/* valeur par défaut de la clock */
	private final int DEF_CLOCK = 20;
	/* angle max autorisé pour le tir */
	private final int MAX_ANGLE = 180;
	
	public PlayerIA(Field field, String color, int xInit, int yInit, String side, double defStep, String strategy) {
		super(field, color, xInit, yInit, side, defStep);
		if (strategy == "medium") this.strategy = "medium";
		else this.strategy = "easy";
	}
	
	public void changeStrategy(String strategy) {
		this.strategy = strategy;
	}
	
	public void strategy() {
		switch(strategy) {
		case "medium":
			strategyMedium();
			break;
		default:
			strategyEasy();
		}
		
	}
	
	public void strategyEasy() {
		//bouge aléatoirement
		if (clock == 0) {
			choice = random.nextInt(2);
			clock = DEF_CLOCK;
		}
		if (choice == 1) this.moveLeft();
		else this.moveRight();
		clock--;
		//L'IA tire dès qu'il a la balle droit devant lui
		this.shoot();
	}
	
	public void strategyMedium() {
		if (field.balle.getPlayer() == null && Math.abs(field.balle.getVelocity().y) < field.balle.NULL_VELOCITY) {
			//on va ramasser la balle
			if (position.x - field.balle.position.x > 0) this.moveLeft();
			else this.moveRight();
		}
		//un équipier possède la balle
		else if (field.balle.getPlayer() != null && field.balle.getPlayer().side == this.side) {
			if (field.balle.getPlayer() == this) {
				/* Il s'agit de nous même, on doit tirer */
				this.setAngle(random.nextInt(MAX_ANGLE));
				this.shoot();
			}
			else strategyEasy(); //on bouge sans se soucier du danger tant que notre allié possède la balle
		}
		//la balle est un danger!
		else {
			/* si la balle va à droite on va à gauche et vice versa */
			if (field.balle.getVelocity().x > 0) this.moveLeft();
			else if (field.balle.getVelocity().x < 0) this.moveRight();
			else {
				/* si la balle va tout droit on esquive selon sa position */
				if (field.balle.position.x >= field.width/2 + 1) {
					this.moveLeft();
				}
				else this.moveRight();
			}
		}
	}
	
}
