package fr.icom.info.m1.balleauprisonnier_mvn.maths;

public class Vector2 {
	//vecteur 2-Dimensions
	
	public int x;
    public int y;
       
    public Vector2() {
        this.x = 0;
        this.y = 0;
    }
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    //on pourra ajouter des fonctions utilitaires
    public boolean equals(Vector2 vecteur) {
        return (this.x == vecteur.x && this.y == vecteur.y);
    }
	public void add(int x, int y) {
		this.x += x;
		this.y += y;
	}
    
    //distance, normalize, scale, etc..
}
