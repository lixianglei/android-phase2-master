package com.ge.med.mobile.nursing;

import android.os.Environment;

/**
 * Created by Administrator on 2016/11/3.
 */
public class Constant {
    //设置检验类医嘱 是否必须按顺序执行
    public static final Boolean JIAN_YAN_SHUNXU = true;
    //设置多长时间自动登录
    public static final int TIMING = 1000000;
    //设置网络访问返回
    public static final String NETWORK_MSG_OK = "成功";
    public static final String NETWORK_MSG_FAILURE = "失败";

    public static final String PATIENT_GROUP_ALL = "全部患者";
    public static final String PATIENT_GROUP_MINE = "我的患者";
    public static final String SORT_BY_ASC = "asc";
    public static final String SORT_BY_DESC = "desc";

    // 设置护理级别常量
    public static final String CARE_LEVEL_TEZHI = "特级";
    public static final String CARE_LEVEL_YIZHI = "一级";
    public static final String CARE_LEVEL_ERZHI = "二级";
    public static final String CARE_LEVEL_SANZHI = "三级";

    //设置疾病 情况
    public static final String ILLNESS_STATE_NAME_GENERAL = "一般";
    public static final String ILLNESS_STATE_NAME_HEAVY = "病重";
    public static final String ILLNESS_STATE_NAME_FATAL = "病危";
    //长期 临时 立即
    public static final String ORDER_DURATION_LINGSHI_0 = "0";
    public static final String ORDER_DURATION_CHANGQI_1 = "1";
    public static final String ORDER_DURATION_LIJI_2 = "2";
    public static final String ORDER_DURATION_LINGSHI = "临";
    public static final String ORDER_DURATION_CHANGQI = "长";
    public static final String ORDER_DURATION_LIJI = "立";

