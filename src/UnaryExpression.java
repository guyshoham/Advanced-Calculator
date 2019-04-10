/**
 * Class UnaryExpression.
 */
abstract class UnaryExpression extends BaseExpression implements Expression {

    /**
     * class constructor.
     *
     * @param expression expression
     */
    UnaryExpression(Expression expression) {
        super(expression, null);
    }
}
