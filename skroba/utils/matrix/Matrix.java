package skroba.utils.matrix;

import skroba.utils.Vec;
import skroba.utils.Vector;

import java.util.ArrayList;
import java.util.List;

public interface Matrix{
	double EPS = 1e-20;
	double getElement(int row, int col);
	void set(int row, int col, double el);
	default Vector mul(Vector vec) {
		if (size() != vec.size()) {
			throw new IllegalArgumentException("Vector must have equal span to matrix");
		}
		
		final List<Double> ans = new ArrayList<>(this.size());
		
		for (int i = 0; i < this.size(); i++) {
			double accum = 0.0;
			for (int j = 0; j < size(); j++) {
				accum += getElement(j, i) * vec.get(j);
			}
			ans.add(accum);
		}
		return new Vector(ans);
	}
	int size();
}
