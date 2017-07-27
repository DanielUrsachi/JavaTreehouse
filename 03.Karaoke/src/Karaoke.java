import com.teamtreehouse.module.Song;
import com.teamtreehouse.module.SongBook;

public class Karaoke { //clasa principala
    public static void main(String[] argh){
        Song song = new Song(
                "Michael Jackson",
                "Beat It",
                "https://www.youtube.com/watch?v=oRdxUFDoQe0");
        SongBook songBook = new SongBook();
        System.out.println("Adding " + song + " song");
        songBook.addSong(song);
        System.out.println("There are " + songBook.getSongCount() + " songs");

    }



}
