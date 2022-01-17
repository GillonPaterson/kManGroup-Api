package com.kainos.ea.data;

import com.kainos.ea.model.JobTraining;
import com.kainos.ea.model.TrainingAddBandResponseModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainingDAO {
    public List<TrainingAddBandResponseModel> getTrainingFromDatabase(Connection connection) throws SQLException {
        List<TrainingAddBandResponseModel> training = new ArrayList<>();
        String query = "SELECT trainingID, trainingName, trainingLink FROM training";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            TrainingAddBandResponseModel trainingAddBandResponseModel = new TrainingAddBandResponseModel(rs.getInt("trainingID"), rs.getString("trainingName"), rs.getString("trainingLink"));
            training.add(trainingAddBandResponseModel);
        }
        return training;
    }

    public boolean insertIntoBandLevelsTraining(Connection connection, int bandLevelID, int trainingID) throws SQLException{
        String query = "INSERT INTO bandLevelsTraining (jobBandLevelID, trainingID) VALUES (?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, bandLevelID);
        preparedStatement.setInt(2, trainingID);

        int count = preparedStatement.executeUpdate();
        if(count > 0){
            return true;
        }
        else{
            return false;
        }
    }
}
