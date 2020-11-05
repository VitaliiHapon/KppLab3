import java.util.Iterator;
import java.util.Map;

public abstract class Sentence {
    protected Text text;
    protected int off;
    protected int len;
    protected boolean question;

    public class Word{
        private String word;
        private int pos;

        public Word(String word, int pos){
            this.word = word;
            this.pos = pos;
        }

        public int getPos() {
            return pos;
        }

        @Override
        public String toString() {
            return word;
        }
    }

    public abstract Iterator<Word> getWords();

    public abstract void calculateStatistic(Map<String, Integer> statistics);

    public boolean isQuestion(){
        return question;
    }

    public int getLen() {
        return len;
    }

    public int getOff() {
        return off;
    }

    @Override
    public String toString() {
        return text.toString().substring(off, off + len);
    }
}