    //设置全局变量Key值
    public static final String GLOBAL_KEY_DATA = "data";
    public static final String GLOBAL_FILENAME_USER = "user";
    public static final String GLOBAL_FILENAME_CACHE = "cache";
    public static final String GLOBAL_KEY_IS_CACHE_ASSESSDEFINE = "cache_assess_define";
    public static final String GLOBAL_KEY_IS_CACHE_VITALDEFINE = "cache_vitalDefine";
    public static final String GLOBAL_KEY_IS_CACHE_EXCEPTIONDEFINE = "cache_exceptionDefine";
    public static final String GLOBAL_KEY_IS_CACHE_RISKDEFINE = "cache_riskDefine";
    public static final String GLOBAL_KEY_IS_CACHE_NUESINGEVENTTEMP = "cache_nursingEventTemp";
    public static final String GLOBAL_KEY_IS_CACHE_NUESINGEV_LIEBIAO = "cache_nursingLieBiao";
    public static final String GLOBAL_KEY_IS_CACHE_XUANJIAO = "cache_xuanjiao";
    public static final String GLOBAL_KEY_IS_CACHE_HOUQI_CUOSHI = "cache_hou_qi_cuo_shi";
    public static final String GLOBAL_KEY_IS_CACHE_USER = "cache_user";
    public static final String GLOBAL_KEY_USER_ID = "userid";
    public static final String GLOBAL_KEY_USER_ID1 = "userId";
    public static final String GLOBAL_KEY_USER_ACCOUND = "username";
    public static final String GLOBAL_KEY_HOSPITAL_NAME = "hospitalname";
    public static final String GLOBAL_KEY_USER_LEVEL = "userlevel";
    public static final String GLOBAL_KEY_IS_PLAYSOUND = "isPlaySound";
    public static final String GLOBAL_KEY_IS_VIBRATE = "isVibrate";
    public static final String GLOBAL_KEY_IS_FIRST = "isFirst";
    public static final String GLOBAL_KEY_IS_CACHE = "isCache";
    public static final String GLOBAL_KEY_USER_NAME = "userdisplayname";
    public static final String GLOBAL_KEY_WARD_ID = "wardId";
    public static final String GLOBAL_KEY_DAYS = "days";
    public static final String GLOBAL_KEY_DATE = "date";
    public static final String GLOBAL_KEY_ID = "id";
    public static final String GLOBAL_KEY_FLAG = "flag";
    public static final String GLOBAL_KEY_ORDER_TYPE = "ordertype";
    public static final String GLOBAL_KEY_ORDER_STATUS = "status";
    public static final String GLOBAL_KEY_WARD_NAME = "WardName";
    public static final String GLOBAL_KEY_TOKEN = "Token";
    public static final String GLOBAL_KEY_PATIENT_ID = "patientId";
    public static final String GLOBAL_KEY_SUBMIT_JSON = "jsonString";
    public static final String GLOBAL_KEY_SUBMIT_JSON_VITAL = "vitalSignJson";
    public static final String GLOBAL_KEY_SHEET_ID = "sheetid";
    public static final String GLOBAL_KEY_EMPNO = "empNo";
    public static final String GLOBAL_KEY_IP = "ip";
    public static final String GLOBAL_KEY_PORT = "port";
    public static final String GLOBAL_KEY_PROGRESS = "progress";
    public static final String GLOBAL_KEY_NEED_RETURN = "needReturn";
    public static final String GLOBAL_VALUE_NEED_RETURN = "1";
    //体温体征标识
    public static final String VITAL_SIGN_TYPE_TEMPERATURE = "V001";
    public static final String VITAL_SIGN_TYPE_HIGH_BLOOD_PRESSURE = "V005";
    public static final String VITAL_SIGN_TYPE_LOW_BLOOD_PRESSURE = "V006";
    //疼痛体征标识
    public static final String VITAL_SIGN_TYPE_PAIN = "V021";
    //体征键盘类型定义
    public static final String VITAL_INPUT_TYPE_NUMBER = "0";
    public static final String VITAL_INPUT_TYPE_HOT = "1";
    public static final String VITAL_INPUT_TYPE_EDITABLE_HOT = "2";
    public static final String VITAL_INPUT_TYPE_NUMBER_RANGE = "3";
    public static final String VITAL_INPUT_TYPE_NOTE = "4";
    public static final String VITAL_IS_NUMBER_YES = "1";
    public static final String VITAL_KEYBOARD_BUTTON_BACK = "退格键";
    public static final int VITAL_NOTE_TYPE_HANDLE = 1;
    public static final String VITAL_VALUE_SPLIT_CHAR = "-";

    //皮试结果 阴
    public static final String SKIN_RESULT_YIN = "0";
    public static final String SKIN_RESULT_NAME_YIN = " 阴性";
    //皮试结果 阳
    public static final String SKIN_RESULT_YANG = "1";
    public static final String SKIN_RESULT_NAME_YANG = " 阳性";


    //设置医嘱类型：输液类
    public static final String TYPE_YZ_SHUYE = "3";
    //设置医嘱类型：输血类
    public static final String TYPE_YZ_SHUXUE = "4";
    //设置医嘱类型：注射类
    public static final String TYPE_YZ_ZHUSHE = "0";
    //设置医嘱类型：口服药类
    public static final String TYPE_YZ_KOUFU = "1";
    //设置医嘱类型：检验类
    public static final String TYPE_YZ_JIANYAN = "2";
    //设置医嘱类型：辅助治疗类
    public static final String TYPE_YZ_FUZHU = "5";
    //设置医嘱类型：皮试类
    public static final String TYPE_YZ_PISHI = "6";
    //设置医嘱类型：肌肉注射
    public static final String TYPE_YZ_JIROUZHUSHE = "10";
    //设置医嘱类型：皮下注射
    public static final String TYPE_YZ_PIXIAZHUSHE = "11";
    //设置医嘱类型：静脉注射
    public static final String TYPE_YZ_JINGMAIZHUSHE = "12";
    //设置医嘱类型：膳食
    public static final String TYPE_YZ_SHANSHI = "13";
    //设置医嘱类型：治疗
    public static final String TYPE_YZ_ZHILIAO = "14";
    //设置医嘱类型：检验 血
    public static final String TYPE_YZ_JIANYAN_BLOOD = "15";
    //设置医嘱类型：检验 非血
    public static final String TYPE_YZ_JIANYAN_UN_BLOOD = "16";
    //设置医嘱类型：护理
    public static final String TYPE_YZ_HULI = "17";
    //设置医嘱类型：入壶
    public static final String TYPE_YZ_RUHU = "18";
    //设置医嘱类型：采血
    public static final String TYPE_YZ_CAIXUE = "19";


