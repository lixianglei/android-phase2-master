package com.ge.med.mobile.nursing.dao.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 网络访问实体JSON解析Bean
 * Created by xxl on 2017/4/12.
 */

public class XuanJiaoBean implements Serializable {
    /**
     * msg : 成功
     * data : [{"id":1,"title":"入院宣教","parentId":0,"leaves":1,"sort":1,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[{"id":7,"title":"病房环境及设施（呼叫、淋浴、用电等）使用","parentId":1,"leaves":2,"sort":1,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":8,"title":"制度介绍（作息、探视、陪住、物品管理等）","parentId":1,"leaves":2,"sort":2,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":9,"title":"订餐方法及饮食要求","parentId":1,"leaves":2,"sort":3,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":10,"title":"标本留取方法","parentId":1,"leaves":2,"sort":4,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":11,"title":"人员介绍（主管医生、主管护士、护士长）","parentId":1,"leaves":2,"sort":5,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]}]},{"id":2,"title":"检查检验宣教","parentId":0,"leaves":1,"sort":2,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":3,"title":"手术宣教","parentId":0,"leaves":1,"sort":3,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[{"id":12,"title":"脊柱","parentId":3,"leaves":2,"sort":1,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[{"id":18,"title":"麻醉","parentId":12,"leaves":3,"sort":1,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[{"id":36,"title":"腰椎间盘突（膨）出症","parentId":18,"leaves":4,"sort":1,"detailpagetitle":"病房环境及设施（呼叫、淋浴、CCC）","detailpagetext":"很多病人和病人家属对手术前不能吃饭、喝水不理解、不明白、部分病人和家长怕委屈了自己和孩子，甚至有的人认为在手术前要吃的饱饱的，才能更好的忍耐手术，\u201c饿着肚子手术是会受不了的\u201d。\\r\\n因此，有时由于病人或家属不听医师的劝告或忘记了护士的嘱咐而在手术前吃东西，而不得不停止这次手术，择期再做。","detailpageimgpath":"http://192.168.140.253:8080/intelli/images/edu/edu.png","edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":37,"title":"腰椎间盘突（膨）出症的症状是什么","parentId":18,"leaves":4,"sort":2,"detailpagetitle":"详细标题XX","detailpagetext":"文本XXX","detailpageimgpath":"http://192.168.140.253:8080/intelli/images/edu/xxx.png","edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":38,"title":"腰椎间盘突（膨）出症好发人群","parentId":18,"leaves":4,"sort":3,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":39,"title":"腰椎间盘突（膨）出症的治疗原则是什么","parentId":18,"leaves":4,"sort":4,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]}]},{"id":19,"title":"手术前","parentId":12,"leaves":3,"sort":2,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[{"id":40,"title":"手术XX突（膨）出症","parentId":19,"leaves":4,"sort":1,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":41,"title":"手术CC突（膨）出症的症状是什么","parentId":19,"leaves":4,"sort":2,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":42,"title":"手术DD突（膨）出症好发人群","parentId":19,"leaves":4,"sort":3,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":43,"title":"手术FF突（膨）出症的治疗原则是什么","parentId":19,"leaves":4,"sort":4,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]}]},{"id":20,"title":"手术后","parentId":12,"leaves":3,"sort":3,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]}]},{"id":13,"title":"颈椎前后路手术","parentId":3,"leaves":2,"sort":2,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[{"id":21,"title":"麻醉","parentId":13,"leaves":3,"sort":1,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":22,"title":"手术前","parentId":13,"leaves":3,"sort":2,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":23,"title":"手术后","parentId":13,"leaves":3,"sort":3,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]}]},{"id":14,"title":"颈前路齿突骨折中空加压螺钉内固定术","parentId":3,"leaves":2,"sort":3,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[{"id":24,"title":"麻醉","parentId":14,"leaves":3,"sort":1,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":25,"title":"手术前","parentId":14,"leaves":3,"sort":2,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":26,"title":"手术后","parentId":14,"leaves":3,"sort":3,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]}]},{"id":15,"title":"经皮前路齿状突螺钉内固定术","parentId":3,"leaves":2,"sort":4,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[{"id":27,"title":"麻醉","parentId":15,"leaves":3,"sort":1,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":28,"title":"手术前","parentId":15,"leaves":3,"sort":2,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":29,"title":"手术后","parentId":15,"leaves":3,"sort":3,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]}]},{"id":16,"title":"颅盆环牵引术","parentId":3,"leaves":2,"sort":5,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[{"edudefineid":16,"edumodetext":"讲解","edurecordpk":2,"eduresulttext":"未掌握","edutime":1491983536000,"eduuserid":4,"status":"0","userName":"张扬张扬"}],"child":[{"id":30,"title":"麻醉","parentId":16,"leaves":3,"sort":1,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":31,"title":"手术前","parentId":16,"leaves":3,"sort":2,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":32,"title":"手术后","parentId":16,"leaves":3,"sort":3,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]}]},{"id":17,"title":"成人脊髓栓系综合的手术","parentId":3,"leaves":2,"sort":6,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[{"id":33,"title":"麻醉","parentId":17,"leaves":3,"sort":1,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":34,"title":"手术前","parentId":17,"leaves":3,"sort":2,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":35,"title":"手术后","parentId":17,"leaves":3,"sort":3,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]}]}]},{"id":4,"title":"住院宣教","parentId":0,"leaves":1,"sort":4,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":5,"title":"出院宣教","parentId":0,"leaves":1,"sort":5,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":6,"title":"其它","parentId":0,"leaves":1,"sort":6,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]}]
     * status : 1
     */

