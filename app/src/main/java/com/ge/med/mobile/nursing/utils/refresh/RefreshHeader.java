package com.ge.med.mobile.nursing.utils.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.utils.DensityUtil;

/**
 * Created by shy on 2016/10/25.
 */
public class RefreshHeader extends BaseHeader {

    private AnimationDrawable animationPull;
    private AnimationDrawable animationPullFan;
    private AnimationDrawable animationRefresh;

    private Context context;
    private ImageView header_img;
    private int[] pullAnimSrcs = new int[]{R.drawable.icon_refresh_1, R.drawable.icon_refresh_2, R.drawable.icon_refresh_3, R.drawable.icon_refresh_4,
            R.drawable.icon_refresh_5,
            R.drawable.icon_refresh_6, R.drawable.icon_refresh_7, R.drawable.icon_refresh_8, R.drawable.icon_refresh_9, R.drawable.icon_refresh_10,
            R.drawable.icon_refresh_11,
            R.drawable.icon_refresh_12, R.drawable.icon_refresh_13, R.drawable.icon_refresh_14, R.drawable.icon_refresh_15, R.drawable.icon_refresh_16};
    private int[] refreshAnimSrcs = new int[]{R.drawable.icon_refresh_1, R.drawable.icon_refresh_2, R.drawable.icon_refresh_3, R.drawable.icon_refresh_4,
            R.drawable.icon_refresh_5,
            R.drawable.icon_refresh_6, R.drawable.icon_refresh_7, R.drawable.icon_refresh_8, R.drawable.icon_refresh_9, R.drawable.icon_refresh_10,
            R.drawable.icon_refresh_11,
            R.drawable.icon_refresh_12, R.drawable.icon_refresh_13,
            R.drawable.icon_refresh_14, R.drawable.icon_refresh_15, R.drawable.icon_refresh_16};

    public RefreshHeader(Context context){
        this(context,null,null);
    }
    public RefreshHeader(Context context,int[] pullAnimSrcs,int[] refreshAnimSrcs){
        this.context = context;
        if (pullAnimSrcs!=null) this.pullAnimSrcs = pullAnimSrcs;
        if (refreshAnimSrcs!=null) this.refreshAnimSrcs = refreshAnimSrcs;
        animationPull = new AnimationDrawable();
        animationPullFan = new AnimationDrawable();
        animationRefresh = new AnimationDrawable();
        for (int i=1;i< this.pullAnimSrcs.length;i++) {
            animationPull.addFrame(ContextCompat.getDrawable(context, this.pullAnimSrcs[i]),50);
            animationRefresh.setOneShot(true);
        }
        for (int i= this.pullAnimSrcs.length-1;i>=0;i--){
            animationPullFan.addFrame(ContextCompat.getDrawable(context, this.pullAnimSrcs[i]), 50);
            animationRefresh.setOneShot(true);
        }
        for (int src: this.refreshAnimSrcs) {
            animationRefresh.addFrame(ContextCompat.getDrawable(context, src),50);
            animationRefresh.setOneShot(false);
        }
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.refresh_header, viewGroup, true);
        header_img = (ImageView) view.findViewById(R.id.refresh_header_img);
        if (pullAnimSrcs !=null&& pullAnimSrcs.length>0)
            header_img.setImageResource(pullAnimSrcs[0]);
        return view;
    }

    @Override
    public void onPreDrag(View rootView) {
    }

    @Override
    public void onDropAnim(View rootView, int dy) {
        int maxw = DensityUtil.dip2px(context, 40);
        float w = maxw*Math.abs(dy)/rootView.getMeasuredHeight();
        if (w>maxw) return;
        ViewGroup.LayoutParams layoutParams = header_img.getLayoutParams();
        layoutParams.width = (int) w;
        header_img.setLayoutParams(layoutParams);
    }

    @Override
    public void onLimitDes(View rootView, boolean upORdown) {
        if (!upORdown){
            header_img.setImageDrawable(animationPull);
            animationPull.start();
        }else {
            header_img.setImageDrawable(animationPullFan);
            animationPullFan.start();
        }
    }

    @Override
    public void onStartAnim() {
        header_img.setImageDrawable(animationRefresh);
        animationRefresh.start();
    }

    @Override
    public void onFinishAnim() {
        if (pullAnimSrcs !=null&& pullAnimSrcs.length>0)
            header_img.setImageResource(pullAnimSrcs[0]);
    }
}
