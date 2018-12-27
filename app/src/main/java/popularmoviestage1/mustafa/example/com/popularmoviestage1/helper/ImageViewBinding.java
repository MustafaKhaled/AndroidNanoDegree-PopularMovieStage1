package popularmoviestage1.mustafa.example.com.popularmoviestage1.helper;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/*
 * Created by Mustafa Khaled on 12/20/2018.
 *
 */ public class ImageViewBinding  {

     @BindingAdapter({"imageUrl"})
     public static void loadImage(ImageView imageView, String url){
         Picasso.with(imageView.getContext()).load(url).into(imageView);
     }
}
