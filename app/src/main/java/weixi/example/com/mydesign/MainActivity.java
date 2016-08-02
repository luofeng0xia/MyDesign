package weixi.example.com.mydesign;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        TabLayout tab = (TabLayout) findViewById(R.id.tab);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawer,toolbar,0,0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView nav = (NavigationView) findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(this);
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(String.format(Locale.CHINA,"第%02d页",i));
        }
        MyAdapter adapter=new MyAdapter(getSupportFragmentManager(),list);
        pager.setAdapter(adapter);
        tab.setupWithViewPager(pager);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_4:
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
