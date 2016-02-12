package com.example.cattinder.rx;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Hint: Decouple your scheduling from your algorithm!
 *
 * "Objects are abstractions of processing. Threads are abstractions of schedule."
 */
public class SchedulerFactory {

    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }

    public Scheduler immediate() {
        return Schedulers.immediate();
    }

    public Scheduler trampoline() {
        return Schedulers.trampoline();
    }

    public Scheduler newThread() {
        return Schedulers.newThread();
    }

    public Scheduler computation() {
        return Schedulers.computation();
    }

    public Scheduler io() {
        return Schedulers.io();
    }

    public Scheduler test() {
        return Schedulers.test();
    }
}
