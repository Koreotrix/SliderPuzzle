package sliderpuzzle.model;

import java.awt.Color;

public class Empty extends Grid {
    
    public Empty() {
        super(Color.BLACK);
        super.toggleMovement();
    }

    @Override
    public String toString() {
        return " x";
    }
}
