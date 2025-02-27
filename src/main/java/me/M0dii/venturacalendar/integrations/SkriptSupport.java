package me.m0dii.venturacalendar.integrations;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.util.Getter;
import me.m0dii.venturacalendar.VenturaCalendar;
import me.m0dii.venturacalendar.integrations.skript.ExprCalendarDay;
import me.m0dii.venturacalendar.integrations.skript.ExprCalendarMonth;
import me.m0dii.venturacalendar.integrations.skript.ExprCalendarYear;
import me.m0dii.venturacalendar.base.events.NewDayEvent;
import me.m0dii.venturacalendar.base.dateutils.Date;
import org.bukkit.World;

public class SkriptSupport {
    private final VenturaCalendar plugin;
    private boolean enabled = false;

    public SkriptSupport(VenturaCalendar plugin) {
        this.plugin = plugin;
        if(plugin.getServer().getPluginManager().getPlugin("Skript") != null) {
            registerSkript();
        }
    }

    private void registerSkript() {
        enabled = true;

        // Register Events
        Skript.registerEvent("ventura calendar new day", SimpleEvent.class, NewDayEvent.class,
                "ventura calendar new day");

        // Register Event Values using modern API
        EventValues.registerEventValue(NewDayEvent.class, World.class, e -> e.getWorld());
        EventValues.registerEventValue(NewDayEvent.class, Date.class, e -> e.getDate());

        // Register Expressions
        Skript.registerExpression(ExprCalendarDay.class, Number.class, ExpressionType.PROPERTY,
                "calendar day", "ventura calendar day");
        
        Skript.registerExpression(ExprCalendarMonth.class, Number.class, ExpressionType.PROPERTY,
                "calendar month", "ventura calendar month");
        
        Skript.registerExpression(ExprCalendarYear.class, Number.class, ExpressionType.PROPERTY,
                "calendar year", "ventura calendar year");
    }

    public boolean isEnabled() {
        return enabled;
    }
}
