package me.fdawei.textheadphoto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import me.fdawei.textheadphoto.util.BitmapUtils;
import me.fdawei.textheadphoto.util.DimensionUtils;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    int _100dp = (int) DimensionUtils.dp2px(this, 100);

    ImageView textPhoto = findViewById(R.id.iv_text_photo);
    textPhoto.setImageBitmap(BitmapUtils.createTextBmp("好", _100dp));

    Bitmap source = BitmapFactory.decodeResource(getResources(), R.drawable.image_long);

    ImageView imagePhoto = findViewById(R.id.iv_image_photo);
    imagePhoto.setImageBitmap(BitmapUtils.createCircleBmp(source, _100dp, _100dp));

    ImageView roundRectImage = findViewById(R.id.iv_round_rect_photo);
    int _10dp = (int) DimensionUtils.dp2px(this, 10);
    roundRectImage.setImageBitmap(BitmapUtils.createRoundRectBmp(source, _100dp, _100dp, _10dp));
  }
}
