package lesson02.taks02;

public abstract class Singer {
    private static int counter = 0;
    private final String name;
    private final int id;
    public Singer(String name) {
        this.name = name;
        this.id = ++counter;
    }

    public abstract String sing();

    /*
    • static: ...loudest(...) which takes as the argument an array of objects/singers
    and returns the one whose text of the sung song contains the greatest
    number of capital letters.
     */
    public static Singer loudest(Singer[] arr) {
        Singer loudest = null;
        int max = 0;
        for (Singer singer : arr) {
            String song = singer.sing();
            int capitalLettersCounter = 0;
            for (int i = 0; i < song.length(); i++) {
                if (song.charAt(i) <= 'Z' && song.charAt(i) >= 'A') {
                    capitalLettersCounter++;
                }
            }
            if (capitalLettersCounter > max) {
                max = capitalLettersCounter;
                loudest = singer;
            }
        }
        return loudest;
    }

    @Override
    public String toString() {
        return "(" + id + ")" + name + ": " + sing();
    }


}
