package vu.lt.mybatis.dao;

import java.util.List;
import vu.lt.mybatis.model.Drivers;
import org.mybatis.cdi.Mapper;

@Mapper
public interface DriversMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.DRIVERS
     *
     * @mbg.generated Fri Apr 05 20:37:44 EEST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.DRIVERS
     *
     * @mbg.generated Fri Apr 05 20:37:44 EEST 2019
     */
    int insert(Drivers record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.DRIVERS
     *
     * @mbg.generated Fri Apr 05 20:37:44 EEST 2019
     */
    Drivers selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.DRIVERS
     *
     * @mbg.generated Fri Apr 05 20:37:44 EEST 2019
     */
    List<Drivers> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.DRIVERS
     *
     * @mbg.generated Fri Apr 05 20:37:44 EEST 2019
     */
    int updateByPrimaryKey(Drivers record);
}