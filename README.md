# ImageLoader

基于第三方图片加载框架封装的一个应用层，可随意切换加载框架

#### imageloader-module的设计主要分为：

- 代理模块
- mode模块
- request模块
- cache模块
- transformation模块
- 调用模块

#### 使用说明

##### 定义

案例中只对Glide做了一层封装，其他加载框架可参考，具体如下：

- 实现 **IImageLoader** 接口
- 可参考 **GlideLoader** 类

##### 初始化

```java
public class AppContext extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化ImageLoader
        ImageLoader.getDefault().init(new GlideLoader());
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ImageLoader.getDefault().trimMemory(getApplicationContext(), level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImageLoader.getDefault().clearMemory(getApplicationContext());
    }
}
```

##### 具体调用

- 加载 **Assets** 中的图片

```java
 String ASSETS_IMAGE = "file:///android_asset/image/default_assets_image.jpg";
 ImageLoader.getDefault()
        .with(this)
        .load(ASSETS_IMAGE)
        .override(800, 800)
		.build()
        .into((ImageView) findViewById(R.id.image_view));
```

- 加载 **Drawable** 中的图片资源

```java
 ImageLoader.getDefault()
        .with(this)
        .load(R.drawable.ic_launcher_background)
        .override(800, 800)
		.build()
        .into((ImageView) findViewById(R.id.image_view));
```

- 加载网络图片

```java
 String IMAGE_URI = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532341650414&di=e1f5a6c0f5cd073dc266ad75f402cb44&imgtype=0&src=http%3A%2F%2Fi1.17173.itc.cn%2F2011%2Fnews%2F2011%2F02%2F11%2Fs0211eve04s.jpg";
 ImageLoader.getDefault()
        .with(this)
        .load(IMAGE_URI)
        .override(800, 800)
		.build()
        .into((ImageView) findViewById(R.id.image_view));
```

更多使用方式见案例
