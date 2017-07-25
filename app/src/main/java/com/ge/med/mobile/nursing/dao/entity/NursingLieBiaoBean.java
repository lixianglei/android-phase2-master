package com.ge.med.mobile.nursing.dao.entity;

import java.util.List;

/**定义网络访问实体JSON解析Bean
 * Created by xxl on 2017/4/5.
 */

public class NursingLieBiaoBean  {

    private int status;
    private DataBean data;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private List<AssessDefine.DataBean> assessDefineList;
        private List<NursingRecordDefineListBean> nursingRecordDefineList;

        public List<AssessDefine.DataBean> getAssessDefineList() {
            return assessDefineList;
        }

        public void setAssessDefineList(List<AssessDefine.DataBean> assessDefineList) {
            this.assessDefineList = assessDefineList;
        }

        public List<NursingRecordDefineListBean> getNursingRecordDefineList() {
            return nursingRecordDefineList;
        }

        public void setNursingRecordDefineList(List<NursingRecordDefineListBean> nursingRecordDefineList) {
            this.nursingRecordDefineList = nursingRecordDefineList;
        }



        public static class NursingRecordDefineListBean {
            /**
             * id : 1
             * name : 护理记录
             * type : 0
             * wardid : 301
             */

            private int id;
            private String name;
            private String type;
            private String wardid;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getWardid() {
                return wardid;
            }

            public void setWardid(String wardid) {
                this.wardid = wardid;
            }
        }
    }
}
