package com.ge.med.mobile.nursing.ui.fragment;


import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sj.library.base.BaseFragment;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.db.DBAssess;
import com.ge.med.mobile.nursing.db.DBAssessDefine;
import com.ge.med.mobile.nursing.db.DBUser;
import com.ge.med.mobile.nursing.forjson.entity.AssessRecordBean;
import com.ge.med.mobile.nursing.forjson.entity.QuestionFinishBean;
import com.ge.med.mobile.nursing.ui.activity.HZActivity;
import com.ge.med.mobile.nursing.ui.activity.MainActivity;
import com.ge.med.mobile.nursing.ui.activity.PG_DTQuestionActivity;
import com.ge.med.mobile.nursing.ui.activity.PG_ZJMActivity;
import com.ge.med.mobile.nursing.ui.adapter.HzLspgLvItemAdapter;
import com.ge.med.mobile.nursing.ui.view.dialog.ConfirmDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 评估界面
 */
public class PGFragment extends BaseFragment {
    private TextView mXinJianPgTv;
    private TextView mHisAssessLabel;
    private ListView mHzLspgLv;
    public HzLspgLvItemAdapter hzLspgLvItemAdapter;
    private List<AssessRecordBean> hisAssessList;
    private HZActivity hzActivity;
    // 完成情况列表
    private List<QuestionFinishBean> finishList;


    @Override
    public int setRootView() {
        return R.layout.fragment_pg;
    }

