package org.example;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formula {
    private Double a = null;
    private Double b = null;
    private String v;
    private String w;
    private boolean isConvert;

    private static final DecimalFormat decimalFormat;
    private static final DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
    static{
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormat = new DecimalFormat("#.###",decimalFormatSymbols);
    }



    public Double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public boolean isConvert() {
        return isConvert;
    }

    public void setConvert(boolean convert) {
        isConvert = convert;
    }

    public static Formula parserFormula(String inputStr){
        if (inputStr == null) return null;
        List<String> parseredFormulaDigits = new ArrayList<>();
        List<String> parseredFormulaValues = new ArrayList<>();
        Pattern digitPattern = Pattern.compile("\\d+\\.?\\d*|\\?");
        Matcher digitMatcher = digitPattern.matcher(inputStr);
        int i = 0;
        while (digitMatcher.find()) {
            parseredFormulaDigits.add(digitMatcher.group());
            i++;
        }
        Pattern wordPattern = Pattern.compile("[a-zA-Z]+");
        Matcher wordMatcher = wordPattern.matcher(inputStr);
        while (wordMatcher.find()) {
            parseredFormulaValues.add(wordMatcher.group().trim());
            i++;
        }
        if (parseredFormulaDigits.size()!= 2 || parseredFormulaValues.size() != 2) return null;
        Formula formula = new Formula();
        formula.v = parseredFormulaValues.get(0);
        formula.w = parseredFormulaValues.get(1);
        if(parseredFormulaDigits.get(0).equals("?")) {
            formula.b = Double.valueOf(parseredFormulaDigits.get(1));
            formula.isConvert = true;
        }
        else if (parseredFormulaDigits.get(1).equals("?")) {
            formula.a = Double.valueOf(parseredFormulaDigits.get(0));
            formula.isConvert = true;
        } else {
            formula.a = Double.valueOf(parseredFormulaDigits.get(0));
            formula.b = Double.valueOf(parseredFormulaDigits.get(1));
        }
        return formula;
    }

    @Override
    public String toString() {
        if(isConvert) return "Conversion not possible.";
        else return decimalFormat.format(a) + " " + v + " = " + decimalFormat.format(b) + " " + w;
    }


}
