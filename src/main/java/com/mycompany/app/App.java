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
  public static double calculate(ArrayList<Integer> array,int ratio1, ArrayList<Integer> array2,int ratio2) {
    double result = 0;
    int summation_array = 0;
    int summation_array2 = 0;
    summation_array = getSumOfArrayList(array);
    summation_array2 = getSumOfArrayList(array2);
    int average_array = summation_array / array.size();
    int average_array2 = summation_array2 / array2.size();
    result = (average_array * ((double)ratio1/100)) + (average_array2 * ((double)ratio2/100));
    return result;

  }

  private static int getSumOfArrayList(ArrayList<Integer> array) {
    int sum = 0;
    for(int num : array) {
      sum += num;
    }
    return sum;

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
        java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
        while (sc1.hasNext())
        {
          int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
          inputList.add(value);
        }
        System.out.println(inputList);


        String input3 = req.queryParams("input3");
        java.util.Scanner sc2 = new java.util.Scanner(input3);
        sc2.useDelimiter("[;\r\n]+");
        java.util.ArrayList<Integer> inputList2 = new java.util.ArrayList<>();
        while (sc2.hasNext())
        {
          int value = Integer.parseInt(sc2.next().replaceAll("\\s",""));
          inputList2.add(value);
        }
        System.out.println(inputList);

        String input2 = req.queryParams("input2").replaceAll("\\s","");
        int input2AsInt = Integer.parseInt(input2);


        String input4 = req.queryParams("input4").replaceAll("\\s","");
        int input4AsInt = Integer.parseInt(input2);

        double result = App.calculate(inputList,inputList.size(), inputList2,inputList2.size());

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
      return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
  }
}



