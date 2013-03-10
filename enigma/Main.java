package enigma;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.ArrayList;

/** Enigma simulator.
 *  @author Patrick Lu
 */

public final class Main {

    /** Rotor and Reflector variables in a hashtable. */
    private static Hashtable<String, Rotor> rotors =
        new Hashtable<String, Rotor>();
    /** Reflector variable in hashtable. */
    private static Hashtable<String, Reflector> reflectors =
        new Hashtable<String, Reflector>();

    /** Process a sequence of encryptions and decryptions, as
     *  specified in the input from the standard input.  Print the
     *  results on the standard output. Exits normally if there are
     *  no errors in the input; otherwise with code 1. */
    public static void main(String[] unused) {
        Machine M = new Machine();
        BufferedReader input =
            new BufferedReader(new InputStreamReader(System.in));

        buildRotors();

        try {
            while (true) {
                String line = input.readLine();
                if (line == null) {
                    break;
                }
                if (isConfigurationLine(line)) {
                    configure(M, line);
                } else {
                    printMessageLine(M.convert(standardize(line)));
                }
            }
        } catch (IOException excp) {
            System.err.printf("Input error: %s%n", excp.getMessage());
            System.exit(1);
        }
    }

    /** Return true iff LINE is an Enigma configuration line. */
    static boolean isConfigurationLine(String line) {
        String[] split = line.split(" ");
        boolean reflector = false;
        boolean rotor1 = false;
        boolean rotor2 = false;
        boolean rotor3 = false;
        boolean start = false;
        String[] rot = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII"};
        if (split[0].equals("*")) {
            if (split[2].equals(split[3]) || split[2].equals(split[4])
                || split[3].equals(split[4])) {
                return false;
            }
            if (split[1].equals("B") || split[1].equals("C")) {
                reflector = true;
            }
            for (int i = 0; i < 8; i += 1) {
                if (split[2].equals(rot[i])) {
                    rotor1 = true;
                }
            }
            for (int i = 0; i < 8; i += 1) {
                if (split[2].equals(rot[i])) {
                    rotor2 = true;
                }
            }
            for (int i = 0; i < 8; i += 1) {
                if (split[3].equals(rot[i])) {
                    rotor3 = true;
                }
            }
            start = containsletters(split[5]);
            return (reflector && rotor1 && rotor2 && rotor3 && start);
        }
        return false;
    }

    /** Return true iff string contains all letters.
     * @param line string
     */

    static boolean containsletters(String line) {
        if (line.length() != 4) {
            return false;
        }
        for (int i = 0; i < line.length(); i += 1) {
            char k = line.charAt(i);
            if (!Character.isUpperCase(k)) {
                return false;
            }
        }
        return true;
    }

    /** Configure M according to the specification given on CONFIG,
     *  which must have the format specified in the assignment. */
    static void configure(Machine M, String config) {
        String[] split = config.split(" ");
        M.setRotors(reflectors.get(split[1]), rotors.get(split[2]),
                    rotors.get(split[3]), rotors.get(split[4]));
        M.setPositions(split[5]);
    }
    /** Return the result of converting LINE to all upper case,
     *  removing all blanks.  It is an error if LINE contains
     *  characters other than letters and blanks. */
    static String standardize(String line) {
        String result = line.replaceAll("\\s", "");
        result = result.toUpperCase();
        for (int i = 0; i < result.length(); i += 1) {
            char k = result.charAt(i);
            if (!Character.isLetter(k)) {
                System.exit(1);
            }
        }
        return result;
    }

    /** Print MSG in groups of five (except that the last group may
     *  have fewer letters). */
    static void printMessageLine(String msg) {
        String results = "";
        ArrayList<String> holder = new ArrayList<String>();
        for (int i = 0; i < msg.length(); i += 5) {
            holder.add(msg.substring(i,
                                        Math.min(msg.length(), i + 5)));
        }
        for (int i = 0; i < holder.size(); i += 1) {
            results += holder.get(i) + " ";
        }
        System.out.println(results);
    }

    /** Create all the necessary rotors. */
    static void buildRotors() {
        String roto1 = "E K M F L G D Q V Z N T O W Y H X U S P A I B R C J";
        String roto2 = "A J D K S I R U X B L H W T M C Q G Z N P Y F V O E";
        String roto3 = "B D F H J L C P R T X V Z N Y E I W G A K M U S Q O";
        String roto4 = "E S O V P Z J A Y Q U I R H X L N F T G K D C M W B";
        String roto5 = "V Z B R G I T Y U P S D N H L X A W M J Q O F E C K";
        String roto6 = "J P G V O U M F Y Q B E N H Z R D K A S X L I C T W";
        String roto7 = "N Z J H G R C X M Y S W B O U F A I V L P E K Q D T";
        String roto8 = "F K Q H T L X O C B J S P D Z R A M E W N I U Y G V";
        String B = "Y R U H Q S L D P X N G O K M I E B F Z C W V J A T";
        String C = "F V P J I A O Y E D R Z X W G C T K U Q S B N M H L";
        roto1 = roto1.replaceAll("\\s", "");
        roto2 = roto2.replaceAll("\\s", "");
        roto3 = roto3.replaceAll("\\s", "");
        roto4 = roto4.replaceAll("\\s", "");
        roto5 = roto5.replaceAll("\\s", "");
        roto6 = roto6.replaceAll("\\s", "");
        roto7 = roto7.replaceAll("\\s", "");
        roto8 = roto8.replaceAll("\\s", "");
        B = B.replaceAll("\\s", "");
        C = C.replaceAll("\\s", "");
        rotors.put("I", new Rotor("I", roto1, "Q", 'A'));
        rotors.put("II", new Rotor("II", roto2, "E", 'A'));
        rotors.put("III", new Rotor("III", roto3, "V", 'A'));
        rotors.put("IV", new Rotor("IV", roto4, "J", 'A'));
        rotors.put("V", new Rotor("V", roto5, "Z", 'A'));
        rotors.put("VI", new Rotor("VI", roto6, "ZM", 'A'));
        rotors.put("VII", new Rotor("VII", roto7, "ZM", 'A'));
        rotors.put("VIII", new Rotor("VIII", roto8, "ZM", 'A'));
        reflectors.put("B", new Reflector("B", B, "", 'A'));
        reflectors.put("C", new Reflector("C", C, "", 'A'));
    }
}

