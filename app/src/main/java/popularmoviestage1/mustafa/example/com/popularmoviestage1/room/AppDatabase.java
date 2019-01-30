package popularmoviestage1.mustafa.example.com.popularmoviestage1.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.security.PublicKey;

/*
 * Created by Mustafa Khaled on 1/29/2019.
 *
 */
    @Database(entities = {PopularMovieTableModel.class},version = 2,exportSchema = false)
    public abstract class AppDatabase extends RoomDatabase {

     private static String DB_NAME= "fvoritemovies";
     private static AppDatabase mInstance;

     public static AppDatabase getInstance(Context mContext){
         if(mInstance ==null){
             synchronized (new Object()){

                 mInstance = Room.databaseBuilder(mContext.getApplicationContext(),
                         AppDatabase.class, AppDatabase.DB_NAME
                         ).allowMainThreadQueries().build();
             }

         }
         return mInstance;
     }
     public abstract PopularMoviesDAO accessDatabaseDAO();
}
