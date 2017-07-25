package com.ge.med.mobile.nursing.ui.adapter;

/**
 * Created by Administrator on 2016/11/16.
 */

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.forjson.entity.AssessRecordBean;
import com.ge.med.mobile.nursing.forjson.entity.NetworkForImage;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AssessTopicAnswerItemAdapter extends BaseAdapter {
    private static final int ITEM_TYPE_COUNT = 4;
    private static final int ITEM_TYPE_TOPIC_IMAGE = 0;
    private static final int ITEM_TYPE_ANSWER_TEXTVIEW = 1;
    private static final int ITEM_TYPE_ANSWER_IMAGEVIEW = 2;
    private static final int ITEM_TYPE_TOPIC_EDITTEXT = 3;
    private static final int ITEM_TYPE_TOPIC_TIME = 5;
    private AssessDefine.DataBean.AssessTopicDefineListBean mTopicDefine;
    private AssessRecordBean.AssessTopicRecordListBean mTopicRecord;
    private Context context;
    private LayoutInflater layoutInflater;
    private AssessDefine.DataBean mAssessDefine;
    private List<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean.AssessAnswerTopicRelationListBean> answerTopicRelationList;
    private List<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean.AssessAnswerRelateAnswerListBean> answerRelateAnswerList;

    private List<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean> mAnswerDefineList;


    public List<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean.AssessAnswerRelateAnswerListBean> getAnswerRelateAnswerList() {
        return answerRelateAnswerList;
    }

    public List<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean.AssessAnswerTopicRelationListBean> getAnswerTopicRelationList() {
        return answerTopicRelationList;
    }

    public AssessRecordBean.AssessTopicRecordListBean getmTopicRecord() {
        return mTopicRecord;
    }

    public List<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean> getmAnswerDefineList() {
        return mAnswerDefineList;
    }

    public AssessTopicAnswerItemAdapter(Context context, AssessDefine.DataBean mAssessDefine) {
        this.context = context;
        this.mAssessDefine = mAssessDefine;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public AssessDefine.DataBean.AssessTopicDefineListBean getmTopicDefine() {
        return mTopicDefine;
    }

    public void changeTopic(AssessDefine.DataBean.AssessTopicDefineListBean topicDefine
            , AssessRecordBean.AssessTopicRecordListBean topicRecord) {
        this.mTopicDefine = topicDefine;
        if (mTopicDefine.getAssessAnswerDefineList() == null){
            topicDefine.setAssessAnswerDefineList(new ArrayList<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean>());
        }
        this.mTopicRecord = topicRecord;
        List<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean> answerDefineList = mTopicDefine.getAssessAnswerDefineList();
        mAnswerDefineList = new ArrayList<>();
        for (AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean assessAnswerDefineListBean : answerDefineList) {
            if (assessAnswerDefineListBean.getInitType() == 0) {
                mAnswerDefineList.add(assessAnswerDefineListBean);
            }
        }
        if (mTopicRecord == null) createNewTopic();
        if (mTopicRecord.getAnswerRecordListMap() == null) {
            mTopicRecord.setAnswerRecordListMap(new HashMap<Integer, AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean>());
        }
//        if (mTopicRecord.getAssessAnswerRecordList() == null)
//            mTopicRecord.setAssessAnswerRecordList(new ArrayList<AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean>());
    }

    @Override
    public int getItemViewType(int position) {
        int retval = ITEM_TYPE_TOPIC_EDITTEXT;
        int pos = getRealPosition(position);
        if (mTopicDefine != null) {
            if (mTopicDefine.getImageId() != null) {
                if (position == 0) retval = ITEM_TYPE_TOPIC_IMAGE;
                else if (mTopicDefine.getTopicType() != null && Constant.TOPIC_TYPE_INPUT.equals(mTopicDefine.getTopicType()))
                    retval = ITEM_TYPE_TOPIC_EDITTEXT;
                else {

                    if (mAnswerDefineList.size() > pos) {
                        AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean defineBean = mAnswerDefineList.get(pos);
                        if (defineBean != null && defineBean.getImageId() != null) {
                            retval = ITEM_TYPE_ANSWER_IMAGEVIEW;
                        } else {
                            retval = ITEM_TYPE_ANSWER_TEXTVIEW;
                        }
                    } else if (mAnswerDefineList.size() == pos) {
                        retval = ITEM_TYPE_TOPIC_EDITTEXT;
                    }
                }
            } else if (mTopicDefine.getTopicType() != null && Constant.TOPIC_TYPE_INPUT.equals(mTopicDefine.getTopicType())) {
                retval = ITEM_TYPE_TOPIC_EDITTEXT;
            } else if (mTopicDefine.getTopicType() != null && Constant.TOPIC_TYPE_TIME.equals(mTopicDefine.getTopicType())) {
                retval = ITEM_TYPE_TOPIC_TIME;
            } else {
                if (mAnswerDefineList.size() > pos) {
                    AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean defineBean = mAnswerDefineList.get(pos);
                    if (defineBean != null && defineBean.getImageId() != null) {
                        retval = ITEM_TYPE_ANSWER_IMAGEVIEW;
                    } else {
                        retval = ITEM_TYPE_ANSWER_TEXTVIEW;
                    }
                } else if (mAnswerDefineList.size() == pos) {
                    retval = ITEM_TYPE_TOPIC_EDITTEXT;
                }
            }
        }
        return retval;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_TYPE_COUNT;
    }

    @Override
    public int getCount() {
        int retval = 0;
        if (mTopicDefine != null) {
            if (mTopicDefine.getImageId() != null) {
                retval++;
            }
            if (mTopicDefine.getTopicType() != null && Constant.TOPIC_TYPE_INPUT.equals(mTopicDefine.getTopicType())) {
                retval++;
            } else {
                if (mAnswerDefineList != null) {
                    retval += mAnswerDefineList.size();
                }
                if (showTopicNote()) {
                    retval++;
                }
            }
        }
        return retval;
    }

    private void createNewTopic() {
        mTopicRecord = new AssessRecordBean.AssessTopicRecordListBean();
    }

    private AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean getAnswerDefine(Integer defineId) {
        AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean retval = null;
        if (defineId != null && mTopicDefine != null && mAnswerDefineList != null) {
            for (AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean define : mTopicDefine.getAssessAnswerDefineList()) {
                if (define != null && define.getId() == defineId.intValue()) {
                    retval = define;
                    break;
                }
            }
        }
        return retval;
    }

    private boolean showTopicNote() {
        boolean retval = false;
//        if (mTopicRecord != null && mTopicRecord.getAssessAnswerRecordList() != null && mTopicRecord.getAssessAnswerRecordList().size() > 0) {
        if (mTopicRecord != null && mTopicRecord.getAnswerRecordListMap() != null && mTopicRecord.getAnswerRecordListMap().size() > 0) {
            AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean define = null;
            Iterator iter = mTopicRecord.getAnswerRecordListMap().entrySet().iterator();
            AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean record = null;
            Map.Entry entry = null;
            while (iter.hasNext()) {
                entry = (Map.Entry) iter.next();
                if (entry.getValue() != null) {
                    record = (AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean) entry.getValue();
                    if (record != null && record.getAnswerDefineId() != null) {
                        define = getAnswerDefine(record.getAnswerDefineId());
                        if (define != null && define.getNeedNote() != null && define.getNeedNote().intValue() == Constant.ASSESS_ANSWER_NEED_NOTE) {
                            retval = true;
                            break;
                        }
                    }
                }
            }
//            for (AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean record : mTopicRecord.getAssessAnswerRecordList()) {
//                if (record != null && record.getAnswerDefineId() != null) {
//                    define = getAnswerDefine(record.getAnswerDefineId());
//                    if (define != null && define.getNeedNote() != null && define.getNeedNote().intValue() == Constant.ASSESS_ANSWER_NEED_NOTE) {
//                        retval = true;
//                        break;
//                    }
//                }
//            }
        }
        return retval;
    }

    private int getRealPosition(int position) {
        int retval = position;
        if (mTopicDefine != null) {
            if (mTopicDefine.getImageId() != null) {
                retval = position - 1;
                if (retval < 0) retval = 0;
            }
        }
        return retval;
    }

    @Override
    public Object getItem(int position) {
        Object retval = null;
        switch (getItemViewType(position)) {
            case ITEM_TYPE_ANSWER_TEXTVIEW: {
                int pos = getRealPosition(position);
                if (mTopicDefine != null && mAnswerDefineList != null && mAnswerDefineList.size() > pos) {
                    retval = mAnswerDefineList.get(pos).getValue();
                }
            }
            break;
            case ITEM_TYPE_ANSWER_IMAGEVIEW: {
                int pos = getRealPosition(position);
                if (mTopicDefine != null && mAnswerDefineList != null && mAnswerDefineList.size() > pos) {
                    retval = mAnswerDefineList.get(pos).getImageString();
                }
            }
            break;
            case ITEM_TYPE_TOPIC_EDITTEXT:
                if (mTopicRecord != null) retval = mTopicRecord.getValue();
                break;
            case ITEM_TYPE_TOPIC_IMAGE:
                retval = mTopicDefine.getImageString();
                LogUtil.d("mTopicDefine[id:" + mTopicDefine.getId() + ",imageId:" + mTopicDefine.getImageId() + ",imageString:" + retval + "].");
                break;
            case ITEM_TYPE_TOPIC_TIME:
                retval = ITEM_TYPE_TOPIC_TIME;
                break;
        }
        return retval;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)) {
            case ITEM_TYPE_ANSWER_TEXTVIEW:
                convertView = handleAnswerText(position, convertView, parent);
                break;
            case ITEM_TYPE_ANSWER_IMAGEVIEW:
                convertView = handleAnswerImage(position, convertView, parent);
                break;
            case ITEM_TYPE_TOPIC_EDITTEXT:
                convertView = handleTopicEditText(position, convertView, parent);
                break;
            case ITEM_TYPE_TOPIC_IMAGE:
                convertView = handleTopicImage(position, convertView, parent);
                break;
        }
        return convertView;

    }

    private boolean isAnswerSelected(int position) {
        LogUtil.d("isAnswerSelected(" + position + ") calling...");
        boolean retval = false;
        position = getRealPosition(position);
        if (mTopicDefine != null && mAnswerDefineList != null
                && mAnswerDefineList.size() > position && mAnswerDefineList.get(position) != null) {
//            if (mTopicRecord.getAssessAnswerRecordList() != null && mTopicRecord.getAssessAnswerRecordList().size() > 0) {
//                for (AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean answerRecord : mTopicRecord.getAssessAnswerRecordList()) {
            if (mTopicRecord.getAnswerRecordListMap() != null && mTopicRecord.getAnswerRecordListMap().size() > 0) {
                if (mTopicRecord.getAnswerRecordListMap().get(mAnswerDefineList.get(position).getId()) != null) {
                    retval = true;
                }
//                for (AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean answerRecord : mTopicRecord.getAssessAnswerRecordList()) {
//                    if (answerRecord.getAnswerDefineId() != null
//                            && answerRecord.getAnswerDefineId().intValue() == mAnswerDefineList.get(position).getId()) {
//                        retval = true;
//                        break;
//                    }
//                }
            }
        }
        return retval;
    }

    //    选中或取消选中答案
    private boolean addRemoveAnswer(int defineId) {
        boolean retval = true;
        if (mTopicRecord == null) createNewTopic();
        if (mTopicRecord.getAnswerRecordListMap() == null) {
            mTopicRecord.setAnswerRecordListMap(new HashMap<Integer, AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean>());
        }
//        if (mTopicRecord.getAssessAnswerRecordList() == null)
//            mTopicRecord.setAssessAnswerRecordList(new ArrayList<AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean>());
        AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean answerRecord = null;
//        if (mTopicRecord.getAssessAnswerRecordList().size() > 0) {
//            for (int i = mTopicRecord.getAssessAnswerRecordList().size() - 1; i >= 0; i--) {
//                answerRecord = mTopicRecord.getAssessAnswerRecordList().get(i);
        if (mTopicRecord.getAnswerRecordListMap().get(defineId) != null) {
            retval = false;
            mTopicRecord.getAnswerRecordListMap().remove(defineId);
        }
//                if (answerRecord.getAnswerDefineId().intValue() == defineId) {
//                    retval = false;
//                    mTopicRecord.getAssessAnswerRecordList().remove(answerRecord);
//                    mTopicRecord.getAnswerRecordListMap().remove(answerRecord.getId());
//                }
//            }
//        }
        if (Constant.TOPIC_TYPE_SELECT_SINGLE.equals(mTopicDefine.getTopicType())) {
//            mTopicRecord.getAssessAnswerRecordList().clear();
            mTopicRecord.getAnswerRecordListMap().clear();
            notifyDataSetChanged();
        }
        if (retval) {
            answerRecord = new AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean();
            answerRecord.setAnswerDefineId(defineId);
            answerRecord.setToprecordid(mTopicRecord.getId());
            mTopicRecord.getAnswerRecordListMap().put(defineId, answerRecord);
//            mTopicRecord.getAssessAnswerRecordList().add(answerRecord);
        }
        return retval;
    }

    //答题答案记录
    public boolean clickItem(View view, int position) {
        position = getRealPosition(position);
        boolean b = false;
        if (mTopicDefine != null && mAnswerDefineList != null
                && mAnswerDefineList.size() > position && mAnswerDefineList.get(position) != null) {
            if (mAnswerDefineList.get(position).getRelateTo() == 1) {
                answerTopicRelationList = mAnswerDefineList.get(position).getAssessAnswerTopicRelationList();
            } else {
                answerTopicRelationList = null;
            }
            if (mAnswerDefineList.get(position).getRelateTo() == 2) {
                answerRelateAnswerList = mAnswerDefineList.get(position).getAssessAnswerRelateAnswerList();
            } else {
                answerRelateAnswerList = null;
            }
            b = addRemoveAnswer(mAnswerDefineList.get(position).getId());
            notifyDataSetChanged();
        }
        return b;
    }

    //    处理文字类型答案
    private View handleAnswerText(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.assess_answer_item_text, parent, false);
            convertView.setTag(new AnswerTextViewHolder(convertView));
        }
        if (convertView != null && convertView.getTag() instanceof AnswerTextViewHolder) {
            final AnswerTextViewHolder holder = (AnswerTextViewHolder) convertView.getTag();
            holder.answerText.setVisibility(View.VISIBLE);
            holder.answerText.setText((String) getItem(position));
            setAnswerSelected(holder.answerText, holder.selectImage, position);
        }
        return convertView;
    }

    //    设置图片选中样式
    private void setAnswerSelected(View view, ImageView selectImage, int position) {
        if (isAnswerSelected(position)) {
            selectImage.setVisibility(View.VISIBLE);
            view.setBackgroundResource(R.drawable.yz_hd_miao_bian_shape3);
        } else {
            selectImage.setVisibility(View.GONE);
            view.setBackgroundResource(R.drawable.yz_hd_miao_bian_shape1);
        }
    }

    // 处理图片类型答案
    private View handleAnswerImage(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.assess_answer_item_image, parent, false);
            convertView.setTag(new AnswerImageViewHolder(convertView));
        }
        if (convertView != null && convertView.getTag() instanceof AnswerImageViewHolder) {
            final AnswerImageViewHolder holder = (AnswerImageViewHolder) convertView.getTag();
            holder.answerImage.setVisibility(View.VISIBLE);
            setAnswerSelected(holder.answerImage, holder.selectImage, position);

            setImage(position, holder.answerImage);
            LogUtil.d("setting answer image...");
        }
        return convertView;
    }

    private void setImage(int position, ImageView imgView) {
        String imgUrl = null;
        if (getItem(position) != null) {
            imgUrl = (String) getItem(position);
            LogUtil.d("Image URL is " + imgUrl);
            imgView.setImageBitmap(NetworkForImage.getLoacalBitmap(imgUrl));
//                Glide.with(context)
//                        .load(imgUrl)
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .into(imgView);
        } else LogUtil.e("Image URL is null!");
    }

    private View handleTopicImage(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.assess_topic_item_image, parent, false);
            convertView.setTag(new TopicImageViewHolder(convertView));
        }
        if (convertView != null && convertView.getTag() instanceof TopicImageViewHolder) {
            final TopicImageViewHolder holder = (TopicImageViewHolder) convertView.getTag();
            LogUtil.d("setting topic image...");
            holder.topicImage.setVisibility(View.VISIBLE);
            setImage(position, holder.topicImage);
        }
        return convertView;
    }

    private View handleTopicEditText(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.assess_topic_item_edit, parent, false);
            convertView.setTag(new TopicEditTextHolder(convertView));
        }
        if (convertView != null && convertView.getTag() instanceof TopicEditTextHolder) {
            final TopicEditTextHolder holder = (TopicEditTextHolder) convertView.getTag();
            holder.topicEdit.setVisibility(View.VISIBLE);
            holder.topicEdit.setText(mTopicRecord.getValue());
        }
        return convertView;
    }
