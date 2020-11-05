import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordHelperTest {

    @Test
    void isNumber() {
        assertTrue(WordHelper.isNumber("43"));
        assertTrue(WordHelper.isNumber("434555.4343"));
        assertTrue(WordHelper.isNumber("0,4343"));

        assertFalse(WordHelper.isNumber("0,f4343"));
        assertFalse(WordHelper.isNumber("aa"));
        assertFalse(WordHelper.isNumber("11aa"));
        assertFalse(WordHelper.isNumber("11o524"));
    }

    @Test
    void wordType() {
        assertEquals(WordHelper.wordType("Latin"), WordHelper.LATIN);
        assertEquals(WordHelper.wordType("Кирилиця"), WordHelper.CYRILLIC);
        assertEquals(WordHelper.wordType("4344,55"), WordHelper.NUMBER);
        assertEquals(WordHelper.wordType("Other444"), WordHelper.OTHER);
    }
}