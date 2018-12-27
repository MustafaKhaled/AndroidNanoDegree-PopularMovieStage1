package popularmoviestage1.mustafa.example.com.popularmoviestage1.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import popularmoviestage1.mustafa.example.com.popularmoviestage1.interfaces.ItemClickListner;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.helper.Constants;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.model.Movie;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.databinding.SingleGridItemBinding;


/*
 * Created by Mustafa Khaled on 12/19/2018.
 *
 */ public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = "MyViewHolder";
     SingleGridItemBinding singleGridItemBinding;
     ImageView imageView;
     ItemClickListner itemClickListner;
    public MyViewHolder(View itemView, ItemClickListner itemClickListner) {
        super(itemView);
        this.singleGridItemBinding = DataBindingUtil.bind(itemView);
        this.itemClickListner=itemClickListner;
        itemView.setOnClickListener(this);

    }
    public void bind(Movie movie){
        this.singleGridItemBinding.setMovie(movie);
        this.singleGridItemBinding.executePendingBindings();
        Log.d(TAG, "bind: Image Url : "+Constants.POSTER_BASE_URL+ movie.getImgUrl() );
//        Picasso.with(imageView.getContext()).load( movie.getImgUrl()).into(imageView);

    }

    @Override
    public void onClick(View view) {
        itemClickListner.itemClicked(getAdapterPosition());
    }
}
