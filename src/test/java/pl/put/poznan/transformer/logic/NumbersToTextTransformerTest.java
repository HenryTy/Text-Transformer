package pl.put.poznan.transformer.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

public class NumbersToTextTransformerTest {

    private NumbersToTextTransformer numbersToTextTransformer;
    private NumberFormatChecker mockNumberFormatChecker;
    private NumberToTextConverter mockNumberToTextConverter;

    @Before
    public void setUp() throws Exception {
        configureMockNumberFormatChecker();
        configureMockNumberToTextConverter();

        TextTransformer mockBasicTransformer = mock(TextTransformer.class);
        when(mockBasicTransformer.transform(anyString())).then(returnsFirstArg());

        numbersToTextTransformer = new NumbersToTextTransformer(mockBasicTransformer,
                mockNumberFormatChecker, mockNumberToTextConverter);
    }

    private void configureMockNumberFormatChecker() {
        mockNumberFormatChecker = mock(NumberFormatChecker.class);
        when(mockNumberFormatChecker.isNumeric(anyString())).thenReturn(false);
        when(mockNumberFormatChecker.isNumeric("678")).thenReturn(true);
        when(mockNumberFormatChecker.isNumeric("3.14")).thenReturn(true);

        when(mockNumberFormatChecker.isFloat(anyString())).thenReturn(false);
        when(mockNumberFormatChecker.isFloat("3.14")).thenReturn(true);
    }

    private void configureMockNumberToTextConverter() {
        mockNumberToTextConverter = mock(NumberToTextConverter.class);
        when(mockNumberToTextConverter.convertIntegerPart("678")).thenReturn("sześćset siedemdziesiąt osiem");
        when(mockNumberToTextConverter.convertIntegerPart("3.14")).thenReturn("trzy");
        when(mockNumberToTextConverter.convertFractionalPart("3.14")).thenReturn("czternaście setnych");
    }

    @Test
    public void testDecoratedTransformerIsInvoked() {
        TextTransformer mockTextTransformer = mock(TextTransformer.class);
        String textReturnedByMockTransformer = "My transformed text";
        when(mockTextTransformer.transform(anyString())).thenReturn(textReturnedByMockTransformer);
        NumbersToTextTransformer testedTransformer = new NumbersToTextTransformer(mockTextTransformer,
                mockNumberFormatChecker, mockNumberToTextConverter);

        String textToTransform = "678 i 3.14 to moje ulubione liczby";
        String transformedText = testedTransformer.transform(textToTransform).trim();

        verify(mockTextTransformer).transform(textToTransform);
        assertEquals(textReturnedByMockTransformer, transformedText);
    }

    @Test
    public void testTransformTextWithoutNumbers() {
        String textToTransform = "Ala ma sześćset siedemdziesiąt osiem kotów";
        String transformedText = numbersToTextTransformer.transform(textToTransform).trim();

        verify(mockNumberFormatChecker, atLeastOnce()).isNumeric(anyString());
        verify(mockNumberToTextConverter, never()).convertIntegerPart(anyString());
        verify(mockNumberToTextConverter, never()).convertFractionalPart(anyString());
        assertEquals(textToTransform, transformedText);
    }

    @Test
    public void testTransformTextWithOneIntegerNumber() {
        String textToTransform = "Ala ma 678 kotów";
        String transformedText = numbersToTextTransformer.transform(textToTransform).trim();

        verify(mockNumberFormatChecker, atLeastOnce()).isNumeric(anyString());
        verify(mockNumberToTextConverter).convertIntegerPart("678");
        verify(mockNumberToTextConverter, never()).convertFractionalPart(anyString());
        assertEquals("Ala ma sześćset siedemdziesiąt osiem kotów", transformedText);
    }

    @Test
    public void testTransformTextWithOneFloatNumber() {
        String textToTransform = "Ala ma 3.14 kota";
        String transformedText = numbersToTextTransformer.transform(textToTransform).trim();

        verify(mockNumberFormatChecker, atLeastOnce()).isFloat(anyString());
        verify(mockNumberToTextConverter).convertIntegerPart("3.14");
        verify(mockNumberToTextConverter).convertFractionalPart("3.14");
        assertEquals("Ala ma trzy i czternaście setnych kota", transformedText);
    }

    @Test
    public void testTransformTextWithOnlyNumbers() {
        String textToTransform = "678 3.14 3.14 678";
        String transformedText = numbersToTextTransformer.transform(textToTransform).trim();

        assertEquals("sześćset siedemdziesiąt osiem trzy i czternaście setnych" +
                " trzy i czternaście setnych sześćset siedemdziesiąt osiem", transformedText);
    }

    @Test
    public void testTransformTextStartingWithNumber() {
        String textToTransform = "678 studentów kocha inżynierię oprogramowania";
        String transformedText = numbersToTextTransformer.transform(textToTransform).trim();

        assertEquals("sześćset siedemdziesiąt osiem studentów kocha inżynierię oprogramowania", transformedText);
    }

    @Test
    public void testTransformTextEndingWithNumber() {
        String textToTransform = "Bla bla bla 3.14";
        String transformedText = numbersToTextTransformer.transform(textToTransform).trim();

        assertEquals("Bla bla bla trzy i czternaście setnych", transformedText);
    }
}