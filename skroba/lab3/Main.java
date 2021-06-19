package skroba.lab3;

import skroba.lab3.generators.Generator;
import skroba.lab3.generators.GeneratorDiagonal;
import skroba.lab3.matrix.ProfileMatrix;
import skroba.lab3.method.LUSolver;
import skroba.lab3.method.Solver;
import skroba.utils.Vec;
import skroba.utils.Vector;
import skroba.utils.fileWriter.FileWriter;
import skroba.utils.fileWriter.FileWriterImpl;
import skroba.utils.matrix.Matrix;
import skroba.utils.matrix.SquareMatrix;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) {
		//generateLU();
		testLU();
	}
	
	private static void testLU() {
		try (final FileWriter writer = new FileWriterImpl("results/lu")) {
			writer.write("\\begin{tabular}{|c|c|c|c|}\n");
			writer.write("\\hline\n");
			writer.write("n & k & $ || x^{*} - x_{k} || $ & $ || x^{*} - x_{k} || / || x^{*} || $ \\\\ \\hline\n");
			Solver solver = new LUSolver();
			for (int i = 10; i <= 1000; i *= 10) {
				Vector x = new Vector(IntStream.range(1, i + 1).mapToDouble(Double::new).boxed().collect(Collectors.toList()));
				double norma = x.norma();
				for (int j = 1; j < 8; j++) {
					Matrix matrix = MatrixReader.read(String.format("data/lu/%d/%d", i, j));
					Vector f = matrix.mul(x);
					Vector ans = solver.solve(new ProfileMatrix(matrix), f);
					double thirdPar = x.sum(ans.scalarMul(-1)).norma();
					double fourthPar = thirdPar / norma;
					writer.write(String.format("%d & %d & %.12f & %.12f \\\\ \\hline \n", i, j, thirdPar, fourthPar));
				}
			}
			writer.write("\\end{tabular}");
		}
	}
	
	private static void generateLU() {
		GeneratorDiagonal generatorDiagonal = new GeneratorDiagonal();
		generatorDiagonal.generate("data/lu", 10, 1000, 10, 1, 8);
	}
}
