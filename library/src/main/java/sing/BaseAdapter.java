package sing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * RecyclerView 的通用适配器
 * 继承该适配器即可，如果有多条目的，实现 MultiTypeSupport 并完善方法，注意 list.size 的条数
 * T 数据类型
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected LayoutInflater layoutInflater;

    protected List<T> list;

    protected int layoutId;

    protected MultiTypeSupport<T> multiTypeSupport;

    public BaseAdapter(Context context, List<T> list, int layoutId) {
        this.layoutInflater = LayoutInflater.from(context);
        this.list = list;
        this.layoutId = layoutId;
    }

    @Override
    public int getItemViewType(int position) {
        if (multiTypeSupport != null) {
            return multiTypeSupport.getLayoutId(list.get(position), position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (multiTypeSupport != null) {
            layoutId = viewType;
        }
        View itemView = layoutInflater.inflate(layoutId, parent, false);
        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        bindData(holder, list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    protected abstract void bindData(BaseViewHolder holder, T data, int position);

    public interface MultiTypeSupport<T> {
        int getLayoutId(T item, int position);
    }
}