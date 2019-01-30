package popularmoviestage1.mustafa.example.com.popularmoviestage1.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import popularmoviestage1.mustafa.example.com.popularmoviestage1.R;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.activities.RatingDetails;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.databinding.RatingListItemBinding;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.databinding.ShowTrailerBinding;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.model.ReviewModel;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.vh.RatingViewHolder;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.vh.TrailersViewHolder;

/*
 * Created by Mustafa Khaled on 1/29/2019.
 *
 */ public class RatingAdapter extends RecyclerView.Adapter<RatingViewHolder> {
    Context mContext;
    ArrayList<ReviewModel> modelArrayList;
    public RatingAdapter(Context mContext, ArrayList<ReviewModel> marrayList) {
        this.mContext = mContext;
        this.modelArrayList = marrayList;
    }

    @NonNull
    @Override
    public RatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        RatingListItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.rating_list_item,parent,false);
        return new RatingViewHolder(binding.getRoot());

    }

    @Override
    public void onBindViewHolder(@NonNull RatingViewHolder holder, int position) {
            ReviewModel reviewModel = modelArrayList.get(position);
            holder.bind(reviewModel);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }
}
