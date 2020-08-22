package sliderpuzzle.model;

import java.awt.Color;

/**
 *
 * @author trist
 */
public class Tile extends Grid {
    
    private int id;
    
    public Tile(int id, Color colour) {
        super(colour);
        this.id = id;
    }
    
    public Tile() {
        this(1, Color.WHITE);
    }

    public int getID() { return this.id; }
    public void setID(int id) { this.id = id; } 
    
    @Override
    public String toString() { 
        String n = Integer.toString(this.id);
        if (id < 10) return String.format("%" + 2 + "s", n);
        return n ; 
    }
    
    
}
