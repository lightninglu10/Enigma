package enigma;

/** Class that represents a rotor in the enigma machine.
 *  @author Patrick Lu
 */
class Rotor {

    /** Position of rotor. */
    private int pos;

    /** Name of rotor. */
    private String which;

    /** position of notch. */
    private String noch;

    /** string permutation. */
    private String perm;

    /** obtain char. */
    private static final int SIZE = 65;

    /** Cycle through alphabet. */
    private static final int ALPHA = 26;

    /** Constructor for Rotor class.
     * @param name name
     * @param permutation permute
     * @param notch noch
     * @param position pos */
    public Rotor(String name, String permutation, String notch, char position) {
        which = name;
        perm = permutation;
        pos = toIndex(position);
        noch = notch;
    }

    /** Assuming that P is an integer in the range 0..25, returns the
     *  corresponding upper-case letter in the range A..Z. */
    static char toLetter(int p) {
        p += SIZE;
        final char result = (char) p;
        return result;
    }

    /** Assuming that C is an upper-case letter in the range A-Z, return the
     *  corresponding index in the range 0..25. Inverse of toLetter. */
    static int toIndex(char c) {
        return c - 'A';
    }

    /** Return my current rotational position as an integer between 0
     *  and 25 (corresponding to letters 'A' to 'Z').  */
    int getPosition() {
        return pos;
    }

    /** Set getPosition() to POSN.  */
    void setPosition(int posn) {
        pos = posn;
    }

    /** Return the conversion of P (an integer in the range 0..25)
     *  according to my permutation. */
    int convertForward(int p) {
        p = (p + pos) % ALPHA;
        return (toIndex(perm.charAt(p)) - pos + ALPHA) % ALPHA;
    }

    /** Return the conversion of E (an integer in the range 0..25)
     *  according to the inverse of my permutation. */
    int convertBackward(int e) {
        e = (e + pos) % ALPHA;
        return (perm.indexOf(toLetter(e)) - pos + ALPHA) % ALPHA;
    }

    /** Returns true iff I am positioned to allow the rotor to my left
     *  to advance. */
    boolean atNotch() {
        if (noch.indexOf(toLetter(pos)) == -1) {
            return false;
        }
        return true;
    }

    /** Advance me one position. */
    void advance() {
        int a = (pos + 1) % ALPHA;
        setPosition(a);
    }

}
