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
import ramasatriafb.dicoding.myanimedb.data.ResultTvShow;
import ramasatriafb.dicoding.myanimedb.detail.DetailTvActivity;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvHolder> {

    private List<ResultTvShow> resultTvShowList = new ArrayList<>();
    private Context context;

    public TvAdapter(Context context) {
        this.context = context;
    }

//    public MovieAdapter(List<ResultMovie> results) {
//        this.results = results;
//    }

    @NonNull
    @Override
    public TvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grid_tvshow, parent, false);

        return new TvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvHolder tvHolder, final int position) {
        tvHolder.bindView(resultTvShowList.get(position));
    }

    public void setResultTvShows(List<ResultTvShow> resultTvShows) {
        this.resultTvShowList = resultTvShows;
    }

    public List<ResultTvShow> getList() {
        return resultTvShowList;
    }

    @Override
    public int getItemCount() {
        return resultTvShowList.size();
    }

    class TvHolder extends RecyclerView.ViewHolder {
        ImageView Poster;

        public TvHolder(View itemView) {
            super(itemView);
            Poster = itemView.findViewById(R.id.img_poster_tvshow);
        }

        public void bindView(final ResultTvShow resultTvShow) {
            Picasso.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w185" + resultTvShow.getPosterPath())
                    .into(Poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(itemView.getContext(), DetailTvActivity.class);
                    i.putExtra("tv_show", resultTvShow);
                    itemView.getContext().startActivity(i);
                }
            });
        }
    }
}
