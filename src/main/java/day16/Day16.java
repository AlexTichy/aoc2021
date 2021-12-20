package day16;

import com.sun.source.tree.Tree;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static Utils.Utils.readFile;

public class Day16 {

    public static void main(String[] args) {

        String input_file = args[0];

        int first_solution = first(readFile(input_file).get(0));
        System.out.println("First solution: " + first_solution);

        long second_solution = second(readFile(input_file).get(0));
        System.out.println("Second solution: " + second_solution);
    }

    static int first(String message) {
        String bin_message = toBinary(message);
        PacketParser packetParser = new PacketParser(bin_message);
        return packetParser.get_sum_of_version_numbers();
    }

    static long second(String message) {
        String bin_message = toBinary(message);
        PacketParser packetParser = new PacketParser(bin_message);
        return packetParser.calc();
    }


    static String toBinary(String hexString){
        StringBuilder bin = new StringBuilder();
        for (String s: hexString.split("")) {  //just every character
            String binaryString = Integer.toBinaryString(Integer.valueOf(s, 16));
            bin.append(String.format("%4s", binaryString).replace(' ', '0'));
        }
        return bin.toString();
    }


    static class PacketParser{

        @SuperBuilder
        static abstract class Packet{
            int start_pos;
            int end_pos;
        }

        @SuperBuilder
        static class OperatorPacket extends Packet {
            List<Packet> subpackets;
        }

        @SuperBuilder
        static class LiteralPacket extends Packet{
            String value;
        }

        OperatorPacket packet_tree;
        String description;

        PacketParser(String description){
            this.description = description;
            packet_tree = OperatorPacket.builder().start_pos(0).subpackets(new ArrayList<>()).build();
            parse(0, packet_tree);
        }

        public long calc(){
            return calc(packet_tree.subpackets.get(0));
        }

        private long calc(Packet p){
            int type_id = get_type_id(p);
            if(type_id == 4){
                return Long.parseLong(((LiteralPacket) p).value, 2);
            }
            OperatorPacket op = (OperatorPacket) p;
            switch(type_id){
                case 0:
                    return op.subpackets.stream().mapToLong(this::calc).sum();
                case 1:
                    long prod = 1;
                    for(Packet subpacket: op.subpackets){
                        prod *= calc(subpacket);
                    }
                    return prod;
                case 2:
                    return op.subpackets.stream().mapToLong(this::calc).min().getAsLong();
                case 3:
                    return op.subpackets.stream().mapToLong(this::calc).max().getAsLong();
                case 5:
                    return calc(op.subpackets.get(0)) > calc(op.subpackets.get(1)) ? 1L : 0L;
                case 6:
                    return calc(op.subpackets.get(0)) < calc(op.subpackets.get(1)) ? 1L : 0L;
                case 7:
                    return calc(op.subpackets.get(0)) == calc(op.subpackets.get(1)) ? 1L : 0L;
                default:
                    return -1L;
            }
        }

        public int get_sum_of_version_numbers(){
            return get_sum_of_version_numbers(packet_tree.subpackets.get(0));
        }
        
        private int get_sum_of_version_numbers(Packet p){
            int sum = get_version_number(p);
            if(packet_is_operator(p.start_pos)){
                for(Packet subpacket: ((OperatorPacket) p).subpackets){
                    sum += get_sum_of_version_numbers(subpacket);
                }
            }
            return sum;
        }

        private int parse(int start_pos, OperatorPacket super_packet){
            if(packet_is_operator(start_pos)){
                if(description.charAt(start_pos + 6) == '0'){
                    return parse_operator_length_type_0(start_pos, super_packet);
                }else{
                    return parse_operator_length_type_1(start_pos, super_packet);
                }
            }else{
                return parse_literal(start_pos, super_packet);
            }
        }

        private int parse_operator_length_type_0(int start_pos, OperatorPacket super_packet){
            int total_length = parse_from(start_pos+7, start_pos+22);
            OperatorPacket operator_packet =  OperatorPacket.builder()
                                                        .start_pos(start_pos)
                                                        .end_pos(start_pos + 22 + total_length)
                                                        .subpackets(new ArrayList<>())
                                                        .build();
            super_packet.subpackets.add(operator_packet);
            int start_of_subpacket = start_pos + 22;
            int end_of_subpacket;
            do{
                end_of_subpacket = parse(start_of_subpacket, operator_packet);
                start_of_subpacket = end_of_subpacket;
            }while(end_of_subpacket < operator_packet.end_pos);
            return operator_packet.end_pos;
        }

        private int parse_operator_length_type_1(int start_pos, OperatorPacket super_packet){

            int no_subpackets = parse_from(start_pos+7, start_pos+18);

            OperatorPacket operator_packet =  OperatorPacket.builder()
                    .start_pos(start_pos)
                    .subpackets(new ArrayList<>())
                    .build();

            int subpacket_count = 0;
            int start_of_subpacket = start_pos + 18;
            int end_of_subpacket;
            do{
                end_of_subpacket = parse(start_of_subpacket, operator_packet);
                start_of_subpacket = end_of_subpacket;
                subpacket_count ++;
            }while(subpacket_count < no_subpackets);

            operator_packet.end_pos = end_of_subpacket;
            super_packet.subpackets.add(operator_packet);

            return operator_packet.end_pos;
        }

        private int parse_literal(int start_pos, OperatorPacket super_packet){
            String value = "";
            int prefix_pos = start_pos + 6;
            while(description.charAt(prefix_pos) == '1'){ //inner group
                value += description.substring(prefix_pos + 1, prefix_pos + 5);
                prefix_pos += 5;
            }
            value += description.substring(prefix_pos + 1, prefix_pos + 5);
            LiteralPacket lp = LiteralPacket.builder().start_pos(start_pos).end_pos(prefix_pos + 5).value(value).build();
            super_packet.subpackets.add(lp);
            return lp.end_pos;
        }

        private boolean packet_is_operator(int start_pos){
            if(description.substring(start_pos + 3, start_pos + 6).equals("100")){
                return false;
            }
            return true;
        }

        private int get_type_id(Packet p){
            return parse_from(p.start_pos+3, p.start_pos+6);
        }

        private int get_version_number(Packet p){
            return parse_from(p.start_pos, p.start_pos +3);
        }

        private int parse_from(int start, int end){
            return Integer.parseInt(description.substring(start, end), 2);
        }

    }




}
