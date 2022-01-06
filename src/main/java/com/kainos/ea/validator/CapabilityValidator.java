package com.kainos.ea.validator;

import com.kainos.ea.model.CapabilityRequest;

public class CapabilityValidator {

    public String addCapabilityValidator(CapabilityRequest capabilityRequest){
        String capName = capabilityRequest.getCapabilityName();

        if (capName == "" | capName.length() > 20) {
            return "capability name cannot be anymore than 20 characters";
        }
        char[] chars = capName.toCharArray();
        System.out.println(chars);
        for(int i = 0; i < chars.length; i++){
            if(Character.isDigit(chars[i])){
                return "capability name cannot contain numbers";

            }

        }
        if (capName.contains(" ") == true) {
            return "capability name cannot contain empty spaces";
        }
        return null;

    }
}
