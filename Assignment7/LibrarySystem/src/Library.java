import java.util.List;

public class Library {
    public static void main(String[] args) {
        //instantiate some books
        Book book1 = new Book("Stone Blind", List.of("Natalie Haynes"));
        Book book2 = new Book("Nationalism and Gender", List.of("Chizuko Ueno"));
        Book book3 = new Book( "The Stranger in the Mirror", List.of("Constantine", "Liv"));

        //instantiate some DVDs
        DVD dvd1 = new DVD("A Room of One’s Own", List.of("Virginia Woolf"), "Virginia Woolf");
        DVD dvd2 = new DVD("Little Women", List.of("Louisa May Alcott", "Greta Gerwig"), "Greta Gerwig");

        //instantiare some CDs
        CD cd1 = new CD("Songs I wrote in My Bedroom", List.of("Anson Seabra"), List.of("Emerald Eyes", "Stay With Me", "Don't Forget to Breath"));
        CD cd2 = new CD("Felling For My Life", List.of("Anson Seabra"), List.of("Love Me", "Keep Your Head Up Princess"));

        //display the details
        displayItemDetaiks(book1);
        displayItemDetaiks(book2);
        displayItemDetaiks(book3);
        displayItemDetaiks(dvd1);
        displayItemDetaiks(dvd2);
        displayItemDetaiks(cd1);
        displayItemDetaiks(cd2);
    }

    private static void displayItemDetaiks(Item item) {
        item.displayAttributes();
        System.out.println();
    }
}
