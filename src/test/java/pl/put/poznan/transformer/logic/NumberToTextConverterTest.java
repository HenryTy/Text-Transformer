package pl.put.poznan.transformer.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumberToTextConverterTest {

    private NumberToTextConverter numberToTextConverter;

    @Before
    public void setUp() throws Exception {
        numberToTextConverter = new NumberToTextConverter();
    }

    @Test
    public void testConvertIntegerPartFloatNumber() {
        String convertedNumberWithComma = numberToTextConverter.convertIntegerPart("3,14");
        String convertedNumberWithDot = numberToTextConverter.convertIntegerPart("3.14");

        assertEquals("trzy", convertedNumberWithComma);
        assertEquals("trzy", convertedNumberWithDot);
    }

    @Test
    public void testConvertIntegerPartOneDigitNumber() {
        String convertedNumber = numberToTextConverter.convertIntegerPart("7");
        assertEquals("siedem", convertedNumber);
    }

    @Test
    public void testConvertIntegerPartTeenNumber() {
        String convertedNumber = numberToTextConverter.convertIntegerPart("18");
        assertEquals("osiemnaście", convertedNumber);
    }

    @Test
    public void testConvertIntegerPartMultipleOfTen() {
        String convertedNumber = numberToTextConverter.convertIntegerPart("60");
        assertEquals("sześćdziesiąt", convertedNumber);
    }

    @Test
    public void testConvertIntegerPartTwoDigitNumber() {
        String convertedNumber = numberToTextConverter.convertIntegerPart("89");
        assertEquals("osiemdziesiąt dziewięć", convertedNumber);
    }

    @Test
    public void testConvertIntegerPartMultipleOfHundred() {
        String convertedNumber = numberToTextConverter.convertIntegerPart("300");
        assertEquals("trzysta", convertedNumber);
    }

    @Test
    public void testConvertIntegerPartThreeDigitNumber() {
        String convertedNumber = numberToTextConverter.convertIntegerPart("999");
        assertEquals("dziewięćset dziewięćdziesiąt dziewięć", convertedNumber);
    }

    @Test
    public void testConvertIntegerPartThreeDigitNumberWithZeroInside() {
        String convertedNumber = numberToTextConverter.convertIntegerPart("504");
        assertEquals("pięćset cztery", convertedNumber);
    }

    @Test
    public void testConvertFractionalPartNumberWithComma() {
        String convertedNumber = numberToTextConverter.convertFractionalPart("0,74");
        assertEquals("siedemdziesiąt cztery setne", convertedNumber);
    }

    @Test
    public void testConvertFractionalPartJednaDziesiata() {
        String convertedNumber = numberToTextConverter.convertFractionalPart("128.1");
        assertEquals("jedna dziesiąta", convertedNumber);
    }

    @Test
    public void testConvertFractionalPartDwieDziesiate() {
        String convertedNumber = numberToTextConverter.convertFractionalPart("5.2");
        assertEquals("dwie dziesiąte", convertedNumber);
    }

    @Test
    public void testConvertFractionalPartEndsWithDziesiatych() {
        String convertedNumber = numberToTextConverter.convertFractionalPart("18.5");
        assertEquals("pięć dziesiątych", convertedNumber);
    }

    @Test
    public void testConvertFractionalPartJednaSetna() {
        String convertedNumber = numberToTextConverter.convertFractionalPart("13.01");
        assertEquals("jedna setna", convertedNumber);
    }

    @Test
    public void testConvertFractionalPartEndsWithJedenSetnych() {
        String convertedNumber = numberToTextConverter.convertFractionalPart("0.51");
        assertEquals("pięćdziesiąt jeden setnych", convertedNumber);
    }

    @Test
    public void testConvertFractionalPartEndsWithSetne() {
        String convertedNumber = numberToTextConverter.convertFractionalPart("0.42");
        assertEquals("czterdzieści dwie setne", convertedNumber);
    }

    @Test
    public void testConvertFractionalPartEndsWithSetnych() {
        String convertedNumber = numberToTextConverter.convertFractionalPart("0.96");
        assertEquals("dziewięćdziesiąt sześć setnych", convertedNumber);
    }

    @Test
    public void testConvertFractionalPartTeenNumber() {
        String convertedNumber = numberToTextConverter.convertFractionalPart("0.11");
        assertEquals("jedenaście setnych", convertedNumber);
    }

    @Test
    public void testConvertFractionalPartEndsWithZero() {
        String convertedNumber = numberToTextConverter.convertFractionalPart("0.80");
        assertEquals("osiemdziesiąt setnych", convertedNumber);
    }

    @Test
    public void testConvertFractionalPartEqualsZero() {
        String convertedNumber0 = numberToTextConverter.convertFractionalPart("0.0");
        String convertedNumber00 = numberToTextConverter.convertFractionalPart("0.00");
        assertEquals("", convertedNumber0);
        assertEquals("", convertedNumber00);
    }
}