package com.zj.entity;

/**
 * @author 王晓楠
 * @Description
 * @create 2019-05-08 10:16
 */
public class Stock {
        private int factoryId;
        private int mode;
        private String hourseStatus;
        private String hleftQight;
        private int hrow;
        private int hcol;
        private double hourseY;
        private double hourseX;
        private String outHourseDire;
        private String inHourseCard;
        public void setFactoryId(int factoryId) {
            this.factoryId = factoryId;
        }
        public int getFactoryId() {
            return factoryId;
        }

        public void setMode(int mode) {
            this.mode = mode;
        }
        public int getMode() {
            return mode;
        }

        public void setHourseStatus(String hourseStatus) {
            this.hourseStatus = hourseStatus;
        }
        public String getHourseStatus() {
            return hourseStatus;
        }

        public void setHleftQight(String hleftQight) {
            this.hleftQight = hleftQight;
        }
        public String getHleftQight() {
            return hleftQight;
        }

        public void setHrow(int hrow) {
            this.hrow = hrow;
        }
        public int getHrow() {
            return hrow;
        }

        public void setHcol(int hcol) {
            this.hcol = hcol;
        }
        public int getHcol() {
            return hcol;
        }

        public void setHourseY(double hourseY) {
            this.hourseY = hourseY;
        }
        public double getHourseY() {
            return hourseY;
        }

        public void setHourseX(double hourseX) {
            this.hourseX = hourseX;
        }
        public double getHourseX() {
            return hourseX;
        }

        public void setOutHourseDire(String outHourseDire) {
            this.outHourseDire = outHourseDire;
        }
        public String getOutHourseDire() {
            return outHourseDire;
        }

        public void setInHourseCard(String inHourseCard) {
            this.inHourseCard = inHourseCard;
        }
        public String getInHourseCard() {
            return inHourseCard;
        }

}
