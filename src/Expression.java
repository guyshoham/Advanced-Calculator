import java.util.List;
import java.util.Map;

/**
 * Interface Expression.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided in the assignment.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     *
     * @param assignment assignment
     * @return the result
     * @throws Exception if one or more of the variables in expression has not been assigned
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return the result
     * @throws Exception if cannot evaluate expression (because of arithmetic rules)
     */
    double evaluate() throws Exception;

    /**
     * @return a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * @return a nice string representation of the expression.
     */
    String toString();

    /**
     * @param var        variable
     * @param expression expression
     * @return a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     */
    Expression assign(String var, Expression expression);

    /**
     * @param var variable
     * @return the expression tree resulting from differentiating the current expression relative to variable `var`.
     */
    Expression differentiate(String var);

    /**
     * @return a simplified version of the current expression.
     */
    Expression simplify();

    /**
     * @return true if there is no vars in the expression, false otherwise.
     */
    boolean isNoVars();

    /**
     * @return the sign of the expression.
     */
    String getSign();
}