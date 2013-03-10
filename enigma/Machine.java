package enigma;

/** Class that represents a complete enigma machine.
 *  @author Patrick Lu
 */
class Machine {

    /** Setting the rotors and reflectors. */
    private Reflector reflect;
    /** Left rotor. */
    private Rotor lefts;
    /** Middle rotor. */
    private Rotor middles;
    /** Right rotor. */
    private Rotor rights;

    /** Set my rotors to (from left to right), REFLECTOR, LEFT,
     *  MIDDLE, and RIGHT.  Initially, their positions are all 'A'. */
    void setRotors(Reflector reflector,
                   Rotor left, Rotor middle, Rotor right) {
        reflect = reflector;
        lefts = left;
        middles = middle;
        rights = right;
    }

    /** Set the positions of my rotors according to SETTING, which
     *  must be a string of 4 upper-case letters. The first letter
     *  refers to the reflector position, and the rest to the rotor
     *  positions, left to right. */
    void setPositions(String setting) {
        if (setting.length() != 4) {
            System.exit(1);
        }
        reflect.setPosition(Rotor.toIndex(setting.charAt(0)));
        lefts.setPosition(Rotor.toIndex(setting.charAt(1)));
        middles.setPosition(Rotor.toIndex(setting.charAt(2)));
        rights.setPosition(Rotor.toIndex(setting.charAt(3)));
    }

    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        int length = msg.length();
        String result = "";
        for (int i = 0; i < length; i += 1) {
            boolean advance = true;
            if (msg.charAt(i) == ' ') {
                i += 1;
                result += " ";
            }
            if (middles.atNotch()) {
                lefts.advance();
                middles.advance();
                advance = false;
            }
            if (rights.atNotch()) {
                if (advance) {
                    middles.advance();
                }
            }
            rights.advance();
            int a = rights.convertForward(Rotor.toIndex(msg.charAt(i)));
            int b = middles.convertForward(a);
            int c = lefts.convertForward(b);
            int h = reflect.convertForward(c);
            int d = lefts.convertBackward(h);
            int e = middles.convertBackward(d);
            int f = rights.convertBackward(e);
            result += Rotor.toLetter(f);
        }
        return result;
    }
}
