package popularmoviestage1.mustafa.example.com.popularmoviestage1.vh;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import popularmoviestage1.mustafa.example.com.popularmoviestage1.databinding.ActivityRatingDetailsBinding;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.databinding.RatingListItemBinding;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.model.ReviewModel;

/*
 * Created by Mustafa Khaled on 1/28/2019.
 *
 */ public class RatingViewHolder extends RecyclerView.ViewHolder{
    private static final String TAG = "RatingViewHolder";
     RatingListItemBinding ratingDetailsBinding;
    public RatingViewHolder(View itemView) {
        super(itemView);
        ratingDetailsBinding = DataBindingUtil.bind(itemView);

    }

    public void bind (ReviewModel reviewModel){
        ratingDetailsBinding.setReview(reviewModel);
    }



}
