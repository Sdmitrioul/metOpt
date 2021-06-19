package skroba.lab3.method;

import skroba.utils.matrix.Matrix;

public abstract class AbstractSolver<T extends Matrix> implements Solver<T>{
	protected long iterations;
	
	@Override
	public long getIterations() {
		return iterations;
	}
}
