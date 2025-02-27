package me.m0dii.venturacalendar.integrations.skript;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.m0dii.venturacalendar.VenturaCalendar;
import me.m0dii.venturacalendar.base.dateutils.DateCalculator;
import me.m0dii.venturacalendar.base.dateutils.TimeSystem;
import org.bukkit.World;

public class ExprCalendarDay extends SimplePropertyExpression<World, Number> {

    static {
        register(ExprCalendarDay.class, Number.class,
                "[ventura] calendar day",
                "world");
    }

    @Override
    protected String getPropertyName() {
        return "calendar day";
    }

    @Override
    public Number convert(World world) {
        TimeSystem ts = VenturaCalendar.getInstance().getTimeConfig().getTimeSystem();
        if(ts.isRealTime()) {
            return DateCalculator.realTimeNow().getDay();
        }
        return DateCalculator.fromTicks(world.getFullTime(), ts).getDay();
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }
}
