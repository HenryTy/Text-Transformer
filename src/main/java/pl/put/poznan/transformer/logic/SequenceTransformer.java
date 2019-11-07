package pl.put.poznan.transformer.logic;

public class SequenceTransformer {

    private final String[] transforms;

    public SequenceTransformer(String[] transforms){
        this.transforms = transforms;
    }

    public String transform(String text){
        // of course normally it would to something based on transforms
        return text.toUpperCase();
    }
}
