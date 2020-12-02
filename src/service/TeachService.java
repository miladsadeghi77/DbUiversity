package service;

import entities.StuEntity;
import entities.TeachEntity;
import util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeachService {
    // This class is designed to perform teacher operations
    //Update operation performed by checking the teacher ID
    public void updateTeach(TeachEntity teachEntity) {
        String updateQuery = "Update teacher " +
                "set fname = ? , lname = ?  " +
                "where teachid = ? ";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, teachEntity.getfName());
            preparedStatement.setString(2, teachEntity.getlName());
            preparedStatement.setInt(3, teachEntity.getSsn());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    //The addition operation is done by receiving the ID, name and lastname of the teacher
    public void addTeach(TeachEntity teachEntity) {
        String addQuery = "insert into teacher (fname,lname)  values (?,?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addQuery)) {
            preparedStatement.setString(1, teachEntity.getfName());
            preparedStatement.setString(2, teachEntity.getlName());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    //The deletion operation is performed by receiving the teacher ID
    public void removeTeach(TeachEntity teachEntity) {
        String removeQuery = "Delete from teacher where teachid=?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(removeQuery)) {
            preparedStatement.setInt(1, teachEntity.getSsn());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    //This method was created to call the Teacher list
    public List<TeachEntity> loadTeach() {
        String showQuery = "Select * from teacher";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(showQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<TeachEntity> teachers = new ArrayList<>();
            while (resultSet.next()) {
                TeachEntity teacher = new TeachEntity();
                teacher.setSsn(resultSet.getInt("Teachid"));
                teacher.setfName(resultSet.getString("fname"));
                teacher.setlName(resultSet.getString("lname"));
                teachers.add(teacher);
            }
            return teachers;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    //This method was created to display Teachers
    public List<TeachEntity> printTeach() {
        List<TeachEntity> teachers = loadTeach();
        for (TeachEntity model : teachers) {
            System.out.println(model.toString());
        }
        return teachers;
    }

//This method was created to find students using the user's teacher ID
    public List<StuEntity> findStudent(StuEntity stuEntity) {
        String showQuery = "select  s.stuid,s.fname,s.lname  from  students s " +
                " join stu_teach st  on s.stuid=st.stuid " +
                " where st.teachid = ? ";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(showQuery)) {
            preparedStatement.setInt(1,stuEntity.getSsn());

            ResultSet resultSet = preparedStatement.executeQuery();
            List<StuEntity> stuList = new ArrayList<>();
            while (resultSet.next()) {
                StuEntity stu = new StuEntity();
                stu.setSsn(resultSet.getInt("stuid"));
                stu.setfName(resultSet.getString("fname"));
                stu.setlName(resultSet.getString("lname"));
                stuList.add(stu);
            }
            return stuList;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }


}
