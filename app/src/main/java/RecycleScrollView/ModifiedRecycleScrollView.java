package RecycleScrollView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ScrollView;

public abstract class ModifiedRecycleScrollView extends ScrollView {
    RecycleScrollViewLayout contentLayout;

    abstract protected View createContentElement(Context context);
    abstract protected void updateContentElement(View view, int index);
    abstract int getCount();

    public ModifiedRecycleScrollView(Context context) {
        super(context);
        init(context);
    }

    public ModifiedRecycleScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context){
        contentLayout = new RecycleScrollViewLayout(context);
        contentLayout.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0));
        this.addView(contentLayout);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        contentLayout.setParentHeight(h);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        contentLayout.setParentScroll(t);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void update(){
        setScrollY(0);
        contentLayout.setItemCount(getCount());
    }
}
