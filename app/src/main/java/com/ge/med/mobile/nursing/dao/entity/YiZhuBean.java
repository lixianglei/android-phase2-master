package com.ge.med.mobile.nursing.dao.entity;

import com.ge.med.mobile.nursing.forjson.entity.BaseJSONBean;
import com.ge.med.mobile.nursing.forjson.entity.IBaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * 网络访问实体JSON解析Bean
 * Created by Administrator on 2016/11/24.
 */
public class YiZhuBean implements BaseJSONBean {


    /**
     * status : 1
     * data : [{"bedNo":null,"createdby":"系统","creationtime":1480817186468,"doctorOrders":[{"createdby":"系统","creationtime":1480817247267,"endtime":null,"hisid":1,"id":1,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"orderExceptions":[{"createdby":"系统","creationtime":1480817254472,"exceptionDefine":{"exceptionname":"","handle":"","id":1},"exceptiondefineid":null,"exerecordid":null,"id":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"note":"","orderid":1,"time":null,"userid":1}],"orderExecuteRecords":[{"actualvolume":"","createdby":"系统","creationtime":1480817271969,"dropspeed":"","exetime":null,"id":1,"Integerervaltime":"","isdeleted":"0","jobtype":"","lastupdatedby":"","lastupdatetime":null,"orderExceptions":null,"orderid":1,"userName1":"AA","userName2":"","userid":1,"userid2":null}],"starttime":null,"status":"","suborderduration":"","subordername":""}],"exetime":null,"id":1,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"note":"","orderdose":"","orderduration":"","orderfrequency":"","orderfrom":"","ordername":"","orderststus":"","ordertype":"","orderunit":"","patientName":"","patientid":"AA01","routedesc":"","starttime":null,"userid":1},{"bedNo":null,"createdby":"系统","creationtime":1480820661386,"doctorOrders":[{"createdby":"系统","creationtime":1480820687164,"endtime":null,"hisid":2,"id":2,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"orderExceptions":[{"createdby":"系统","creationtime":1480820717593,"exceptionDefine":{"exceptionname":"","handle":"","id":2},"exceptiondefineid":null,"exerecordid":null,"id":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"note":"","orderid":2,"time":null,"userid":1}],"orderExecuteRecords":[{"actualvolume":"","createdby":"系统","creationtime":1480820733461,"dropspeed":"","exetime":null,"id":2,"Integerervaltime":"","isdeleted":"0","jobtype":"","lastupdatedby":"","lastupdatetime":null,"orderExceptions":null,"orderid":2,"userName1":"AA","userName2":"","userid":1,"userid2":null}],"starttime":null,"status":"","suborderduration":"","subordername":""}],"exetime":null,"id":2,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"note":"","orderdose":"","orderduration":"","orderfrequency":"","orderfrom":"","ordername":"","orderststus":"","ordertype":"","orderunit":"","patientName":"","patientid":"AA01","routedesc":"","starttime":null,"userid":1}]
     * msg : 成功
     */

    private Integer status;
    private String msg;
    /**
     * bedNo : null
     * createdby : 系统
     * creationtime : 1480817186468
     * doctorOrders : [{"createdby":"系统","creationtime":1480817247267,"endtime":null,"hisid":1,"id":1,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"orderExceptions":[{"createdby":"系统","creationtime":1480817254472,"exceptionDefine":{"exceptionname":"","handle":"","id":1},"exceptiondefineid":null,"exerecordid":null,"id":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"note":"","orderid":1,"time":null,"userid":1}],"orderExecuteRecords":[{"actualvolume":"","createdby":"系统","creationtime":1480817271969,"dropspeed":"","exetime":null,"id":1,"Integerervaltime":"","isdeleted":"0","jobtype":"","lastupdatedby":"","lastupdatetime":null,"orderExceptions":null,"orderid":1,"userName1":"AA","userName2":"","userid":1,"userid2":null}],"starttime":null,"status":"","suborderduration":"","subordername":""}]
     * exetime : null
     * id : 1
     * isdeleted : 0
     * lastupdatedby :
     * lastupdatetime : null
     * note :
     * orderdose :
     * orderduration :
     * orderfrequency :
     * orderfrom :
     * ordername :
     * orderststus :
     * ordertype :
     * orderunit :
     * patientName :
     * patientid : AA01
     * routedesc :
     * starttime : null
     * userid : 1
     */

