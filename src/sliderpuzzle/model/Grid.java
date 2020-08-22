package sliderpuzzle.model;

import java.awt.Color;

public class Grid {
    
    private Color colour;
    private boolean isMoveable;
    
    public Grid(Color colour) {
        this.colour = colour;
        this.isMoveable = false;
    }
    
    public Color getColour() { return this.colour; }
    public boolean isMoveable() { return this.isMoveable; }
    
    public void setColour(Color colour) { this.colour = colour; }
    public void toggleMovement() { this.isMoveable = !isMoveable; }
}
