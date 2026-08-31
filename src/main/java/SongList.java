import java.util.ArrayList;

/** READ FIRST
 * A music website keeps track of downloaded music. For each song the site
 * stores a SongInfo object (provided) holding the song's title and the number
 * of times it has been downloaded. SongList keeps those objects in an
 * ArrayList<SongInfo> named downloadList. Titles are unique: no two SongInfo
 * objects in downloadList have the same title.
 *
 * You implement two methods: getSongInfo and updateDownloads.
 *
 * Adapted from the 2013 AP Computer Science A free-response question
 * "MusicDownloads" (Question 1).
 *
 * ---- getSongInfo ----
 * Returns a reference to the SongInfo object whose title equals the parameter,
 * or null if no song in downloadList has that title. downloadList is not
 * changed.
 *
 * The variable mySongs is a SongList whose downloadList holds:
 * Index:           0               1               2
 * SongInfo:    "Little Man"    "Yam Yam"     "Tree Hugger"
 *                  5               2               4
 *
 * mySongs.getSongInfo("Yam Yam") returns a reference to the object at
 *     index 1 (the second object in the list).
 * mySongs.getSongInfo("Vampire") returns null because no SongInfo in the
 *     list has that title.
 *
 * ---- updateDownloads ----
 * Takes an ArrayList<String> of song titles. For EACH title in that list, in
 * order: if a SongInfo with that title already exists in downloadList, its
 * download count goes up by 1; otherwise a new SongInfo with that title (and
 * therefore a download count of 1) is added to the END of downloadList. The
 * existing entries never move.
 *
 * Start with the same mySongs as above and
 *   songTitles = {"Hey Jude", "Daria", "Yam Yam", "Russian", "Tree Hugger", "Hey Jude"}
 *
 * After mySongs.updateDownloads(songTitles), downloadList holds:
 *     0               1               2               3              4               5
 * "Little Man"    "Yam Yam"     "Tree Hugger"     "Hey Jude"      "Daria"        "Russian"
 *     5               3               5               2              1               1
 *
 * "Hey Jude" appeared twice: the first time it was added with count 1, the
 * second time that same object was incremented to 2.
 *
 * You MUST call getSongInfo from updateDownloads - do not rewrite the search.
 */
public class SongList {
    private ArrayList<SongInfo> downloadList;

    /** PROVIDED - do not change */
    public SongList() {
        downloadList = new ArrayList<SongInfo>();
    }

    /** PROVIDED - do not change
     * Used by the tests to start from a prepared list.
     */
    public SongList(ArrayList<SongInfo> newList) {
        downloadList = newList;
    }

    /** PROVIDED - do not change
     * Used by the tests to inspect the list.
     */
    public ArrayList<SongInfo> getDownloadList() {
        return downloadList;
    }

    /** COMPLETE THIS METHOD
     * Precondition: no two songs in downloadList have the same title.
     * Returns a reference to the SongInfo in downloadList whose title equals
     * title, or null if there is none. Postcondition: downloadList unchanged.
     * Example: with the list above, getSongInfo("Yam Yam") returns the object
     *          at index 1; getSongInfo("Vampire") returns null.
     * Hint: for-each over downloadList, compare titles with .equals, return
     *       the match as soon as you find it; return null AFTER the loop.
     */
    public SongInfo getSongInfo(String title) {
        // Insert your code below

        return null;
    }

    /** COMPLETE THIS METHOD
     * Updates downloadList using every title in titles, in order.
     * Postconditions:
     * - there are no duplicate titles in downloadList
     * - no entries were removed from downloadList
     * - every title in titles is represented in downloadList
     * - each existing entry's count grew by the number of times its title
     *   appeared in titles
     * - the order of the existing entries is unchanged
     * - a title that was not already present is added to the END of the list
     *   the first time it is seen; later repeats increment that new entry
     * - new entries appear in the order they were first seen
     * Example: see the table in the READ FIRST comment.
     * Hint: for each title, call getSongInfo(title). If it returns null,
     *       downloadList.add(new SongInfo(title)); otherwise call
     *       incrementTimesDownloaded() on what it returned.
     */
    public void updateDownloads(ArrayList<String> titles) {
        // Insert your code below

    }
}
