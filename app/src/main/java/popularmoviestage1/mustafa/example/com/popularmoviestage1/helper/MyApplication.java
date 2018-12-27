package popularmoviestage1.mustafa.example.com.popularmoviestage1.helper;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

/*
 * Created by Mustafa Khaled on 12/20/2018.
 *
 */ public class MyApplication extends Application {

     public static Context mContext;
     public static Activity curActivity=null;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
    public static Activity getCurActivity(){
        return curActivity;
    }


}
