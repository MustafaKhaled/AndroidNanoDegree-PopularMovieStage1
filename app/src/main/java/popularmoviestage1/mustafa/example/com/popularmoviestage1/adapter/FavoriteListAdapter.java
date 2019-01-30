package popularmoviestage1.mustafa.example.com.popularmoviestage1.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import popularmoviestage1.mustafa.example.com.popularmoviestage1.R;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.databinding.FavoriteListItemBinding;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.room.AppDatabase;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.room.PopularMovieTableModel;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.vh.FavoritesViewHolder;

/*
 * Created by Mustafa Khaled on 1/30/2019.
 *
 */ public class FavoriteListAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {
     List<PopularMovieTableModel> favorite_list;
     AppDatabase mDB;
     Context mContext;
    FavoriteListItemBinding favoriteBinding;
    public FavoriteListAdapter(List<PopularMovieTableModel> favorite_list,Context mContext) {
        this.favorite_list = favorite_list;
        this.mContext = mContext;
        mDB  =AppDatabase.getInstance(mContext);
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        favoriteBinding = DataBindingUtil.inflate(layoutInflater,R.layout.favorite_list_item,parent,false);
        return new FavoritesViewHolder(favoriteBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, final int position) {
        holder.bind(favorite_list.get(position));
        favoriteBinding.unfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDB.accessDatabaseDAO().delete(favorite_list.get(position));
                favorite_list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,favorite_list.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return favorite_list.size();
    }
}
