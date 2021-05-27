package skroba.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple realization of {@link SquareMatrix}.
 */
public class SquareMatrix {
	private final List<Vector> matrix;
	
	/**
	 * Constructor from List of List of Doubles.
	 * @param matrix - List of List of Doubles.
	 */
	public SquareMatrix(final List<List<Double>> matrix) {
		this(matrix.stream()
				.map(Vector::new)
				.toArray(Vector[]::new)
		);
	}
	
	/**
	 * Constructor from array of Vectors.
	 * @param matrix
	 */
	public SquareMatrix(final Vector[] matrix) {
		final int length = matrix.length;
		if (Arrays.stream(matrix).anyMatch(x -> x.size() != length)) {
			throw new IllegalArgumentException("Matrix must be square!");
		}
		this.matrix = Arrays.stream(matrix).collect(Collectors.toList());
	}
	
	/**
	 * Returns element of matrix. on position (row, element).
	 * @param row - row of matrix.
	 * @param column - column of matrix.
	 * @return - element on position (row, element).
	 */
	public double getElement(final int row, final int column) {
		return matrix.get(row).get(column);
	}
	
	/**
	 * Returns {@link Vector} from row of matrix.
	 * @param  - row number.
	 * @return - Vector.
	 */
	public Vector getVector(final int i) {
		return matrix.get(i);
	}
	
	/**
	 * Returns size of squarre matrix.
	 *
	 * @return - size.
	 */
	public int size() {
		return matrix.size();
	}
	
	@Override
	public String toString() {
		return "["
				+ matrix.stream()
				.map(Vector::toString)
				.collect(Collectors
						.joining(",\n"))
				+ "]";
	}
}
