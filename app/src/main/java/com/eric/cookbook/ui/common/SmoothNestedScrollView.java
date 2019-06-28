package com.eric.cookbook.ui.common;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;


public class SmoothNestedScrollView extends NestedScrollView {
        private int downX;
        private int downY;
        private int mTouchSlop;

        public SmoothNestedScrollView(Context context) {
            this(context, null);
        }

        public SmoothNestedScrollView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public SmoothNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent e) {
            int action = e.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    downX = (int) e.getRawX();
                    downY = (int) e.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int moveY = (int) e.getRawY();
                    if (Math.abs(moveY - downY) > mTouchSlop) {
                        return true;
                    }
            }
            return super.onInterceptTouchEvent(e);
        }
}
