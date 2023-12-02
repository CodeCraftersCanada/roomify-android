package edu.lambton.roomify.navigation;

import android.content.Context;
import android.content.Intent;

public class AppNavigation {

    public void navigateToStudentRegistration(Context context) {
        Intent intent = new Intent(context, null);
        context.startActivity(intent);
    }

    public void navigateToLandlordRegistration(Context context) {
        Intent intent = new Intent(context, null);
        context.startActivity(intent);
    }
}
