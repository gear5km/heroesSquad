import static spark.Spark.*;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

    public class heroes {
        public static void main(String[] args){
            /************************************
             * Routes Declared Here
             ***********************************/
            get("/index", (request, response) ->{return new ModelAndView(new HashMap(), "index.hbs");
            },new HandlebarsTemplateEngine());
        }
    }

