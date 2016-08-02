package luofeng.study.com.app1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SwipeDismissBehavior.OnDismissListener, View.OnClickListener {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text = (TextView) findViewById(R.id.tv);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.format(Locale.CHINA,"第%03d个条目",i));
        }
        recyclerView.setAdapter(new MyAdapter(this,list));
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.fab);
        button.setOnClickListener(this);
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) text.getLayoutParams();
//        layoutParams.setBehavior(new MyBehavior());

//        SwipeDismissBehavior behavior=new SwipeDismissBehavior();
//        behavior.setListener(this);
//        layoutParams.setBehavior(behavior);
    }

    @Override
    public void onDismiss(View view) {
        text.setVisibility(View.GONE);
        Snackbar.make(view,"snackbar",Snackbar.LENGTH_SHORT).setAction("撤销", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setVisibility(View.VISIBLE);
                text.animate().alpha(1);
            }
        }).show();
    }

    @Override
    public void onDragStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        Snackbar.make(view,"snackbar",Snackbar.LENGTH_SHORT).setAction("撤销", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,TextInputActivity.class));
//                Toast.makeText(MainActivity.this,"Toast",Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
}
