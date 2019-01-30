package popularmoviestage1.mustafa.example.com.popularmoviestage1.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import popularmoviestage1.mustafa.example.com.popularmoviestage1.R;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.adapter.FavoriteListAdapter;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.databinding.ActivityFavoriteBinding;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.room.PopularMovieTableModel;
import popularmoviestage1.mustafa.example.com.popularmoviestage1.viewmodel.DatabaseViewModel;

public class FavoriteActivity extends AppCompatActivity {
    private static final String TAG = "FavoriteActivity";
    ActivityFavoriteBinding favoriteBinding;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favoriteBinding = DataBindingUtil.setContentView(this,R.layout.activity_favorite);
        linearLayoutManager = new LinearLayoutManager(FavoriteActivity.this,LinearLayoutManager.VERTICAL,false);
        setUpViewModel();
    }

    public void setUpViewModel(){
        DatabaseViewModel viewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        viewModel.getAllFavorites().observe(this, new Observer<List<PopularMovieTableModel>>() {
            @Override
            public void onChanged(@Nullable List<PopularMovieTableModel> popularMovieTableModels) {
                favoriteBinding.favoriteList.setHasFixedSize(true);
                favoriteBinding.favoriteList.setLayoutManager(linearLayoutManager);
                favoriteBinding.favoriteList.setAdapter(new FavoriteListAdapter(popularMovieTableModels,FavoriteActivity.this));
                Log.d(TAG, "onChanged: Updating List from LiveData");
                Log.d(TAG,"onChange: Popular Size: "+popularMovieTableModels.size());

            }
        });
    }

}
