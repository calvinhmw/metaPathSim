package metaLessPathSim;

import java.util.Stack;

public class Package {
    public int name;
    public String type;
    public Stack<String> stk;
    public String phaseLabel;

    public Package(int name, String type, String phaseLabel) {
        stk = new Stack<String>();
        this.name = name;
        this.type = type;
        this.phaseLabel = phaseLabel;
    }

    public Package(Package p) {
        this.name = p.name;
        this.type = p.type;
        this.phaseLabel = p.phaseLabel;
        this.stk = (Stack<String>)p.stk.clone();
    }

    public void pushTypeToStack(String type) {
        stk.push(type);
    }

    public String popStack() {
        return stk.empty()? null : stk.pop();
    }

    public String topStack() {
        return stk.empty()? null : stk.peek();
    }

    public int sizeOfStack(){
        return stk.size();
    }

    public boolean emptyStack() {
        return stk.empty();
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setPhaseLabel(String phaseLabel) {
        this.phaseLabel = phaseLabel;
    }

    @Override
    public String toString() {
        return String.format("{%d, %s, %s, %s}", name, type, stk, phaseLabel);
    }

//    public static void main(String[] args) {
//        Package p = new Package(1, "S", "PUSH");
//        p.pushTypeToStack("T");
//        p.pushTypeToStack("P");
//        p.pushTypeToStack("A");
//        p.pushTypeToStack("C");
//        Package pp = new Package(p);
//        pp.popStack();
//        pp.pushTypeToStack("R");
//        System.out.println(p);
//        System.out.println(pp);
//    }

}
