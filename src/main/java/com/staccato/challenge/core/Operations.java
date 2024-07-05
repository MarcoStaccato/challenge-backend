package com.staccato.challenge.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Operations {

  public static int addition(int num1, int num2) {
    return num1 + num2;
  }

  public static int substraction(int num1, int num2) {
    return num1 - num2;
  }

  public static int multiplication(int num1, int num2) {
    return num1 * num2;
  }

  //TODO: validate special cases
  public static int division(int num1, int num2) {
    return num1/num2;
  }

  public static double sqrt(int num1) {
    return Math.sqrt(num1);
  }

  public static String random() {
    String result = "";
    try {
      Document doc = Jsoup.connect("https://www.random.org/strings/?num=1&len=8&loweralpha=on&unique=on&format=plain&rnd=new").get();
      Elements randomElements = doc.select("body");
      result = randomElements.first().firstChild().toString().split("\n")[1];
    } catch (IOException e) {
      return "An error occurred, please try again";
    }

    return  result;
  }
}
