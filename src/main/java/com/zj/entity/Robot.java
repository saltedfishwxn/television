package com.zj.entity;

/**
 * @author 王晓楠
 * @Description
 * @create 2019-05-08 10:44
 */
public class Robot {
        private int robotId;
        private String robotStatus;
        private String robotWork;
        public void setRobotId(int robotId) {
            this.robotId = robotId;
        }
        public int getRobotId() {
            return robotId;
        }

        public void setRobotStatus(String robotStatus) {
            this.robotStatus = robotStatus;
        }
        public String getRobotStatus() {
            return robotStatus;
        }

        public void setRobotWork(String robotWork) {
            this.robotWork = robotWork;
        }
        public String getRobotWork() {
            return robotWork;
        }
}
