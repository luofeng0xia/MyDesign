package luofeng.study.com.app1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by weixi on 2016/8/2.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> list;

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView view = new TextView(context);
        return new RecyclerView.ViewHolder(view) {};
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TextView) holder.itemView).setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
