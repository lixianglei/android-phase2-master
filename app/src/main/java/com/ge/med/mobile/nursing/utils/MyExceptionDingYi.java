package com.ge.med.mobile.nursing.utils;

import android.view.View;
import android.widget.CheckBox;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.db.DBExceptionConfig;
import com.ge.med.mobile.nursing.db.DBExceptionDefine;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */
public class MyExceptionDingYi {
    public static void setZhuShe_NOExce(List<CheckBox> checkBoxes,String yzType) {
        List<DBExceptionDefine> dbExceptionDefines1 = new ArrayList<>();
        List<DBExceptionDefine> dbExceptionDefines2 = new ArrayList<>();
        //从数据库获取异常定义
        List<DBExceptionConfig> dbExceptionConfigs = DataSupport.where("ordertype = ?", yzType).find(DBExceptionConfig.class);
        for (DBExceptionConfig config : dbExceptionConfigs) {
            if (config != null) {
                if (Constant.YZ_TYPE_WEIZHIXING.equals(config.getOrderstatus())) {
                    DBExceptionDefine first = DataSupport.where("dbexceptionid = ?", config.getExceptiondefineid() + "").findFirst(DBExceptionDefine.class);
                    if (first != null) {
                        if (first.getType().equals("0")) {
                            dbExceptionDefines1.add(first);
                        } else {
                            dbExceptionDefines2.add(first);
                        }
                    }
                }
            }
        }
        Integer exceptiondefineid = null;
        DBExceptionDefine first = null;
        if (dbExceptionDefines1.size() <= 6) {
            try {
                //第一个
                first = dbExceptionDefines1.get(0);
                if (first != null) {
                    checkBoxes.get(0).setText(first.getExceptionname());
                } else {
                    checkBoxes.get(0).setText("");
                }
                //第二个
                first = dbExceptionDefines1.get(1);
                if (first != null) {
                    checkBoxes.get(1).setText(first.getExceptionname());
                } else {
                    checkBoxes.get(1).setText("");
                }
                //第三个
                first = dbExceptionDefines1.get(2);
                if (first != null) {
                    checkBoxes.get(2).setText(first.getExceptionname());
                } else {
                    checkBoxes.get(2).setText("");
                }

                //第4个
                first = dbExceptionDefines1.get(3);
                if (first != null) {
                    checkBoxes.get(9).setText(first.getExceptionname());
                } else {
                    checkBoxes.get(9).setText("");
                }
                //第5个
                first = dbExceptionDefines1.get(4);
                if (first != null) {
                    checkBoxes.get(10).setText(first.getExceptionname());
                } else {
                    checkBoxes.get(10).setText("");
                }
                //第6个
                first = dbExceptionDefines1.get(5);
                if (first != null) {
                    checkBoxes.get(11).setText(first.getExceptionname());
                } else {
                    checkBoxes.get(11).setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            checkBoxes.get(3).setVisibility(View.GONE);
            checkBoxes.get(4).setVisibility(View.GONE);
            checkBoxes.get(5).setVisibility(View.GONE);
            checkBoxes.get(6).setVisibility(View.GONE);
            checkBoxes.get(7).setVisibility(View.GONE);
            checkBoxes.get(8).setVisibility(View.GONE);
        }

        if (dbExceptionDefines1.size() > 6 && dbExceptionDefines1.size() <= 9) {
            try {
                //第一个
                first = dbExceptionDefines1.get(0);
                if (first != null) {
                    checkBoxes.get(0).setText(first.getExceptionname());
                } else {
                    checkBoxes.get(0).setText("");
                }
                //第二个
                first = dbExceptionDefines1.get(1);
                if (first != null) {
                    checkBoxes.get(1).setText(first.getExceptionname());
                } else {
                    checkBoxes.get(1).setText("");
                }
                //第三个
                first = dbExceptionDefines1.get(2);
                if (first != null) {
                    checkBoxes.get(2).setText(first.getExceptionname());
                } else {
                    checkBoxes.get(2).setText("");
                }
                //第4个
                first = dbExceptionDefines1.get(3);
                if (first != null) {
                    checkBoxes.get(3).setText(first.getExceptionname());
                } else {
                    checkBoxes.get(3).setText("");
                }
                //第5个
                first = dbExceptionDefines1.get(4);
                if (first != null) {
                    checkBoxes.get(4).setText(first.getExceptionname());
                } else {
                    checkBoxes.get(4).setText("");
                }
                //第6个
                first = dbExceptionDefines1.get(5);
                if (first != null) {
                    checkBoxes.get(5).setText(first.getExceptionname());
                } else {
                    checkBoxes.get(5).setText("");
                }

                //第4个
                first = dbExceptionDefines1.get(6);
                if (first != null) {
                    checkBoxes.get(9).setText(first.getExceptionname());
                } else {
                    checkBoxes.get(9).setText("");
                }
                //第5个
                first = dbExceptionDefines1.get(7);
                if (first != null) {
                    checkBoxes.get(10).setText(first.getExceptionname());
                } else {
                    checkBoxes.get(10).setText("");
                }
                //第6个
                first = dbExceptionDefines1.get(8);
                if (first != null) {
                    checkBoxes.get(11).setText(first.getExceptionname());
                } else {
                    checkBoxes.get(11).setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            checkBoxes.get(6).setVisibility(View.GONE);
            checkBoxes.get(7).setVisibility(View.GONE);
            checkBoxes.get(8).setVisibility(View.GONE);
        }
        if (dbExceptionDefines1.size() > 9) {
            try {
                for (int i = 0; i < 12; i++) {
                    first = dbExceptionDefines1.get(i);
                    if (first != null) {
                        checkBoxes.get(i).setText(first.getExceptionname());
                    } else {
                        checkBoxes.get(i).setText("");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Integer index=null;
        int j = 0;
       for(DBExceptionDefine dbExceptionDefine:dbExceptionDefines2){
           if(dbExceptionDefine!=null){
               if("其他".equals(dbExceptionDefine.getExceptionname())){
                   dbExceptionDefines2.add(dbExceptionDefines2.size()-1, dbExceptionDefines2.remove(dbExceptionDefines2.indexOf(dbExceptionDefine)));
                   break;
               }
           }

       }

        try {
            for (int i = 12; i < 18; i++) {
                first = dbExceptionDefines2.get(j);
                j++;
                if (first != null) {
                    checkBoxes.get(i).setText(first.getExceptionname());
                } else {
                    checkBoxes.get(i).setText("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
