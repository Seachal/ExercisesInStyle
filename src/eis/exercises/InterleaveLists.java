import java.util.*;

public class InterleaveLists {

    /**
     * Returns the interleaving of two lists.
     * Assumes the input lists have the same length.
     */ 
    public static <T> List<T> interleaveLists(List<? extends T> a, List<? extends T> b) {
	if (a==null || b==null)
	    throw new NullPointerException("Both lists must be non-null.");
	if (a.size() != b.size())
	    throw new IllegalArgumentException("The lists must have the same length.");

	List<T> result = new ArrayList<>();
	Iterator<? extends T> ia = a.iterator(), ib = b.iterator();
	while (ia.hasNext()) {
	    result.add(ia.next());
	    result.add(ib.next());
	}
	assert interleaveCheckPost(a, b, result);
	return result;
    }

    private static boolean interleaveCheckPost(List<?> a, List<?> b, List<?> result) {
	if (result.size() != a.size() + b.size())
	    return false;

	Iterator<?> ia = a.iterator(), ib = b.iterator();
	boolean odd = true;
	for (Object elem: result) {
	    if ( odd && elem != ia.next()) return false;
	    if (!odd && elem != ib.next()) return false;
	    odd = !odd;
	}
	return true;
    }
}
