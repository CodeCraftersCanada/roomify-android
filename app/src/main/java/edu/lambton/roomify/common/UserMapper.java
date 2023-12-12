package edu.lambton.roomify.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import edu.lambton.roomify.landlord.dto.UserResponse;
import edu.lambton.roomify.landlord.model.User;

public class UserMapper {

    @Nullable
    public static User mapUserResponseToUser(@NonNull UserResponse userResponse) {
        UserResponse.User user = userResponse.getUser();
        if (user == null) {
            return null;
        }

        return new User(
                user.getId(),
                user.getUid(),
                user.getUserType().getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getCollege(),
                user.getAddress(),
                user.getImagePath(),
                user.getLatitude(),
                0.0
        );
    }
}
