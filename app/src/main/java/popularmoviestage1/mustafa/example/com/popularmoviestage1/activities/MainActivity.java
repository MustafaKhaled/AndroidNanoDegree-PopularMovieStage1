         package popularmoviestage1.mustafa.example.com.popularmoviestage1.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.R;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.adapter.MovieStageAdapter;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.databinding.ActivityMainBinding;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.helper.Constants;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.helper.MyApplication;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.interfaces.ItemClickListner;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.model.Movie;
import static popularmoviestage1.mustafa.example.com.popularmoviestage1.helper.Constants.POSTER_BASE_URL;
import static popularmoviestage1.mustafa.example.com.popularmoviestage1.helper.Constants.READ_TIMEOUT;
    public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ArrayList<Movie> movieArrayList = new ArrayList<>();
    @BindView(R.id.movies_rv)
    RecyclerView movies_rv;
    GridLayoutManager layoutManager;
    MovieStageAdapter movieStageAdapter;
    ActivityMainBinding mainBinding;
    Movie movie;
    String type;
    boolean orientationChange=true;
    AlertDialog.Builder alertBuilder;
    AlertDialog alertDialog;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        movie = new Movie();
        mainBinding.setMovie(movie);
        ButterKnife.bind(this);
        movie.setLoading(true);
        if(savedInstanceState==null){
            new RequestMovieNetworkCall().execute(Constants.BASE_URL+Constants.MOST_POPULAR_ENDPOINT);
            type="Most";
        }
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putString("type",type);


        }

        @Override
        protected void onRestoreInstanceState(Bundle savedInstanceState) {
            super.onRestoreInstanceState(savedInstanceState);
            Log.d(TAG, "onRestoreInstanceState: Restore data");
            if(savedInstanceState!=null){
                if(savedInstanceState.getString("type").equalsIgnoreCase("Most")){
                    Log.d(TAG, "onRestoreInstanceState: Most Popular Emdpoint");
                    new RequestMovieNetworkCall().execute(Constants.BASE_URL+Constants.MOST_POPULAR_ENDPOINT);
                    type="Most";

                }
                else if(savedInstanceState.getString("type").equalsIgnoreCase("High")){
                    Log.d(TAG, "onRestoreInstanceState: Top Rated Endpoint");
                    new RequestMovieNetworkCall().execute(Constants.BASE_URL+Constants.TOP_RATED_ENDPOINT);
                    type="High";
                }
            }
//            Toast.makeText(MainActivity.this,savedInstanceState.getString("type")+" ",Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getItemId() == R.id.sort_menu){
                showDialog();
            }
            else if(item.getItemId() == R.id.favorite_menu){
                startActivity(new Intent(MainActivity.this, FavoriteActivity.class));
            }
            return true;
        }

        void showDialog(){
                if(alertBuilder == null){


                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.sort_movies_layout,null);
                alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setView(view);
                alertDialog = alertBuilder.create();
                alertDialog.show();
                RadioGroup radioGroup = view.findViewById(R.id.sort_group);
                RadioButton most = view.findViewById(R.id.most_pop);
                RadioButton high = view.findViewById(R.id.highest_rated);
                if(!type.equalsIgnoreCase(null) && type.equalsIgnoreCase("Most")){
                    radioGroup.check(R.id.most_pop);
                }
                else if(!type.equalsIgnoreCase(null) && type.equalsIgnoreCase("High")){
                    radioGroup.check(R.id.highest_rated);

                }
                most.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG, "onCheckedChanged: Most Recent checked");
                        if(alertDialog.isShowing())
                            alertDialog.dismiss();

                        movieArrayList.clear();
                        movie.setLoading(true);
                        new RequestMovieNetworkCall().execute(Constants.BASE_URL+Constants.MOST_POPULAR_ENDPOINT);
                        movieStageAdapter.notifyDataSetChanged();
                        Log.d(TAG, "onCreate: Orientation Value: "+ orientationChange);
                        type="Most";

                    }
                });

                high.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG, "onCheckedChanged: Highest rate checked");
                        if(alertDialog.isShowing())
                            alertDialog.dismiss();
                        movie.setLoading(true);
                        movieArrayList.clear();
                        new RequestMovieNetworkCall().execute(Constants.BASE_URL+Constants.TOP_RATED_ENDPOINT);
                        movieStageAdapter.notifyDataSetChanged();
                        Log.d(TAG, "onCreate: Orientation Value: "+ orientationChange);
                        type="High";

                    }
                });
                }
                else{

                    alertDialog.show();
                }

        }


        public  class RequestMovieNetworkCall extends AsyncTask<String,Void,String> implements ItemClickListner {
        private static final String TAG = "RequestMovieNetworkCall";
        String inputString;
        String result;

            public RequestMovieNetworkCall() {
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
                movie.setLoading(false);

            try {
                JSONObject jsonresult = new JSONObject(s);
                JSONArray results = jsonresult.getJSONArray("results");
                Log.d(TAG, "onPostExecute: Results Array: "+ results.toString());
                for (int i=0;i<results.length();i++){
                    String title = results.getJSONObject(i).get("original_title").toString();
                    String poster_url = POSTER_BASE_URL.concat(results.getJSONObject(i) .get("poster_path").toString());
                    String overView = results.getJSONObject(i).get("overview").toString();
                    String user_rating = results.getJSONObject(i).get("vote_average").toString();
                    String relase_date = results.getJSONObject(i).get("release_date").toString();
                    String id = results.getJSONObject(i).get("id").toString();
                    movieArrayList.add(new Movie(title,poster_url,overView,user_rating,relase_date,id));

                }

                Log.d(TAG, "onPostExecute: Movies Count "+ movieArrayList.size());

            } catch (JSONException e) {
                e.printStackTrace();
            }

            movieStageAdapter = new MovieStageAdapter(MyApplication.mContext,movieArrayList,this);
            layoutManager = new GridLayoutManager(MainActivity.this,2,GridLayoutManager.VERTICAL,false);
            movies_rv.setHasFixedSize(true);
            movies_rv.setLayoutManager(layoutManager);
            movies_rv.setAdapter(movieStageAdapter);

        }
        }
            @Override
            public void itemClicked(int position) {
                Log.d(TAG, "itemClicked: Item Clicked");
                Intent intent = new Intent(MainActivity.this,MovieDetails.class);
                intent.putExtra("details",movieArrayList.get(position));
                startActivity(intent);
            }

    }

    }