    private List<DataBean> data;

    @Override
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String getCallName() {
        return "load Doctor Order";
    }

    public static class DataBean implements Serializable, IBaseBean {
        private Integer dbId;
        private Object bedNo;
        private String createdby;
        private Long creationtime;
        private Long exetime;
        private String executeby;
        private Integer id;
        private String isdeleted;
        private String lastupdatedby;
        private Long lastupdatetime;
        private String note;
        private String orderdose;
        private String orderduration;
        private String orderfrequency;//频次
        private String orderfrom;
        private String ordername;
        private String orderststus;
        private String ordertype;
        private String orderunit;
        private String patientName;
        private String patientid;
        private String routedesc;//途径
        private Long starttime;
        private Integer userid;
        private String doctorname;
        private String checkby;
        private Long checktime;
        private String imageid;
        private String orderresult;//医嘱结果
        private String orderbarcode;//医嘱条码code
        private String yiChangXinXi;//核对医嘱
        private Boolean yiChang; //核对医嘱 用
        private List<OrderBloodPropertyBean> orderBloodProperty;
        private List<OrderCheckPropertyBean> orderCheckProperty;
        private List<DoctorOrdersBean> doctorOrders;
        private List<OrderPharm> pharmList;
        private List<OrderNoteListBean> orderNoteList;

        public List<OrderNoteListBean> getOrderNoteList() {
            return orderNoteList;
        }

        public void setOrderNoteList(List<OrderNoteListBean> orderNoteList) {
            this.orderNoteList = orderNoteList;
        }

        public List<OrderPharm> getPharmList() {
            return pharmList;
        }

        public void setPharmList(List<OrderPharm> pharmList) {
            this.pharmList = pharmList;
        }

        public String getOrderresult() {
            return orderresult;
        }

        public void setOrderresult(String orderresult) {
            this.orderresult = orderresult;
        }


        public String getImageid() {
            return imageid;
        }

        public void setImageid(String imageid) {
            this.imageid = imageid;
        }

        public String getCheckby() {
            return checkby;
        }

        public void setCheckby(String checkby) {
            this.checkby = checkby;
        }

        public Long getChecktime() {
            return checktime;
        }

        public void setChecktime(Long checktime) {
            this.checktime = checktime;
        }

        public Boolean getYiChang() {
            return yiChang;
        }

        public void setYiChang(Boolean yiChang) {
            this.yiChang = yiChang;
        }

        public String getYiChangXinXi() {
            return yiChangXinXi;
        }

        public void setYiChangXinXi(String yiChangXinXi) {
            this.yiChangXinXi = yiChangXinXi;
        }

        public String getExecuteby() {
            return executeby;
        }

        public void setExecuteby(String executeby) {
            this.executeby = executeby;
        }

        public String getDoctorname() {
            return doctorname;
        }

        public void setDoctorname(String doctorname) {
            this.doctorname = doctorname;
        }

        public String getOrderbarcode() {
            return orderbarcode;
        }

        public void setOrderbarcode(String orderbarcode) {
            this.orderbarcode = orderbarcode;
        }


        public List<OrderBloodPropertyBean> getOrderBloodProperty() {
            return orderBloodProperty;
        }

        public void setOrderBloodProperty(List<OrderBloodPropertyBean> orderBloodProperty) {
            this.orderBloodProperty = orderBloodProperty;
        }

        public List<OrderCheckPropertyBean> getOrderCheckProperty() {
            return orderCheckProperty;
        }

        public void setOrderCheckProperty(List<OrderCheckPropertyBean> orderCheckProperty) {
            this.orderCheckProperty = orderCheckProperty;
        }

        public Integer getDbId() {
            return dbId;
        }

        @Override
        public void setDbId(Integer dbId) {
            this.dbId = dbId;
        }

        public static class OrderCheckPropertyBean implements Serializable {

