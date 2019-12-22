package com.nopalyer.movieapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.nopalyer.movieapp.Activities.MainActivity;
import com.nopalyer.movieapp.Fragments.DetailedFragment;
import com.nopalyer.movieapp.Model.NowPlaying;
import com.nopalyer.movieapp.Model.Upcoming;
import com.nopalyer.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UpcomingMovieAdapter extends RecyclerView.Adapter<UpcomingMovieAdapter.ViewHolder> {

    Context context;
    List<Upcoming> upcomings;


    public UpcomingMovieAdapter(Context context, List<Upcoming> upcomings) {
        this.context = context;
        this.upcomings = upcomings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_movie_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Upcoming upcoming = upcomings.get(position);

        String img_base_url = "https://image.tmdb.org/t/p/w185_and_h278_bestv2/";
        final String imgUrl = img_base_url+upcoming.getPoster_path();
        Picasso.with(context).load(imgUrl).into(holder.imageView);
        final String rating = upcoming.getVote_average()+"/10";
        holder.tvRating.setText(rating);
        holder.tvReleaseDate.setText(upcoming.getRelease_date());
        holder.tvTitle.setText(upcoming.getTitle());

        holder.btnBuyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailedFragment detailedFragment = new DetailedFragment();
                Bundle bundle = new Bundle();
                bundle.putString("movieTitle",upcoming.getTitle());
                bundle.putString("movieRating",rating);
                bundle.putString("movieReleaseDate",upcoming.getRelease_date());
                bundle.putString("movieDesc",upcoming.getOverview());
                bundle.putString("moviePosterPath",imgUrl);
                detailedFragment.setArguments(bundle);
                ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,detailedFragment).addToBackStack(null).commit();
            }
        });



    }

    @Override
    public int getItemCount() {
        return upcomings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvRating,tvTitle,tvReleaseDate;
        ImageView imageView;
        Button btnBuyTicket,bookmark;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRating = itemView.findViewById(R.id.movieRating);
            tvTitle = itemView.findViewById(R.id.movieTitle);
            tvReleaseDate = itemView.findViewById(R.id.releaseDate);
            imageView = itemView.findViewById(R.id.imgPoster);
            bookmark = itemView.findViewById(R.id.btnBookmark);
            btnBuyTicket = itemView.findViewById(R.id.btnBuyTicket);

        }
    }



}
