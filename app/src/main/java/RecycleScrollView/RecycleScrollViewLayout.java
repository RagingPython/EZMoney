package RecycleScrollView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class RecycleScrollViewLayout extends ViewGroup {
    int itemCount =0 , parentHeight = 0, parentScroll = 0, contentInstances = 0;

    public RecycleScrollViewLayout(Context context) {
        super(context);
    }

    public RecycleScrollViewLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    protected void setItemCount(int count) {
        itemCount=count;
        requestLayout();
    }

    protected void setParentScroll(int scroll) {
        parentScroll=scroll;
    }

    protected void setParentHeight(int newHeight) {
        parentHeight=newHeight;
    }

}
