/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despairs.games.tetris.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author EKovtunenko
 */
public class EventManager {
    private Map<Integer, ArrayList<EventReceiver>> receivers = new HashMap<>();

    private static EventManager instance = new EventManager();

    public interface EventReceiver {
        void onEventReceive(int eventId, Object... data);
    }

    public static EventManager getInstance() {
        return instance;
    }

    public void registerReceiver(int eventId, EventReceiver receiver) {
        ArrayList<EventReceiver> eventReceivers = receivers.get(eventId);
        if (eventReceivers == null) {
            eventReceivers = new ArrayList<>();
            receivers.put(eventId, eventReceivers);
        }
        if (!eventReceivers.contains(receiver)) {
            eventReceivers.add(receiver);
        }
    }

    public void unregisterReceiver(int eventId, EventReceiver receiver) {
        ArrayList<EventReceiver> eventReceivers = receivers.get(eventId);
        if (eventReceivers == null) {
            eventReceivers = new ArrayList<>();
            receivers.put(eventId, eventReceivers);
        }
        if (eventReceivers.contains(receiver)) {
            eventReceivers.remove(receiver);
        }
    }

    public void sendEvent(int eventId, Object... data) {
        ArrayList<EventReceiver> eventReceivers = receivers.get(eventId);
        if (eventReceivers != null && !eventReceivers.isEmpty()) {
            for (EventReceiver receiver : eventReceivers) {
                receiver.onEventReceive(eventId, data);
            }
        }
    }
}
