package com.ge.med.mobile.nursing.config;

/**
 * 所有网络访问的接口URL
 * Created by Administrator on 2016/10/28.
 */
public class URL {
    public static void resetURL(String ip, String port) {
        if (port == null || port.isEmpty()) port = "8080";
        URL_PORT = port;
        if (ip == null || ip.isEmpty()) ip = "192.168.43.75";
        URL_IP = ip;
        URL = "http://" + URL_IP + ":" + URL_PORT + "/intelli";
        URL_SOCKET = "ws://" + URL_IP + ":" + URL_PORT + "/intelli/socketServer";
        LOG_IN = URL + "/user/identity";
        USER_ALL = URL + "/user/getWardList";
        XIU_GAI_MI_MA = URL + "/user/changePwd";
        LOG_IN_GET_USER = URL + "/user/getUser";
        URL_SY = URL + "/patient/getList";
//        URL_QUAN_BU_SY = URL_SY;
        URL_QUAN_BU_SY = URL + "/patient/getPatientList";
        URL_GET_SINGLE_PATIENT = URL + "/patient/getSinglePatient";
        URL_QUAN_BU_YZ = URL + "/docOrder/getOrderList";
        URL_DANG_TIAN_YZ = URL + "/docOrder/getTodayOrderList";
        URL_DAN_GE_YZ = URL + "/docOrder/getSingleOrder";
        URL_SC_HD_YZ = URL + "/docOrder/submitList";
        URL_SC_YZ = URL + "/docOrder/submitOrderEntity";
        URL_YZ_MORE = URL + "/docOrder/getTwelveOrderList";
        URL_ALL_EXCEPTION = URL + "/exception/getALLExceptionConfigByOrderType";
        URL_GET_EXCEPTION = URL + "/exception/getExceptionConfigInfo";
        URL_GET_ALL_EXCEPTION = URL + "/exception/getALLExceptionConfigByOrderType";
        URL_ASSESS_DEFINE = URL + "/assess/getWardDefine";
        URL_ASSESS_LIST = URL + "/assess/getList";
        URL_ASSESS_SINGLE = URL + "/assess/getSingle";
        URL_ASSESS_SUBMIT = URL + "/assess/subAssess";
        URL_ASSESS_SUBMIT_LIST = URL + "/assess/submitList";
        URL_WARD_RISK_DEFINE = URL + "/risk/getWardRisk";
        URL_VITAL_WARD_DEFINE = URL + "/wardConfig/getWardVitalDefineList";
        URL_VITAL_SUBMIT_LIST = URL + "/vital/pushVitalSigns";
        URL_VITAL_LIST = URL + "/vital/getVitalSignListById";
        URL_VITAL_SINGLE = URL + "/vital/getVitalSignEntity";
        URL_VITAL_PATIENT_LAST = URL + "/vital/getLastVitalSignListByPid";
        URL_VITAL_SAVE = URL + "/vital/saveVitalSign";
        URL_VITAL_SAVE_LIST = URL + "/vital/saveVitalSigns";
        URL_JB_DAN = URL + "/discharge/subSingle";  //提交交班记录单条 /discharge/subSingle ? json   Method.POST
        URL_TONGZHI = URL + "/notify/getNotifyInfoForPDA";
        CESHI = URL + "/notify/testServer";
        URL_ICON = URL + "/image/getImagePath";
        TZ_SHANCHU = URL + "/notify/refreshNotifyStatus";
        URL_HULI_JILU = URL + "/record/getNursingRecordList";
        URL_HULI_JILU_SUBMIT_LIST = URL + "/record/submitNursingRecordList";
        URL_HULI_JILU_SUBMIT = URL + "/record/submitNursingRecord";
        URL_HULI_JILU_SHIJIAN = URL + "/getNursingEventTemplateList";
        URL_HULI_JILU_LIEBIAO = URL + "/getWardNursingRecordDefine";
        URL_JB_CALL = URL + "/discharge/getDataByPid";
        URL_XUANJIAO = URL + "/getWardPatientEducationList";
        URL_SUBMIT_XUANJIAO = URL + "/submitPatientEducationRecords";
        //URL_JILU_XUANJIAO = URL + "/getPatientEducationRecordListByPId";
        URL_JILU_XUANJIAO = URL + "/getLastPatientEducationRecordList";
        URL_HOUJI_CUOSHI_DEFINE = URL + "/measure/getAssessMeasureDefineByCode";
        URL_HOUJI_CUOSHI_RECORD = URL + "/measure/getAssessMeasureRecordById";
        URL_SUBMIT_HOUJI_CUOSHI_RECORD = URL + "/measure/submitAssessMeasureRecords";
        URL_FUWEN = URL + "/rewarming/startRewarming";
        URL_MEASURE_FALLDOWN_INSERT = URL + "/measure/falldown/insertRecord";
        URL_VITAL_DELETE_RECORD = URL + "/vital/deleteVitalSign/";
        URL_EVENT_GET_LIST = URL + "/event/android/getList";
        URL_EVENT_GET_DEFINE = URL + "/event/pc/getDefine";
        URL_EVENT_DELETE = URL + "/deletePatientEventRecordById";
        URL_EVENT_GET_RECORD = URL + "/getPatientEventRecordByPId";
        URL_EVENT_SUBMIT_RECORD = URL + "/submitPatientEventRecords";
        URL_ASSESS_GET_TASK_LABEL = URL + "/nursingTask/assess/getTaskLabel";
    }

