package pl.put.poznan.transformer.logic;

public class CapitalizeTransformer implements TextTransformer{

    public String transform(String text){
        String capitalized = text.substring(0, 1).toUpperCase() + text.substring(1);
        return capitalized;
    }
}
