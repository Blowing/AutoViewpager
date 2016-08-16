package com.wujie.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/8/15.
 */
public class AutoPlayViewPager extends ViewPager{
    public AutoPlayViewPager(Context context) {
        super(context);

    }

    public AutoPlayViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 播放时间
     */
    private int showTime = 3 * 1000;

    /**
     * 滚动方向
     */
    private Direction direction = Direction.LEFT;

    public void setShowTime(int showTimeMillis) {
        this.showTime = showTimeMillis;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void start() {
        stop();
        postDelayed(player,showTime);
    }

    public void stop () {
        removeCallbacks(player);
    }

    public void previous() {
        if (direction == Direction.RIGHT) {
            play(Direction.LEFT);
        } else if(direction == Direction.LEFT) {
            play(Direction.RIGHT);
        }
    }

    public void next() {
        play(direction);
    }

    private synchronized void play(Direction direction) {
        PagerAdapter pagerAdapter = getAdapter();
        if(pagerAdapter != null) {
            int count = pagerAdapter.getCount();
            int currentItem = getCurrentItem();
            switch (direction) {
                case LEFT:
                    currentItem++;
                    if(currentItem > count) {
                        currentItem = 0;
                    }
                    break;
                case RIGHT:
                    currentItem--;
                    if (currentItem < 0){
                        currentItem = count;
                    }
                    break;
                setCurrentItem(currentItem);
            }
            start();
        }

    }

    private Runnable player = new Runnable() {
        @Override
        public void run() {
            play(direction);
        }
    };

    public enum Direction {
        LEFT,
        RIGHT
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        addOnPageChangeListenr(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == SCROLL_STATE_IDLE)
                    start();
                else if (state == SCROLL_STATE_DRAGGING)
                    stop();
            }
        });
    }
}
