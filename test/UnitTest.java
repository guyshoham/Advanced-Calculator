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
        e = new Pow(new Mult(new Plus(new Mult(new Num(2), new Var("x")),
                new Var("y")), new Num(4)), new Var("x"));
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
        e = new Plus(new Mult(new Num(2), new Var("x")), new Var("y"));
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
        e = new Pow(new Var("x"), new Num(4));
        String actual = e.differentiate("x").toString();
        String expected = "(4 * (x^(4 - 1)))";
        assertEquals(expected, actual);
    }
}