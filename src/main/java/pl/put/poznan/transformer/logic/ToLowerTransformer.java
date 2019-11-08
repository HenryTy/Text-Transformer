package pl.put.poznan.transformer.logic;

public class ToLowerTransformer implements TextTransformer{

    public String transform(String text){
        return text.toLowerCase();
    }
}
