package ramasatriafb.dicoding.myanimedb.api;

import ramasatriafb.dicoding.myanimedb.data.Movie;
import ramasatriafb.dicoding.myanimedb.data.TvShow;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    String DB_API = "9413542f6df14542c3eb2963343a39e5";
    String LINK_MOVIE = "&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=16&with_original_language=ja";
    String LINK_TV_SHOW = "&sort_by=popularity.desc&page=1&timezone=Japan%2FTokyo&with_genres=16&include_null_first_air_dates=false&with_original_language=ja";

    @GET("movie?api_key=" + DB_API + LINK_MOVIE)
    Call<Movie> getMovieAnime();

    @GET("tv?api_key=" + DB_API + LINK_TV_SHOW)
    Call<TvShow> getTvShow();
}
