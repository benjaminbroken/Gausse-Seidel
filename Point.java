/**
 * Created by benjaminbroken on 28.05.16.
 */
public class Point {
    public double x;
    public double y;

    public Point(){
        x = 0.0;
        y = 0.0;
    }

    public Point (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point (double[] a) {
        x = a[0];
        y = a[1];
    }

    public Point (Point a){
        this(a.getX(), a.getY());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX (double newX) {
        x = newX;
    }

    public void setY (double newY) {
        y = newY;
    }


    public static Point minus (Point a, Point b) {
        return new Point(a.getX() - b.getX(), a.getY() - b.getY());
    }

    public static Point product (Point a, double[] e){
        Point result = new Point();
        for (int i = 0; i < e.length; i++) {
            result.setX(result.getX() + a.getX() * e[i]);
            result.setY(result.getY() + a.getY() * e[i]);
        }
        return result;
    }

}


