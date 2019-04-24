/**
 * Class Div.
 */
public class Div extends BinaryExpression implements Expression {
    private Expression left, right;

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Div(Expression left, Expression right) {
        super(left, right);
        this.left = left;
        this.right = right;
    }

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Div(Expression left, String right) {
        this(left, new Var(right));
    }

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Div(Expression left, double right) {
        this(left, new Num(right));
    }

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Div(String left, Expression right) {
        this(new Var(left), right);
    }

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Div(String left, String right) {
        this(new Var(left), new Var(right));
    }

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Div(String left, double right) {
        this(new Var(left), new Num(right));
    }

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Div(double left, Expression right) {
        this(new Num(left), right);
    }

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Div(double left, String right) {
        this(new Num(left), new Var(right));
    }

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Div(double left, double right) {
        this(new Num(left), new Num(right));
    }

    @Override
    public double evaluate() throws Exception {
        if (right.evaluate() == 0) {
            throw new RuntimeException("cannot divide by zero.");
        }
        return left.evaluate() / right.evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression e1 = left.assign(var, expression);
        Expression e2 = right.assign(var, expression);
        return new Div(e1, e2);
    }

    @Override
    public Expression differentiate(String var) {
        return new Div(
                new Minus(
                        new Mult(left.differentiate(var), right),
                        new Mult(left, right.differentiate(var))),
                new Pow(right, 2));
    }

    @Override
    public Expression simplify() throws Exception {
        //left is only one num
        if (left.isNoVars() && !left.getClass().getTypeName().equals("Num")) {
            left = new Num(left.evaluate());
            return this.simplify();
        }
        //right is only one num
        if (right.isNoVars() && !right.getClass().getTypeName().equals("Num")) {
            right = new Num(right.evaluate());
            return this.simplify();
        }
        //left and right has no vars
        if (left.isNoVars() && right.isNoVars()) {
            return new Num(left.evaluate() / right.evaluate());
        }
        //X / X = 1
        if (left.toString().equals(right.toString())) {
            return new Num(1);
        }
        //X / 1 = X
        if (right.toString().equals("1")) {
            return left;
        }
        if (canBeSimplified(left)) {
            left = left.simplify();
            return this.simplify();
        }
        if (canBeSimplified(right)) {
            right = right.simplify();
            return this.simplify();
        }
        return new Div(left.simplify(), right.simplify());
    }

    @Override
    public String getSign() {
        return " / ";
    }
}
