package RecycleScrollView;

import android.content.Context;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class RecycleScrollViewLayout extends ViewGroup {
    int itemCount =0 , parentHeight = 0, parentScroll = 0;

    public RecycleScrollViewLayout(Context context) {
        super(context);
    }

    public RecycleScrollViewLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        //int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int wMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        int hMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);

        if (itemCount==0) {
            removeAllViewsInLayout();
        } else {
            if (getChildCount() == 0) {
                addView(((ModifiedRecycleScrollView) getParent()).createContentElement(getContext()));
            }
            getChildAt(0).measure(wMeasureSpec, hMeasureSpec);

            for (int i = 0; i < getChildCount(); i++) {
                getChildAt(i).measure(wMeasureSpec, hMeasureSpec);
            }
        }
        setMeasuredDimension(width, height);
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
