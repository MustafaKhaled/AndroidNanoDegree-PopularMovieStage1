package popularmoviestage1.mustafa.example.com.popularmoviestage1.model;

/*
 * Created by Mustafa Khaled on 1/26/2019.
 *
 */ public class MovieTrailers {
     String counter;
     String trailer_key;

    public MovieTrailers(String counter, String trailer_key) {
        this.counter = counter;
        this.trailer_key = trailer_key;
    }

    public String getTrailer_key() {
        return trailer_key;
    }

    public String getCounter() {
        return counter;
    }
}