    @Override
    public void initViews() {
        hzActivity = (HZActivity) mActivitySelf;
        mXinJianPgTv = (TextView) mRootView.findViewById(R.id.xin_jian_pg_tv);
        mHzLspgLv = (ListView) mRootView.findViewById(R.id.hz_lspg_lv);
        mHisAssessLabel = (TextView) mRootView.findViewById(R.id.history_pg_tv_id);
        hzLspgLvItemAdapter = new HzLspgLvItemAdapter(mActivitySelf);
        if (hisAssessList != null) {
            hzLspgLvItemAdapter.setAssessList(hisAssessList);
        }
        mHzLspgLv.setAdapter(hzLspgLvItemAdapter);
        mHzLspgLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AssessRecordBean assess = hisAssessList.get(position);
                String userType = MainActivity.userCache.get("usertype");
                // 评估护士的id
                int reocrdUserId = assess.getUserId();
                String reocrdUserIdStr = String.valueOf(reocrdUserId);
                // 当前使用移动终端的护士的id
                String userId = MainActivity.userCache.get("userid");
                if (userId != null && userType != null) {
                    if (!reocrdUserIdStr.equals(userId) && !userType.equals("1")) {
                        Toast.makeText(hzActivity, "只有评估的创建者以及护士长才能修改", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    return;
                }

                String status = assess.getStatus();
                if (status != null && !status.equals("")) {
                    // 孕妇，儿童，残疾人的特殊评估记录
                    if (status.equals("9")) {
                        // 跳转到措施界面
                        getBundle().putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.BUNDLE_KEY_ACTIVITY_TYPE_HOUJI_CUOSHI);
                        getBundle().putString(Constant.BUNDLE_KEY_FRAGMENT_TYPE, Constant.BUNDLE_KEY_ACTIVITY_TYPE_HOUJI_CUOSHI);
                        getBundle().putSerializable(Constant.BUNDLE_KEY_ASSESSDEFINE, new AssessDaoImpl().getAssessDefineById(hisAssessList.get(position).getAssessDefineId()));
                        getBundle().putSerializable(Constant.BUNDLE_KEY_ASSESS, assess);
                        goToActivity(PG_ZJMActivity.class, getBundle());
                    } else {
                        getBundle().putSerializable(Constant.BUNDLE_KEY_ASSESSDEFINE, new AssessDaoImpl().getAssessDefineById(hisAssessList.get(position).getAssessDefineId()));
                        getBundle().putSerializable(Constant.BUNDLE_KEY_ASSESS, assess);
                        goToActivity(PG_DTQuestionActivity.class, getBundle());
                    }
                }
               /* if (Constant.ASSESS_STATUS_SAVE.equals(assess.getStatus())) {
                    showToastShort("评估未完成！");
                    getBundle().putSerializable(Constant.BUNDLE_KEY_ASSESSDEFINE
                            , new AssessDaoImpl().getAssessDefineById(hisAssessList.get(position).getAssessDefineId()));
                    getBundle().putSerializable(Constant.BUNDLE_KEY_ASSESS, assess);
                    goToActivity(PG_DTQuestionActivity.class, getBundle());
                } else if (Constant.ASSESS_STATUS_FINISHED.equals(assess.getStatus())) {
                    Integer id1 = assess.getId();
                    DBAssessMeasureRecords first = null;
                    if (id1 != null) {
                        first = DataSupport.where("assessRecordId = ?", id1 + "").findFirst(DBAssessMeasureRecords.class);
                    }
                    if (first != null) {
                        if (hzActivity.getUserid().equals(assess.getUserId() + "")) {
                            getBundle().putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.BUNDLE_KEY_ACTIVITY_TYPE_HOUJI_CUOSHI);
                            getBundle().putString(Constant.BUNDLE_KEY_FRAGMENT_TYPE, Constant.BUNDLE_KEY_ACTIVITY_TYPE_HOUJI_CUOSHI);
                            getBundle().putSerializable(Constant.BUNDLE_KEY_ASSESSDEFINE
                                    , new AssessDaoImpl().getAssessDefineById(hisAssessList.get(position).getAssessDefineId()));
                            getBundle().putSerializable(Constant.BUNDLE_KEY_ASSESS, assess);
                            goToActivity(PG_ZJMActivity.class, getBundle());
                        } else {
                            showToastShort("与评估创人不符,无法执行后继措施!");
                        }
                    } else {
                        showToastShort("评估已经完成");
                        if (hzActivity.getUserid().equals(assess.getUserId() + "")) {
                            getBundle().putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.BUNDLE_KEY_ACTIVITY_TYPE_HOUJI_CUOSHI);
                            getBundle().putString(Constant.BUNDLE_KEY_FRAGMENT_TYPE, Constant.BUNDLE_KEY_ACTIVITY_TYPE_HOUJI_CUOSHI);
                            getBundle().putSerializable(Constant.BUNDLE_KEY_ASSESSDEFINE
                                    , new AssessDaoImpl().getAssessDefineById(hisAssessList.get(position).getAssessDefineId()));
                            getBundle().putSerializable(Constant.BUNDLE_KEY_ASSESS, assess);
                            goToActivity(PG_ZJMActivity.class, getBundle());
                        } else {
                            showToastShort("与评估创人不符,无法执行后继措施!");
                        }
                    }
                } else {
                    showToastShort("评估状态异常！");
                }*/

            }
        });

        // 长按删除
        mHzLspgLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                // 只有本人和护士长才能删除
                // 判断是否是护士长
                String userType = MainActivity.userCache.get("usertype");
                // 判断是否是本人
                AssessRecordBean record = hisAssessList.get(position);
                // 评估护士的id
                int reocrdUserId = record.getUserId();
                String reocrdUserIdStr = String.valueOf(reocrdUserId);
                // 当前使用移动终端的护士的id
                String userId = MainActivity.userCache.get("userid");
                if (userId != null && userType != null) {
                    if (!reocrdUserIdStr.equals(userId) && !userType.equals("1")) {
                        Toast.makeText(hzActivity, "只有评估的创建者以及护士长才能删除", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                } else {
                    return true;
                }
                // 弹出提示框是否确认删除
                ConfirmDialog confirmDialog = new ConfirmDialog(hzActivity, "确认要删除该条记录吗?");
                confirmDialog.showPopupWindow();
                confirmDialog.setListener(new ConfirmDialog.ClickListener() {
                    @Override
                    public void clickOk() {
                        if (hisAssessList.get(position).getId() != null) {
                            // 调用删除该条记录的接口
                            deleteAssessRecord(hisAssessList.get(position).getId());
                            // 删除数据库中的内容
                            DataSupport.deleteAll(DBAssess.class, "assessdefineid = ? and creationtime = ?", String.valueOf(hisAssessList.get(position).getAssessDefineId()), String.valueOf(hisAssessList.get(position).getCreationTime()));
                            // 在界面上删除
                            hisAssessList.remove(position);
                            finishList.remove(position);
                            hzLspgLvItemAdapter.notifyDataSetChanged();
                        }
                    }
                });
                return true;
            }
        });


        mXinJianPgTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getBundle() != null) {
                    showToastShort("新建评估");
                    getBundle().remove(Constant.BUNDLE_KEY_ASSESS);
                    getBundle().remove(Constant.BUNDLE_KEY_FRAGMENT_TYPE);
                    getBundle().remove(Constant.BUNDLE_KEY_ACTIVITY_TYPE);
                    goToActivity(PG_ZJMActivity.class, getBundle());
                }
            }
        });
        showHideSomething();
        // 隐藏评估标签
        hzActivity.labels_layout.setVisibility(View.GONE);
    }

    /**
     * 删除评估记录
     */
    private void deleteAssessRecord(int assessRecordId) {
        // 从数据库中获取用户名
        DBUser user = DataSupport.findFirst(DBUser.class);
        OkHttpUtils
                .get()
                .addHeader("User-Agent", "www.gs.com")
                .url(URL.URL + "/assess/deleteAssessRecord")
                .addParams("assessRecordId", String.valueOf(assessRecordId))
                .addParams("userName", user.getUsername())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("assess_delete_record", response);
                    }
                });
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }


    public void setHisAssessList(List<AssessRecordBean> hisAssessList) {
        LogUtil.d("PGFragment>setHisAssessList calling... size:" + (hisAssessList == null ? "null" : hisAssessList.size()));
        this.hisAssessList = hisAssessList;
        if (null != hzLspgLvItemAdapter) {
            hzLspgLvItemAdapter.setAssessList(hisAssessList);

            hzLspgLvItemAdapter.notifyDataSetChanged();
        }
        showHideSomething();
    }

    private void showHideSomething() {
        if (mHisAssessLabel == null) return;
        if (hisAssessList == null || hisAssessList.size() <= 0) {
            mHisAssessLabel.setVisibility(View.INVISIBLE);
        } else {
            mHisAssessLabel.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 完成情况
     */
    public void calFinishInfo() {
        finishList = new ArrayList<>();
        if (hisAssessList.size() > 0) {
            for (int i = 0; i < hisAssessList.size(); i++) {
                AssessRecordBean record = hisAssessList.get(i);
                // 获取assessDefinedId
                int assessDefineId = record.getAssessDefineId();
                // 从数据库中查找总题数
                List<DBAssessDefine> totalNoList = DataSupport.select("topictotalno").where("assessdefineid = ?", String.valueOf(assessDefineId)).find(DBAssessDefine.class);
                if (totalNoList != null && totalNoList.size() > 0) {
                    // 获得总题数
                    int totalNo = totalNoList.get(0).getTopictotalno();
                    // 获得已完成的数量
                    int finishNo = getFinishNum(record);
                    QuestionFinishBean entity = new QuestionFinishBean();
                    entity.setTotalNum(totalNo);
                    entity.setFinishNum(finishNo);
                    finishList.add(entity);
                }
            }
        }
        if (hzLspgLvItemAdapter != null) {
            hzLspgLvItemAdapter.setFinishList(finishList);
            hzLspgLvItemAdapter.notifyDataSetChanged();
        }
    }


    /**
     * 算完成的题数
     */
    private int getFinishNum(AssessRecordBean data2) {
        int count = 0;
        List<AssessRecordBean.AssessTopicRecordListBean> recordList = data2.getAssessTopicRecordList();
        if (recordList.size() > 0) {
            for (int i = 0; i < recordList.size(); i++) {
                if (recordList.get(i).getValue() != null && !recordList.get(i).getValue().equals("")) {
                    count++;
                } else {
                    List<AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean> list = recordList.get(i).getAssessAnswerRecordList();
                    if (list != null && list.size() > 0) {
                        if (list.get(0).getAnswerDefineId() != null) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
