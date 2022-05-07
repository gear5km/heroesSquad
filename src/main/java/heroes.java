import static spark.Spark.*;
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

        }
    }

