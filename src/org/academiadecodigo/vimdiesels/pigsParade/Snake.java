package org.academiadecodigo.vimdiesels.pigsParade;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import org.academiadecodigo.vimdiesels.pigsParade.components.Header;

import org.academiadecodigo.vimdiesels.pigsParade.grid.Grid;
import org.academiadecodigo.vimdiesels.pigsParade.grid.GridColor;
import org.academiadecodigo.vimdiesels.pigsParade.grid.GridDirection;

import org.academiadecodigo.vimdiesels.pigsParade.grid.position.GridPosition;
import org.academiadecodigo.vimdiesels.pigsParade.grid.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Snake implements KeyboardHandler{

    private Grid grid;

    private List<GridPosition> snakeBody;
    private GridPosition snakeHead;

    private Keyboard keyboard;

    private GridDirection currentDirection;

    private int lastRowPosition, lastColPosition;

    public Snake(Grid grid) {
        this.grid = grid;

        currentDirection = GridDirection.RIGHT;
        keyboard = new Keyboard(this);
        init();
    }

    private void init() {

        createSnake();

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

    public void createSnake(){

        snakeBody = new ArrayList();

        snakeHead = new Position(grid.getCols()/2, grid.getRows()/2, grid);
        snakeHead.setColor(GridColor.BLUE);
        snakeHead.setDirection(currentDirection);

        snakeBody.add(snakeHead);

        for(int i = 1; i < 3; i++){
            snakeBody.add(new Position(snakeHead.getCol()-i, snakeHead.getRow(), grid));
        }

    }

    public void autoMove() throws InterruptedException {

        if (foundWall()) {
            return;
        }

        while (true) {

            GridDirection direction = currentDirection;

            Thread.sleep(100);

            for (int i = snakeBody.size()-1; i >= 0; i--) {

                if( i == 0 ){
                    snakeBody.get(i).moveInDirection(direction, 1);
                    break;
                }

                snakeBody.get(i).setPos(
                        snakeBody.get(i-1).getCol(),
                        snakeBody.get(i-1).getRow()
                );

                snakeBody.get(i).getRectangle().translate(
                        this.grid.columnToX(snakeBody.get(i).getCol()) - snakeBody.get(i).getRectangle().getX(),
                        this.grid.rowToY(snakeBody.get(i).getRow()) - snakeBody.get(i).getRectangle().getY()
                );

            }

        }
    }

    public void growSnake(){
        
    }

    public boolean foundWall(){

        GridPosition snakeHeadPosition = snakeBody.get(0);

        if(
                (snakeHeadPosition.getCol() == grid.getCols()-grid.getBorderCells())
                        || (snakeHeadPosition.getCol() == grid.getBorderCells() - 1)
                        || (snakeHeadPosition.getRow() == grid.getBorderCells() + Header.getHeightCells() -1)
                        || (snakeHeadPosition.getRow() == grid.getBorderCells())
        ){
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
        System.out.println("current Direction" + currentDirection);

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

}