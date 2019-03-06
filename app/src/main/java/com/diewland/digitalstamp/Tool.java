package com.diewland.digitalstamp;

import java.util.ArrayList;
import java.util.Collections;

public class Tool {

    public static double calc_distance(int x1, int y1, int x2, int y2){
        double dx = Math.pow(x2 - x1, 2);
        double dy = Math.pow(y2 - y1, 2);
        return Math.sqrt(dx + dy);
    }

    public static ArrayList<Double> build_signature(ArrayList<Integer[]> xys){
        ArrayList<Double> sign = new ArrayList<Double>();

        // full-scan
        double sum_dist = 0.0;
        for(Integer[] xy1 : xys){
            for(Integer[] xy2 : xys) {
                double dist = calc_distance(xy1[0], xy1[1], xy2[0], xy2[1]);
                sign.add(dist);
                sum_dist += dist;
            }
        }

        // convert values in range 0-1
        for(int i=0; i < sign.size(); i++){
            double dist = sign.get(i);
            sign.set(i, dist / sum_dist);
        }

        // fill up to 100 items ( 10x10 - maximum 10 touches per time )
        int items_left = 100 - sign.size();
        for(int i=1; i<=items_left; i++){
            sign.add(0.0);
        }

        // sort max -> min
        Collections.sort(sign);
        Collections.reverse(sign);
        return sign;
    }

    public static Double calc_diff(ArrayList<Double> sign1, ArrayList<Double> sign2){
        Double diff = 0.0;
        if(sign1.size() != sign2.size()){
            return null; // size not match
        }
        for(int i=0; i< sign1.size(); i++){
            diff += Math.abs(sign1.get(i) - sign2.get(i));
        }
        diff *= 100; // x100 for easy read
        return diff;
    }

    public static Double calc_diff2(ArrayList<Integer[]> list1, ArrayList<Integer[]> list2){
        ArrayList<Double> sign1 = Tool.build_signature(list1);
        ArrayList<Double> sign2 = Tool.build_signature(list2);
        return Tool.calc_diff(sign1, sign2);
    }

    public static void main(String[] args){

        ArrayList<Integer[]> xys = new ArrayList<Integer[]>();
        xys.add(new Integer[]{ 0,   0 });
        xys.add(new Integer[]{ 100, 0 });
        xys.add(new Integer[]{ 0,   100 });

        ArrayList<Integer[]> xys2 = new ArrayList<Integer[]>();
        xys2.add(new Integer[]{ 10, 10 });
        xys2.add(new Integer[]{ 90, 5 });
        xys2.add(new Integer[]{ 10, 90 });

        // basic steps
        ArrayList<Double> sign = build_signature(xys);
        ArrayList<Double> sign2 = build_signature(xys2);
        Double diff = calc_diff(sign, sign2);
        System.out.println(diff);

        // shorthand
        System.out.println(Tool.calc_diff2(xys, xys2));
    }

}
