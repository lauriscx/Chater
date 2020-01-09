package com.gym.chater.Data;

public class FragmentManager {
    private static androidx.fragment.app.FragmentManager manager = null;

    public static void setManager(androidx.fragment.app.FragmentManager manager){
        FragmentManager.manager = manager;
    }

    public static androidx.fragment.app.FragmentManager getManager(){
        return manager;
    }
}
