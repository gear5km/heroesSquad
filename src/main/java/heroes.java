import static spark.Spark.*;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

    public class heroes {
        public static void main(String[] args){
            staticFiles.location("/public"); // Static files

            /************************************
             * Routes Declared Here
             ***********************************/
            get("/", (request, response) ->{return new ModelAndView(new HashMap(), "index.hbs");
            },new HandlebarsTemplateEngine());

            get("/build_squad", (request, response) -> {
                Map<String,Object> model=new HashMap<String, Object>();
                String user = request.queryParams("user");
                model.put("user", user);
                return new ModelAndView(model, buildSquad.hbs);
            }, new HandlebarsTemplateEngine());


        }
    }

