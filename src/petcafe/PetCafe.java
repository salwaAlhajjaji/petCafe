/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petcafe;

/**
 *
 * @author Rahaf
 */
public class PetCafe {

    public static enum Services {
        GENRALE_EXAMINATION, MASSAGE, HAIR_CUT, NAILS
    }

    public static class PriceCalaulat {

        Services s;
        double price;

        public PriceCalaulat(Services s) {
            this.s = s;
        }

        public String toString() {

            switch (s) {
                case GENRALE_EXAMINATION:
                    price += 50;
                case MASSAGE:
                    price += 100;
                case HAIR_CUT:
                    price += 30;
                case NAILS:
                    price += 100;
                default:
                    price = 0;
                    return " price is " + price;
            }

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        petCafeForm forme = new petCafeForm();
        // TODO code application logic here
        PriceCalaulat s1 = new PriceCalaulat(Services.HAIR_CUT);
        System.out.println(s1);
    }

}
