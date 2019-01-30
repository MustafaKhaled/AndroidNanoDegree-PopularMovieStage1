package popularmoviestage1.mustafa.example.com.popularmoviestage1.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
import popularmoviestage1.mustafa.example.com.popularmoviestage1.adapter.RatingAdapter;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.databinding.ActivityRatingDetailsBinding;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.helper.Constants;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.helper.Util;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.interfaces.ItemClickListner;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.model.ReviewModel;

import static popularmoviestage1.mustafa.example.com.popularmoviestage1.helper.Constants.READ_TIMEOUT;

/*
 * Created by Mustafa Khaled on 1/28/2019.
 *
 */
    public class RatingDetails extends AppCompatActivity {
        ActivityRatingDetailsBinding activityRatingDetailsBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String id = getIntent().getStringExtra("id");
        activityRatingDetailsBinding = DataBindingUtil.setContentView(this,R.layout.activity_rating_details);
        new RequestReviewsList().execute(Util.UriBuilder(id,"reviews"));
    }
    public  class RequestReviewsList extends AsyncTask<String,Void,String> {
        private static final String TAG = "RequestMovieNetworkCall";
        String inputString;
        String result;
        ArrayList<ReviewModel> reviewsArrayList= new ArrayList<>();
        public RequestReviewsList() {

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
                        String author = results.getJSONObject(i).get("author").toString();
                        String value= results.getJSONObject(i).get("content").toString();

                        reviewsArrayList.add(new ReviewModel(author, value));
                    }

//                    Log.d(TAG, "onPostExecute: Movies Trailers "+ trailers.size());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RatingAdapter adapter = new RatingAdapter(RatingDetails.this,reviewsArrayList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RatingDetails.this,LinearLayoutManager.VERTICAL,false);
                activityRatingDetailsBinding.ratingList.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.HORIZONTAL));
                activityRatingDetailsBinding.ratingList.setHasFixedSize(true);
                activityRatingDetailsBinding.ratingList.setLayoutManager(layoutManager);
                activityRatingDetailsBinding.ratingList.setAdapter(adapter);

//                ratingAdapter = new RatingAdapter(RatingDetails.this,reviewsArrayList);
//                layoutManager = new LinearLayoutManager(RatingDetails.this, LinearLayout.VERTICAL,false);
//                movies_rv.setHasFixedSize(true);
//                movies_rv.setLayoutManager(layoutManager);
//                movies_rv.setAdapter(movieStageAdapter);

            }
        }


    }
}
