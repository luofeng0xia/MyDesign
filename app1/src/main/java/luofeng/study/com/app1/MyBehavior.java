package luofeng.study.com.app1;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by weixi on 2016/8/2.
 */
public class MyBehavior extends FloatingActionButton.Behavior {

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if (dy>0){
            child.hide();
        }else {
            child.show();
        }
    }
}
