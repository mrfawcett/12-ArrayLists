import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class GetSongInfoTest {

    /** version 1: Gila Monster(1), Breezeblocks(1), Walcott(3), Vampire(2)
     *  version 2: Breezeblocks(1), Vampire(2), Pocket Crocodile(1)
     *  version 3: Gila Monster(1), Walcott(3), Iamayam(2)               */
    private ArrayList<SongInfo> createSongList(int version) {
        SongInfo song1 = new SongInfo("Gila Monster");
        SongInfo song2 = new SongInfo("Breezeblocks");
        SongInfo song3 = new SongInfo("Walcott");
        song3.incrementTimesDownloaded();
        song3.incrementTimesDownloaded();
        SongInfo song4 = new SongInfo("Vampire");
        song4.incrementTimesDownloaded();
        SongInfo song5 = new SongInfo("Iamayam");
        song5.incrementTimesDownloaded();
        SongInfo song6 = new SongInfo("Pocket Crocodile");

        ArrayList<SongInfo> list = new ArrayList<SongInfo>();
        if (version == 1) {
            list.add(song1);
            list.add(song2);
            list.add(song3);
            list.add(song4);
        } else if (version == 2) {
            list.add(song2);
            list.add(song4);
            list.add(song6);
        } else {
            list.add(song1);
            list.add(song3);
            list.add(song5);
        }
        return list;
    }

    @DisplayName("getSongInfo(\"Gila Monster\"): first song in the list -> that SongInfo, 1 download")
    @Test
    void getSongInfo_Test01() {
        SongList sl = new SongList(createSongList(1));
        SongInfo song = sl.getSongInfo("Gila Monster");
        assertNotNull(song, "Gila Monster is in the list - should not return null");
        assertEquals("Gila Monster", song.getTitle(), "wrong song returned");
        assertEquals(1, song.getNumOfDownloads(), "Gila Monster has 1 download");
    }

    @DisplayName("getSongInfo(\"Vampire\"): middle of the list -> that SongInfo, 2 downloads")
    @Test
    void getSongInfo_Test02() {
        SongList sl = new SongList(createSongList(2));
        SongInfo song = sl.getSongInfo("Vampire");
        assertNotNull(song, "Vampire is in the list");
        assertEquals("Vampire", song.getTitle(), "wrong song returned");
        assertEquals(2, song.getNumOfDownloads(), "Vampire has 2 downloads");
    }

    @DisplayName("getSongInfo(\"Iamayam\") on a list that does not contain it -> null")
    @Test
    void getSongInfo_Test03() {
        SongList sl = new SongList(createSongList(1));
        assertNull(sl.getSongInfo("Iamayam"), "title not in the list -> return null, not some other song");
    }

    @DisplayName("getSongInfo(\"Iamayam\"): last song in the list -> that SongInfo, 2 downloads")
    @Test
    void getSongInfo_Test04() {
        SongList sl = new SongList(createSongList(3));
        SongInfo song = sl.getSongInfo("Iamayam");
        assertNotNull(song, "Iamayam is the last song - make sure the loop reaches the end");
        assertEquals("Iamayam", song.getTitle(), "wrong song returned");
        assertEquals(2, song.getNumOfDownloads(), "Iamayam has 2 downloads");
    }

    @DisplayName("getSongInfo(\"Breezeblocks\") -> that SongInfo, 1 download")
    @Test
    void getSongInfo_Test05() {
        SongList sl = new SongList(createSongList(2));
        SongInfo song = sl.getSongInfo("Breezeblocks");
        assertNotNull(song, "Breezeblocks is in the list");
        assertEquals("Breezeblocks", song.getTitle(), "wrong song returned");
        assertEquals(1, song.getNumOfDownloads(), "Breezeblocks has 1 download");
    }

    @DisplayName("getSongInfo(\"Pocket Crocodile\") on a list without it -> null")
    @Test
    void getSongInfo_Test06() {
        SongList sl = new SongList(createSongList(3));
        assertNull(sl.getSongInfo("Pocket Crocodile"), "title not in the list -> null");
    }

    @DisplayName("getSongInfo returns the SAME object that is in the list, not a copy")
    @Test
    void getSongInfo_Test07() {
        ArrayList<SongInfo> list = createSongList(1);
        SongList sl = new SongList(list);
        assertSame(list.get(2), sl.getSongInfo("Walcott"), "return the reference stored in downloadList");
    }

    @DisplayName("getSongInfo does not change the list (size and order intact)")
    @Test
    void getSongInfo_Test08() {
        SongList sl = new SongList(createSongList(1));
        sl.getSongInfo("Walcott");
        sl.getSongInfo("Nothing Here");
        ArrayList<SongInfo> list = sl.getDownloadList();
        assertEquals(4, list.size(), "the list should still have 4 songs");
        assertEquals("Gila Monster", list.get(0).getTitle(), "index 0 unchanged");
        assertEquals("Vampire", list.get(3).getTitle(), "index 3 unchanged");
    }

    @DisplayName("getSongInfo on an empty list -> null")
    @Test
    void getSongInfo_Test09() {
        SongList sl = new SongList();
        assertNull(sl.getSongInfo("Anything"), "empty list -> null");
    }

    @DisplayName("getSongInfo is case-sensitive: \"walcott\" does not match \"Walcott\" -> null")
    @Test
    void getSongInfo_Test10() {
        SongList sl = new SongList(createSongList(1));
        assertNull(sl.getSongInfo("walcott"), "titles must match exactly");
    }
}
