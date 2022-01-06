package com.kainos.ea.validator;

import com.kainos.ea.model.AddJobRole;

public class JobRoleValidator {

    public String addJobRoleValidator(AddJobRole addJobRole){
        String roleName = addJobRole.getJobRole();
        String roleSpec = addJobRole.getJobSpec();
        String roleResp = addJobRole.getJobResponsibilities();
        String jobLink = addJobRole.getJobLink();

        if(roleName == "")
            return "The role name must be entered";

        if (roleName.length() > 40) {
            return "The role name cannot be anymore than 40 characters";
        }

        char[] chars = roleName.toCharArray();

        for(int i = 0; i < chars.length; i++){
            if(Character.isDigit(chars[i]))
                return "The role name cannot contain numbers";
        }

        if (roleName.charAt(0) == ' ' || roleName.charAt(roleName.length() - 1) == ' ') {
            return "The role name cannot contain empty spaces at the start and end";
        }

        if(roleSpec == "")
            return "The job specification must be entered";

        if(roleResp == "")
            return "The job responsibilities must be entered";

        if (jobLink.charAt(0) != 'h' || jobLink.charAt(1) != 't' || jobLink.charAt(2) != 't' || jobLink.charAt(3) != 'p' || jobLink.charAt(4) != 's' || jobLink.charAt(5) != ':' || jobLink.charAt(6) != '/' || jobLink.charAt(7) != '/')
            return "The link must start with 'https://'";

        if (jobLink.length() < 9)
            return "A link must be entered";

        return null;
    }
}
