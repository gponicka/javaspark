import org.bson.types.ObjectId;
import spark.ModelAndView;
import org.bson.Document;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class Controller {
    public Controller(){
        Spark.port(8082);
    }

    public static void main(String[] args) throws IOException {
        staticFiles.location("/public");
        Controller c = new Controller();
        c.InitializeRoutes();
    }

    private void InitializeRoutes(){
        get("/", (request, response) -> {
            Map<String, Object> viewObjects = new HashMap<>();
            viewObjects.put("message", "Welcome to Spark with MongoDB");
            viewObjects.put("templateName", "home.ftl");

            return new FreemarkerEngine().render(new ModelAndView(viewObjects, "index.ftl"));
        });

        get("/new-user", (request, response) -> {
            Map<String, Object> viewObjects = new HashMap<>();
            viewObjects.put("saved", false);
            viewObjects.put("templateName", "createuser.ftl");

            return new FreemarkerEngine().render(new ModelAndView(viewObjects, "index.ftl"));
        });

        post("/new-user", (request, response) -> {
            String phone = CheckOptionalParams(request.queryParams("phone"));
            String email = CheckOptionalParams(request.queryParams("email"));

            User user = new User();
            user.setFirstName(request.queryParams("firstname"));
            user.setLastName(request.queryParams("lastname"));
            user.setGender(request.queryParams("gender"));
            user.setAge(Integer.parseInt(request.queryParams("age")));
            user.setPhone(phone);
            user.setEmail(email);
            user.setAddress(request.queryParams("address"));

            Map<String, Object> viewObjects = new HashMap<>();
            Boolean saved = new CRUD().CreateUser(user);

            viewObjects.put("saved", saved);
            viewObjects.put("templateName", "createuser.ftl");
            return new FreemarkerEngine().render(new ModelAndView(viewObjects, "index.ftl"));
        });

        get("/profile/:id", (request, response) -> {
            ObjectId _id = new ObjectId(request.params(":id"));
            Document document = new CRUD().GetUserByID(_id);

            Map<String, Object> viewObjects = new HashMap<>();
            viewObjects.put("document", document);
            viewObjects.put("templateName", "profile.ftl");

            return new FreemarkerEngine().render(new ModelAndView(viewObjects, "index.ftl"));
        });

        get("/update-user/:id", (request, response) -> {
            ObjectId _id = new ObjectId(request.params(":id"));
            Document document = new CRUD().GetUserByID(_id);

            Map<String, Object> viewObjects = new HashMap<>();
            viewObjects.put("document", document);
            viewObjects.put("templateName", "updateuser.ftl");
            return new FreemarkerEngine().render(new ModelAndView(viewObjects, "index.ftl"));
        });

        post("/update-user/:id", (request, response) -> {
            ObjectId _id = new ObjectId(request.params(":id"));

            String phone = CheckOptionalParams(request.queryParams("phone"));
            String email = CheckOptionalParams(request.queryParams("email"));

            User user = new User();
            user.setFirstName(request.queryParams("firstname"));
            user.setLastName(request.queryParams("lastname"));
            user.setGender(request.queryParams("gender"));
            user.setAge(Integer.parseInt(request.queryParams("age")));
            user.setPhone(phone);
            user.setEmail(email);
            user.setAddress(request.queryParams("address"));

            Map<String, Object> viewObjects = new HashMap<>();
            if(new CRUD().UpdateUser(user, _id)){
                viewObjects.put("heading", "Updated");
                viewObjects.put("message", "The user information was successfully updated.");
            }else{
                viewObjects.put("heading", "Unsuccessful Update");
                viewObjects.put("message", "The user information was NOT successfully updated.");
            }
            viewObjects.put("title", "Updated");
            viewObjects.put("templateName", "updateanddelete.ftl");
            return new FreemarkerEngine().render(new ModelAndView(viewObjects, "index.ftl"));
        });

        //displays all users in the database
        get("/user-list", (request, response) -> {
            String find = request.params(":id");
            String title = "List of Users",
                   description = "Below are the list of users. Click View to see the full profile.";
            Map<String, Object> viewObjects = new HashMap<>();
            List<Document> searchResult = new CRUD().SearchUser(find);

            viewObjects.put("title", title);
            viewObjects.put("description", description);
            viewObjects.put("searchResult", searchResult);
            viewObjects.put("templateName", "userlist.ftl");
            return new FreemarkerEngine().render(new ModelAndView(viewObjects, "index.ftl"));
        });

        post("/search", (request, response) -> {
            String find = request.queryParams("search");
            if(find.equals("") || find.equals(" ")) {
                response.redirect("/user-list");
            }else {
                response.redirect("/search/" + find);
            }

            return response;
        });

        post("/mobile", (request, response) -> {
            String find = request.queryParams("search2");
            if(find.equals("") || find.equals(" ")) {
                response.redirect("/user-list");
            }else {
                response.redirect("/search/" + find);
            }

            return response;
        });

        get("/search/:find", (Request request, Response response) -> {
            String find = request.params(":find");
            List<Document> searchResult;
            Map<String, Object> viewObjects = new HashMap<>();

            if(find.equalsIgnoreCase("Male") || find.equalsIgnoreCase("Female")){
                searchResult = new CRUD().SearchByGender(find);
                viewObjects.put("templateName", "userbygender.ftl");
            }else if(find.equals("group-by-lastname")){
                searchResult = new CRUD().SearchByLastname();
                viewObjects.put("templateName", "userbylastname.ftl");
            }else{
                searchResult = new CRUD().SearchUser(find);
                viewObjects.put("templateName", "userlist.ftl");
            }

            String title = "Showing Results for " + find,
                   description = searchResult.size() + " Record(s) found";

            viewObjects.put("title", title);
            viewObjects.put("description", description);
            viewObjects.put("searchResult", searchResult);

            return new FreemarkerEngine().render(new ModelAndView(viewObjects, "index.ftl"));
        });

        get("/profile/deleted/:id", (request, response) -> {
            Document doc = new CRUD().GetUserByID(new ObjectId(request.params(":id")));
            Map<String, Object> viewObjects = new HashMap<>();
            if(new CRUD().DeleteUser(new ObjectId(request.params(":id")))){
                viewObjects.put("heading", "Deleted");
                viewObjects.put("message", doc.get("fullname") + " was successfully removed from the list of users.");
            }else{
                viewObjects.put("heading", "Unsuccessful Deletion");
                viewObjects.put("message", doc.get("fullname") + " was Not successfully removed from the list of users.");
            }
            viewObjects.put("title", "Deleted");
            viewObjects.put("templateName", "updateanddelete.ftl");
            return new FreemarkerEngine().render(new ModelAndView(viewObjects, "index.ftl"));
        });
    }

    private static String CheckOptionalParams(String params){
        if(!params.equals(null) || !params.equals("")){
            return params;
        }else {
            return params;
        }
    }
}

