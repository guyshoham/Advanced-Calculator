/**
 * Class Mult.
 */
public class Mult extends BinaryExpression implements Expression {
    private Expression left, right;

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Mult(Expression left, Expression right) {
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
    public Mult(Expression left, String right) {
        this(left, new Var(right));
    }

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Mult(Expression left, double right) {
        this(left, new Num(right));
    }

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Mult(String left, Expression right) {
        this(new Var(left), right);
    }

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Mult(String left, String right) {
        this(new Var(left), new Var(right));
    }

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Mult(String left, double right) {
        this(new Var(left), new Num(right));
    }

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Mult(double left, Expression right) {
        this(new Num(left), right);
    }

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Mult(double left, String right) {
        this(new Num(left), new Var(right));
    }

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Mult(double left, double right) {
        this(new Num(left), new Num(right));
    }

    @Override
    protected double calculate(Expression expression) throws Exception {
        return expression.getLeft().evaluate() * expression.getRight().evaluate();
    }

    @Override
    public double evaluate() throws Exception {
        return left.evaluate() * right.evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression e1 = left.assign(var, expression);
        Expression e2 = right.assign(var, expression);
        return new Mult(e1, e2);
    }

    @Override
    public Expression differentiate(String var) {
        return new Plus(new Mult(left.differentiate(var), right), new Mult(left, right.differentiate(var)));
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
            return new Num(left.evaluate() * right.evaluate());
        }
        //X * 1 = X
        if (right.toString().equals("1")) {
            return left;
        }
        //1 * X = X
        if (left.toString().equals("1")) {
            return right;
        }
        //X * 0 = 0, 0 * X = 0
        if (left.toString().equals("0") || right.toString().equals("0")) {
            return new Num(0);
        }
        if (canBeSimplified(left)) {
            left = left.simplify();
            return this.simplify();
        }
        if (canBeSimplified(right)) {
            right = right.simplify();
            return this.simplify();
        }
        return new Mult(left.simplify(), right.simplify());
    }

    @Override
    public String getSign() {
        return " * ";
    }
}
