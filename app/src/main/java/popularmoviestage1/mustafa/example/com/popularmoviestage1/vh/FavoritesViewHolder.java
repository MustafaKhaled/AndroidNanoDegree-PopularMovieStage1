package popularmoviestage1.mustafa.example.com.popularmoviestage1.vh;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import popularmoviestage1.mustafa.example.com.popularmoviestage1.databinding.FavoriteListItemBinding;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.room.PopularMovieTableModel;

/*
 * Created by Mustafa Khaled on 1/29/2019.
 *
 */
    public class FavoritesViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "FavoritesViewHolder";
    FavoriteListItemBinding favoriteListItemBinding;
        public FavoritesViewHolder(View itemView) {
            super(itemView);
            favoriteListItemBinding= DataBindingUtil.bind(itemView);
        }

        public void bind(PopularMovieTableModel popularMovieTableModel){
            favoriteListItemBinding.setFavoriteItem(popularMovieTableModel);
        }



}
