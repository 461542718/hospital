package com.dejie.hospital.registration.model;

public class Userinfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userinfo.Cardnumber
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    private Integer cardnumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userinfo.Tel
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    private String tel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userinfo.address
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userinfo.Sex
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    private Integer sex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userinfo.Uphoto
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    private String uphoto;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userinfo.Briefinfo
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    private String briefinfo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userinfo.Cardnumber
     *
     * @return the value of userinfo.Cardnumber
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    public Integer getCardnumber() {
        return cardnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userinfo.Cardnumber
     *
     * @param cardnumber the value for userinfo.Cardnumber
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    public void setCardnumber(Integer cardnumber) {
        this.cardnumber = cardnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userinfo.Tel
     *
     * @return the value of userinfo.Tel
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    public String getTel() {
        return tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userinfo.Tel
     *
     * @param tel the value for userinfo.Tel
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userinfo.address
     *
     * @return the value of userinfo.address
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userinfo.address
     *
     * @param address the value for userinfo.address
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userinfo.Sex
     *
     * @return the value of userinfo.Sex
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userinfo.Sex
     *
     * @param sex the value for userinfo.Sex
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userinfo.Uphoto
     *
     * @return the value of userinfo.Uphoto
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    public String getUphoto() {
        return uphoto;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userinfo.Uphoto
     *
     * @param uphoto the value for userinfo.Uphoto
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    public void setUphoto(String uphoto) {
        this.uphoto = uphoto == null ? null : uphoto.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userinfo.Briefinfo
     *
     * @return the value of userinfo.Briefinfo
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    public String getBriefinfo() {
        return briefinfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userinfo.Briefinfo
     *
     * @param briefinfo the value for userinfo.Briefinfo
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    public void setBriefinfo(String briefinfo) {
        this.briefinfo = briefinfo == null ? null : briefinfo.trim();
    }
}