package popularmoviestage1.mustafa.example.com.popularmoviestage1.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import popularmoviestage1.mustafa.example.com.popularmoviestage1.interfaces.ItemClickListner;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.model.Movie;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.R;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.databinding.SingleGridItemBinding;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.vh.MyViewHolder;

/*
 * Created by Mustafa Khaled on 12/19/2018.
 *
 */ public class MovieStageAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context mContext;
    ArrayList<Movie> movieArrayList;
    ItemClickListner itemClickListner;
    public MovieStageAdapter(Context context, ArrayList<Movie> movieArrayList,ItemClickListner itemClickListner) {
        this.mContext=context;
        this.movieArrayList=movieArrayList;
        this.itemClickListner=itemClickListner;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        SingleGridItemBinding binding = DataBindingUtil.inflate(layoutInflater,R.layout.single_grid_item,parent,false);
        return new MyViewHolder(binding.getRoot(),itemClickListner);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Movie movie = movieArrayList.get(position);
        holder.bind(movie);

    }


    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }
}
