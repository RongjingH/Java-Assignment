import java.util.List;

public class DVD extends Item{

    private String director;

    public DVD(String title, List<String> authors, String director) {
        super(title, authors);
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public void displayAttributes() {
        System.out.println("Here are the details of the DVD.");
        System.out.println("DVD ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Authors: " + getAuthors());
        System.out.println("Director: " + getDirector());
    }
}
