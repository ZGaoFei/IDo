package com.example.baselibrary.view;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class DipAndPix {
	private static DisplayMetrics metrics;

	public static float dip2px(View view, float dpValue) {
		return dip2px(view.getContext(), dpValue);
	}

	public static float sp2px(View view, float dpValue) {
		return sp2px(view.getContext(), dpValue);
	}

	public static float dip2px(Context context, float dpValue) {
    	return (dpValue * density(context) + 0.5f);
    }

	/**
	 * 输出 int 类型
	 * @param context
	 * @param dpValue
	 * @return
	 */
	public static int dip2pxI(Context context, float dpValue) {
		return (int) dip2px(context, dpValue);
	}

	public static float px2dip(Context context, float pxValue) {
        return (pxValue / density(context) + 0.5f);
    }
    
	public static float px2sp(Context context, float pxValue) {
		return (pxValue / scaledDensity(context) + 0.5f);
	}

	public static float sp2px(Context context, float spValue) {
		return (spValue * scaledDensity(context) + 0.5f);
	}
	
	public static Point getDisplaySize(Context context) {
		final Point sc = new Point(0, 0);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
			wm.getDefaultDisplay().getSize(sc);
		}

        return sc;
	}

	public static int getDisplayWidth(Context context) {
		return getDisplaySize(context).x;
	}

	public static int getDisplayHeight(Context context) {
		return getDisplaySize(context).y;
	}

	/**
	 * 获取屏幕最小边，比如宽度，在横屏的时候，要取y
	 * @param context
	 * @return
	 */
	public static int getDisplayMinSide(Context context) {
		Point pt = getDisplaySize(context);
		return pt.x > pt.y ? pt.y : pt.x;
	}

	public static int convertI(Context context, float srcSize, float srcDensity) {
		return (int) convert(context, srcSize, srcDensity);
	}

	public static float convert(Context context, float srcSize, float srcDensity) {
		float td = density(context);
		return srcSize * td / srcDensity;
	}

	private static float density(Context context) {
		if (metrics == null) {
			metrics = context.getResources().getDisplayMetrics();
		}

		if (metrics == null) {
			return 3.0f;
		}

		return metrics.density;
	}

	private static float scaledDensity(Context context) {
		if (metrics == null) {
			metrics = context.getResources().getDisplayMetrics();
		}

		if (metrics == null) {
			return 3.0f;
		}

		return metrics.scaledDensity;
	}
} 
