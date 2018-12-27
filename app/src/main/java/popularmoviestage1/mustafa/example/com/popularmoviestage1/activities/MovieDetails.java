package popularmoviestage1.mustafa.example.com.popularmoviestage1.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import popularmoviestage1.mustafa.example.com.popularmoviestage1.R;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.databinding.MovieDetailsBinding;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.model.Movie;

public class MovieDetails extends AppCompatActivity {
    private static final String TAG = "MovieDetails";
    Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movie = getIntent().getParcelableExtra("details");
        MovieDetailsBinding movieDetailsBinding = DataBindingUtil.setContentView(this,R.layout.movie_details);
        movieDetailsBinding.setDetails(movie);

        Log.d(TAG, "onCreate: Movie Details: "+ movie.getImgUrl() +" "+ "Movie Title: "+movie.getTitle());
    }
}
