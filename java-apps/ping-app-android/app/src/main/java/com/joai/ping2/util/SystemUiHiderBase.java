package com.joai.ping2.util;;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

/**
 * A base implementation of {@link SystemUiHiderBase}. Uses APIs available in all
 * API levels to show and hide the status bar.
 */
public class SystemUiHiderBase {
    public static final int FLAG_LAYOUT_IN_SCREEN_OLDER_DEVICES = 0x1;
    public static final int FLAG_FULLSCREEN = 0x2;
    public static final int FLAG_HIDE_NAVIGATION = FLAG_FULLSCREEN | 0x4;
    private static OnVisibilityChangeListener sDummyListener = new OnVisibilityChangeListener() {
        @Override
        public void onVisibilityChange(boolean visible) {
        }
    };
    protected Activity mActivity;
    protected View mAnchorView;
    protected int mFlags;
    protected OnVisibilityChangeListener mOnVisibilityChangeListener = sDummyListener;
    /**
     * Whether or not the system UI is currently visible. This is a cached value
     * from calls to {@link #hide()} and {@link #show()}.
     */
    private boolean mVisible = true;

    /**
     * Constructor not intended to be called by clients. Use
     * {@link SystemUiHiderBase#getInstance} to obtain an instance.
     */
    protected SystemUiHiderBase(Activity activity, View anchorView, int flags) {
        SystemUiHiderBase.this.mActivity = activity;
        SystemUiHiderBase.this.mAnchorView = anchorView;
        SystemUiHiderBase.this.mFlags = flags;
    }

    public static SystemUiHiderBase getInstance(Activity activity, View anchorView, int flags) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return new SystemUiHiderHoneycomb(activity, anchorView, flags);
        } else {
            return new SystemUiHiderBase(activity, anchorView, flags);
        }
    }

    public void setup() {
        if ((mFlags & FLAG_LAYOUT_IN_SCREEN_OLDER_DEVICES) == 0) {
            mActivity.getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                            | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                            | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    public boolean isVisible() {
        return mVisible;
    }

    public void hide() {
        if ((mFlags & FLAG_FULLSCREEN) != 0) {
            mActivity.getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        mOnVisibilityChangeListener.onVisibilityChange(false);
        mVisible = false;
    }

    public void show() {
        if ((mFlags & FLAG_FULLSCREEN) != 0) {
            mActivity.getWindow().setFlags(
                    0,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        mOnVisibilityChangeListener.onVisibilityChange(true);
        mVisible = true;
    }

    public void toggle() {
        if (isVisible()) {
            hide();
        } else {
            show();
        }
    }

    public void setOnVisibilityChangeListener(OnVisibilityChangeListener listener) {
        if (listener == null) {
            listener = sDummyListener;
        }

        mOnVisibilityChangeListener = listener;
    }

    public interface OnVisibilityChangeListener {
        public void onVisibilityChange(boolean visible);
    }
}
