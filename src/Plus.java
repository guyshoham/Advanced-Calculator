/**
 * Plus Class.
 */
public class Plus extends BinaryExpression implements Expression {

    private Expression left, right;

    /**
     * class constructor.
     *
     * @param left  expression
     * @param right expression
     */
    public Plus(Expression left, Expression right) {
        super(left, right);
        this.left = left;
        this.right = right;
    }

    /**
     * class constructor.
     *
     * @param left  expression
     * @param right string
     */
    public Plus(Expression left, String right) {
        this(left, new Var(right));
    }

    /**
     * class constructor.
     *
     * @param left  expression
     * @param right double
     */
    public Plus(Expression left, double right) {
        this(left, new Num(right));
    }

    /**
     * class constructor.
     *
     * @param left  string
     * @param right expression
     */
    public Plus(String left, Expression right) {
        this(new Var(left), right);
    }

    /**
     * class constructor.
     *
     * @param left  string
     * @param right string
     */
    public Plus(String left, String right) {
        this(new Var(left), new Var(right));
    }

    /**
     * class constructor.
     *
     * @param left  string
     * @param right double
     */
    public Plus(String left, double right) {
        this(new Var(left), new Num(right));
    }

    /**
     * class constructor.
     *
     * @param left  double
     * @param right expression
     */
    public Plus(double left, Expression right) {
        this(new Num(left), right);
    }

    /**
     * class constructor.
     *
     * @param left  double
     * @param right string
     */
    public Plus(double left, String right) {
        this(new Num(left), new Var(right));
    }

    /**
     * class constructor.
     *
     * @param left  double
     * @param right double
     */
    public Plus(double left, double right) {
        this(new Num(left), new Num(right));
    }

    @Override
    protected double calculate(Expression expression) throws Exception {
        return expression.getLeft().evaluate() + expression.getRight().evaluate();
    }

    @Override
    public double evaluate() throws Exception {
        return left.evaluate() + right.evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression e1 = left.assign(var, expression);
        Expression e2 = right.assign(var, expression);
        return new Plus(e1, e2);
    }

    @Override
    public Expression differentiate(String var) {
        return new Plus(left.differentiate(var), right.differentiate(var));
    }

    @Override
    public Expression simplify() {
        //X + 0 = X
        if (right.toString().equals("0")) {
            return left;
        }
        //0 + X = X
        if (left.toString().equals("0")) {
            return right;
        }
        return this;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " + " + right.toString() + ")";
    }
}
