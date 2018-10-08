package com.mycompany.app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;


/**
 * Hello world!
 *
 */
public class App {

  public static double calculate(ArrayList<Integer> midterm_exam,int midterm_exam_ratio, ArrayList<Integer> final_exam,int final_exam_ratio) {
    double result = 0;
    int summation_array = getSumOfArrayList(midterm_exam);
    int summation_array2 = getSumOfArrayList(final_exam);
    double average_array = summation_array / midterm_exam.size();
    double average_array2 = summation_array2 / final_exam.size();
    result = (average_array * midterm_exam_ratio) + (average_array2 * final_exam_ratio);
    return result;

  }

  private static int getSumOfArrayList(ArrayList<Integer> array) {
    int sum = 0;
    for(int num : array) {
      sum += num;
    }
    return sum;

  }

  public static boolean search(ArrayList<Integer> array, int e) {
    System.out.println("inside search");
    if (array == null) return false;
    for (int elt : array) {
      if (elt == e) return true;
    }
    return false;
  }

  public static void main(String[] args) {

    port(getHerokuAssignedPort());
    get("/", (req, res) -> "Hello, World");
    post("/compute", (req, res) -> {
    //System.out.println(req.queryParams("input1"));
    //System.out.println(req.queryParams("input2"));
    String input1 = req.queryParams("input1");
    java.util.Scanner sc1 = new java.util.Scanner(input1);
    sc1.useDelimiter("[;\r\n]+");
    java.util.ArrayList<Integer> inputList_1 = new java.util.ArrayList<>();
    while (sc1.hasNext())
    {
      int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
      inputList_1.add(value);
    }
    System.out.println(inputList_1);
    String input2 = req.queryParams("input2").replaceAll("\\s","");
    int input2AsInt = Integer.parseInt(input2);

    String input3 = req.queryParams("input3");
    java.util.Scanner sc2 = new java.util.Scanner(input3);
    sc1.useDelimiter("[;\r\n]+");
    java.util.ArrayList<Integer> inputList_2 = new java.util.ArrayList<>();
    while (sc1.hasNext())
    {
      int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
      inputList_2.add(value);
    }
    System.out.println(inputList_2);

    String input4 = req.queryParams("input4").replaceAll("\\s","");
    int input4AsInt = Integer.parseInt(input4);

    double result = calculate(inputList_1, input2AsInt, inputList_2, input4AsInt);
    //boolean result = App.search(inputList_1, input2AsInt);
    Map map = new HashMap();
    map.put("result", result);
    return new ModelAndView(map, "compute.mustache");
    }, new MustacheTemplateEngine());
    get("/compute",
        (rq, rs) -> {
          Map map = new HashMap();
          map.put("result", "not computed yet!");
          return new ModelAndView(map, "compute.mustache");
        },
        new MustacheTemplateEngine());
  }
  static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
        return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 4567;
  }
}



