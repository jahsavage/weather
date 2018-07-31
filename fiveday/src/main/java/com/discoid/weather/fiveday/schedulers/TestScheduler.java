package com.discoid.weather.fiveday.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class TestScheduler implements IScheduler {
    @Override
    public Scheduler currentThread() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler computationThread() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler mainThread() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler backgroundThread() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler recursionThread() {
        return Schedulers.trampoline();
    }
}
