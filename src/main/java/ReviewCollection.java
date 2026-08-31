import java.util.ArrayList;

/** READ FIRST
 * You plan to analyze product reviews from a website by looking for keywords
 * in posted reviews. The ProductReview class (provided) represents a single
 * review: a product name and the text of the review. ReviewCollection holds
 * many reviews plus the list of distinct product names that have been
 * reviewed:
 *   reviewList  - ArrayList<ProductReview>, every review, in the order added
 *   productList - ArrayList<String>, each product name exactly once, in the
 *                 order the product was FIRST reviewed
 *
 * Adapted from the "ReviewCollector" free-response question on the College
 * Board's 2020 AP Computer Science A Practice Exam.
 *
 * ---- addReview ----
 * Adds one ProductReview to the collection:
 * - the ProductReview object is added to the end of reviewList;
 * - the product's name is added to the end of productList ONLY if that name
 *   is not already in productList.
 *
 * Example: adding reviews for "HUE Lights", "Thousand Helmet", "HUE Lights",
 * "SAMSUNG TV" in that order gives
 *   reviewList  = the four reviews, in that order
 *   productList = {"HUE Lights", "Thousand Helmet", "SAMSUNG TV"}
 *
 * ---- getNumGoodReviews ----
 * Returns how many reviews of the product named prodName are GOOD. A review
 * is good if it contains the WORD "best". If the product has no reviews (or
 * is not in the collection at all) the method returns 0.
 *
 * The check is not case-sensitive: "best", "Best" and "BEST" all count. But it
 * must be the whole word: "Dumbest", "asbestos" and "bestiary" contain the
 * letters b-e-s-t and do NOT count. You may assume reviews contain no
 * punctuation glued to the word (a comma or period next to "best" never
 * happens in the tests), and "best" may be the first or last word.
 *
 * TECHNIQUE: lower-case the review, then wrap it in spaces and look for
 * " best " (with a space on each side):
 *     String padded = " " + review.toLowerCase() + " ";
 *     if (padded.indexOf(" best ") >= 0) ...
 * The extra spaces make the first and last words match too. indexOf("best")
 * alone is NOT enough - it counts "Dumbest".
 *
 * Examples with these reviews for "HUE Lights":
 *   "Best lights out there"                    -> good  (first word, capital B)
 *   "Dumbest app ever"                         -> not good
 *   "These light are the best for a smart house" -> good
 * getNumGoodReviews("HUE Lights") returns 2.
 */
public class ReviewCollection {
    private ArrayList<ProductReview> reviewList;
    private ArrayList<String> productList;

    /** PROVIDED - do not change
     * Constructs an empty ReviewCollection.
     */
    public ReviewCollection() {
        reviewList = new ArrayList<ProductReview>();
        productList = new ArrayList<String>();
    }

    /** PROVIDED - do not change
     * Used by the tests to start from prepared lists.
     */
    public ReviewCollection(ArrayList<ProductReview> revList, ArrayList<String> prodList) {
        reviewList = revList;
        productList = prodList;
    }

    /** PROVIDED - do not change
     * Used by the tests to inspect the reviews.
     */
    public ArrayList<ProductReview> getReviewList() {
        return reviewList;
    }

    /** PROVIDED - do not change
     * Used by the tests to inspect the product names.
     */
    public ArrayList<String> getProductList() {
        return productList;
    }

    /** COMPLETE THIS METHOD
     * Adds prodReview to the end of reviewList, and adds its product name to
     * the end of productList if that name is not already there.
     * Example: after adding reviews for "A", "B", "A": reviewList has 3
     *          entries, productList is {"A", "B"}.
     * Hint: loop over productList with a boolean "found" flag, comparing
     *       with .equals (never == on Strings). Add the name after the loop
     *       only if found is still false.
     */
    public void addReview(ProductReview prodReview) {
        // Insert your code below

    }

    /** COMPLETE THIS METHOD
     * Returns the number of reviews in reviewList whose product name equals
     * prodName AND whose text contains the whole word "best" in any
     * capitalization. Returns 0 if the product has no such reviews.
     * Example: with the HUE Lights reviews above, getNumGoodReviews("HUE Lights")
     *          returns 2; a product with no reviews returns 0.
     * Hint: see TECHNIQUE in the READ FIRST comment. Check the product name
     *       first, then the review text.
     */
    public int getNumGoodReviews(String prodName) {
        // Insert your code below

        return 0;
    }
}
