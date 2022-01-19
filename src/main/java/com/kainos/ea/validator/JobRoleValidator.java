package com.kainos.ea.validator;

import com.kainos.ea.model.AddJobRole;

public class JobRoleValidator {

    public String addJobRoleValidator(AddJobRole addJobRole) {
        return validationMethod(addJobRole);
    }

    public String validationMethod(AddJobRole addJobRole) {
        String roleName;
        String roleSpec;
        String roleResp;
        String jobLink;

        roleName = addJobRole.getJobRole();
        roleSpec = addJobRole.getJobSpec();
        roleResp = addJobRole.getJobResponsibilities();
        jobLink = addJobRole.getJobLink();

        if (roleName == "") {
            return "The role name must be entered";

        }
        if (roleName.length() > 40) {
            return "The role name cannot be anymore than 40 characters";
        }

        char[] chars = roleName.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                return "The role name cannot contain numbers";
            }
        }

        if (roleName.charAt(0) == ' ' || roleName.charAt(roleName.length() - 1) == ' ') {
            return "The role name cannot contain empty spaces at the start and end";
        }

        if (roleSpec == "") {
            return "The job specification must be entered";
        }


        if (roleSpec.length() > 1000) {
            return "The job specification cannot be anymore than 1000 characters";
        }

        if (roleResp == "") {
            return "The job responsibilities must be entered";
        }


        if (roleResp.length() > 1000) {
            return "The job responsibilities cannot be anymore than 1000 characters";
        }

        if (jobLink.charAt(0) != 'h' || jobLink.charAt(1) != 't' || jobLink.charAt(2) != 't' || jobLink.charAt(3) != 'p' || jobLink.charAt(4) != 's' || jobLink.charAt(5) != ':' || jobLink.charAt(6) != '/' || jobLink.charAt(7) != '/') {
            return "The link must start with 'https://'";
        }

        if (jobLink.length() < 9) {
            return "A link must be entered";
        }

        if (jobLink.length() > 500) {
            return "The link cannot be anymore than 500 characters";
        }

        return null;
    }
}
