/** PROVIDED - do not change
 * One review of one product: the product's name and the text of the review.
 */
public class ProductReview {
    private String name;
    private String review;

    /** Constructs a ProductReview and initializes both instance variables. */
    public ProductReview(String pName, String pReview) {
        name = pName;
        review = pReview;
    }

    /** @return the name of the product */
    public String getName() {
        return name;
    }

    /** @return the text of the review */
    public String getReview() {
        return review;
    }
}
