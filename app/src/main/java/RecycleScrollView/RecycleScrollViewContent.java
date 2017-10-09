package RecycleScrollView;

import android.content.Context;
import android.view.View;

public abstract class RecycleScrollViewContent {
    private OnUpdateContentListener parent;

    abstract public View createInstance(Context context);
    abstract public void updateElement(View view, int index);
    abstract public void updateData();
    abstract public int getCount();

    protected void setOnUpdateContentListener(OnUpdateContentListener listener) {
        parent=listener;
    };

    public void update() {
        parent.onUpdateContent();
    }
}
