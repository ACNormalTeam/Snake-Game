package org.academiadecodigo.vimdiesels.pigsParade;


//import org.academiadecodigo.vimdiesels.pigsParade.components.GameStage;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.vimdiesels.pigsParade.components.Header;
import org.academiadecodigo.vimdiesels.pigsParade.grid.Grid;
import org.academiadecodigo.vimdiesels.pigsParade.grid.GridDirection;
import org.academiadecodigo.vimdiesels.pigsParade.grid.position.Position;
import org.w3c.dom.css.Rect;


public class Game {

<<<<<<< HEAD
    int delay;
    Snake snake;
    Food food;

=======
    private int delay;
    private Snake snake;
    private Food food;
    private Grid grid;
>>>>>>> a22d88c210143c50cc6fa2ced3ac5928a4a37aa0

    public Game(int delay){
        this.delay = delay;
    }

    public void init(){

        Grid grid = new Grid(82, 42, 3);
        this.grid = grid;
        this.grid.init();

        //this.borderSize = grid.getCellSize();

        Header header = new Header(this.grid, 3);
        header.init();

        this.grid.buildBorders();


        snake = new Snake(this.grid);

<<<<<<< HEAD
        snake = new Snake(grid);
        food = new Food(grid);

=======
>>>>>>> a22d88c210143c50cc6fa2ced3ac5928a4a37aa0

    }

    public void createFood(){
        food = new Food(this.grid);
        food.createFood();
    }

    public void start() throws InterruptedException {
        this.createFood();
        snake.autoMove();

    }



}