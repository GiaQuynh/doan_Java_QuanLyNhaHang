/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.prefs.Preferences;

/**
 *
 * @author Admin
 */
public class SharedPreferences {

    private static final String USER_KEY = "user";
    private static final String PASS_KEY = "pass";
    private static final String ROLE_KEY = "role";
    private static final String TIME_KEY = "time";

    public static void saveCredentials(String user, String pass, String roler, String time) {
        Preferences prefs = Preferences.userNodeForPackage(SharedPreferences.class);
        prefs.put(USER_KEY, user);
        prefs.put(PASS_KEY, pass);
        prefs.put(ROLE_KEY, roler);
        prefs.put(TIME_KEY, time);
    }

    public static String getUser() {
        Preferences prefs = Preferences.userNodeForPackage(SharedPreferences.class);
        return prefs.get(USER_KEY, null);
    }

    public static String getTime() {
        Preferences prefs = Preferences.userNodeForPackage(SharedPreferences.class);
        return prefs.get(TIME_KEY, null);
    }

    public static String getRole() {
        Preferences prefs = Preferences.userNodeForPackage(SharedPreferences.class);
        return prefs.get(ROLE_KEY, null);
    }

    public static String getPassword() {
        Preferences prefs = Preferences.userNodeForPackage(SharedPreferences.class);
        return prefs.get(PASS_KEY, null);
    }

    public static void clearCredentials() {
        Preferences prefs = Preferences.userNodeForPackage(SharedPreferences.class);
        prefs.remove(USER_KEY);
        prefs.remove(PASS_KEY);
        prefs.remove(ROLE_KEY);
        prefs.remove(TIME_KEY);
    }
}
