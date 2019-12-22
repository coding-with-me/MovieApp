package com.nopalyer.movieapp.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.nopalyer.movieapp.Activities.MainActivity;
import com.nopalyer.movieapp.Fragments.DetailedFragment;
import com.nopalyer.movieapp.Model.MovieResponse;
import com.nopalyer.movieapp.Model.NowPlaying;
import com.nopalyer.movieapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NowPlayingMovieAdapter extends RecyclerView.Adapter<NowPlayingMovieAdapter.ViewHolder> {

    Context context;
    List<NowPlaying> nowPlayings;

    public NowPlayingMovieAdapter(Context context, List<NowPlaying> nowPlayings) {
        this.context = context;
        this.nowPlayings = nowPlayings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.now_playing_movie_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final NowPlaying playing = nowPlayings.get(position);

        String img_base_url = "https://image.tmdb.org/t/p/w185_and_h278_bestv2/";
        final String imgUrl = img_base_url+playing.getPoster_path();
        Picasso.with(context).load(imgUrl).into(holder.imageView);
        final String rating = playing.getVote_average()+"/10";
        holder.tvRating.setText(rating);
        holder.tvTitle.setText(playing.getTitle());

        holder.btnBuyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailedFragment detailedFragment = new DetailedFragment();
                Bundle bundle = new Bundle();
                bundle.putString("movieTitle",playing.getTitle());
                bundle.putString("movieRating",rating);
                bundle.putString("movieReleaseDate",playing.getRelease_date());
                bundle.putString("movieDesc",playing.getOverview());
                bundle.putString("moviePosterPath",imgUrl);
                detailedFragment.setArguments(bundle);
                ((MainActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer,detailedFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return nowPlayings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvRating,tvTitle;
        ImageView imageView;
        Button btnBuyTicket,bookmark;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRating = itemView.findViewById(R.id.movieRating);
            tvTitle = itemView.findViewById(R.id.movieTitle);
            imageView = itemView.findViewById(R.id.imgPoster);
            bookmark = itemView.findViewById(R.id.btnBookmark);
            btnBuyTicket = itemView.findViewById(R.id.btnBuyTicket);

        }
    }
}
