package com.bailemeng.app.tencent.shortvideo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


import com.bailemeng.app.R;

import java.util.ArrayList;

/**
 * Created by vinsonswang on 2017/8/24.
 */

public class RecordProgressView extends View{
    private final String TAG = "RecordProgressView";

    private Paint mRecordPaint;
    private Paint mPendingPaint;
    private Paint mSpacePaint;
    private int mBackgroundColor;
    private int mRecordColor;
    private int mPendingColor;
    private int mSpaceColor;

    private boolean isCursorShow = false;
    private boolean isInProgress = false;
    private Handler mHandler;

    private LinearGradient progressGradient;

    private ArrayList<ClipInfo> mClipInfoList;
    private ClipInfo mCurClipInfo;
    private boolean isPending;
//    private float mTotalWidth;

    private int mMaxDuration;
    private int mMinDuration;

    public RecordProgressView(Context context) {
        super(context);
        init();
    }

    public RecordProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecordProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mRecordPaint = new Paint();
        mPendingPaint = new Paint();
        mSpacePaint = new Paint();

        mRecordPaint.setAntiAlias(true);
        mPendingPaint.setAntiAlias(true);
        mSpacePaint.setAntiAlias(true);

        mBackgroundColor = getResources().getColor(R.color.record_progress_bg);
        mRecordColor = getResources().getColor(R.color.record_progress);
        mPendingColor = getResources().getColor(R.color.record_progress_pending);
        mSpaceColor = getResources().getColor(R.color.white);

        mRecordPaint.setColor(mRecordColor);
        mPendingPaint.setColor(mPendingColor);
        mSpacePaint.setColor(mSpaceColor);

        mClipInfoList = new ArrayList<ClipInfo>();
        mCurClipInfo = new ClipInfo();
        isPending = false;

        mHandler = new Handler();
        mHandler.postDelayed(cursorRunnable, 500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(mBackgroundColor);

        int lastTotalProgress = 0;
        float totalWidth = 0;
        for(ClipInfo clipInfo : mClipInfoList){
            float newWidth = (lastTotalProgress + clipInfo.progress) / (float)mMaxDuration * getWidth();
            switch (clipInfo.clipType){
                case ClipInfo.CLIP_TYPE_SPACE:
                    canvas.drawRect(totalWidth - getResources().getDimension(R.dimen.ugc_progress_divider), 0f, newWidth, getHeight(), mSpacePaint);
                    break;
                case ClipInfo.CLIP_TYPE_PROGRESS:
                    canvas.drawRect(totalWidth, 0f, newWidth, getHeight(), mRecordPaint);
//                    mPaint.setShader(progressGradient);
                    break;
                case ClipInfo.CLIP_TYPE_PENDING:
                    canvas.drawRect(totalWidth, 0f, newWidth, getHeight(), mPendingPaint);
                    break;
            }
            lastTotalProgress += clipInfo.progress;
            totalWidth = newWidth;
        }
        if(mCurClipInfo != null && mCurClipInfo.progress != 0){
            canvas.drawRect(totalWidth, 0f, totalWidth + mCurClipInfo.progress / (float)mMaxDuration * getWidth(), getHeight(), mRecordPaint);
            totalWidth = totalWidth + mCurClipInfo.progress / (float)mMaxDuration * getWidth();
        }
        if(lastTotalProgress + mCurClipInfo.progress < mMinDuration){
            canvas.drawRect(mMinDuration / (float)mMaxDuration * getWidth(), 0f,
                    mMinDuration / (float)mMaxDuration * getWidth() + getResources().getDimension(R.dimen.ugc_progress_min_pos), getHeight(), mSpacePaint);
        }
        if(isCursorShow || isInProgress){
            canvas.drawRect(totalWidth, 0f, totalWidth + getResources().getDimension(R.dimen.ugc_progress_cursor), getHeight(), mSpacePaint);
        }
    }

    private class ClipInfo{
        public static final int CLIP_TYPE_PROGRESS = 1;
        public static final int CLIP_TYPE_PENDING = 2;
        public static final int CLIP_TYPE_SPACE = 3;

        public int progress;
        public int clipType;
    }

    public void setMaxDuration(int maxDuration){
        this.mMaxDuration = maxDuration;
    }

    public void setMinDuration(int minDuration){
        this.mMinDuration = minDuration;
    }

    public void setProgress(int progress){
        isInProgress = true;
        if(isPending){
            for(ClipInfo clipInfo : mClipInfoList){
                if(clipInfo.clipType == ClipInfo.CLIP_TYPE_PENDING){
                    clipInfo.clipType = ClipInfo.CLIP_TYPE_PROGRESS;
                    isPending = false;
                    break;
                }
            }
        }
        this.mCurClipInfo.clipType = ClipInfo.CLIP_TYPE_PROGRESS;
        this.mCurClipInfo.progress = progress;
        invalidate();
    }

    public void clipComplete(){
        isInProgress = false;

        mClipInfoList.add(mCurClipInfo);
        ClipInfo clipInfo = new ClipInfo();
        clipInfo.clipType = ClipInfo.CLIP_TYPE_SPACE;
        clipInfo.progress = 0;
        mClipInfoList.add(clipInfo);
        mCurClipInfo = new ClipInfo();

        invalidate();
    }

    public void selectLast(){
        if(mClipInfoList.size() >= 2){
            ClipInfo clipInfo = mClipInfoList.get(mClipInfoList.size() - 2);
            clipInfo.clipType = ClipInfo.CLIP_TYPE_PENDING;
            isPending = true;
            invalidate();
        }
    }

    public void deleteLast(){
        if(mClipInfoList.size() >= 2){
            mClipInfoList.remove(mClipInfoList.size() - 1);
            mClipInfoList.remove(mClipInfoList.size() - 1);
        }
        invalidate();
    }

    private Runnable cursorRunnable = new Runnable() {
        @Override
        public void run() {
            isCursorShow = !isCursorShow;
            mHandler.postDelayed(cursorRunnable, 500);
            invalidate();
        }
    };

    public void release(){
        if(mHandler != null){
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }

}