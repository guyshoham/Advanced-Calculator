/**
 * Class BinaryExpression.
 */
abstract class BinaryExpression extends BaseExpression implements Expression {

    BinaryExpression(Expression left, Expression right) {
        super(left, right);
    }
}
