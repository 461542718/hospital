package com.dejie.hospital.registration.model;

public class Shiftapply {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiftapply.id
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiftapply.Did
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    private Integer did;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiftapply.content
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiftapply.note
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    private String note;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiftapply.gmt_create
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiftapply.gmt_modify
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    private Long gmtModify;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shiftapply.status
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    private Integer status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiftapply.id
     *
     * @return the value of shiftapply.id
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiftapply.id
     *
     * @param id the value for shiftapply.id
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiftapply.Did
     *
     * @return the value of shiftapply.Did
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    public Integer getDid() {
        return did;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiftapply.Did
     *
     * @param did the value for shiftapply.Did
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    public void setDid(Integer did) {
        this.did = did;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiftapply.content
     *
     * @return the value of shiftapply.content
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiftapply.content
     *
     * @param content the value for shiftapply.content
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiftapply.note
     *
     * @return the value of shiftapply.note
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiftapply.note
     *
     * @param note the value for shiftapply.note
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiftapply.gmt_create
     *
     * @return the value of shiftapply.gmt_create
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiftapply.gmt_create
     *
     * @param gmtCreate the value for shiftapply.gmt_create
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiftapply.gmt_modify
     *
     * @return the value of shiftapply.gmt_modify
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    public Long getGmtModify() {
        return gmtModify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiftapply.gmt_modify
     *
     * @param gmtModify the value for shiftapply.gmt_modify
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    public void setGmtModify(Long gmtModify) {
        this.gmtModify = gmtModify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shiftapply.status
     *
     * @return the value of shiftapply.status
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shiftapply.status
     *
     * @param status the value for shiftapply.status
     *
     * @mbg.generated Thu Apr 08 11:08:30 CST 2021
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}