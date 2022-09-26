package fr.icom.info.m1.balleauprisonnier_fx;


import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class Field extends Canvas {
	
	/** Joueurs */
	Player [] joueurs = new Player[2];
	Player [] joueursIA = new Player[4];
	/** Couleurs possibles */
	String[] colorMap = new String[] {"blue", "green", "orange", "purple", "yellow"};
	/** Tableau traçant les evenements */
    ArrayList<String> input = new ArrayList<String>();
    

    final GraphicsContext gc;
    final int width;
    final int height;
    
    /**
     * Canvas dans lequel on va dessiner le jeu.
     * 
     * @param scene Scene principale du jeu a laquelle on va ajouter notre Canvas
     * @param w largeur du canvas
     * @param h hauteur du canvas
     */
	public Field(Scene scene, int w, int h) 
	{
		super(w, h); 
		width = w;
		height = h;
		
		/** permet de capturer le focus et donc les evenements clavier et souris */
		this.setFocusTraversable(true);
		
        gc = this.getGraphicsContext2D();
        
        /** On initialise le terrain de jeu */
    	joueurs[0] = new Player(gc, colorMap[0], w/2, h-95, "bottom", 3);
    	joueurs[0].display();

    	joueurs[1] = new Player(gc, colorMap[1], w/2, 45, "top", 2);
    	joueurs[1].display();
    	
    	/* IA */
    	joueursIA[0] = new PlayerIA(gc, colorMap[0], w/3, h-70, "bottom", 5);
    	joueursIA[0].display();
    	joueursIA[2] = new PlayerIA(gc, colorMap[0], w - w/3, h-70, "bottom", 5);
    	joueursIA[2].display();
    	
    	joueursIA[1] = new PlayerIA(gc, colorMap[1], w/3, 20, "top", 2);
    	joueursIA[1].display();
    	joueursIA[3] = new PlayerIA(gc, colorMap[1], w - w/3, 20, "top", 2);
    	joueursIA[3].display();

	    /** 
	     * Event Listener du clavier 
	     * quand une touche est pressee on la rajoute a la liste d'input
	     *   
	     */
	    this.setOnKeyPressed(
	    		new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            // only add once... prevent duplicates
	    	            if ( !input.contains(code) )
	    	                input.add( code );
	    	        }
	    	    });

	    /** 
	     * Event Listener du clavier 
	     * quand une touche est relachee on l'enleve de la liste d'input
	     *   
	     */
	    this.setOnKeyReleased(
	    	    new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            input.remove( code );
	    	        }
	    	    });
	    
	    /** 
	     * 
	     * Boucle principale du jeu
	     * 
	     * handle() est appelee a chaque rafraichissement de frame
	     * soit environ 60 fois par seconde.
	     * 
	     */
	    new AnimationTimer() 
	    {
	        public void handle(long currentNanoTime)
	        {	 
	            // On nettoie le canvas a chaque frame
	            gc.setFill( Color.LIGHTGRAY);
	            gc.fillRect(0, 0, width, height);
	        	
	            // Deplacement et affichage des joueurs
	        	for (int i = 0; i < joueurs.length; i++) 
	    	    {
	        		if (i==0 && input.contains("LEFT"))
	        		{
	        			joueurs[i].moveLeft();
	        		} 
	        		if (i==0 && input.contains("RIGHT")) 
	        		{
	        			joueurs[i].moveRight();	        			
	        		}
	        		if (i==0 && input.contains("UP"))
	        		{
	        			joueurs[i].turnLeft();
	        		} 
	        		if (i==0 && input.contains("DOWN")) 
	        		{
	        			joueurs[i].turnRight();	        			
	        		}
	        		if (i==0 && input.contains("SPACE")){
	        			joueurs[i].shoot();
					}
	        		if (i==1 && input.contains("Q"))
	        		{
	        			joueurs[i].moveLeft();
	        		} 
	        		if (i==1 && input.contains("D")) 
	        		{
	        			joueurs[i].moveRight();	        			
	        		}
	        		if (i==1 && input.contains("Z"))
	        		{
	        			joueurs[i].turnLeft();
	        		} 
	        		if (i==1 && input.contains("S")) 
	        		{
	        			joueurs[i].turnRight();	        			
	        		}
	        		if (i==1 && input.contains("A")){
	        			joueurs[i].shoot();
					}

	        		joueurs[i].display();
	    	    }
	        	
	        	for (int i = 0; i < joueursIA.length; i++) {
	        		joueursIA[i].display();
	        	}
	        	
	    	}
	     }.start(); // On lance la boucle de rafraichissement 
	     
	}

	public Player[] getJoueurs() {
		return joueurs;
	}
	
	public Player[] getJoueursIA() {
		return joueursIA;
	}
}
