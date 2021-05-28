package skroba.lab2.methods;

import skroba.utils.Pair;
import skroba.utils.QuadraticFunction;
import skroba.utils.Vector;

import java.util.NoSuchElementException;

public class GradientMethod extends AbstractGradientIterator {
	private final static String NAME = "Gradient descent";
	
	private final double step = 1.0;
	
	public GradientMethod(final QuadraticFunction function) {
		super(NAME, function);
	}
	
	public GradientMethod(final QuadraticFunction function, final Double EPS) {
		super(NAME, function, EPS);
	}
	
	@Override
	public boolean hasNext() {
		return false;
	}
	
	@Override
	public Pair<Vector, Double> next() {
		if (currentValue == null && nextValue == null && !hasNext()) {
			throw new NoSuchElementException("There isn't any next position");
		}
		
		this.currentValue = this.nextValue;
		
		return this.currentValue;
	}
}