    //医嘱执行记录 jobtype 摆药
    public static final String YZ_EXECUTE_JOBTYPE_BAIYAO = "0";
    //医嘱执行记录 jobtype 配液
    public static final String YZ_EXECUTE_JOBTYPE_PEIYE = "1";
    //医嘱执行记录 jobtype 取血
    public static final String YZ_EXECUTE_JOBTYPE_QUXUE = "2";
    //医嘱执行记录 jobtype 待核对
    public static final String YZ_EXECUTE_JOBTYPE_HEDUI = "3";
    //设置执行记录type 巡视
    public static final String YZ_EXECUTE_JOBTYPE_XUNSHI = "4";
    //设置执行记录type 换液
    public static final String YZ_EXECUTE_JOBTYPE_HUANYE = "5";
    //设置执行记录type 执行
    public static final String YZ_EXECUTE_JOBTYPE_ZHIXING = "6";
    //设置执行记录type 采血
    public static final String YZ_EXECUTE_JOBTYPE_CAIXUE = "7";
    //设置医嘱类型：
    public static final String TYPE_YZ_TOTAL = "total";

    public static final String TI_JIAO_TYPE_YES = "提交成功";
    public static final String TI_JIAO_TYPE_NO = "提交失败";

    public static final String YZ_TYPE_WEIHEDUI = "未核对";
    public static final String YZ_TYPE_WEIZHIXING = "未执行";
    public static final String YZ_TYPE_DAIBAIYAO = "待摆药";
    public static final String YZ_TYPE_DAIPEIYE = "待配液";
    public static final String YZ_TYPE_DAIQUXUE = "待取血";
    public static final String YZ_TYPE_FUWENZHONG = "复温中";
    //设置医嘱执行状态：进行中
    public static final String YZ_TYPE_ZHIXINGZHONG = "执行中";
    public static final String YZ_TYPE_YIZHIXING = "已执行";
    public static final String YZ_TYPE_YIZANTING = "已暂停";
    public static final String YZ_TYPE_DAIGUANCHA = "待观察";
    public static final String YZ_TYPE_DAIHEDUI = "待核对";

    public static final String YZ_TYPE_YIQUXIAO = "已取消";
    public static final String YZ_TYPE_YITINGZHI = "已停止";
    public static final String YZ_TYPE_WEITONGGUO = "未通过";
    public static final String YZ_TYPE_AUDITED = "审核过";
    public static final String YZ_TYPE_CHECKED = "核对过";
    public static final String YZ_TYPE_ALL = "全部医嘱";
    public static final int YZ_TYPE_NUMBER = 18;

