package skroba.lab2;


import skroba.lab2.methods.AbstractGradientIterator;
import skroba.lab2.methods.ConjugateGradients;
import skroba.lab2.methods.FastGradientMethod;
import skroba.lab2.methods.GradientMethod;
import skroba.utils.*;
import skroba.utils.logger.GradientLoggerImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		final SquareMatrix aMatrixFirst = new SquareMatrix(List.of(List.of(32.0, -2.0), List.of(-2.0, 16.0)));
		final Vector bVectorFirst = new Vector(List.of(5.0, -7.0));
		final QuadraticFunction funFirst = new QuadraticFunction(aMatrixFirst, bVectorFirst, 1);
		
		final SquareMatrix aMatrixSecond = new SquareMatrix(List.of(List.of(2.0, 0.0), List.of(0.0, 6.0)));
		final Vector bVectorSecond = new Vector(List.of(-20.0, -12.0));
		final QuadraticFunction funSecond = new QuadraticFunction(aMatrixSecond, bVectorSecond, 5);
		
		
		AbstractGradientIterator gradientMethod = new GradientMethod(funFirst);
		gradientMethod.setLogger(new GradientLoggerImpl());
		while (gradientMethod.hasNext()) {
			gradientMethod.next();
		}
		
		
		
	}
}

