package enigma;

/** Class that represents a reflector in the enigma.
 *  @author Patrick Lu
 */
class Reflector extends Rotor {
    /** Constructor for Reflector class.
     * @param name0 name
     * @param permutation0 permute
     * @param position0 char
     * @param notch0 notch
     */
    public Reflector(String name0, String permutation0, String notch0,
                     char position0) {
        super(name0, permutation0, notch0, position0);
    }

    /** Returns a useless value; should never be called. */
    @Override
    int convertBackward(int unused) {
        throw new UnsupportedOperationException();
    }

    /** Reflectors do not advance. */
    @Override
    void advance() {
    }

}
