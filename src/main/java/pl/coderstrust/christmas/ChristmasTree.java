package pl.coderstrust.christmas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2018-02-04.
 */
public class ChristmasTree {

  public static List<String> christmasTree(int height) {
    List<String> result = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      String line = "";
      for (int j = 0; j < height - i; j++) {
        line += " ";
      }
      for (int j = 0; j <= i * 2; j++) {
        line += "*";
      }
      for (int j = 0; j < height - i; j++) {
        line += " ";
      }
      result.add(line);
    }
    String treeTrunk = "";
    for (int j = 0; j < height - 1; j++) {
      treeTrunk += " ";
    }
    treeTrunk += "***";
    for (int j = 0; j < height - 1; j++) {
      treeTrunk += " ";
    }
    result.add(treeTrunk);
    return result;
  }
}