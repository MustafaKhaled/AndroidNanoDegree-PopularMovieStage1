package popularmoviestage1.mustafa.example.com.popularmoviestage1.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import popularmoviestage1.mustafa.example.com.popularmoviestage1.room.AppDatabase;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.room.PopularMovieTableModel;

/*
 * Created by Mustafa Khaled on 1/29/2019.
 *
 */ public class DatabaseViewModel extends AndroidViewModel {
    private static final String TAG = "DatabaseViewModel";
    AppDatabase mDB;
    private LiveData<List<PopularMovieTableModel>> favorite_movies;
    public DatabaseViewModel(@NonNull Application application) {
        super(application);

        mDB = AppDatabase.getInstance(this.getApplication());
        Log.d(TAG, "DatabaseViewModel: Fetching all favorites");
        favorite_movies = mDB.accessDatabaseDAO().getAllFavorites();
     }

     public LiveData<List<PopularMovieTableModel>> getAllFavorites(){
        return favorite_movies;
     }
}
