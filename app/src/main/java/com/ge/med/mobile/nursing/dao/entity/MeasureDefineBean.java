package com.ge.med.mobile.nursing.dao.entity;

import java.io.Serializable;
import java.util.List;

/**
 *  定义网络访问实体JSON解析Bean
 * Created by xxl on 2017/4/14.
 */

public class MeasureDefineBean implements Serializable{
    /**
     * status : 1
     * data : [{"assessDefineCode":"FALL_DOWN_ASSESS","id":1,"measureCode":"FALL_DOWN","measureTopicDefineList":[{"id":1,"measureAnswerDefineList":[{"code":"FALL01","id":1,"measureTopicDefineId":1,"type":null,"value":"1.鍚戠梾浜哄強瀹跺睘杩涜椋庨櫓鍙婇槻鑼冩帾鏂藉憡鐭ュ苟鍦ㄣ\u20ac婅穼鍊?/鍧犲簥椋庨櫓鍙婇槻鑼冨憡鐭ヤ功銆嬩笂绛惧瓧锛?"},{"code":"FALL02","id":2,"measureTopicDefineId":1,"type":null,"value":"2.鍛婄煡鐥呬汉/鐓ч【鑰?/澶栧嫟绛夌浉鍏充汉鍛樿穼鍊?/鍧犲簥鐨勯闄╁強棰勯槻鎺柦锛?"},{"code":"FALL03","id":3,"measureTopicDefineId":1,"type":null,"value":"3.搴婂ご鎸傗\u20ac滈闃茶穼鍊?/鍧犲簥鈥濇彁绀哄崱锛?"},{"code":"FALL04","id":4,"measureTopicDefineId":1,"type":null,"value":"4.寤虹珛璺屽\u20ac?/鍧犲簥楂橀闄╃梾浜轰竴瑙堣〃锛?"}],"measureDefineId":"1","measureTopicValue":"鎻愮ず瀹ｆ暀","type":null},{"id":2,"measureAnswerDefineList":[{"code":"FALL05","id":5,"measureTopicDefineId":2,"type":null,"value":"1.瀹夌疆鐥呬汉鍦ㄩ潬杩戝帟鎵\u20ac鐨勪綅缃紱"},{"code":"FALL06","id":6,"measureTopicDefineId":2,"type":null,"value":"2.灏嗙粡甯歌穼鍊掑強涓嶅惉鍔濆憡鐨勭梾浜哄畨缃湪瀹规槗瑙傚療鐨勫湴鏂癸紱"},{"code":"FALL07","id":7,"measureTopicDefineId":2,"type":null,"value":"3.灏嗙梾搴婅皟鑷虫渶浣庝綅缃紱"},{"code":"FALL08","id":8,"measureTopicDefineId":2,"type":null,"value":"4.瀹夋帓鐭簥锛?"},{"code":"FALL09","id":9,"measureTopicDefineId":2,"type":null,"value":"5"},{"code":"FALL10","id":10,"measureTopicDefineId":2,"type":null,"value":"6"},{"code":"FALL11","id":11,"measureTopicDefineId":2,"type":null,"value":"7"},{"code":"FALL12","id":12,"measureTopicDefineId":2,"type":null,"value":"8"},{"code":"FALL13","id":13,"measureTopicDefineId":2,"type":null,"value":"9"},{"code":"FALL14","id":14,"measureTopicDefineId":2,"type":null,"value":"10"},{"code":"FALL15","id":15,"measureTopicDefineId":2,"type":null,"value":"11"},{"code":"FALL16","id":16,"measureTopicDefineId":2,"type":null,"value":"12"},{"code":"FALL17","id":17,"measureTopicDefineId":2,"type":null,"value":"13"},{"code":"FALL18","id":18,"measureTopicDefineId":2,"type":null,"value":"14"},{"code":"FALL19","id":19,"measureTopicDefineId":2,"type":null,"value":"15"},{"code":"FALL20","id":20,"measureTopicDefineId":2,"type":null,"value":"16"}],"measureDefineId":"1","measureTopicValue":"棰勯槻鎺柦","type":null},{"id":3,"measureAnswerDefineList":[{"code":"FALL21","id":23,"measureTopicDefineId":3,"type":null,"value":"1.鍏跺畠1"},{"code":"FALL22","id":24,"measureTopicDefineId":3,"type":null,"value":"2.鍏跺畠2"},{"code":"FALL23","id":25,"measureTopicDefineId":3,"type":null,"value":"3.鍏跺畠3"}],"measureDefineId":"1","measureTopicValue":"鍏跺畠","type":null}],"name":"璺屽\u20ac?/鍧犲簥","ruleCode":"0","warnScoreMax":2,"warnScoreMin":null},{"assessDefineCode":"PRESSURE_ASSESS","id":2,"measureCode":"PRESSURE","measureTopicDefineList":[{"id":4,"measureAnswerDefineList":[{"code":"PRESSURE01","id":26,"measureTopicDefineId":4,"type":null,"value":"1"},{"code":"PRESSURE02","id":27,"measureTopicDefineId":4,"type":null,"value":"2"},{"code":"PRESSURE03","id":28,"measureTopicDefineId":4,"type":null,"value":"3"},{"code":"PRESSURE04","id":29,"measureTopicDefineId":4,"type":null,"value":"4"}],"measureDefineId":"2","measureTopicValue":"鎻愮ず瀹ｆ暀","type":null},{"id":5,"measureAnswerDefineList":[{"code":"PRESSURE05","id":30,"measureTopicDefineId":5,"type":null,"value":"1"},{"code":"PRESSURE06","id":31,"measureTopicDefineId":5,"type":null,"value":"2"},{"code":"PRESSURE07","id":32,"measureTopicDefineId":5,"type":null,"value":"3"},{"code":"PRESSURE08","id":33,"measureTopicDefineId":5,"type":null,"value":"4"},{"code":"PRESSURE09","id":34,"measureTopicDefineId":5,"type":null,"value":"5"},{"code":"PRESSURE10","id":35,"measureTopicDefineId":5,"type":null,"value":"6"},{"code":"PRESSURE11","id":36,"measureTopicDefineId":5,"type":null,"value":"7"},{"code":"PRESSURE12","id":37,"measureTopicDefineId":5,"type":null,"value":"8"},{"code":"PRESSURE13","id":38,"measureTopicDefineId":5,"type":null,"value":"9"},{"code":"PRESSURE14","id":39,"measureTopicDefineId":5,"type":null,"value":"10"},{"code":"PRESSURE15","id":40,"measureTopicDefineId":5,"type":null,"value":"11"},{"code":"PRESSURE16","id":41,"measureTopicDefineId":5,"type":null,"value":"12"},{"code":"PRESSURE17","id":42,"measureTopicDefineId":5,"type":null,"value":"13"},{"code":"PRESSURE18","id":43,"measureTopicDefineId":5,"type":null,"value":"14"},{"code":"PRESSURE19","id":44,"measureTopicDefineId":5,"type":null,"value":"15"},{"code":"PRESSURE20","id":45,"measureTopicDefineId":5,"type":null,"value":"16"}],"measureDefineId":"2","measureTopicValue":"鍑忓帇鎺柦","type":null},{"id":6,"measureAnswerDefineList":[{"code":"PRESSURE21","id":46,"measureTopicDefineId":6,"type":null,"value":"17"}],"measureDefineId":"2","measureTopicValue":"娓呮磥","type":null},{"id":7,"measureAnswerDefineList":[],"measureDefineId":"2","measureTopicValue":"钀ュ吇","type":null},{"id":8,"measureAnswerDefineList":[],"measureDefineId":"2","measureTopicValue":"鍏跺畠","type":null}],"name":"鍘嬬柈","ruleCode":"1","warnScoreMax":null,"warnScoreMin":18}]
     * msg : 鎴愬姛
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
         * assessDefineCode : FALL_DOWN_ASSESS
         * id : 1
         * measureCode : FALL_DOWN
         * measureTopicDefineList : [{"id":1,"measureAnswerDefineList":[{"code":"FALL01","id":1,"measureTopicDefineId":1,"type":null,"value":"1.鍚戠梾浜哄強瀹跺睘杩涜椋庨櫓鍙婇槻鑼冩帾鏂藉憡鐭ュ苟鍦ㄣ\u20ac婅穼鍊?/鍧犲簥椋庨櫓鍙婇槻鑼冨憡鐭ヤ功銆嬩笂绛惧瓧锛?"},{"code":"FALL02","id":2,"measureTopicDefineId":1,"type":null,"value":"2.鍛婄煡鐥呬汉/鐓ч【鑰?/澶栧嫟绛夌浉鍏充汉鍛樿穼鍊?/鍧犲簥鐨勯闄╁強棰勯槻鎺柦锛?"},{"code":"FALL03","id":3,"measureTopicDefineId":1,"type":null,"value":"3.搴婂ご鎸傗\u20ac滈闃茶穼鍊?/鍧犲簥鈥濇彁绀哄崱锛?"},{"code":"FALL04","id":4,"measureTopicDefineId":1,"type":null,"value":"4.寤虹珛璺屽\u20ac?/鍧犲簥楂橀闄╃梾浜轰竴瑙堣〃锛?"}],"measureDefineId":"1","measureTopicValue":"鎻愮ず瀹ｆ暀","type":null},{"id":2,"measureAnswerDefineList":[{"code":"FALL05","id":5,"measureTopicDefineId":2,"type":null,"value":"1.瀹夌疆鐥呬汉鍦ㄩ潬杩戝帟鎵\u20ac鐨勪綅缃紱"},{"code":"FALL06","id":6,"measureTopicDefineId":2,"type":null,"value":"2.灏嗙粡甯歌穼鍊掑強涓嶅惉鍔濆憡鐨勭梾浜哄畨缃湪瀹规槗瑙傚療鐨勫湴鏂癸紱"},{"code":"FALL07","id":7,"measureTopicDefineId":2,"type":null,"value":"3.灏嗙梾搴婅皟鑷虫渶浣庝綅缃紱"},{"code":"FALL08","id":8,"measureTopicDefineId":2,"type":null,"value":"4.瀹夋帓鐭簥锛?"},{"code":"FALL09","id":9,"measureTopicDefineId":2,"type":null,"value":"5"},{"code":"FALL10","id":10,"measureTopicDefineId":2,"type":null,"value":"6"},{"code":"FALL11","id":11,"measureTopicDefineId":2,"type":null,"value":"7"},{"code":"FALL12","id":12,"measureTopicDefineId":2,"type":null,"value":"8"},{"code":"FALL13","id":13,"measureTopicDefineId":2,"type":null,"value":"9"},{"code":"FALL14","id":14,"measureTopicDefineId":2,"type":null,"value":"10"},{"code":"FALL15","id":15,"measureTopicDefineId":2,"type":null,"value":"11"},{"code":"FALL16","id":16,"measureTopicDefineId":2,"type":null,"value":"12"},{"code":"FALL17","id":17,"measureTopicDefineId":2,"type":null,"value":"13"},{"code":"FALL18","id":18,"measureTopicDefineId":2,"type":null,"value":"14"},{"code":"FALL19","id":19,"measureTopicDefineId":2,"type":null,"value":"15"},{"code":"FALL20","id":20,"measureTopicDefineId":2,"type":null,"value":"16"}],"measureDefineId":"1","measureTopicValue":"棰勯槻鎺柦","type":null},{"id":3,"measureAnswerDefineList":[{"code":"FALL21","id":23,"measureTopicDefineId":3,"type":null,"value":"1.鍏跺畠1"},{"code":"FALL22","id":24,"measureTopicDefineId":3,"type":null,"value":"2.鍏跺畠2"},{"code":"FALL23","id":25,"measureTopicDefineId":3,"type":null,"value":"3.鍏跺畠3"}],"measureDefineId":"1","measureTopicValue":"鍏跺畠","type":null}]
         * name : 璺屽€?/鍧犲簥
         * ruleCode : 0
         * warnScoreMax : 2
         * warnScoreMin : null
         */

