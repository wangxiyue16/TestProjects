package com.example.an.retrofittest.bean;

import java.util.ArrayList;

/**
 * description:
 * author: WDSG
 * date: 2017/3/6
 */
public class Repo {
    private int rs_code;
    private String rs_msg;
    private DetailsData details;

    public int getRs_code() {
        return rs_code;
    }

    public void setRs_code(int rs_code) {
        this.rs_code = rs_code;
    }

    public String getRs_msg() {
        return rs_msg;
    }

    public void setRs_msg(String rs_msg) {
        this.rs_msg = rs_msg;
    }

    public DetailsData getDetails() {
        return details;
    }

    public void setDetails(DetailsData details) {
        this.details = details;
    }

    public static class DetailsData{
        private String remain;
        private String m_income;
        private String a_income;
        private ArrayList<Incomes> income;
        private ArrayList<Banners> banner;
        private ArrayList<String> inform;
        private ArrayList<Ranks> rank;
        private Configs config;

        public String getRemain() {
            return remain;
        }

        public void setRemain(String remain) {
            this.remain = remain;
        }

        public String getM_income() {
            return m_income;
        }

        public void setM_income(String m_income) {
            this.m_income = m_income;
        }

        public String getA_income() {
            return a_income;
        }

        public void setA_income(String a_income) {
            this.a_income = a_income;
        }

        public ArrayList<Incomes> getIncome() {
            return income;
        }

        public void setIncome(ArrayList<Incomes> income) {
            this.income = income;
        }

        public ArrayList<Banners> getBanner() {
            return banner;
        }

        public void setBanner(ArrayList<Banners> banner) {
            this.banner = banner;
        }

        public ArrayList<String> getInform() {
            return inform;
        }

        public void setInform(ArrayList<String> inform) {
            this.inform = inform;
        }

        public ArrayList<Ranks> getRank() {
            return rank;
        }

        public void setRank(ArrayList<Ranks> rank) {
            this.rank = rank;
        }

        public Configs getConfig() {
            return config;
        }

        public void setConfig(Configs config) {
            this.config = config;
        }

        public static class Incomes{
            private String title;
            private String a_income;
            private String m_income;
            private String u_num;
            private String a_account;
            private String type;
            private String pic;
            private String flag;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getA_income() {
                return a_income;
            }

            public void setA_income(String a_income) {
                this.a_income = a_income;
            }

            public String getM_income() {
                return m_income;
            }

            public void setM_income(String m_income) {
                this.m_income = m_income;
            }

            public String getU_num() {
                return u_num;
            }

            public void setU_num(String u_num) {
                this.u_num = u_num;
            }

            public String getA_account() {
                return a_account;
            }

            public void setA_account(String a_account) {
                this.a_account = a_account;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }
        }

        public static class Banners{
            private String pic;
            private String url;
            private String title;

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class Ranks{
            private String name;
            private String city_name;
            private String income;
            private String picname;
            private String top_img;
            private String type;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
            }

            public String getIncome() {
                return income;
            }

            public void setIncome(String income) {
                this.income = income;
            }

            public String getPicname() {
                return picname;
            }

            public void setPicname(String picname) {
                this.picname = picname;
            }

            public String getTop_img() {
                return top_img;
            }

            public void setTop_img(String top_img) {
                this.top_img = top_img;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class Configs{
            private String withdraw_url;
            private String withdraw_token;
            private String withdraw_status;
            private String card_url;
            private String card_status;
            private String generalize_url;

            public String getWithdraw_url() {
                return withdraw_url;
            }

            public void setWithdraw_url(String withdraw_url) {
                this.withdraw_url = withdraw_url;
            }

            public String getWithdraw_token() {
                return withdraw_token;
            }

            public void setWithdraw_token(String withdraw_token) {
                this.withdraw_token = withdraw_token;
            }

            public String getWithdraw_status() {
                return withdraw_status;
            }

            public void setWithdraw_status(String withdraw_status) {
                this.withdraw_status = withdraw_status;
            }

            public String getCard_url() {
                return card_url;
            }

            public void setCard_url(String card_url) {
                this.card_url = card_url;
            }

            public String getCard_status() {
                return card_status;
            }

            public void setCard_status(String card_status) {
                this.card_status = card_status;
            }

            public String getGeneralize_url() {
                return generalize_url;
            }

            public void setGeneralize_url(String generalize_url) {
                this.generalize_url = generalize_url;
            }
        }
    }
}
