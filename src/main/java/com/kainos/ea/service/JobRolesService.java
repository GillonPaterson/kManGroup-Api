package com.kainos.ea.service;

import com.kainos.ea.data.JobRolesDAO;
import com.kainos.ea.model.Competency;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobSpecModel;
import com.kainos.ea.util.DatabaseConnector;
import org.checkerframework.checker.units.qual.C;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JobRolesService {

    JobRolesDAO jobRolesDAO = new JobRolesDAO();
    DatabaseConnector databaseConnector = new DatabaseConnector();

    public JobRolesService(){

    }

    public JobRolesService(JobRolesDAO jobRolesDAO, DatabaseConnector databaseConnector){
        this.jobRolesDAO = jobRolesDAO;
        this.databaseConnector = databaseConnector;
    }

    public List<JobRole> getJobRoles() throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return jobRolesDAO.getJobRolesFromDatabase(connection);
    }

    public JobSpecModel getJobSpec(int jobRoleID) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return jobRolesDAO.getJobSpecFromDatabase(connection, jobRoleID);
    }

    public Competency getComp(int jobRoleID) throws SQLException {
        Competency competency = new Competency();
        Connection connection = databaseConnector.getConnection();
        competency = jobRolesDAO.getJobCompFromDatabase(connection, jobRoleID);
        if (competency.getCompetencyStage().equals("stage 1")){
             Competency competency1 = new Competency(competency.getBandLevel(), competency.getCompetencyStage1());
             return competency1;
        } else if (competency.getCompetencyStage().equals("stage 2")){
            Competency competency1 = new Competency(competency.getBandLevel(), competency.getCompetencyStage1(), competency.getCompetencyStage2());
            return competency1;
        } else if (competency.getCompetencyStage().equals("stage 3")){
            Competency competency1 = new Competency(competency.getBandLevel(), competency.getCompetencyStage1(), competency.getCompetencyStage2(), competency.getCompetencyStage3());
            return competency1;
        }else if (competency.getCompetencyStage().equals("stage 4")){
            Competency competency1 = new Competency(competency.getBandLevel(), competency.getCompetencyStage1(), competency.getCompetencyStage2(), competency.getCompetencyStage3(), competency.getCompetencyStage4());
            return competency1;
        } else {
            System.out.println("Null response");
            return null;
        }
    }
}
