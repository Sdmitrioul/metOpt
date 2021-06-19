package skroba.lab3.generators;

import skroba.utils.matrix.Matrix;
import skroba.utils.matrix.SquareMatrix;

public class GeneratorSimple extends AbstractGenerator{
	@Override
	public Matrix generate(int n) {
		double[][] matrix = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = Math.random() * 10;
			}
		}
		return new SquareMatrix(matrix);
	}
}
