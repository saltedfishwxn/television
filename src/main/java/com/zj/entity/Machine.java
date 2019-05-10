package com.zj.entity;

/**
 * @author 王晓楠
 * @Description
 * @create 2019-05-08 10:44
 */
public class Machine {
        private int machineId;
        private String location;
        private String rfid;
        private int machineStatus;
        public void setMachineId(int machineId) {
            this.machineId = machineId;
        }
        public int getMachineId() {
            return machineId;
        }

        public void setLocation(String location) {
            this.location = location;
        }
        public String getLocation() {
            return location;
        }

        public void setRfid(String rfid) {
            this.rfid = rfid;
        }
        public String getRfid() {
            return rfid;
        }

        public void setMachineStatus(int machineStatus) {
            this.machineStatus = machineStatus;
        }
        public int getMachineStatus() {
            return machineStatus;
        }
}
