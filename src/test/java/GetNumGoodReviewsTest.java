import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class GetNumGoodReviewsTest {

    private ReviewCollection createReviewCollection() {
        ArrayList<ProductReview> rl = new ArrayList<ProductReview>();
        rl.add(new ProductReview("HUE Lights", "Best lights out there"));
        rl.add(new ProductReview("Thousand Helmet", "Kept my head safe, best purchase ever"));
        rl.add(new ProductReview("HUE Lights", "Dumbest app ever"));
        rl.add(new ProductReview("Mythos Book", "This book has a horrible bestiary collection"));
        rl.add(new ProductReview("Dyson v12", "This was the best vacuum"));
        rl.add(new ProductReview("Thousand Helmet", "Kept my head safe"));
        rl.add(new ProductReview("Dyson v12", "Great purchase. Beats the rest"));
        rl.add(new ProductReview("Dyson v12", "Has a great asbestos filter"));
        rl.add(new ProductReview("YONEX Racquet", "This is the best"));
        rl.add(new ProductReview("HUE Lights", "These light are the best for a smart house"));
        rl.add(new ProductReview("YONEX Racquet", "BEST racquet but overpriced"));
        rl.add(new ProductReview("YONEX Birdies", "Worth the price"));
        rl.add(new ProductReview("YONEX Racquet", "It's so-so"));

        ArrayList<String> pl = new ArrayList<String>();
        pl.add("HUE Lights");
        pl.add("Thousand Helmet");
        pl.add("SAMSUNG TV");
        pl.add("Dyson v12");
        pl.add("YONEX Racquet");
        pl.add("YONEX Birdies");
        pl.add("Mythos Book");

        return new ReviewCollection(rl, pl);
    }

    @DisplayName("getNumGoodReviews(\"HUE Lights\") -> 2 (\"Best ...\" and \"... the best for ...\" count; \"Dumbest\" does not)")
    @Test
    void getNumGoodReviews_Test01() {
        assertEquals(2, createReviewCollection().getNumGoodReviews("HUE Lights"),
                "Best (capital) and 'the best for' are good; 'Dumbest' is not the word best");
    }

    @DisplayName("getNumGoodReviews(\"Thousand Helmet\") -> 1 (\"safe, best purchase\")")
    @Test
    void getNumGoodReviews_Test02() {
        assertEquals(1, createReviewCollection().getNumGoodReviews("Thousand Helmet"),
                "one of the two helmet reviews contains the word best");
    }

    @DisplayName("getNumGoodReviews(\"Mythos Book\") -> 0 (\"bestiary\" is not the word best)")
    @Test
    void getNumGoodReviews_Test03() {
        assertEquals(0, createReviewCollection().getNumGoodReviews("Mythos Book"),
                "'bestiary' starts with best but is a different word");
    }

    @DisplayName("getNumGoodReviews(\"Dyson v12\") -> 1 (\"asbestos\" and \"Beats\" do not count)")
    @Test
    void getNumGoodReviews_Test04() {
        assertEquals(1, createReviewCollection().getNumGoodReviews("Dyson v12"),
                "only 'This was the best vacuum' is good");
    }

    @DisplayName("getNumGoodReviews(\"YONEX Racquet\") -> 2 (\"the best\" as the LAST word, and \"BEST\" in caps)")
    @Test
    void getNumGoodReviews_Test05() {
        assertEquals(2, createReviewCollection().getNumGoodReviews("YONEX Racquet"),
                "'This is the best' ends with the word; 'BEST racquet' is upper case - both count");
    }

    @DisplayName("getNumGoodReviews(\"YONEX Birdies\") -> 0 (has a review, but not a good one)")
    @Test
    void getNumGoodReviews_Test06() {
        assertEquals(0, createReviewCollection().getNumGoodReviews("YONEX Birdies"),
                "'Worth the price' does not contain best");
    }

    @DisplayName("getNumGoodReviews(\"SAMSUNG TV\") -> 0 (product is listed but has ZERO reviews)")
    @Test
    void getNumGoodReviews_Test07() {
        assertEquals(0, createReviewCollection().getNumGoodReviews("SAMSUNG TV"),
                "a product with no reviews at all has 0 good reviews");
    }

    @DisplayName("getNumGoodReviews(\"Nonexistent\") -> 0 (product not in the collection at all)")
    @Test
    void getNumGoodReviews_Test08() {
        assertEquals(0, createReviewCollection().getNumGoodReviews("Nonexistent"),
                "unknown product -> 0, no exception");
    }

    @DisplayName("getNumGoodReviews on an empty collection -> 0")
    @Test
    void getNumGoodReviews_Test09() {
        assertEquals(0, new ReviewCollection().getNumGoodReviews("Anything"), "no reviews -> 0");
    }

    @DisplayName("getNumGoodReviews: \"bestest\" is not the word best, but a review that is just \"best\" is -> 1")
    @Test
    void getNumGoodReviews_Test10() {
        ReviewCollection rc = new ReviewCollection();
        ArrayList<ProductReview> rl = rc.getReviewList();
        rl.add(new ProductReview("Pen", "the bestest pen"));
        rl.add(new ProductReview("Pen", "best"));
        rl.add(new ProductReview("Pen", "Best pen I own"));
        assertEquals(2, rc.getNumGoodReviews("Pen"),
                "'best' alone and 'Best pen' count; 'bestest' does not - indexOf(\"best\") alone gives 3");
    }

    @DisplayName("getNumGoodReviews only counts reviews of the requested product")
    @Test
    void getNumGoodReviews_Test11() {
        ReviewCollection rc = new ReviewCollection();
        ArrayList<ProductReview> rl = rc.getReviewList();
        rl.add(new ProductReview("Cup", "best cup"));
        rl.add(new ProductReview("Mug", "best mug"));
        rl.add(new ProductReview("Cup", "the best"));
        rl.add(new ProductReview("Mug", "meh"));
        assertEquals(2, rc.getNumGoodReviews("Cup"), "two good Cup reviews");
        assertEquals(1, rc.getNumGoodReviews("Mug"), "one good Mug review");
    }
}
