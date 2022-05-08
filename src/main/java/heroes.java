import static spark.Spark.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

    public class heroes {

        public  static void main(String[] args){
            staticFiles.location("/public"); // Static files Directory for storing things like CSS and Images
            section userSection = new section();
            userSection.members.add(soldier.ThompsonSMG); // Adds SMG (YOU) to section - Default- User is automatically in the Section


            /************************************
             * Homepage Route
             ***********************************/
            get("/", (request, response) ->{return new ModelAndView(new HashMap(), "index.hbs");
            },new HandlebarsTemplateEngine());

            /************************************
             * Squad Build Route
             ***********************************/
            get("/build_squad", (request, response) -> {
                Map<String,Object> model=new HashMap<String, Object>();
                String user = request.queryParams("user"); // Gets a user from the form in homepage
                model.put("user", user);
                //model.put("user", request.session().attribute("username"));
                return new ModelAndView(model, "buildSquad.hbs");
            }, new HandlebarsTemplateEngine());

            post("/build_squad", (request, response)-> {
                Map<String,Object> model=new HashMap<String, Object>();
                model.put("userSection", userSection.members);
                userSection.members.add(soldier.brenGunner); // Adds a Brengunner to your Section
                model.put("userSectionHealth", userSection.members.get(0).health);
                return new ModelAndView(model, "buildSquad.hbs");
            }, new HandlebarsTemplateEngine());


        }
    }