        private String assessDefineCode;
        private int id;
        private String measureCode;
        private String name;
        private String ruleCode;
        private int warnScoreMax;
        private Object warnScoreMin;
        private List<MeasureTopicDefineListBean> measureTopicDefineList;

        public String getAssessDefineCode() {
            return assessDefineCode;
        }

        public void setAssessDefineCode(String assessDefineCode) {
            this.assessDefineCode = assessDefineCode;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMeasureCode() {
            return measureCode;
        }

        public void setMeasureCode(String measureCode) {
            this.measureCode = measureCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRuleCode() {
            return ruleCode;
        }

        public void setRuleCode(String ruleCode) {
            this.ruleCode = ruleCode;
        }

        public int getWarnScoreMax() {
            return warnScoreMax;
        }

        public void setWarnScoreMax(int warnScoreMax) {
            this.warnScoreMax = warnScoreMax;
        }

        public Object getWarnScoreMin() {
            return warnScoreMin;
        }

        public void setWarnScoreMin(Object warnScoreMin) {
            this.warnScoreMin = warnScoreMin;
        }

        public List<MeasureTopicDefineListBean> getMeasureTopicDefineList() {
            return measureTopicDefineList;
        }

        public void setMeasureTopicDefineList(List<MeasureTopicDefineListBean> measureTopicDefineList) {
            this.measureTopicDefineList = measureTopicDefineList;
        }

        public static class MeasureTopicDefineListBean implements Serializable{
            /**
             * id : 1
             * measureAnswerDefineList : [{"code":"FALL01","id":1,"measureTopicDefineId":1,"type":null,"value":"1.鍚戠梾浜哄強瀹跺睘杩涜椋庨櫓鍙婇槻鑼冩帾鏂藉憡鐭ュ苟鍦ㄣ\u20ac婅穼鍊?/鍧犲簥椋庨櫓鍙婇槻鑼冨憡鐭ヤ功銆嬩笂绛惧瓧锛?"},{"code":"FALL02","id":2,"measureTopicDefineId":1,"type":null,"value":"2.鍛婄煡鐥呬汉/鐓ч【鑰?/澶栧嫟绛夌浉鍏充汉鍛樿穼鍊?/鍧犲簥鐨勯闄╁強棰勯槻鎺柦锛?"},{"code":"FALL03","id":3,"measureTopicDefineId":1,"type":null,"value":"3.搴婂ご鎸傗\u20ac滈闃茶穼鍊?/鍧犲簥鈥濇彁绀哄崱锛?"},{"code":"FALL04","id":4,"measureTopicDefineId":1,"type":null,"value":"4.寤虹珛璺屽\u20ac?/鍧犲簥楂橀闄╃梾浜轰竴瑙堣〃锛?"}]
             * measureDefineId : 1
             * measureTopicValue : 鎻愮ず瀹ｆ暀
             * type : null
             */

            private int id;
            private String measureDefineId;
            private String measureTopicValue;
            private Object type;
            private List<MeasureAnswerDefineListBean> measureAnswerDefineList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMeasureDefineId() {
                return measureDefineId;
            }

            public void setMeasureDefineId(String measureDefineId) {
                this.measureDefineId = measureDefineId;
            }

            public String getMeasureTopicValue() {
                return measureTopicValue;
            }

            public void setMeasureTopicValue(String measureTopicValue) {
                this.measureTopicValue = measureTopicValue;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public List<MeasureAnswerDefineListBean> getMeasureAnswerDefineList() {
                return measureAnswerDefineList;
            }

            public void setMeasureAnswerDefineList(List<MeasureAnswerDefineListBean> measureAnswerDefineList) {
                this.measureAnswerDefineList = measureAnswerDefineList;
            }

            public static class MeasureAnswerDefineListBean implements Serializable{
                /**
                 * code : FALL01
                 * id : 1
                 * measureTopicDefineId : 1
                 * type : null
                 * value : 1.鍚戠梾浜哄強瀹跺睘杩涜椋庨櫓鍙婇槻鑼冩帾鏂藉憡鐭ュ苟鍦ㄣ€婅穼鍊?/鍧犲簥椋庨櫓鍙婇槻鑼冨憡鐭ヤ功銆嬩笂绛惧瓧锛?
                 */

                private String code;
                private int id;
                private int measureTopicDefineId;
                private Object type;
                private String value;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getMeasureTopicDefineId() {
                    return measureTopicDefineId;
                }

                public void setMeasureTopicDefineId(int measureTopicDefineId) {
                    this.measureTopicDefineId = measureTopicDefineId;
                }

                public Object getType() {
                    return type;
                }

                public void setType(Object type) {
                    this.type = type;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }
}
