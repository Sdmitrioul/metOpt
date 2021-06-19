package skroba.lab3.method;


import skroba.utils.matrix.Matrix;
import skroba.utils.Vector;

public interface Solver<T extends Matrix> {
	double EPS = 1e-20;
	Vector solve(final T matrix, final Vector b);
	long getIterations();
}
