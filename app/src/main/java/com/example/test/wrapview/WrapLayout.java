package com.example.test.wrapview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class WrapLayout extends ViewGroup {
    private int showNum = 6;

    public WrapLayout(Context context) {
        super(context);
    }

    public WrapLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 显示几个子控件，默认是6个
    public void showChildrenNum(int num) {
        if (num > 6) {
            num = 6;
        } else if (num < 3) {
            num = 3;
        }
        showNum = num;

        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        hideChildren();

        layoutChildren();
    }

    private void hideChildren() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            // 控制显示和隐藏
            if (i > showNum - 1) {
                child.setVisibility(GONE);
            } else {
                child.setVisibility(VISIBLE);
            }
        }
    }

    private void layoutChildren() {
        int width = getWidth();
        int height = getHeight();
        int itemHeight = height / 2;
        if (showNum == 3) {
            View child0 = getChildAt(0);
            View child1 = getChildAt(1);
            View child2 = getChildAt(2);
            int itemWidth = width / 2;
            child0.layout(0, 0, itemWidth, itemHeight);
            child1.layout(itemWidth, 0, width, itemHeight);
            child2.layout(itemWidth / 2, itemHeight, itemWidth / 2 + itemWidth, height);
        } else if (showNum == 4) {
            View child0 = getChildAt(0);
            View child1 = getChildAt(1);
            View child2 = getChildAt(2);
            View child3 = getChildAt(3);
            int itemWidth = width / 2;
            child0.layout(0, 0, itemWidth, itemHeight);
            child1.layout(itemWidth, 0, width, itemHeight);
            child2.layout(0, itemHeight, itemWidth, height);
            child3.layout(itemWidth, itemHeight, width, height);
        } else if (showNum == 5) {
            View child0 = getChildAt(0);
            View child1 = getChildAt(1);
            View child2 = getChildAt(2);
            View child3 = getChildAt(3);
            View child4 = getChildAt(4);
            int itemWidth = width / 3;
            child0.layout(0, 0, itemWidth, itemHeight);
            child1.layout(itemWidth, 0, itemWidth + itemWidth, itemHeight);
            child2.layout(itemWidth + itemWidth, 0, width, itemHeight);
            child3.layout(itemWidth / 2, itemHeight, itemWidth + itemWidth / 2, height);
            child4.layout(itemWidth / 2 + itemWidth, itemHeight, itemWidth + itemWidth + itemWidth / 2, height);
        } else if (showNum == 6) {
            View child0 = getChildAt(0);
            View child1 = getChildAt(1);
            View child2 = getChildAt(2);
            View child3 = getChildAt(3);
            View child4 = getChildAt(4);
            View child5 = getChildAt(5);
            int itemWidth = width / 3;
            child0.layout(0, 0, itemWidth, itemHeight);
            child1.layout(itemWidth, 0, itemWidth + itemWidth, itemHeight);
            child2.layout(itemWidth + itemWidth, 0, width, itemHeight);
            child3.layout(0, itemHeight, itemWidth, height);
            child4.layout(itemWidth, itemHeight, itemWidth + itemWidth, height);
            child5.layout(itemWidth + itemWidth, itemHeight, width, height);
        }
    }
}
