package org.example;


import java.util.*;


public class App
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        String inputSting;
        Graph graph = new Graph();
        do {
            System.out.println("Введите формулу в формате \"aV=bW\". Для перевода величин - введите формулу заменив \"a\" или \"b\" на знак \"?\" - ?V=bW. Для завершения наберите \"выход\"");
            inputSting = scanner.nextLine();
            if (inputSting.equals("выход")) break;
            Formula formula = Formula.parserFormula(inputSting);
            if (formula == null) System.out.println("Неверный формат формулы");
            else if(formula.isConvert()){
                System.out.println(graph.convert(formula));
            } else graph.addFormula(formula.getA(), formula.getV(), formula.getB(), formula.getW());

        } while (!inputSting.equals("выход"));


    }


}
