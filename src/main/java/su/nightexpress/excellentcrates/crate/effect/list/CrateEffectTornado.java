package su.nightexpress.excellentcrates.crate.effect.list;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import su.nexmedia.engine.api.particle.SimpleParticle;
import su.nightexpress.excellentcrates.crate.effect.CrateEffectModel;
import su.nightexpress.excellentcrates.crate.effect.CrateEffectTask;

import java.util.ArrayList;
import java.util.List;

public class CrateEffectTornado extends CrateEffectTask {

    private static final double Y_OFFSET           = 0.15D;
    private static final float  TORNADO_HEIGHT     = 3.15F;
    private static final float  MAX_TORNADO_RADIUS = 2.25F;
    private static final double DISTANCE           = 0.375D;

    public CrateEffectTornado() {
        super(CrateEffectModel.TORNADO, 4L, 7);
    }

    @Override
    public void doStep(@NotNull Location loc2, @NotNull SimpleParticle particle, int step) {
        Location loc = loc2.clone().add(0.0D, 0.5D, 0.0D);
        double offset = 0.25D * (MAX_TORNADO_RADIUS * (2.35D / TORNADO_HEIGHT));
        double vertical = TORNADO_HEIGHT - DISTANCE * step;

        double radius = offset * vertical;
        if (radius > MAX_TORNADO_RADIUS) {
            radius = MAX_TORNADO_RADIUS;
        }
        for (Vector vector : this.createCircle(vertical, radius)) {
            particle.play(loc.add(vector), 0.1f, 0.0f, 3);
            loc.subtract(vector);
        }
        loc.subtract(0.0D, Y_OFFSET, 0.0D);
    }

    private List<Vector> createCircle(double vertical, double radius) {
        double amount = radius * 64.0D;
        double d2 = 6.283185307179586D / amount;
        List<Vector> vectors = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            double d3 = i * d2;
            double cos = radius * Math.cos(d3);
            double sin = radius * Math.sin(d3);
            Vector vector = new Vector(cos, vertical, sin);
            vectors.add(vector);
        }
        return vectors;
    }
}
