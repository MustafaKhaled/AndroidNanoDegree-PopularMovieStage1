package popularmoviestage1.mustafa.example.com.popularmoviestage1.activities;

import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import popularmoviestage1.mustafa.example.com.popularmoviestage1.R;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.adapter.TrailersAdapter;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.databinding.MovieDetailsBinding;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.helper.Constants;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.interfaces.ItemClickListner;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.model.Movie;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.model.MovieTrailers;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.room.AppDatabase;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.room.PopularMovieTableModel;

import static popularmoviestage1.mustafa.example.com.popularmoviestage1.helper.Constants.READ_TIMEOUT;
import static popularmoviestage1.mustafa.example.com.popularmoviestage1.helper.Util.UriBuilder;

public class MovieDetails extends AppCompatActivity {
    private static final String TAG = "MovieTrailers";
    Movie movie;
    MovieDetailsBinding movieDetailsBinding;
    RecyclerView trailers_rv;
    AppDatabase appDatabase;
    ArrayList<MovieTrailers> trailers = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        movie = getIntent().getParcelableExtra("details");
        movieDetailsBinding = DataBindingUtil.setContentView(this,R.layout.movie_details);
        movieDetailsBinding.setDetails(movie);
        if(checkAddedToFavorite()){
        }
        else {
        }
        movieDetailsBinding.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkAddedToFavorite()){
                    movieDetailsBinding.favorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_border_black_24dp));
                    appDatabase.accessDatabaseDAO().delete(new PopularMovieTableModel(movie.getId(),movie.getTitle(),movie.getImgUrl(),movie.getUser_rating(),movie.getRelease_date()));
                    Log.d(TAG, "onClick: removed from database");
                }
                else {
                    movieDetailsBinding.favorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_red_24dp));
                    appDatabase.accessDatabaseDAO().insertMovie(new PopularMovieTableModel(movie.getId(),movie.getTitle(),movie.getImgUrl(),movie.getUser_rating(),movie.getRelease_date()));
                    Log.d(TAG, "onClick: Added to database");
                }

            }
        });
        movieDetailsBinding.ratingValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetails.this,RatingDetails.class);
                intent.putExtra("id",movie.getId());
                startActivity(intent);
            }
        });
        new RequestTrailersList().execute(UriBuilder(movie.getId(),"videos"));


        Log.d(TAG, "onCreate: Movie Details: "+ movie.getImgUrl() +" "+ "Movie Title: "+movie.getTitle());
    }

    private boolean checkAddedToFavorite(){
        if(appDatabase.accessDatabaseDAO().selectedMovie(movie.getId())!=null){
            Log.d(TAG, "checkAddedToFavorite: Was added to favorite");
            movieDetailsBinding.favorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_red_24dp));
            return true;
        }
        else {
            Log.d(TAG, "checkAddedToFavorite: Not added to favorite");
            movieDetailsBinding.favorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_border_black_24dp));

            return false;
        }

    }

    public  class RequestTrailersList extends AsyncTask<String,Void,String> implements ItemClickListner {
        private static final String TAG = "RequestMovieNetworkCall";
        String inputString;
        String result;

        public RequestTrailersList() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String[] params) {
            try {
                //Create a URL object holding our url

                URL myUrl = new URL(params[0]);
                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(Constants.GET_REQUEST);
                connection.setReadTimeout(READ_TIMEOUT);

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputString = reader.readLine()) != null){
                    stringBuilder.append(inputString);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
                Log.d(TAG, "doInBackground: the result is: "+ result);


            }
            catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s!=null){
//                movie.setLoading(false);

                try {
                    JSONObject jsonresult = new JSONObject(s);
                    JSONArray results = jsonresult.getJSONArray("results");
                    Log.d(TAG, "onPostExecute: Results Array: "+ results.toString());
                    for (int i=0;i<results.length();i++){
                        String key = results.getJSONObject(i).get("key").toString();
                        trailers.add(new MovieTrailers("Trailer "+(i+1), key));
                         }

                    Log.d(TAG, "onPostExecute: Movies Trailers "+ trailers.size());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                TrailersAdapter adapter = new TrailersAdapter(MovieDetails.this,trailers,this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MovieDetails.this,LinearLayoutManager.VERTICAL,false);
                movieDetailsBinding.trailersList.setHasFixedSize(true);
                movieDetailsBinding.trailersList.setLayoutManager(layoutManager);
                movieDetailsBinding.trailersList.setAdapter(adapter);

//                movieStageAdapter = new MovieStageAdapter(MyApplication.mContext,movieArrayList,this);
//                layoutManager = new GridLayoutManager(MainActivity.this,2,GridLayoutManager.VERTICAL,false);
//                movies_rv.setHasFixedSize(true);
//                movies_rv.setLayoutManager(layoutManager);
//                movies_rv.setAdapter(movieStageAdapter);

            }
        }
        @Override
        public void itemClicked(int position) {
            Log.d(TAG, "itemClicked: item #"+position + "Pressed");
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(Constants.TRAILER_BASE_URL+trailers.get(position).getTrailer_key()));
            startActivity(intent);

        }

    }


}
