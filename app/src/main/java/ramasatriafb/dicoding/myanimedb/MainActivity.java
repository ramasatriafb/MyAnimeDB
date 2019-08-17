package ramasatriafb.dicoding.myanimedb;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ramasatriafb.dicoding.myanimedb.fragment.MovieFragment;
import ramasatriafb.dicoding.myanimedb.fragment.TvFragment;
import ramasatriafb.dicoding.myanimedb.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabs;
    private int[] tabIcons = {
            R.drawable.baseline_movie_filter_white_24dp,
            R.drawable.baseline_tv_white_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.view_pager);
        tabs = findViewById(R.id.tabs);
        setUpViewpager(viewPager);
        tabs.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setUpViewpager(ViewPager viewPager) {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        sectionsPagerAdapter.createFragment(new MovieFragment(), getString(R.string.movie));
        sectionsPagerAdapter.createFragment(new TvFragment(), getString(R.string.tv_show));
        viewPager.setAdapter(sectionsPagerAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupTabIcons() {
        tabs.getTabAt(0).setIcon(tabIcons[0]);
        tabs.getTabAt(1).setIcon(tabIcons[1]);
    }


}