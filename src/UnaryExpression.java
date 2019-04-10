/**
 * Class UnaryExpression.
 */
abstract public class UnaryExpression extends BaseExpression implements Expression {

    public UnaryExpression(Expression expression) {
        super(expression, null);
    }
}
