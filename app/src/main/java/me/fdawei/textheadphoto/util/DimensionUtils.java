package me.fdawei.textheadphoto.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by david on 2018/1/22.
 */

public class DimensionUtils {
  public static float dp2px(Context context, float dp) {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
  }
}
