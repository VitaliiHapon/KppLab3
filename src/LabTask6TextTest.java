import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LabTask6TextTest {

    @Test
    void calculateStatistic() {
        LabTask6Text obj = new LabTask6Text(
                "LatinA LatinB. кирилицяА кирилицяБ кирилицяВ? 323 434,44 3331 1? Ohter1 Ohter2. notSentence ");

        var map = new HashMap<String, Integer>();
        obj.calculateStatistic(map);

        assertEquals(map.get(WordHelper.LATIN), 2);
        assertEquals(map.get(WordHelper.CYRILLIC), 3);
        assertEquals(map.get(WordHelper.NUMBER), 4);
        assertEquals(map.get(WordHelper.OTHER), 2);
    }
}