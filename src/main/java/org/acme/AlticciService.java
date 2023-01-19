package org.acme;

import javax.enterprise.context.ApplicationScoped;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class AlticciService {

    static Map<Integer, BigInteger> cache = new ConcurrentHashMap<>();
    

    public static BigInteger alticci(int index) {
        cache.putIfAbsent(0, new BigInteger("0"));
        cache.putIfAbsent(1,new BigInteger("1"));
        cache.putIfAbsent(2,new BigInteger("1"));
        int calculatedindex = 2;

        for(int i = index; i>0; i--){ //find the largest number in the alticci series that is already in the cache
            if(cache.containsKey(i)){
                calculatedindex=i;
                break;
            }
        }
		
		for(int i=calculatedindex; i<index; i++) { //calculate the remaining values
			BigInteger newfib = cache.get(i-2).add(cache.get(i-1));
            cache.put(i+1, newfib);
		}
		return cache.get(index);
    }

    public String getAlticci(int index) {
        try{
            return alticci(index).toString();
        }catch(Exception e){
            return ""+e;
        }
    }

}