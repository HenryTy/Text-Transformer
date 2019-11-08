package pl.put.poznan.transformer.logic;

public class ToUpperTransformer implements TextTransformer {

    public String transform(String text){
        return text.toUpperCase();
    }
}
