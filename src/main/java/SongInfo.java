/** PROVIDED - do not change
 * One song on the music site: a unique title and how many times it has been
 * downloaded. A new SongInfo starts with 1 download.
 */
public class SongInfo {
    private String title;
    private int numOfDl;

    /** Creates a new SongInfo with the given unique title and a download
     *  count of 1.
     *  @param title the unique title of the downloaded song
     */
    public SongInfo(String title) {
        this.title = title;
        numOfDl = 1;
    }

    /** @return the title */
    public String getTitle() {
        return title;
    }

    /** @return number of times downloaded */
    public int getNumOfDownloads() {
        return numOfDl;
    }

    /** Adds 1 to the number of times downloaded. */
    public void incrementTimesDownloaded() {
        numOfDl++;
    }
}
