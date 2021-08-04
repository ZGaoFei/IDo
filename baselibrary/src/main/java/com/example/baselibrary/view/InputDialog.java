package com.example.baselibrary.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.baselibrary.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

// 骗子，哼……
public class InputDialog extends Dialog implements View.OnLayoutChangeListener {

    private boolean landscape;
    private int screenHeight;
    private int mHeight;
    private int statusBarHeight;
    private boolean isMiUi;

    private InputLayout inputLayout;

    public InputDialog(@NonNull Context context) {
        this(context, 0);
    }

    public InputDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.BaseDialogStyle);
        setCanceledOnTouchOutside(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(inputLayout = new InputLayout(getContext()));

        screenHeight = getScreenHeight();
        isMiUi = true;

        if (isActivityTintMode(getOwnerActivity())) {
            statusBarHeight = getStatusBarHeight(getContext2());
        }

        View view = getOwnerActivity().findViewById(Window.ID_ANDROID_CONTENT);
        view.addOnLayoutChangeListener(this);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.x = 0;
        lp.y = 0;
        lp.height = (mHeight = view.getHeight()) - 44;
        lp.width = -1;
        lp.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;
        lp.gravity = Gravity.TOP;
        getWindow().setAttributes(lp);
        screenHeight = getScreenHeight();
    }

    @Override
    protected void onStop() {
        super.onStop();
        inputLayout.hideSoftInput();
        dismiss();
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom,
                               int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (!isMiUi) {
            boolean resize = bottom != screenHeight;
            screenHeight = bottom;
            if (resize && inputLayout != null) {
            }
        }

        int height = bottom - top;
        if (mHeight != height && height > 0) {
            mHeight = height;
            Window window = getWindow();
            if (window == null) {
                return;
            }
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.height = mHeight - statusBarHeight;
            window.setAttributes(lp);
        }
    }

    private int getScreenHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static boolean isMIUI() {
        String property = getSystemProperty("ro.miui.ui.version.code", "");
        return !TextUtils.isEmpty(property);
    }

    public Context getContext2() {
        Activity activity = getOwnerActivity();
        return activity == null ? getContext() : activity;
    }

    private static String getSystemProperty(String key, String defaultValue) {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            return (String) get.invoke(clz, key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static boolean isActivityTintMode(Activity activity) {
        if (activity == null) {
            return false;
        }

        return isWindowTintMode(activity.getWindow());
    }

    public static boolean isWindowTintMode(Window window) {
        if (window == null) {
            return false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View view = window.findViewById(android.R.id.content);
            if (view != null) {
                return view.getBottom() == view.getHeight();
            }
        }

        return false;
    }

    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        int resourceId = getResourceIdentifier(context, "android", "dimen", "status_bar_height");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }

        return statusBarHeight;
    }

    public static int getResourceIdentifier(Context context, String pkgName, String type, String name) {
        int id = context.getResources().getIdentifier(name, type, pkgName);
        if (id <= 0) { // 插件中可能获取不到，用反射来
            if ("android".equals(pkgName)) {
                pkgName = "com.android.internal";
            }

            try {
                Field field = Class.forName(pkgName + ".R$" + type).getDeclaredField(name);
                field.setAccessible(true);
                id = (int) field.get(null);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return id;
    }

    public class InputLayout extends LinearLayout implements TextView.OnEditorActionListener, TextWatcher {
        private final static int KEYBOARD_DEFAULT_HEIGHT_VERTICAL = 301;
        private final static int KEYBOARD_DEFAULT_HEIGHT_HORIZONTAL = 201;
        private EditText editText;

        private InputMethodManager inputMethodManager;

        private boolean reShowSoftInput = false;

        public InputLayout(@NonNull Context context) {
            this(context, null);
        }

        public InputLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public InputLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);

            init(context);
        }

        private void init(Context context) {
            setOrientation(VERTICAL);
            setGravity(Gravity.BOTTOM);
            if (inputMethodManager == null) {
                inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            }

            View view = LayoutInflater.from(context).inflate(R.layout.layout_input, this, false);
            editText = view.findViewById(R.id.input);
            editText.setHint("hello world");
            addView(view);

            editText.setOnEditorActionListener(this);
            editText.addTextChangedListener(this);

            editText.requestFocus();
        }

        private boolean isSoftShow() {
            int height = getScreenHeight();
            Rect rec = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rec);

            if (rec.bottom < height - 250) {
                return true;
            } else {
                return false;
            }
        }

        private void showSoftInput() {
            if (isSoftShow()) {
                return;
            }

            reShowSoftInput = true;
            editText.requestFocus();
            editText.post(new Runnable() {
                @Override
                public void run() {
                    inputMethodManager.toggleSoftInput(0, 0);
                }
            });
        }

        private void hideSoftInput() {
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            }
        }

        private int getKeyBoardHeight() {
            if (landscape) {
                return getHorizontalKeyBoardHeight();
            } else {
                return getVerticalKeyBoardHeight();
            }
        }


        private int getHorizontalKeyBoardHeight() {
            //并没有保存软键盘数据
            Rect rec = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rec);


            int height = screenHeight - rec.bottom;
            if (height < (int) DipAndPix.dip2px(getContext(), 100)) {
                return (int) DipAndPix.dip2px(getContext(), KEYBOARD_DEFAULT_HEIGHT_HORIZONTAL);
            }

            return height;
        }

        private int getVerticalKeyBoardHeight() {
            //并没有保存软键盘数据
            Rect rec = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rec);

            if (isMiUi) {//小米手机采用此方法，否则会有问题
                if (rec.bottom > screenHeight) {
                    screenHeight = rec.bottom;
                }
            }

            int height = screenHeight - rec.bottom;
            if (height < dip2px(100)) {
                return dip2px(KEYBOARD_DEFAULT_HEIGHT_VERTICAL);
            }

            return height;
        }

        private int dip2px(int dip) {
            return (int) DipAndPix.dip2px(getContext(), dip);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            try {
                boolean handle = super.dispatchTouchEvent(ev);
                if (!handle && ev.getAction() == MotionEvent.ACTION_DOWN) {
                    hideSoftInput();
                }
                return handle;
            } catch (Throwable e) {
                //XLog.reportError(e);
            }
            return false;
        }

        @Override
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            editText.setOnEditorActionListener(null);
            editText.removeTextChangedListener(this);
        }

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            return false;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