    private String msg;
    private int status;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * title : 入院宣教
         * parentId : 0
         * leaves : 1
         * sort : 1
         * detailpagetitle : null
         * detailpagetext : null
         * detailpageimgpath : null
         * edumode : 讲解,示范,书面,录像
         * eduresult : 掌握，未掌握
         * wardid : 0
         * patientEducationRecordList : []
         * child : [{"id":7,"title":"病房环境及设施（呼叫、淋浴、用电等）使用","parentId":1,"leaves":2,"sort":1,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":8,"title":"制度介绍（作息、探视、陪住、物品管理等）","parentId":1,"leaves":2,"sort":2,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":9,"title":"订餐方法及饮食要求","parentId":1,"leaves":2,"sort":3,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":10,"title":"标本留取方法","parentId":1,"leaves":2,"sort":4,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]},{"id":11,"title":"人员介绍（主管医生、主管护士、护士长）","parentId":1,"leaves":2,"sort":5,"detailpagetitle":null,"detailpagetext":null,"detailpageimgpath":null,"edumode":"讲解,示范,书面,录像","eduresult":"掌握，未掌握","wardid":0,"patientEducationRecordList":[],"child":[]}]
         */

        private int id;
        private String title;
        private int parentId;
        private int leaves;
        private int sort;
        private Object detailpagetitle;
        private String detailpagetext;
        private String detailpageimgpath;
        private String edumode;
        private String eduresult;
        private String wardid;
        private List<DataBean> child;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getLeaves() {
            return leaves;
        }

        public void setLeaves(int leaves) {
            this.leaves = leaves;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public Object getDetailpagetitle() {
            return detailpagetitle;
        }

        public void setDetailpagetitle(Object detailpagetitle) {
            this.detailpagetitle = detailpagetitle;
        }

        public String getDetailpagetext() {
            return detailpagetext;
        }

        public void setDetailpagetext(String detailpagetext) {
            this.detailpagetext = detailpagetext;
        }

        public String getDetailpageimgpath() {
            return detailpageimgpath;
        }

        public void setDetailpageimgpath(String detailpageimgpath) {
            this.detailpageimgpath = detailpageimgpath;
        }

        public String getEdumode() {
            return edumode;
        }

        public void setEdumode(String edumode) {
            this.edumode = edumode;
        }

        public String getEduresult() {
            return eduresult;
        }

        public void setEduresult(String eduresult) {
            this.eduresult = eduresult;
        }

        public String getWardid() {
            return wardid;
        }

        public void setWardid(String wardid) {
            this.wardid = wardid;
        }

        public List<DataBean> getChild() {
            return child;
        }

        public void setChild(List<DataBean> child) {
            this.child = child;
        }
    }
}
