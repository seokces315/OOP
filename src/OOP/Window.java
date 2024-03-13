package OOP;

import java.util.ArrayList;

// Type of Color
enum colorType { Black, Red, Green, Blue, White }

// Abstract Class
abstract class Figure {

    // Instance field
    colorType c;

    // Setter
    void setColor(colorType c) { this.c = c; }

    // Abstract method
    // TODO : move the location of the shape by the amounts of xx and yy
    abstract void move(int xx, int yy);

}

// Concrete Classes
class Point extends Figure {

    // Instance field
    int x, y;

    // Constructor
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Overriding the abstract method
    @Override
    void move(int xx, int yy) {
        System.out.println("Moving the Point!");
        System.out.printf("(%d, %d) -> ", x, y);
        x += xx;
        y += yy;
        System.out.printf("(%d, %d)\n", x, y);
    }

}

class Circle extends Figure {

    // Instance field
    Point center;
    float radius;

    // Constructor
    Circle(Point center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    // Overriding the abstract method
    @Override
    void move(int xx, int yy) {
        System.out.println("Moving the Circle!");
        System.out.printf("Center : (%d, %d) -> ", center.x, center.y);
        center.x += xx;
        center.y += yy;
        System.out.printf("(%d, %d)\n", center.x, center.y);
        System.out.println(); // 줄바꿈
    }

}

class Square extends Figure {

    // Instance field
    Point upleft;
    int side;

    // Constructor
    Square(Point upleft, int side) {
        this.upleft = upleft;
        this.side = side;
    }

    // Overriding the abstract method
    @Override
    void move(int xx, int yy) {
        System.out.println("Moving the Square!");
        System.out.printf("Upleft >>> (%d, %d) -> ", upleft.x, upleft.y);
        upleft.x += xx;
        upleft.y += yy;
        System.out.printf("(%d, %d)\n", upleft.x, upleft.y);
        System.out.println(); // 줄바꿈
    }

}

class Line extends Figure {

    // Instance field
    Point p1, p2;

    // Constructor
    Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    // Overriding the abstract method
    @Override
    void move(int xx, int yy) {
        System.out.println("Moving the Line!");
        System.out.printf("P1(%d, %d), P2(%d, %d) -> ", p1.x, p1.y, p2.x, p2.y);
        this.p1.x += xx;
        this.p1.y += yy;
        this.p2.x += xx;
        this.p2.y += yy;
        System.out.printf("P1(%d, %d), P2(%d, %d)\n", p1.x, p1.y, p2.x, p2.y);
        System.out.println(); // 줄바꿈
    }

}

public class Window {

    // Class field
    static ArrayList<Figure> figureList = new ArrayList<>();
    static int count = 0;   // counter

    // Methods
    static void addFigure(Figure fig) {
        // Duplication check
        for(Figure e : figureList) {
            if(e == fig) {
                System.out.println("The figure already existed!");
                return;
            }
        }

        // add the figure
        figureList.add(fig);
        // increment the counter
        count++;

        System.out.println("Figure added!");
    }

    static void moveAll(int xx, int yy) {
        // TODO : move all the figure objects in the collection
        for(Figure fig : figureList)
            fig.move(xx, yy);

        System.out.println("All the figures in collection are moved!");
    }

    public static void main(String[] args) {

        // Create and Add the instance of Circle
        Circle circle = new Circle(new Point(0, 0), 5);
        addFigure(circle);

        // Create and Add the instance of Square
        Square square = new Square(new Point(0, 10), 10);
        addFigure(square);

        // Create and Add the instance of Line
        Line line = new Line(new Point(-15, 0), new Point(15, 0));
        addFigure(line);

        System.out.println(); // 줄바꿈

        // invoke moveAll()
        moveAll(50, 50);
    }

}
