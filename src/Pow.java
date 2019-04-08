public class Pow extends BinaryExpression implements Expression {
    private Expression base, power;

    public Pow(Expression base, Expression power) {
        super(base, power);
        this.base = base;
        this.power = power;
    }

    public Pow(String base, Expression power) {
        this(new Var(base), power);
    }

    public Pow(Expression base, String power) {
        this(base, new Var(power));
    }

    public Pow(Expression base, double power) {
        this(base, new Num(power));
    }

    public Pow(double base, Expression power) {
        this(new Num(base), power);
    }

    public Pow(String base, String power) {
        this(new Var(base), new Var(power));
    }

    public Pow(String base, double power) {
        this(new Var(base), new Num(power));
    }

    public Pow(double base, String power) {
        this(new Num(base), new Var(power));
    }

    public Pow(double base, double power) {
        this(new Num(base), new Num(power));
    }

    @Override
    protected double calculate(Expression expression) throws Exception {
        return Math.pow(expression.getLeft().evaluate(), expression.getRight().evaluate());
    }

    public double evaluate() throws Exception {
        return Math.pow(base.evaluate(), power.evaluate());
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(power, new Pow(base, new Minus(power, 1)));
    }

    @Override
    public Expression simplify() {
        return null;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression e1 = base.assign(var, expression);
        Expression e2 = power.assign(var, expression);
        return new Pow(e1, e2);
    }

    @Override
    public String toString() {
        return "(" + base.toString() + "^" + power.toString() + ")";
    }
}
