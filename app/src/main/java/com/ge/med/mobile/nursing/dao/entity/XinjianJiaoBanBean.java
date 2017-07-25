package com.ge.med.mobile.nursing.dao.entity;

import java.io.Serializable;
import java.util.List;

/**网络访问实体JSON解析Bean
 * Created by Administrator on 2017/3/10.
 */
public class XinjianJiaoBanBean  implements Serializable{

    /**
     * msg : 成功
     * data : {"eventList":[{"createdby":"系统","creationtime":1482717727000,"id":1,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"sortno":1,"templatecontent":"接班看病人：患者意识清楚，双侧瞳孔等大等圆，直径<span style=\"color:red\">XX<\/span>mm。对光反射灵敏持续心电、血压、呼吸、脉痒饱和度检测，机器运转正常。持续低流量吸氧。速度为<span style=\"color:red\">XX<\/span>升／分钟。","templatename":"事件1","wardId":301},{"createdby":"系统","creationtime":1482717727000,"id":2,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"sortno":1,"templatecontent":"接班看病人：患者意识清楚，双侧瞳孔等大等圆，直径<span style=\"color:red\">XX<\/span>mm。持续低流量吸氧。速度为<span style=\"color:red\">XX<\/span>升／分钟。","templatename":"事件2","wardId":301},{"createdby":"系统","creationtime":1482717727000,"id":3,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"sortno":1,"templatecontent":"接班看病人：患者意识清楚，双侧瞳孔等大等圆，直径<span style=\"color:red\">XX<\/span>mm。持续低流量吸氧。","templatename":"事件3","wardId":301}],"patient":{"bedNo":1,"executingOrderList":[{"auditby":"","audittime":null,"bedNo":1,"checkby":"3","checktime":1490178895000,"createdby":"","creationtime":null,"doctorOrders":[{"createdby":"","creationtime":null,"duration":"","endtime":null,"executename":"","hisid":null,"id":578,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"note":"","orderDose":"","orderExceptions":[],"orderExecuteRecords":[],"orderNoteList":null,"orderUnit":"","routeDesc":"","signRecord":null,"starttime":1490343724000,"status":"执行中","suborderduration":"","subordername":""}],"doctorname":"","doctorno":"","executeby":"","executename":"","executetime":null,"exetime":null,"flag":null,"id":9,"imageid":null,"isdeleted":"0","lastupdatedby":"晨晨","lastupdatetime":1492498564000,"medicinedetails":"","mrnNo":"","note":"巴拉巴拉","orderBloodProperty":[],"orderCheckProperty":[],"orderNoteList":[],"orderTypeName":"","orderbarcode":"250186093","orderdose":"100","orderduration":"1","orderfrequency":"1 /日","orderfrom":"","ordername":"葡萄糖 200ml 9","orderresult":"","orderststus":"执行中","ordertype":"1","orderunit":"ml","patientName":"张三123","patientid":"A100233_1","pharmList":[],"printUserName":"","printtime":1493173135000,"printuser":"","routedesc":"静脉输液","starttime":1492597536000,"userid":3},{"auditby":"","audittime":null,"bedNo":1,"checkby":"3","checktime":1490178895000,"createdby":"","creationtime":null,"doctorOrders":[{"createdby":"","creationtime":null,"duration":"","endtime":null,"executename":"","hisid":null,"id":582,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"note":"","orderDose":"","orderExceptions":[],"orderExecuteRecords":[],"orderNoteList":null,"orderUnit":"","routeDesc":"","signRecord":null,"starttime":1490343724000,"status":"已执行","suborderduration":"","subordername":""}],"doctorname":"","doctorno":"","executeby":"3","executename":"","executetime":1492509560000,"exetime":null,"flag":null,"id":10,"imageid":null,"isdeleted":"0","lastupdatedby":"晨晨","lastupdatetime":1492509560000,"medicinedetails":"","mrnNo":"","note":"咕叽咕叽","orderBloodProperty":[],"orderCheckProperty":[],"orderNoteList":[],"orderTypeName":"","orderbarcode":"250186092","orderdose":"200","orderduration":"2","orderfrequency":"2 /日","orderfrom":"","ordername":"葡萄糖 200ml 10","orderresult":"","orderststus":"执行中","ordertype":"10","orderunit":"ml","patientName":"张三123","patientid":"A100233_1","pharmList":[],"printUserName":"","printtime":1493173135000,"printuser":"","routedesc":"静脉输液","starttime":1492597536000,"userid":3},{"auditby":"","audittime":null,"bedNo":1,"checkby":"3","checktime":1490178895000,"createdby":"","creationtime":null,"doctorOrders":[{"createdby":"","creationtime":null,"duration":"","endtime":null,"executename":"","hisid":null,"id":580,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"note":"","orderDose":"","orderExceptions":[],"orderExecuteRecords":[],"orderNoteList":null,"orderUnit":"","routeDesc":"","signRecord":null,"starttime":1490343724000,"status":"已执行","suborderduration":"","subordername":""}],"doctorname":"","doctorno":"","executeby":"3","executename":"","executetime":1492510276000,"exetime":null,"flag":null,"id":11,"imageid":null,"isdeleted":"0","lastupdatedby":"晨晨","lastupdatetime":1492510276000,"medicinedetails":"","mrnNo":"","note":"biubiubiu~","orderBloodProperty":[],"orderCheckProperty":[],"orderNoteList":[],"orderTypeName":"","orderbarcode":"250186091","orderdose":"150","orderduration":"2","orderfrequency":"3 /日","orderfrom":"","ordername":"葡萄糖 200ml 11","orderresult":"","orderststus":"执行中","ordertype":"1","orderunit":"ml","patientName":"张三123","patientid":"A100233_1","pharmList":[],"printUserName":"","printtime":1493173135000,"printuser":"","routedesc":"静脉输液","starttime":1492597536000,"userid":3}],"lastVitalSign":null,"name":"张三123","patientid":"A100233_1","totalVitalInOutList":[],"wardId":301},"wardVitalDefine":[{"connectionmode":"","createdby":"","creationtime":null,"id":14,"imageid":null,"inouttype":"","inputtype":"","isdeleted":"","isnotemandatory":"","isnumber":"","lastupdatedby":"","lastupdatetime":null,"rangeend":1000,"rangestart":1,"signname":"饮水量","unitdesc":"ml","vcode":"V014","vitalNoteList":null},{"connectionmode":"","createdby":"","creationtime":null,"id":15,"imageid":null,"inouttype":"","inputtype":"","isdeleted":"","isnotemandatory":"","isnumber":"","lastupdatedby":"","lastupdatetime":null,"rangeend":1000,"rangestart":1,"signname":"口服液量","unitdesc":"ml","vcode":"V015","vitalNoteList":null},{"connectionmode":"","createdby":"","creationtime":null,"id":12,"imageid":null,"inouttype":"","inputtype":"","isdeleted":"","isnotemandatory":"","isnumber":"","lastupdatedby":"","lastupdatetime":null,"rangeend":1000,"rangestart":1,"signname":"尿量","unitdesc":"ml","vcode":"V012","vitalNoteList":null},{"connectionmode":"","createdby":"","creationtime":null,"id":13,"imageid":null,"inouttype":"","inputtype":"","isdeleted":"","isnotemandatory":"","isnumber":"","lastupdatedby":"","lastupdatetime":null,"rangeend":1000,"rangestart":1,"signname":"呕吐量","unitdesc":"ml","vcode":"V013","vitalNoteList":null}]}
     * status : 1
     */

