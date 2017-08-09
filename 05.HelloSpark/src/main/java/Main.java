import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World"); //get http method din URI-ul: /hello + prin lambda transmite Hello World
        get("/", (req, res) -> "Hello Students");
    }
}
