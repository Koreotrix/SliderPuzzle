package sliderpuzzle.model;

import java.awt.Color;

public class Board {
    
    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(board.toString());
        board.moveLeft();
        board.makeMove(15);
        System.out.println(board.toString());
        
    }
    
    private Grid[] grid;
    private int emptySlot;
    private int left, right, up, down;
    
    public Board() {
        this.grid = new Grid[16];
        this.emptySlot = 15;
            
        // populate grid
        for(int i = 1; i < grid.length; i++) {
            
            if (i%2 == 0) {
                grid[i-1] = new Tile(i, Color.WHITE);
            } else {
                grid[i-1] = new Tile(i, Color.RED);
            }
        }
        this.grid[grid.length - 1] = new Empty(); // last space (bottom right) is the empty space
        
        
    }
    
    // Player Mechanics ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//    public int findEmpty() { 
//        for (int i = 0; i < grid.length; i++) {
//            if (grid[i] instanceof Empty) {
//                return i;
//            }
//        } return 15; // the empty space is at the bottom right
//    }
    
    public void makeMove(int placement) {
        
        // calculate the move being made
        left = emptySlot - 1;
        right = emptySlot + 1;
        up = emptySlot - 4;
        down = emptySlot + 4;

        if (placement == left) { moveLeft(); }
        else if (placement == right) { moveRight(); }
        else if (placement == up) { moveUp(); }
        else if (placement == down) { moveDown(); }
        else { System.err.println("Cannot make move"); }
        
        if (checkCompletion()) System.out.println("COMPLETED");
    }
    
    public boolean moveLeft() {
        
        if (emptySlot == 0 || emptySlot == 4 || emptySlot == 8 
                || emptySlot == 12) { return false; }
        
        // swap empty with position to the left
        Empty empty = (Empty) grid[emptySlot];
        Tile tile = (Tile)grid[emptySlot-1];
        
        grid[emptySlot] = tile;
        grid[emptySlot-1] = empty;
        
        emptySlot -= 1;
        
        
        return true;
    }
    
    public boolean moveRight() {
        if (emptySlot == 15 || emptySlot == 11 || emptySlot == 7 
                || emptySlot == 3) { return false; }
        
        // swap empty with position to the right
        Empty empty = (Empty) grid[emptySlot];
        Tile tile = (Tile)grid[emptySlot+1];
        
        grid[emptySlot] = tile;
        grid[emptySlot+1] = empty;
        
        emptySlot += 1;
        
        return true;
    }
    
    public boolean moveUp() {

        if (emptySlot < 4) { return false; }
        
        // swap empty with position upwards
        Empty empty = (Empty) grid[emptySlot];
        Tile tile = (Tile)grid[emptySlot-4];
        
        grid[emptySlot] = tile;
        grid[emptySlot-4] = empty;
        
        emptySlot -= 4;
        
        return true;
    }
    
    public boolean moveDown() {
        
        if (emptySlot > 11) { return false; }
        
        // swap empty with position downwards
        Empty empty = (Empty) grid[emptySlot];
        Tile tile = (Tile)grid[emptySlot+4];
        
        grid[emptySlot] = tile;
        grid[emptySlot+4] = empty;
        
        emptySlot += 4;
        
        return true;
    
    }
    
    public void randomizeBoard() {
        int steps = 200;
        int direction = 0;
        
        for (int i = 0; i < steps; i++) {
            
            // randomize the direction of movement for the empty space
            direction = (int) (Math.random() * (4 - 1 + 1) + 1);
            
            switch (direction) {
                case 1: moveUp(); break;
                case 2: moveDown(); break;
                case 3: moveLeft(); break;
                default: moveRight(); 
                
            } 
        }
        
    }
    
    // Background Mechanics ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    public boolean checkCompletion() {
        
        if(emptySlot != 15) { return false; }
        
        Tile current = null;
        
        for (int i = 0; i <= emptySlot; i++ ) {
            
            if (grid[i] instanceof Tile) {
                current = (Tile) grid[i];
                
                if (current.getID() != i+1) { return false; }
                
            } else return false;
            
        }
        
        return true;
    }
    
    // Overrides ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int counter = 0;
        
        builder.append(" ~~~~ ~~~~ ~~~~ ~~~~ \n");
        for (Grid current : grid) {
            if (counter == 4) {
                builder.append("|\n");
                builder.append(" ~~~~ ~~~~ ~~~~ ~~~~ \n");
                counter = 0;
            }
            builder.append("| ");
            builder.append(current.toString());
            builder.append(" ");
            counter++;
        }
        builder.append("|\n ~~~~ ~~~~ ~~~~ ~~~~ \n");
        
        return builder.toString();
    }
}
