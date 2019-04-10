/**
 * Class Pow.
 */
public class Pow extends BinaryExpression implements Expression {
    private Expression base, power;

    /**
     * class constructor.
     *
     * @param base  expression represent base
     * @param power expression represent power
     */
    public Pow(Expression base, Expression power) {
        super(base, power);
        this.base = base;
        this.power = power;
    }

    /**
     * class constructor.
     *
     * @param base  expression represent base
     * @param power expression represent power
     */
    public Pow(Expression base, String power) {
        this(base, new Var(power));
    }

    /**
     * class constructor.
     *
     * @param base  expression represent base
     * @param power expression represent power
     */
    public Pow(Expression base, double power) {
        this(base, new Num(power));
    }

    /**
     * class constructor.
     *
     * @param base  expression represent base
     * @param power expression represent power
     */
    public Pow(String base, Expression power) {
        this(new Var(base), power);
    }

    /**
     * class constructor.
     *
     * @param base  expression represent base
     * @param power expression represent power
     */
    public Pow(String base, String power) {
        this(new Var(base), new Var(power));
    }

    /**
     * class constructor.
     *
     * @param base  expression represent base
     * @param power expression represent power
     */
    public Pow(String base, double power) {
        this(new Var(base), new Num(power));
    }

    /**
     * class constructor.
     *
     * @param base  expression represent base
     * @param power expression represent power
     */
    public Pow(double base, Expression power) {
        this(new Num(base), power);
    }

    /**
     * class constructor.
     *
     * @param base  expression represent base
     * @param power expression represent power
     */
    public Pow(double base, String power) {
        this(new Num(base), new Var(power));
    }

    /**
     * class constructor.
     *
     * @param base  expression represent base
     * @param power expression represent power
     */
    public Pow(double base, double power) {
        this(new Num(base), new Num(power));
    }

    @Override
    public double evaluate() throws Exception {
        return Math.pow(base.evaluate(), power.evaluate());
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(power, new Pow(base, new Minus(power, 1)));
    }

    @Override
    public Expression simplify() throws Exception {
        //base is only one num
        if (base.isNoVars() && !base.getClass().getTypeName().equals("Num")) {
            base = new Num(base.evaluate());
            return this.simplify();
        }
        //power is only one num
        if (power.isNoVars() && !power.getClass().getTypeName().equals("Num")) {
            power = new Num(power.evaluate());
            return this.simplify();
        }
        //base and power has no vars
        if (base.isNoVars() && power.isNoVars()) {
            return new Num(this.evaluate());
        }
        if (canBeSimplified(base)) {
            base = base.simplify();
            return this.simplify();
        }
        if (canBeSimplified(power)) {
            power = power.simplify();
            return this.simplify();
        }
        return new Pow(base.simplify(), power.simplify());
    }

    @Override
    public String getSign() {
        return "^";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression e1 = base.assign(var, expression);
        Expression e2 = power.assign(var, expression);
        return new Pow(e1, e2);
    }
}
