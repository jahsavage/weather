package com.discoid.weather.fiveday.schedulers;


import io.reactivex.Scheduler;

/**
 * Permits scheduler substitution for testing
 *
 * Created by jahsavage on 07/08/2016.
 */
public interface IScheduler {

    // For debugging
    Scheduler currentThread();

    // CPU Calculations
    Scheduler computationThread();

    // Android main UI thread
    Scheduler mainThread();

    // IO general background
    Scheduler backgroundThread();

    // recursion thread - executes tasks in a FIFO
    Scheduler recursionThread();

}
