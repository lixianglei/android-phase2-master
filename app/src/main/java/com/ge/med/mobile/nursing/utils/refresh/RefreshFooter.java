package com.ge.med.mobile.nursing.utils.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ge.med.mobile.nursing.R;

/**
 * Created by shy on 2016/10/25.
 */
public class RefreshFooter extends BaseFooter {

    private AnimationDrawable animationLoading;

    private Context context;
    private ImageView footer_img;
    private int[] loadingAnimSrcs = new int[]{R.drawable.icon_refresh_1, R.drawable.icon_refresh_2, R.drawable.icon_refresh_3, R.drawable.icon_refresh_4,
            R.drawable.icon_refresh_5,
            R.drawable.icon_refresh_6, R.drawable.icon_refresh_7, R.drawable.icon_refresh_8, R.drawable.icon_refresh_9, R.drawable.icon_refresh_10,
            R.drawable.icon_refresh_11,
            R.drawable.icon_refresh_12, R.drawable.icon_refresh_13, R.drawable.icon_refresh_14, R.drawable.icon_refresh_15, R.drawable.icon_refresh_16};

    public RefreshFooter(Context context){
        this(context,null);
    }
    public RefreshFooter(Context context,int[] loadingAnimSrcs){
        this.context = context;
        if (loadingAnimSrcs!=null) this.loadingAnimSrcs = loadingAnimSrcs;
        animationLoading = new AnimationDrawable();
        for (int src: this.loadingAnimSrcs) {
            animationLoading.addFrame(ContextCompat.getDrawable(context, src),50);
            animationLoading.setOneShot(false);
        }
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.refresh_footer, viewGroup, true);
        footer_img = (ImageView) view.findViewById(R.id.refresh_footer_img);
        if (animationLoading!=null)
            footer_img.setImageDrawable(animationLoading);
        return view;
    }

    @Override
    public void onPreDrag(View rootView) {
        animationLoading.stop();
        if (animationLoading!=null && animationLoading.getNumberOfFrames()>0)
            footer_img.setImageDrawable(animationLoading.getFrame(0));
    }

    @Override
    public void onDropAnim(View rootView, int dy) {
    }

    @Override
    public void onLimitDes(View rootView, boolean upORdown) {
    }

    @Override
    public void onStartAnim() {
        if (animationLoading!=null)
            footer_img.setImageDrawable(animationLoading);
        animationLoading.start();
    }

    @Override
    public void onFinishAnim() {
        animationLoading.stop();
        if (animationLoading!=null && animationLoading.getNumberOfFrames()>0)
            footer_img.setImageDrawable(animationLoading.getFrame(0));
    }
}
