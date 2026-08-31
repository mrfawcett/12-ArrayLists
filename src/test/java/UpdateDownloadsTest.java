import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class UpdateDownloadsTest {

    /** version 1: Gila Monster(1), Breezeblocks(1), Walcott(3), Vampire(2)
     *  version 2: Breezeblocks(1), Vampire(2), Pocket Crocodile(1)
     *  version 3: Gila Monster(1), Walcott(3), Yam Yam(2)                */
    private ArrayList<SongInfo> createSongList(int version) {
        SongInfo song1 = new SongInfo("Gila Monster");
        SongInfo song2 = new SongInfo("Breezeblocks");
        SongInfo song3 = new SongInfo("Walcott");
        song3.incrementTimesDownloaded();
        song3.incrementTimesDownloaded();
        SongInfo song4 = new SongInfo("Vampire");
        song4.incrementTimesDownloaded();
        SongInfo song5 = new SongInfo("Yam Yam");
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

    private ArrayList<String> titles(String a, String b, String c) {
        ArrayList<String> t = new ArrayList<String>();
        t.add(a);
        t.add(b);
        t.add(c);
        return t;
    }

    @DisplayName("updateDownloads: new title twice + existing title once -> Vampire 3, Gila Monster added with 2")
    @Test
    void updateDownloads_Test01() {
        SongList sl = new SongList(createSongList(2));
        sl.updateDownloads(titles("Gila Monster", "Vampire", "Gila Monster"));
        ArrayList<SongInfo> list = sl.getDownloadList();
        assertEquals(3, list.get(1).getNumOfDownloads(), "Vampire had 2, appears once in titles -> 3");
        assertEquals("Gila Monster", list.get(3).getTitle(), "Gila Monster should be added at the END (index 3)");
        assertEquals(2, list.get(3).getNumOfDownloads(), "Gila Monster appeared twice: added with 1, then incremented to 2");
    }

    @DisplayName("updateDownloads: existing title repeated twice -> count grows by 2 (1 -> 3)")
    @Test
    void updateDownloads_Test02() {
        SongList sl = new SongList(createSongList(1));
        ArrayList<String> t = new ArrayList<String>();
        t.add("Gila Monster");
        t.add("Gila Monster");
        sl.updateDownloads(t);
        assertEquals(3, sl.getDownloadList().get(0).getNumOfDownloads(), "Gila Monster: 1 + 2 = 3");
        assertEquals(4, sl.getDownloadList().size(), "no new entry should be added for an existing title");
    }

    @DisplayName("updateDownloads: two new titles then an existing one -> new ones at indexes 3 and 4 with 1 each, Yam Yam 3")
    @Test
    void updateDownloads_Test03() {
        SongList sl = new SongList(createSongList(3));
        sl.updateDownloads(titles("Tree Hugger", "Buttercup", "Yam Yam"));
        ArrayList<SongInfo> list = sl.getDownloadList();
        assertEquals(1, list.get(3).getNumOfDownloads(), "Tree Hugger is new -> count 1");
        assertEquals(1, list.get(4).getNumOfDownloads(), "Buttercup is new -> count 1");
        assertEquals(3, list.get(2).getNumOfDownloads(), "Yam Yam had 2 -> 3");
        assertEquals("Tree Hugger", list.get(3).getTitle(), "new titles are appended in the order first seen");
        assertEquals("Buttercup", list.get(4).getTitle(), "new titles are appended in the order first seen");
    }

    @DisplayName("updateDownloads: a brand-new title repeated twice -> ONE new entry with count 2 (not two entries)")
    @Test
    void updateDownloads_Test04() {
        SongList sl = new SongList(createSongList(2));
        ArrayList<String> t = new ArrayList<String>();
        t.add("Gila Monster");
        t.add("Gila Monster");
        sl.updateDownloads(t);
        ArrayList<SongInfo> list = sl.getDownloadList();
        assertEquals(4, list.size(), "exactly one entry should be added for a repeated new title");
        assertEquals(2, list.get(3).getNumOfDownloads(), "the new entry should have been incremented to 2");
    }

    @DisplayName("updateDownloads: two new titles, each twice, interleaved -> both appended with count 2")
    @Test
    void updateDownloads_Test05() {
        SongList sl = new SongList(createSongList(2));
        ArrayList<String> t = new ArrayList<String>();
        t.add("Yam Yam");
        t.add("Pretty Girls");
        t.add("Yam Yam");
        t.add("Pretty Girls");
        sl.updateDownloads(t);
        ArrayList<SongInfo> list = sl.getDownloadList();
        assertEquals(5, list.size(), "two new titles -> two new entries");
        assertEquals(2, list.get(3).getNumOfDownloads(), "Yam Yam appeared twice -> 2");
        assertEquals(2, list.get(4).getNumOfDownloads(), "Pretty Girls appeared twice -> 2");
    }

    @DisplayName("updateDownloads: the READ FIRST example - Little Man 5, Yam Yam 3, Tree Hugger 5, Hey Jude 2, Daria 1, Russian 1")
    @Test
    void updateDownloads_Test06() {
        ArrayList<SongInfo> list = new ArrayList<SongInfo>();
        SongInfo littleMan = new SongInfo("Little Man");
        for (int i = 0; i < 4; i++) littleMan.incrementTimesDownloaded();   // 5
        SongInfo yamYam = new SongInfo("Yam Yam");
        yamYam.incrementTimesDownloaded();                                   // 2
        SongInfo treeHugger = new SongInfo("Tree Hugger");
        for (int i = 0; i < 3; i++) treeHugger.incrementTimesDownloaded();  // 4
        list.add(littleMan);
        list.add(yamYam);
        list.add(treeHugger);
        SongList sl = new SongList(list);

        ArrayList<String> t = new ArrayList<String>();
        t.add("Hey Jude");
        t.add("Daria");
        t.add("Yam Yam");
        t.add("Russian");
        t.add("Tree Hugger");
        t.add("Hey Jude");
        sl.updateDownloads(t);

        String[] expectedTitles = {"Little Man", "Yam Yam", "Tree Hugger", "Hey Jude", "Daria", "Russian"};
        int[] expectedCounts = {5, 3, 5, 2, 1, 1};
        assertEquals(6, list.size(), "3 existing + 3 new titles = 6 entries");
        for (int i = 0; i < 6; i++) {
            assertEquals(expectedTitles[i], list.get(i).getTitle(), "wrong title at index " + i);
            assertEquals(expectedCounts[i], list.get(i).getNumOfDownloads(), "wrong count for " + expectedTitles[i]);
        }
    }

    @DisplayName("updateDownloads with an empty titles list -> nothing changes")
    @Test
    void updateDownloads_Test07() {
        SongList sl = new SongList(createSongList(1));
        sl.updateDownloads(new ArrayList<String>());
        ArrayList<SongInfo> list = sl.getDownloadList();
        assertEquals(4, list.size(), "size unchanged");
        assertEquals(1, list.get(0).getNumOfDownloads(), "Gila Monster still 1");
        assertEquals(3, list.get(2).getNumOfDownloads(), "Walcott still 3");
    }

    @DisplayName("updateDownloads on an EMPTY downloadList -> every title becomes a new entry (A 2, B 1)")
    @Test
    void updateDownloads_Test08() {
        SongList sl = new SongList();
        sl.updateDownloads(titles("A", "B", "A"));
        ArrayList<SongInfo> list = sl.getDownloadList();
        assertEquals(2, list.size(), "two distinct titles -> two entries");
        assertEquals("A", list.get(0).getTitle(), "A was seen first");
        assertEquals(2, list.get(0).getNumOfDownloads(), "A appeared twice");
        assertEquals("B", list.get(1).getTitle(), "B was seen second");
        assertEquals(1, list.get(1).getNumOfDownloads(), "B appeared once");
    }

    @DisplayName("updateDownloads keeps the existing SongInfo objects in place (increment them, do not replace them)")
    @Test
    void updateDownloads_Test09() {
        ArrayList<SongInfo> list = createSongList(1);
        SongInfo walcottBefore = list.get(2);
        SongList sl = new SongList(list);
        sl.updateDownloads(titles("Walcott", "Walcott", "New Song"));
        assertSame(walcottBefore, sl.getDownloadList().get(2), "the Walcott object must be the same one, just incremented");
        assertEquals(5, walcottBefore.getNumOfDownloads(), "Walcott: 3 + 2 = 5");
    }
}
