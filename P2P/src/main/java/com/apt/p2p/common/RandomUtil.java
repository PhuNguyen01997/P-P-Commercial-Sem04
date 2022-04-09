package com.apt.p2p.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Random;

@Service
public final class RandomUtil {
    private static Random random = new Random();

    public static Integer getRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static Integer getRandomNumber(int max) {
        return random.nextInt(max + 1);
    }

    public static Integer getRandomNumber() {
        return random.nextInt(Integer.MAX_VALUE);
    }

    public static String getRandomPhone(){
        return getRandomStringNumber(10);
    }

    public static String getRandomString(int length){
        byte[] array = new byte[length];
        String saltAlpha = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = (int) (random.nextFloat() * saltAlpha.length());
            stringBuilder.append(saltAlpha.charAt(index));
        }

        return stringBuilder.toString();
    }

    public static String getRandomString(int min, int max){
        Integer randomLength = getRandomNumber(min, max);
        return getRandomString(randomLength);
    }

    public static String getRandomStringNumber(int length){
        String saltNumber = "0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (random.nextFloat() * saltNumber.length());
            stringBuilder.append(saltNumber.charAt(index));
        }

        return stringBuilder.toString();
    }

    public static String getRandomStringNumber(int min, int max){
        Integer randomLength = getRandomNumber(min, max);
        return getRandomStringNumber(randomLength);
    }
}
