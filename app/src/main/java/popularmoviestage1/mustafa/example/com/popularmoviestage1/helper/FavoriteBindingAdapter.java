package popularmoviestage1.mustafa.example.com.popularmoviestage1.helper;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import popularmoviestage1.mustafa.example.com.popularmoviestage1.room.AppDatabase;

/*
 * Created by Mustafa Khaled on 1/30/2019.
 *
 */ public class FavoriteBindingAdapter {
    private static final String TAG = "FavoritePosterBindingAd";
    static AppDatabase mDB;

    public FavoriteBindingAdapter() {
        mDB = AppDatabase.getInstance(MyApplication.mContext);
    }

    @BindingAdapter({"android:imagesrc"})
     public static void setImageViewSrc(ImageView imageViewSrc , String url){
         try {
             Log.d(TAG, "setImageViewSrc: Poster URL is "+url);
             URL image_url = new URL(url);
             Bitmap bitmap = BitmapFactory.decodeStream(image_url.openStream());
             imageViewSrc.setImageBitmap(bitmap);
         } catch (MalformedURLException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }

     }
    @BindingAdapter({"android:onClickLis"})
    public static void onClick(Button b, final  String id){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Item Clicked is: "+ id);
//                mDB.accessDatabaseDAO().delete(id);

                Log.d(TAG,"Item has been deleted");
            }
        });
    }


}
