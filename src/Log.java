public class Log extends BinaryExpression implements Expression {
    private Expression base, num;

    public Log(Expression base, Expression num) {
        super(base, num);
        this.base = base;
        this.num = num;
    }

    public Log(String base, Expression num) {
        this(new Var(base), num);
    }

    public Log(Expression base, String num) {
        this(base, new Var(num));
    }

    public Log(String base, String num) {
        this(new Var(base), new Var(num));
    }

    public Log(String base, double num) {
        this(new Var(base), new Num(num));
    }

    public Log(double base, String num) {
        this(new Num(base), new Var(num));
    }

    public Log(double base, double num) {
        this(new Num(base), new Num(num));
    }

    public Log(double base, Expression num) {
        this(new Num(base), num);
    }

    public Log(Expression base, double num) {
        this(base, new Num(num));
    }

    @Override
    protected double calculate(Expression expression) throws Exception {
        return Math.log(expression.getRight().evaluate()) / Math.log(expression.getLeft().evaluate());
    }

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
    public String toString() {
        return "log(" + base.toString() + "," + num.toString() + ")";
    }
}
