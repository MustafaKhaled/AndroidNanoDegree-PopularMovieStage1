package popularmoviestage1.mustafa.example.com.popularmoviestage1.model;

/*
 * Created by Mustafa Khaled on 1/28/2019.
 *
 */ public class ReviewModel {
     String author;
     String review_value;

    public ReviewModel(String author, String review_value) {
        this.author = author;
        this.review_value = review_value;
    }

    public String getAuthor() {
        return author;
    }

    public String getReview_value() {
        return review_value;
    }
}
