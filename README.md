# BaseAdapter recyclerview 通用适配器

## gradle:
```groovy
dependencies {
    ...
    compile 'sing.baseadapter:library:1.0.1'
}
```
## Maven:
```xml
<dependency>
　　<groupId>sing.baseadapter</groupId>
　　<artifactId>library</artifactId>
　　<version>1.0.1</version>
　　<type>pom</type>
</dependency>
```

使用：
在`activity`中还是正常使用：

```JAVA
MyAdapter adapter = new MyAdapter(this,list);
recyclerView.setAdapter(adapter);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
```

在`adapter`中继承`sing.BaseAdapter`：

```JAVA
public class MyAdapter extends BaseAdapter<String> {

    public MyAdapter(Context context, List<String> list) {
        super(context, list, R.layout.item_left);
    }

    @Override
    protected void bindData(BaseViewHolder holder, String data, int position) {
        holder.setImageResource(R.id.iv,R.mipmap.ic_launcher)
              .setText(R.id.tv,"我是左边" + position)
              .setOnChildClickListener(R.id.iv, new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Toast.makeText(context, "图片" + position, Toast.LENGTH_SHORT).show();
                  }
              });
    }
}
```

如果需要不同类型的布局，在`adapter`中实现`BaseAdapter.MultiTypeSupport<T>`即可：

```JAVA
public class MyAdapter extends BaseAdapter<String> implements BaseAdapter.MultiTypeSupport<String> {

    public MyAdapter(Context context, List<String> list) {
        super(context, list, R.layout.item_left);
        this.multiTypeSupport = this;// 注意
    }

    @Override
    public int getLayoutId(String item, int position) {// 这个方法判断类型
        if (position % 2 ==0) {
            return R.layout.item_left;
        }
        return R.layout.item_right;
    }

    @Override
    protected void bindData(BaseViewHolder holder, String data, int position) {
        if (position % 2 ==0) {// 分别设置不同的布局数据
            holder.setImageResource(R.id.iv,R.mipmap.ic_launcher)
                    .setText(R.id.tv,"我是左边" + position);
        }else{
            holder.setImageResource(R.id.iv,R.mipmap.ic_launcher)
                    .setText(R.id.tv,"我是右边" + position);
        }
    }
}
```