    private String msg;
    private DataBean data;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean implements Serializable{
        /**
         * eventList : [{"createdby":"系统","creationtime":1482717727000,"id":1,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"sortno":1,"templatecontent":"接班看病人：患者意识清楚，双侧瞳孔等大等圆，直径<span style=\"color:red\">XX<\/span>mm。对光反射灵敏持续心电、血压、呼吸、脉痒饱和度检测，机器运转正常。持续低流量吸氧。速度为<span style=\"color:red\">XX<\/span>升／分钟。","templatename":"事件1","wardId":301},{"createdby":"系统","creationtime":1482717727000,"id":2,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"sortno":1,"templatecontent":"接班看病人：患者意识清楚，双侧瞳孔等大等圆，直径<span style=\"color:red\">XX<\/span>mm。持续低流量吸氧。速度为<span style=\"color:red\">XX<\/span>升／分钟。","templatename":"事件2","wardId":301},{"createdby":"系统","creationtime":1482717727000,"id":3,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"sortno":1,"templatecontent":"接班看病人：患者意识清楚，双侧瞳孔等大等圆，直径<span style=\"color:red\">XX<\/span>mm。持续低流量吸氧。","templatename":"事件3","wardId":301}]
         * patient : {"bedNo":1,"executingOrderList":[{"auditby":"","audittime":null,"bedNo":1,"checkby":"3","checktime":1490178895000,"createdby":"","creationtime":null,"doctorOrders":[{"createdby":"","creationtime":null,"duration":"","endtime":null,"executename":"","hisid":null,"id":578,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"note":"","orderDose":"","orderExceptions":[],"orderExecuteRecords":[],"orderNoteList":null,"orderUnit":"","routeDesc":"","signRecord":null,"starttime":1490343724000,"status":"执行中","suborderduration":"","subordername":""}],"doctorname":"","doctorno":"","executeby":"","executename":"","executetime":null,"exetime":null,"flag":null,"id":9,"imageid":null,"isdeleted":"0","lastupdatedby":"晨晨","lastupdatetime":1492498564000,"medicinedetails":"","mrnNo":"","note":"巴拉巴拉","orderBloodProperty":[],"orderCheckProperty":[],"orderNoteList":[],"orderTypeName":"","orderbarcode":"250186093","orderdose":"100","orderduration":"1","orderfrequency":"1 /日","orderfrom":"","ordername":"葡萄糖 200ml 9","orderresult":"","orderststus":"执行中","ordertype":"1","orderunit":"ml","patientName":"张三123","patientid":"A100233_1","pharmList":[],"printUserName":"","printtime":1493173135000,"printuser":"","routedesc":"静脉输液","starttime":1492597536000,"userid":3},{"auditby":"","audittime":null,"bedNo":1,"checkby":"3","checktime":1490178895000,"createdby":"","creationtime":null,"doctorOrders":[{"createdby":"","creationtime":null,"duration":"","endtime":null,"executename":"","hisid":null,"id":582,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"note":"","orderDose":"","orderExceptions":[],"orderExecuteRecords":[],"orderNoteList":null,"orderUnit":"","routeDesc":"","signRecord":null,"starttime":1490343724000,"status":"已执行","suborderduration":"","subordername":""}],"doctorname":"","doctorno":"","executeby":"3","executename":"","executetime":1492509560000,"exetime":null,"flag":null,"id":10,"imageid":null,"isdeleted":"0","lastupdatedby":"晨晨","lastupdatetime":1492509560000,"medicinedetails":"","mrnNo":"","note":"咕叽咕叽","orderBloodProperty":[],"orderCheckProperty":[],"orderNoteList":[],"orderTypeName":"","orderbarcode":"250186092","orderdose":"200","orderduration":"2","orderfrequency":"2 /日","orderfrom":"","ordername":"葡萄糖 200ml 10","orderresult":"","orderststus":"执行中","ordertype":"10","orderunit":"ml","patientName":"张三123","patientid":"A100233_1","pharmList":[],"printUserName":"","printtime":1493173135000,"printuser":"","routedesc":"静脉输液","starttime":1492597536000,"userid":3},{"auditby":"","audittime":null,"bedNo":1,"checkby":"3","checktime":1490178895000,"createdby":"","creationtime":null,"doctorOrders":[{"createdby":"","creationtime":null,"duration":"","endtime":null,"executename":"","hisid":null,"id":580,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"note":"","orderDose":"","orderExceptions":[],"orderExecuteRecords":[],"orderNoteList":null,"orderUnit":"","routeDesc":"","signRecord":null,"starttime":1490343724000,"status":"已执行","suborderduration":"","subordername":""}],"doctorname":"","doctorno":"","executeby":"3","executename":"","executetime":1492510276000,"exetime":null,"flag":null,"id":11,"imageid":null,"isdeleted":"0","lastupdatedby":"晨晨","lastupdatetime":1492510276000,"medicinedetails":"","mrnNo":"","note":"biubiubiu~","orderBloodProperty":[],"orderCheckProperty":[],"orderNoteList":[],"orderTypeName":"","orderbarcode":"250186091","orderdose":"150","orderduration":"2","orderfrequency":"3 /日","orderfrom":"","ordername":"葡萄糖 200ml 11","orderresult":"","orderststus":"执行中","ordertype":"1","orderunit":"ml","patientName":"张三123","patientid":"A100233_1","pharmList":[],"printUserName":"","printtime":1493173135000,"printuser":"","routedesc":"静脉输液","starttime":1492597536000,"userid":3}],"lastVitalSign":null,"name":"张三123","patientid":"A100233_1","totalVitalInOutList":[],"wardId":301}
         * wardVitalDefine : [{"connectionmode":"","createdby":"","creationtime":null,"id":14,"imageid":null,"inouttype":"","inputtype":"","isdeleted":"","isnotemandatory":"","isnumber":"","lastupdatedby":"","lastupdatetime":null,"rangeend":1000,"rangestart":1,"signname":"饮水量","unitdesc":"ml","vcode":"V014","vitalNoteList":null},{"connectionmode":"","createdby":"","creationtime":null,"id":15,"imageid":null,"inouttype":"","inputtype":"","isdeleted":"","isnotemandatory":"","isnumber":"","lastupdatedby":"","lastupdatetime":null,"rangeend":1000,"rangestart":1,"signname":"口服液量","unitdesc":"ml","vcode":"V015","vitalNoteList":null},{"connectionmode":"","createdby":"","creationtime":null,"id":12,"imageid":null,"inouttype":"","inputtype":"","isdeleted":"","isnotemandatory":"","isnumber":"","lastupdatedby":"","lastupdatetime":null,"rangeend":1000,"rangestart":1,"signname":"尿量","unitdesc":"ml","vcode":"V012","vitalNoteList":null},{"connectionmode":"","createdby":"","creationtime":null,"id":13,"imageid":null,"inouttype":"","inputtype":"","isdeleted":"","isnotemandatory":"","isnumber":"","lastupdatedby":"","lastupdatetime":null,"rangeend":1000,"rangestart":1,"signname":"呕吐量","unitdesc":"ml","vcode":"V013","vitalNoteList":null}]
         */

        private PatientBean patient;
        private List<EventListBean> eventList;
        private List<WardVitalDefineBean> wardVitalDefine;

        public PatientBean getPatient() {
            return patient;
        }

        public void setPatient(PatientBean patient) {
            this.patient = patient;
        }

        public List<EventListBean> getEventList() {
            return eventList;
        }

        public void setEventList(List<EventListBean> eventList) {
            this.eventList = eventList;
        }

        public List<WardVitalDefineBean> getWardVitalDefine() {
            return wardVitalDefine;
        }

        public void setWardVitalDefine(List<WardVitalDefineBean> wardVitalDefine) {
            this.wardVitalDefine = wardVitalDefine;
        }

        public static class PatientBean implements Serializable{
            /**
             * bedNo : 1
             * executingOrderList : [{"auditby":"","audittime":null,"bedNo":1,"checkby":"3","checktime":1490178895000,"createdby":"","creationtime":null,"doctorOrders":[{"createdby":"","creationtime":null,"duration":"","endtime":null,"executename":"","hisid":null,"id":578,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"note":"","orderDose":"","orderExceptions":[],"orderExecuteRecords":[],"orderNoteList":null,"orderUnit":"","routeDesc":"","signRecord":null,"starttime":1490343724000,"status":"执行中","suborderduration":"","subordername":""}],"doctorname":"","doctorno":"","executeby":"","executename":"","executetime":null,"exetime":null,"flag":null,"id":9,"imageid":null,"isdeleted":"0","lastupdatedby":"晨晨","lastupdatetime":1492498564000,"medicinedetails":"","mrnNo":"","note":"巴拉巴拉","orderBloodProperty":[],"orderCheckProperty":[],"orderNoteList":[],"orderTypeName":"","orderbarcode":"250186093","orderdose":"100","orderduration":"1","orderfrequency":"1 /日","orderfrom":"","ordername":"葡萄糖 200ml 9","orderresult":"","orderststus":"执行中","ordertype":"1","orderunit":"ml","patientName":"张三123","patientid":"A100233_1","pharmList":[],"printUserName":"","printtime":1493173135000,"printuser":"","routedesc":"静脉输液","starttime":1492597536000,"userid":3},{"auditby":"","audittime":null,"bedNo":1,"checkby":"3","checktime":1490178895000,"createdby":"","creationtime":null,"doctorOrders":[{"createdby":"","creationtime":null,"duration":"","endtime":null,"executename":"","hisid":null,"id":582,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"note":"","orderDose":"","orderExceptions":[],"orderExecuteRecords":[],"orderNoteList":null,"orderUnit":"","routeDesc":"","signRecord":null,"starttime":1490343724000,"status":"已执行","suborderduration":"","subordername":""}],"doctorname":"","doctorno":"","executeby":"3","executename":"","executetime":1492509560000,"exetime":null,"flag":null,"id":10,"imageid":null,"isdeleted":"0","lastupdatedby":"晨晨","lastupdatetime":1492509560000,"medicinedetails":"","mrnNo":"","note":"咕叽咕叽","orderBloodProperty":[],"orderCheckProperty":[],"orderNoteList":[],"orderTypeName":"","orderbarcode":"250186092","orderdose":"200","orderduration":"2","orderfrequency":"2 /日","orderfrom":"","ordername":"葡萄糖 200ml 10","orderresult":"","orderststus":"执行中","ordertype":"10","orderunit":"ml","patientName":"张三123","patientid":"A100233_1","pharmList":[],"printUserName":"","printtime":1493173135000,"printuser":"","routedesc":"静脉输液","starttime":1492597536000,"userid":3},{"auditby":"","audittime":null,"bedNo":1,"checkby":"3","checktime":1490178895000,"createdby":"","creationtime":null,"doctorOrders":[{"createdby":"","creationtime":null,"duration":"","endtime":null,"executename":"","hisid":null,"id":580,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"note":"","orderDose":"","orderExceptions":[],"orderExecuteRecords":[],"orderNoteList":null,"orderUnit":"","routeDesc":"","signRecord":null,"starttime":1490343724000,"status":"已执行","suborderduration":"","subordername":""}],"doctorname":"","doctorno":"","executeby":"3","executename":"","executetime":1492510276000,"exetime":null,"flag":null,"id":11,"imageid":null,"isdeleted":"0","lastupdatedby":"晨晨","lastupdatetime":1492510276000,"medicinedetails":"","mrnNo":"","note":"biubiubiu~","orderBloodProperty":[],"orderCheckProperty":[],"orderNoteList":[],"orderTypeName":"","orderbarcode":"250186091","orderdose":"150","orderduration":"2","orderfrequency":"3 /日","orderfrom":"","ordername":"葡萄糖 200ml 11","orderresult":"","orderststus":"执行中","ordertype":"1","orderunit":"ml","patientName":"张三123","patientid":"A100233_1","pharmList":[],"printUserName":"","printtime":1493173135000,"printuser":"","routedesc":"静脉输液","starttime":1492597536000,"userid":3}]
             * lastVitalSign : null
             * name : 张三123
             * patientid : A100233_1
             * totalVitalInOutList : []
             * wardId : 301
             */

            private int bedNo;
            private Object lastVitalSign;
            private String name;
            private String patientid;
            private String wardId;
            private List<ExecutingOrderListBean> executingOrderList;
            private List<TotalVitalInOutListBean> totalVitalInOutList;

            public int getBedNo() {
                return bedNo;
            }

            public void setBedNo(int bedNo) {
                this.bedNo = bedNo;
            }

            public Object getLastVitalSign() {
                return lastVitalSign;
            }

            public void setLastVitalSign(Object lastVitalSign) {
                this.lastVitalSign = lastVitalSign;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPatientid() {
                return patientid;
            }

            public void setPatientid(String patientid) {
                this.patientid = patientid;
            }

            public String getWardId() {
                return wardId;
            }

            public void setWardId(String wardId) {
                this.wardId = wardId;
            }

            public List<ExecutingOrderListBean> getExecutingOrderList() {
                return executingOrderList;
            }

            public void setExecutingOrderList(List<ExecutingOrderListBean> executingOrderList) {
                this.executingOrderList = executingOrderList;
            }

            public List<TotalVitalInOutListBean> getTotalVitalInOutList() {
                return totalVitalInOutList;
            }

            public void setTotalVitalInOutList(List<TotalVitalInOutListBean> totalVitalInOutList) {
                this.totalVitalInOutList = totalVitalInOutList;
            }

            public static class ExecutingOrderListBean implements Serializable{
                /**
                 * auditby :
                 * audittime : null
                 * bedNo : 1
                 * checkby : 3
                 * checktime : 1490178895000
                 * createdby :
                 * creationtime : null
                 * doctorOrders : [{"createdby":"","creationtime":null,"duration":"","endtime":null,"executename":"","hisid":null,"id":578,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"note":"","orderDose":"","orderExceptions":[],"orderExecuteRecords":[],"orderNoteList":null,"orderUnit":"","routeDesc":"","signRecord":null,"starttime":1490343724000,"status":"执行中","suborderduration":"","subordername":""}]
                 * doctorname :
                 * doctorno :
                 * executeby :
                 * executename :
                 * executetime : null
                 * exetime : null
                 * flag : null
                 * id : 9
                 * imageid : null
                 * isdeleted : 0
                 * lastupdatedby : 晨晨
                 * lastupdatetime : 1492498564000
                 * medicinedetails :
                 * mrnNo :
                 * note : 巴拉巴拉
                 * orderBloodProperty : []
                 * orderCheckProperty : []
                 * orderNoteList : []
                 * orderTypeName :
                 * orderbarcode : 250186093
                 * orderdose : 100
                 * orderduration : 1
                 * orderfrequency : 1 /日
                 * orderfrom :
                 * ordername : 葡萄糖 200ml 9
                 * orderresult :
                 * orderststus : 执行中
                 * ordertype : 1
                 * orderunit : ml
                 * patientName : 张三123
                 * patientid : A100233_1
                 * pharmList : []
                 * printUserName :
                 * printtime : 1493173135000
                 * printuser :
                 * routedesc : 静脉输液
                 * starttime : 1492597536000
                 * userid : 3
                 */

                private String auditby;
                private Object audittime;
                private int bedNo;
                private String checkby;
                private long checktime;
                private String createdby;
                private Object creationtime;
                private String doctorname;
                private String doctorno;
                private String executeby;
                private String executename;
                private Object executetime;
                private Object exetime;
                private Object flag;
                private int id;
                private Object imageid;
                private String isdeleted;
                private String lastupdatedby;
                private long lastupdatetime;
                private String medicinedetails;
                private String mrnNo;
                private String note;
                private String orderTypeName;
                private String orderbarcode;
                private String orderdose;
                private String orderduration;
                private String orderfrequency;
                private String orderfrom;
                private String ordername;
                private String orderresult;
                private String orderststus;
                private String ordertype;
                private String orderunit;
                private String patientName;
                private String patientid;
                private String printUserName;
                private long printtime;
                private String printuser;
                private String routedesc;
                private long starttime;
                private int userid;
                private List<DoctorOrdersBean> doctorOrders;
                private List<?> orderBloodProperty;
                private List<?> orderCheckProperty;
                private List<?> orderNoteList;
                private List<?> pharmList;

                public String getAuditby() {
                    return auditby;
                }

                public void setAuditby(String auditby) {
                    this.auditby = auditby;
                }

                public Object getAudittime() {
                    return audittime;
                }

                public void setAudittime(Object audittime) {
                    this.audittime = audittime;
                }

                public int getBedNo() {
                    return bedNo;
                }

                public void setBedNo(int bedNo) {
                    this.bedNo = bedNo;
                }

                public String getCheckby() {
                    return checkby;
                }

                public void setCheckby(String checkby) {
                    this.checkby = checkby;
                }

                public long getChecktime() {
                    return checktime;
                }

                public void setChecktime(long checktime) {
                    this.checktime = checktime;
                }

                public String getCreatedby() {
                    return createdby;
                }

                public void setCreatedby(String createdby) {
                    this.createdby = createdby;
                }

                public Object getCreationtime() {
                    return creationtime;
                }

                public void setCreationtime(Object creationtime) {
                    this.creationtime = creationtime;
                }

                public String getDoctorname() {
                    return doctorname;
                }

                public void setDoctorname(String doctorname) {
                    this.doctorname = doctorname;
                }

                public String getDoctorno() {
                    return doctorno;
                }

                public void setDoctorno(String doctorno) {
                    this.doctorno = doctorno;
                }

                public String getExecuteby() {
                    return executeby;
                }

                public void setExecuteby(String executeby) {
                    this.executeby = executeby;
                }

                public String getExecutename() {
                    return executename;
                }

                public void setExecutename(String executename) {
                    this.executename = executename;
                }

                public Object getExecutetime() {
                    return executetime;
                }

                public void setExecutetime(Object executetime) {
                    this.executetime = executetime;
                }

                public Object getExetime() {
                    return exetime;
                }

                public void setExetime(Object exetime) {
                    this.exetime = exetime;
                }

                public Object getFlag() {
                    return flag;
                }

                public void setFlag(Object flag) {
                    this.flag = flag;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public Object getImageid() {
                    return imageid;
                }

                public void setImageid(Object imageid) {
                    this.imageid = imageid;
                }

                public String getIsdeleted() {
                    return isdeleted;
                }

                public void setIsdeleted(String isdeleted) {
                    this.isdeleted = isdeleted;
                }

                public String getLastupdatedby() {
                    return lastupdatedby;
                }

                public void setLastupdatedby(String lastupdatedby) {
                    this.lastupdatedby = lastupdatedby;
                }

                public long getLastupdatetime() {
                    return lastupdatetime;
                }

                public void setLastupdatetime(long lastupdatetime) {
                    this.lastupdatetime = lastupdatetime;
                }

                public String getMedicinedetails() {
                    return medicinedetails;
                }

                public void setMedicinedetails(String medicinedetails) {
                    this.medicinedetails = medicinedetails;
                }

                public String getMrnNo() {
                    return mrnNo;
                }

                public void setMrnNo(String mrnNo) {
                    this.mrnNo = mrnNo;
                }

                public String getNote() {
                    return note;
                }

                public void setNote(String note) {
                    this.note = note;
                }

                public String getOrderTypeName() {
                    return orderTypeName;
                }

                public void setOrderTypeName(String orderTypeName) {
                    this.orderTypeName = orderTypeName;
                }

                public String getOrderbarcode() {
                    return orderbarcode;
                }

                public void setOrderbarcode(String orderbarcode) {
                    this.orderbarcode = orderbarcode;
                }

                public String getOrderdose() {
                    return orderdose;
                }

                public void setOrderdose(String orderdose) {
                    this.orderdose = orderdose;
                }

                public String getOrderduration() {
                    return orderduration;
                }

                public void setOrderduration(String orderduration) {
                    this.orderduration = orderduration;
                }

                public String getOrderfrequency() {
                    return orderfrequency;
                }

                public void setOrderfrequency(String orderfrequency) {
                    this.orderfrequency = orderfrequency;
                }

                public String getOrderfrom() {
                    return orderfrom;
                }

                public void setOrderfrom(String orderfrom) {
                    this.orderfrom = orderfrom;
                }

                public String getOrdername() {
                    return ordername;
                }

                public void setOrdername(String ordername) {
                    this.ordername = ordername;
                }

                public String getOrderresult() {
                    return orderresult;
                }

                public void setOrderresult(String orderresult) {
                    this.orderresult = orderresult;
                }

                public String getOrderststus() {
                    return orderststus;
                }

                public void setOrderststus(String orderststus) {
                    this.orderststus = orderststus;
                }

                public String getOrdertype() {
                    return ordertype;
                }

                public void setOrdertype(String ordertype) {
                    this.ordertype = ordertype;
                }

                public String getOrderunit() {
                    return orderunit;
                }

                public void setOrderunit(String orderunit) {
                    this.orderunit = orderunit;
                }

                public String getPatientName() {
                    return patientName;
                }

                public void setPatientName(String patientName) {
                    this.patientName = patientName;
                }

                public String getPatientid() {
                    return patientid;
                }

                public void setPatientid(String patientid) {
                    this.patientid = patientid;
                }

                public String getPrintUserName() {
                    return printUserName;
                }

                public void setPrintUserName(String printUserName) {
                    this.printUserName = printUserName;
                }

                public long getPrinttime() {
                    return printtime;
                }

                public void setPrinttime(long printtime) {
                    this.printtime = printtime;
                }

                public String getPrintuser() {
                    return printuser;
                }

                public void setPrintuser(String printuser) {
                    this.printuser = printuser;
                }

                public String getRoutedesc() {
                    return routedesc;
                }

                public void setRoutedesc(String routedesc) {
                    this.routedesc = routedesc;
                }

                public long getStarttime() {
                    return starttime;
                }

                public void setStarttime(long starttime) {
                    this.starttime = starttime;
                }

                public int getUserid() {
                    return userid;
                }

                public void setUserid(int userid) {
                    this.userid = userid;
                }

                public List<DoctorOrdersBean> getDoctorOrders() {
                    return doctorOrders;
                }

                public void setDoctorOrders(List<DoctorOrdersBean> doctorOrders) {
                    this.doctorOrders = doctorOrders;
                }

                public List<?> getOrderBloodProperty() {
                    return orderBloodProperty;
                }

                public void setOrderBloodProperty(List<?> orderBloodProperty) {
                    this.orderBloodProperty = orderBloodProperty;
                }

                public List<?> getOrderCheckProperty() {
                    return orderCheckProperty;
                }

                public void setOrderCheckProperty(List<?> orderCheckProperty) {
                    this.orderCheckProperty = orderCheckProperty;
                }

                public List<?> getOrderNoteList() {
                    return orderNoteList;
                }

                public void setOrderNoteList(List<?> orderNoteList) {
                    this.orderNoteList = orderNoteList;
                }

                public List<?> getPharmList() {
                    return pharmList;
                }

                public void setPharmList(List<?> pharmList) {
                    this.pharmList = pharmList;
                }

                public static class DoctorOrdersBean implements Serializable{
                    /**
                     * createdby :
                     * creationtime : null
                     * duration :
                     * endtime : null
                     * executename :
                     * hisid : null
                     * id : 578
                     * isdeleted :
                     * lastupdatedby :
                     * lastupdatetime : null
                     * note :
                     * orderDose :
                     * orderExceptions : []
                     * orderExecuteRecords : []
                     * orderNoteList : null
                     * orderUnit :
                     * routeDesc :
                     * signRecord : null
                     * starttime : 1490343724000
                     * status : 执行中
                     * suborderduration :
                     * subordername :
                     */

                    private String createdby;
                    private Object creationtime;
                    private String duration;
                    private Object endtime;
                    private String executename;
                    private Object hisid;
                    private int id;
                    private String isdeleted;
                    private String lastupdatedby;
                    private Object lastupdatetime;
                    private String note;
                    private String orderDose;
                    private Object orderNoteList;
                    private String orderUnit;
                    private String routeDesc;
                    private Object signRecord;
                    private long starttime;
                    private String status;
                    private String suborderduration;
                    private String subordername;
                    private List<?> orderExceptions;
                    private List<?> orderExecuteRecords;

                    public String getCreatedby() {
                        return createdby;
                    }

                    public void setCreatedby(String createdby) {
                        this.createdby = createdby;
                    }

                    public Object getCreationtime() {
                        return creationtime;
                    }

                    public void setCreationtime(Object creationtime) {
                        this.creationtime = creationtime;
                    }

                    public String getDuration() {
                        return duration;
                    }

                    public void setDuration(String duration) {
                        this.duration = duration;
                    }

                    public Object getEndtime() {
                        return endtime;
                    }

                    public void setEndtime(Object endtime) {
                        this.endtime = endtime;
                    }

                    public String getExecutename() {
                        return executename;
                    }

                    public void setExecutename(String executename) {
                        this.executename = executename;
                    }

                    public Object getHisid() {
                        return hisid;
                    }

                    public void setHisid(Object hisid) {
                        this.hisid = hisid;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getIsdeleted() {
                        return isdeleted;
                    }

                    public void setIsdeleted(String isdeleted) {
                        this.isdeleted = isdeleted;
                    }

                    public String getLastupdatedby() {
                        return lastupdatedby;
                    }

                    public void setLastupdatedby(String lastupdatedby) {
                        this.lastupdatedby = lastupdatedby;
                    }

                    public Object getLastupdatetime() {
                        return lastupdatetime;
                    }

                    public void setLastupdatetime(Object lastupdatetime) {
                        this.lastupdatetime = lastupdatetime;
                    }

                    public String getNote() {
                        return note;
                    }

                    public void setNote(String note) {
                        this.note = note;
                    }

                    public String getOrderDose() {
                        return orderDose;
                    }

                    public void setOrderDose(String orderDose) {
                        this.orderDose = orderDose;
                    }

                    public Object getOrderNoteList() {
                        return orderNoteList;
                    }

                    public void setOrderNoteList(Object orderNoteList) {
                        this.orderNoteList = orderNoteList;
                    }

                    public String getOrderUnit() {
                        return orderUnit;
                    }

                    public void setOrderUnit(String orderUnit) {
                        this.orderUnit = orderUnit;
                    }

                    public String getRouteDesc() {
                        return routeDesc;
                    }

                    public void setRouteDesc(String routeDesc) {
                        this.routeDesc = routeDesc;
                    }

                    public Object getSignRecord() {
                        return signRecord;
                    }

                    public void setSignRecord(Object signRecord) {
                        this.signRecord = signRecord;
                    }

                    public long getStarttime() {
                        return starttime;
                    }

                    public void setStarttime(long starttime) {
                        this.starttime = starttime;
                    }

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    public String getSuborderduration() {
                        return suborderduration;
                    }

                    public void setSuborderduration(String suborderduration) {
                        this.suborderduration = suborderduration;
                    }

                    public String getSubordername() {
                        return subordername;
                    }

                    public void setSubordername(String subordername) {
                        this.subordername = subordername;
                    }

                    public List<?> getOrderExceptions() {
                        return orderExceptions;
                    }

                    public void setOrderExceptions(List<?> orderExceptions) {
                        this.orderExceptions = orderExceptions;
                    }

                    public List<?> getOrderExecuteRecords() {
                        return orderExecuteRecords;
                    }

                    public void setOrderExecuteRecords(List<?> orderExecuteRecords) {
                        this.orderExecuteRecords = orderExecuteRecords;
                    }
                }
            }
            public static class TotalVitalInOutListBean implements Serializable{
                /**
                 * defineId : 12
                 * name : 尿量
                 * unit : ml
                 * value : 42
                 */

                private int defineId;
                private String name;
                private String unit;
                private String value;

                public int getDefineId() {
                    return defineId;
                }

                public void setDefineId(int defineId) {
                    this.defineId = defineId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }

        public static class EventListBean implements Serializable{
            /**
             * createdby : 系统
             * creationtime : 1482717727000
             * id : 1
             * isdeleted : 0
             * lastupdatedby :
             * lastupdatetime : null
             * sortno : 1
             * templatecontent : 接班看病人：患者意识清楚，双侧瞳孔等大等圆，直径<span style="color:red">XX</span>mm。对光反射灵敏持续心电、血压、呼吸、脉痒饱和度检测，机器运转正常。持续低流量吸氧。速度为<span style="color:red">XX</span>升／分钟。
             * templatename : 事件1
             * wardId : 301
             */

            private String createdby;
            private long creationtime;
            private int id;
            private String isdeleted;
            private String lastupdatedby;
            private Object lastupdatetime;
            private int sortno;
            private String templatecontent;
            private String templatename;
            private String wardId;

            public String getCreatedby() {
                return createdby;
            }

            public void setCreatedby(String createdby) {
                this.createdby = createdby;
            }

            public long getCreationtime() {
                return creationtime;
            }

            public void setCreationtime(long creationtime) {
                this.creationtime = creationtime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIsdeleted() {
                return isdeleted;
            }

            public void setIsdeleted(String isdeleted) {
                this.isdeleted = isdeleted;
            }

            public String getLastupdatedby() {
                return lastupdatedby;
            }

            public void setLastupdatedby(String lastupdatedby) {
                this.lastupdatedby = lastupdatedby;
            }

            public Object getLastupdatetime() {
                return lastupdatetime;
            }

            public void setLastupdatetime(Object lastupdatetime) {
                this.lastupdatetime = lastupdatetime;
            }

            public int getSortno() {
                return sortno;
            }

            public void setSortno(int sortno) {
                this.sortno = sortno;
            }

            public String getTemplatecontent() {
                return templatecontent;
            }

            public void setTemplatecontent(String templatecontent) {
                this.templatecontent = templatecontent;
            }

            public String getTemplatename() {
                return templatename;
            }

            public void setTemplatename(String templatename) {
                this.templatename = templatename;
            }

            public String getWardId() {
                return wardId;
            }

            public void setWardId(String wardId) {
                this.wardId = wardId;
            }
        }

        public static class WardVitalDefineBean implements Serializable{
            /**
             * connectionmode :
             * createdby :
             * creationtime : null
             * id : 14
             * imageid : null
             * inouttype :
             * inputtype :
             * isdeleted :
             * isnotemandatory :
             * isnumber :
             * lastupdatedby :
             * lastupdatetime : null
             * rangeend : 1000.0
             * rangestart : 1.0
             * signname : 饮水量
             * unitdesc : ml
             * vcode : V014
             * vitalNoteList : null
             */

            private String connectionmode;
            private String createdby;
            private Object creationtime;
            private int id;
            private Object imageid;
            private String inouttype;
            private String inputtype;
            private String isdeleted;
            private String isnotemandatory;
            private String isnumber;
            private String lastupdatedby;
            private Object lastupdatetime;
            private double rangeend;
            private double rangestart;
            private String signname;
            private String unitdesc;
            private String vcode;
            private Object vitalNoteList;

            public String getConnectionmode() {
                return connectionmode;
            }

            public void setConnectionmode(String connectionmode) {
                this.connectionmode = connectionmode;
            }

            public String getCreatedby() {
                return createdby;
            }

            public void setCreatedby(String createdby) {
                this.createdby = createdby;
            }

            public Object getCreationtime() {
                return creationtime;
            }

            public void setCreationtime(Object creationtime) {
                this.creationtime = creationtime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getImageid() {
                return imageid;
            }

            public void setImageid(Object imageid) {
                this.imageid = imageid;
            }

            public String getInouttype() {
                return inouttype;
            }

            public void setInouttype(String inouttype) {
                this.inouttype = inouttype;
            }

            public String getInputtype() {
                return inputtype;
            }

            public void setInputtype(String inputtype) {
                this.inputtype = inputtype;
            }

            public String getIsdeleted() {
                return isdeleted;
            }

            public void setIsdeleted(String isdeleted) {
                this.isdeleted = isdeleted;
            }

            public String getIsnotemandatory() {
                return isnotemandatory;
            }

            public void setIsnotemandatory(String isnotemandatory) {
                this.isnotemandatory = isnotemandatory;
            }

            public String getIsnumber() {
                return isnumber;
            }

            public void setIsnumber(String isnumber) {
                this.isnumber = isnumber;
            }

            public String getLastupdatedby() {
                return lastupdatedby;
            }

            public void setLastupdatedby(String lastupdatedby) {
                this.lastupdatedby = lastupdatedby;
            }

            public Object getLastupdatetime() {
                return lastupdatetime;
            }

            public void setLastupdatetime(Object lastupdatetime) {
                this.lastupdatetime = lastupdatetime;
            }

            public double getRangeend() {
                return rangeend;
            }

            public void setRangeend(double rangeend) {
                this.rangeend = rangeend;
            }

            public double getRangestart() {
                return rangestart;
            }

            public void setRangestart(double rangestart) {
                this.rangestart = rangestart;
            }

            public String getSignname() {
                return signname;
            }

            public void setSignname(String signname) {
                this.signname = signname;
            }

            public String getUnitdesc() {
                return unitdesc;
            }

            public void setUnitdesc(String unitdesc) {
                this.unitdesc = unitdesc;
            }

            public String getVcode() {
                return vcode;
            }

            public void setVcode(String vcode) {
                this.vcode = vcode;
            }

            public Object getVitalNoteList() {
                return vitalNoteList;
            }

            public void setVitalNoteList(Object vitalNoteList) {
                this.vitalNoteList = vitalNoteList;
            }
        }
    }
}
