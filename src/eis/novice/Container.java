package eis.novice;

public class Container {

    Container[] g;
    int n;
    double x;

    public Container() { 
        g = new Container[1000];
        g[0] = this;
        n = 1;
        x = 0;
    }

    public double getAmount() { return x; }

    public void addWater(double x) {
        double y = x / n;
        for (int i=0; i<n; i++)
            g[i].x = g[i].x + y;
    }

    public void connectTo(Container c) {
        double z = (x*n + c.x*c.n) / (n + c.n);

        for (int i=0; i<n; i++) // for each container g[i] in first group
            for (int j=0; j<c.n; j++) { // for each container c.g[j] in second group
                g[i].g[n+j] = c.g[j]; // add second to group of first 
                c.g[j].g[c.n+i] = g[i]; // add first to group of second
            }

        n += c.n;

        for (int i=0; i<n; i++) {
            g[i].n = n;
            g[i].x = z;
        }
    }
}
