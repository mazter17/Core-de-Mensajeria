package Entities.M07_Template.HandlerPackage;

import Entities.M07_Template.PlanningPackage.Planning;
import Entities.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * PlanningHandler class used for the management of information
 * search in the database with respect to Planning.
 */
public class PlanningHandler {
    /**
     * connection to the database
     */
    public static Sql sql;

    /**
     * constructor without receiving parameters that instantiates
     * the sql attribute of the PlanningHandler class.
     */
    public PlanningHandler() {
        sql = new Sql();
    }

    public static Planning getPlanning(int templateId){
        Planning planning = new Planning();
        try{
            Connection connection = Sql.getConInstance();
            PreparedStatement preparedStatement = connection.prepareCall("{call m07_select_Planning(?)}");
            preparedStatement.setInt(1, templateId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            planning.setStartDate(resultSet.getDate("panningStartDate"));
            planning.setStartTime(resultSet.getString("planningStartTime"));
            planning.setEndDate(resultSet.getDate("planningEndDate"));
            planning.setEndTime(resultSet.getString("planningEndTime"));
            planning.setIdPlanning(resultSet.getInt("(planningId"));
        }catch (SQLException e){
            e.fillInStackTrace();
        }catch (Exception e){
            e.fillInStackTrace();
        }finally {
            return planning;
        }
    }

    public static void postPlanning(String[] planning, int templateId){

        Sql sql = new Sql();
        String query = "INSERT INTO public.Planning" +
                "(pla_start_date, pla_start_time, pla_end_date, pla_end_time, pla_template_id) " +
                "VALUES ('" + planning[0] + "','" + planning[2] +
                "','" + planning[1] + "','" + planning[3] + "'," + templateId + ")";
        try{
            sql.sqlNoReturn(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            Sql.bdClose(sql.getConn());
        }

    }


    public static void updatePlanning(String[] planning, int templateId) {
        Sql sql = new Sql();
        String query = "update public.Planning \n" +
                "set pla_start_date = '" + planning[0] + "',pla_start_time = '" + planning[2] + "', pla_end_date = '" + planning[1] + "', pla_end_time = '" + planning[3] + "'" +
                "where pla_template_id = " + templateId;
        try{
            sql.sqlNoReturn(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            Sql.bdClose(sql.getConn());
        }
    }
}
