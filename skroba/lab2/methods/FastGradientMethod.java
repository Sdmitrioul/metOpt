package skroba.lab2.methods;

import skroba.exceptions.TimeOutException;
import skroba.lab1.methods.GoldenRatioMethod;
import skroba.lab1.methods.MinimumSearcher;
import skroba.utils.Pair;
import skroba.utils.QuadraticFunction;
import skroba.utils.Vector;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

/**
 * Simple realization of Fast Gradient Method, extended {@link AbstractGradientIterator}.
 */
public class FastGradientMethod extends AbstractGradientIterator {
	private final static String NAME = "Fast gradient descent";
	private Class<? extends MinimumSearcher> oneDimSearcherToken = GoldenRatioMethod.class;
	
	/**
	 *	Constructor with standard EPS equals 1e-3.
	 *
	 * @param function - given minimizing function.
	 */
	public FastGradientMethod(final QuadraticFunction function) {
		super(NAME, function);
	}
	
	/**
	 * Constructor with given EPS.
	 *
	 * @param function - given minimizing function.
	 * @param EPS - double value characterizing precession of calculating.
	 */
	public FastGradientMethod(final QuadraticFunction function, final Double EPS) {
		super(NAME, function, EPS);
	}
	
	/**
	 * Constructor with given EPS and one dim minimum searcher.
	 *
	 * @param function - given minimizing function.
	 * @param EPS - double value characterizing precession of calculating.
	 * @param oneDimSearcherToken - {@link Class} token of minimum searcher. It must implement {@link MinimumSearcher} interface.
	 */
	public FastGradientMethod(final QuadraticFunction function, final Double EPS, final Class<? extends MinimumSearcher> oneDimSearcherToken) {
		super(NAME, function, EPS);
		this.oneDimSearcherToken = oneDimSearcherToken;
	}
	
	/**
	 * Constructor with given one dim minimum searcher.
	 *
	 * @param function - given minimizing function.
	 * @param oneDimSearcherToken - {@link Class} token of minimum searcher. It must implement {@link MinimumSearcher} interface.
	 */
	public FastGradientMethod(final QuadraticFunction function, final Class<? extends MinimumSearcher> oneDimSearcherToken) {
		super(NAME, function);
		this.oneDimSearcherToken = oneDimSearcherToken;
	}
	
	@Override
	protected boolean hasNextPr() {
		final Vector cPoint = currentValue.getFirst();
		final double cNorma = function.getGradient(cPoint).norma();
		
		if (comparator.compare(EPS, cNorma) != -1) {
			return false;
		}
		
		final Function<Double, Vector> oneDimFun = x -> currentValue.getFirst()
				.sum(function.getGradient(currentValue.getFirst()).scalarMul(-x));
		
		MinimumSearcher searcher = getMinimumSearcher(oneDimFun);
		
		try {
			final double val = searcher.findMin(0.0, 1.0).getMin();
			nextValue = new Pair<>(oneDimFun.apply(val), val);
			return true;
		} catch (TimeOutException ex) {
			return false;
		}
	}
	
	/**
	 * Function that returns new instance of minimum searcher.
	 * @param oneDimFun - one dim function for minimizing.
	 * @return - instance of class implementing {@link MinimumSearcher} interface.
	 */
	private MinimumSearcher getMinimumSearcher(Function<Double, Vector> oneDimFun) {
		try {
			return oneDimSearcherToken
					.getConstructor(double.class, double.class, Function.class)
					.newInstance(EPS / 100, STANDARD_DELTA, oneDimFun.andThen(function));
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
			throw new RuntimeException("There's no such constructor: " + ex.getMessage());
		}
	}
	
	@Override
	protected Pair<Vector, Double> nextPr() {
		this.currentValue = this.nextValue;
		
		return this.currentValue;
	}
}