            /**
             * createdby : 系统
             * creationtime : 1480921490157
             * hisorderid : 1
             * id : 1
             * isdeleted : 0
             * lastupdatedby :
             * lastupdatetime : null
             * samplevolume : 11
             * specialrequirement : 特殊要求
             * testtime : 1480921505000
             * testtubenumber : 5411023243
             * testtubetype : 红色
             */

            private String createdby;
            private Long creationtime;
            private Integer hisorderid;
            private Integer id;
            private String isdeleted;
            private String samplevolume;
            private String specialrequirement;
            private Long testtime;
            private String testtubenumber;
            private String testtubetype;
            private Integer sort;

            public Integer getSort() {
                return sort;
            }

            public void setSort(Integer sort) {
                this.sort = sort;
            }

            public String getCreatedby() {
                return createdby;
            }

            public void setCreatedby(String createdby) {
                this.createdby = createdby;
            }

            public Long getCreationtime() {
                return creationtime;
            }

            public void setCreationtime(Long creationtime) {
                this.creationtime = creationtime;
            }

            public Integer getHisorderid() {
                return hisorderid;
            }

            public void setHisorderid(Integer hisorderid) {
                this.hisorderid = hisorderid;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getIsdeleted() {
                return isdeleted;
            }

            public void setIsdeleted(String isdeleted) {
                this.isdeleted = isdeleted;
            }

            public String getSamplevolume() {
                return samplevolume;
            }

            public void setSamplevolume(String samplevolume) {
                this.samplevolume = samplevolume;
            }

            public String getSpecialrequirement() {
                return specialrequirement;
            }

            public void setSpecialrequirement(String specialrequirement) {
                this.specialrequirement = specialrequirement;
            }

            public Long getTesttime() {
                return testtime;
            }

            public void setTesttime(Long testtime) {
                this.testtime = testtime;
            }

            public String getTesttubenumber() {
                return testtubenumber;
            }

            public void setTesttubenumber(String testtubenumber) {
                this.testtubenumber = testtubenumber;
            }

            public String getTesttubetype() {
                return testtubetype;
            }

            public void setTesttubetype(String testtubetype) {
                this.testtubetype = testtubetype;
            }

        }

        public static class OrderBloodPropertyBean implements Serializable {
            /**
             * bloodbagbloodrh : 阳性
             * bloodbagbloodtype : B型
             * bloodbagno : 1345001
             * bloodbagtypename : 悬浮红细胞
             * bloodvolume : 2.0u
             * createdby : 系统
             * creationtime : 1480921578666
             * hisorderid : 1
             * id : 1
             * isdeleted : 0
             * lastupdatedby :
             * lastupdatetime : null
             * leavestoragetime : 1480921604000
             * productno : 1230045
             */

            private String bloodbagbloodrh;
            private String bloodbagbloodtype;
            private String bloodbagno;
            private String bloodbagtypename;
            private String bloodvolume;
            private String createdby;
            private Long creationtime;
            private Integer hisorderid;
            private Integer id;
            private String isdeleted;
            private Long leavestoragetime;
            private String productno;
            private String scheduledbloodtime;
            private String bloodtype;
            private String scheduledbloodvariety;
            private String scheduledbloodvolume;
            private String crossbloodvalue;
            private String bloodtubetype;
            private String bloodtubenumber;

            public String getScheduledbloodtime() {
                return scheduledbloodtime;
            }

            public void setScheduledbloodtime(String scheduledbloodtime) {
                this.scheduledbloodtime = scheduledbloodtime;
            }

            public String getBloodtype() {
                return bloodtype;
            }

            public void setBloodtype(String bloodtype) {
                this.bloodtype = bloodtype;
            }

            public String getScheduledbloodvariety() {
                return scheduledbloodvariety;
            }

            public void setScheduledbloodvariety(String scheduledbloodvariety) {
                this.scheduledbloodvariety = scheduledbloodvariety;
            }

            public String getScheduledbloodvolume() {
                return scheduledbloodvolume;
            }

            public void setScheduledbloodvolume(String scheduledbloodvolume) {
                this.scheduledbloodvolume = scheduledbloodvolume;
            }

