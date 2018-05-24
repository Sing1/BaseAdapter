package sing;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import sing.commonadapter.R;

public class MyAdapter extends BaseAdapter<String> implements BaseAdapter.MultiTypeSupport<String> {

    public MyAdapter(Context context, List<String> list) {
        super(context, list, R.layout.item_left);
        this.multiTypeSupport = this;
    }

    @Override
    public int getLayoutId(String item, int position) {
        if (position % 2 ==0) {
            return R.layout.item_left;
        }
        return R.layout.item_right;
    }

    @Override
    protected void bindData(BaseViewHolder holder, String data, int position) {
        if (position % 2 ==0) {
            holder.setImageResource(R.id.iv,R.mipmap.ic_launcher)
                    .setText(R.id.tv,"我是左边" + position);
        }else{
            holder.setImageResource(R.id.iv,R.mipmap.ic_launcher)
                    .setText(R.id.tv,"我是右边" + position);
        }
    }
}