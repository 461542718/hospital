package com.dejie.hospital.registration.mapper;

import com.dejie.hospital.registration.model.Hospital_department;
import com.dejie.hospital.registration.model.Hospital_departmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface Hospital_departmentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital_department
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    long countByExample(Hospital_departmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital_department
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int deleteByExample(Hospital_departmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital_department
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital_department
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int insert(Hospital_department record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital_department
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int insertSelective(Hospital_department record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital_department
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    List<Hospital_department> selectByExampleWithRowbounds(Hospital_departmentExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital_department
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    List<Hospital_department> selectByExample(Hospital_departmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital_department
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    Hospital_department selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital_department
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int updateByExampleSelective(@Param("record") Hospital_department record, @Param("example") Hospital_departmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital_department
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int updateByExample(@Param("record") Hospital_department record, @Param("example") Hospital_departmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital_department
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int updateByPrimaryKeySelective(Hospital_department record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital_department
     *
     * @mbg.generated Wed Mar 03 17:40:30 CST 2021
     */
    int updateByPrimaryKey(Hospital_department record);
    List<Hospital_department> selectAll();
}