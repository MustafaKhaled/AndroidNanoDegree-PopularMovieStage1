package popularmoviestage1.mustafa.example.com.popularmoviestage1.vh;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import popularmoviestage1.mustafa.example.com.popularmoviestage1.databinding.ShowTrailerBinding;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.interfaces.ItemClickListner;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.model.MovieTrailers;

/*
 * Created by Mustafa Khaled on 1/26/2019.
 *
 */ public class TrailersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
     ShowTrailerBinding showTrailerBinding;
     ItemClickListner customListener;
    public TrailersViewHolder(View itemView,ItemClickListner customListener) {
        super(itemView);
        showTrailerBinding = DataBindingUtil.bind(itemView);
        this.customListener = customListener;
        itemView.setOnClickListener(this);

    }

    public void bind(MovieTrailers movieTrailers){
        showTrailerBinding.setTrailer(movieTrailers);
        showTrailerBinding.executePendingBindings();
    }

    @Override
    public void onClick(View view) {
        customListener.itemClicked(getAdapterPosition());
    }
}
