package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Graph {


    private List<List<Double>> matrixKoef = new ArrayList<>();
    private List<Value> valueList = new ArrayList<>();
    private Stack<Integer> stack = new Stack<>();;


    public void addFormula(double koefV, String nameV, double koefW, String nameW){
        int start = addValue(nameV);
        int end = addValue(nameW);
        addEdges(start,end,koefW/koefV);
    }

    private int addValue(String name) {
        int start = findIndexByName(valueList, name);
        if(start == -1){
            valueList.add(new Value(name));
            start = valueList.size()-1;
            for (List<Double> list:matrixKoef) {
                list.add(0.0);
            }
            List<Double> tempList = new ArrayList<>(Collections.nCopies(start+1, 0.0));
            matrixKoef.add(tempList);
        }
        return start;
    }

    public Formula convert(Formula formula) {
        String valueV = formula.getV();
        String valueW = formula.getW();
        clearValueVisited(valueList);
        int indexV = findIndexByName(valueList,valueV);
        int indexW = findIndexByName(valueList,valueW);
        valueList.get(indexV).setVisited(true);
        stack.clear();
        stack.push(indexV);
        while (!stack.empty()){
            int v = getAdjUnvisitedVertex(stack.peek());
            if (v == -1) {
                stack.pop();
            } else {
                valueList.get(v).setVisited(true);
                stack.push(v);
                if (v == indexW) break;
            }
        }
        double totalKoef = 0;
        if (!stack.empty()) {
            totalKoef = 1;
            int indexValueStart = stack.pop();
            while (!stack.empty()){
                int indexValueStop = stack.pop();
                totalKoef *= matrixKoef.get(indexValueStop).get(indexValueStart);
                indexValueStart = indexValueStop;
            }
        }
        if (totalKoef==0) return formula;
        else {
            if(formula.getA() == null) formula.setA(formula.getB()/totalKoef);
            else formula.setB(totalKoef*formula.getA());
            formula.setConvert(false);
            return formula;
        }
    }
    private void clearValueVisited(List<Value> list){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setVisited(false);
        }
    }

    private int getAdjUnvisitedVertex(int v) {
        for (int j = 0; j < valueList.size(); j++) {
            if (matrixKoef.get(v).get(j) != 0 && !valueList.get(j).isVisited()) {
                return j;
            }
        }
        return -1;
    }

    private static int findIndexByName(List<Value> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private void addEdges(int start, int end, double koef){
        matrixKoef.get(start).set(end,koef);
        matrixKoef.get(end).set(start,1/koef);
    }


}
