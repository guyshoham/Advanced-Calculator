/**
 * Class BinaryExpression.
 */
abstract class BinaryExpression extends BaseExpression implements Expression {

    /**
     * class constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    BinaryExpression(Expression left, Expression right) {
        super(left, right);
    }
}
