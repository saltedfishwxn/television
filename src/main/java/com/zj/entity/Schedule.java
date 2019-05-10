package com.zj.entity;

/**
 * @author 王晓楠
 * @Description
 * @create 2019-05-09 16:39
 */
public class Schedule {
        private int startHour;
        private int startMin;
        private int startSec;
        private int endHour;
        private int endMin;
        private int endSec;
        private int workHour;
        private int workMin;
        private int workSec;
        public void setStartHour(int startHour) {
            this.startHour = startHour;
        }
        public int getStartHour() {
            return startHour;
        }

        public void setStartMin(int startMin) {
            this.startMin = startMin;
        }
        public int getStartMin() {
            return startMin;
        }

        public void setStartSec(int startSec) {
            this.startSec = startSec;
        }
        public int getStartSec() {
            return startSec;
        }

        public void setEndHour(int endHour) {
            this.endHour = endHour;
        }
        public int getEndHour() {
            return endHour;
        }

        public void setEndMin(int endMin) {
            this.endMin = endMin;
        }
        public int getEndMin() {
            return endMin;
        }

        public void setEndSec(int endSec) {
            this.endSec = endSec;
        }
        public int getEndSec() {
            return endSec;
        }

        public void setWorkHour(int workHour) {
            this.workHour = workHour;
        }
        public int getWorkHour() {
            return workHour;
        }

        public void setWorkMin(int workMin) {
            this.workMin = workMin;
        }
        public int getWorkMin() {
            return workMin;
        }

        public void setWorkSec(int workSec) {
            this.workSec = workSec;
        }
        public int getWorkSec() {
            return workSec;
        }
}
