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
            //userSection.members.add(soldier.ThompsonSMG); // Adds SMG (YOU) to section - Default- User is automatically in the Section


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

            /************************************
             * Posts  Units to the Section
             ************************************/

            post("/build_squad", (request, response)-> {
                Map<String,Object> model=new HashMap<String, Object>();
                model.put("userSection", userSection.members); // Gets the section list for display

                String brenGunnerName= request.queryParams("setBrenName"); //Sets bren gunner Name
                int brenGunnerHealth = Integer.parseInt(request.queryParams("setBrenHealth")); //Sets bren gunner health
                int brenGunnerSkill = Integer.parseInt(request.queryParams("setBrenSkill")) ; // Sets bren gunner skill

                String thompsonGunnerName= request.queryParams("setThompsonName"); //Sets bren gunner Name
                int thompsonGunnerHealth = Integer.parseInt(request.queryParams("setThompsonHealth")); //Sets bren gunner health
                int thompsonGunnerSkill = Integer.parseInt(request.queryParams("setThompsonSkill")) ; // Sets bren gunner skill

                String rifleManName= request.queryParams("setrifleManName"); //Sets bren gunner Name
                int rifleManHealth = Integer.parseInt(request.queryParams("setrifleManHealth")); //Sets bren gunner health
                int rifleManSkill = Integer.parseInt(request.queryParams("setrifleManSkill")) ; // Sets bren gunner skill


                userSection.members.add(new soldier(brenGunnerName,brenGunnerHealth,brenGunnerSkill)); // Adds a Brengunner to your Section
                userSection.members.add(new soldier(thompsonGunnerName,thompsonGunnerHealth,thompsonGunnerSkill)); // Adds a Brengunner to your Section
                userSection.members.add(new soldier(rifleManName,rifleManHealth,rifleManSkill)); // Adds a Brengunner to your Section

                 for(int i=0; i>userSection.members.size(); i++){
                     int [] soldierHealth= new int[3];
                     int [] soldierSkill= new int[3];
                      soldierHealth[i]= userSection.members.get(i).health;
                      soldierSkill[i]= userSection.members.get(i).skill;

                     model.put("userSectionHealth", soldierHealth);
                     model.put("userSectionSkill", soldierSkill);
                }

                return new ModelAndView(model, "buildSquad.hbs");
            }, new HandlebarsTemplateEngine());

            post("/deploy", (request, response) -> {
                Map<String,Object> model=new HashMap<String, Object>();
                model.put("userSectionMembers", userSection.members);
                return new ModelAndView(model, "deploy.hbs");
            }, new HandlebarsTemplateEngine());

        }
    }

