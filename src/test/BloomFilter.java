package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

public class BloomFilter {
    BitSet bitset;
    MessageDigest md;
    String[] algs;
    BigInteger bi;
    public BloomFilter(int bitlength, String... algs){
        bitset = new BitSet(bitlength);
        this.algs = algs;
    }
    public void add(String s) {
        int val;
        for(String i : algs){
            try {
                md = MessageDigest.getInstance(i);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            byte[] bits = md.digest(s.getBytes());
            bi = new BigInteger(bits);
            val = (bi.intValue()) % (bitset.size());
            bitset.set(Math.abs(val));
        }
    }
    public boolean contains(String s)  {
        int val;
        for(String i : algs){
            try {
                md = MessageDigest.getInstance(i);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            byte[] bits = md.digest(s.getBytes());
            bi = new BigInteger(bits);
            val = bi.intValue() % bitset.size();
            if(!bitset.get(Math.abs(val))){
                return false;
            }
        }
        return true;
    }
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < bitset.size(); i++){
            if(bitset.get(i)){
                res.append("1");
            }else{
                res.append("0");
            }
        }
        while(true){
            if(res.charAt(res.length()-1) == '0'){
                res.deleteCharAt(res.length()-1);
            }else{
                break;
            }
        }
        return res.toString();
    }
}
