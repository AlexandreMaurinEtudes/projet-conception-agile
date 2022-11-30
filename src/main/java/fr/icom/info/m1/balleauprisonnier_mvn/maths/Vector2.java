package fr.icom.info.m1.balleauprisonnier_mvn.maths;

public class Vector2 {
	// vecteur 2-Dimensions

	public double x;
	public double y;

	public Vector2() {
		this.x = 0;
		this.y = 0;
	}

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// on pourra ajouter des fonctions utilitaires
	public boolean equals(Vector2 vecteur) {
		return (this.x == vecteur.x && this.y == vecteur.y);
	}

	public void add(double d, double y) {
		this.x += d;
		this.y += y;
	}

	// distance, normalize, scale, etc..
}
