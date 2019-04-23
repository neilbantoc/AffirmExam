package neilbantoc.framework.utils;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;

//http://stackoverflow.com/questions/7417123/android-how-to-adjust-layout-in-full-screen-mode-when-softkeyboard-is-visible
public class AndroidBug5497Workaround {


    private static AndroidBug5497Workaround mInstance = null;
    private View mChildOfContent;
    private int usableHeightPrevious;
    private FrameLayout.LayoutParams frameLayoutParams;
    private ViewTreeObserver.OnGlobalLayoutListener _globalListener;

    public static AndroidBug5497Workaround getInstance (AppCompatActivity activity) {
        if(mInstance==null) {
            synchronized (AndroidBug5497Workaround.class) {
                mInstance = new AndroidBug5497Workaround(activity);
            }
        }
        return mInstance;
    }

    private AndroidBug5497Workaround(AppCompatActivity activity) {
        FrameLayout content = activity.findViewById(android.R.id.content);
        mChildOfContent = content.getChildAt(0);
        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();

        _globalListener = new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                 possiblyResizeChildOfContent();
            }
        };
    }

    public void setListener()
    {
         mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(_globalListener);
    }

    public void removeListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mChildOfContent.getViewTreeObserver().removeOnGlobalLayoutListener(_globalListener);
        } else {
            mChildOfContent.getViewTreeObserver().removeGlobalOnLayoutListener(_globalListener);
        }
    }

    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            if (heightDifference > (usableHeightSansKeyboard/4)) {
                // keyboard probably just became visible
                frameLayoutParams.height = usableHeightSansKeyboard - heightDifference;
            } else {
                // keyboard probably just became hidden
                frameLayoutParams.height = usableHeightSansKeyboard;
            }
            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        return (r.bottom);
    } 
}