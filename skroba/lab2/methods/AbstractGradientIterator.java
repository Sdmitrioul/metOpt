package skroba.lab2.methods;

import skroba.utils.*;

import java.util.Collections;

/**
 * Abstract class for gradient methods.
 */
public abstract class AbstractGradientIterator implements Iterator<Pair<Vector, Double>> {
	protected final Double EPS;
	protected final QuadraticFunction function;
	private final String methodName;
	protected final DoubleComparator comparator;
	protected Pair<Vector, Double> currentValue;
	protected Pair<Vector, Double> nextValue;
	
	public AbstractGradientIterator(final String methodName, final QuadraticFunction function, final Double EPS) {
		this.function = function;
		this.methodName = methodName;
		this.EPS = EPS;
		this.comparator = new DoubleComparator(EPS);
		final Vector nVector = new Vector(Collections.nCopies(function.getSpan(), 0.0));
		this.currentValue = new Pair<>(nVector, function.apply(nVector));
	}
	
	public AbstractGradientIterator(final String methodName, final QuadraticFunction function) {
		this(methodName, function, 1e-3);
	}
	
	public abstract boolean hasNext();
	
	public abstract Pair<Vector, Double> next();
	
	/**
	 * Function that returns method name.
	 * @return - {@link String}, presenting method name.
	 */
	public String getMethodName() {
		return methodName;
	}
	
	/**
	 * Returns current value of iterator.
	 *
	 * @return {@link Pair} of {@link Vector} showing current point and double value, result of function on current point.
	 */
	public Pair<Vector, Double> getCurrentValue() {
		return currentValue;
	}
	
	/**
	 * Gradient of the function in current point.
	 * @return {@link Vector} gradient in current point.
	 */
	public Vector getGradient() {
		return function.getGradient(currentValue.getFirst());
	}
	
	/**
	 * Returns iterating function.
	 * @return {@link QuadraticFunction} function.
	 */
	public QuadraticFunction getFunction() {
		return function;
	}
}
