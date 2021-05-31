package skroba.lab2.methods;

import skroba.exceptions.TimeOutException;
import skroba.lab1.methods.GoldenRatioMethod;
import skroba.lab1.methods.MinimumSearcher;
import skroba.utils.Pair;
import skroba.utils.QuadraticFunction;
import skroba.utils.Vector;

import java.util.function.Function;

/**
 * Simple realization of Fast Gradient Method, extended {@link AbstractGradientIterator}.
 */
public class FastGradientMethod extends AbstractGradientIterator {
	private final static String NAME = "Fast gradient descent";
	
	/**
	 *	Constructor with standard EPS equals 1e-3.
	 *
	 * @param function - given minimizing function.
	 */
	public FastGradientMethod(QuadraticFunction function) {
		super(NAME, function);
	}
	
	/**
	 * Constructor with given EPS.
	 *
	 * @param function - given minimizing function.
	 * @param EPS - double value characterizing precession of calculating.
	 */
	public FastGradientMethod(QuadraticFunction function, Double EPS) {
		super(NAME, function, EPS);
	}
	
	@Override
	protected boolean hasNextPr() {
		final Vector cPoint = currentValue.getFirst();
		final double cNorma = function.getGradient(cPoint).norma();
		
		if (comparator.compare(EPS, cNorma) != -1) {
			return false;
		}
		
		final Function<Double, Vector> oneDimFun = x -> currentValue.getFirst().sum(function.getGradient(currentValue.getFirst()).scalarMul(-x));
		MinimumSearcher searcher = new GoldenRatioMethod(EPS / 100, STANDARD_DELTA, oneDimFun.andThen(function));
		try {
			final double val = searcher.findMin(0.0, 1.0).getMin();
			nextValue = new Pair<>(oneDimFun.apply(val), val);
			return true;
		} catch (TimeOutException ex) {
			return false;
		}
	}
	
	@Override
	protected Pair<Vector, Double> nextPr() {
		this.currentValue = this.nextValue;
		
		return this.currentValue;
	}
}
