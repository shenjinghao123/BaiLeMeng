package com.bailemeng.app.utils;

/**
 * Created by test1234 on 2017/8/9.
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Bitmap工具类
 * 创建Bitmap对象
 */
public class BitmapUtils{

    private Context context;

    /**
     * 单例模式创建实体类
     */
    public BitmapUtils (Context context){

    }

    /**
     * 压缩后图片
     */
    public Bitmap BitmapCompression(Bitmap rawBitmap, float newHeight, float newWidth){
        // 得到原图的高度和宽度
        float rawHeight = rawBitmap.getHeight();
        float rawWidth = rawBitmap.getWidth();
        // 计算缩放因子
        float heightScale = ((float) newHeight) / rawHeight;
        float widthScale = ((float) newWidth) / rawWidth;
        // 新建立矩阵
        Matrix matrix = new Matrix();
        matrix.postScale(heightScale, widthScale);

        // 设置图片的旋转角度
        //matrix.postRotate(-30);
        // 设置图片的倾斜
        //matrix.postSkew(0.1f, 0.1f);

        //压缩后图片的宽和高以及KB大小均会变化
        return Bitmap.createBitmap(rawBitmap, 0, 0, (int)rawWidth, (int)rawWidth, matrix, true);
    }

    /**
     * 将Bitmap转换成Drawable并在ImageView上显示
     */
    public void BitmapToDrawable(Bitmap rawBitmap, ImageView imageView){
        Drawable newBitmapDrawable = new BitmapDrawable(rawBitmap);
        imageView.setImageDrawable(newBitmapDrawable);
    }

    public static Bitmap Bytes2Bimap(byte[] b){
        if(b.length!=0){
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        }else {
            return null;
        }
    }

    //问题:
    //原图大小为625x690 90.2kB
    //如果设置图片500x500 压缩后大小为171kB.即压缩后kB反而变大了.
    //原因是将:compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);
    //第二个参数quality设置得有些大了(比如100).
    //常用的是80,刚设100太大了造成的.
    //————>以上为将图片高宽和的大小kB压缩