            public String getCrossbloodvalue() {
                return crossbloodvalue;
            }

            public void setCrossbloodvalue(String crossbloodvalue) {
                this.crossbloodvalue = crossbloodvalue;
            }

            public String getBloodtubetype() {
                return bloodtubetype;
            }

            public void setBloodtubetype(String bloodtubetype) {
                this.bloodtubetype = bloodtubetype;
            }

            public String getBloodtubenumber() {
                return bloodtubenumber;
            }

            public void setBloodtubenumber(String bloodtubenumber) {
                this.bloodtubenumber = bloodtubenumber;
            }

            public String getBloodbagbloodrh() {
                return bloodbagbloodrh;
            }

            public void setBloodbagbloodrh(String bloodbagbloodrh) {
                this.bloodbagbloodrh = bloodbagbloodrh;
            }

            public String getBloodbagbloodtype() {
                return bloodbagbloodtype;
            }

            public void setBloodbagbloodtype(String bloodbagbloodtype) {
                this.bloodbagbloodtype = bloodbagbloodtype;
            }

            public String getBloodbagno() {
                return bloodbagno;
            }

            public void setBloodbagno(String bloodbagno) {
                this.bloodbagno = bloodbagno;
            }

            public String getBloodbagtypename() {
                return bloodbagtypename;
            }

            public void setBloodbagtypename(String bloodbagtypename) {
                this.bloodbagtypename = bloodbagtypename;
            }

            public String getBloodvolume() {
                return bloodvolume;
            }

            public void setBloodvolume(String bloodvolume) {
                this.bloodvolume = bloodvolume;
            }

            public String getCreatedby() {
                return createdby;
            }

            public void setCreatedby(String createdby) {
                this.createdby = createdby;
            }

            public Long getCreationtime() {
                return creationtime;
            }

            public void setCreationtime(Long creationtime) {
                this.creationtime = creationtime;
            }

            public Integer getHisorderid() {
                return hisorderid;
            }

            public void setHisorderid(Integer hisorderid) {
                this.hisorderid = hisorderid;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getIsdeleted() {
                return isdeleted;
            }

            public void setIsdeleted(String isdeleted) {
                this.isdeleted = isdeleted;
            }

            public Long getLeavestoragetime() {
                return leavestoragetime;
            }

            public void setLeavestoragetime(Long leavestoragetime) {
                this.leavestoragetime = leavestoragetime;
            }

            public String getProductno() {
                return productno;
            }

            public void setProductno(String productno) {
                this.productno = productno;
            }

        }


        /**
         * createdby : 系统
         * creationtime : 1480817247267
         * endtime : null
         * hisid : 1
         * id : 1
         * isdeleted : 0
         * lastupdatedby :
         * lastupdatetime : null
         * orderExceptions : [{"createdby":"系统","creationtime":1480817254472,"exceptionDefine":{"exceptionname":"","handle":"","id":1},"exceptiondefineid":null,"exerecordid":null,"id":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"note":"","orderid":1,"time":null,"userid":1}]
         * orderExecuteRecords : [{"actualvolume":"","createdby":"系统","creationtime":1480817271969,"dropspeed":"","exetime":null,"id":1,"Integerervaltime":"","isdeleted":"0","jobtype":"","lastupdatedby":"","lastupdatetime":null,"orderExceptions":null,"orderid":1,"userName1":"AA","userName2":"","userid":1,"userid2":null}]
         * starttime : null
         * status :
         * suborderduration :
         * subordername :
         */


        public Object getBedNo() {
            return bedNo;
        }

        public void setBedNo(Object bedNo) {
            this.bedNo = bedNo;
        }

        public String getCreatedby() {
            return createdby;
        }

        public void setCreatedby(String createdby) {
            this.createdby = createdby;
        }

        public Long getCreationtime() {
            return creationtime;
        }

        public void setCreationtime(Long creationtime) {
            this.creationtime = creationtime;
        }

        public Long getExetime() {
            return exetime;
        }

