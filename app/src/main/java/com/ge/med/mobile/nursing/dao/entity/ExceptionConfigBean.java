package com.ge.med.mobile.nursing.dao.entity;

import java.util.List;

/**
 * 网络访问实体Bean
 * Created by Administrator on 2016/12/7.
 */
public class ExceptionConfigBean {
    /**
     * status : 1
     * data : [{"createdby":"","creationtime":null,"exceptionDefine":{"exceptionname":"药品错误","handle":"处理措施","id":null,"type":"0"},"exceptiondefineid":1,"id":1,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"orderstatus":"","ordertype":"2"},{"createdby":"","creationtime":null,"exceptionDefine":{"exceptionname":"患者外出","handle":"警告,處分","id":null,"type":"1"},"exceptiondefineid":2,"id":2,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"orderstatus":"","ordertype":"2"}]
     * msg : 成功
     */

    private Integer status;
    private String msg;
    /**
     * createdby :
     * creationtime : null
     * exceptionDefine : {"exceptionname":"药品错误","handle":"处理措施","id":null,"type":"0"}
     * exceptiondefineid : 1
     * id : 1
     * isdeleted :
     * lastupdatedby :
     * lastupdatetime : null
     * orderstatus :
     * ordertype : 2
     */

    private List<DataBean> data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String createdby;
        private Object creationtime;
        /**
         * exceptionname : 药品错误
         * handle : 处理措施
         * id : null
         * type : 0
         */

        private ExceptionDefineBean exceptionDefine;
        private Integer exceptiondefineid;
        private Integer id;
        private String isdeleted;
        private String lastupdatedby;
        private long lastupdatetime;
        private String orderstatus;
        private String ordertype;

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

        public long getLastupdatetime() {
            return lastupdatetime;
        }

        public void setLastupdatetime(long lastupdatetime) {
            this.lastupdatetime = lastupdatetime;
        }

        public String getOrderstatus() {
            return orderstatus;
        }

        public void setOrderstatus(String orderstatus) {
            this.orderstatus = orderstatus;
        }

        public String getOrdertype() {
            return ordertype;
        }

        public void setOrdertype(String ordertype) {
            this.ordertype = ordertype;
        }

        public static class ExceptionDefineBean {
            private String exceptionname;
            private String handle;
            private Integer id;
            private String type;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
