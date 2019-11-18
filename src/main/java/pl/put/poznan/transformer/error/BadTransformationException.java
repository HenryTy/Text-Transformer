package pl.put.poznan.transformer.error;

public class BadTransformationException extends Exception {

    public BadTransformationException() {

    }

    public BadTransformationException(String message) {
        super(message);
    }
}
