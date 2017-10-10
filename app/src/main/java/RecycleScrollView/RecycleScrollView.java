package RecycleScrollView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;

public class RecycleScrollView extends ScrollView implements OnUpdateContentListener {
    int count=0;
    int visibleInFrameCount =0;
    int childHeight=0;
    RecycleScrollViewContent content=null;
    private LinearLayout recycleScrollViewLayout;
    private Space topSpacing;


    public RecycleScrollView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        recycleScrollViewLayout =new LinearLayout(context);
        recycleScrollViewLayout.setOrientation(LinearLayout.VERTICAL);
        recycleScrollViewLayout.setLayoutParams(new LinearLayout.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));


        topSpacing = new Space(context);
        topSpacing.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));

        this.addView(recycleScrollViewLayout);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        populateLayout();
    }

    public void setContent(RecycleScrollViewContent recycleScrollViewContent) {
        content = recycleScrollViewContent;
        content.setOnUpdateContentListener(this);
        count=0;
        visibleInFrameCount =0;
        childHeight=0;
        recycleScrollViewLayout.removeAllViewsInLayout();

        ViewGroup.LayoutParams par = recycleScrollViewLayout.getLayoutParams();
        par.height=100;
        recycleScrollViewLayout.setLayoutParams(par);

        View v=content.createInstance(getContext());
        v.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        childHeight = v.getMeasuredHeight();

        //v.addOnLayoutChangeListener(new OnLayoutChangeListener() {
        //    @Override
        //    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //    childHeight=v.getHeight();
        //        if(childHeight!=0) {
        //            v.removeOnLayoutChangeListener(this);
        //            populateLayout();
        //        }
        //    }
        //});
        //recycleScrollViewLayout.addView(v);

        //childHeight =57;
        populateLayout();
    }

    public void onUpdateContent() {
        count=content.getCount();
        ViewGroup.LayoutParams par = recycleScrollViewLayout.getLayoutParams();
        par.height=childHeight*count;
        recycleScrollViewLayout.setLayoutParams(par);
        setScrollY(0);
        updateLayout();
    }

    protected void populateLayout(){
        recycleScrollViewLayout.removeAllViewsInLayout();
        visibleInFrameCount =0;
        View v;
        int h = getHeight();
        recycleScrollViewLayout.addView(topSpacing);
        if ((h!=0)&(childHeight!=0)) {
            visibleInFrameCount =(int) (Math.ceil(h/childHeight)+1);
            for (int i = 0; i< visibleInFrameCount; i++) {
                v=content.createInstance(getContext());
                recycleScrollViewLayout.addView(content.createInstance(getContext()));
            }
        }
        updateLayout();
    };

    private void updateLayout(){
        int firstVisibleIndex = 0;
        if (childHeight!=0) {
            firstVisibleIndex = Math.max((int) Math.floor(getScrollY() / childHeight-0.5f), 0);
            firstVisibleIndex = Math.min(firstVisibleIndex, Math.max(count-visibleInFrameCount,0));
            for (int i=firstVisibleIndex;i<Math.min(firstVisibleIndex+visibleInFrameCount,count);i++) {
                content.updateElement(recycleScrollViewLayout.getChildAt(i-firstVisibleIndex),i);
            }
            ViewGroup.LayoutParams par = topSpacing.getLayoutParams();
            par.height= firstVisibleIndex*childHeight;
            topSpacing.setLayoutParams(par);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        updateLayout();
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public String debug(){
        ViewGroup.LayoutParams par = recycleScrollViewLayout.getLayoutParams();
        par.height=100;
        recycleScrollViewLayout.setLayoutParams(par);
        recycleScrollViewLayout.requestLayout();
        return String.valueOf(count)+ " " + String.valueOf(childHeight) + " " + String.valueOf(visibleInFrameCount) + " " + String.valueOf(getHeight()) + " "+ String.valueOf(recycleScrollViewLayout.getHeight());
     };
}
