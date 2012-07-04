/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.freedomotic.objects.impl;

import it.freedomotic.persistence.TriggerPersistence;
import it.freedomotic.reactions.Trigger;

/**
 *
 * @author enrico
 */
public class Clock extends Decoration {

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected void createCommands() {
        //no commands for this kind of objects
        super.createCommands();
    }

    @Override
    protected void createTriggers() {
        super.createTriggers();
        Trigger everySecond = new Trigger();
        everySecond.setName("Every one second");
        everySecond.setDescription("schedule actions to be executed at a fixed interval of 1 second");
        everySecond.setChannel("app.event.sensor.calendar.event.schedule");
        everySecond.getPayload().addStatement("object.name", getPojo().getName());
        everySecond.setSuspensionTime(1000);

        Trigger everyMinute = new Trigger();
        everyMinute.setName("Every one minute");
        everyMinute.setDescription("schedule actions to be executed at a fixed interval of 60 second");
        everyMinute.setChannel("app.event.sensor.calendar.event.schedule");
        everyMinute.getPayload().addStatement("object.name", getPojo().getName());
        everyMinute.setSuspensionTime(60000);

        Trigger morning = new Trigger();
        morning.setName("Every minute while is morning");
        morning.setDescription("executes a command every 60 second from 8:00 to 12:00");
        morning.setChannel("app.event.sensor.calendar.event.schedule");
        morning.getPayload().addStatement("object.name", getPojo().getName());
        morning.getPayload().addStatement("AND", "time.hour", "GREATER_THEN", "7");
        morning.getPayload().addStatement("AND", "time.hour", "LESS_THEN", "13");
        morning.setSuspensionTime(60000);

        TriggerPersistence.add(everySecond);
        TriggerPersistence.add(everyMinute);
        TriggerPersistence.add(morning);
    }
}