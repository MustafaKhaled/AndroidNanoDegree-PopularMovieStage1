package popularmoviestage1.mustafa.example.com.popularmoviestage1.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import popularmoviestage1.mustafa.example.com.popularmoviestage1.BR;

/*
 * Created by Mustafa Khaled on 12/19/2018.
 *
 */ public class Movie extends BaseObservable implements Parcelable {
     String title;
     String imgUrl;
     String plot_synopsis;
     String user_rating;
     String release_date;
     boolean isLoading;
     String id;

     public Movie() {
     }

     public Movie(String title, String imgUrl, String plot_synopsis, String user_rating, String release_date, String id) {
          this.title = title;
          this.imgUrl = imgUrl;
          this.plot_synopsis = plot_synopsis;
          this.user_rating=user_rating;
          this.release_date = release_date;
          this.id=id;
     }

     protected Movie(Parcel in) {
          title = in.readString();
          imgUrl = in.readString();
          plot_synopsis = in.readString();
          user_rating = in.readString();
          release_date = in.readString();
          id=in.readString();
     }

     public static final Creator<Movie> CREATOR = new Creator<Movie>() {
          @Override
          public Movie createFromParcel(Parcel in) {
               return new Movie(in);
          }

          @Override
          public Movie[] newArray(int size) {
               return new Movie[size];
          }
     };

     public void setLoading(boolean loading) {
          isLoading = loading;
          notifyPropertyChanged(BR.loading);
     }

     @Bindable
     public boolean isLoading() {
          return isLoading;
     }

     public void setTitle(String title) {
          this.title = title;
     }

     public void setImgUrl(String imgUrl) {
          this.imgUrl = imgUrl;
     }

     public void setPlot_synopsis(String plot_synopsis) {
          this.plot_synopsis = plot_synopsis;
     }

     public void setUser_rating(String user_rating) {
          this.user_rating = user_rating;
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

     public String getImgUrl() {
          return imgUrl;
     }

     public String getPlot_synopsis() {
          return plot_synopsis;
     }

     public String getUser_rating() {
          return user_rating;
     }

     public String getRelease_date() {
          return release_date;
     }

     @Override
     public int describeContents() {
          return 0;
     }

     @Override
     public void writeToParcel(Parcel parcel, int i) {
          parcel.writeString(title);
          parcel.writeString(imgUrl);
          parcel.writeString(plot_synopsis);
          parcel.writeString(user_rating);
          parcel.writeString(release_date);
          parcel.writeString(id);
     }
}
