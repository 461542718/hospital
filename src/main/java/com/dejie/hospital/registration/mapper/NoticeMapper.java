package com.dejie.hospital.registration.mapper;

import com.dejie.hospital.registration.model.Notice;
import com.dejie.hospital.registration.model.NoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface NoticeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    long countByExample(NoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int deleteByExample(NoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int deleteByPrimaryKey(Integer noticeid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int insert(Notice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int insertSelective(Notice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    List<Notice> selectByExampleWithBLOBsWithRowbounds(NoticeExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    List<Notice> selectByExampleWithBLOBs(NoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    List<Notice> selectByExampleWithRowbounds(NoticeExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    List<Notice> selectByExample(NoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    Notice selectByPrimaryKey(Integer noticeid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int updateByExampleSelective(@Param("record") Notice record, @Param("example") NoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int updateByExampleWithBLOBs(@Param("record") Notice record, @Param("example") NoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int updateByExample(@Param("record") Notice record, @Param("example") NoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int updateByPrimaryKeySelective(Notice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int updateByPrimaryKeyWithBLOBs(Notice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int updateByPrimaryKey(Notice record);
    List<Notice> selectAll();
}