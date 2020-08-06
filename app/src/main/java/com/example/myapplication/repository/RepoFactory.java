package com.example.myapplication.repository;

import android.content.Context;

public class RepoFactory {


    public static IItemRepository getItemRepository(Context context){
        return new ItemDBRepository(context);
    }
}
