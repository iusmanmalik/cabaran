package com.cabaran.cabaranapi.Utils;

import java.math.BigDecimal;
import java.util.Random;

public class Utils {

  /**
   * Generate random id number of a particular length .
   *
   * @param length
   * @return the id number
   */
  public static long generateRandomNumber(int length) {
    Random random = new Random();
    char[] digits = new char[length];
    digits[0] = (char) (random.nextInt(9) + '1');
    for (int i = 1; i < length; i++) {
      digits[i] = (char) (random.nextInt(10) + '0');
    }
    return new BigDecimal(digits).longValue();
  }
}
