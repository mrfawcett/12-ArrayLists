import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AddReviewTest {

    /** HUE Lights, Thousand Helmet, HUE Lights, SAMSUNG TV */
    private ReviewCollection fourReviews() {
        ReviewCollection rc = new ReviewCollection();
        rc.addReview(new ProductReview("HUE Lights", "Best lights out there"));
        rc.addReview(new ProductReview("Thousand Helmet", "Kept my head safe"));
        rc.addReview(new ProductReview("HUE Lights", "Worst app ever"));
        rc.addReview(new ProductReview("SAMSUNG TV", "Overpriced pos"));
        return rc;
    }

    /** YONEX Racquet, YONEX Racquet, YONEX Birdies, YONEX Racquet */
    private ReviewCollection yonexReviews() {
        ReviewCollection rc = new ReviewCollection();
        rc.addReview(new ProductReview("YONEX Racquet", "This is the best racquet"));
        rc.addReview(new ProductReview("YONEX Racquet", "Great racquet but overpriced"));
        rc.addReview(new ProductReview("YONEX Birdies", "Worth the price"));
        rc.addReview(new ProductReview("YONEX Racquet", "It's so-so"));
        return rc;
    }

    @DisplayName("addReview: productList has each product ONCE, in first-seen order (HUE Lights, Thousand Helmet, SAMSUNG TV)")
    @Test
    void addReview_Test01() {
        ReviewCollection rc = fourReviews();
        assertEquals(3, rc.getProductList().size(), "4 reviews of 3 distinct products -> 3 names");
        assertEquals("HUE Lights", rc.getProductList().get(0), "first product seen");
        assertEquals("Thousand Helmet", rc.getProductList().get(1), "second product seen");
        assertEquals("SAMSUNG TV", rc.getProductList().get(2), "third product seen - the repeat of HUE Lights must not be added");
    }

    @DisplayName("addReview: reviewList keeps EVERY review in order, repeats included")
    @Test
    void addReview_Test02() {
        ReviewCollection rc = fourReviews();
        assertEquals(4, rc.getReviewList().size(), "all 4 reviews are kept");
        assertEquals("Kept my head safe", rc.getReviewList().get(1).getReview(), "review at index 1");
        assertEquals("Worst app ever", rc.getReviewList().get(2).getReview(), "review at index 2 (second HUE Lights review)");
        assertEquals("Overpriced pos", rc.getReviewList().get(3).getReview(), "review at index 3");
    }

    @DisplayName("addReview: three reviews of the same racquet -> productList is {YONEX Racquet, YONEX Birdies}")
    @Test
    void addReview_Test03() {
        ReviewCollection rc = yonexReviews();
        assertEquals(2, rc.getProductList().size(), "2 distinct products");
        assertEquals("YONEX Racquet", rc.getProductList().get(0), "first product seen");
        assertEquals("YONEX Birdies", rc.getProductList().get(1), "second product seen");
    }

    @DisplayName("addReview: reviewList order for the YONEX reviews")
    @Test
    void addReview_Test04() {
        ReviewCollection rc = yonexReviews();
        assertEquals("This is the best racquet", rc.getReviewList().get(0).getReview(), "index 0");
        assertEquals("Great racquet but overpriced", rc.getReviewList().get(1).getReview(), "index 1");
        assertEquals("Worth the price", rc.getReviewList().get(2).getReview(), "index 2");
        assertEquals("It's so-so", rc.getReviewList().get(3).getReview(), "index 3");
    }

    @DisplayName("addReview: first review into an empty collection -> both lists have exactly one entry")
    @Test
    void addReview_Test05() {
        ReviewCollection rc = new ReviewCollection();
        ProductReview pr = new ProductReview("Kettle", "Boils water, best kettle");
        rc.addReview(pr);
        assertEquals(1, rc.getReviewList().size(), "one review");
        assertSame(pr, rc.getReviewList().get(0), "the same ProductReview object is stored");
        assertEquals(1, rc.getProductList().size(), "one product name");
        assertEquals("Kettle", rc.getProductList().get(0), "the product name");
    }

    @DisplayName("addReview: product names are case-sensitive - \"hue lights\" is a different product from \"HUE Lights\"")
    @Test
    void addReview_Test06() {
        ReviewCollection rc = new ReviewCollection();
        rc.addReview(new ProductReview("HUE Lights", "fine"));
        rc.addReview(new ProductReview("hue lights", "also fine"));
        assertEquals(2, rc.getProductList().size(), "exact match only: two distinct names");
    }
}
