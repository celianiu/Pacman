package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.adapter.DispatchAdapter;
import edu.rice.comp504.model.enumtype.DirectionEnumType;
import edu.rice.comp504.model.map.GameMap;
import edu.rice.comp504.model.map.MapSetting;

import static spark.Spark.*;


/**
 * The paint world controller creates the adapter(s) that communicate with the view.
 * The controller responds to requests from the view after contacting the adapter(s).
 */
public class GameController {
    public static String DIR = "dir";

    /**
     * The main entry point into the program.
     *
     * @param args The program arguments normally specified on the cmd line
     */
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getHerokuAssignedPort());
        Gson gson = new Gson();
        DispatchAdapter dis = new DispatchAdapter();


        /*
            receive this request per 100 ms.
         */
        post("/move", (request, response) -> {
            String direction = request.body().split("=")[1];
            DirectionEnumType directionEnumType = null;
            switch (direction) {
                case "UP":
                    directionEnumType = DirectionEnumType.UP;
                    break;
                case "LEFT":
                    directionEnumType = DirectionEnumType.LEFT;
                    break;
                case "DOWN":
                    directionEnumType = DirectionEnumType.DOWN;
                    break;
                case "RIGHT":
                    directionEnumType = DirectionEnumType.RIGHT;
                    break;
                case "NULL":
                    directionEnumType = null;
                    break;
                default:
                    break;
            }
            GameMap gameMap = dis.moveObject(directionEnumType);
            return gson.toJson(gameMap);
        });

        post("/reset", (request, response) -> {
            //return gson.toJson("new ball");
            String gameLevel = request.queryParams("gameLevel");
            String ghostNum = request.queryParams("ghostNum");
            if (gameLevel == null) {
                String[] params = request.body().split("&");
                gameLevel = params[0].split("=")[1];
                ghostNum = params[1].split("=")[1];
            }
            MapSetting mapSetting = new MapSetting();
            /*
              parse map setting json in to a object
             */
            GameMap gameMap = dis.reset(mapSetting);
            dis.addToMap(gameLevel, ghostNum);
//            return gson.toJson(gameMap.getMapSetting());
            return gson.toJson(gameMap);
        });

        post("/respawn", (request, response) -> {
            GameMap gameMap = dis.respawn();
            return gson.toJson(gameMap);
        });

        notFound((request, response) -> {
            response.redirect("/");
            return "{\"message\":\"Custom 404\"}";
        });


    }

    /**
     * Get the heroku assigned port number.
     *
     * @return The port number
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
