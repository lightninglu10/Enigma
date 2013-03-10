package enigma;
import java.lang.Character;
import java.lang.String;
import java.util.Arrays;
 

/** java HW1Test should test the methods defined in Progs.
 *  @author Patrick Lu
 */

public class test {
    public static void  main(String[] args) {
        String abc = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z";
        String roto1 = "E K M F L G D Q V Z N T O W Y H X U S P A I B R C J";
        String roto2 = "A J D K S I R U X B L H W T M C Q G Z N P Y F V O E";
        String roto3 = "B D F H J L C P R T X V Z N Y E I W G A K M U S Q O";
        String roto4 = "E S O V P Z J A Y Q U I R H X L N F T G K D C M W B";
        String roto5 = "V Z B R G I T Y U P S D N H L X A W M J Q O F E C K";
        String roto6 = "J P G V O U M F Y Q B E N H Z R D K A S X L I C T W";
        String roto7 = "N Z J H G R C X M Y S W B O U F A I V L P E K Q D T";
        String roto8 = "F K Q H T L X O C B J S P D Z R A M E W N I U Y G V";
        String Bb = "Y R U H Q S L D P X N G O K M I E B F Z C W V J A T";
        String Cc = "F V P J I A O Y E D R Z X W G C T K U Q S B N M H L";
        roto1 = roto1.replaceAll("\\s", "");
        roto2 = roto2.replaceAll("\\s", "");
        roto3 = roto3.replaceAll("\\s", "");
        roto4 = roto4.replaceAll("\\s", "");
        roto5 = roto5.replaceAll("\\s", "");
        roto6 = roto6.replaceAll("\\s", "");
        roto7 = roto7.replaceAll("\\s", "");
        roto8 = roto8.replaceAll("\\s", "");
        Bb = Bb.replaceAll("\\s", "");
        Cc = Cc.replaceAll("\\s", "");
        Rotor k = new Rotor("I", roto1, "A", 'A');
        Machine M = new Machine();
        Main F = new Main();
        F.buildRotors();
        F.configure(M, "* B I II III AAAA");
        System.out.println(F.isConfigurationLine("* B I II III AAAA"));
        String[] rot = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII"};
        String j = "* B I II III AAAA";
        String[] split = j.split(" ");
        System.out.println(Arrays.toString(split));
        boolean rott = false;
        for (int i = 0; i < 8; i += 1) {
            if (split[2].equals(rot[i])) {
                rott = true;
            }
        }
        String f = "";
        String [] names = f.split(" ");
        System.out.println(Arrays.toString(names));
        System.out.println(-5 % 6);
    }
}
   
