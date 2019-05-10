package com.zj.entity;

/**
 * @author 王晓楠
 * @Description
 * @create 2019-05-08 10:44
 */
public class Staging {
        private int id;
        private String rfid;
        private int status;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setRfid(String rfid) {
            this.rfid = rfid;
        }
        public String getRfid() {
            return rfid;
        }

        public void setStatus(int status) {
            this.status = status;
        }
        public int getStatus() {
            return status;
        }
}
