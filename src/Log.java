/**
 * Class Log.
 */
public class Log extends BinaryExpression implements Expression {
    private Expression base, num;

    /**
     * class constructor.
     *
     * @param base expression represent base
     * @param num  expression represent num
     */
    public Log(Expression base, Expression num) {
        super(base, num);
        this.base = base;
        this.num = num;
    }

    /**
     * class constructor.
     *
     * @param base expression represent base
     * @param num  expression represent num
     */
    public Log(Expression base, String num) {
        this(base, new Var(num));
    }

    /**
     * class constructor.
     *
     * @param base expression represent base
     * @param num  expression represent num
     */
    public Log(Expression base, double num) {
        this(base, new Num(num));
    }

    /**
     * class constructor.
     *
     * @param base expression represent base
     * @param num  expression represent num
     */
    public Log(String base, Expression num) {
        this(new Var(base), num);
    }

    /**
     * class constructor.
     *
     * @param base expression represent base
     * @param num  expression represent num
     */
    public Log(String base, String num) {
        this(new Var(base), new Var(num));
    }

    /**
     * class constructor.
     *
     * @param base expression represent base
     * @param num  expression represent num
     */
    public Log(String base, double num) {
        this(new Var(base), new Num(num));
    }

    /**
     * class constructor.
     *
     * @param base expression represent base
     * @param num  expression represent num
     */
    public Log(double base, Expression num) {
        this(new Num(base), num);
    }

    /**
     * class constructor.
     *
     * @param base expression represent base
     * @param num  expression represent num
     */
    public Log(double base, String num) {
        this(new Num(base), new Var(num));
    }

    /**
     * class constructor.
     *
     * @param base expression represent base
     * @param num  expression represent num
     */
    public Log(double base, double num) {
        this(new Num(base), new Num(num));
    }

    @Override
    protected double calculate(Expression expression) throws Exception {
        return Math.log(expression.getRight().evaluate()) / Math.log(expression.getLeft().evaluate());
    }

    @Override
    public double evaluate() throws Exception {
        return Math.log(num.evaluate()) / Math.log(base.evaluate());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression e1 = base.assign(var, expression);
        Expression e2 = num.assign(var, expression);
        return new Log(e1, e2);
    }

    @Override
    public Expression differentiate(String var) {
        return new Div(1, new Mult(new Log(Math.E, base), num));
    }

    @Override
    public Expression simplify() throws Exception {
        //base is only one num
        if (base.isNoVars() && !base.getClass().getTypeName().equals("Num")) {
            base = new Num(base.evaluate());
            return this.simplify();
        }
        //num is only one num
        if (num.isNoVars() && !num.getClass().getTypeName().equals("Num")) {
            num = new Num(num.evaluate());
            return this.simplify();
        }
        //base and power has no vars
        if (base.isNoVars() && num.isNoVars()) {
            return new Num(this.evaluate());
        }
        //log(x,x) = 1
        if (base.toString().equals(num.toString())) {
            return new Num(1);
        }
        if (canBeSimplified(base, base.simplify())) {
            base = base.simplify();
            return this.simplify();
        }
        if (canBeSimplified(num, num.simplify())) {
            num = num.simplify();
            return this.simplify();
        }
        return new Log(base.simplify(), num.simplify());
    }

    @Override
    public String getSign() {
        return ",";
    }

    @Override
    public String toString() {
        return "log(" + base.toString() + this.getSign() + num.toString() + ")";
    }
}
