package com.zj.entity;

/**
 * @author 王晓楠
 * @Description
 * @create 2019-05-08 10:44
 */
public class Order {
        private String corder;
        private int numbers;
        private String property;
        private int done;
        private int percent;
        public void setCorder(String corder) {
            this.corder = corder;
        }
        public String getCorder() {
            return corder;
        }

        public void setNumber(int numbers) {
            this.numbers = numbers;
        }
        public int getNumber() {
            return numbers;
        }

        public void setProperty(String property) {
            this.property = property;
        }
        public String getProperty() {
            return property;
        }

        public void setDone(int done) {
            this.done = done;
        }
        public int getDone() {
            return done;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }
        public int getPercent() {
            return percent;
        }
}
