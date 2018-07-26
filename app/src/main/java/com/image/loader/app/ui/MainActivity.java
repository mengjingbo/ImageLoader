package com.image.loader.app.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.image.loader.app.R;
import com.image.loader.app.TestUri;
import com.imageloader.module.ImageLoader;
import com.imageloader.module.cache.CacheMode;
import com.imageloader.module.mode.PriorityMode;
import com.imageloader.module.transform.BlurTransformation;
import com.imageloader.module.transform.CircleTransformation;
import com.imageloader.module.transform.GrayScaleTransformation;
import com.imageloader.module.transform.RotateTransformation;
import com.imageloader.module.transform.RoundedCornersTransformation;

public class MainActivity extends AppCompatActivity {

    private String mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        mUri = TestUri.IMAGE_1;
        ImageLoader.getDefault()
                .with(this)
                .load(TestUri.IMAGE_1)
                .override(800, 800)
                .diskCacheStrategy(CacheMode.AUTOMATIC)
                .priority(PriorityMode.HIGH)
                .skipMemoryCache(true)
                .build()
                .into((ImageView) findViewById(R.id.image_view));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_1:
                mUri = TestUri.ASSETS_IMAGE;
                ImageLoader.getDefault()
                        .with(this)
                        .load(mUri)
                        .override(800, 800)
                        .build()
                        .into((ImageView) findViewById(R.id.image_view));
                break;
            case R.id.item_2:
                ImageLoader.getDefault()
                        .with(this)
                        .load(R.drawable.ic_launcher_background)
                        .override(800, 800)
                        .build()
                        .into((ImageView) findViewById(R.id.image_view));
                break;
            case R.id.item_3:
                mUri = TestUri.IMAGE_1;
                ImageLoader.getDefault()
                        .with(this)
                        .load(mUri)
                        .override(800, 800)
                        .build()
                        .into((ImageView) findViewById(R.id.image_view));
                break;
            case R.id.item_4:
                ImageLoader.getDefault().pauseAllRequests(this);
                break;
            case R.id.item_5:
                ImageLoader.getDefault().resumeRequests(this);
                break;
            case R.id.item_6:
                ImageLoader.getDefault().clearDiskCache(this);
                break;
            case R.id.item_7:
                ImageLoader.getDefault().clearMemory(this);
                break;
            case R.id.item_8:
                ImageLoader.getDefault()
                        .with(this)
                        .override(800, 800)
                        .load(mUri)
                        .blur(new BlurTransformation(this, 8))
                        .build()
                        .into((ImageView) findViewById(R.id.image_view));
                break;
            case R.id.item_9:
                ImageLoader.getDefault()
                        .with(this)
                        .override(800, 800)
                        .load(mUri)
                        .rotate(new RotateTransformation(90))
                        .build()
                        .into((ImageView) findViewById(R.id.image_view));
                break;
            case R.id.item_10:
                ImageLoader.getDefault()
                        .with(this)
                        .override(800, 800)
                        .load(mUri)
                        .grayScale(new GrayScaleTransformation())
                        .build()
                        .into((ImageView) findViewById(R.id.image_view));
                break;
            case R.id.item_11:
                ImageLoader.getDefault()
                        .with(this)
                        .override(800, 800)
                        .load(mUri)
                        .roundedCorners(new RoundedCornersTransformation(20, 10))
                        .build()
                        .into((ImageView) findViewById(R.id.image_view));
                break;
            case R.id.item_12:
                ImageLoader.getDefault()
                        .with(this)
                        .override(800, 800)
                        .load(mUri)
                        .circle(new CircleTransformation())
                        .build()
                        .into((ImageView) findViewById(R.id.image_view));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
