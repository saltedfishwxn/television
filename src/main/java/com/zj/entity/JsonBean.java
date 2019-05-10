package com.zj.entity;

import java.util.List;

/**
 * @author 王晓楠
 * @Description
 * @create 2019-05-08 10:17
 */
public class JsonBean {
        private List<Stock> stock;
        private List<Agv> agv;
        private List<Robot> robot;
        private List<Machine> machine;
        private List<Staging> staging;
        private List<Order> order;
        private List<WarnMessage> warnMessage;
        private List<Oem> oem;
        private Schedule schedule;
        public void setSchedule(Schedule schedule) { this.schedule = schedule; }
        public Schedule getSchedule() { return schedule; }

        public void setStock(List<Stock> stock) {
            this.stock = stock;
        }
        public List<Stock> getStock() {
            return stock;
        }

        public void setAgv(List<Agv> agv) {
            this.agv = agv;
        }
        public List<Agv> getAgv() {
            return agv;
        }

        public void setRobot(List<Robot> robot) {
            this.robot = robot;
        }
        public List<Robot> getRobot() {
            return robot;
        }

        public void setMachine(List<Machine> machine) {
            this.machine = machine;
        }
        public List<Machine> getMachine() {
            return machine;
        }

        public void setStaging(List<Staging> staging) {
            this.staging = staging;
        }
        public List<Staging> getStaging() {
            return staging;
        }

        public void setOrder(List<Order> order) {
            this.order = order;
        }
        public List<Order> getOrder() {
            return order;
        }

        public void setWarnMessage(List<WarnMessage> warnMessage) {
            this.warnMessage = warnMessage;
        }
        public List<WarnMessage> getWarnMessage() {
            return warnMessage;
        }

        public void setOem(List<Oem> oem) {
            this.oem = oem;
        }
        public List<Oem> getOem() {
            return oem;
        }
}