    //----------------------------------------------------------------------------------------------------------------------------------

    public static String URL_PORT = "8080";
    public static String URL_IP = "192.168.0.107";
    //    public static String URL_IP = "192.168.1.156";
//    public static String URL_IP = "192.168.1.132";
//    public static String URL_IP = "192.168.140.211";
    //public static  URL_IP = "192.168.140.212";
    // Project base URL
    public static String URL = "http://" + URL_IP + ":" + URL_PORT + "/intelli";

    // 通知相关API定义
    // Socket URL
    public static String URL_SOCKET = "ws://" + URL_IP + ":" + URL_PORT + "/intelli/socketServer";

    // 用户相关API定义
    //登录
    public static String LOG_IN = URL + "/user/identity";
    //加载所有的 user
    public static String USER_ALL = URL + "/user/getWardList";
    //修改密码
    public static String XIU_GAI_MI_MA = URL + "/user/changePwd";
    //user信息
    public static String LOG_IN_GET_USER = URL + "/user/getUser";
    ///images/assess?id=       GET
//    这是获取药品详情图片的接口
//    public static String URL_ICON = URL + "/images/assess";
    public static String URL_ICON = URL + "/image/getImagePath";

    // 患者相关API定义
    //首页我的患者列表
    public static String URL_SY = URL + "/patient/getList";
    public static String URL_GET_SINGLE_PATIENT = URL + "/patient/getSinglePatient";
    //首页病区全部患者
    public static String URL_QUAN_BU_SY = URL + "/patient/getPatientList";

    // 医嘱相关API定义
    //获取全部医嘱/getOrderList

    public static String URL_QUAN_BU_YZ = URL + "/docOrder/getOrderList";
    //@RequestParam(value = "wardId", required = false) Integer wardId,
//    @RequestParam(value = "userId", required = false) Integer userId,
//    @RequestParam(value = "patientId", required = false) String patientId,
//    @RequestParam(value = "ordertype", required = false) String ordertype,
//    @RequestParam(value = "status", required = false) String status ：已执行；未执行；未审核；未通过；未核对；执行中；已取消；已停止；已暂停
    //获取当天医嘱 0:00:00~23:59:59
    public static String URL_DANG_TIAN_YZ = URL + "/docOrder/getTodayOrderList";
    //获取单个医嘱 id
    public static String URL_DAN_GE_YZ = URL + "/docOrder/getSingleOrder";
    //批量提交医嘱
    public static String URL_SC_HD_YZ = URL + "/docOrder/submitList";
    //上传单条医嘱
    public static String URL_SC_YZ = URL + "/docOrder/submitOrderEntity";
    //加载更多医嘱
    public static String URL_YZ_MORE = URL + "/docOrder/getTwelveOrderList";
    // 获取所有医嘱的异常配置
    public static String URL_ALL_EXCEPTION = URL + "/exception/getALLExceptionConfigByOrderType";
    ///exception/getExceptionConfigInfo GET 参数=》orderType，orderStatus；
    // 获取异常配置表（可根据医嘱类型获取，也可根据医嘱状态获取，也可二者结合获取）
    public static String URL_GET_EXCEPTION = URL + "/exception/getExceptionConfigInfo";
    //GET /exception/getALLExceptionConfigByOrderType 获取所有医嘱的异常配置
    public static String URL_GET_ALL_EXCEPTION = URL + "/exception/getALLExceptionConfigByOrderType";

    // 评估相关API定义
    //获取病区评估定义
    public static String URL_ASSESS_DEFINE = URL + "/assess/getWardDefine";
    //获取多条评估记录
    public static String URL_ASSESS_LIST = URL + "/assess/getList";
    //获取单条评估记录
    public static String URL_ASSESS_SINGLE = URL + "/assess/getSingle";
    //保存单条评估
    public static String URL_ASSESS_SUBMIT = URL + "/assess/subAssess";
    //保存多条评估
    public static String URL_ASSESS_SUBMIT_LIST = URL + "/assess/submitList";
    //获取护理风险评估标签定义接口：wardId,POST
    public static String URL_WARD_RISK_DEFINE = URL + "/risk/getWardRisk";

