import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    public class heroes {

        static int getHerokuAssignedPort() {
            ProcessBuilder processBuilder = new ProcessBuilder();
            if (processBuilder.environment().get("PORT") != null) {
                return Integer.parseInt(processBuilder.environment().get("PORT"));
            }
            return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
        }


        public  static void main(String[] args){
            port(getHerokuAssignedPort());
            staticFiles.location("/public"); // Static files Directory for storing things like CSS and Images
            section userSection = new section();
            ArrayList existingSol= soldier.all();
            System.out.println(existingSol);



            /************************************
             * Homepage Route
             ***********************************/
            get("/", (request, response) ->{return new ModelAndView(new HashMap(), "index.hbs");
            },new HandlebarsTemplateEngine());



            /************************************
             * Squad Build Route
             ***********************************/
            get("/build_squad", (request, response) -> {
                Map <String, ArrayList<soldier>> model = new HashMap<>();
                String user = request.queryParams("user"); // Gets a user from the form in homepage
                soldier corporal = new soldier(user, 100, 99); // Adds SMG (YOU) to section - Default- User is automatically in the Section
                ArrayList mySectionMembersArrayList = soldier.all(); //Gets existing Soldiers
                model.put("mySectionMembers", mySectionMembersArrayList); // Maps existing soldiers to model

                return new ModelAndView(model, "buildSquad.hbs");
            }, new HandlebarsTemplateEngine());



            /************************************
             * Posts  Units to the Section
             ************************************/

            post("/build_squad/add", (request, response)-> {
                Map<String, Object> model = new HashMap<>();
                String brenGunnerName= request.queryParams("setBrenName"); //Sets bren gunner Name
                int brenGunnerHealth = soldier.brenGunnerHealth; //Sets bren gunner health
                int brenGunnerSkill = soldier.brenGunnerSkill ; // Sets bren gunner skill

                String thompsonGunnerName= request.queryParams("setThompsonName"); //Sets bren gunner Name
                int thompsonGunnerHealth = soldier.thompsonGunnerHealth; //Sets bren gunner health
                int thompsonGunnerSkill = soldier.thompsonGunnerSkill; // Sets bren gunner skill

                String rifleManName= request.queryParams("setrifleManName"); //Sets bren gunner Name
                int rifleManHealth = soldier.rifleManHealth; //Sets bren gunner health
                int rifleManSkill = soldier.rifleManSkill;

                //int rifleManSkill = Integer.parseInt(request.queryParams("setrifleManSkill")) ; // Sets bren gunner skill


                soldier brenGunner = new soldier(brenGunnerName,brenGunnerHealth,brenGunnerSkill); // Adds a Brengunner to your Section
                soldier thompsonGunner = new soldier(thompsonGunnerName,thompsonGunnerHealth,thompsonGunnerSkill); // Adds a Brengunner to your Section
                soldier rifleMan = new soldier(rifleManName,rifleManHealth,rifleManSkill);
                //soldier.members.add(new soldier(thompsonGunnerName,thompsonGunnerHealth,thompsonGunnerSkill)); // Adds a Brengunner to your Section
                //soldier.members.add(new soldier(rifleManName,rifleManHealth,rifleManSkill)); // Adds a Brengunner to your Section
                response.redirect("/build_squad");
                return null;
            });

            /************************************
             * Assigns Soldiers to Fireteam Section
             ************************************/

            get("/deploy", (request, response) -> {
                Map<String,ArrayList<soldier>> model= new HashMap<String, ArrayList<soldier>>();
                ArrayList mySoldiers = soldier.all();
                ArrayList team1soldiers= fireteam.getTeam1();
                ArrayList team2soldiers= fireteam.getTeam2();
                model.put("allSoldiers", mySoldiers);
                model.put ("fireTeam1Soldiers", team1soldiers );
                model.put ("fireTeam2Soldiers", team2soldiers );


                for (int i = 0; i < mySoldiers.size(); i++){

                    Object[] soldierArray = mySoldiers.toArray();
                    int soldierArrayLength =soldierArray.length;

                    System.out.println(soldierArrayLength);


                    System.out.println(soldierArray[i]);

                    int[] soldierIndex= new int[i];
                    //soldierIndex[i] =mySoldiers.indexOf(i);
                    //model.put("sectonSoldiers", mySoldiers.get(i));
                    //model.put("sectionSoldiers", soldierIndex);
                }
                return new ModelAndView(model, "deploy.hbs");
            }, new HandlebarsTemplateEngine());


            post("/assignFireTeam1", (request, response) -> {
                Map <String,Object> model=new HashMap<String,Object>();
                ArrayList<soldier> mySoldiers = soldier.all();
                fireteam.team1.add(mySoldiers.get(Integer.parseInt(request.queryParams("soldierId"))));
                response.redirect("/deploy");
                return null;
            });

            post("/assignFireTeam2", (request, response) -> {
                Map <String,Object> model=new HashMap<String,Object>();
                ArrayList<soldier> mySoldiers = soldier.all();
                fireteam.team2.add(mySoldiers.get(Integer.parseInt(request.queryParams("soldierId"))));
                response.redirect("/deploy");
                return null;
            });

            post("/deploy/removeFromFireTeam", (request,response)->{
                Map <String,Object> model=new HashMap<String,Object>();
                ArrayList<soldier> mySoldiers = soldier.all();
                fireteam.team1.remove(mySoldiers.get(new Integer(Integer.parseInt(request.queryParams("soldierId")))));
                response.redirect("/deploy");
                return null;
            });

        }
    }

