package ramasatriafb.dicoding.myanimedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ramasatriafb.dicoding.myanimedb.R;
import ramasatriafb.dicoding.myanimedb.data.ResultMovie;
import ramasatriafb.dicoding.myanimedb.detail.DetailActivity;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private List<ResultMovie> resultMovieList = new ArrayList<>();
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
    }

//    public MovieAdapter(List<ResultMovie> results) {
//        this.results = results;
//    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grid_movie, parent, false);

        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieHolder movieHolder, final int position) {
        movieHolder.bindView(resultMovieList.get(position));
    }

    public void setMovieResult(List<ResultMovie> resultMovies) {
        this.resultMovieList = resultMovies;
    }

    public List<ResultMovie> getList() {
        return resultMovieList;
    }

    @Override
    public int getItemCount() {
        return resultMovieList.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        ImageView Poster;

        public MovieHolder(View itemView) {
            super(itemView);
            Poster = itemView.findViewById(R.id.img_poster_movie);
        }

        public void bindView(final ResultMovie resultMovie) {
            Picasso.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w185" + resultMovie.getPosterPath())
                    .into(Poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(itemView.getContext(), DetailActivity.class);
                    i.putExtra("movie", resultMovie);
                    itemView.getContext().startActivity(i);
                }
            });
        }
    }
}
