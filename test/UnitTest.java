import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class UnitTest {

    private Map<String, Double> assignment = new TreeMap<>();
    private static final double X = 2;
    private static final double Y = 12;
    private static final double Z = 2;

    private Expression e;

    private void setVariables() {
        assignment.put("x", X);
        assignment.put("y", Y);
        assignment.put("z", Z);
    }

    @Test
    public void Part1Integration() throws Exception {
        setVariables();
        //((2x + y) * 4)^x
        e = new Pow(new Mult(new Plus(new Mult(2, new Var("x")),
                new Var("y")), 4), new Var("x"));
        int value = (int) e.evaluate(assignment);
        assertEquals(4096, value);
    }

    @Test
    public void Plus() throws Exception {
        setVariables();
        e = new Plus("x", new Div("y", 6));
        int actual = (int) e.evaluate(assignment);
        int expected = (int) (X + (Y / 6));
        assertEquals(expected, actual);
    }

    @Test
    public void Pow() throws Exception {
        setVariables();
        e = new Pow("z", new Pow(2, 3));
        int actual = (int) e.evaluate(assignment);
        int expected = (int) Math.pow(Z, Math.pow(2, 3));
        assertEquals(expected, actual);
    }

    @Test
    public void Log() throws Exception {
        setVariables();
        e = new Log(2, 256);
        int actual = (int) e.evaluate(assignment);
        int expected = (int) (Math.log(256) / Math.log(2));
        assertEquals(expected, actual);
    }

    @Test
    public void Mult() throws Exception {
        setVariables();
        e = new Plus(new Mult(2, new Var("x")), new Var("y"));
        int actual = (int) e.evaluate(assignment);
        int expected = (int) ((2 * X) + Y);
        assertEquals(expected, actual);
    }

    @Test
    public void Neg() throws Exception {
        setVariables();
        e = new Neg(5);
        int actual = (int) e.evaluate(assignment);
        int expected = -5;
        assertEquals(expected, actual);
    }

    @Test
    public void Sin() throws Exception {
        setVariables();
        e = new Sin(Math.toRadians(90));
        int actual = (int) e.evaluate(assignment);
        int expected = (int) Math.sin(Math.toRadians(90));
        assertEquals(expected, actual);
    }

    @Test
    public void Cos() throws Exception {
        setVariables();
        e = new Cos(Math.PI);
        int actual = (int) e.evaluate(assignment);
        int expected = (int) Math.cos(Math.PI);
        assertEquals(expected, actual);
    }

    @Test
    public void Differentiate() {
        setVariables();
        e = new Pow(new Var("x"), 4);
        String actual = e.differentiate("x").toString();
        String expected = "(4 * (x^(4 - 1)))";
        assertEquals(expected, actual);
    }

    @Test
    public void DifferentiateVar() {
        setVariables();
        e = new Var("x");
        String actual = e.differentiate("x").toString();
        String expected = "1";
        assertEquals(expected, actual);
    }

    @Test
    public void DifferentiateNum() {
        setVariables();
        e = new Num(5);
        String actual = e.differentiate("x").toString();
        String expected = "0";
        assertEquals(expected, actual);
    }

    @Test
    public void DifferentiatePlus() {
        setVariables();
        e = new Plus("x", "x");
        String actual = e.differentiate("x").toString();
        String expected = "(1 + 1)";
        assertEquals(expected, actual);
    }

    @Test
    public void DifferentiateMinus() {
        setVariables();
        e = new Minus("x", "x");
        String actual = e.differentiate("x").toString();
        String expected = "(1 - 1)";
        assertEquals(expected, actual);
    }

    @Test
    public void DifferentiatePow() {
        setVariables();
        e = new Pow("x", 2);
        String actual = e.differentiate("x").toString();
        String expected = "(2 * (x^(2 - 1)))";
        assertEquals(expected, actual);
    }

    @Test
    public void DifferentiateMult() {
        setVariables();
        e = new Mult(new Pow("x", 2), new Mult(2, "x"));
        String actual = e.differentiate("x").toString();
        String expected = "(((2 * (x^(2 - 1))) * (2 * x)) + ((x^2) * ((0 * x) + (2 * 1))))";
        assertEquals(expected, actual);
    }

    @Test
    public void DifferentiateDiv() {
        setVariables();
        e = new Div(new Pow("x", 2), new Mult(2, "x"));
        String actual = e.differentiate("x").toString();
        String expected = "((((2 * (x^(2 - 1))) * (2 * x)) - ((x^2) * ((0 * x) + (2 * 1)))) / ((2 * x)^2))";
        assertEquals(expected, actual);
    }

    @Test
    public void DifferentiateLog() {
        setVariables();
        e = new Log(Math.E, "x");
        String actual = e.differentiate("x").toString();
        String expected = "(1 / (log(2.718281828459045,2.718281828459045) * x))";
        assertEquals(expected, actual);
    }

    @Test
    public void DifferentiateNeg() {
        setVariables();
        e = new Neg("x");
        String actual = e.differentiate("x").toString();
        String expected = "(-1)";
        assertEquals(expected, actual);
    }

    @Test
    public void DifferentiateSin() {
        setVariables();
        e = new Sin("x");
        String actual = e.differentiate("x").toString();
        String expected = "(cos(x) * 1)";
        assertEquals(expected, actual);
    }

    @Test
    public void DifferentiateCos() {
        setVariables();
        e = new Cos("x");
        String actual = e.differentiate("x").toString();
        String expected = "((-sin(x)) * 1)";
        assertEquals(expected, actual);
    }

    @Test
    public void Simplify1() throws Exception {
        setVariables();
        e = new Pow(new Minus(new Mult(2, 8), 6), 2);
        String actual = e.simplify().toString();
        String expected = "100";
        assertEquals(expected, actual);
    }

    @Test
    public void Simplify2() throws Exception {
        setVariables();
        e = new Plus(new Mult(5, 6), "x");
        String actual = e.simplify().toString();
        String expected = "(30 + x)";
        assertEquals(expected, actual);
    }

    @Test
    public void Simplify3() throws Exception {
        //log(9x,9x)*2y => 2y
        setVariables();
        e = new Mult(new Log(new Mult(9, "x"), new Mult(9, "x")), new Mult(2, "y"));
        String actual = e.simplify().toString();
        String expected = "(2 * y)";
        assertEquals(expected, actual);
    }

    @Test
    public void Simplify4() throws Exception {
        //((3+6)*x + (4x * sin(0))) => 9x
        setVariables();
        e = new Plus(new Mult(new Plus(3, 6), "x"), new Mult(new Mult(4, "x"), new Sin(0)));
        String actual = e.simplify().toString();
        String expected = "(9 * x)";
        assertEquals(expected, actual);
    }


}