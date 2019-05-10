package com.zj.entity;


/**
 * @author 王晓楠
 * @Description
 * @create 2019-04-26 12:56
 */
public class Agv {
        private int agvId;
        private String model;
        private String currentId;
        public void setAgvId(int agvId) {
            this.agvId = agvId;
        }
        public int getAgvId() {
            return agvId;
        }

        public void setModel(String model) {
            this.model = model;
        }
        public String getModel() {
            return model;
        }

        public void setCurrentId(String currentId) {
            this.currentId = currentId;
        }
        public String getCurrentId() {
            return currentId;
        }
}
