package day16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day16Test {

    @Test
    void firstA() {
        int res = Day16.first("8A004A801A8002F478");
        assertEquals(16, res);
    }

    @Test
    void firstB() {
        int res = Day16.first("620080001611562C8802118E34");
        assertEquals(12, res);
    }

    /*

    C200B40A82 finds the sum of 1 and 2, resulting in the value 3.
    04005AC33890 finds the product of 6 and 9, resulting in the value 54.
    880086C3E88112 finds the minimum of 7, 8, and 9, resulting in the value 7.
    CE00C43D881120 finds the maximum of 7, 8, and 9, resulting in the value 9.
    D8005AC2A8F0 produces 1, because 5 is less than 15.
    F600BC2D8F produces 0, because 5 is not greater than 15.
    9C005AC2F8F0 produces 0, because 5 is not equal to 15.
    9C0141080250320F1802104A08 produces 1, because 1 + 3 = 2 * 2.

     */

    @Test
    void secondA() {
        assertEquals(3L, Day16.second("C200B40A82"));
        assertEquals(54L, Day16.second("04005AC33890"));
        assertEquals(7L, Day16.second("880086C3E88112"));
        assertEquals(9L, Day16.second("CE00C43D881120"));
        assertEquals(1L, Day16.second("D8005AC2A8F0"));
        assertEquals(0L, Day16.second("F600BC2D8F"));
        assertEquals(0L, Day16.second("9C005AC2F8F0"));
        assertEquals(1L, Day16.second("9C0141080250320F1802104A08"));
    }

    @Test
    void toBinary(){
        assertEquals("0001", Day16.toBinary("1"));
        assertEquals("1010101111001101", Day16.toBinary("ABCD"));
        assertEquals("110100101111111000101000", Day16.toBinary("D2FE28"));
    }

}