//    private void initializeViews(AssessDefineJSONBean.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean entity, final ViewHolder holder, final int position) {
//        holder.tvpg_item_answer.setText(entity.getValue());
//        setAnswerSelected(holder, isAnswerSelected(entity.getId()));
//    }


    protected class TopicImageViewHolder {
        private ImageView topicImage;

        public TopicImageViewHolder(View view) {
            topicImage = (ImageView) view.findViewById(R.id.assess_topic_image_iv);
        }
    }

    protected class TopicEditTextHolder {
        private EditText topicEdit;

        public TopicEditTextHolder(View view) {
            topicEdit = (EditText) view.findViewById(R.id.hz_pg_dt_topic_input);
            topicEdit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    mTopicRecord.setValue(s.toString());
                }
            });
        }
    }

    protected class AnswerImageViewHolder {
        private ImageView answerImage;
        private ImageView selectImage;

        public AnswerImageViewHolder(View view) {
            answerImage = (ImageView) view.findViewById(R.id.assess_question_image_iv);
            selectImage = (ImageView) view.findViewById(R.id.assess_question_select_image1);
        }
    }

    protected class AnswerTextViewHolder {
        private TextView answerText;
        private ImageView selectImage;

        public AnswerTextViewHolder(View view) {
            answerText = (TextView) view.findViewById(R.id.assess_answer_text_tv);
            selectImage = (ImageView) view.findViewById(R.id.assess_question_select_image2);
        }
    }
}
