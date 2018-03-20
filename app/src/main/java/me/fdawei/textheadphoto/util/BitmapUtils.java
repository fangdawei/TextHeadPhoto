package me.fdawei.textheadphoto.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.text.TextUtils;

/**
 * Created by david on 2018/1/22.
 */

public class BitmapUtils {

  /**
   * 创建圆形背景带文字的图片
   *
   * @param text 图片显示的文字
   * @param bgColor 背景颜色
   * @param textColor 文字颜色
   * @param width 创建的图片的宽度
   * @param height 创建的图片的高度
   */
  public static Bitmap createTextBmp(String text, int bgColor, int textColor, int width, int height) {
    Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);

    Paint paint = new Paint();
    paint.setAntiAlias(true);//开启抗锯齿

    //绘制圆形背景
    int radius = width < height ? width / 2 : height / 2;
    paint.setColor(bgColor);
    canvas.drawCircle(width / 2, height / 2, radius, paint);

    if (!TextUtils.isEmpty(text)) {
      //绘制文字
      String firstLetter = text.substring(0, 1);
      paint.setColor(textColor);
      paint.setTextSize(radius);//字体大小等于半径
      paint.setTextAlign(Paint.Align.CENTER);
      Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
      int baseLine = height / 2 - (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.top;
      canvas.drawText(firstLetter, width / 2, baseLine, paint);
    }

    return bitmap;
  }

  public static Bitmap createTextBmp(String text, int radius) {
    return createTextBmp(text, 0xFF20A0FF, 0xFFFFFFFF, radius, radius);
  }

  /**
   * 创建圆形图片
   *
   * @param source 源图片
   * @param width 目标图片的宽度
   * @param height 目标图片的高度
   */
  public static Bitmap createCircleBmp(Bitmap source, int width, int height) {
    Bitmap taget = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(taget);

    Paint paint = new Paint();
    paint.setAntiAlias(true);

    int radius = width < height ? width / 2 : height / 2;
    canvas.drawCircle(width / 2, height / 2, radius, paint);

    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

    Bitmap scaledSource = BitmapUtils.createCenterScaledBmp(source, width, height);
    canvas.drawBitmap(scaledSource, 0, 0, paint);

    return taget;
  }

  /**
   * 根据目标图片比例，以源图片中心点为基准自适应缩放
   *
   * @param source 源图片
   * @param targetWidth 目标图片的宽度
   * @param targetHeight 目标图片的高度
   */
  public static Bitmap createCenterScaledBmp(Bitmap source, int targetWidth, int targetHeight) {
    int sourceWidth = source.getWidth();
    int sourceHeight = source.getHeight();

    float scale = (float) targetWidth / targetHeight;

    int scaledWidth;
    int scaledHeight;
    boolean baseHeight = true;
    scaledHeight = sourceHeight;
    scaledWidth = (int) (scaledHeight * scale);
    if (scaledWidth > sourceWidth) {
      scaledWidth = sourceWidth;
      scaledHeight = (int) (scaledWidth / scale);
      baseHeight = false;
    }

    int x;
    int y;
    if (baseHeight) {
      y = 0;
      x = (sourceWidth - scaledWidth) / 2;
    } else {
      x = 0;
      y = (sourceHeight - scaledHeight) / 2;
    }

    Bitmap tmpBmp = Bitmap.createBitmap(source, x, y, scaledWidth, scaledHeight);

    Bitmap resultBmp = null;
    if (tmpBmp != null) {
      resultBmp = Bitmap.createScaledBitmap(tmpBmp, targetWidth, targetHeight, true);
      tmpBmp.recycle();
    }
    return resultBmp;
  }

  /**
   * 创建圆角矩形图片
   *
   * @param source 源图片
   * @param width 目标图片的宽度
   * @param height 目标图片的高度
   * @param radius 圆角半径
   */
  public static Bitmap createRoundRectBmp(Bitmap source, int width, int height, int radius) {
    Bitmap tartget = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(tartget);

    Paint paint = new Paint();
    paint.setAntiAlias(true);//开启抗锯齿

    RectF rect = new RectF(0, 0, width, height);
    canvas.drawRoundRect(rect, radius, radius, paint);

    Bitmap scaledBitmap = createCenterScaledBmp(source, width, height);
    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    canvas.drawBitmap(scaledBitmap, 0, 0, paint);
    return tartget;
  }
}
