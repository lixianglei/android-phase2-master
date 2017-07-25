package com.ge.med.mobile.nursing.dao.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 定义网络访问实体JSON解析Bean
 * Created by Administrator on 2017/3/29.
 */
public class NursingEventTempLateLisBean implements Serializable{
    /**
     * status : 1
     * data : [{"eventcode":"0","eventname":"呼吸","id":1,"nursingEventTemplateDefineList":[{"eventdefineid":1,"template":{"sortno":1,"templatecontent":"接班看病人：患者意识清楚，双侧瞳孔等大等圆，直径<span style=\"color:red\">XX<\/span>mm。对光反射灵敏持续心电、血压、呼吸、脉痒饱和度检测，机器运转正常。持续低流量吸氧。速度为<span style=\"color:red\">XX<\/span>升／分钟。","templatename":"事件1"},"templateid":1},{"eventdefineid":1,"template":{"sortno":1,"templatecontent":"接班看病人：患者意识清楚，双侧瞳孔等大等圆，直径<span style=\"color:red\">XX<\/span>mm。持续低流量吸氧。速度为<span style=\"color:red\">XX<\/span>升／分钟。","templatename":"事件2"},"templateid":2},{"eventdefineid":1,"template":{"sortno":1,"templatecontent":"接班看病人：患者意识清楚，双侧瞳孔等大等圆，直径<span style=\"color:red\">XX<\/span>mm。持续低流量吸氧。","templatename":"事件3"},"templateid":3}],"wardid":301},{"eventcode":"E999","eventname":"疼痛","id":2,"nursingEventTemplateDefineList":[],"wardid":301},{"eventcode":"E666","eventname":"医嘱备注","id":3,"nursingEventTemplateDefineList":[],"wardid":301},{"eventcode":"0","eventname":"其他","id":4,"nursingEventTemplateDefineList":[],"wardid":301},{"eventcode":"E888","eventname":"自动记录","id":5,"nursingEventTemplateDefineList":[],"wardid":301}]
     * msg : 成功
     */
    private int status;
    private String msg;

    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public static class DataBean implements Serializable{
        /**
         * eventcode : 0
         * eventname : 呼吸
         * id : 1
         * nursingEventTemplateDefineList : [{"eventdefineid":1,"template":{"sortno":1,"templatecontent":"接班看病人：患者意识清楚，双侧瞳孔等大等圆，直径<span style=\"color:red\">XX<\/span>mm。对光反射灵敏持续心电、血压、呼吸、脉痒饱和度检测，机器运转正常。持续低流量吸氧。速度为<span style=\"color:red\">XX<\/span>升／分钟。","templatename":"事件1"},"templateid":1},{"eventdefineid":1,"template":{"sortno":1,"templatecontent":"接班看病人：患者意识清楚，双侧瞳孔等大等圆，直径<span style=\"color:red\">XX<\/span>mm。持续低流量吸氧。速度为<span style=\"color:red\">XX<\/span>升／分钟。","templatename":"事件2"},"templateid":2},{"eventdefineid":1,"template":{"sortno":1,"templatecontent":"接班看病人：患者意识清楚，双侧瞳孔等大等圆，直径<span style=\"color:red\">XX<\/span>mm。持续低流量吸氧。","templatename":"事件3"},"templateid":3}]
         * wardid : 301
         */

        private String eventcode;
        private String eventname;
        private int id;
        private String wardid;

        private List<NursingEventTemplateDefineListBean> nursingEventTemplateDefineList;

        public String getEventcode() {
            return eventcode;
        }

        public void setEventcode(String eventcode) {
            this.eventcode = eventcode;
        }

        public String getEventname() {
            return eventname;
        }

        public void setEventname(String eventname) {
            this.eventname = eventname;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWardid() {
            return wardid;
        }

        public void setWardid(String wardid) {
            this.wardid = wardid;
        }

        public List<NursingEventTemplateDefineListBean> getNursingEventTemplateDefineList() {
            return nursingEventTemplateDefineList;
        }

        public void setNursingEventTemplateDefineList(List<NursingEventTemplateDefineListBean> nursingEventTemplateDefineList) {
            this.nursingEventTemplateDefineList = nursingEventTemplateDefineList;
        }

        public static class NursingEventTemplateDefineListBean implements Serializable{
            /**
             * eventdefineid : 1
             * template : {"sortno":1,"templatecontent":"接班看病人：患者意识清楚，双侧瞳孔等大等圆，直径<span style=\"color:red\">XX<\/span>mm。对光反射灵敏持续心电、血压、呼吸、脉痒饱和度检测，机器运转正常。持续低流量吸氧。速度为<span style=\"color:red\">XX<\/span>升／分钟。","templatename":"事件1"}
             * templateid : 1
             */

            private int eventdefineid;
            private TemplateBean template;
            private int templateid;

            public int getEventdefineid() {
                return eventdefineid;
            }

            public void setEventdefineid(int eventdefineid) {
                this.eventdefineid = eventdefineid;
            }

            public TemplateBean getTemplate() {
                return template;
            }

            public void setTemplate(TemplateBean template) {
                this.template = template;
            }

            public int getTemplateid() {
                return templateid;
            }

            public void setTemplateid(int templateid) {
                this.templateid = templateid;
            }

            public static class TemplateBean implements Serializable{
                /**
                 * sortno : 1
                 * templatecontent : 接班看病人：患者意识清楚，双侧瞳孔等大等圆，直径<span style="color:red">XX</span>mm。对光反射灵敏持续心电、血压、呼吸、脉痒饱和度检测，机器运转正常。持续低流量吸氧。速度为<span style="color:red">XX</span>升／分钟。
                 * templatename : 事件1
                 */
                private int sortno;
                private String templatecontent;
                private String templatename;

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
            }
        }
    }
}
