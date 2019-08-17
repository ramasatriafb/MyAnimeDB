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
import ramasatriafb.dicoding.myanimedb.adapter.TvAdapter;
import ramasatriafb.dicoding.myanimedb.api.ApiClient;
import ramasatriafb.dicoding.myanimedb.api.ApiInterface;
import ramasatriafb.dicoding.myanimedb.data.ResultTvShow;
import ramasatriafb.dicoding.myanimedb.data.TvShow;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {
    RecyclerView recyclerView;
    TvAdapter tvAdapter;
    List<ResultTvShow> resultTvShows;
    ProgressBar progressBar;

    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tvshow, container, false);
        progressBar = view.findViewById(R.id.progress_bar);
        resultTvShows = new ArrayList<>();
        tvAdapter = new TvAdapter(getActivity());

        recyclerView = view.findViewById(R.id.rv_tv);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if (savedInstanceState != null) {
            ArrayList<ResultTvShow> list;
            list = savedInstanceState.getParcelableArrayList("tv_show");
            tvAdapter.setResultTvShows(list);
            recyclerView.setAdapter(tvAdapter);
        } else {
            tvLoad();
        }

        return view;

    }

    private void tvLoad() {
        showProgressBar();
        ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<TvShow> call = api.getTvShow();
        call.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {

                resultTvShows = response.body().getResults();
                Log.d("tes", String.valueOf(resultTvShows));
                tvAdapter.setResultTvShows(resultTvShows);
                recyclerView.setAdapter(tvAdapter);
                hideProgressBar();


            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
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
        outState.putParcelableArrayList("tv_show", new ArrayList<>(tvAdapter.getList()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            ArrayList<ResultTvShow> list;
            list = savedInstanceState.getParcelableArrayList("tv_show");
            tvAdapter.setResultTvShows(list);
            recyclerView.setAdapter(tvAdapter);
        }
    }
}