    // 体征相关API定义
    //获取病区体征定义 请求方式：POST,请求参数：wardId
    public static String URL_VITAL_WARD_DEFINE = URL + "/wardConfig/getWardVitalDefineList";
    public static String URL_VITAL_SUBMIT_LIST = URL + "/vital/pushVitalSigns";
    //获取多次体征记录:POST,参数：patientId， userId， wardId， days
    //特殊参数days举例：0:取今天数据，-1:取昨天数据,1:取明天的数据，依次类推，可以取到想要几天的数据
    public static String URL_VITAL_LIST = URL + "/vital/getVitalSignListById";
    //获取单次体征录入记录: POST, 参数：sheetid
    public static String URL_VITAL_SINGLE = URL + "/vital/getVitalSignEntity";
    //获取病人最后一次体征录入记录: POST, 参数：patientId
    public static String URL_VITAL_PATIENT_LAST = URL + "/vital/getLastVitalSignListByPid";
    //保存单次体征记录：POST，结果：成功 or 失败，参数：vitalSignJson， empNo
    public static String URL_VITAL_SAVE = URL + "/vital/saveVitalSign";
    //保存多次体征记录：POST，结果：成功 or 失败，参数：vitalSignJson， empNo
    public static String URL_VITAL_SAVE_LIST = URL + "/vital/saveVitalSigns";
    //提交多次体征记录：POST，参数：idJson，empNo

    //提交交班记录单条 /discharge/subSingle ? jsonString   Method.POST
    public static String URL_JB_DAN = URL + "/discharge/subSingle";  //提交交班记录单条 /discharge/subSingle ? jsonString   Method.POST
    //获取交班内容 patientId
    public static String URL_JB_CALL = URL + "/discharge/getDataByPid";
    //获取所有通知 ；userId 必须参数(integer:用户id)
//    eventType 非必须参数(string:通知的类别-> 医嘱未执行、超时未巡视)
    public static String URL_TONGZHI = URL + "/notify/getNotifyInfoForPDA";
    //    /refreshNotifyStatus POST 参数：jsonString
    public static String TZ_SHANCHU = URL + "/notify/refreshNotifyStatus";

    //测试
    public static String CESHI = URL + "/notify/testServer";
    //远程获取护理记录数据
    public static String URL_HULI_JILU = URL + "/record/getNursingRecordList";
    public static String URL_HULI_JILU_SUBMIT_LIST = URL + "/record/submitNursingRecordList";
    public static String URL_HULI_JILU_SUBMIT = URL + "/record/submitNursingRecord";
    //获取事件
    public static String URL_HULI_JILU_SHIJIAN = URL + "/getNursingEventTemplateList";
    //远程获取记录列表
    public static String URL_HULI_JILU_LIEBIAO = URL + "/getWardNursingRecordDefine";
    //    http://localhost:8080/intelli/getWardPatientEducationList?wardId=301
    //获取宣教 定义
    public static String URL_XUANJIAO = URL + "/getWardPatientEducationList";
    //    http://localhost:8080/intelli/submitPatientEducationRecords POST
    //宣教提交
    public static String URL_SUBMIT_XUANJIAO = URL + "/submitPatientEducationRecords";
    //宣教记录
    //public static String URL_JILU_XUANJIAO = URL + "/getPatientEducationRecordListByPId";
    public static String URL_JILU_XUANJIAO = URL + "/getLastPatientEducationRecordList";
    //    1.获取措施定义
//    /measure/getAssessMeasureDefineByCode GET 参数：measureDefineCode
    public static String URL_HOUJI_CUOSHI_DEFINE = URL + "/measure/getAssessMeasureDefineByCode";
    //    2.获取措施记录
//    /measure/getAssessMeasureRecordById GET patientId，assessRecordId
    public static String URL_HOUJI_CUOSHI_RECORD = URL + "/measure/getAssessMeasureRecordById";
    //    /measure/getAssessMeasureRecordById GET patientId，assessRecordId
    public static String URL_SUBMIT_HOUJI_CUOSHI_RECORD = URL + "/measure/submitAssessMeasureRecords";
    //    /rewarming/startRewarming POST
    //设置复温时间
    public static String URL_FUWEN = URL + "/rewarming/startRewarming";

    // 获取最新的版本号
    public static String URL_LASTEST_VERSION = URL + "/appversion/getappmaxversion";

    // 删除评估项
    public static String URL_ASSESS_DELETE_RECODR = URL + "/assess/deleteAssessRecord";

    // 残疾人、儿童、孕妇创建措施接口
    public static String URL_MEASURE_FALLDOWN_INSERT = URL + "/measure/falldown/insertRecord";

    // 删除体征记录
    public static String URL_VITAL_DELETE_RECORD = URL + "/vital/deleteVitalSign/";

    // 获取事件列表
    public static String URL_EVENT_GET_LIST = URL + "/event/android/getList";

    // 获取事件定义
    public static String URL_EVENT_GET_DEFINE = URL + "/event/pc/getDefine";

    // 删除事件
    public static String URL_EVENT_DELETE = URL + "/deletePatientEventRecordById";

    // 获取事件单条记录
    public static String URL_EVENT_GET_RECORD = URL + "/getPatientEventRecordByPId";

    // 提交事件记录
    public static String URL_EVENT_SUBMIT_RECORD = URL + "/submitPatientEventRecords";

    // 评估标签
    public static String URL_ASSESS_GET_TASK_LABEL = URL + "/nursingTask/assess/getTaskLabel";

}
