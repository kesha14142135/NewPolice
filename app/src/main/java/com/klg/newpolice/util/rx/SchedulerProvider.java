package com.klg.newpolice.util.rx;

import io.reactivex.Scheduler;


/**
 * Created by sergejkozin on 9/22/17.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