    //设置Bundle KEY
    public static final String BUNDLE_KEY_IS_SINGLE_PATIENT = "isSinglePatient";
    //    public static final String BUNDLE_KEY_XUEDAI_NO = "xuedai_no";
    public static final String BUNDLE_KEY_RW = "rw";
    public static final String BUNDLE_KEY_VALUE_BAIYAO = "摆药";
    public static final String BUNDLE_KEY_VALUE_PEIYE = "配液";
    public static final String BUNDLE_KEY_VALUE_HEDUI = "核对";
    public static final String BUNDLE_KEY_IS_SAOMA = "isSaoMa";
    public static final String BUNDLE_KEY_YIZHU = "dataBean";
    public static final String BUNDLE_KEY_YITYPE = "yztype";
    public static final String BUNDLE_KEY_YIZHULEIXING = "yizhuleixing";
    public static final String BUNDLE_KEY_SHUYE = "shuye";
    public static final String BUNDLE_KEY_SHUXUE = "shuxue";
    public static final String BUNDLE_KEY_SELECTED_HUANZHE = "selectedhz";
    public static final String BUNDLE_KEY_JIAOBAN_SHIJIAN = "JIAOBAN_SHIJIAN";
    public static final String BUNDLE_KEY_JIAOBAN_SHIJIAN_EDT = "JIAOBAN_SHIJIAN_edt";
    public static final String BUNDLE_KEY_JIAOBAN_SHIJIAN_TEMPLATEID = "JIAOBAN_SHIJIAN_templateId";
    public static final String BUNDLE_KEY_VITAL_GROUP = "vitalgroup";
    public static final String BUNDLE_KEY_LIST_HUANZHE = "patientidlist";
    public static final String BUNDLE_KEY_HISID = "hisid";
    public static final String BUNDLE_KEY_PATIENT_ASSESS = "patientAssess";
    public static final String BUNDLE_KEY_TYPE_EXCE = "typeexce";
    public static final String BUNDLE_KEY_USER_ID2 = "USER_ID2";
    public static final String BUNDLE_KEY_STATUS_EXCE = "status_exce";
    public static final String BUNDLE_KEY_YICHANG = "yichuang";
    public static final String BUNDLE_KEY_PATIENT_TAB_NUM = "tab_number";
    public static final String BUNDLE_KEY_YIZHU_LIST = "mYizhuBeanList";
    public static final String BUNDLE_KEY_SJ = "sj";
    public static final String BUNDLE_KEY_TENGTONG_BUWEI = "tengtong";
    public static final String BUNDLE_KEY_ACTIVITY_TYPE = "activity_type";
    public static final String BUNDLE_KEY_FRAGMENT_TYPE = "FRAGMENT_type2";
    public static final String BUNDLE_KEY_ACTIVITY_TYPE_XUANJIAO = "XUANJIAO";
    public static final String BUNDLE_KEY_ACTIVITY_TYPE_HOUJI_CUOSHI = "HOUJICUOSHI";
    public static final String BUNDLE_KEY_ACTIVITY_TYPE_XUANJIAO_DEFINE = "xuanjiaodefine";
    public static final String BUNDLE_KEY_ACTIVITY_TYPE_XIANGQING = "BUNDLE_KEY_ACTIVITY_TYPE_XIANGQINGxuanjiaoxiangqing";
    public static final String BUNDLE_ACTIVITY_JILU_CHANGGUI_HULI = "常规护理记录";
    public static final String BUNDLE_ACTIVITY_JILU_CHANGGUI_HULI_SHIJIAN = "常规护理记录事件";
    public static final String BUNDLE_ACTIVITY_JILU_CHANGGUI_HULI_SHIJIAN_SUB = "常规护理记录事件提交";
    //分次 次数 Bundle key
    public static final String BUNDLE_KEY_FENCI = "fenci";
    //滴速跳转
    public static final String BUNDLE_KEY_DISU = "disu";
    public static final String BUNDLE_KEY_IS_DISU = "is_disu";
    //实入量
    public static final String BUNDLE_KEY_SHIRULIANG = "shiruliang";
    //是否跳转实入量
    public static final String BUNDLE_KEY_IS_SHIRULIANG = "is_shiruliang";
    //是否跳转实入量
    public static final String BUNDLE_KEY_IS_WANCHENG_SHUYE = "is_wanchneg_shuye";
    //是否是输液界面
    public static final String BUNDLE_KEY_IS_HUANYE = "is_huanye";
    //是否巡视跳转
    public static final String BUNDLE_KEY_IS_XUNSHI_GOTO = "is_xunshi_goto";
    public static final String BUNDLE_KEY_XUNSHI_JILU = "xunshi_jilu";

