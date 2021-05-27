package skroba.utils;

import java.util.function.Function;

public class QuadraticFunction implements Function<Vector, Double> {
	private final int span;
	public final SquareMatrix aMatrix;
	public final Vector bVector;
	public final double constant;
	
	public QuadraticFunction(SquareMatrix aMatrix, Vector bVector, double constant) {
		this.span = aMatrix.size();
		if (bVector.size() != span) {
			throw new IllegalArgumentException("Matrix and vector must have equals span");
		}
		this.aMatrix = aMatrix;
		this.bVector = bVector;
		this.constant = constant;
	}
	
	@Override
	public Double apply(Vector vector) {
		double answer = 0.0;
		
		
		return answer;
	}
}
