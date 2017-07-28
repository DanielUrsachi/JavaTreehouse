import com.teamtreehouse.KaraokeMachine;
import com.teamtreehouse.module.Song;
import com.teamtreehouse.module.SongBook;

import javax.sound.midi.Soundbank;

public class Karaoke { //clasa principala
    public static void main(String[] argh){
//        Song song = new Song(
//                "Michael Jackson",
//                "Beat It",
//                "https://www.youtube.com/watch?v=oRdxUFDoQe0");
//        SongBook songBook = new SongBook();
//        System.out.println("Adding " + song + " song");
//        songBook.addSong(song);
//        System.out.println("There are " + songBook.getSongCount() + " songs");

        SongBook songBook = new SongBook();
        songBook.importFrom("songs.txt");
        KaraokeMachine machine = new KaraokeMachine(songBook);
        machine.run();
        System.out.println("Saving book . . . ");
        songBook.exportTo("songs.txt");

    }



}
