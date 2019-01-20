public class Wrapper {

    public String x;

    public Wrapper(String x){
        this.x=x;
    }

    public String backToString() {
        return x;
    }

    public boolean equals(Object o) {
   if(o == null) return false;
   if(!(o instanceof Wrapper)) return false;
   final Wrapper p = (Wrapper) o;
   if(p.x.equals(this.x)) return true;
   return false;
}

    @Override
    public int hashCode() {
      int hash = 3;
      hash = 53 * hash + x.length();
      return hash;
    }
}