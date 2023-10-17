package com.sockib.springresourceserver.services;

import com.sockib.springresourceserver.model.dto.UserAccountDetails;
import com.sockib.springresourceserver.model.dto.UserAccountStats;

public interface UserService {

    UserAccountDetails getUserAccountDetails(Long userId);
    UserAccountDetails modifyUserAccountDetails(Long userId, UserAccountDetails newAccountDetails);
    UserAccountStats getUserAccountStats(Long userId);

}
