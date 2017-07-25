package com.ge.med.mobile.nursing.dao.entity;

/**网络访问实体JSON解析Bean
 * Created by Administrator on 2016/10/27.
 */
public class User {
    /**
     * status : 1
     * data : {"work_year":"N6","created_by":"系统","last_update_time":1483705110000,"bed_count":50,"status":"0","ward_name":"骨科东侧病房","ward_id":301,"password":"c4ca4238a0b923820dcc509a6f75849b","id":3,"last_updated_by":"晨晨","is_deleted":"0","emp_no":"an089","name":"晨晨","nursing_level":"护师","temporary_ward_id":301,"creation_time":1479178637000,"temporary_department_id":"D402","role_type":"1","parent_id":"D400"}
     * msg : 成功
     */

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
        /**
         * work_year : N6
         * created_by : 系统
         * last_update_time : 1483705110000
         * bed_count : 50
         * status : 0
         * ward_name : 骨科东侧病房
         * ward_id : 301
         * password : c4ca4238a0b923820dcc509a6f75849b
         * id : 3
         * last_updated_by : 晨晨
         * is_deleted : 0
         * emp_no : an089
         * name : 晨晨
         * nursing_level : 护师
         * temporary_ward_id : 301
         * creation_time : 1479178637000
         * temporary_department_id : D402
         * role_type : 1
         * parent_id : D400
         */

        private String work_year;
        private String created_by;
        private long last_update_time;
        private int bed_count;
        private String status;
        private String ward_name;
        private String ward_id;
        private String password;
        private int id;
        private String last_updated_by;
        private String is_deleted;
        private String emp_no;
        private String name;
        private String nursing_level;
        private int temporary_ward_id;
        private long creation_time;
        private String temporary_department_id;
        private String role_type;
        private String parent_id;

        public String getWork_year() {
            return work_year;
        }

        public void setWork_year(String work_year) {
            this.work_year = work_year;
        }

        public String getCreated_by() {
            return created_by;
        }

        public void setCreated_by(String created_by) {
            this.created_by = created_by;
        }

        public long getLast_update_time() {
            return last_update_time;
        }

        public void setLast_update_time(long last_update_time) {
            this.last_update_time = last_update_time;
        }

        public int getBed_count() {
            return bed_count;
        }

        public void setBed_count(int bed_count) {
            this.bed_count = bed_count;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getWard_name() {
            return ward_name;
        }

        public void setWard_name(String ward_name) {
            this.ward_name = ward_name;
        }

        public String getWard_id() {
            return ward_id;
        }

        public void setWard_id(String ward_id) {
            this.ward_id = ward_id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLast_updated_by() {
            return last_updated_by;
        }

        public void setLast_updated_by(String last_updated_by) {
            this.last_updated_by = last_updated_by;
        }

        public String getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(String is_deleted) {
            this.is_deleted = is_deleted;
        }

        public String getEmp_no() {
            return emp_no;
        }

        public void setEmp_no(String emp_no) {
            this.emp_no = emp_no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNursing_level() {
            return nursing_level;
        }

        public void setNursing_level(String nursing_level) {
            this.nursing_level = nursing_level;
        }

        public int getTemporary_ward_id() {
            return temporary_ward_id;
        }

        public void setTemporary_ward_id(int temporary_ward_id) {
            this.temporary_ward_id = temporary_ward_id;
        }

        public long getCreation_time() {
            return creation_time;
        }

        public void setCreation_time(long creation_time) {
            this.creation_time = creation_time;
        }

        public String getTemporary_department_id() {
            return temporary_department_id;
        }

        public void setTemporary_department_id(String temporary_department_id) {
            this.temporary_department_id = temporary_department_id;
        }

        public String getRole_type() {
            return role_type;
        }

        public void setRole_type(String role_type) {
            this.role_type = role_type;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }
    }
}
