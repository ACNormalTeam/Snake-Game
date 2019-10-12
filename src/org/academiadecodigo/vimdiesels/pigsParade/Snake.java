package org.academiadecodigo.vimdiesels.pigsParade;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.vimdiesels.pigsParade.components.Header;
import org.academiadecodigo.vimdiesels.pigsParade.grid.Grid;
import org.academiadecodigo.vimdiesels.pigsParade.grid.GridDirection;
import org.academiadecodigo.vimdiesels.pigsParade.grid.position.GridPosition;
import org.academiadecodigo.vimdiesels.pigsParade.grid.position.Position;

public class Snake implements KeyboardHandler {

    private GridPosition position;
    private Grid grid;

    private Keyboard keyboard;

    private GridDirection currentDirection;


    public Snake(Grid grid) {
        this.grid = grid;
        this.position = new Position(grid.getCols()/2, grid.getRows()/2, grid);

        currentDirection = GridDirection.values()[(int) (Math.random() * GridDirection.values().length)];
        keyboard = new Keyboard(this);
        init();
    }

    private void init() {
        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
        keyboard.addEventListener(up);
        keyboard.addEventListener(down);

    }

    public void autoMove() {

        if(foundWall()){
            return;
        }

        GridDirection direction = GridDirection.RIGHT;

        this.position.moveInDirection(direction, 1);

    }

    public boolean foundWall(){

        if(
                (this.position.getCol() == grid.getCols()-grid.getBorderCells())
                        || (this.position.getCol() == grid.getBorderCells() - 1)
                        || (this.position.getRow() == grid.getBorderCells() + Header.getHeightCells() -1)
                        || (this.position.getRow() == grid.getBorderCells())
        ){
            /*System.out.println("snake col: " + this.position.getCol());
            System.out.println("Left border cells: " + grid.getBorderCells());
            System.out.println("snake pos in px: " + grid.columnToX(this.position.getCol()));
            System.out.println("x: "+grid.columnToX(grid.getBorderCells()));*/
            return true;
        }
        return false;
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                currentDirection = GridDirection.LEFT;
                break;
            case KeyboardEvent.KEY_RIGHT:
                currentDirection = GridDirection.RIGHT;
                break;
            case KeyboardEvent.KEY_UP:
                currentDirection = GridDirection.UP;
                break;
            case KeyboardEvent.KEY_DOWN:
                currentDirection = GridDirection.DOWN;
                break;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

}