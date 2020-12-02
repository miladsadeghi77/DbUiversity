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

public class StuService{
// This class is designed to perform student operations
    //Update operation performed by checking the student ID
    public void updateStu(StuEntity stuEntity) {
        String updateQuery = "Update students " +
                "set fname = ? , lname = ?  " +
                "where stuId = ? ";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, stuEntity.getfName());
            preparedStatement.setString(2, stuEntity.getlName());
            preparedStatement.setInt(3, stuEntity.getSsn());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

//The addition operation is done by receiving the ID, name and lastname of the student
    public void addStu(StuEntity stuEntity) {
        String addQuery = "insert into students (stuid,fname,lname)  values (?,?,?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addQuery)) {
            preparedStatement.setInt(1,stuEntity.getSsn());
            preparedStatement.setString(2, stuEntity.getfName());
            preparedStatement.setString(3, stuEntity.getlName());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    //The deletion operation is performed by receiving the student ID
    public void removeStu(StuEntity stuEntity) {
        String removeQuery = "Delete from students where stuId=?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(removeQuery)) {
            preparedStatement.setInt(1, stuEntity.getSsn());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    //This method was created to call the student list
    public List<StuEntity> loadStu() {
        String showQuery = "Select * from students";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(showQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<StuEntity> students = new ArrayList<>();
            while (resultSet.next()) {
                StuEntity student = new StuEntity();
                student.setSsn(resultSet.getInt("stuid"));
                student.setfName(resultSet.getString("fname"));
                student.setlName(resultSet.getString("lname"));
                students.add(student);
            }
            return students;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
//This method was created to display students
    public List<StuEntity> printStu() {
        List<StuEntity> students = loadStu();
        for (StuEntity model : students) {
            System.out.println(model.toString());
        }
        return students;
    }
//This method was created to find teachers using the user's student ID
    public List<TeachEntity> findTeach(TeachEntity teachEntity) {
        String showQuery = "select  t.teachId,t.fname,t.lname  from  teacher t " +
                " join stu_teach st  on t.teachid=st.teachId " +
                " where st.stuid = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(showQuery)) {
            preparedStatement.setInt(1,teachEntity.getSsn());

            ResultSet resultSet = preparedStatement.executeQuery();
            List<TeachEntity> teachList = new ArrayList<>();
            while (resultSet.next()) {
                TeachEntity teach = new TeachEntity();
                teach.setSsn(resultSet.getInt("teachid"));
                teach.setfName(resultSet.getString("fname"));
                teach.setlName(resultSet.getString("lname"));
                teachList.add(teach);
            }
            return teachList;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
