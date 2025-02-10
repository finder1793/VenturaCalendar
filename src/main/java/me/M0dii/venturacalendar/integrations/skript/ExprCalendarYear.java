package me.m0dii.venturacalendar.integrations.skript;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.m0dii.venturacalendar.VenturaCalendar;
import me.m0dii.venturacalendar.base.dateutils.DateCalculator;
import me.m0dii.venturacalendar.base.dateutils.TimeSystem;
import org.bukkit.World;

public class ExprCalendarYear extends SimplePropertyExpression<World, Number> {

    static {
        register(ExprCalendarYear.class, Number.class,
                "[ventura] calendar year",
                "world");
    }

    @Override
    protected String getPropertyName() {
        return "calendar year";
    }

    @Override
    public Number convert(World world) {
        TimeSystem ts = VenturaCalendar.getInstance().getTimeConfig().getTimeSystem();
        if(ts.isRealTime()) {
            return DateCalculator.realTimeNow().getYear();
        }
        return DateCalculator.fromTicks(world.getFullTime(), ts).getYear();
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }
}