    //巡视间隔时间
    public static final String BUNDLE_KEY_XUNSHI_SJ = "xunshi_sj";
    //是否分次 次数 Bundle key
    public static final String BUNDLE_KEY_ISFENCI = "isfenci";
    public static final boolean BUNDLE_KEY_FENCI_YES = true;
    public static final boolean BUNDLE_KEY_FENCI_NO = false;
    //分次 时间间隔 Bundle key
    public static final String BUNDLE_KEY_SHIJIAN = "shijian";
    //常规护理记录 疼痛类型标识
    public static final String HULI_TYPR_TENGTONG = "E999";
    //常规护理记录 医嘱备注标识
    public static final String HULI_TYPR_YIZHU_BEIZHU = "E666";
    //常规护理记录 医嘱备注标识
    public static final String HULI_TYPR_YIZHU_BEIZHU_2 = "beizhu";
    //常规护理记录 其他
    public static final String HULI_TYPR_QITA = "qita";

    //常规护理记录 交班
    public static final String HULI_TYPR_JIAOBAN = "JIAO_BAN";

    public static final String BUNDLE_KEY_ASSESSDEFINE = "assessDefine";
    public static final String BUNDLE_KEY_XUANJIAO_DEFINE = "xuanjiaodefine";
    public static final String BUNDLE_KEY_TOPIC_DEFINE = "CUOSHI_TOPIC_DEFINE";
    public static final String BUNDLE_KEY_CUOSHI_TOPIC_DEFINE = "BUNDLE_KEY_CUOSHI_TOPIC_DEFINE";
    public static final String BUNDLE_KEY_ASSESS = "assess";

    //设置页面类型：是否是第一患者相关的页面
    public static final int PAGE_TYPE_NOPATIENT = 0;
    public static final int PAGE_TYPE_PATIENT = 1;

    //activity resultCode
    public static final int RESULT_CODE_ZHUSHE = 1001;
    public static final int RESULT_CODE_SHUTYE = 1002;
    public static final int RESULT_CODE_KOUFU = 1003;
    public static final int RESULT_CODE_JIANYAN = 1004;
    public static final int RESULT_CODE_SHUXUE = 1005;
    public static final int RESULT_CODE_FUZHU = 1006;
    public static final int RESULT_CODE_BEIZHU = 1007;
    public static final int RESULT_CODE_YICHANG = 1008;
    public static final int RESULT_CODE_YICHANG_CHULI = 1009;

    //zhushe2异常跳转识别
    public static final String ZHU_SHE_2_KEY = "zhushe2_key";
    public static final String ZHU_SHE_2 = "zhushe2";

    //评估设置
    public static final String TOPIC_TYPE_SELECT_SINGLE = "0";
    public static final String TOPIC_TYPE_SELECT_MULTIPLE = "1";
    public static final String TOPIC_TYPE_INPUT = "2";
    public static final String TOPIC_TYPE_TIME = "3";
    public static final String TOPIC_CODE_HUMAN_BOTY = "HUMAN_BODY";
    public static final String TOPIC_CODE_PAINT_LEVEL = "PAIN_LEVEL";
    public static final String ASSESS_CODE_PAIN_ASSESS = "PAIN_ASSESS";
    public static final String ASSESS_STATUS_SAVE = "0";
    public static final String ASSESS_STATUS_FINISHED = "1";
    public static final String ASSESS_RESULT_NORMAL = "正常";
    public static final int ANSWER_TYPE_TEXT = 0;
    public static final int ANSWER_TYPE_PICTURE = 1;
    public static final int ASSESS_ANSWER_NEED_NOTE = 1;

    //通知


