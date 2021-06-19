package skroba.lab3.generators;

import skroba.utils.Vector;
import skroba.utils.matrix.Matrix;

public interface Generator {
	Matrix generate(int n);
	void generate(String prefix, int from, int to, int step);
	void writeInFile(Matrix matrix, String fileName);
	static Vector generateVector(int n) {
		double[] vector = new double[n];
		for (int i = 0; i < n; i++) {
			vector[i] = Math.random() * 13;
		}
		return new Vector(vector);
	}
}
