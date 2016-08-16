package com.wujie.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/16.
 */
public class BannerAdapter extends PagerAdapter{

    private Context mContext;

    private List<Integer> resIds;

    public BannerAdapter(Context context) {
        this.mContext = context;
    }

    public void update(List<Integer> resIds) {
        if(this.resIds != null)
            this.resIds.clear();
        if(resIds != null)
            this.resIds = resIds;
    }
    @Override
    public int getCount() {
        return resIds == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(resIds.get(position % resIds.size()));
        container.addView(imageView);

        return imageView;
    }
}
