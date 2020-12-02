package service;

import entities.StuEntity;
import entities.Stu_TeachEntity;
import util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Stu_TeachService {
    //In this class, it is possible to select a teacher for the student using an interface table
    //This method was created to add the student ID and the teacher ID to the interface table
    public void addStuTeach(Stu_TeachEntity stu_teachEntity) {
        String addQuery="insert into stu_teach (teachId,stuid)  values (?,?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addQuery)) {
            preparedStatement.setInt(1, stu_teachEntity.getIdTeacher());
            preparedStatement.setInt(2,stu_teachEntity.getIdStudent());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
}
