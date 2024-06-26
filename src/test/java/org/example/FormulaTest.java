package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class FormulaTest {

    @Test
    public void testGetSetA(){
        Formula formula = new Formula();
        formula.setA(1.0);
        assertEquals((Double) 1.0, formula.getA());
    }

    @Test
    public void testGetSetB(){
        Formula formula = new Formula();
        formula.setB(1.0);
        assertEquals((Double) 1.0, formula.getB());
    }

    @Test
    public void testGetSetV(){
        Formula formula = new Formula();
        formula.setV("byte");
        assertEquals("byte", formula.getV());
    }

    @Test
    public void testGetSetW(){
        Formula formula = new Formula();
        formula.setW("byte");
        assertEquals("byte", formula.getW());
    }

    @Test
    public void testIsConvert(){
        Formula formula = new Formula();
        formula.setConvert(true);
        assertEquals(true, formula.isConvert());
        formula.setConvert(false);
        assertEquals(false, formula.isConvert());
    }

    @Test
    public void parserFormulaCorrect() {
        Formula formula = Formula.parserFormula("16.8 ring = 2 pyramid");
        assertNotNull(formula);
        assertEquals("ring",formula.getV());
        assertEquals("pyramid",formula.getW());
        assertEquals(Double.valueOf(16.8), formula.getA());
        assertEquals(Double.valueOf(2), formula.getB());
        assertEquals(false, formula.isConvert());

        formula = Formula.parserFormula("? ring = 2 pyramid");
        assertNotNull(formula);
        assertEquals("ring",formula.getV());
        assertEquals("pyramid",formula.getW());
        assertNull(formula.getA());
        assertEquals(Double.valueOf(2), formula.getB());
        assertEquals(true, formula.isConvert());
    }

    @Test
    public void parserFormulaFirstQuestionMark() {
        Formula formula = Formula.parserFormula("? ring = 2 pyramid");
        assertNotNull(formula);
        assertEquals("ring",formula.getV());
        assertEquals("pyramid",formula.getW());
        assertNull(formula.getA());
        assertEquals(Double.valueOf(2), formula.getB());
        assertEquals(true, formula.isConvert());
    }

    @Test
    public void parserFormulaSecondQuestionMark() {
        Formula formula = Formula.parserFormula("16.7 ring = ? pyramid");
        assertNotNull(formula);
        assertEquals("ring",formula.getV());
        assertEquals("pyramid",formula.getW());
        assertEquals(Double.valueOf(16.7),formula.getA());
        assertNull(formula.getB());
        assertEquals(true, formula.isConvert());
    }

    @Test
    public void parserFormulaWrong() {
        Formula formula = Formula.parserFormula("16.8 ring = pyramid");
        assertNull(formula);

        formula = Formula.parserFormula("16.8 ring = 2");
        assertNull(formula);

        formula = Formula.parserFormula("ring = 2 pyramid");
        assertNull(formula);

        formula = Formula.parserFormula("");
        assertNull(formula);

        formula = Formula.parserFormula(null);
        assertNull(formula);
    }

    @Test
    public void testToStringOk() {
        Formula formula = Formula.parserFormula("16.8 ring = 2 pyramid");
        assertEquals("16.8 ring = 2 pyramid",formula.toString());
    }

    @Test
    public void testToStringFail() {
        Formula formula = Formula.parserFormula("16.7 ring = ? pyramid");
        assertEquals("Conversion not possible.",formula.toString());
    }


}