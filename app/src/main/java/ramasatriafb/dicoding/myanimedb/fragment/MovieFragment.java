package ramasatriafb.dicoding.myanimedb.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ramasatriafb.dicoding.myanimedb.R;
import ramasatriafb.dicoding.myanimedb.adapter.MovieAdapter;
import ramasatriafb.dicoding.myanimedb.api.ApiClient;
import ramasatriafb.dicoding.myanimedb.api.ApiInterface;
import ramasatriafb.dicoding.myanimedb.data.Movie;
import ramasatriafb.dicoding.myanimedb.data.ResultMovie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    List<ResultMovie> resultMovieList;
    ProgressBar progressBar;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        progressBar = view.findViewById(R.id.progress_bar);
        resultMovieList = new ArrayList<>();
        movieAdapter = new MovieAdapter(getActivity());

        recyclerView = view.findViewById(R.id.rv_movie);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if (savedInstanceState != null) {
            ArrayList<ResultMovie> list;
            list = savedInstanceState.getParcelableArrayList("movie_anime");
            movieAdapter.setMovieResult(list);
            recyclerView.setAdapter(movieAdapter);
        } else {
            movieLoad();
        }

        return view;

    }

    private void movieLoad() {
        showProgressBar();
        ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<Movie> call = api.getMovieAnime();
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {

                resultMovieList = response.body().getResults();
                Log.d("tes", String.valueOf(resultMovieList));
                movieAdapter.setMovieResult(resultMovieList);
                recyclerView.setAdapter(movieAdapter);
                hideProgressBar();


            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d("Error", t.getMessage());
                Toast.makeText(getActivity(), getString(R.string.ErrorLoading)
                        , Toast.LENGTH_SHORT).show();
                hideProgressBar();
            }
        });
    }


    void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(), getString(R.string.Loading)
                , Toast.LENGTH_SHORT).show();

    }

    void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("now_movie", new ArrayList<>(movieAdapter.getList()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            ArrayList<ResultMovie> list;
            list = savedInstanceState.getParcelableArrayList("now_movie");
            movieAdapter.setMovieResult(list);
            recyclerView.setAdapter(movieAdapter);
        }
    }
}
