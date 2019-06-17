package com.example.demo;

import com.github.houbb.word.checker.core.impl.EnWordChecker;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

public class RandomTest {

    @Test
    public void wordsTest() {
        String str = RandomStringUtils.randomAlphabetic(10).toLowerCase();
        System.out.println(str);
        String result = EnWordChecker.getInstance().correct(str);
        System.out.println(result);
    }
}
