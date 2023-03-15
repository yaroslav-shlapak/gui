package lesson02.taks02;

/*
Define an abstract class Singer which represents singers. Each singer has a name
and a number (for example, in a talent competition), which is assigned automatically
when an object of the class is being cretaed — you can use a static field incremented
in the constructor. The class should have a constructor taking (only) the singer’s
name (as a String) and the following methods:
• abstract: abstract String sing(), which returns the text that is sung by the
singer in the competition;
• public String toString() returning the information about the singer;
• static: ...loudest(...) which takes as the argument an array of objects/singers
and returns the one whose text of the sung song contains the greatest
number of capital letters.
In the main function of the testing class Main:
1. create several (minimum 3) objects/singers using anonymous classes which
extend Singer. Implementation sets the text of a song which a singer sings
in the competition;
2. create an array of singers which consists of objects from the item 1;
3. test the function loudest of class Singer.
The following function main in class Main:

should print
(1) Martin: Arrivederci, Roma...
(2) Joplin: ...for me and my Bobby MacGee
(3) Houston: I will always love youuuu
(2) Joplin: ...for me and my Bobby MacGee
Important: The code of the class Main should be changed only in places marked by comments.
 */

public class Main {
    public static void main(String[] args) {
        Singer s1 = new Singer("Martin") {

            @Override
            public String sing() {
                return "Arrivederci, Roma...";
            }
        };

        Singer s2 = new Singer("Joplin") {
            @Override
            public String sing() {
                return "...for me and my Bobby MacGee";
            }
        };

        Singer s3 = new Singer("Houston") {
            @Override
            public String sing() {
                return "I will always love youuuu";
            }
        };

        Singer sng[] = {s1, s2, s3};
        for (Singer s : sng) System.out.println(s);
        System.out.println("\n" + Singer.loudest(sng));
    }
}