    /**
     * 压缩且保存图片到SDCard
     * Bitmap rawBitmap 需要保存的Bitmap
     * String fileName  保存的文件名
     * int quality      图像压缩比的值
     */
    private void compressAndSaveBitmapToSDCard(Bitmap rawBitmap, String fileName, int quality){
        String saveFilePaht = this.getSDCardPath() + File.separator + fileName;
        File saveFile = new File(saveFilePaht);
        if (!saveFile.exists()) {
            try {
                saveFile.createNewFile();
                FileOutputStream fileOutputStream=new FileOutputStream(saveFile);
                if (fileOutputStream!=null) {
                    //imageBitmap.compress(format, quality, stream);
                    //把位图的压缩信息写入到一个指定的输出流中
                    //第一个参数format为压缩的格式
                    //第二个参数quality为图像压缩比的值,0-100.0 意味着小尺寸压缩,100意味着高质量压缩,常用的是80
                    //第三个参数stream为输出流
                    rawBitmap.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    /**
//     * 获取网络图片
//     *
//     * @param imgUrl 网络图片的地
//     * @param im     网络图片
//     */
//    public static synchronized void setBitmap(final URL imgUrl, final ImageView im) {
//        final Handler handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                Bundle data = msg.getData();
//                Bitmap bitmap = data.getParcelable("bitmap");
//
////                    int width, height;
////                    height = bitmap.getHeight();
////                    width = bitmap.getWidth();
////
////                    Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
////                    Canvas c = new Canvas(bmpGrayscale);
////                    Paint paint = new Paint();
////                    ColorMatrix cm = new ColorMatrix();
////                    cm.setSaturation(0);
////                    ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
////                    paint.setColorFilter(f);
////                    c.drawBitmap(bitmap, 0, 0, paint);
//
//                if (bitmap != null && !bitmap.isRecycled()) {
//                    im.setImageBitmap(bitmap);
//                }
////					bitmap.recycle();
//                super.handleMessage(msg);
//            }
//        };
//        // 将文件名MD5加密
//        final String umd5 = MD5Util.digest/*MD5*/(imgUrl.toString());
//
//        final FileUtil fu = FileUtil.getInstance(null);
//
//        if (fu.existsThumb(umd5)) {
//            Message msg = new Message();
//            Bundle data = new Bundle();
//            Bitmap thumb = fu.readThumbFile(umd5);
//            data.putParcelable("bitmap", thumb);
//            msg.setData(data);
//            handler.sendMessage(msg);
//        } else {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    Bitmap bitmap = null;
//                    try {
//                        bitmap = BitmapFactory.decodeStream(imgUrl.openStream());
//                        fu.writeFileToSD(umd5, bitmap, true);
//                        Message msg = new Message();
//                        Bundle data = new Bundle();
//                        data.putParcelable("bitmap", bitmap);
//                        msg.setData(data);
//                        handler.sendMessage(msg);
//                    } catch (Exception e) {
//                    }
//                }
//            }).start();
//        }
//    }

//    /***
//     * 设置网路图片为缩略图
//     */
//    public static void setThumbBitmap(final URL imgUrl, final ImageView im) {
//        setThumbBitmap(imgUrl, im, 100, 100);
//    }

//    /**
//     * 缩略图
//     */
//    public static synchronized void setThumbBitmap(final URL imgUrl, final ImageView im, final int height, final int width) {
//        final Handler handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                Bundle data = msg.getData();
//                Bitmap thumb = data.getParcelable("thumb");
//                if (thumb != null && !thumb.isRecycled()) {
//                    im.setImageBitmap(thumb);
//                }
//                super.handleMessage(msg);
//            }
//        };
//        // 将文件名MD5加密
//        final String umd5 = MD5Util.MD5(imgUrl.toString());
//
//        final FileUtil fu = FileUtil.getInstance(null);
//
//        if (fu.existsThumb(umd5)) {
//            Message msg = new Message();
//            Bundle data = new Bundle();
//            Bitmap thumb = fu.readThumbFile(umd5);
//            data.putParcelable("thumb", thumb);
//            msg.setData(data);
//            handler.sendMessage(msg);
//        } else {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    Bitmap bitmap = null;
//                    try {
//                        bitmap = BitmapFactory.decodeStream(imgUrl.openStream());
//                        Bitmap thumb = ThumbnailUtils.extractThumbnail(bitmap, width, height);
//                        fu.writeFileToSD(umd5, thumb, true);
//                        bitmap.recycle();
//                        Message msg = new Message();
//                        Bundle data = new Bundle();
//                        data.putParcelable("thumb", thumb);
//                        msg.setData(data);
//                        handler.sendMessage(msg);
//                    } catch (Exception e) {
//                    }
//                }
//            }).start();
//        }
//    }

    /**
     * 获取图片的缩略图
     * String filePath 图片路径
     */
    private Bitmap getBitmapThumbnail(String filePath){
        BitmapFactory.Options options = new BitmapFactory.Options();
        //true那么将不返回实际的bitmap对象,不给其分配内存空间但是可以得到一些解码边界信息即图片大小等信息
        options.inJustDecodeBounds = true;
        //此时rawBitmap为null
        Bitmap rawBitmap = BitmapFactory.decodeFile(filePath, options);
        if (rawBitmap == null) {
            System.out.println("此时rawBitmap为null");
        }
        //inSampleSize表示缩略图大小为原始图片大小的几分之一,若该值为3
        //则取出的缩略图的宽和高都是原始图片的1/3,图片大小就为原始大小的1/9
        //计算sampleSize
        int sampleSize = computeSampleSize(options, 150, 200*200);
        //为了读到图片,必须把options.inJustDecodeBounds设回false
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        //原图大小为625x690 90.2kB
        //测试调用computeSampleSize(options, 100, 200*100);
        //得到sampleSize=8
        //得到宽和高位79和87
        //79*8=632 87*8=696
        Bitmap thumbnailBitmap = BitmapFactory.decodeFile(filePath, options);
        //保存到SD卡方便比较
        this.compressAndSaveBitmapToSDCard(thumbnailBitmap, "15.jpg", 80);
        return thumbnailBitmap;
    }

    /**
     * 获取图片缩略图方法2
     * String filePath 图片路径
     */
    public Bitmap getBitmapThumbnail2(String filePath, int newHeight, int newWidth){
        //String SDCarePath2=Environment.getExternalStorageDirectory().toString();
        //String filePath2=SDCarePath2+"/"+"haha.jpg";
        Bitmap tempBitmap = BitmapFactory.decodeFile(filePath);
        return ThumbnailUtils.extractThumbnail(tempBitmap, newHeight, newWidth);
    }

    /**
     * 从资源文件中得到图片
     */
    public Bitmap getBitmapForRes(Context context, int resId){
        return BitmapFactory.decodeResource(context.getResources(), resId);
    }

    /**
     * 获取资源图片
     */
    public static Bitmap getImageFromAssetsFile(Context context, String filename) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(filename);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            Log.e("ERROR", "FROM BitmapUtil:" + e.getMessage());
        }
        return image;
    }

    /**
     * 获取SDCard上的图片
     * String filePath 图片路径
     * return
     */
    private InputStream getBitmapInputStreamFromSDCard(String fileName){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String SDCarePath = Environment.getExternalStorageDirectory().toString();
            String filePath = SDCarePath + File.separator+fileName;
            File file = new File(filePath);
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                return fileInputStream;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 从SDCard中获取图片
     */
    public Bitmap getBitmapForSDCard(String filePath){
        return BitmapFactory.decodeFile(filePath, null);
    }

    /**
     * 从SDCard中获取图片
     */
    public Bitmap getBitmapForSDCard2(String fileName){
        InputStream inputStream=getBitmapInputStreamFromSDCard(fileName);
        return BitmapFactory.decodeStream(inputStream);
    }

    /**
     * 设置图片圆角
     */
    public Bitmap getRoundBitmap(Context context, Bitmap bitmap, ImageView image){
        Bitmap roundBitmap = toRoundCorner(bitmap, 40);
        image.setImageBitmap(roundBitmap);
        return roundBitmap;
    }

    /**
     * @param bitmap 需要修改的图片
     * @param pixels 圆角的弧度
     * @return 圆角图片
     * 参考资料: http://blog.csdn.net/c8822882/article/details/6906768
     */

    public Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
        Bitmap roundCornerBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(roundCornerBitmap);
        int color = 0xff424242; //int color = 0xff424242;
        Paint paint = new Paint();
        paint.setColor(color);
        //防止锯齿
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float roundPx = pixels;
        //相当于清屏
        canvas.drawARGB(0, 0, 0, 0);
        //先画了一个带圆角的矩形
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //再把原来的bitmap画到现在的bitmap！！！注意这个理解
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return roundCornerBitmap;
    }

//    /**
//     * 设置图片灰度
//     */
//    public static synchronized void setGreyBitmap(final URL imgUrl, final ImageView im) {
//        final Handler handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                Bundle data = msg.getData();
//                Bitmap bitmap = data.getParcelable("bitmap");
//
//                int width, height;
//                height = bitmap.getHeight();
//                width = bitmap.getWidth();
//
//                Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
//                Canvas c = new Canvas(bmpGrayscale);
//                Paint paint = new Paint();
//                ColorMatrix cm = new ColorMatrix();
//                cm.setSaturation(0);
//                ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
//                paint.setColorFilter(f);
//                c.drawBitmap(bitmap, 0, 0, paint);
//
//                if (bmpGrayscale != null && !bmpGrayscale.isRecycled()) {
//                    im.setImageBitmap(bmpGrayscale);
//                }
////					bitmap.recycle();
//                super.handleMessage(msg);
//            }
//        };
//        // 将文件名MD5加密
//        final String umd5 = MD5Util.MD5(imgUrl.toString());
//
//        final FileUtil fu = FileUtil.getInstance(null);
//
//        if (fu.existsThumb(umd5)) {
//            Message msg = new Message();
//            Bundle data = new Bundle();
//            Bitmap thumb = fu.readThumbFile(umd5);
//            data.putParcelable("bitmap", thumb);
//            msg.setData(data);
//            handler.sendMessage(msg);
//        } else {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    Bitmap bitmap = null;
//                    try {
//                        bitmap = BitmapFactory.decodeStream(imgUrl.openStream());
//                        fu.writeFileToSD(umd5, bitmap, true);
//                        Message msg = new Message();
//                        Bundle data = new Bundle();
//                        data.putParcelable("bitmap", bitmap);
//                        msg.setData(data);
//                        handler.sendMessage(msg);
//                    } catch (Exception e) {
//                    }
//                }
//            }).start();
//        }
//    }


    //参考资料：
    //http://my.csdn.net/zljk000/code/detail/18212
    //第一个参数:原本Bitmap的options
    //第二个参数:希望生成的缩略图的宽高中的较小的值
    //第三个参数:希望生成的缩量图的总像素
    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        //原始图片的宽
        double w = options.outWidth;
        //原始图片的高
        double h = options.outHeight;
        System.out.println("========== w="+w+",h="+h);
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    /**
     * 获取SDCard的目录路径功能
     */
    private String getSDCardPath() {
        String SDCardPath = null;
        // 判断SDCard是否存在
        boolean IsSDcardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if (IsSDcardExist) {
            SDCardPath = Environment.getExternalStorageDirectory().toString();
        }
        return SDCardPath;
    }

}