package com.zj.entity;

/**
 * @author 王晓楠
 * @Description
 * @create 2019-05-08 10:48
 */
public class Oem {
        private double equipment;
        private double product;
        private int oee;
        private double time;
        private double planTime;
        private double realTime;
        public void setEquipment(double equipment) {
            this.equipment = equipment;
        }
        public double getEquipment() {
            return equipment;
        }

        public void setProduct(double product) {
            this.product = product;
        }
        public double getProduct() {
            return product;
        }

        public void setOee(int oee) {
            this.oee = oee;
        }
        public int getOee() {
            return oee;
        }

        public void setTime(double time) {
            this.time = time;
        }
        public double getTime() {
            return time;
        }

        public void setPlanTime(double planTime) {
            this.planTime = planTime;
        }
        public double getPlanTime() {
            return planTime;
        }

        public void setRealTime(double realTime) {
            this.realTime = realTime;
        }
        public double getRealTime() {
            return realTime;
        }
}
