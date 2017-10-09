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
    private LinearLayout layout;
    private Space topSpacing;


    public RecycleScrollView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        layout=new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));


        topSpacing = new Space(context);
        topSpacing.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));

        this.addView(layout);
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
        layout.removeAllViewsInLayout();

        View v=content.createInstance(getContext());
        v.setVisibility(INVISIBLE);
        v.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                childHeight=v.getHeight();
                if(childHeight!=0) {
                    v.removeOnLayoutChangeListener(this);
                }
                populateLayout();
            }
        });
        layout.addView(v);

        updateLayout();
    }

    public void onUpdateContent() {
        count=content.getCount();
        ViewGroup.LayoutParams par = layout.getLayoutParams();
        par.height=childHeight*count;
        layout.setLayoutParams(par);
        setScrollY(0);
        updateLayout();
    }

    protected void populateLayout(){
        layout.removeAllViewsInLayout();
        visibleInFrameCount =0;
        View v;
        int h = getHeight();
        layout.addView(topSpacing);
        if ((h!=0)&(childHeight!=0)) {
            visibleInFrameCount =(int) (Math.ceil(h/childHeight)+1);
            for (int i = 0; i> visibleInFrameCount; i++) {
                v=content.createInstance(getContext());
                v.setVisibility(INVISIBLE);
                layout.addView(content.createInstance(getContext()));
            }
        }
        updateLayout();
    };

    private void updateLayout(){
        int firstVisibleIndex = 0;
        if (childHeight!=0) {
            firstVisibleIndex = Math.max((int) Math.floor(getScrollY() / childHeight-0.5f), 0);
            firstVisibleIndex = Math.min(firstVisibleIndex, count-visibleInFrameCount);
            for (int i=firstVisibleIndex;i<Math.min(firstVisibleIndex+visibleInFrameCount,count);i++) {
                content.updateElement(layout.getChildAt(i-firstVisibleIndex),i);
            }
        }
        ViewGroup.LayoutParams par = topSpacing.getLayoutParams();
        par.height= firstVisibleIndex*childHeight;
        topSpacing.setLayoutParams(par);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        updateLayout();
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
