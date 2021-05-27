package skroba.utils;

/**
 * Simple class, containing pair of elements.
 * @param <F> - first element.
 * @param <S> - second element.
 */
public class Pair<F, S> {
	final F first;
	final S second;
	
	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}
	
	public F getFirst() {
		return first;
	}
	
	public S getSecond() {
		return second;
	}
}
