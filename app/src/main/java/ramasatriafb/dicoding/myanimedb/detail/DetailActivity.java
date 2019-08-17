package ramasatriafb.dicoding.myanimedb.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ramasatriafb.dicoding.myanimedb.R;
import ramasatriafb.dicoding.myanimedb.data.ResultMovie;

public class DetailActivity extends AppCompatActivity {
    private ImageView posterMovie;
    private ImageView posterMovieSmall;
    TextView titleMovie;
    TextView releaseDateMovie;
    TextView ratingMovie;
    TextView synopsisMovie;


    ResultMovie result;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        posterMovie = findViewById(R.id.img_poster_movie);
        posterMovieSmall = findViewById(R.id.img_poster_movie_small);
        titleMovie = findViewById(R.id.tv_title_movie);
        releaseDateMovie = findViewById(R.id.tv_releaseDate_movie);
        ratingMovie = findViewById(R.id.tv_rating_movie);
        synopsisMovie = findViewById(R.id.tv_synopsis_movie);

        result = getIntent().getParcelableExtra("movie");
        Toast.makeText(this, getString(R.string.Loading)
                , Toast.LENGTH_SHORT).show();

        Picasso.with(DetailActivity.this).
                load("https://image.tmdb.org/t/p/w500" + result.getPosterPath())
                .into(posterMovie);
        posterMovie.setAdjustViewBounds(true);
        posterMovie.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Picasso.with(DetailActivity.this).
                load("https://image.tmdb.org/t/p/w185" + result.getPosterPath())
                .into(posterMovieSmall);

        titleMovie.setText(result.getTitle());
        String releaseDate = formatDate("yyyy-MM-dd", "dd MMMM yyyy", result.getReleaseDate());
        releaseDateMovie.setText(releaseDate);
        ratingMovie.setText(Double.toString(result.getVoteAverage()));
        synopsisMovie.setText(result.getOverview());


    }

    public static String formatDate(String fromFormat, String toFormat, String dateToFormat) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat inFormat = new SimpleDateFormat(fromFormat);
        Date date = null;
        try {
            date = inFormat.parse(dateToFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat(toFormat);

        return outFormat.format(date);
    }
}
