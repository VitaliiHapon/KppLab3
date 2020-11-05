

import java.util.Iterator;
import java.util.Map;

public abstract class Text {
    private String text;

    public Text(String text){
        this.text = text;
    }

    public abstract Iterator<Sentence> getSentences();

    public abstract void calculateStatistic(Map<String, Integer> statistics);

    public void removeSentence(Sentence sentence){
        var tmp = text;

        text = tmp.substring(0, sentence.getOff() - 1) + tmp.substring(sentence.getOff() + 1 + sentence.getLen());
    }

    @Override
    public String toString() {
        return text;
    }
}