        public void setExetime(Long exetime) {
            this.exetime = exetime;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
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

        public Long getLastupdatetime() {
            return lastupdatetime;
        }

        public void setLastupdatetime(Long lastupdatetime) {
            this.lastupdatetime = lastupdatetime;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
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

        public String getRoutedesc() {
            return routedesc;
        }

        public void setRoutedesc(String routedesc) {
            this.routedesc = routedesc;
        }

        public Long getStarttime() {
            return starttime;
        }

        public void setStarttime(Long starttime) {
            this.starttime = starttime;
        }

        public Integer getUserid() {
            return userid;
        }

        public void setUserid(Integer userid) {
            this.userid = userid;
        }

        public List<DoctorOrdersBean> getDoctorOrders() {
            return doctorOrders;
        }

        public void setDoctorOrders(List<DoctorOrdersBean> doctorOrders) {
            this.doctorOrders = doctorOrders;
        }

        public static class DoctorOrdersBean implements Serializable {
            private String createdby;
            private Long creationtime;
            private Long endtime;
            private Integer hisid;
            private Integer id;
            private String isdeleted;
            private String lastupdatedby;
            private Long lastupdatetime;
            private Long starttime;
            private String status;
            private String suborderduration;
            private String subordername;
            private List<OrderExceptionsBean> orderExceptions;
            private List<OrderExecuteRecordsBean> orderExecuteRecords;

            /**
             * createdby : 系统
             * creationtime : 1480817254472
             * exceptionDefine : {"exceptionname":"","handle":"","id":1}
             * exceptiondefineid : null
             * exerecordid : null
             * id : null
             * isdeleted : 0
             * lastupdatedby :
             * lastupdatetime : null
             * note :
             * orderid : 1
             * time : null
             * userid : 1
             */

            /**
             * actualvolume :
             * createdby : 系统
             * creationtime : 1480817271969
             * dropspeed :
             * exetime : null
             * id : 1
             * Integerervaltime :
             * isdeleted : 0
             * jobtype :
             * lastupdatedby :
             * lastupdatetime : null
             * orderExceptions : null
             * orderid : 1
             * userName1 : AA
             * userName2 :
             * userid : 1
             * userid2 : null
             */


            public String getCreatedby() {
                return createdby;
            }

            public void setCreatedby(String createdby) {
                this.createdby = createdby;
            }

            public Long getCreationtime() {
                return creationtime;
            }

            public void setCreationtime(Long creationtime) {
                this.creationtime = creationtime;
            }

            public Long getEndtime() {
                return endtime;
            }

            public void setEndtime(Long endtime) {
                this.endtime = endtime;
            }

            public Integer getHisid() {
                return hisid;
            }

            public void setHisid(Integer hisid) {
                this.hisid = hisid;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
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

            public Long getLastupdatetime() {
                return lastupdatetime;
            }

            public void setLastupdatetime(Long lastupdatetime) {
                this.lastupdatetime = lastupdatetime;
            }

            public Long getStarttime() {
                return starttime;
            }

            public void setStarttime(Long starttime) {
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

            public List<OrderExceptionsBean> getOrderExceptions() {
                return orderExceptions;
            }

            public void setOrderExceptions(List<OrderExceptionsBean> orderExceptions) {
                this.orderExceptions = orderExceptions;
            }

            public List<OrderExecuteRecordsBean> getOrderExecuteRecords() {
                return orderExecuteRecords;
            }

            public void setOrderExecuteRecords(List<OrderExecuteRecordsBean> orderExecuteRecords) {
                this.orderExecuteRecords = orderExecuteRecords;
            }

            public static class OrderExceptionsBean implements Serializable {
                private String createdby;
                private Long creationtime;
                /**
                 * exceptionname :
                 * handle :
                 * id : 1
                 */

                private ExceptionDefineBean exceptionDefine;
                private Integer exceptiondefineid;
                private String exerecordid;
                private Integer id;
                private String isdeleted;
                private String lastupdatedby;
                private Long lastupdatetime;
                private String note;
                private Integer orderid;
                private Long time;
                private Integer userid;
                private String treatmentmeasures;

                public String getTreatmentmeasures() {
                    return treatmentmeasures;
                }

                public void setTreatmentmeasures(String treatmentmeasures) {
                    this.treatmentmeasures = treatmentmeasures;
                }

                public String getCreatedby() {
                    return createdby;
                }

                public void setCreatedby(String createdby) {
                    this.createdby = createdby;
                }

                public Long getCreationtime() {
                    return creationtime;
                }

                public void setCreationtime(Long creationtime) {
                    this.creationtime = creationtime;
                }

                public ExceptionDefineBean getExceptionDefine() {
                    return exceptionDefine;
                }

                public void setExceptionDefine(ExceptionDefineBean exceptionDefine) {
                    this.exceptionDefine = exceptionDefine;
                }

                public Integer getExceptiondefineid() {
                    return exceptiondefineid;
                }

                public void setExceptiondefineid(Integer exceptiondefineid) {
                    this.exceptiondefineid = exceptiondefineid;
                }

                public String getExerecordid() {
                    return exerecordid;
                }

                public void setExerecordid(String exerecordid) {
                    this.exerecordid = exerecordid;
                }

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
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

                public Long getLastupdatetime() {
                    return lastupdatetime;
                }

                public void setLastupdatetime(Long lastupdatetime) {
                    this.lastupdatetime = lastupdatetime;
                }

                public String getNote() {
                    return note;
                }

                public void setNote(String note) {
                    this.note = note;
                }

                public Integer getOrderid() {
                    return orderid;
                }

                public void setOrderid(Integer orderid) {
                    this.orderid = orderid;
                }

                public Long getTime() {
                    return time;
                }

                public void setTime(Long time) {
                    this.time = time;
                }

                public Integer getUserid() {
                    return userid;
                }

                public void setUserid(Integer userid) {
                    this.userid = userid;
                }

                public static class ExceptionDefineBean implements Serializable {
                    private String exceptionname;
                    private String handle;
                    private Integer id;

                    public String getExceptionname() {
                        return exceptionname;
                    }

                    public void setExceptionname(String exceptionname) {
                        this.exceptionname = exceptionname;
                    }

                    public String getHandle() {
                        return handle;
                    }

                    public void setHandle(String handle) {
                        this.handle = handle;
                    }

                    public Integer getId() {
                        return id;
                    }

                    public void setId(Integer id) {
                        this.id = id;
                    }
                }
            }

            public static class OrderExecuteRecordsBean implements Serializable {
                private String actualvolume;
                private String createdby;
                private Long creationtime;
                private String dropspeed;
                private Long exetime;
                private Integer id;
                private String Integerervaltime;
                private String isdeleted;
                private String jobtype;
                private String lastupdatedby;
                private Long lastupdatetime;
                private List<OrderExceptionsBean> orderExceptionList;
                private Integer orderid;
                private String userName1;
                private String userName2;
                private String userid;
                private String userid2;
                private Integer pharmStatus;
                private Integer pharmId;
                private String jobStatus;//工作状态  记录换血袋记录

                public String getJobStatus() {
                    return jobStatus;
                }

                public void setJobStatus(String jobStatus) {
                    this.jobStatus = jobStatus;
                }

                public Integer getPharmStatus() {
                    return pharmStatus;
                }

                public void setPharmStatus(Integer pharmStatus) {
                    this.pharmStatus = pharmStatus;
                }

                public Integer getPharmId() {
                    return pharmId;
                }

                public void setPharmId(Integer pharmId) {
                    this.pharmId = pharmId;
                }

                public String getActualvolume() {
                    return actualvolume;
                }

                public void setActualvolume(String actualvolume) {
                    this.actualvolume = actualvolume;
                }

                public String getCreatedby() {
                    return createdby;
                }

                public void setCreatedby(String createdby) {
                    this.createdby = createdby;
                }

                public Long getCreationtime() {
                    return creationtime;
                }

                public void setCreationtime(Long creationtime) {
                    this.creationtime = creationtime;
                }

                public String getDropspeed() {
                    return dropspeed;
                }

                public void setDropspeed(String dropspeed) {
                    this.dropspeed = dropspeed;
                }

                public Long getExetime() {
                    return exetime;
                }

                public void setExetime(Long exetime) {
                    this.exetime = exetime;
                }

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public String getIntervaltime() {
                    return Integerervaltime;
                }

                public void setIntervaltime(String Integerervaltime) {
                    this.Integerervaltime = Integerervaltime;
                }

                public String getIsdeleted() {
                    return isdeleted;
                }

                public void setIsdeleted(String isdeleted) {
                    this.isdeleted = isdeleted;
                }

                public String getJobtype() {
                    return jobtype;
                }

                public void setJobtype(String jobtype) {
                    this.jobtype = jobtype;
                }

                public String getLastupdatedby() {
                    return lastupdatedby;
                }

                public void setLastupdatedby(String lastupdatedby) {
                    this.lastupdatedby = lastupdatedby;
                }

                public Long getLastupdatetime() {
                    return lastupdatetime;
                }

                public void setLastupdatetime(Long lastupdatetime) {
                    this.lastupdatetime = lastupdatetime;
                }

                public List<OrderExceptionsBean> getOrderExceptionList() {
                    return orderExceptionList;
                }

                public void setOrderExceptionList(List<OrderExceptionsBean> orderExceptionList) {
                    this.orderExceptionList = orderExceptionList;
                }

                public Integer getOrderid() {
                    return orderid;
                }

                public void setOrderid(Integer orderid) {
                    this.orderid = orderid;
                }

                public String getUserName1() {
                    return userName1;
                }

                public void setUserName1(String userName1) {
                    this.userName1 = userName1;
                }

                public String getUserName2() {
                    return userName2;
                }

                public void setUserName2(String userName2) {
                    this.userName2 = userName2;
                }

                public String getUserid() {
                    return userid;
                }

                public void setUserid(String userid) {
                    this.userid = userid;
                }

                public String getUserid2() {
                    return userid2;
                }

                public void setUserid2(String userid2) {
                    this.userid2 = userid2;
                }
            }
        }
    }

    /**
     * Created by Administrator on 2017/3/20.
     */
    public static class OrderPharm implements Serializable {
        private Integer id;
        private Integer docorderId;//医嘱ID
        private Integer pharmId;//药品表ID
        private String frequency;//频次
        private String dose;//剂量
        private String unit;//单位
        private String pharmName;//药品名称

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getDocorderId() {
            return docorderId;
        }

        public void setDocorderId(Integer docorderId) {
            this.docorderId = docorderId;
        }

        public Integer getPharmId() {
            return pharmId;
        }

        public void setPharmId(Integer pharmId) {
            this.pharmId = pharmId;
        }

        public String getFrequency() {
            return frequency;
        }

        public void setFrequency(String frequency) {
            this.frequency = frequency;
        }

        public String getDose() {
            return dose;
        }

        public void setDose(String dose) {
            this.dose = dose;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getPharmName() {
            return pharmName;
        }

        public void setPharmName(String pharmName) {
            this.pharmName = pharmName;
        }
    }

    /**
     * Created by Administrator on 2017/3/31.
     */
    public static class OrderNoteListBean implements Serializable {
        private Integer id;

        private String notevalue;

        private Long noterecordtime;

        private Integer hisorderid;

        private Integer nursingrecordid;
        private String noteStatus;

        public String getNoteStatus() {
            return noteStatus;
        }

        public void setNoteStatus(String noteStatus) {
            this.noteStatus = noteStatus;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNotevalue() {
            return notevalue;
        }

        public void setNotevalue(String notevalue) {
            this.notevalue = notevalue;
        }

        public Long getNoterecordtime() {
            return noterecordtime;
        }

        public void setNoterecordtime(Long noterecordtime) {
            this.noterecordtime = noterecordtime;
        }

        public Integer getHisorderid() {
            return hisorderid;
        }

        public void setHisorderid(Integer hisorderid) {
            this.hisorderid = hisorderid;
        }

        public Integer getNursingrecordid() {
            return nursingrecordid;
        }

        public void setNursingrecordid(Integer nursingrecordid) {
            this.nursingrecordid = nursingrecordid;
        }
    }
}
