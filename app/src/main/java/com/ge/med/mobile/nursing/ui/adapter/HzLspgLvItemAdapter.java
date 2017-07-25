package com.ge.med.mobile.nursing.ui.adapter;

/**
 * Created by Administrator on 2016/11/11.
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.forjson.entity.AssessRecordBean;
import com.ge.med.mobile.nursing.forjson.entity.QuestionFinishBean;
import com.ge.med.mobile.nursing.ui.activity.AssessmentPreviewActivity;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 患者历史评估列表适配器
 */
public class HzLspgLvItemAdapter extends BaseAdapter {

    private List<AssessRecordBean> mAssessList = new ArrayList<>();
    // 完成情况列表
    private List<QuestionFinishBean> finishList = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;


    public HzLspgLvItemAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int count = mAssessList == null ? 0 : mAssessList.size();
        LogUtil.d("HzLspgLvItemAdapter.getCount() return " + count);
        return count;
    }

    @Override
    public AssessRecordBean getItem(int position) {
        return mAssessList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.hz_lspg_lv_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((AssessRecordBean) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(final AssessRecordBean object, ViewHolder holder, int position) {
        if (object == null || holder == null) return;
        if (holder.xsdrTitleTv != null)
            holder.xsdrTitleTv.setText("护士：" + (object.getUserName() == null ? "未设护士" : object.getUserName()) + " "
                    + (object.getLastUpdateTime() == null ? "未设时间" : DateUtils.getDateString("yyyy-MM-dd HH:mm", object.getLastUpdateTime())));
        if (holder.xsdrNeiRongTv != null && holder.xsdrJieGuoTv != null) {
            holder.xsdrNeiRongTv.setText(object.getDescription());
            if (object.getDescription().equals("入院评估")) {
                holder.xsdrDeFenTv.setText("评估得分：");
                holder.xsdrJieGuoTv.setText("评估结果：");
            } else {
                holder.xsdrDeFenTv.setText("评估得分：" + object.getScore());
                holder.xsdrJieGuoTv.setText("评估结果：" + object.getResultDescription());
            }
        }
        if (holder.huLiJiBieIconImgv != null && object.getPatientId() != null) {
            final DBHuanZheLieBiao huanZheLieBiao = AdapterUtil.getPatientFromDB(object.getPatientId());
            if (huanZheLieBiao != null)
                holder.huLiJiBieIconImgv.setImageResource(AdapterUtil.findCareLevelImage(huanZheLieBiao.getCarelevel()));
        }
        if (object.getStatus() != null) {
            if (object.getStatus().equals("9")) {
                holder.tv_finish.setVisibility(View.GONE);
            } else {
                holder.tv_finish.setVisibility(View.VISIBLE);
                if (finishList != null && finishList.size() > 0 && finishList.size() > position) {
                    holder.tv_finish.setText("已完成" + finishList.get(position).getFinishNum() + "道题/共" + finishList.get(position).getTotalNum() + "道题");
                } else {
                    holder.tv_finish.setText("");
                }
            }
        }
        // 预览按钮点击事件
        holder.img_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到预览界面
                Intent intent = new Intent(context, AssessmentPreviewActivity.class);
                // 传递assessdefine表中的id
                intent.putExtra("AssessDefineId", String.valueOf(object.getAssessDefineId()));
                // 传递创建时间
                intent.putExtra("CreationTime", String.valueOf(object.getCreationTime()));
                context.startActivity(intent);
            }
        });

    }

    public List<AssessRecordBean> getAssessList() {
        return mAssessList;
    }

    public void setAssessList(List<AssessRecordBean> mAssessList) {
        LogUtil.d("setAssessList(" + (mAssessList == null ? "null" : mAssessList.size()) + ") in HzLspgLvItemAdapter.java.");
        if (mAssessList == null) this.mAssessList = new ArrayList();
        else this.mAssessList = mAssessList;
        Collections.sort(this.mAssessList, new Comparator<AssessRecordBean>() {
            @Override
            public int compare(AssessRecordBean lhs, AssessRecordBean rhs) {
                if (lhs == null || rhs == null || lhs.getLastUpdateTime() == null || rhs.getLastUpdateTime() == null)
                    return 0;
                if (lhs.getLastUpdateTime().longValue() > rhs.getLastUpdateTime().longValue())
                    return -1;
                else if (lhs.getLastUpdateTime().longValue() < rhs.getLastUpdateTime().longValue())
                    return 1;
                return 0;
            }
        });
    }

    public void setFinishList(List<QuestionFinishBean> finishList) {
        this.finishList.addAll(finishList);
    }

    protected class ViewHolder {
        private TextView xsdrTitleTv;
        private TextView xsdrNeiRongTv;
        private TextView xsdrDeFenTv;
        private TextView xsdrJieGuoTv;
        private ImageView huLiJiBieIconImgv;
        // 评估预览
        private ImageView img_preview;
        // 完成进度
        private TextView tv_finish;

        public ViewHolder(View view) {
            xsdrTitleTv = (TextView) view.findViewById(R.id.xsdr_title_tv);
            xsdrNeiRongTv = (TextView) view.findViewById(R.id.xsdr_nei_rong_tv);
            xsdrDeFenTv = (TextView) view.findViewById(R.id.xsdr_de_fen_tv);
            xsdrJieGuoTv = (TextView) view.findViewById(R.id.xsdr_jie_guo_tv);
            huLiJiBieIconImgv = (ImageView) view.findViewById(R.id.hu_li_ji_bie_icon_imgv);
            img_preview = (ImageView) view.findViewById(R.id.img_preview);
            tv_finish = (TextView) view.findViewById(R.id.tv_finish);
        }
    }
}

