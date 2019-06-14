package eis.generic;

/** A use case for water containers.
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class UseCase {
    public static void main(String[] args) {
	Container a = new Container();
	Container b = new Container();
	Container c = new Container();
	Container d = new Container();
	
	a.update(12.0);
	d.update(8.0);
	a.connectTo(b);
	System.out.println(a.get()+" "+b.get()+" "+
			   c.get()+" "+d.get());
	b.connectTo(c);
	System.out.println(a.get()+" "+b.get()+" "+
			   c.get()+" "+d.get());
	b.connectTo(d);
	System.out.println(a.get()+" "+b.get()+" "+
			   c.get()+" "+d.get());
    }
}
