package com.project2;

import java.util.*;

public class Light {

    private String present = "";
    private ArrayList<String> previous = new ArrayList<String>();

    public Light(String p) {
        this.present = p;
        for (int i = 0; i < p.length(); i++) {
            previous.add("");
        }
    }

    public void setprevious(String pv, int button) {
        previous.set(button, pv);
    }

    public void setbroken(int po) {
        char Temp[] = present.toCharArray();
        if (Temp[po] == '1') {
            Temp[po] = '3';
        } else {
            Temp[po] = '2';
        }
        present = String.valueOf(Temp);
    }

    public String getpresent() {
        return present;
    }
    public String getpresentconverse() {
        char Temp[] = present.toCharArray();
        for(int i = 0 ;i< Temp.length;i++){
            if(Temp[i]=='2'){
                Temp[i] = '0';
            }if(Temp[i]=='3'){
                Temp[i] = '1';
            }            
        }
        String converse =String.valueOf(Temp);
        return converse;
    }

    public String gettoggle(Light pre) {
        String o_f = "";
        int po = previous.indexOf(pre.getpresent());
        char tog = pre.getpresent().charAt(po);
        switch (tog) {
            case '0':
                o_f = "on";
                break;
            case '1':
                o_f = "off";
                break;
            case '2':
                o_f = "on";
                break;
            case '3':
                o_f = "off";
                break;
            default:
                System.exit(0);
                break;
        }
        return o_f;
    }
    
    public int getpo(Light pre) {
        int po = previous.indexOf(pre.getpresent());
        return po;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Light Other = (Light) o;
        return Objects.equals(present, Other.getpresent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(present);
    }

    @Override
    public String toString() {
        return present;
    }
}
