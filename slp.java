//package slp;

abstract class Stm {
   public abstract int maxargs();
}

class CompoundStm extends Stm {
   Stm stm1, stm2;
   CompoundStm(Stm s1, Stm s2) {stm1=s1; stm2=s2;}
   public int maxargs(){
      int ma1 = stm1.maxargs(), ma2 = stm2.maxargs();
      if (ma1 > ma2){
         return ma1;
      } else {
         return ma2;
      }
   }
}

class AssignStm extends Stm {
   String id; Exp exp;
   AssignStm(String i, Exp e) {id=i; exp=e;}
   public int maxargs(){
      return exp.maxargs();
   }
}

class PrintStm extends Stm {
   ExpList exps;
   PrintStm(ExpList e) {exps=e;}
   public int maxargs(){
      int ma1 = exps.size, ma2 = exps.maxargs();
      if (ma1 > ma2){
         return ma1;
      } else {
         return ma2;
      }            
   }
}

abstract class Exp {
   public abstract int maxargs();
}

class IdExp extends Exp {
   String id;
   IdExp(String i) {id=i;}
   public int maxargs(){
      //Um id não pode ser avaliado para uma expressão print
      return 0;
   }
}

class NumExp extends Exp {
   int num;
   NumExp(int n) {num=n;}
   public int maxargs(){
      return 0;
   }
}

class OpExp extends Exp {
   Exp left, right; int oper;
   final static int Plus=1,Minus=2,Times=3,Div=4;
   OpExp(Exp l, int o, Exp r) {left=l; oper=o; right=r;}
   public int maxargs(){
      int ma1 = left.maxargs(), ma2 = right.maxargs();
      if (ma1 > ma2){
         return ma1;
      } else {
         return ma2;
      }
   }
}

class EseqExp extends Exp {
   Stm stm; Exp exp;
   EseqExp(Stm s, Exp e) {stm=s; exp=e;}
   public int maxargs(){
      int ma1 = stm.maxargs(), ma2 = exp.maxargs();
      if (ma1 > ma2){
         return ma1;
      } else {
         return ma2;
      }
   }
}

abstract class ExpList {
   int size;
   public abstract int maxargs();
}

class PairExpList extends ExpList {
   Exp head; ExpList tail;
   public PairExpList(Exp h, ExpList t) {head=h; tail=t; size = tail.size + 1;}
   public int maxargs(){
      int ma1 = head.maxargs(), ma2 = tail.maxargs();
      if (ma1 > ma2){
         return ma1;
      } else {
         return ma2;
      }   
   }
}

class LastExpList extends ExpList {
   Exp head; 
   public LastExpList(Exp h) {head=h; size=1;}
   public int maxargs(){
      return head.maxargs();
   }
}
