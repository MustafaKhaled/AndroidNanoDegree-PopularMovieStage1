package popularmoviestage1.mustafa.example.com.popularmoviestage1.helper;

import android.net.Uri;
import android.util.Log;

/*
 * Created by Mustafa Khaled on 1/28/2019.
 *
 */ public class Util {
    private static final String TAG = "Util";
    public static String UriBuilder (String movieId,String path){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(Constants.URL_SCHEME);
        builder.authority(Constants.URL_AUTHORITY);
        builder.appendPath("3");
        builder.appendPath("movie");
        builder.appendPath(movieId);
        builder.appendPath(path);
        builder.appendQueryParameter("api_key", Constants.API_KEY);
        Log.d(TAG, "doInBackground: Videos URL "+ builder.build().toString());


        return builder.build().toString();

    }
}
