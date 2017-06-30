package utils.time;

public class Time {

    public enum Unit {
        NANO(1),
        MIN(6e10);

        private final double nanoToUnit;

        Unit(double nanoToUnit) {
            this.nanoToUnit = nanoToUnit;
        }

        public double getNanoToUnit() {
            return nanoToUnit;
        }
    }

    public interface F<T> {
        T run();
    }

    public static <T> T log(F<T> f, String msg, Unit unit) {
        long start = System.nanoTime();

        T ans = f.run();

        long elapsed = System.nanoTime() - start;

        System.out.println(msg + " " + elapsed / unit.getNanoToUnit() + " " + unit);

        return ans;
    }

    public interface Falt {
        void run();
    }

    public static void log(Falt originalF, String msg, Unit unit) {
        F<Void> f = () -> {
            originalF.run();
            return null;
        };

        log(f, msg, unit);
    }

    public static void log(Falt originalF, String msg) {
        log(originalF, msg, Unit.MIN);
    }

    public static <T> T log(F<T> f, String msg) {
        return log(f, msg, Unit.MIN);
    }
}


