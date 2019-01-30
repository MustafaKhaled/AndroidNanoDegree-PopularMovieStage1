package popularmoviestage1.mustafa.example.com.popularmoviestage1.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/*
 * Created by Mustafa Khaled on 1/29/2019.
 *
 */
    @Entity(tableName = "popular_movie")
    public class PopularMovieTableModel {

        @NonNull
        @PrimaryKey
        private String id;
        private String title;
        private  String poster_url;
        private String rating;
        private String release_date;

        public PopularMovieTableModel(String id, String title, String poster_url,String rating, String release_date) {
            this.id = id;
            this.title = title;
            this.poster_url = poster_url;
            this.rating = rating;
            this.release_date = release_date;
    }




    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPoster_url() {
            return poster_url;
        }

        public void setPoster_url(String poster_url) {
            this.poster_url = poster_url;
        }
}
