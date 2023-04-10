package OOP.ec20315.A8;

public class A8_ec20315 {
    public static void main(String[] args){
        Visitable r = new Room_ec20315();
        Visitor v = new GUIVisitor_ec20315(System.out,System.in);
        r.visit(v, Direction.FROM_SOUTH);
    }
}
