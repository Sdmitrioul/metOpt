package skroba.utils;

import java.util.function.Function;

/**
 *	Simple realization of algebra quadratic function. Using {@link SquareMatrix} and {@link Vector}.
 */
public class QuadraticFunction implements Function<Vector, Double> {
	private final int span;
	public final SquareMatrix aMatrix;
	public final Vector bVector;
	public final double constant;
	
	/**
	 * Constructor for {@link QuadraticFunction}. Matrix and vector must have equals spans.
	 *
	 * @param aMatrix - matrix
	 * @param bVector - vector
	 * @param constant - constant
	 */
	public QuadraticFunction(final SquareMatrix aMatrix, final Vector bVector, final double constant) {
		this.span = aMatrix.size();
		if (bVector.size() != span) {
			throw new IllegalArgumentException("Matrix and vector must have equals span");
		}
		this.aMatrix = aMatrix;
		this.bVector = bVector;
		this.constant = constant;
	}
	
	/**
	 * Returns value of the function in given point.
	 *
	 * @param point - given point.
	 * @return - value of the function.
	 */
	@Override
	public Double apply(final Vector point) {
		double answer = constant;
		
		for (int i = 0; i < span; i++) {
			answer += aMatrix.mul(point).mul(point);
		}
		
		answer += bVector.mul(point);
		
		return answer;
	}
	
	/**
	 * Function returns gradient of Quadratic Function in given point. Point must have span equals to function span.
	 *
	 * @param point - {@link Vector}, characteristics of given point.
	 * @return - {@link Vector}, gradient of Quadratic function in given point.
	 */
	public Vector getGradient(final Vector point) {
		return aMatrix.mul(point).sum(bVector);
	}
	
	@Override
	public String toString() {
		return aMatrix + " * x^2 + " + bVector + " * x + " + String.format("%.4f", constant);
	}
}
