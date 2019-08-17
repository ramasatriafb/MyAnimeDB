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
import ramasatriafb.dicoding.myanimedb.data.ResultTvShow;

public class DetailTvActivity extends AppCompatActivity {
    private ImageView posterTV;
    private ImageView posterTVSmall;
    TextView titleTV;
    TextView firstAirTV;
    TextView ratingTV;
    TextView synopsisTV;


    ResultTvShow result;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvdetail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        posterTV = findViewById(R.id.img_poster_tv);
        posterTVSmall = findViewById(R.id.img_poster_tv_small);
        titleTV = findViewById(R.id.tv_title_tv);
        firstAirTV = findViewById(R.id.tv_First_Air);
        ratingTV = findViewById(R.id.tv_rating_tv);
        synopsisTV = findViewById(R.id.tv_synopsis_tv);

        result = getIntent().getParcelableExtra("tv_show");
        Toast.makeText(this, getString(R.string.Loading)
                , Toast.LENGTH_SHORT).show();

        Picasso.with(DetailTvActivity.this).
                load("https://image.tmdb.org/t/p/w500" + result.getPosterPath())
                .into(posterTV);
        posterTV.setAdjustViewBounds(true);
        posterTV.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Picasso.with(DetailTvActivity.this).
                load("https://image.tmdb.org/t/p/w185" + result.getPosterPath())
                .into(posterTVSmall);

        titleTV.setText(result.getName());
        String firstAirDate = formatDate("yyyy-MM-dd", "dd MMMM yyyy", result.getFirstAirDate());
        firstAirTV.setText(firstAirDate);
        ratingTV.setText(Double.toString(result.getVoteAverage()));
        if (result.getOverview().equalsIgnoreCase("")) {
            synopsisTV.setText(getString(R.string.Overview));

        } else {
            synopsisTV.setText(result.getOverview());
        }

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
