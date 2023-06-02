package com.ohh.icloud.component;

public class ThreadLocalComponent {

    public static ThreadLocal<Long> user_id = new ThreadLocal<>();
    public static ThreadLocal<String> user_name = new ThreadLocal<>();

    public static void setUser_id(Long userId) {
        user_id.set(userId);
    }

    public static Long getUserId() {
        return user_id.get();
    }

    public static String getUsername() {
        return user_name.get();
    }

    public static void setUserIdAndUsername(Long userId, String username) {
        user_id.set(userId);
        user_name.set(username);
    }

    public static void removeAll() {
        user_id.remove();
        user_name.remove();
    }
}
