import java.util.ArrayList;

/**
 * Created by benjaminbroken on 28.05.16.
 */
public class Solver {

    static Point primary;
    static double step;
    static final double epsilon = 0.0001;

    static double f(Point a) {
        return (Math.pow(a.getX(), 2) + Math.pow(a.getY() + 1, 2))*(Math.pow(a.getX(), 2) + Math.pow(a.getY() - 1, 2));
    }

    static double dx(Point a) {
        return 4*a.getX()*(Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2) + 1);
    }

    static double dy(Point a) {
        return 4*a.getY()*(Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2) - 1);
    }

    static double[] gradient(Point a) {
        double[] grad = new double[2];
        grad[0] = dx(a);
        grad[1] = dy(a);
        return grad;
    }

    static double norm (Point a, Point b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    static double gradientNorm(Point a) {
        double[] grad = gradient(a);
        return Math.sqrt(Math.pow(grad[0], 2) + Math.pow(grad[1], 2));
    }


    static double doubleStep (Point a, double step) {
        ArrayList<Double> steps = new ArrayList<>();
        steps.add(0.0);
        steps.add(step);
        int i = 1;
        while(f(new Point(a.getX() - steps.get(i - 1), a.getY() - steps.get(i - 1))) > f(new Point(a.getX() - steps.get(i), a.getY() - steps.get(i)))) {
            steps.add(step * 2);
            i++;
        }

        return steps.get(i);
    }

    static void CoordinateDescent () {

        ArrayList<Point> points = new ArrayList<>();
        //step = 0.2;
        primary = new Point(1.0, 2.0);
        int i = 0, count = 0;

        System.out.println("Заданная точность: " + epsilon + '\n' + "Первичное приближение: " + primary.getX() + " " + primary.getY());

        points.add(primary);
        Point current = new Point(primary);

        while(true) {

            if (gradientNorm(current) <= epsilon)
                break;

            step = doubleStep(current, 0.01);
            current.setX(points.get(i).getX() - step*dx(points.get(i)));
            current.setY(points.get(i).getY() - step*dy(points.get(i)));
            points.add(new Point(current));
            i++;
            if (norm(current, points.get(i - 1)) < epsilon)
                break;

            count++;
        }

        System.out.print("Найдена точка минимума: ");
        System.out.printf("%.6f", current.getX());
        System.out.print(" ");
        System.out.printf("%.6f", current.getY());
        System.out.print('\n' + "Минимум функции: ");
        System.out.printf("%.6f", f(current));
        System.out.print('\n' + "Количество итераций: " + count);

    }


}
