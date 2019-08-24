package eis.appendixa.golfing;

public class Container {
    float a,s,t;
    Container n=this,c=n;
    public float getAmount() {
	for(s=t=0;s<1||c!=n;c=c.n,s++)t+=c.a;
	return t/s; 
    }
    public void connectTo(Container o) {c=o.n;o.n=o.c=n;n=c;}    
    public void addWater(float w) {a+=w;}
}

