import java.io.PrintStream;
import java.util.Random;

public class LCGPredictor {
    static final long multiplier = 0x5DEECE66DL;
    static final long addend = 0xBL;
    static final long mask = (1L << 48) - 1;
    static PrintStream out = System.out;

    static int next(long oldseed) {
	long tmp = ((oldseed * multiplier + addend) & mask);
	return (int) (tmp >>> 16);
    }

    public static void main(String[] args) {
	Random random = new Random();
	long seed = 0, oldseed = random.nextInt(), nextseed = random.nextInt();

	for (int i = 0; i < (1 << 16); i++) {
	    seed = ((long) oldseed << 16) + i;
	    if (next(seed) == nextseed) {
		seed ^= multiplier;
		break;
	    }
	}

	Random test = new Random(seed);
	out.println("nextseed=" + test.nextInt() + " 1=" + test.nextInt() + " 2=" + test.nextInt());
	out.println("nextseed=" + nextseed + " 1=" + random.nextInt() + " 2=" + random.nextInt());
    }
}
