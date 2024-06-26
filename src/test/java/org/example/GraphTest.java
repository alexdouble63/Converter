package org.example;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void testFindIndexByName() throws Exception {
        Graph graph = new Graph();

        List<Value> list = new ArrayList<>();
        list.add(new Value("byte"));
        list.add(new Value("bit"));

        Method method = Graph.class.getDeclaredMethod("findIndexByName",List.class, String.class);
        method.setAccessible(true);
        assertEquals(1,method.invoke(graph,list, "bit"));
    }

    @Test
    public void testFailFindIndexByName() throws Exception {
        Graph graph = new Graph();

        List<Value> list = new ArrayList<>();
        list.add(new Value("byte"));
        list.add(new Value("bit"));

        Method method = Graph.class.getDeclaredMethod("findIndexByName",List.class, String.class);
        method.setAccessible(true);
        assertEquals(-1,method.invoke(graph,list, "aaa"));
    }

    @Test
    public void testConvert(){
        Graph graph = new Graph();
        Formula formula = Formula.parserFormula("1024 byte = 1 kilobyte");
        graph.addFormula(formula.getA(), formula.getV(), formula.getB(), formula.getW());
        formula = Formula.parserFormula("2 bar = 12 ring");
        graph.addFormula(formula.getA(), formula.getV(), formula.getB(), formula.getW());
        formula = Formula.parserFormula("16.8 ring = 2 pyramid");
        graph.addFormula(formula.getA(), formula.getV(), formula.getB(), formula.getW());
        formula = Formula.parserFormula("4 hare = 1 cat");
        graph.addFormula(formula.getA(), formula.getV(), formula.getB(), formula.getW());
        formula = Formula.parserFormula("5 cat = 0.5 giraffe");
        graph.addFormula(formula.getA(), formula.getV(), formula.getB(), formula.getW());
        formula = Formula.parserFormula("1 byte = 8 bit");
        graph.addFormula(formula.getA(), formula.getV(), formula.getB(), formula.getW());
        formula = Formula.parserFormula("15 ring = 2.5 bar");
        graph.addFormula(formula.getA(), formula.getV(), formula.getB(), formula.getW());

        formula = Formula.parserFormula("1 pyramid = ? bar");
        assertEquals("1 pyramid = 1.4 bar",graph.convert(formula).toString());
        formula = Formula.parserFormula("1 giraffe = ? hare");
        assertEquals("1 giraffe = 40 hare",graph.convert(formula).toString());
        formula = Formula.parserFormula("2 kilobyte = ? bit");
        assertEquals("2 kilobyte = 16384 bit",graph.convert(formula).toString());
        formula = Formula.parserFormula("? kilobyte = 16384 bit");
        assertEquals("2 kilobyte = 16384 bit",graph.convert(formula).toString());
    }

    @Test
    public void testFailConvert(){
        Graph graph = new Graph();
        Formula formula = Formula.parserFormula("1024 byte = 1 kilobyte");
        graph.addFormula(formula.getA(), formula.getV(), formula.getB(), formula.getW());
        formula = Formula.parserFormula("2 bar = 12 ring");
        graph.addFormula(formula.getA(), formula.getV(), formula.getB(), formula.getW());
        formula = Formula.parserFormula("16.8 ring = 2 pyramid");
        graph.addFormula(formula.getA(), formula.getV(), formula.getB(), formula.getW());
        formula = Formula.parserFormula("4 hare = 1 cat");
        graph.addFormula(formula.getA(), formula.getV(), formula.getB(), formula.getW());
        formula = Formula.parserFormula("5 cat = 0.5 giraffe");
        graph.addFormula(formula.getA(), formula.getV(), formula.getB(), formula.getW());
        formula = Formula.parserFormula("1 byte = 8 bit");
        graph.addFormula(formula.getA(), formula.getV(), formula.getB(), formula.getW());
        formula = Formula.parserFormula("15 ring = 2.5 bar");
        graph.addFormula(formula.getA(), formula.getV(), formula.getB(), formula.getW());

        formula = Formula.parserFormula("0.5 byte = ? cat");
        assertEquals("Conversion not possible.",graph.convert(formula).toString());
    }

}