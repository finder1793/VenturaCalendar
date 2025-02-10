package me.m0dii.venturacalendar.base.events;

import me.m0dii.venturacalendar.base.dateutils.Date;
import me.m0dii.venturacalendar.base.dateutils.TimeSystem;
import org.bukkit.World;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class NewDayEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;

    private final TimeSystem ts;
    private final World world;
    private final Date date;

    public NewDayEvent(TimeSystem ts, World world, Date date) {
        this.ts = ts;
        this.world = world;
        this.date = date;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public TimeSystem getTimeSystem() {
        return ts;
    }

    public World getWorld() {
        return world;
    }

    public Date getDate() {
        return date;
    }
}
