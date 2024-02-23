import java.util.List;

public class CD extends Item{

    List<String> songsTrack;

    public CD(String title, List<String> authors, List<String> songsTrack) {
        super(title, authors);
        this.songsTrack = songsTrack;
    }

    public List<String> getSongsTrack() {
        return songsTrack;
    }

    @Override
    public void displayAttributes() {
        System.out.println("Here are the details of the VD.");
        System.out.println("CD ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Authors: " + getAuthors());
        System.out.println("Songs: " + getSongsTrack());
    }
}
