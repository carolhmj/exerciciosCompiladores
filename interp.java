//import slp.Stm;

class interp {
  static void interp(Stm s) { /* you write this part */ }

  static int maxargs(Stm s) { /* you write this part */
  	return s.maxargs();
  }

  public static void main(String args[]) throws java.io.IOException {
     System.out.println(maxargs(prog2.prog));
     //interp(prog.prog);
  }
}