    /*****************通知模块分类*****************/
    public static final int NOTIFY_TYPE_ORDER = 0;//医嘱
    public static final int NOTIFY_TYPE_VITAL = 1;//体征
    public static final int NOTIFY_TYPE_ASSESS = 2;//评估
    public static final int NOTIFY_TYPE_PATIENT_LABEL = 3;//标签
    public static final int NOTIFY_TYPE_PDA_SYNC = 4;//安卓端同步
    public static final int NOTIFY_TYPE_JIAOBAN = 5;//交班

    /*******************通知子模块分类****************************/
    public static final int NOTIFY_SUB_TYPE_ASSESS_COMMON = 11;//普通评估
    public static final int NOTIFY_SUB_TYPE_ASSESS_MEASURE = 12;//评估措施

    /******************通知的操作****************************/
    public static final int STATUS_UNREAD = 0;//未读
    public static final int STATUS_READ = 1;//已读
    public static final int STATUS_REMOVE = 2;//删除

    /*******************通知事件分类*************************************/
    //医嘱
    public static final String EVENT_TYPE_ORDER_PLAN_EXECUTE_REMIND = "医嘱执行提醒";
    public static final String EVENT_TYPE_ORDER_NO_EXECUTE_TIMEOUT = "医嘱逾期未执行";
    public static final String EVENT_TYPE_ORDER_INFUSION_WATCH_TIMEOUT = "输液待巡视";
    public static final String EVENT_TYPE_ORDER_BLOOD_WATCH_TIMEOUT = "输血待巡视";
    public static final String EVENT_TYPE_ORDER_INFUSION_WATCH_REMIND = "输液巡视提醒";
    public static final String EVENT_TYPE_ORDER_BLOOD_WATCH_REMIND = "输血巡视提醒";
    public static final String EVENT_TYPE_ORDER_NEAR_EXECUTE = "即将执行";
    public static final String EVENT_TYPE_ORDER_NEAR_WATCH = "即将巡视";
    //体征
    public static final String EVENT_TYPE_VITAL_EXCEPTION = "生命体征异常";
    public static final String EVENT_TYPE_VITAL_RECORD = "生命体征记录";
    public static final String EVENT_TYPE_VITAL_RETEST = "生命体征复测";
    //评估
    public static final String EVENT_TYPE_ASSESS_RECORD = "病情评估记录";
    //离线数据同步
    public static final String EVENT_TYPE_OFFLINE_SYNC = "离线数据同步";
    /*******************同步相关事件分类*************************************/
    public static final String MSG_SYNC_READY = "SYNC_READY";
    public static final String MSG_SYNC_REQ = "SYNC_REQ";
    public static final String MSG_SYNC_REQ_SUBMIT = "SYNC_SUBMIT"; // 数据开始同步，需要等待一会儿时间
    public static final String MSG_SYNC_REQ_REFUSE_NODATA = "ERROR_0"; //没有需要同步的数据
    public static final String MSG_SYNC_REQ_REFUSE_SYNCING = "ERROR_1"; // 上次同步没完成
    public static final long TIME_INTERVAL_COUNT = 2000l;
    public static final int CHECK_UNSYNC_MAX_COUNT = 15;
    //private static final int CHECK_UNSYNC_MAX_COUNT = 30;

    public static final String XUANJIAO_CHILD_KEY = "childXuanJiaoKey";
    public static final String XUANJIAO_CHILD_VALUE = "childXuanJiaoValue";

    // Honeywell设备型号
    public static final String DEVICE_MODEL_HONEYWELL = "Honeywell";
    // 神达设备型号
    public static final String DEVICE_MODEL_MITAC = "Mitac_International_Corp";
    // 未知设备
    public static final String DEVICE_MODEL_UNKNOW = "Unknow";

    /**
     * 移动护理目录
     */
    // 根目录
    public static final String PATH_ROOT = Environment.getExternalStorageDirectory().toString() + "/intelli/MobileNursing/";
    // 下载路径
    public static final String PATH_DOWNLOAD = PATH_ROOT + "download/";
    // 文件安装路径
    public static final String PATH_APK = PATH_DOWNLOAD + "apk/";
}
